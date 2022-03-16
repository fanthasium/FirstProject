package com.example.firstproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firstproject.databinding.ItemMainActivityBinding;

import java.util.ArrayList;


public class RcyViewAdapter extends RecyclerView.Adapter<RcyViewAdapter.ViewHolder> {

    private ArrayList<UserData> list;
    private Context context;

    public RcyViewAdapter(ArrayList<UserData> list, Context context) {
        this.list = list;
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
        Glide.with(holder.itemView);
        holder.mbinding.emailTxtView.setText(list.get(position).getEmail());
        holder.mbinding.mkTimeTxtView.setText(String.valueOf(list.get(position).getMkDate()));
        //holder.mbinding.mkTimeTxtView.setText(list.get(position).getorder());

    }

    @Override
    public int getItemCount() {
        //삼항연산자
        return (list != null ? list.size() : 0);
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        private ItemMainActivityBinding mbinding;

        public ViewHolder(@NonNull ItemMainActivityBinding mbinding) {
            super(mbinding.getRoot());
            this.mbinding = mbinding;

        }
    }

}
