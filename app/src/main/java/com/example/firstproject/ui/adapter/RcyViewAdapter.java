package com.example.firstproject.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstproject.R;
import com.example.firstproject.data.AccountData;
import com.example.firstproject.data.UserData;
import com.example.firstproject.databinding.ItemMainActivityBinding;
import com.example.firstproject.ui.activity.AccountInfoActivity;
import com.example.firstproject.ui.activity.MainActivity;
import com.google.firebase.firestore.auth.User;


import java.util.ArrayList;


public class RcyViewAdapter extends RecyclerView.Adapter<RcyViewAdapter.ViewHolder> {

    private ArrayList<UserData> arrayList;
    private Context context;
    private ArrayList<AccountData> accountDataArrayList;

    public RcyViewAdapter(ArrayList<AccountData> accountDataArrayList, ArrayList<UserData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.accountDataArrayList = accountDataArrayList;
        
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMainActivityBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.item_main_activity, parent,false);
        mBinding.setCardView(this);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        UserData userData = arrayList.get(position);
        Log.e("email", "= " + userData.getEmail());
        holder.mBinding.setUserData(userData);
        holder.mBinding.executePendingBindings();
 //account info에 있는 db값 넣어주기!!!!

        Log.e("main","account\t" + accountDataArrayList);

        holder.mBinding.mainCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AccountInfoActivity.class);
                intent.putExtra("accountData", accountDataArrayList.get(position)); // 1 : 1 매칭 getposition

                view.getContext().startActivity(intent);
                Toast.makeText(context, "touch", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        //삼항연산자
        return (arrayList != null ? arrayList.size() : 0);
    }
    public static class ViewHolder extends  RecyclerView.ViewHolder{
        ItemMainActivityBinding mBinding;

        public ViewHolder(@NonNull ItemMainActivityBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
            }

    }

}



