package com.mybeats.fixkoro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import musabbir.mybeats.fixkoro.R;

/**
 * Created by MUSABBIR MAMUN on 25-Dec-17.
 */
public class LoginOrSignup extends Activity {
    Button login_btn, signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_signup);

        login_btn = (Button) findViewById(R.id.login_btn);
        signup_btn = (Button) findViewById(R.id.signup_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMainActivity = new Intent(getApplicationContext(), Login.class);
                startActivity(openMainActivity);
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMainActivity = new Intent(getApplicationContext(), Signup.class);
                startActivity(openMainActivity);
            }
        });
    }
}
