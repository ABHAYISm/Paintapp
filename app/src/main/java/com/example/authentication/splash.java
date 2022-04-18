package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class splash extends AppCompatActivity {
    private static int Splash_timeout = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashintent = new Intent(splash.this,MainActivity.class);
                startActivity(splashintent);
                finish();
            }
        },Splash_timeout);
        Animation myanimation = AnimationUtils.loadAnimation(splash.this,R.anim.animation1);
        Animation myanimation1 = AnimationUtils.loadAnimation(splash.this,R.anim.animation2);

    }
}