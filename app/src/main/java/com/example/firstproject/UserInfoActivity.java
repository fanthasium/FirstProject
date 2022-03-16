package com.example.firstproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.firstproject.databinding.ActivityUserinfoPagBinding;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserInfoActivity extends AppCompatActivity {
    private ActivityUserinfoPagBinding mbinding;
    private DatabaseReference databaseReference;

    AlertDialog.Builder builder;
    List<String> selectItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_userinfo_pag);
        mbinding.setUserInfoActivity(this);


    }

    public void nextActivity(View view) {
        String name = mbinding.nameEditText.getText().toString();     //Name

        final RadioGroup radioGroup = mbinding.genderRadioGroup;   //Gender
        int gender = radioGroup.getCheckedRadioButtonId();

        DialogFragment newFragment = new BirthDialog();               //birth
        newFragment.show(getSupportFragmentManager(), "datePicker");

        String number = mbinding.nameEditText.getText().toString();   //Number


        showDialog(); // Hobby

        Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("gender", gender);
      //  intent.putExtra("birth", );
        startActivity(intent);
    }

    //Hobby
    public void showDialog() {
        selectItem = new ArrayList<>();
        builder = new AlertDialog.Builder(UserInfoActivity.this);
        builder.setMultiChoiceItems(R.array.hobby, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean checked) {
                String[] items = getResources().getStringArray(R.array.hobby);

                if (checked) {
                    selectItem.add(items[which]);
                } else if (selectItem.contains(items[which])) {
                    selectItem.remove(items[which]);
                }

            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getApplicationContext(), "선택 됐습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

