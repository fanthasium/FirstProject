package com.example.firstproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.firstproject.databinding.ActivityLoginBinding;
import com.example.firstproject.databinding.ActivityLoginBindingImpl;
import com.example.firstproject.databinding.ActivityUserinfoPagBinding;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding mbinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mbinding.setLoginActivity(this);
    }
}
