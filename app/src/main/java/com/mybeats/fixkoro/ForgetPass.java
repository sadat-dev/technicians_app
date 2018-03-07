package com.mybeats.fixkoro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mybeats.fixkoro.util.ConstantStuffs;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import musabbir.mybeats.fixkoro.R;

/**
 * Created by MUSABBIR on 21-Oct-17.
 */
public class ForgetPass extends Activity {
    EditText email_et;
    Button submit_btn;
    String email_et_str = "";
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pass);

        email_et = (EditText) findViewById(R.id.email_et);
        findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_et_str = email_et.getText().toString().trim();
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email_et_str).matches()) {
                    Toast.makeText(ForgetPass.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                } else {
                    submitRequest();
                }
            }
        });
    }

    public void submitRequest() {
        pDialog = new ProgressDialog(this, AlertDialog.THEME_HOLO_LIGHT);
        pDialog.setMessage("Please wait for while...");
        pDialog.show();

        StringRequest loginRequest = new StringRequest(Request.Method.POST, ConstantStuffs.base_url + ConstantStuffs.url_passrecovery,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hidePDialog();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getString("success").equals("true")) {
                                Toast.makeText(ForgetPass.this, "Please check your email", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getBaseContext(), Login.class);
                                startActivity(i);
                                finish();
                            } else if (obj.getString("success").equals("not_found")) {
                                Toast.makeText(ForgetPass.this, "This email is not registered", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ForgetPass.this, "Something went wrong", Toast.LENGTH_LONG).show();
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
}
