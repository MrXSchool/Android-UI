package com.example.assignment.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.database.KhoanDao;
import com.example.assignment.database.LoaiDao;
import com.example.assignment.models.Khoan;
import com.example.assignment.models.Loai;

import java.util.ArrayList;
import java.util.Calendar;

public class KhoanAdapter extends RecyclerView.Adapter<KhoanAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Khoan> list;
    private int trangthai;
    private KhoanDao khoanDao;
    private ArrayList<Loai> listLoai;

    public KhoanAdapter(Context context, ArrayList<Khoan> list, int trangthai, KhoanDao khoanDao) {
        this.context = context;
        this.list = list;
        this.trangthai = trangthai;
        this.khoanDao = khoanDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khoan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtID.setText(String.valueOf( list.get(position).getIdKhoan()));
        holder.txtName.setText(list.get(position).getTenKhoan());
        holder.txtPrice.setText(String.valueOf(list.get(position).getTien()));
        holder.txtDate.setText(list.get(position).getNgay());

        //nut edit
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(list.get(holder.getAdapterPosition()));
            }
        });
        //nut delete
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        khoanDao.xoaKhoan(list.get(holder.getAdapterPosition()).getIdKhoan());
                        list.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
    private void showDialog(Khoan khoan){
        AlertDialog.Builder buidel = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_khoan_add_edit, null);

        Spinner spnCate = view.findViewById(R.id.spnCate);
        //load data
        loadDataSpinner(spnCate);
        int positionItem = -1;
        for(int i=0; i<listLoai.size(); i++){
            if(listLoai.get(i).getIdLoai() == khoan.getIdLoai()){
                positionItem = i;
                break;
            }
        }
        spnCate.setSelection(positionItem);




        buidel.setView(view);
        AlertDialog alertDialog = buidel.create();
        //ánh xạ 2 button trong dialog
        Button ivAdd = view.findViewById(R.id.btnSave);
        Button ivCancel = view.findViewById(R.id.btnCanccel);
        //ánh xạ các edittext trong dialog
        TextView edtName = view.findViewById(R.id.edtName);
        TextView edtDate = view.findViewById(R.id.edtDate);
        TextView edtPrice = view.findViewById(R.id.edtPrice);

        //fill dữ liệu vào edittext
        edtName.setText(khoan.getTenKhoan());
        edtDate.setText(khoan.getNgay());
        edtPrice.setText(String.valueOf(khoan.getTien()));


        //set sự kiện cho 2 button

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lấy dữ liệu từ dialog String tenKhoan, int tien, String ngay, int idLoai
                String tenKhoan = edtName.getText().toString();
                int tien = Integer.parseInt(edtPrice.getText().toString());
                String ngay = edtDate.getText().toString();
                int idLoai = listLoai.get(spnCate.getSelectedItemPosition()).getIdLoai();
                int idKhoan = khoan.getIdKhoan();
                //set text cho txtCate từ spinner
                TextView txtCate = view.findViewById(R.id.txtCate);
                txtCate.setText(listLoai.get(spnCate.getSelectedItemPosition()).getTenLoai());

                //tạo đối tượng khoản
                Khoan khoan = new Khoan(idKhoan, tenKhoan, tien, ngay, idLoai);

                //gọi hàm sửa
                khoanDao.suaKhoan(khoan);
                //load lại dữ liệu
                list.clear();
                list.addAll(khoanDao.getDsKhoan(trangthai));
                notifyDataSetChanged();
                //đóng dialog

                alertDialog.dismiss();

            }
        });

        //hiện daypickerdialog khi click vào edittext ngày
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DAY_OF_MONTH);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        edtDate.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });



        alertDialog.show();



    }
    private void loadDataSpinner(Spinner spnCate){
        LoaiDao loaiDao = new LoaiDao(context);
        listLoai = loaiDao.getDS(trangthai);
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(context, listLoai);
        spnCate.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtID, txtName, txtDate, txtPrice, txtCate;
        ImageView ivEdit, ivDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtName = itemView.findViewById(R.id.txtName);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtCate = itemView.findViewById(R.id.txtCate);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDel = itemView.findViewById(R.id.ivDel);


        }
    }
}
