package com.example.firstproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.firstproject.R;
import com.example.firstproject.databinding.ActivityIdPagBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;


public class IdActivity extends AppCompatActivity {
    private ActivityIdPagBinding mbinding;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_id_pag); //데이터 바인딩
        mbinding.setId(this);

    }

    // Fragment -> 사용


    public void startPwdActivity(View view) {

         String editText = mbinding.idEditText.getText().toString().trim();

         String email = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

        if(email.length() > 0 && Pattern.matches(email, editText)) {
                startToast("사용자 아이디가 생성됐습니다");
                Intent intent = new Intent(IdActivity.this, PwActivity.class);
                intent.putExtra("email", editText);
                startActivity(intent);

            }else {
            startToast("잘못된 표현: 올바른 정규식으로 입력해주세요");
        }
    }


    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}


