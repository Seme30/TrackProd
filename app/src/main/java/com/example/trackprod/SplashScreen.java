package com.example.trackprod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logintoapp();
            }
        }, SPLASH_TIME_OUT);


    }

    private void logintoapp() {

        boolean alreadyLogin =  Prefs.INSTANCE.isLogin();

        Intent i;
        if (alreadyLogin) {
            i = new Intent(SplashScreen.this, HomeActivity.class);

        } else {
            i = new Intent(SplashScreen.this, LoginActivity.class);

        }

        startActivity(i);

        finish();
    }
}