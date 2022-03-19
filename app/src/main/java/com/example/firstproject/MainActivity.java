package com.example.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;


import com.example.firstproject.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<UserData> arrayList;
   private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mbinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        RecyclerView recyclerView = mbinding.rcyView;


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(recyclerView.getLayoutManager());
        arrayList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance(); //firebase database 연동
        DatabaseReference databaseReference = database.getReference("user info");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터 가져오는 곳
                arrayList.clear(); //기존 배열리스트 존재하지 않게 초기화
                UserData userData = dataSnapshot.getValue(UserData.class); //만들어둔 data class 객체에 담기
                    arrayList.add(userData);  //담은 데이터 배열리스트에 넣고 어텝터에 옮겨줄거임
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //가져오던 중 에러나면
                Log.e("mainActivity", String.valueOf(databaseError.toException()));
            }
        });
        adapter = new RcyViewAdapter(arrayList,this);
        mbinding.rcyView.setAdapter(adapter);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

    }
    
}



