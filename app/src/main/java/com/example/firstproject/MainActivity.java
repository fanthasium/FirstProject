package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;


import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.firstproject.databinding.ActivityIdPagBinding;
import com.example.firstproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


     ActivityMainBinding mbinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mbinding.setMain(this);
    }


}