package com.example.lab3.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.R;
import com.example.lab3.model.Info;

import java.util.ArrayList;

public class InfoRecyclerAdapter extends RecyclerView.Adapter<InfoRecyclerAdapter.viewHolder>{

    private Context context;
    private ArrayList<Info> list;

    public InfoRecyclerAdapter(Context context, ArrayList<Info> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list, parent, false);


        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.ivAvatar.setImageResource(list.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        ImageView ivAvatar;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
        }
    }
}
