package com.example.lab3.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.example.lab3.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private bai_1_fragment bai_1;
    private bai_2 bai_2;
    private bai_3 bai_3;
    private bai_4 bai_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.buttonTest);
        fragmentManager = getSupportFragmentManager();
        bai_1 = new bai_1_fragment();
        bai_2 = new bai_2();
        bai_3 = new bai_3();
        bai_4 = new bai_4();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu1:
                    fragmentManager.beginTransaction()
                            .replace(R.id.mainView, bai_1)
                            .commit();
                    break;
                case R.id.menu2:
                    fragmentManager.beginTransaction()
                            .replace(R.id.mainView, bai_2)
                            .commit();
                    break;
                case R.id.menu3:
                    fragmentManager.beginTransaction()
                            .replace(R.id.mainView, bai_3)
                            .commit();
                    break;
                case R.id.menu4:
                    fragmentManager.beginTransaction()
                            .replace(R.id.mainView, bai_4)
                            .commit();
                    break;
            }
            return true;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        registerForContextMenu(toolbar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu1, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                Toast.makeText(MainActivity.this, "Menu 1", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu2:
                                Toast.makeText(MainActivity.this, "Menu 2", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu3:
                                Toast.makeText(MainActivity.this, "Menu 3", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu4:
                                Toast.makeText(MainActivity.this, "Menu 4", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, "Menu 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(this, "Menu 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                Toast.makeText(this, "Menu 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu4:
                Toast.makeText(this, "Menu 4", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, "Menu 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(this, "Menu 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                Toast.makeText(this, "Menu 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu4:
                Toast.makeText(this, "Menu 4", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }


}