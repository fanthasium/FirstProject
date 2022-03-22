package com.example.firstproject;

import static java.lang.String.valueOf;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstproject.databinding.ActitivtyAccountInfoBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Stack;

public class AccountInfoActivity extends AppCompatActivity {

    private ActitivtyAccountInfoBinding mbinding;
    private ArrayList<AccountData> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.actitivty_account_info);
        mbinding.setAccountinfo(this);


        arrayList = new ArrayList<>();

        //firebase database 연동

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("account info");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                arrayList.clear(); //기존 배열리스트 존재하지 않게 초기화

                TextView name = mbinding.nameTxtView;
                TextView gender = mbinding.genderTxtView;
                TextView birth = mbinding.birthTxtView;
                TextView phoneNum = mbinding.phoneNumTxtView;
                TextView hobby = mbinding.hobbyTxtView;


                /*AccountData accountData = dataSnapshot.getValue(AccountData.class);
                accountData.setName(valueOf(name));
                accountData.setName(valueOf(gender));
                accountData.setName(valueOf(birth));
                accountData.setName(valueOf(phoneNum));
                accountData.setName(valueOf(hobby));*/
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("account Activity", "loadPost:onCancelled", databaseError.toException());
            }
        });

    }
}