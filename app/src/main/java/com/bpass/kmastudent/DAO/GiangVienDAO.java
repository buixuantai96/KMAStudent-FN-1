package com.bpass.kmastudent.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bpass.kmastudent.DTO.GiangVienDTO;
import com.bpass.kmastudent.Database.CreateDatabase;

import java.util.ArrayList;



/**
 * Created by VietDung-KMA-AT11D on 11/21/16.
 */

public class GiangVienDAO{
    //SQLiteDatabase sqLiteDatabase;
    Context context;

    public GiangVienDAO(Context context){
        //CreateDatabase createDatabase = new CreateDatabase(context);
        //sqLiteDatabase = createDatabase.open1();
        this.context = context;
    }

    /* select [GIANGVIEN].*
from ([GIANGVIEN] join [LOPHOC] on [GIANGVIEN].[IDGV]=[LOPHOC].[IDGIANGVIEN]) join [MONHOC] on [MONHOC].[IDMONHOC]=[LOPHOC].[IDMONHOC]
where [TENLOP] = 'L01' and [MONHOC].[IDMONHOC] = 'DVM' */

    public void deleteAll(){
//        CreateDatabase createDatabase = new CreateDatabase(context);
//        SQLiteDatabase sqLiteDatabase = createDatabase.open1();
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase sqLiteDatabase = createDatabase.open1();
        sqLiteDatabase.execSQL(" delete from  GIANGVIEN ");
    }

    public void insertGiangVienToDB(GiangVienDTO giangVienDTO){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(CreateDatabase.TB_GIANGVIEN_IDGV,giangVienDTO.getIDGiangVien());
//        contentValues.put(CreateDatabase.TB_GIANGVIEN_TENGV,giangVienDTO.getTenGiangVien());
//        contentValues.put(CreateDatabase.TB_GIANGVIEN_KHOA,giangVienDTO.getKhoaGiangVien());
//        contentValues.put(CreateDatabase.TB_GIANGVIEN_MAILGV,giangVienDTO.getEmailGiangVien());
//        contentValues.put(CreateDatabase.TB_GIANGVIEN_GIOITHIEUGV,giangVienDTO.getGioiThieuGiangVien());
//        contentValues.put(CreateDatabase.TB_GIANGVIEN_ANHGV,giangVienDTO.getAnhGiangVien());
//        sqLiteDatabase.insert(CreateDatabase.TB_GIANGVIEN,null,contentValues);
//        CreateDatabase createDatabase = new CreateDatabase(context);
//        SQLiteDatabase sqLiteDatabase = createDatabase.open1();
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase sqLiteDatabase = createDatabase.open1();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(CreateDatabase.TB_GIANGVIEN_IDGV,giangVienDTO.getIDGiangVien());
            contentValues.put(CreateDatabase.TB_GIANGVIEN_TENGV,giangVienDTO.getTenGiangVien());
            contentValues.put(CreateDatabase.TB_GIANGVIEN_KHOA,giangVienDTO.getKhoaGiangVien());
            contentValues.put(CreateDatabase.TB_GIANGVIEN_MAILGV,giangVienDTO.getEmailGiangVien());
            contentValues.put(CreateDatabase.TB_GIANGVIEN_GIOITHIEUGV,giangVienDTO.getGioiThieuGiangVien());
            contentValues.put(CreateDatabase.TB_GIANGVIEN_ANHGV,giangVienDTO.getAnhGiangVien());
            sqLiteDatabase.insert(CreateDatabase.TB_GIANGVIEN,null,contentValues);
        }
        finally {
            sqLiteDatabase.close();
        }
    }

    public ArrayList<GiangVienDTO> layGiangVienTheoTenMonVaTenLop(String maMon){
//        CreateDatabase createDatabase = new CreateDatabase(context);
//        SQLiteDatabase sqLiteDatabase = createDatabase.open1();
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase sqLiteDatabase = createDatabase.open1();
        ArrayList<GiangVienDTO> listGiangVien = new ArrayList<>();

        String cauTruyVan = " select distinct [GIANGVIEN].*\n" +
                "from [GIANGVIEN] join [LOPHOC] on [GIANGVIEN].[IDGV]=[LOPHOC].[IDGV]\n" +
                "where [IDMONHOC] = ? ";
//        Cursor cursor = sqLiteDatabase.rawQuery(cauTruyVan,new String[]{maMon});
//        for (int i=0;i<cursor.getCount();i++){
//            cursor.moveToPosition(i);
//            GiangVienDTO giangVienDTO = new GiangVienDTO();
//            giangVienDTO.setIDGiangVien(cursor.getString(0));
//            giangVienDTO.setTenGiangVien(cursor.getString(1));
//            giangVienDTO.setKhoaGiangVien(cursor.getString(2));
//            giangVienDTO.setEmailGiangVien(cursor.getString(3));
//            giangVienDTO.setGioiThieuGiangVien(cursor.getString(4));
//            giangVienDTO.setAnhGiangVien(cursor.getBlob(5));
//            listGiangVien.add(giangVienDTO);
//        }
//        cursor.close();
//        return  listGiangVien;

        try {
            Cursor cursor = sqLiteDatabase.rawQuery(cauTruyVan,new String[]{maMon});
            try {
                for (int i=0;i<cursor.getCount();i++){
                    cursor.moveToPosition(i);
                    GiangVienDTO giangVienDTO = new GiangVienDTO();
                    giangVienDTO.setIDGiangVien(cursor.getString(0));
                    giangVienDTO.setTenGiangVien(cursor.getString(1));
                    giangVienDTO.setKhoaGiangVien(cursor.getString(2));
                    giangVienDTO.setEmailGiangVien(cursor.getString(3));
                    giangVienDTO.setGioiThieuGiangVien(cursor.getString(4));
                    giangVienDTO.setAnhGiangVien(cursor.getBlob(5));
                    listGiangVien.add(giangVienDTO);
                }
                return  listGiangVien;
            }
            finally {
                cursor.close();
            }
        }
        finally {
            sqLiteDatabase.close();
        }
    }
}
