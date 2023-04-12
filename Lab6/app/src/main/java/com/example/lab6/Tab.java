package com.example.lab6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Tab extends AppCompatActivity {

    TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);

        TabAdapter adapter = new TabAdapter(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Tab 1");
                        break;
                    case 1:
                        tab.setText("Tab 2");
                        break;
                    case 2:
                        tab.setText("Tab 3");
                        break;
                }
                Log.d("TAG>>>>>>>>>>>>>>>>", "onConfigureTab: ");
            }
        }).attach();

        Log.d("TAG>>>>>>>>>>:", "onCreate: ");
    }
}