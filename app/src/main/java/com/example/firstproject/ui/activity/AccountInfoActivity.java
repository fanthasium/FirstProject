package com.example.firstproject.ui.activity;

import static java.lang.String.valueOf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.firstproject.R;

import com.example.firstproject.data.AccountData;
import com.example.firstproject.data.UserData;
import com.example.firstproject.databinding.ActitivtyAccountInfoBinding;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;

public class AccountInfoActivity extends AppCompatActivity {

    private static final String TAG = AccountInfoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActitivtyAccountInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.actitivty_account_info);
        binding.setActivity(this);

        //데이터 바인딩에서는 객체 초기화 or 선언
        AccountData accountData = getIntent().getParcelableExtra("accountData");
        Log.e(TAG, "hi" + accountData);

        binding.setAccountData(accountData);  // 연결
    }
}