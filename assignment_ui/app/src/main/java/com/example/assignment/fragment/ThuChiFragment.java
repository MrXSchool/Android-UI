package com.example.assignment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.assignment.R;
import com.example.assignment.adapter.ViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThuChiFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi, container, false);
        //anh xa
        TabLayout tabLayout = view.findViewById(R.id.tablayout);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);

        //lay du lieu
        Bundle bundle = getArguments();
        int trangthai = bundle.getInt("trangthai");

        //xu ly
        ViewPager2Adapter adapter = new ViewPager2Adapter(getActivity());
        adapter.setTrangthai(trangthai);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0){
                    //loai
                    if(trangthai == 0){
                        tab.setText("Loai thu");
                    }else{
                        tab.setText("Loai chi");
                    }
                }else {
                    //khoan
                    if(trangthai == 0){
                        tab.setText("khoan thu");
                    }else{
                        tab.setText("khoan chi");
                    }
                }
            }
        }).attach();

        return view;
    }
}
