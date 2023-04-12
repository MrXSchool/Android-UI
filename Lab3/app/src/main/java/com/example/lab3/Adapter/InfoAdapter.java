package com.example.lab3.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab3.R;
import com.example.lab3.model.Info;

import java.util.ArrayList;

public class InfoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Info> list;

    public InfoAdapter(Context context, ArrayList<Info> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_list, parent, false);

        //ánh xạ
        TextView tvName = convertView.findViewById(R.id.tvName);
        ImageView ivAvatar = convertView.findViewById(R.id.ivAvatar);

        //gán giá trị
        tvName.setText(list.get(position).getName());
        ivAvatar.setImageResource(list.get(position).getAvatar());


        return convertView;
    }
}
