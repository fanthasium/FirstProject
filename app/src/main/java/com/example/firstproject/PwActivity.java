package com.example.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.firstproject.databinding.ActivityIdPagBinding;
import com.example.firstproject.databinding.ActivityPasswordPagBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class PwActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;
    private ActivityPasswordPagBinding mbinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_password_pag); //데이터 바인딩
        mbinding.setPw(this);


        mAuth = FirebaseAuth.getInstance();

    }


 public void startInfoNextActivity(View view) {

      String email = getIntent().getStringExtra("email");
      String password = mbinding.pwEditText.getText().toString();
      String passwordCheck = mbinding.pwCheckEditText.getText().toString();

      String passwordRge = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,19}";

   if (password.equals(passwordCheck) && Pattern.matches(passwordRge, password)) {  //수정 : 8자이상 대소문자..알림 뜨게

            mAuth.createUserWithEmailAndPassword(String.valueOf(email), password)   //전에 email 과 password 값 파이어베이스로
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("회원가입을 성공하였습니다");
                                Intent intent = new Intent(PwActivity.this, UserInfoActivity.class);
                                startActivity(intent);
                                //UI

                            } else {
                                if (task.getException() != null) {
                                    // If sign in fails, display a message to the user.
                                    startToast(task.getException().toString());
                                    //UI
                                }

                            }
                        }
                    });
        } else {
            startToast("비밀번호가 일치하지 않습니다.");
        }
    }


    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}

