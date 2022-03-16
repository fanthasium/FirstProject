package com.example.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.accounts.Account;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.firstproject.databinding.ActivityIdPagBinding;
import com.example.firstproject.databinding.ActivityMainBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.SnapshotHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private ActivityMainBinding mbinding;
   private FirebaseDatabase database;
   private DatabaseReference databaseReference;
   private ArrayList<UserData> arrayList;
   private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_main);




        database = FirebaseDatabase.getInstance(); //firebase database 연동
        databaseReference = database.getReference("user info");
        arrayList = new ArrayList<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            private Object UserData;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터 가져오는 곳
                arrayList.clear(); //기존 배열리스트 존재하지 않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserData data = snapshot.getValue(UserData.class);  //만들어둔 data class 객체에 담기
                    arrayList.add((com.example.firstproject.UserData) UserData);  //담은 데이터 배열리스트에 넣고 리사이클러뷰에 옮겨줄거임
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //가져오던 중 에러나면
                Log.e("mainActivity", String.valueOf(databaseError.toException()));
            }
        });

        adapter = new RcyViewAdapter(arrayList, this);
        mbinding.rcyView.setAdapter(adapter);
        mbinding.rcyView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}



