package com.example.firstproject.ui.activity;

import static java.lang.String.valueOf;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.firstproject.R;
import com.example.firstproject.data.AccountData;
import com.example.firstproject.data.UserData;
import com.example.firstproject.databinding.ActitivtyAccountInfoBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class AccountInfoActivity extends AppCompatActivity {

    private ActitivtyAccountInfoBinding mbinding;
    private ArrayList<AccountData> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.actitivty_account_info);
        mbinding.setAccountinfo(this);

        TextView nameTxtView = mbinding.nameTxtView;
        TextView genderTxtView = mbinding.genderTxtView;
        TextView birthTxtView = mbinding.birthTxtView;
        TextView hobbyTxtView = mbinding.hobbyTxtView;
        TextView phoneNumTxtViewTxtView = mbinding.phoneNumTxtView;

        arrayList = new ArrayList<>();
        //firebase database 연동

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("account info");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    AccountData accountData = ds.getValue(AccountData.class);

                    String name = accountData.getName();
                    String birth = accountData.getBirth();
                    String gender = accountData.getGender();
                    String phoneNum = accountData.getPhoneNum();
                    String hobby = accountData.getHobby();


                    Log.e("d", "d" + name); //자꾸 null이 반환된다..
                    nameTxtView.setText(name);
                    genderTxtView.setText(gender);
                    birthTxtView.setText(birth);
                    hobbyTxtView.setText(hobby);
                    phoneNumTxtViewTxtView.setText(phoneNum);
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("account Activity", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }


}