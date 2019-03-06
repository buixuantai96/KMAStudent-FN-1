package com.bpass.kmastudent.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bpass.kmastudent.Activity.DiemActivity;
import com.bpass.kmastudent.DTO.DiemDTO;
import com.bpass.kmastudent.Database.CreateDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by buixu on 16/11/2016.
 */

public class DiemDAO {
    Context context;
    DiemDTO diemDTO;

    public DiemDAO(Context context) {
        this.context = context;
    }



    public int updateDiem(DiemDTO diemDTO) {
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase database = createDatabase.open1();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_DIEMCHUYENCAN, String.valueOf(diemDTO.getDIEMCC()));
            contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_DIEMGIUAKY, String.valueOf(diemDTO.getDIEMGK()));
            contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_DIEMKTHP, String.valueOf(diemDTO.getDIEMKTHP()));
            contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_DIEMTBMON, String.valueOf(diemDTO.getDIEMTB()));
            contentValues.put(CreateDatabase.TB_LICHHOCVADIEM_DIEMTONGKET, diemDTO.getDIEMTK());

            int kiemTra = database.update(CreateDatabase.TB_LICHHOCVADIEM, contentValues, "MASV = ? and IDLOP = ?",
                    new String[]{diemDTO.getMASV(), diemDTO.getIDLOP()});
            return kiemTra;
        }
        finally {
            database.close();
        }

    }


    public ArrayList<DiemDTO> LayDanhSachDIEM(String masv) {
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase database = createDatabase.open1();
        ArrayList<DiemDTO> LayDanhSachDIEM = new ArrayList<>();
        String cauTruyVan = " SELECT * FROM "
                + CreateDatabase.TB_MONHOC + " JOIN " + CreateDatabase.TB_LICHHOCVADIEM + " USING( " +
                CreateDatabase.TB_MONHOC_IDMONHOC + " ) WHERE " + CreateDatabase.TB_LICHHOCVADIEM_MASV + " ='" + masv+"'";

        try {
            Cursor cursor = database.rawQuery(cauTruyVan, null);
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    diemDTO = new DiemDTO();
                    diemDTO.setMASV(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_LICHHOCVADIEM_MASV)));
                    diemDTO.setIDLOP(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_LICHHOCVADIEM_IDLOP)));
                    diemDTO.setIDMONHOC(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONHOC_IDMONHOC)));
                    diemDTO.setTENMH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONHOC_TENMON)));
                    diemDTO.setSoTin(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONHOC_SOTINCHI)));
                    diemDTO.setDIEMCC(cursor.getFloat(cursor.getColumnIndex(CreateDatabase.TB_LICHHOCVADIEM_DIEMCHUYENCAN)));
                    diemDTO.setDIEMGK(cursor.getFloat(cursor.getColumnIndex(CreateDatabase.TB_LICHHOCVADIEM_DIEMGIUAKY)));
                    diemDTO.setDIEMKTHP(cursor.getFloat(cursor.getColumnIndex(CreateDatabase.TB_LICHHOCVADIEM_DIEMKTHP)));
                    diemDTO.setDIEMTB(cursor.getFloat(cursor.getColumnIndex(CreateDatabase.TB_LICHHOCVADIEM_DIEMTBMON)));
                    diemDTO.setDIEMTK(cursor.getFloat(cursor.getColumnIndex(CreateDatabase.TB_LICHHOCVADIEM_DIEMTONGKET)));

                    LayDanhSachDIEM.add(diemDTO);

                    cursor.moveToNext();
                }
                return LayDanhSachDIEM;
            }
            finally {
                cursor.close();
            }
        }
        finally {
            database.close();
        }
    }



    public int sosanh1() {
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase database = createDatabase.open1();
        String CautruyVan = "SELECT " + CreateDatabase.TB_LICHHOCVADIEM_IDMONHOC + " FROM " + CreateDatabase.TB_LICHHOCVADIEM
                + " WHERE " + CreateDatabase.TB_LICHHOCVADIEM_MASV + " = '" + DiemDTO.MASV + "'";
        try{
            Cursor cursor1 = database.rawQuery(CautruyVan, null);
            try{
                //cursor1.moveToFirst();
                int soluongmon = cursor1.getCount();
                return soluongmon;
            }
            finally {
                cursor1.close();
            }
        }
        finally {
            database.close();
        }
    }

    public int sosanh2(){
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase database = createDatabase.open1();
        String Query = " SELECT " + CreateDatabase.TB_LICHHOCVADIEM_DIEMTBMON + "  FROM "
                + CreateDatabase.TB_LICHHOCVADIEM + " WHERE " + CreateDatabase.TB_LICHHOCVADIEM_DIEMTBMON + " > " + 0 + " AND "
                + CreateDatabase.TB_LICHHOCVADIEM_MASV + " = '" + DiemDTO.MASV +"'";;
        try{
            Cursor cursor2 = database.rawQuery(Query, null);
            try{
                //cursor2.moveToFirst();
                int soluongdiem = cursor2.getCount();
                return  soluongdiem;
            }
            finally {
                cursor2.close();
            }
        }
        finally {
            database.close();
        }
    }

    public float TinhDiemTK(String masv) {
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase database = createDatabase.open1();
        String cauTruyVan = " SELECT SUM ( " + CreateDatabase.TB_LICHHOCVADIEM_DIEMTBMON + " * " + CreateDatabase.TB_MONHOC_SOTINCHI
                +  " ) / SUM ( " + CreateDatabase.TB_MONHOC_SOTINCHI + " ) AS 'ABC' " + " FROM "
                + CreateDatabase.TB_MONHOC + " JOIN " + CreateDatabase.TB_LICHHOCVADIEM + " USING ( " +
                CreateDatabase.TB_MONHOC_IDMONHOC + " ) WHERE " + CreateDatabase.TB_LICHHOCVADIEM_MASV + "='" + masv+"'" ;

        try{
            Cursor cursor = database.rawQuery(cauTruyVan,null);
            try{
                cursor.moveToFirst();
                float DiemTKet = cursor.getFloat(0);
                return DiemTKet;
            }
            finally {
                cursor.close();
            }
        }
        finally {
            database.close();
        }
    }

}
