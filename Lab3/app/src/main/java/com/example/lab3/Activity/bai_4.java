package com.example.lab3.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.Adapter.InfoAdapter;
import com.example.lab3.Adapter.InfoRecyclerAdapter;
import com.example.lab3.R;
import com.example.lab3.model.Info;

import java.util.ArrayList;

public class bai_4 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bai_4, container, false);

        ListView listView = view.findViewById(R.id.listViewBai4);

        ArrayList<Info> list = new ArrayList<>();
        list.add(new Info("Kimchi",  R.mipmap.kimchi));
        list.add(new Info("Kimbap",  R.mipmap.kimbap));
        list.add(new Info("Kimheesun",  R.mipmap.kimheesun));
        list.add(new Info("Kimnamjoo",  R.mipmap.kimnamjoo));
        list.add(new Info("Kimtahee",  R.mipmap.kimtaehee));
        list.add(new Info("Kimchi",  R.mipmap.kimchi));
        list.add(new Info("Kimbap",  R.mipmap.kimbap));
        list.add(new Info("Kimheesun",  R.mipmap.kimheesun));
        list.add(new Info("Kimnamjoo",  R.mipmap.kimnamjoo));
        list.add(new Info("Kimtahee",  R.mipmap.kimtaehee));
        list.add(new Info("Kimchi",  R.mipmap.kimchi));
        list.add(new Info("Kimbap",  R.mipmap.kimbap));
        list.add(new Info("Kimheesun",  R.mipmap.kimheesun));
        list.add(new Info("Kimnamjoo",  R.mipmap.kimnamjoo));
        list.add(new Info("Kimtahee",  R.mipmap.kimtaehee));


        InfoAdapter adapter = new InfoAdapter(getActivity(), list);
        listView.setAdapter(adapter);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
//        /*
//        tạo định dạng cột kiểu như:
//        1 2
//        3
//        4 5
//        6
//        7 8
//        * */
//        recyclerView.setLayoutManager(gridLayoutManager);
//        InfoRecyclerAdapter adapter = new InfoRecyclerAdapter(getActivity(), list);
//        recyclerView.setAdapter(adapter);
        return view;
    }
}
