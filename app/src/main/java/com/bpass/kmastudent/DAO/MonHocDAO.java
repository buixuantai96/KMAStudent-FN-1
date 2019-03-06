package com.bpass.kmastudent.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bpass.kmastudent.DTO.MonHocDTO;
import com.bpass.kmastudent.Database.CreateDatabase;

import java.util.ArrayList;



/**
 * Created by VietDung-KMA-AT11D on 11/16/16.
 */


public class MonHocDAO{
    //SQLiteDatabase sqLiteDatabase;
    Context context;


    public MonHocDAO(Context context){
        //CreateDatabase createDatabase = new CreateDatabase(context);
        //sqLiteDatabase = createDatabase.open1();
        this.context = context;
    }
    //cam may vao de anh debug? dạ máy nào hả anh. chu dung may ao ah dạ vâng, ạbat may ao len em bật rồi ạ, dau?
    public ArrayList<String> layIDNamKy(String nam, String ky) {
//        CreateDatabase createDatabase = new CreateDatabase(context);
//        SQLiteDatabase sqLiteDatabase = createDatabase.open1();
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase sqLiteDatabase = createDatabase.open1();
        ArrayList<String> listIDNamKy = new ArrayList<>();
        String cauTruyVan = " SELECT " + CreateDatabase.TB_NAMKY_IDNAMKY + " FROM " + CreateDatabase.TB_NAMKY + " WHERE " + CreateDatabase.TB_NAMKY_NAM + " = ? AND " +
                CreateDatabase.TB_NAMKY_KY + " = ? ";
//        Cursor cursor = sqLiteDatabase.rawQuery(cauTruyVan,new String[]{nam,ky});
//        if (!cursor.isAfterLast()){
//            cursor.moveToNext();
//            //String id = cursor.getString(0);
//            String id = cursor.getString( cursor.getColumnIndex(CreateDatabase.TB_NAMKY_IDNAMKY));
//            listIDNamKy.add(id);
//        }
//        cursor.close();
//        return listIDNamKy;

        try {
            Cursor cursor = sqLiteDatabase.rawQuery(cauTruyVan,new String[]{nam,ky});
            try {
                if (!cursor.isAfterLast()){
                    cursor.moveToNext();
                    //String id = cursor.getString(0);
                    String id = cursor.getString( cursor.getColumnIndex(CreateDatabase.TB_NAMKY_IDNAMKY));
                    listIDNamKy.add(id);
                }
                return listIDNamKy;
            }
            finally {
                cursor.close();
            }
        }
        finally {
            sqLiteDatabase.close();
        }
    }

    public ArrayList<MonHocDTO> layListMonHoc(String idNamKy){
//        CreateDatabase createDatabase = new CreateDatabase(context);
//        SQLiteDatabase sqLiteDatabase = createDatabase.open1();
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase sqLiteDatabase = createDatabase.open1();

        ArrayList<MonHocDTO> listMonHoc = new ArrayList<>();
        String cauTruyVan = " SELECT * FROM " + CreateDatabase.TB_MONHOC + " WHERE " + CreateDatabase.TB_MONHOC_IDNAMKY + " = ?";
//        Cursor cursor = sqLiteDatabase.rawQuery(cauTruyVan,new String[]{idNamKy});
//        for (int i=0;i<cursor.getCount();i++){
//            cursor.moveToPosition(i);
//            String IDMonHoc = cursor.getString(0);
//            String tenMonHoc = cursor.getString(1);
//            //int doUuTien = cursor.getInt(2);
//            String tailieu = cursor.getString(2);
//            float doKho = cursor.getFloat(3);
//            int soTinChi = cursor.getInt(4);
//            String gioiThieu = cursor.getString(5);
//            String IDNamKy = cursor.getString(6);
//            int soLuongLop = cursor.getInt(7);
//
//
//            MonHocDTO monHocDTO = new MonHocDTO();
//            monHocDTO.setIdMonHoc(IDMonHoc);
//            monHocDTO.setTenMonHoc(tenMonHoc);
//            monHocDTO.setSoTinChi(soTinChi);
//            monHocDTO.setDoKho(doKho);
//            monHocDTO.setTaiLieuThamKhao(tailieu);
//            monHocDTO.setGioiThieuMonHoc(gioiThieu);
//            monHocDTO.setIdNamKy(IDNamKy);
//            monHocDTO.setSoLuongLop(soLuongLop);
//            //monHocDTO.setDoUuTien(doUuTien);
//            listMonHoc.add(monHocDTO);
//        }
//        cursor.close();
//        return listMonHoc;

        try{
            Cursor cursor = sqLiteDatabase.rawQuery(cauTruyVan,new String[]{idNamKy});
            try {
                for (int i=0;i<cursor.getCount();i++){
                    cursor.moveToPosition(i);
                    String IDMonHoc = cursor.getString(0);
                    String tenMonHoc = cursor.getString(1);
                    //int doUuTien = cursor.getInt(2);
                    String tailieu = cursor.getString(2);
                    float doKho = cursor.getFloat(3);
                    int soTinChi = cursor.getInt(4);
                    String gioiThieu = cursor.getString(5);
                    String IDNamKy = cursor.getString(6);
                    int soLuongLop = cursor.getInt(7);


                    MonHocDTO monHocDTO = new MonHocDTO();
                    monHocDTO.setIdMonHoc(IDMonHoc);
                    monHocDTO.setTenMonHoc(tenMonHoc);
                    monHocDTO.setSoTinChi(soTinChi);
                    monHocDTO.setDoKho(doKho);
                    monHocDTO.setTaiLieuThamKhao(tailieu);
                    monHocDTO.setGioiThieuMonHoc(gioiThieu);
                    monHocDTO.setIdNamKy(IDNamKy);
                    monHocDTO.setSoLuongLop(soLuongLop);
                    //monHocDTO.setDoUuTien(doUuTien);
                    listMonHoc.add(monHocDTO);
                }
                return listMonHoc;
            }
            finally {
                cursor.close();
            }
        }
        finally {
            sqLiteDatabase.close();
        }
    }


    /* Nhóm câu lệnh truy vấn liên quan tới các thể loại LỚP */
    public String layIDLopTheoTenLopVaTenMon(String tenLop,String tenMon){
//        CreateDatabase createDatabase = new CreateDatabase(context);
//        SQLiteDatabase sqLiteDatabase = createDatabase.open1();
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase sqLiteDatabase = createDatabase.open1();

        String cauTruyVan = " Select [IDLOP]\n" +
                "        from [LOPHOC] join [MONHOC] on [LOPHOC].[IDMONHOC] = [MONHOC].[IDMONHOC]\n" +
                "        where [TENLOP] = ? " + " and [TENMON] = ?";
//        Cursor cursor = sqLiteDatabase.rawQuery(cauTruyVan,new String[]{tenLop,tenMon});
//        cursor.moveToFirst();
//        String IDLop = cursor.getString(0);
//        cursor.close();
//        return IDLop;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(cauTruyVan,new String[]{tenLop,tenMon});
            try{
                cursor.moveToFirst();
                String IDLop = cursor.getString(0);
                return IDLop;
            }
            finally {
                cursor.close();
            }
        }
        finally {
            sqLiteDatabase.close();
        }
    }


    public void insertIntoLichHocVaDiem(String MaSV, String IDNamKy, MonHocDTO monHocDTO, String IDLop){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_MASV,MaSV);
//        contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_IDLOP,IDLop);
//        contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_IDMONHOC, monHocDTO.getIdMonHoc());
//        contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_IDNAMKY, IDNamKy);
//
//        sqLiteDatabase.insert(CreateDatabase.TB_LICHHOCVADIEM,null,contentValues);

//        CreateDatabase createDatabase = new CreateDatabase(context);
//        SQLiteDatabase sqLiteDatabase = createDatabase.open1();
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase sqLiteDatabase = createDatabase.open1();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_MASV,MaSV);
            contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_IDLOP,IDLop);
            contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_IDMONHOC, monHocDTO.getIdMonHoc());
            contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_IDNAMKY, IDNamKy);

            sqLiteDatabase.insert(CreateDatabase.TB_LICHHOCVADIEM,null,contentValues);
        }
        finally {
            sqLiteDatabase.close();
        }
    }

}