package com.example.registerloginexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdpter extends RecyclerView.Adapter<CustomAdpter.CustomViewAdpter> {

    private ArrayList<Track> arrayList;
    private LayoutInflater mInflate;
    private Context context;

    public CustomAdpter(ArrayList<Track> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.mInflate = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomViewAdpter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewAdpter holder = new CustomViewAdpter(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WritePostActivity.class);
                context.startActivity(intent);
                Toast.makeText(v.getContext(), holder.tv_id.getText() ,Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewAdpter holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);
        holder.tv_id.setText(arrayList.get(position).getId());
        holder.tv_pw.setText(arrayList.get(position).getPw());
        holder.tv_userName.setText(arrayList.get(position).getUserName());


    }

    @Override
    public int getItemCount() {
        if (arrayList != null) {
            return arrayList.size();
        }
        else return 0;
    }

    public  class CustomViewAdpter extends RecyclerView.ViewHolder {
        public ArrayList arrayList;
        ImageView iv_profile;
        TextView tv_id;
        TextView tv_pw;
        TextView tv_userName;

        public CustomViewAdpter(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_pw = itemView.findViewById(R.id.tv_pw);
            this.tv_userName = itemView.findViewById(R.id.tv_userName);

        }
    }
}
