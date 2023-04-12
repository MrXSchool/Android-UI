package com.example.assignment.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.adapter.CustomSpinnerAdapter;
import com.example.assignment.adapter.KhoanAdapter;
import com.example.assignment.database.KhoanDao;
import com.example.assignment.database.LoaiDao;
import com.example.assignment.models.Khoan;
import com.example.assignment.models.Loai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class KhoanFragment extends Fragment {
    private RecyclerView recyclerKhoan;
    private KhoanDao khoanDao;
    private int trangthai, idLoai = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoan, container, false);

        //anh xa
        recyclerKhoan = view.findViewById(R.id.recycleKhoan);


        FloatingActionButton floatAdd = view.findViewById(R.id.floatingKhoan);
        //lay trang thai
        Bundle bundle = getArguments();
        trangthai = bundle.getInt("trangthai");

        khoanDao = new KhoanDao(getContext());
        loadData();

        //them item
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        return view;
    }
    private void loadData(){
        ArrayList<Khoan> list = khoanDao.getDsKhoan(trangthai);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerKhoan.setLayoutManager(linearLayoutManager);
        KhoanAdapter adapter = new KhoanAdapter(getContext(),list, trangthai, khoanDao);
       recyclerKhoan.setAdapter(adapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_khoan_add_edit, null);

        builder.setView(view);
        AlertDialog alertDialog = builder.create();


        //anhxa
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        EditText edtName = view.findViewById(R.id.edtName);
        EditText edtPrice = view.findViewById(R.id.edtPrice);
        EditText edtDate = view.findViewById(R.id.edtDate);
        Spinner spnCate = view.findViewById(R.id.spnCate);
        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnCancel = view.findViewById(R.id.btnCanccel);

        //tieu de
        if(trangthai == 0){
            txtTitle.setText("THÊM MỚI KHOAN THU");
        }else{
            txtTitle.setText("THÊM MỚI KHOAN CHI");
        }
        //nut cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        //show date picker dialog
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(edtDate);
            }
        });
        //load data len spinner
        loadDataSpinner(spnCate);
        //lay gia tri cua spinner
        spnCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Loai loai = (Loai) adapterView.getAdapter().getItem(i);
                idLoai = loai.getIdLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                String name = edtName.getText().toString();
                int price = Integer.parseInt(edtPrice.getText().toString()) ;
                String date = edtDate.getText().toString();

                Khoan khoan = new Khoan(name, price, date, idLoai);
                boolean check = khoanDao.themKhoanMoi(khoan);
                if(check){
                    Toast.makeText(getContext(), "Successfully", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    loadData();
                }else{
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog.show();

    }
    private void showDatePickerDialog(EditText edtDate){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int month = i1+1;
                        edtDate.setText(i2+"/"+month+"/"+i);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }
    private void loadDataSpinner(Spinner spnCate){
        LoaiDao loaiDao = new LoaiDao(getContext());
        ArrayList<Loai> listLoai = loaiDao.getDS(trangthai);
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(getContext(), listLoai);
        spnCate.setAdapter(adapter);
    }
}
