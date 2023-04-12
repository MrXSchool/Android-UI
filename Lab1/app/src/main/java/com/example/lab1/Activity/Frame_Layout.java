package com.example.lab1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.lab1.R;

public class Frame_Layout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);

        int[] images = new int[]{R.mipmap.h1, R.mipmap.h2, R.mipmap.h3, R.mipmap.h4, R.mipmap.h5,
                R.mipmap.h6, R.mipmap.h7, R.mipmap.h8, R.mipmap.h9, R.mipmap.h10, R.mipmap.hj,
                R.mipmap.hq, R.mipmap.hk};

        int i = 0;
        for (int item : images) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(item);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(200, 400);
            imageView.setLayoutParams(layoutParams);
            layoutParams.leftMargin = i * 50;
            layoutParams.topMargin = i * 50;
            frameLayout.addView(imageView);
            i++;
        }
    }
}