package com.example.assignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment.models.Khoan;

import java.util.ArrayList;

public class KhoanDao {
    private DbHelper dbHelper;
    public KhoanDao(Context context){
        dbHelper = new DbHelper(context);
    }

    //lay danh sach KHOAN
    public ArrayList<Khoan> getDsKhoan(int trangThai){
        //trangThai: 1-chi; 0-thu
        ArrayList<Khoan> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAI WHERE trangThai = ?",new String[]{String.valueOf(trangThai)});
        if(cursor.getCount() >0){
            cursor.moveToFirst();
            do{
                int idLoai = cursor.getInt(0);

                // lay danh sach khoan
                Cursor cursorKhoan = sqLiteDatabase.rawQuery("SELECT * FROM KHOAN WHERE idLoai = ?", new String[]{String.valueOf(idLoai)});
                if(cursorKhoan.getCount() > 0){
                    cursorKhoan.moveToFirst();
                    do{
                        list.add(new Khoan(cursorKhoan.getInt(0),
                                            cursorKhoan.getString(1),
                                            cursorKhoan.getInt(2),
                                            cursorKhoan.getString(3),
                                            cursorKhoan.getInt(4)));
                    }while (cursorKhoan.moveToNext());
                }
            }while (cursor.moveToNext());
        }
        return list;
    }

    //them khoan
    public boolean themKhoanMoi(Khoan khoan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKhoan", khoan.getTenKhoan());
        contentValues.put("tien", khoan.getTien());
        contentValues.put("ngay", khoan.getNgay());
        contentValues.put("idLoai", khoan.getIdLoai());

        long check = sqLiteDatabase.insert("KHOAN", null, contentValues);
        return  check == -1 ? false : true;
    }

    //sua khoan
    public boolean suaKhoan(Khoan khoan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKhoan", khoan.getTenKhoan());
        contentValues.put("tien", khoan.getTien());
        contentValues.put("ngay", khoan.getNgay());
        contentValues.put("idLoai", khoan.getIdLoai());

        long check = sqLiteDatabase.update("KHOAN", contentValues, "idKhoan = ?", new String[]{String.valueOf(khoan.getIdKhoan())});
        return  check == -1 ? false : true;
    }

    //xoa khoan
    public boolean xoaKhoan(int idKhoan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("KHOAN", "idKhoan = ? ",new String[]{String.valueOf(idKhoan)});
        return  check == -1 ? false : true;
    }

    //lay danh sach KHOAN theo LOAI
    public ArrayList<Khoan> getDsKhoanTheoLoai(int idLoai){
        //trangThai: 1-chi; 0-thu
        ArrayList<Khoan> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHOAN WHERE idLoai = ?", new String[]{String.valueOf(idLoai)});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                list.add(new Khoan(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getInt(4)));
            }while (cursor.moveToNext());
        }

        return list;
    }
}
