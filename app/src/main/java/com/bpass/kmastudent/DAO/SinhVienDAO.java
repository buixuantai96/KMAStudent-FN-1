package com.bpass.kmastudent.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bpass.kmastudent.DTO.SinhVienDTO;
import com.bpass.kmastudent.Database.CreateDatabase;


public class SinhVienDAO {
    //SQLiteDatabase database;
    Context context;

    public SinhVienDAO(Context context){
        //CreateDatabase createDatabase = new CreateDatabase(context);
        //database = createDatabase.open1();
        this.context = context;
    }

    public long themSinhVien(SinhVienDTO sinhVienDTO){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(CreateDatabase.TB_SINHVIEN_MASV, sinhVienDTO.getMASV());
//        contentValues.put(CreateDatabase.TB_SINHVIEN_MATKHAU, sinhVienDTO.getMATKHAU());
//        contentValues.put(CreateDatabase.TB_SINHVIEN_TENSV, sinhVienDTO.getTENSV());
//        contentValues.put(CreateDatabase.TB_SINHVIEN_MAIL, sinhVienDTO.getMAIL());
//        contentValues.put(CreateDatabase.TB_SINHVIEN_LOGINSTATUS, sinhVienDTO.getLOGINSTATUS());
//
//        long kiemtra = database.insert(CreateDatabase.TB_SINHVIEN, null, contentValues);
//        return kiemtra;
        //CreateDatabase createDatabase = new CreateDatabase(context);
        //SQLiteDatabase database = createDatabase.open1();
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase database = createDatabase.open1();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CreateDatabase.TB_SINHVIEN_MASV, sinhVienDTO.getMASV());
            contentValues.put(CreateDatabase.TB_SINHVIEN_MATKHAU, sinhVienDTO.getMATKHAU());
            contentValues.put(CreateDatabase.TB_SINHVIEN_TENSV, sinhVienDTO.getTENSV());
            contentValues.put(CreateDatabase.TB_SINHVIEN_MAIL, sinhVienDTO.getMAIL());
            contentValues.put(CreateDatabase.TB_SINHVIEN_LOGINSTATUS, sinhVienDTO.getLOGINSTATUS());

            long kiemtra = database.insert(CreateDatabase.TB_SINHVIEN, null, contentValues);
            return kiemtra;
        }
        finally {
            if(database != null && database.isOpen()){
                database.close();
            }
        }
    }

    public boolean kiemTraDangNhap(String maSV, String mat_khau){
//        CreateDatabase createDatabase = new CreateDatabase(context);
//        SQLiteDatabase database = createDatabase.open1();
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase database = createDatabase.open1();

        String truyvan = "SELECT * FROM " + CreateDatabase.TB_SINHVIEN + " WHERE "
                + CreateDatabase.TB_SINHVIEN_MASV + " = '" + maSV + "' AND "
                + CreateDatabase.TB_SINHVIEN_MATKHAU + " = '" + mat_khau +"'";
//        Cursor  cursor = database.rawQuery(truyvan, null);
//        if (cursor.getCount() != 0){
//            cursor.close();
//            return true;
//        }
//        else {
//            cursor.close();
//            return false;
//        }

        try{
            Cursor  cursor = database.rawQuery(truyvan, null);
            try {
                if (cursor.getCount() != 0){
                    return true;
                }
                else {
                    return false;
                }
            }
            finally {
                cursor.close();
            }
        }
        finally {
            if(database != null && database.isOpen()){
                database.close();
            }
        }

    }

    public void updateLoginStatus(String masv){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(CreateDatabase.TB_SINHVIEN_LOGINSTATUS,"1");
//        database.update(CreateDatabase.TB_SINHVIEN,contentValues,"masv = ?",new String[]{masv});

//        CreateDatabase createDatabase = new CreateDatabase(context);
//        SQLiteDatabase database = createDatabase.open1();
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase database = createDatabase.open1();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(CreateDatabase.TB_SINHVIEN_LOGINSTATUS,"1");
            database.update(CreateDatabase.TB_SINHVIEN,contentValues,"masv = ?",new String[]{masv});
        }
        finally {
            if(database != null && database.isOpen()){
                database.close();
            }
        }
    }

    public String getLoginStatus(String masv){
//        CreateDatabase createDatabase = new CreateDatabase(context);
//        SQLiteDatabase database = createDatabase.open1();
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase database = createDatabase.open1();

        String truyvan = " select " + CreateDatabase.TB_SINHVIEN_LOGINSTATUS + " from " + CreateDatabase.TB_SINHVIEN +
                " where " + CreateDatabase.TB_SINHVIEN_MASV + " = '" + masv + "'";
//        Cursor cursor = database.rawQuery(truyvan,null);
//        cursor.moveToFirst();
//        String loginStatus = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_SINHVIEN_LOGINSTATUS));
//        cursor.close();
//        return loginStatus;

        try {
            Cursor cursor = database.rawQuery(truyvan,null);
            try{
                cursor.moveToFirst();
                String loginStatus = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_SINHVIEN_LOGINSTATUS));
                return loginStatus;
            }
            finally {
                cursor.close();
            }
        }
        finally {
            if(database != null && database.isOpen()){
                database.close();
            }
        }
    }

}
