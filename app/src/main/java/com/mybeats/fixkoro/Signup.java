package com.mybeats.fixkoro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.mybeats.fixkoro.util.ConstantStuffs;
import com.mybeats.fixkoro.util.InputChecker;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import musabbir.mybeats.fixkoro.R;

/**
 * Created by MUSABBIR on 21-Oct-17.
 */

public class Signup extends Activity {

    TextView itemsHeader;
    EditText name_et, lastname_et, phone_et, email_et, pass_et, conf_pass_et, address_et;
    String name_et_str, phone_et_str, email_et_str, pass_et_str, conf_pass_et_str, address_et_str;
    Button sign_up_btn;
    private ProgressDialog pDialog;
    final Context context = this;
    JSONObject json;
    private static final int REQUEST_CODE_EMAIL = 1;
    public static final String SP_NAME = "name", SP_EMAIL = "email", SP_PHONE = "phone", SP_USERID = "id_user", SP_TOKEN = "auth_token";
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        InputChecker inpChckObj = new InputChecker();
        String fontPath = "fonts/Bauhaus_Medium.ttf";
//        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
//        itemsHeader = (TextView) findViewById(R.id.itemsHeader);
//        itemsHeader.setTypeface(tf, BOLD);

        name_et = (EditText) findViewById(R.id.name_et);
        phone_et = (EditText) findViewById(R.id.phone_et);
        email_et = (EditText) findViewById(R.id.email_et);
        pass_et = (EditText) findViewById(R.id.pass_et);
        conf_pass_et = (EditText) findViewById(R.id.conf_pass_et);
        address_et = (EditText) findViewById(R.id.address_et);

        name_et.setFilters(new InputFilter[]{inpChckObj.filter});
        phone_et.setFilters(new InputFilter[]{inpChckObj.filter});
        email_et.setFilters(new InputFilter[]{inpChckObj.filter});
        pass_et.setFilters(new InputFilter[]{inpChckObj.filter});
        conf_pass_et.setFilters(new InputFilter[]{inpChckObj.filter});

//        try {
//            Intent intent = AccountPicker.newChooseAccountIntent(null, null,
//                    new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE}, false, null, null, null, null);
//            startActivityForResult(intent, REQUEST_CODE_EMAIL);
//        } catch (ActivityNotFoundException e) {
//            // TODO
//        }

//        TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        String mPhoneNumber = tMgr.getLine1Number();
//        if (mPhoneNumber.length() > 1) {
//            phone_et.setText(mPhoneNumber);
//            phone_et.setFocusable(false);
//            phone_et.setFocusableInTouchMode(false);
//        } else {
//            phone_et.setHint("Phone no.");
//        }

        sign_up_btn = (Button) findViewById(R.id.sign_up_btn);
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_et_str = name_et.getText().toString().trim();
                phone_et_str = phone_et.getText().toString().trim();
                email_et_str = email_et.getText().toString().trim();
                pass_et_str = pass_et.getText().toString().trim();
                conf_pass_et_str = conf_pass_et.getText().toString().trim();
                address_et_str = address_et.getText().toString().trim();

                if (name_et_str.equals("")) {
                    Toast.makeText(Signup.this, "Name can't be empty", Toast.LENGTH_SHORT).show();
                } else if (phone_et_str.equals("")) {
                    Toast.makeText(Signup.this, "Phone number required", Toast.LENGTH_SHORT).show();
                } else if (phone_et_str.length() < 5) {
                    Toast.makeText(Signup.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                } else if (email_et_str.equals("")) {
                    Toast.makeText(Signup.this, "Email address required", Toast.LENGTH_SHORT).show();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email_et_str).matches()) {
                    Toast.makeText(Signup.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                } else if (pass_et_str.length() < 4) {
                    Toast.makeText(Signup.this, "Password can not be empty or less than four characters", Toast.LENGTH_SHORT).show();
                } else if (!pass_et_str.equals(conf_pass_et_str)) {
                    Toast.makeText(Signup.this, "Password did not matched", Toast.LENGTH_SHORT).show();
                } else {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        submitSignupData();
                    } else {
                        Toast.makeText(Signup.this, "Enable your internet connection please", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_CODE_EMAIL) {
//            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
//            email_et.setText(accountName);
//            email_et.setFocusable(false);
//            email_et.setFocusableInTouchMode(false);
//        } else {
//            email_et.setHint("Email");
//        }
//    }

    public void submitSignupData() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait for while...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConstantStuffs.base_url + ConstantStuffs.url_signup,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hidePDialog();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getString("success").equals("true")) {
                                String id_user_str = obj.getString("id_user");
//                                String token_str = obj.getString("auth_token");
                                String token_str = "";

                                storeUserInfo(name_et_str, email_et_str, phone_et_str, id_user_str, token_str);

                                Toast.makeText(Signup.this, "You have registered successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            } else if (obj.getString("success").equals("email_exist")) {
                                Toast.makeText(Signup.this, "This email already registered. Try to recover your password.", Toast.LENGTH_LONG).show();
                            }
                        } catch (Throwable t) {
                            Log.e("My App", "Could not parse JSON: \"" + json + "\"");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hidePDialog();
                        Toast.makeText(Signup.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name_et_str", name_et_str);
                params.put("phone_et_str", phone_et_str);
                params.put("email_et_str", email_et_str);
                params.put("pass_et_str", pass_et_str);
                params.put("address_et_str", address_et_str);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                ConstantStuffs.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

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
