package com.bpass.kmastudent.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.bpass.kmastudent.DTO.KhoangThoiGianDTO;
import com.bpass.kmastudent.Database.CreateDatabase;

import java.util.ArrayList;

/**
 * Created by buixu on 28/11/2016.
 */

public class LichHocDAO {
    Context context;

    public LichHocDAO(Context context){
        //CreateDatabase createDatabase = new CreateDatabase(context);
        //sqLiteDatabase = createDatabase.open1();
        this.context = context;
    }


//    public class testclass {
//
//        select NGAYBATDAU, NGAYKETTHUC, THU
//        from LOPHOC join LICHHOCVADIEM using (IDLOPHOC)
//        where MASV =....
//
//        public void gettrueDate(String start, String end, String thu){
//            while (select julian(end) - julian(start) > 0){
//                String s1 = select date(start, 'weekday '+thu);
//                if (select julianday(end) - julianday(s1) >= 0 && select julianday(s1) - julianday(start) >=0){
//                    insert into table_TKB values (MASV, s1 , IDLOPHOC , CAHOC);
//                }
//                start = select date(start,'weekday 0','+1 day');
//            }
//        }
//    }


    public ArrayList<KhoangThoiGianDTO> LaydanhsachLichHoc(String masv) {
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        SQLiteDatabase database = createDatabase.open1();

        ArrayList<KhoangThoiGianDTO> laydanhsachLichHoc = new ArrayList<>();
        String cauTruyVan = " SELECT * FROM " + CreateDatabase.TB_KHOANGTHOIGIANHOC + " JOIN " + CreateDatabase.TB_LICHHOCVADIEM
                + " USING ( " + CreateDatabase.TB_LICHHOCVADIEM_IDLOP + " ) WHERE " + CreateDatabase.TB_LICHHOCVADIEM_MASV + " = '"+ masv +"'"
                +  " ORDER BY " + CreateDatabase.TB_KHOANGTHOIGIANHOC_CAHOC ;

        Cursor cursor = database.rawQuery(cauTruyVan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            KhoangThoiGianDTO KhoangThoiGianDTO = new KhoangThoiGianDTO();

            KhoangThoiGianDTO.setNGAYBATDAU(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANGTHOIGIANHOC_NGAYBATDAU)));
            KhoangThoiGianDTO.setNGAYKETTHUC(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANGTHOIGIANHOC_NGAYKETTHUC)));
            KhoangThoiGianDTO.setTHU(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANGTHOIGIANHOC_THU)));
            KhoangThoiGianDTO.setIDLOPHOC(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANGTHOIGIANHOC_IDLOP)));
            KhoangThoiGianDTO.setCAHOC(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_KHOANGTHOIGIANHOC_CAHOC)));

            laydanhsachLichHoc.add(KhoangThoiGianDTO);
            cursor.moveToNext();

        }
        database.close();
        return laydanhsachLichHoc;
    }


}
