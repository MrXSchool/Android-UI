package com.example.assignment.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.adapter.LoaiAdapter;
import com.example.assignment.database.LoaiDao;
import com.example.assignment.models.Loai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiFragment extends Fragment {
    private LoaiDao loaiDao;
    private RecyclerView recyclerLoai;
    private int trangthai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai, container, false);
        //anh xa
        recyclerLoai = view.findViewById(R.id.recyclerLoai);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatingLoai);

        //lay trang thai
        Bundle bundle = getArguments();
        trangthai = bundle.getInt("trangthai");

        loaiDao = new LoaiDao(getContext());
        loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });




        return view;
    }

    private void loadData(){
        ArrayList<Loai> list = loaiDao.getDS(trangthai);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoai.setLayoutManager(linearLayoutManager);
        LoaiAdapter adapter = new LoaiAdapter(getContext(), list, trangthai, loaiDao);
        recyclerLoai.setAdapter(adapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_edit, null);

        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        //xu ly
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        EditText edtName = view.findViewById(R.id.edtName);
        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnCancel = view.findViewById(R.id.btnCanccel);

        //tieu de
        if(trangthai == 0){
            txtTitle.setText("THÊM MỚI LOẠI THU");
        }else{
            txtTitle.setText("THÊM MỚI LOẠI CHI");
        }
        //nut cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        //nut save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                boolean check = loaiDao.themLoai(name, trangthai);
                if(check){
                    Toast.makeText(getContext(), "Successfully", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    loadData();
                }else{
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
