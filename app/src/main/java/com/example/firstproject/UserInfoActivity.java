package com.example.firstproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.firstproject.databinding.ActivityUserinfoPagBinding;
import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.CDATASection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class UserInfoActivity extends AppCompatActivity {
    private ActivityUserinfoPagBinding mbinding;
    private DatabaseReference databaseReference;


    AlertDialog.Builder builder;
    List<String> selectItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_userinfo_pag);
        mbinding.setUserInfo(this);



    }

    public void nextActivity(View view) {
        String name = mbinding.nameEditText.getText().toString();     //Name

        final RadioGroup radioGroup = mbinding.genderRadioGroup;      //Gender
        int gender = radioGroup.getCheckedRadioButtonId();

        String birth  = mbinding.birthdayTxtview.getText().toString();      //birth
        String number = mbinding.phoneNumEditText.getText().toString();   //Number
        String hobby  = mbinding.hobbyTxtview.getText().toString();                                                             //hobby

        Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("gender", gender);
        intent.putExtra("birth", birth);
        intent.putExtra("number", number);
        intent.putExtra("hobby", hobby);
        startActivity(intent);
    }

    //birth dialog
  public void showDatePickerDialog (View view){
      Calendar c = Calendar.getInstance();

      DatePickerDialog datePickerDialog = new DatePickerDialog(UserInfoActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
              // TODO Auto-generated method stub
              try {
                  Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

      datePickerDialog.getDatePicker().setCalendarViewShown(false);
      datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
      datePickerDialog.show();
    }

    //Hobby

    public void showDialog(View view) {
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

