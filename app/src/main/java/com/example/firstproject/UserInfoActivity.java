package com.example.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.firstproject.databinding.ActivityUserinfoPagBinding;


public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserinfoPagBinding mbinding = DataBindingUtil.setContentView(this, R.layout.activity_userinfo_pag);
        mbinding.setUserInfoActivity(this);

    }
    //데이터바인딩은 오버라이드 하지않고 바로 메소드로 선언
    public void nextActivity(View view) {
        Intent intent = new Intent(UserInfoActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}