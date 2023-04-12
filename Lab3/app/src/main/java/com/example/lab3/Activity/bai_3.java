package com.example.lab3.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.Adapter.InfoRecyclerAdapter;
import com.example.lab3.R;
import com.example.lab3.model.Info;

import java.util.ArrayList;

public class bai_3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bai_3, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBai3);

        ArrayList<Info> list = new ArrayList<>();
        list.add(new Info("Android",  R.mipmap.android));
        list.add(new Info("Blogger",  R.mipmap.blogger));
        list.add(new Info("Chrome",  R.mipmap.chrome));
        list.add(new Info("Dell",  R.mipmap.dell));
        list.add(new Info("Facebook",  R.mipmap.facebook));
        list.add(new Info("FireFox",  R.mipmap.firefox));
        list.add(new Info("HP",  R.mipmap.hp));




//        InfoAdapter adapter = new InfoAdapter(getActivity(), list);
//        listView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        /*
        tạo định dạng cột kiểu như:
        1 2
        3
        4 5
        6
        7 8
        * */
        recyclerView.setLayoutManager(gridLayoutManager);
        InfoRecyclerAdapter adapter = new InfoRecyclerAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
