package com.example.firstproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firstproject.databinding.ItemMainActivityBinding;
import com.google.firebase.firestore.auth.User;

import java.sql.Date;
import java.util.ArrayList;


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
        ItemMainActivityBinding mbinding = DataBindingUtil.inflate(inflater,R.layout.item_main_activity, parent,false);
        return new ViewHolder(mbinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.mbinding.emailTxtView.setText(arrayList.get(position).getEmail());
        holder.mbinding.mkTimeTxtView.setText((CharSequence) arrayList.get(position).getMkDate());
        //holder.mbinding.mkTimeTxtView.setText(arrayList.get(position).getorder());
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

}
