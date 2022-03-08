package com.example.firstproject;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.firstproject.databinding.ActivityIdPagBinding;
import com.google.firebase.auth.FirebaseAuth;


public class IdActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityIdPagBinding mbinding = DataBindingUtil.setContentView(this, R.layout.activity_id_pag);
        mbinding.setIdActivity(this);
        
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
    }






    //데이터바인딩은 오버라이드 하지않고 바로 메소드로 선언
    public void nextActivity(View view) {
        Intent intent = new Intent(IdActivity.this, PwActivity.class);
        startActivity(intent);
    }


}


