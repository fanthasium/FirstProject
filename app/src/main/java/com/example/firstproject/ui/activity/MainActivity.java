package com.example.firstproject.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.firstproject.R;
import com.example.firstproject.data.AccountData;
import com.example.firstproject.ui.adapter.RcyViewAdapter;
import com.example.firstproject.data.UserData;
import com.example.firstproject.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ArrayList<UserData> list;
    private RcyViewAdapter adapter;
    private ArrayList<AccountData> accountDataArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mbinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mbinding.rcyView.setAdapter(adapter);
//firebase database 연동
        RecyclerView recyclerView = mbinding.rcyView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(recyclerView.getLayoutManager());


        list = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();

        databaseReference.child("user info").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터 가져오는 곳
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren())           //여러 값을 불러와 하나씩
                {
                    UserData userData = ds.getValue(UserData.class);
                    list.add(userData);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //가져오던 중 에러나면
                Log.e("mainActivity", String.valueOf(databaseError.toException()));
            }
        });

        accountDataArrayList = new ArrayList<>();
        databaseReference.child("account info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                accountDataArrayList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    AccountData accountData = ds.getValue(AccountData.class);
                    accountDataArrayList.add(accountData);

                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("account Activity", "loadPost:onCancelled", databaseError.toException());
            }
        });

        recyclerView.setAdapter(adapter);
        adapter = new RcyViewAdapter(accountDataArrayList, list, this);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
    }
}