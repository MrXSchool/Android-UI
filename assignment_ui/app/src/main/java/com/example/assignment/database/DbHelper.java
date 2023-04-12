package com.example.assignment.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context,"QLTHUCHI", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qLoai ="CREATE TABLE LOAI (idLoai integer primary key autoincrement, tenLoai text, trangThai integer)";
        sqLiteDatabase.execSQL(qLoai);

        String qKhoan = "CREATE TABLE KHOAN(idKhoan integer primary key autoincrement, tenKhoan text, tien integer, ngay text,idLoai integer references LOAI(idLoai) )";
        sqLiteDatabase.execSQL(qKhoan);

        //data test
        //trang thai: 0-thu, 1-chi
        String dLoai="INSERT INTO LOAI VALUES(1,'tiền thưởng',0),(2,'tiền lương', 0),(3,'trọ', 1)";
        sqLiteDatabase.execSQL(dLoai);

        String dKhoan ="INSERT INTO KHOAN VALUES(1,'Thuong thang 1', 5000, '12/12/1221',1)";
        sqLiteDatabase.execSQL(dKhoan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAI");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS KHOAN");
            onCreate(sqLiteDatabase);
        }
    }
}
