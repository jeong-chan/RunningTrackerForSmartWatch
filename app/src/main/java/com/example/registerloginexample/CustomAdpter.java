package com.example.registerloginexample;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdpter extends RecyclerView.Adapter<CustomAdpter.CustomViewAdpter> {

    public static String idfordatabase;
    public static ArrayList<Track> arrayList;
    //private LayoutInflater mInflate;
    private Context context;
    public static CustomViewAdpter every_holder;


    public CustomAdpter(ArrayList<Track> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        //this.mInflate = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomViewAdpter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewAdpter holder = new CustomViewAdpter(view);
        every_holder = holder;

        //홀더에 종속된 각 항목 클릭시 동작
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CheckPostActivity.class);
                context.startActivity(intent);
                Toast.makeText(v.getContext(), holder.tv_id.getText() ,Toast.LENGTH_SHORT).show();
                idfordatabase = holder.tv_id.getText().toString();
            }
        });
        return holder;
    }

    //홀더에 값 바인딩
    @Override
    public void onBindViewHolder(@NonNull CustomViewAdpter holder, int position) {
        holder.tv_Sigungu.setText(arrayList.get(position).getSigungu());
        holder.tv_id.setText(arrayList.get(position).getId());
        holder.tv_City.setText(arrayList.get(position).getCity());
        holder.tv_Title.setText(arrayList.get(position).getTitle());
        holder.tv_Runningplace.setText(arrayList.get(position).getRunningplace());

    }

    @Override
    public int getItemCount() {
        if (arrayList != null) {
            return arrayList.size();
        }
        else return 0;
    }

    public  class CustomViewAdpter extends RecyclerView.ViewHolder {
        //public ArrayList arrayList;
        TextView tv_Runningplace;
        TextView tv_Sigungu;
        TextView tv_id;
        TextView tv_City;
        TextView tv_Title;

        public CustomViewAdpter(@NonNull View itemView) {
            super(itemView);
            this.tv_Sigungu = itemView.findViewById(R.id.tv_Sigungu);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_City = itemView.findViewById(R.id.tv_City);
            this.tv_Title = itemView.findViewById(R.id.tv_Title);
            this.tv_Runningplace = itemView.findViewById(R.id.tv_Runningplace);

        }
        public TextView getTvid(){
            return tv_id;
        }
    }
}
