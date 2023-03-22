package com.example.QUANLYHOCPHAN21IT091

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context) : SQLiteOpenHelper(context,"HOCPHANDB",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        //tạo table , column
        p0?.execSQL("CREATE TABLE HOCPHAN(_id integer primary key autoincrement,MaHP TEXT, TenHP TEXT, SoTC INTEGER, LoaiHP TEXT)")
        //thêm data vào cơ sở dữ liệu
        p0?.execSQL("Insert into HOCPHAN(MaHP,TenHP,soTC,LoaiHP) values ('LTDD','Lập trình di động','3','Bắt buôc')")
        p0?.execSQL("Insert into HOCPHAN(MaHP,TenHP,soTC,LoaiHP) values ('TA3','Tiếng anh 3','2','Bắt Buộc')")
        p0?.execSQL("Insert into HOCPHAN(MaHP,TenHP,soTC,LoaiHP) values ('HH','Hình Họa','2','Tự chọn')")
        p0?.execSQL("Insert into HOCPHAN(MaHP,TenHP,soTC,LoaiHP) values ('CSTH','cơ sở tạo hình','2','tự chọn')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}