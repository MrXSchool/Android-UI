package com.example.assignment.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.database.KhoanDao;
import com.example.assignment.database.LoaiDao;
import com.example.assignment.models.Khoan;
import com.example.assignment.models.Loai;

import java.util.ArrayList;

public class LoaiAdapter extends RecyclerView.Adapter<LoaiAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Loai> list;
    private int trangthai;
    private LoaiDao loaiDao;

    public LoaiAdapter(Context context, ArrayList<Loai> list, int trangthai, LoaiDao loaiDao) {
        this.context = context;
        this.list = list;
        this.trangthai = trangthai;
        this.loaiDao = loaiDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loai, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenLoai.setText(list.get(position).getTenLoai());

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
                showDialogDelete(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaLoai, txtTenLoai;
        ImageView ivEdit, ivDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //anh xa
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDel = itemView.findViewById(R.id.ivDelete);
        }
    }
    private void showDialog(Loai loai){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
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
            txtTitle.setText("CHỈNH SỬA LOẠI THU");
        }else{
            txtTitle.setText("CHỈNH SỬA LOẠI CHI");
        }

        //fill data
        edtName.setText(loai.getTenLoai());

        //button cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        //button save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                boolean check = loaiDao.capNhatLoai(loai.getIdLoai(),name );
                if(check){
                    Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    loadData();
                }else{
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loadData(){
        list = loaiDao.getDS(trangthai);
        notifyDataSetChanged();
    }

    private void showDialogDelete(Loai loai){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        String message = "Bạn có muốn xoá loại " + loai.getTenLoai() + " hay không ?";
        builder.setMessage(message);
        builder.setIcon(R.mipmap.warning);



        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int check = loaiDao.xoaLoai(loai.getIdLoai());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xoá thành công ", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xoá thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Có khoản thu/chi", Toast.LENGTH_SHORT).show();
                        showDialogInfo(loai.getIdLoai());
                        break;
                }
            }
        });

        builder.setNegativeButton("Không", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void showDialogInfo(int idLoai){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Danh sách khoản thu/chi bị trùng, bạn có muốn xoá không?");
        KhoanDao khoanDao = new KhoanDao(context);
        ArrayList<Khoan> listKhoan = khoanDao.getDsKhoanTheoLoai(idLoai);
        String content ="";
        for(Khoan khoan : listKhoan){
            content = " - " + khoan.getTenKhoan() + "\n";
        }
        builder.setMessage(content);
        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for(Khoan khoan : listKhoan){
                    khoanDao.xoaKhoan(khoan.getIdKhoan());
                }
                loaiDao.xoaLoai(idLoai);
                loadData();
            }
        });
        builder.setNegativeButton("Không", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
