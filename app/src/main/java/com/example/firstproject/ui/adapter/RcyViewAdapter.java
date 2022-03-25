package com.example.firstproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstproject.R;
import com.example.firstproject.data.UserData;
import com.example.firstproject.databinding.ItemMainActivityBinding;
import com.example.firstproject.ui.activity.AccountInfoActivity;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RcyViewAdapter extends RecyclerView.Adapter<RcyViewAdapter.ViewHolder> {

    private ArrayList<UserData> arrayList;
    private Context context;

    public RcyViewAdapter(ArrayList<UserData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }



    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMainActivityBinding mbinding = DataBindingUtil.inflate(inflater, R.layout.item_main_activity, parent,false);
        mbinding.setCardView(this);
        return new ViewHolder(mbinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.mbinding.emailTxtView.setText(arrayList.get(position).getEmail());
        holder.mbinding.mkTimeTxtView.setText(arrayList.get(position).getMkDate());

    }

    @Override
    public int getItemCount() {
        //삼항연산자
        return (arrayList != null ? arrayList.size() : 0);
    }
    public class ViewHolder extends  RecyclerView.ViewHolder{

        ItemMainActivityBinding mbinding;

        public ViewHolder(@NonNull ItemMainActivityBinding mbinding) {
            super(mbinding.getRoot());
            this.mbinding = mbinding;
            }

    }
        public void nextInfoAccount(View view) {
            Intent intent = new Intent(view.getContext(), AccountInfoActivity.class);
            view.getContext().startActivity(intent);
            Toast.makeText(context, "touch", Toast.LENGTH_SHORT).show();
    }
}



