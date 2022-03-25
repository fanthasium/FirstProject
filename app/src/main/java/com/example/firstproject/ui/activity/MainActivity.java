package com.example.firstproject.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;


import com.example.firstproject.R;
import com.example.firstproject.ui.adapter.RcyViewAdapter;
import com.example.firstproject.data.UserData;
import com.example.firstproject.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ArrayList<UserData> List;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mbinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        RecyclerView recyclerView = mbinding.rcyView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(recyclerView.getLayoutManager());

        List = new ArrayList<>();

//firebase database 연동
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("user info");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터 가져오는 곳
                List.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren())           //여러 값을 불러와 하나씩
                {
                    Log.e("dd","dd" + ds);
                    UserData userData = ds.getValue(UserData.class);
                    List.add(userData);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //가져오던 중 에러나면
                Log.e("mainActivity", String.valueOf(databaseError.toException()));
            }
        });

        adapter = new RcyViewAdapter(List,this);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

    }

}



