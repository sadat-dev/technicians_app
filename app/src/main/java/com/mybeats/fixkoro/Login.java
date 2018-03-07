package com.mybeats.fixkoro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import musabbir.mybeats.fixkoro.R;

import com.mybeats.fixkoro.util.ConstantStuffs;
import com.mybeats.fixkoro.util.InputChecker;

import static android.graphics.Typeface.BOLD;

/**
 * Created by MUSABBIR MAMUN on 19-Oct-17.
 */
public class Login extends Activity {

    TextView itemsHeader;
    Button sign_up_btn;

    TextView signup_tv, forgetpass_tv;
    Button signin_btn, signup_user_btn, signup_dr_btn;
    EditText email_et, pass_et;
    String email_et_str, pass_et_str;
    private ProgressDialog pDialog;
    final Context context = this;
    JSONObject json;
    SharedPreferences prefs;
    public static final String SP_NAME = "name", SP_EMAIL = "email", SP_PHONE = "phone", SP_USERID = "id_user", SP_TOKEN = "auth_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        InputChecker inpChckObj = new InputChecker();

        forgetpass_tv = (TextView) findViewById(R.id.forgetpass_tv);

        email_et = (EditText) findViewById(R.id.email_et);
        pass_et = (EditText) findViewById(R.id.pass_et);
        email_et.setFilters(new InputFilter[]{inpChckObj.filter});
        pass_et.setFilters(new InputFilter[]{inpChckObj.filter});


        signin_btn = (Button) findViewById(R.id.signin_btn);

        forgetpass_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), ForgetPass.class);
                startActivity(i);
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_et_str = email_et.getText().toString().trim();
                pass_et_str = pass_et.getText().toString().trim();

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email_et_str).matches()) {
                    Toast.makeText(Login.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                } else if (pass_et_str.equals("")) {
                    Toast.makeText(Login.this, "Password can not be empty", Toast.LENGTH_SHORT).show();
                } else {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        submitSigninData();
                    } else {
                        Toast.makeText(Login.this, "Enable your internet connection please", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void submitSigninData() {
        pDialog = new ProgressDialog(this, AlertDialog.THEME_HOLO_LIGHT);
        pDialog.setMessage("Authenticating...");
        pDialog.show();

        StringRequest loginRequest = new StringRequest(Request.Method.POST, ConstantStuffs.base_url + ConstantStuffs.url_login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hidePDialog();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getString("success").equals("true")) {
                                JSONObject objUdata = new JSONObject(obj.getString("user_data"));
                                String id_user_str = objUdata.getString("id_user");
                                String name_str = objUdata.getString("name");
                                String phone_str = objUdata.getString("phone");
                                String email_str = objUdata.getString("email");
//                                String token_str = objUdata.getString("auth_token");
                                String token_str = "";

                                storeUserInfo(name_str, email_str, phone_str, id_user_str, token_str);
                                Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            } else if (obj.getString("success").equals("invalid")) {
                                Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Throwable t) {
                            Log.e("My App", "Could not parse JSON: \"" + response + "\"");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hidePDialog();
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email_et_str);
                params.put("password", pass_et_str);
                return params;
            }
        };
        loginRequest.setRetryPolicy(new DefaultRetryPolicy(2000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(loginRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    private void storeUserInfo(String name, String email, String phone, String id_user, String auth_token) {
        prefs = getSharedPreferences("UserDetailsFixkoro", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SP_NAME, name);
        editor.putString(SP_EMAIL, email);
        editor.putString(SP_PHONE, phone);
        editor.putString(SP_USERID, id_user);
        editor.putString(SP_TOKEN, auth_token);
        editor.commit();
    }
}
