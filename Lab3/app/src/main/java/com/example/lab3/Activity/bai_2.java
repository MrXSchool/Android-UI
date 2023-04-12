package com.example.lab3.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.lab3.R;

import java.util.ArrayList;
import java.util.HashMap;

public class bai_2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bai_2, container, false);

        //xây dựng giao diên (item)
        Spinner Spinner = view.findViewById(R.id.spinner);
        //data
        /*
         * 1. tạo model (name, old, avatar) => constructor + getter + setter
         * ArrayList<Info> list
         * list.add(new Info(...));
         *
         *SharedPreferences
         * editor.putString("name", "Nguyen Van A");
         *
         */
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> hm1 = new HashMap<>();
        hm1.put("name", "HanCock");
        hm1.put("old", 18);
        hm1.put("avatar", R.mipmap.hancock);
        list.add(hm1);

        HashMap<String, Object> hm2 = new HashMap<>();
        hm2.put("name", "Shank");
        hm2.put("old", 20);
        hm2.put("avatar", R.mipmap.shank);
        list.add(hm2);

        //adapter
        SimpleAdapter adapter =new SimpleAdapter(
                getActivity(),
                list,
                R.layout.item_list,
                new String[]{"name", "avatar"},
                new int[]{R.id.tvName, R.id.ivAvatar}
        );
        //set adapter
        Spinner.setAdapter(adapter);

        return view;


    }
}
