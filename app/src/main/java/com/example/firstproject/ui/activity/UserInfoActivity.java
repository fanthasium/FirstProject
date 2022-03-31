package com.example.firstproject.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.firstproject.R;
import com.example.firstproject.data.AccountData;

import com.example.firstproject.databinding.ActivityUserinfoPagBinding;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class UserInfoActivity extends AppCompatActivity {
    private ActivityUserinfoPagBinding mbinding;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    AlertDialog.Builder builder;
    List<String> selectItem;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_userinfo_pag);
        mbinding.setUserInfo(this);
        user = FirebaseAuth.getInstance().getCurrentUser();


    }

    public void nextActivity(View view) {

        String name = mbinding.nameEditText.getText().toString();
        String gender = getGender(view);
        String birth = mbinding.birthdayTxtview.getText().toString();
        String phoneNum = mbinding.phoneNumEditText.getText().toString();
        String hobby = mbinding.hobbyTxtview.getText().toString();

        addAccountInfo(name,gender,birth,phoneNum,hobby);

        Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
        startActivity(intent);



    }

    public void addAccountInfo(String name, String gender, String birth, String phoneNum, String hobby) {
        AccountData accountData = new AccountData(name,gender,birth,phoneNum,hobby);

        String uid = user.getUid();

        HashMap<Object,Object> map = new HashMap<>();
        map.put("uid",accountData);

        Log.e("log", "log\t" + uid);

        databaseReference.child("account info").child(uid).setValue(accountData);
    }

    //gender RaidoGroup
    public String getGender(View view){
        RadioButton male = mbinding.rgbtnMale;
        RadioButton female = mbinding.rgbtnFemale;
        String gender = null;
        if(male.isChecked()){
            gender = male.getText().toString();
        }else if(female.isChecked()){
            gender = female.getText().toString();
        }
        return gender;
    }


    //birth dialog
  public void showDatePickerDialog (View view) {
      Calendar c = Calendar.getInstance();

      DatePickerDialog datePickerDialog = new DatePickerDialog(UserInfoActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

              try {
                  TextView birth = mbinding.birthdayTxtview;
                  birth.setText(String.format("%d - %d - %d", year,monthOfYear+1,dayOfMonth));

              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
      datePickerDialog.getDatePicker().setCalendarViewShown(false);
      datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
      datePickerDialog.show();
  }

    //Hobby dialog
  public void showDialog(View view) {
        selectItem = new ArrayList<>();
        builder = new AlertDialog.Builder(UserInfoActivity.this);
        builder.setMultiChoiceItems(R.array.hobby, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                String[] items = getResources().getStringArray(R.array.hobby);

                if (isChecked) {
                    selectItem.add(items[which]);
                } else if (selectItem.contains(items[which])) {
                    selectItem.remove(items[which]);
                }

            }
        });

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String[] items = getResources().getStringArray(R.array.hobby);
                TextView checkBox = mbinding.hobbyTxtview;
                String selection = "";
                for(String item : selectItem){
                    selection = selection + item + " , ";
                    checkBox.setText(selection);
                }

                Toast.makeText(getApplicationContext(), "선택 됐습니다", Toast.LENGTH_SHORT).show();

            }
        });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
    }



}

