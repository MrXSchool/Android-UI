package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.database.KhoanDao;
import com.example.assignment.database.LoaiDao;
import com.example.assignment.fragment.ThongKeFragment;
import com.example.assignment.fragment.ThongTinFragment;
import com.example.assignment.fragment.ThuChiFragment;
import com.example.assignment.models.Khoan;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //anh xa
        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        linearLayout = findViewById(R.id.linearLayout);
        navigationView = findViewById(R.id.navigationView);


        //xu ly toolbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);

        //xu ly ten trong menu
        View view = navigationView.getHeaderView(0);
        TextView txtTen = view.findViewById(R.id.txtTen);
        txtTen.setText("Mr. X");

        //xu ly item tren NavigationView
        navigationView .setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = null;
                Bundle bundle = new Bundle();

                switch (item.getItemId()){
                    default:
                    case R.id.menuThu:
                        fragment = new ThuChiFragment();
                        bundle.putInt("trangthai", 0);
                        fragment.setArguments(bundle);
                        break;
                    case R.id.menuChi:
                        fragment = new ThuChiFragment();
                        bundle.putInt("trangthai", 1);
                        fragment.setArguments(bundle);
                        break;
                    case R.id.menuThongKe:
                        fragment = new ThongKeFragment();
                        break;
                    case R.id.menuGioiThieu:
                        fragment = new ThongTinFragment();
                        break;
                    case R.id.menuThoat:
                        finishAffinity();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
                item.setCheckable(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

}