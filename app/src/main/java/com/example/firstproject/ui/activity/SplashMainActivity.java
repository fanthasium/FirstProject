package com.example.firstproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.firstproject.R;

public class SplashMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_main);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashMainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 4000);
    }
}
