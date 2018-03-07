package com.mybeats.fixkoro;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mybeats.fixkoro.util.AppController;
import com.mybeats.fixkoro.util.CircularNetworkImageView;
import com.mybeats.fixkoro.util.ConstantStuffs;

import java.util.HashMap;
import java.util.Map;

import musabbir.mybeats.fixkoro.R;

public class TechProfile extends AppCompatActivity {

    WebView location_wv;
    TextView name, speciality, working_area, phone, id_tech;
    String name_str, speciality_str, working_area_str, phone_str, id_tech_str, location_str, image_str, latti_str, longi_str, getCustomerId = "";
    CircularNetworkImageView tech_image;
    ImageLoader imageLoader;
    ImageView call_img, sms_img;
    SharedPreferences prefs;
    public static final String SP_ID_CUSTOMER = "id_user";
    final Context context = this;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tech_profile);

        prefs = getSharedPreferences("UserDetailsFixkoro", MODE_PRIVATE);
        getCustomerId = prefs.getString(SP_ID_CUSTOMER, "");

        tech_image = (CircularNetworkImageView) findViewById(R.id.tech_image);
        imageLoader = AppController.getInstance().getImageLoader();

        name = (TextView) findViewById(R.id.name);
        speciality = (TextView) findViewById(R.id.speciality);
        working_area = (TextView) findViewById(R.id.working_area);
        phone = (TextView) findViewById(R.id.phone);
        id_tech = (TextView) findViewById(R.id.id_tech);

        call_img = (ImageView) findViewById(R.id.call_img);
        sms_img = (ImageView) findViewById(R.id.sms_img);

        Bundle extras = getIntent().getExtras();
        name_str = extras.getString("name");
        speciality_str = extras.getString("special_on");
        working_area_str = extras.getString("working_area");
        location_str = extras.getString("location");
        phone_str = extras.getString("phone");
        id_tech_str = extras.getString("id_tech");
        image_str = extras.getString("image_str");
        latti_str = extras.getString("latti_str");
        longi_str = extras.getString("longi_str");

        name.setText(name_str);
        speciality.setText(speciality_str);
        working_area.setText(working_area_str);
        phone.setText(phone_str);
        id_tech.setText(id_tech_str);

        tech_image.setImageUrl(image_str, imageLoader);
        tech_image.setDefaultImageResId(R.drawable.ic_user_white);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        final LatLng VICTIM = new LatLng(Double.parseDouble(latti_str), Double.parseDouble(longi_str));

        call_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkCallPermission()) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone_str));
                        startActivity(intent);
                        insertCallSmsHistory("Call");
                    } else {
                        Toast.makeText(context, "Please granted the PHONE permission from settings for this feature", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.parse("package:" + getPackageName()));
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                } catch (Exception ex) {
                    Toast.makeText(TechProfile.this, "Sorry this feature is not available in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sms_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkSMSPermission()) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone_str));
                        startActivity(intent);
                        insertCallSmsHistory("SMS");
                    } else {
                        Toast.makeText(context, "Please granted the SMS permission from settings for this feature", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.parse("package:" + getPackageName()));
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                } catch (Exception ex) {
                    Toast.makeText(TechProfile.this, "Sorry this feature is not available in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(VICTIM)
                .zoom(17).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        Marker victims = map.addMarker(new MarkerOptions().position(VICTIM)
                .title("Technician Location")
                .snippet(""));
        victims.showInfoWindow();
    }



    public void insertCallSmsHistory(final String call_or_sms) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConstantStuffs.base_url + ConstantStuffs.url_insert_call_sms_history,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response.equals("true")) {
                                Toast.makeText(TechProfile.this, "success", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Throwable t) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_customer", getCustomerId);
                params.put("id_technician", id_tech_str);
                params.put("call_or_sms", call_or_sms);
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

    private boolean checkCallPermission() {
        String permission = Manifest.permission.CALL_PHONE;
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    private boolean checkSMSPermission() {
        String permission = Manifest.permission.SEND_SMS;
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

}
