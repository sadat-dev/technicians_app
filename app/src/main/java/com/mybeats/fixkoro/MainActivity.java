package com.mybeats.fixkoro;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.PhotoLoader;
import com.mybeats.fixkoro.util.ConstantStuffs;
import com.mybeats.fixkoro.util.MyCommand;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import musabbir.mybeats.fixkoro.R;

import static android.graphics.Typeface.BOLD;

public class MainActivity extends Activity {

    private static int RESULT_LOAD_IMG = 1;
    public int selected_image_val = 0;
    Button submitBtn;
    EditText description_et;
    Intent intent;
    LinearLayout round_ll_mob, round_image_ll, round_ll_one, round_ll_two, round_ll_three, round_ll_four, round_ll_five, round_ll_six, logout_ll, navDrawerList, technician_list_ll, myorders,
            web_ll,
            call_us, email_us,
            locaton_ll,
            fb_ll;
    TextView itemsHeader, round_tv_one, round_tv_mob, round_tv_two, round_tv_three, round_tv_four, round_tv_five, round_tv_six, counter_tv, nav_bar_header;
    ImageView image1, image2, image3, nav_ic, shopping_img;
    final Context context = this;
    private DrawerLayout navDrawerLayout;
    SharedPreferences prefs;
    int image_submitting_count = 0;


    ImageView ivGallery, ivUpload;
    GalleryPhoto galleryPhoto;
    final int GALLERY_REQUEST = 1200;
    final String TAG = this.getClass().getSimpleName();
    LinearLayout photos_ll;
    ArrayList<String> imageList = new ArrayList<>();
    String service_name = "", get_user_id = "", description_et_str = "";
    private ProgressDialog pDialog;
    private static final String SP_USERID = "id_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fontPath = "fonts/Bauhaus_Medium.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        prefs = getSharedPreferences("UserDetailsFixkoro", MODE_PRIVATE);
        get_user_id = prefs.getString(SP_USERID, "");

        navDrawerList = (LinearLayout) findViewById(R.id.navList);

        counter_tv = (TextView) findViewById(R.id.counter_tv);
        nav_bar_header = (TextView) findViewById(R.id.nav_bar_header);
        description_et = (EditText) findViewById(R.id.description_et);
        itemsHeader = (TextView) findViewById(R.id.itemsHeader);
        round_tv_one = (TextView) findViewById(R.id.round_tv_one);
        round_tv_two = (TextView) findViewById(R.id.round_tv_two);
        round_tv_three = (TextView) findViewById(R.id.round_tv_three);
        round_tv_four = (TextView) findViewById(R.id.round_tv_four);
        round_tv_five = (TextView) findViewById(R.id.round_tv_five);
        round_tv_six = (TextView) findViewById(R.id.round_tv_six);
        round_tv_mob = (TextView) findViewById(R.id.round_tv_mob);

        itemsHeader.setTypeface(tf, BOLD);
        nav_bar_header.setTypeface(tf, BOLD);

        round_image_ll = (LinearLayout) findViewById(R.id.round_image_ll);
        round_ll_one = (LinearLayout) findViewById(R.id.round_ll_one);
        round_ll_two = (LinearLayout) findViewById(R.id.round_ll_two);
        round_ll_three = (LinearLayout) findViewById(R.id.round_ll_three);
        round_ll_four = (LinearLayout) findViewById(R.id.round_ll_four);
        round_ll_five = (LinearLayout) findViewById(R.id.round_ll_five);
        round_ll_six = (LinearLayout) findViewById(R.id.round_ll_six);
        round_ll_mob = (LinearLayout) findViewById(R.id.round_ll_mob);
        logout_ll = (LinearLayout) findViewById(R.id.logout_ll);
        technician_list_ll = (LinearLayout) findViewById(R.id.technician_list_ll);
        web_ll = (LinearLayout) findViewById(R.id.web_ll);
        call_us = (LinearLayout) findViewById(R.id.call_us);
        email_us = (LinearLayout) findViewById(R.id.email_us);
        locaton_ll = (LinearLayout) findViewById(R.id.locaton_ll);
        fb_ll = (LinearLayout) findViewById(R.id.fb_ll);

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);

        navDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_ic = (ImageView) findViewById(R.id.nav_ic);

        TranslateAnimation animate = new TranslateAnimation(300, -round_image_ll.getWidth(), 0, 0);
        animate.setDuration(700);
        animate.setFillAfter(true);
        round_image_ll.startAnimation(animate);

        galleryPhoto = new GalleryPhoto(getApplicationContext());
        ivGallery = (ImageView) findViewById(R.id.image1);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        description_et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(300)});
        description_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                description_et_str = description_et.getText().toString();
                counter_tv.setText(description_et_str.length() + "/300");
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.round_ll_mob:
                        resetTextViews();
                        round_tv_mob.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        round_tv_mob.setTypeface(Typeface.DEFAULT, BOLD);
                        service_name = "Mobile";
                        break;
                    case R.id.round_ll_one:
                        resetTextViews();
                        round_tv_one.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        round_tv_one.setTypeface(Typeface.DEFAULT, BOLD);
                        service_name = "Laptop";
                        break;
                    case R.id.round_ll_two:
                        resetTextViews();
                        round_tv_two.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        round_tv_two.setTypeface(Typeface.DEFAULT, BOLD);
                        service_name = "Tab and iPad";
                        break;
                    case R.id.round_ll_three:
                        resetTextViews();
                        round_tv_three.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        round_tv_three.setTypeface(Typeface.DEFAULT, BOLD);
                        service_name = "Games";
                        break;
                    case R.id.round_ll_four:
                        resetTextViews();
                        round_tv_four.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        round_tv_four.setTypeface(Typeface.DEFAULT, BOLD);
                        service_name = "LCD TV";
                        break;
                    case R.id.round_ll_five:
                        resetTextViews();
                        round_tv_five.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        round_tv_five.setTypeface(Typeface.DEFAULT, BOLD);
                        service_name = "MP3 and MP4";
                        break;
                    case R.id.round_ll_six:
                        resetTextViews();
                        round_tv_six.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        round_tv_six.setTypeface(Typeface.DEFAULT, BOLD);
                        service_name = "Others";
                        break;
                    case R.id.technician_list_ll:
                        Intent iTechList = new Intent(getBaseContext(), TechListCard.class);
                        startActivity(iTechList);
                        break;
                    case R.id.logout_ll:
                        clearPreferences();
                        break;
                    case R.id.web_ll:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.fixkoro.com/"));
                        startActivity(intent);
                        break;
                    case R.id.call_us:
                        try {
                            if (checkCallPermission()) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:+8801688043405"));
                                startActivity(intent);
                            } else {
                                Toast.makeText(context, "Please granted the PHONE permission from settings for this feature", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.parse("package:" + getPackageName()));
                                intent.addCategory(Intent.CATEGORY_DEFAULT);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }

                        } catch (Exception ex) {
                            Toast.makeText(context, "Sorry this feature isn't available for your device.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.email_us:
                        try {
                            intent = new Intent(Intent.ACTION_VIEW);
                            intent.setType("plain/text");
                            intent.setData(Uri.parse("my_beats_bd@yahoo.com"));
                            intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                            //intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "info.diapersbd.com@gmail.com" });
                            startActivity(intent);
                        } catch (Exception ex) {
                            Toast.makeText(context, "Sorry this feature isn't available for your device.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.locaton_ll:
                        final Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.visit_us);
                        TextView text = (TextView) dialog.findViewById(R.id.text);
                        dialog.show();
                        break;
                    case R.id.fb_ll:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/fixkoro/"));
                        startActivity(intent);
                        break;
                    case R.id.nav_ic:
                        navDrawerLayout.openDrawer(navDrawerList);
                        navDrawerLayout.bringToFront();
                        navDrawerLayout.requestLayout();
                        break;
                    case R.id.image1:
                        if (checkWriteExternalPermission()) {
                            Intent im1 = galleryPhoto.openGalleryIntent();
                            startActivityForResult(im1, GALLERY_REQUEST);
                            selected_image_val = 1;
                        } else {
                            openPermissionWindow();
                        }
                        break;
                    case R.id.image2:
                        if (checkWriteExternalPermission()) {
                            Intent im2 = galleryPhoto.openGalleryIntent();
                            startActivityForResult(im2, GALLERY_REQUEST);
                            selected_image_val = 2;
                        } else {
                            openPermissionWindow();
                        }
                        break;
                    case R.id.image3:
                        if (checkWriteExternalPermission()) {
                            Intent im3 = galleryPhoto.openGalleryIntent();
                            startActivityForResult(im3, GALLERY_REQUEST);
                            selected_image_val = 3;
                        } else {
                            openPermissionWindow();
                        }
                        break;
                    case R.id.submitBtn:
                        if (service_name.equals("")) {
                            Toast.makeText(context, "Please select a category", Toast.LENGTH_SHORT).show();
                        } else {
                            submitRequestData();
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        round_ll_one.setOnClickListener(listener);
        round_ll_two.setOnClickListener(listener);
        round_ll_three.setOnClickListener(listener);
        round_ll_four.setOnClickListener(listener);
        round_ll_five.setOnClickListener(listener);
        round_ll_six.setOnClickListener(listener);
        round_ll_mob.setOnClickListener(listener);
        logout_ll.setOnClickListener(listener);
        technician_list_ll.setOnClickListener(listener);
        web_ll.setOnClickListener(listener);
        call_us.setOnClickListener(listener);
        locaton_ll.setOnClickListener(listener);
        fb_ll.setOnClickListener(listener);
        email_us.setOnClickListener(listener);
        nav_ic.setOnClickListener(listener);
        image1.setOnClickListener(listener);
        image2.setOnClickListener(listener);
        image3.setOnClickListener(listener);
        submitBtn.setOnClickListener(listener);
    }

    private boolean checkCallPermission() {
        String permission = Manifest.permission.CALL_PHONE;
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    private boolean checkWriteExternalPermission() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public void openPermissionWindow() {
        Toast toast = Toast.makeText(context, "Please granted the STORAGE permission for this feature", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + getPackageName()));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void resetTextViews() {
        round_tv_mob.setTextColor(getResources().getColor(R.color.default_text_color));
        round_tv_mob.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        round_tv_one.setTextColor(getResources().getColor(R.color.default_text_color));
        round_tv_one.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        round_tv_two.setTextColor(getResources().getColor(R.color.default_text_color));
        round_tv_two.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        round_tv_three.setTextColor(getResources().getColor(R.color.default_text_color));
        round_tv_three.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        round_tv_four.setTextColor(getResources().getColor(R.color.default_text_color));
        round_tv_four.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        round_tv_five.setTextColor(getResources().getColor(R.color.default_text_color));
        round_tv_five.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        round_tv_six.setTextColor(getResources().getColor(R.color.default_text_color));
        round_tv_six.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST) {
                galleryPhoto.setPhotoUri(data.getData());
                String photoPath = galleryPhoto.getPath();
                imageList.add(photoPath);
                Log.d(TAG, photoPath);
                try {
                    Bitmap bitmap = PhotoLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    if (selected_image_val == 1) {
                        image1.setImageBitmap(bitmap);
                    } else if (selected_image_val == 2) {
                        image2.setImageBitmap(bitmap);
                    } else if (selected_image_val == 3) {
                        image3.setImageBitmap(bitmap);
                    }
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Error while loading image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void submitRequestData() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait for while...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConstantStuffs.base_url + ConstantStuffs.url_submit_service,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getString("success").equals("true")) {
                                service_name = "";
                                if (imageList.size() > 0) {
                                    String id_service_req = obj.getString("id_service_req");
                                    submitImage(id_service_req);
                                } else {
                                    hidePDialog();
                                    clearFields();
                                    Toast.makeText(MainActivity.this, "Thanks for submit the request. We'll contact with you shortly", Toast.LENGTH_LONG).show();
                                }
                            } else if (obj.getString("success").equals("false")) {
                                Toast.makeText(MainActivity.this, "Sorry something went wrong. Please try again", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_user", get_user_id);
                params.put("service_name", service_name);
                params.put("description", description_et_str);
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

    private void submitImage(final String id_service_req) {
        final MyCommand myCommand = new MyCommand(getApplicationContext());
        for (String imagePath : imageList) {
            try {
                Bitmap bitmap = PhotoLoader.init().from(imagePath).requestSize(512, 512).getBitmap();
                final String encodedString = ImageBase64.encode(bitmap);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, ConstantStuffs.base_url + ConstantStuffs.url_submit_service_images, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            image_submitting_count++;
                        }
                        if (image_submitting_count == imageList.size()) {
                            hidePDialog();
                            clearFields();
                            Toast.makeText(MainActivity.this, "Thanks for submit the request. We'll contact with you shortly", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hidePDialog();
                        clearFields();
                        //Toast.makeText(getApplicationContext(), "Error while uploading image", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("image", encodedString);
                        params.put("id_service_req", id_service_req);
                        return params;
                    }
                };
                myCommand.add(stringRequest);
            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(), "Error while loading image", Toast.LENGTH_SHORT).show();
            }
        }
        myCommand.execute();
    }

    private void clearPreferences() {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences("UserDetailsFixkoro", Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.commit();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        resetTextViews();
        description_et.setText("");
        image1.setImageResource(R.drawable.ic_large_img);
        image2.setImageResource(R.drawable.ic_large_img);
        image3.setImageResource(R.drawable.ic_large_img);
    }


    public void showAlertBox() {

        AlertDialog.Builder alertDlg = new AlertDialog.Builder(context);
        alertDlg.setMessage("Add image from gallery or camera?");
        alertDlg.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        alertDlg.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}