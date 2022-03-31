package com.example.firstproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.firstproject.R;

import com.example.firstproject.data.UserData;
import com.example.firstproject.databinding.ActivityPasswordPagBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class PwActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;
    private ActivityPasswordPagBinding mbinding;
    private FirebaseUser user;

    //데이터 베이스 연결 메소드
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_password_pag); //데이터 바인딩
        mbinding.setPw(this);
        database.getReference();
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }


    public void startInfoNextActivity(View view) {

        String email = getIntent().getStringExtra("email");
        String password = mbinding.pwEditText.getText().toString();
        String passwordCheck = mbinding.pwCheckEditText.getText().toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
        String mkDate = simpleDateFormat.format(new Date());

        String passwordRge = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,19}";

        if (password.equals(passwordCheck) && Pattern.matches(passwordRge, password)) {


            mAuth.createUserWithEmailAndPassword(String.valueOf(email), password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                // Sign in success, update UI with the signed-in user's information
                                Log.e(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("회원가입을 성공하였습니다");
                                Intent intent = new Intent(PwActivity.this, UserInfoActivity.class);
                                writeNewUser(email, mkDate);
                                startActivity(intent);
                                //UI

                            } else {
                                if (task.getException() != null) {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(PwActivity.this, "가입을 실패했습니다.", Toast.LENGTH_SHORT).show();
                                    //UI
                                }

                            }
                        }
                    });
        } else {
            if (!password.equals(passwordCheck)) {
                startToast("비밀번호가 일치하지 않습니다.");
            } else if (!Pattern.matches(passwordRge, password)) {
                startToast("잘못된 표현: 8자 이상 19자 이하의 올바른 정규식으로 입력해주세요");
            }
        }
    }


    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //firbase에 데이터 추가 메서드

    public void writeNewUser(String email, String mkDate) {

        UserData userData = new UserData(email, mkDate);
        String uid = user.getUid();

        databaseReference.child("user info").child(uid).setValue(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(PwActivity.this, "가입을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(PwActivity.this, "가입을 실패했습니다.", Toast.LENGTH_SHORT).show();

                    }
                });

    }
}

