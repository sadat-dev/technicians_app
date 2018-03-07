package com.mybeats.fixkoro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import musabbir.mybeats.fixkoro.R;

/**
 * Created by MUSABBIR on 21-Oct-17.
 */

public class Splash extends Activity {
    SharedPreferences prefs;
    private static final String SP_ID_USER = "id_user";
    String getUserId = "";
    private final static int FADE_DURATION = 1000;
    ImageView gif_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        gif_image = (ImageView) findViewById(R.id.gif_image);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gif_image);
        Glide.with(this).load(R.drawable.splash_screen).into(imageViewTarget);


        prefs = getSharedPreferences("UserDetailsFixkoro", MODE_PRIVATE);
        getUserId = prefs.getString(SP_ID_USER, "");

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (getUserId.equals("") || getUserId.equals(null)) {
                        Intent openMainActivity = new Intent(getApplicationContext(), LoginOrSignup.class);
                        startActivity(openMainActivity);
                    } else {
                        Intent openMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(openMainActivity);
                    }
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
