package com.example.assignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment.models.Loai;

import java.util.ArrayList;

public class LoaiDao {
    private DbHelper dbHelper;
    public LoaiDao(Context context){
        dbHelper = new DbHelper(context);
    }

    //lay danh sach loai thu/chi
    //trangthai: 0-thu, 1-chi;
    public ArrayList<Loai> getDS(int trangThai){
        ArrayList<Loai> list = new ArrayList<>();
        //lay quyen su dung trong database
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAI WHERE trangThai = ?", new String[]{String.valueOf(trangThai)});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                list.add(new Loai(cursor.getInt(0), cursor.getString(1),cursor.getInt(2) ));
            }while (cursor.moveToNext());
        }
        return list;
    }

    //them loai
    public boolean themLoai(String tenLoai, int TrangThai){
        //lay quyen su dung database
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", tenLoai);
        contentValues.put("trangThai", TrangThai);

        long check = sqLiteDatabase.insert("LOAI", null,contentValues);
        if(check == -1){
            return false;
        }
        return true;
    }

    //chinh sua
    public boolean capNhatLoai(int idLoai, String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", tenLoai);
        long check = sqLiteDatabase.update("LOAI", contentValues, "idLoai = ?", new String[]{String.valueOf(idLoai)});
        return  check == -1 ? false : true;
    }

    //xoa 1-thanh cong; 0-that bai; -1-rang buoc khoa ngoai
    public int xoaLoai(int idLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        //kiem tra khoa ngoai
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHOAN WHERE idLoai = ?", new String[]{String.valueOf(idLoai)});
        if(cursor.getCount()>0){
            return -1;// dung khoa ngoai
        }else{
            long check = sqLiteDatabase.delete("LOAI", "idLoai = ?", new String[]{String.valueOf(idLoai)});
            if(check == -1){
                return 0;
            }
            return 1;
        }
    }

    public String getNameById(int idLoai){
        String name="";

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT tenLoai FROM loai WHERE idLoai = ?", new String[]{String.valueOf(idLoai)});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            name = cursor.getString(0);
        }

        return name;
    }

}
