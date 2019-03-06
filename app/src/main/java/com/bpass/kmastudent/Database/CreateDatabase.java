package com.bpass.kmastudent.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.bpass.kmastudent.DTO.Note;

import java.util.ArrayList;

/* Hoàng Nguyễn */

public class CreateDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "KMAStudentttttt";
    public static final int DATA_VERSION = 1;

    // Khai báo biến tĩnh tên các Table trong SQL
    public static final String TB_SINHVIEN = "SINHVIEN"; // thông tin sv
    public static final String TB_NHATKY = "NHATKY";                    // nhật ký
    public static final String TB_NAMKY = "NAMKY";                       // chọn năm kỳ
    public static final String TB_LICHHOCVADIEM = "LICHHOCVADIEM";      // lịch học và điểm
    public static final String TB_MONHOC = "MONHOC";                     // môn học
    public static final String TB_LOPHOC = "LOPHOC";                    // lớp học
    public static final String TB_GIANGVIEN = "GIANGVIEN";              // giảng viên
    public static final String TB_NOTE = "TABLE_NOTE";
    public static final String TB_KHOANGTHOIGIANHOC = "KHOANGTHOIGIANHOC";

    // Khai báo các biến thuộc các table tương ứng
    // table SINHVIEN
    public static final String TB_SINHVIEN_MASV = "MASV";
    public static final String TB_SINHVIEN_MATKHAU = "MATKHAU";
    public static final String TB_SINHVIEN_TENSV = "TENSV";      //tên sv
    public static final String TB_SINHVIEN_MAIL = "MAIL";        // mail
    public static final String TB_SINHVIEN_LOGINSTATUS = "LOGINSTATUS";


    // table NHATKY
    public static final String TB_NHATKY_MASV = "MASV";
    public static final String TB_NHATKY_IDNK = "IDNK";
    public static final String TB_NHATKY_NGAYVIETNK = "NGAYVIETNK";   //ngày viết nhật ký
    public static final String TB_NHATKY_TIEUDENK = "TIEUDENK";
    public static final String TB_NHATKY_NOIDUNGNK = "NOIDUNGNK";      // nội dung nhật ký


    // table NAMKY
    public static final String TB_NAMKY_IDNAMKY = "IDNAMKY";
    public static final String TB_NAMKY_NAM = "NAM";                 // năm
    public static final String TB_NAMKY_KY = "KY";                   // kỳ
//    public static final String TB_NAMKY_SOLUONGMON = "SOLUONGMON";  // số lượng môn học

    // table LICHHOCVADIEM
    public static final String TB_LICHHOCVADIEM_MASV = "MASV";
    public static final String TB_LICHHOCVADIEM_IDLOP = "IDLOP";
    public static final String TB_LICHHOCVADIEM_IDNAMKY = "IDNAMKY";
    public static final String TB_LICHHOCVADIEM_IDMONHOC = "IDMONHOC";                  // tên môn
    public static final String TB_LICHHOCVADIEM_DIEMCHUYENCAN = "DIEMCHUYENCAN";       //điểm chuyên cần
    public static final String TB_LICHHOCVADIEM_DIEMGIUAKY = "DIEMGIUAKY";              // điểm giữa kỳ
    public static final String TB_LICHHOCVADIEM_DIEMKTHP = "DIEMKTHP";              // điểm kết thúc học phần
    public static final String TB_LICHHOCVADIEM_DIEMTBMON = "DIEMTBMON";                  // điểm trung bình môn
    public static final String TB_LICHHOCVADIEM_DIEMTONGKET = "DIEMTONGKET";        // điểm tổng kết cả kỳ học

    // table MONHOC
    public static final String TB_MONHOC_IDMONHOC = "IDMONHOC";
    public static final String TB_MONHOC_TENMON = "TENMON";
    public static final String TB_MONHOC_TLTK = "TLTK";  //tài liệu tham khảo
    public static final String TB_MONHOC_DOKHO = "DOKHO";   // độ khó
    public static final String TB_MONHOC_SOTINCHI = "SOTINCHI";         // số tín chỉ
    public static final String TB_MONHOC_GIOITHIEUMON = "GIOITHIEUMON";      // giới thiệu qua về môn học
    public static final String TB_MONHOC_SOLUONGLOP = "SOLUONGLOP";     //số lượng lớp
    public static final String TB_MONHOC_IDNAMKY = "IDNAMKY";

    // table LOPHOC
    public static final String TB_LOPHOC_IDLOP = "IDLOP";
    public static final String TB_LOPHOC_TENLOP = "TENLOP";         // tên lớp
    public static final String TB_LOPHOC_PHONGHOC = "PHONGHOC";             // phòng học
    public static final String TB_LOPHOC_TENGV = "TENGV";       //tên giảng viên
    public static final String TB_LOPHOC_IDMONHOC = "IDMONHOC";
    public static final String TB_LOPHOC_IDGV = "IDGV";

    // table GIANGVIEN
    public static final String TB_GIANGVIEN_IDGV = "IDGV";   // ID Giảng Viên
    public static final String TB_GIANGVIEN_TENGV = "TENGV";   // tên giảng viên
    public static final String TB_GIANGVIEN_KHOA = "KHOA";    // khoa nào
    public static final String TB_GIANGVIEN_MAILGV = "MAILGV";    //mail giảng viên
    public static final String TB_GIANGVIEN_GIOITHIEUGV = "GIOITHIEUGV";  // giới thiệu giảng viên
    public static final String TB_GIANGVIEN_ANHGV = "ANHGV";        //ảnh giảng viên

    //table NOTE
    public static final String TB_NOTE_ID = "TB_NOTE_ID";
    public static final String TB_NOTE_TITLE = "TB_NOTE_TITLE";
    public static final String TB_NOTE_CONTENT = "TB_NOTE_CONTENT";
    public static final String TB_NOTE_LAST_MODIFIED = "TB_NOTE_LAST_MODIFIED";

    //table Khoang thoi gian hoc
    public static final String TB_KHOANGTHOIGIANHOC_IDLOP = "IDLOP";
    public static final String TB_KHOANGTHOIGIANHOC_NGAYBATDAU = "NGAYBATDAU";
    public static final String TB_KHOANGTHOIGIANHOC_NGAYKETTHUC = "NGAYKETTHUC";
    public static final String TB_KHOANGTHOIGIANHOC_CAHOC = "CAHOC";
    public static  final String TB_KHOANGTHOIGIANHOC_THU = "THU";


    private static CreateDatabase mInstance = null;

    public static CreateDatabase getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new CreateDatabase(ctx.getApplicationContext());
        }
        return mInstance;
    }

    // Constructor
    private CreateDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
        context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);
    }

    // tự tăng : AUTOINCREMENT

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tbSINHVIEN = " CREATE TABLE " + TB_SINHVIEN + " ( " + TB_SINHVIEN_MASV + " TEXT NOT NULL PRIMARY KEY , "
                + TB_SINHVIEN_TENSV + " TEXT NOT NULL , " + TB_SINHVIEN_MATKHAU + " TEXT NOT NULL , "
                + TB_SINHVIEN_MAIL + " TEXT NOT NULL  , " + TB_SINHVIEN_LOGINSTATUS + " TEXT )";

        String tbNHATKY = " CREATE TABLE " + TB_NHATKY + " ( " + TB_NHATKY_MASV + " TEXT NOT NULL, "+ TB_NHATKY_IDNK + " TEXT NOT NULL, "
                + TB_NHATKY_NGAYVIETNK + " TEXT NOT NULL, " + TB_NHATKY_TIEUDENK + " TEXT NOT NULL, "
                + TB_NHATKY_NOIDUNGNK + " TEXT, " + " PRIMARY KEY ( " + TB_NHATKY_MASV + " , " + TB_NHATKY_TIEUDENK + " ),"
                + " FOREIGN KEY ( " + TB_NHATKY_MASV + " ) REFERENCES " + TB_SINHVIEN + " ( " + TB_SINHVIEN_MASV + " ))";

        String tbNAMKY = " CREATE TABLE " + TB_NAMKY + " ( " + TB_NAMKY_IDNAMKY + " TEXT NOT NULL PRIMARY KEY, "
                + TB_NAMKY_NAM + " TEXT NOT NULL , " + TB_NAMKY_KY + " TEXT NOT NULL ) ";

//        String tbLICHHOCVADIEM = " CREATE TABLE " + TB_LICHHOCVADIEM + " ( " + TB_LICHHOCVADIEM_MASV + " TEXT NOT NULL , "
//                + TB_LICHHOCVADIEM_IDLOP + " TEXT NOT NULL , " + TB_LICHHOCVADIEM_IDNAMKY + " TEXT NOT NULL , "
//                + TB_LICHHOCVADIEM_IDMONHOC + " TEXT NOT NULL, " + TB_LICHHOCVADIEM_DIEMCHUYENCAN + " REAL, " + TB_LICHHOCVADIEM_DIEMGIUAKY + " REAL, "
//                + TB_LICHHOCVADIEM_DIEMKTHP + " REAL, " + TB_LICHHOCVADIEM_DIEMTBMON + " REAL, " + TB_LICHHOCVADIEM_DIEMTONGKET + " REAL, "
//                + " PRIMARY KEY ( " + TB_LICHHOCVADIEM_MASV + " , " + TB_LICHHOCVADIEM_IDLOP + " ), "
//                + " FOREIGN KEY ( " + TB_LICHHOCVADIEM_MASV + " ) REFERENCES " + TB_SINHVIEN + " ( " + TB_SINHVIEN_MASV + " ),"
//                + " FOREIGN KEY ( " + TB_LICHHOCVADIEM_IDLOP + " ) REFERENCES " + TB_LOPHOC + " ( " + TB_LOPHOC_IDLOP + " ))";

        String tbLICHHOCVADIEM = " CREATE TABLE " + TB_LICHHOCVADIEM + " ( " + TB_LICHHOCVADIEM_MASV + " TEXT NOT NULL , "
                + TB_LICHHOCVADIEM_IDLOP + " TEXT NOT NULL , " + TB_LICHHOCVADIEM_IDMONHOC + " TEXT NOT NULL , "
                + TB_LICHHOCVADIEM_IDNAMKY + " TEXT NOT NULL, " + TB_LICHHOCVADIEM_DIEMCHUYENCAN + " REAL, " + TB_LICHHOCVADIEM_DIEMGIUAKY + " REAL, "
                + TB_LICHHOCVADIEM_DIEMKTHP + " REAL, " + TB_LICHHOCVADIEM_DIEMTBMON + " REAL, " + TB_LICHHOCVADIEM_DIEMTONGKET + " REAL, "
                + " PRIMARY KEY ( " + TB_LICHHOCVADIEM_MASV + " , " + TB_LICHHOCVADIEM_IDLOP + " ), "
                + " FOREIGN KEY ( "+ TB_LICHHOCVADIEM_MASV + " ) REFERENCES " + TB_SINHVIEN + " ( " + TB_SINHVIEN_MASV + " ),"
                + " FOREIGN KEY ( "+ TB_LICHHOCVADIEM_IDLOP + " ) REFERENCES " + TB_LOPHOC + " ( " + TB_LOPHOC_IDLOP + " ))";

        String tbLOPHOC = " CREATE TABLE " + TB_LOPHOC + " ( " + TB_LOPHOC_IDLOP + " TEXT NOT NULL PRIMARY KEY, " + TB_LOPHOC_TENLOP + " TEXT NOT NULL, "
                + TB_LOPHOC_PHONGHOC + " TEXT NOT NULL, " + TB_LOPHOC_TENGV + " TEXT NOT NULL, " + TB_LOPHOC_IDMONHOC + " TEXT NOT NULL, "
                + TB_LOPHOC_IDGV + " TEXT NOT NULL )";

        String tbMONHOC = " CREATE TABLE " + TB_MONHOC + " ( " + TB_MONHOC_IDMONHOC + " TEXT NOT NULL PRIMARY KEY, "
                + TB_MONHOC_TENMON + " TEXT NOT NULL , " + TB_MONHOC_TLTK + " TEXT, " + TB_MONHOC_DOKHO + " REAL, "
                + TB_MONHOC_SOTINCHI + " INTEGER NOT NULL , " + TB_MONHOC_GIOITHIEUMON + " TEXT, " + TB_MONHOC_IDNAMKY + " TEXT NOT NULL , "
                + TB_MONHOC_SOLUONGLOP + " INTEGER NOT NULL ," + " FOREIGN KEY ( " + TB_MONHOC_IDNAMKY + " ) REFERENCES " + TB_NAMKY + " ( "
                + TB_NAMKY_IDNAMKY + " )) ";



        String tbGIANGVIEN = "CREATE TABLE " + TB_GIANGVIEN + " ( " + TB_GIANGVIEN_IDGV + " TEXT NOT NULL PRIMARY KEY, "
                + TB_GIANGVIEN_TENGV + " TEXT NOT NULL, " + TB_GIANGVIEN_KHOA + " TEXT, " + TB_GIANGVIEN_MAILGV + " TEXT, "
                + TB_GIANGVIEN_GIOITHIEUGV + " TEXT, " + TB_GIANGVIEN_ANHGV + " BLOB )";

        String tbNOTE = " CREATE TABLE " + TB_NOTE + "(" + TB_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + TB_NOTE_TITLE + " TEXT NOT NULL, " + TB_NOTE_CONTENT + " TEXT NOT NULL, "
                + TB_NOTE_LAST_MODIFIED + " TEXT DEFAULT \'\')";

        //table KHOANGTHOIGIANHOC
        String tbKHOANGTHOIGIANHOC = " CREATE TABLE " + TB_KHOANGTHOIGIANHOC + " ( " + TB_KHOANGTHOIGIANHOC_IDLOP + " TEXT NOT NULL, "
                + TB_KHOANGTHOIGIANHOC_NGAYBATDAU + " TEXT NOT NULL, " + TB_KHOANGTHOIGIANHOC_NGAYKETTHUC + " TEXT NOT NULL, "
                + TB_KHOANGTHOIGIANHOC_CAHOC + " INTEGER NOT NULL, " + TB_KHOANGTHOIGIANHOC_THU + " TEXT NOT NULL )"

                ;

        sqLiteDatabase.execSQL(tbSINHVIEN);
        sqLiteDatabase.execSQL(tbNAMKY);
        sqLiteDatabase.execSQL(tbMONHOC);
        sqLiteDatabase.execSQL(tbGIANGVIEN);
        sqLiteDatabase.execSQL(tbLOPHOC);
        sqLiteDatabase.execSQL(tbLICHHOCVADIEM);
        sqLiteDatabase.execSQL(tbNHATKY);
        sqLiteDatabase.execSQL(tbNOTE);
        sqLiteDatabase.execSQL(tbKHOANGTHOIGIANHOC);

        /************** BẮT ĐẦU insert dữ liệu cho bảng Năm - Kỳ **************/
        String insertNam11= " INSERT INTO " + TB_NAMKY + " VALUES " + " ('NAM11','Năm 1','Kỳ 1')" ;
        String insertNam12= " INSERT INTO " + TB_NAMKY + " VALUES " + " ('NAM12','Năm 1','Kỳ 2')" ;
        String insertNam21= " INSERT INTO " + TB_NAMKY + " VALUES " + " ('NAM21','Năm 2','Kỳ 1')" ;
        String insertNam22= " INSERT INTO " + TB_NAMKY + " VALUES " + " ('NAM22','Năm 2','Kỳ 2')" ;
        String insertNam31= " INSERT INTO " + TB_NAMKY + " VALUES " + " ('NAM31','Năm 3','Kỳ 1')" ;
        String insertNam32= " INSERT INTO " + TB_NAMKY + " VALUES " + " ('NAM32','Năm 3','Kỳ 2')" ;
        String insertNam41= " INSERT INTO " + TB_NAMKY + " VALUES " + " ('NAM41','Năm 4','Kỳ 1')" ;
        String insertNam42= " INSERT INTO " + TB_NAMKY + " VALUES " + " ('NAM42','Năm 4','Kỳ 2')" ;
        String insertNam51= " INSERT INTO " + TB_NAMKY + " VALUES " + " ('NAM51','Năm 5','Kỳ 1')" ;
        String insertNam52= " INSERT INTO " + TB_NAMKY + " VALUES " + " ('NAM52','Năm 5','Kỳ 2')" ;

        sqLiteDatabase.execSQL(insertNam11);
        sqLiteDatabase.execSQL(insertNam12);
        sqLiteDatabase.execSQL(insertNam21);
        sqLiteDatabase.execSQL(insertNam22);
        sqLiteDatabase.execSQL(insertNam31);
        sqLiteDatabase.execSQL(insertNam32);
        sqLiteDatabase.execSQL(insertNam41);
        sqLiteDatabase.execSQL(insertNam42);
        sqLiteDatabase.execSQL(insertNam51);
        sqLiteDatabase.execSQL(insertNam52);

        /************** KẾT THÚC insert dữ liệu cho bảng Năm - Kỳ **************/

        /*******************************************KHOA 10**********************************/

        //Insert dữ liệu cho bảng Môn học (KHÓA 10)
        String insertMonATMMT = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('ATMMT' , 'An toàn mạng máy tính' , null , 5 , 4 , 'Đang cập nhật...' , 'NAM42' , 4)";
        String insertMonDGKDHT = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('DGKDHT' , 'Đánh giá & kiểm định AT hệ TTT' , null , 5 , 3 , 'Đang cập nhật...' , 'NAM42' , 4)";
        String insertMonGTATM = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('GTATM' , 'Giao thức an toàn mạng' , null , 4 , 2 , 'Đang cập nhật...' , 'NAM42' , 4)";
        String insertMonKTGT = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('KTGT' , 'Kỹ thuật giấu tin' , null , 4 , 2 , 'Đang cập nhật...' , 'NAM42' , 4)";
        String insertMonPTTKM = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('PTTKM' , 'Phân tích, thiết kế an toàn mạng máy tính' , null , 5 , 2 , 'Đang cập nhật...' , 'NAM42' , 4)";
        String insertMonTTANM = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('TTANM' , 'Thu thập và phân tích TT AN mạng' , null , 5 , 3 , 'Đang cập nhật...' , 'NAM42' , 4)";

        sqLiteDatabase.execSQL(insertMonATMMT);
        sqLiteDatabase.execSQL(insertMonDGKDHT);
        sqLiteDatabase.execSQL(insertMonGTATM);
        sqLiteDatabase.execSQL(insertMonKTGT);
        sqLiteDatabase.execSQL(insertMonPTTKM);
        sqLiteDatabase.execSQL(insertMonTTANM);


        //insert dữ liệu vào table Lớp học (KHÓA 10)
        String insertLopATMMT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'ATMMT1' , 'L01' , '102_TA2 TA2' , 'Hoàng Thanh Nam' , 'ATMMT' , 'HTN')";
        String insertLopATMMT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'ATMMT2' , 'L02' , '102_TA2 TA2' , 'Cao Minh Tuấn' , 'ATMMT' , 'CMT')";
        String insertLopATMMT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'ATMMT3' , 'L03' , '204-TA2 TA2' , 'Hoàng Thanh Nam' , 'ATMMT' , 'HTN')";
        String insertLopATMMT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'ATMMT4' , 'L04' , '303_TA2 TA2' , 'Cao Minh Tuấn' , 'ATMMT' , 'CMT')";

        String insertLopDGKDHT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DGKDHT1' , 'L01' , '102_TA2 TA2' , 'Phạm Minh Thuấn' , 'DGKDHT' , 'PMT')";
        String insertLopDGKDHT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DGKDHT2' , 'L02' , '102_TA2 TA2' , 'Trần Anh Tú' , 'DGKDHT' , 'TAT')";
        String insertLopDGKDHT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DGKDHT3' , 'L03' , '204-TA2 TA2' , 'Trần Anh Tú' , 'DGKDHT' , 'TAT')";
        String insertLopDGKDHT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DGKDHT4' , 'L04' , '303_TA2 TA2' , 'Phạm Minh Thuấn' , 'DGKDHT' , 'PMT')";

        String insertLopGTATM1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'GTATM1' , 'L01' , '102_TA2 TA2' , 'Trần Thị Lượng' , 'GTATM' , 'TTL')";
        String insertLopGTATM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'GTATM2' , 'L02' , '102_TA2 TA2' , 'Trần Thị Lượng' , 'GTATM' , 'TTL')";
        String insertLopGTATM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'GTATM3' , 'L03' , '204-TA2 TA2' , 'Trần Thị Lượng' , 'GTATM' , 'TTL')";
        String insertLopGTATM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'GTATM4' , 'L04' , '303_TA2 TA2' , 'Nguyễn Tuấn Anh' , 'GTATM' , 'NTA')";

        String insertLopKTGT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTGT1' , 'L01' , '102_TA2 TA2' , 'Hoàng Thu Phương' , 'KTGT' , 'HTP')";
        String insertLopKTGT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTGT2' , 'L02' , '102_TA2 TA2' , 'Trần Thị Xuyên' , 'KTGT' , 'TTX')";
        String insertLopKTGT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTGT3' , 'L03' , '204-TA2 TA2' , 'Hoàng Thu Phương' , 'KTGT' , 'HTP')";
        String insertLopKTGT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTGT4' , 'L04' , '303_TA2 TA2' , 'Trần Thị Xuyên' , 'KTGT' , 'TTX')";

        String insertLopPTTKM1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'PTTKM1' , 'L01' , '102_TA2 TA2' , 'Nguyễn Mạnh Thắng' , 'PTTKM' , 'NMT')";
        String insertLopPTTKM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'PTTKM2' , 'L02' , '102_TA2 TA2' , 'Nguyễn Mạnh Thắng' , 'PTTKM' , 'NMT')";
        String insertLopPTTKM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'PTTKM3' , 'L03' , '204-TA2 TA2' , 'Nguyễn Mạnh Thắng' , 'PTTKM' , 'NMT')";
        String insertLopPTTKM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'PTTKM4' , 'L04' , '303_TA2 TA2' , 'Nguyễn Mạnh Thắng' , 'PTTKM' , 'NMT')";

        String insertLopTTANM1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TTANM1' , 'L01' , '102_TA2 TA2' , 'Hoàng Thanh Nam' , 'TTANM' , 'HTN')";
        String insertLopTTANM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TTANM2' , 'L02' , '102_TA2 TA2' , 'Hoàng Thanh Nam' , 'TTANM' , 'HTN')";
        String insertLopTTANM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TTANM3' , 'L03' , '204-TA2 TA2' , 'Hoàng Thanh Nam' , 'TTANM' , 'HTN')";
        String insertLopTTANM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TTANM4' , 'L04' , '303_TA2 TA2' , 'Hoàng Thanh Nam' , 'TTANM' , 'HTN')";


        sqLiteDatabase.execSQL(insertLopATMMT1);    //
        sqLiteDatabase.execSQL(insertLopATMMT2);
        sqLiteDatabase.execSQL(insertLopATMMT3);
        sqLiteDatabase.execSQL(insertLopATMMT4);
        sqLiteDatabase.execSQL(insertLopDGKDHT1);   //
        sqLiteDatabase.execSQL(insertLopDGKDHT2);
        sqLiteDatabase.execSQL(insertLopDGKDHT3);
        sqLiteDatabase.execSQL(insertLopDGKDHT4);
        sqLiteDatabase.execSQL(insertLopGTATM1);    //
        sqLiteDatabase.execSQL(insertLopGTATM2);
        sqLiteDatabase.execSQL(insertLopGTATM3);
        sqLiteDatabase.execSQL(insertLopGTATM4);
        sqLiteDatabase.execSQL(insertLopKTGT1);     //
        sqLiteDatabase.execSQL(insertLopKTGT2);
        sqLiteDatabase.execSQL(insertLopKTGT3);
        sqLiteDatabase.execSQL(insertLopKTGT4);
        sqLiteDatabase.execSQL(insertLopPTTKM1);    //
        sqLiteDatabase.execSQL(insertLopPTTKM2);
        sqLiteDatabase.execSQL(insertLopPTTKM3);
        sqLiteDatabase.execSQL(insertLopPTTKM4);
        sqLiteDatabase.execSQL(insertLopTTANM1);    //
        sqLiteDatabase.execSQL(insertLopTTANM2);
        sqLiteDatabase.execSQL(insertLopTTANM3);
        sqLiteDatabase.execSQL(insertLopTTANM4);


        //Insert KHOANGTHOIGIANHOC cho KHOA 10

        //ATMMT
        String insertKhoangThoiGianHocATMMT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT1' , '2017-1-2' , '2017-1-8' , 1 , '0-3-5' )";
        String insertKhoangThoiGianHocATMMT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT1' , '2017-1-2' , '2017-1-8' , 2 , '0-0-4' )";
        String insertKhoangThoiGianHocATMMT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT1' , '2017-1-9' , '2017-1-15' , 1 , '1-3-5' )";
        String insertKhoangThoiGianHocATMMT1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT1' , '2017-1-16' , '2017-1-22' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocATMMT1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT1' , '2017-1-16' , '2017-1-22' , 2 , '0-0-4' )";
        String insertKhoangThoiGianHocATMMT1_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT1' , '2017-2-6' , '2017-3-12' , 1 , '1-3-5' )";
        String insertKhoangThoiGianHocATMMT1_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT1' , '2017-3-13' , '2017-4-2' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocATMMT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT2' , '2017-1-2' , '2017-1-8' , 3 , '0-3-5' )";
        String insertKhoangThoiGianHocATMMT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT2' , '2017-1-2' , '2017-1-8' , 4 , '0-0-2' )";
        String insertKhoangThoiGianHocATMMT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT2' , '2017-1-9' , '2017-1-15' , 3 , '1-3-5' )";
        String insertKhoangThoiGianHocATMMT2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT2' , '2017-1-16' , '2017-1-22' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocATMMT2_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT2' , '2017-1-16' , '2017-1-22' , 4 , '0-0-4' )";
        String insertKhoangThoiGianHocATMMT2_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT2' , '2017-2-6' , '2017-3-12' , 3 , '1-3-5' )";
        String insertKhoangThoiGianHocATMMT2_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT2' , '2017-3-13' , '2017-4-2' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocATMMT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT3' , '2017-1-2' , '2017-1-8' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocATMMT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT3' , '2017-1-9' , '2017-1-22' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocATMMT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT3' , '2017-2-6' , '2017-3-5' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocATMMT3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT3' , '2017-3-6' , '2017-4-2' , 5 , '1-3-4' )";
        String insertKhoangThoiGianHocATMMT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT4' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocATMMT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT4' , '2017-2-6' , '2017-3-5' , 5 , '0-2-4' )";
        String insertKhoangThoiGianHocATMMT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'ATMMT4' , '2017-3-6' , '2017-4-2' , 5 , '2-4-5' )";


        //DGKDHT
        String insertKhoangThoiGianHocDGKDHT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT1' , '2017-1-2' , '2017-1-22' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocDGKDHT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT1' , '2017-2-6' , '2017-3-19' , 2 , '0-3-5' )";
        String insertKhoangThoiGianHocDGKDHT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT1' , '2017-3-20' , '2017-4-2' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocDGKDHT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT2' , '2017-1-2' , '2017-1-22' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocDGKDHT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT2' , '2017-2-6' , '2017-3-19' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocDGKDHT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT2' , '2017-3-20' , '2017-4-2' , 3 , '0-0-2' )";
        String insertKhoangThoiGianHocDGKDHT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT3' , '2017-1-2' , '2017-1-22' , 5 , '0-2-4' )";
        String insertKhoangThoiGianHocDGKDHT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT3' , '2017-2-6' , '2017-2-12' , 5 , '0-2-4' )";
        String insertKhoangThoiGianHocDGKDHT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT3' , '2017-2-13' , '2017-4-2' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocDGKDHT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT4' , '2017-1-2' , '2017-1-8' , 5 , '0-3-5' )";
        String insertKhoangThoiGianHocDGKDHT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT4' , '2017-1-9' , '2017-1-22' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocDGKDHT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT4' , '2017-2-6' , '2017-3-26' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocDGKDHT4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DGKDHT4' , '2017-3-27' , '2017-4-2' , 3 , '0-1-3' )";


        //GTATM1
        String insertKhoangThoiGianHocGTATM1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'GTATM1' , '2017-4-17' , '2017-4-30' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocGTATM1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'GTATM1' , '2017-5-1' , '2017-5-7' , 1 , '0-3-4' )";
        String insertKhoangThoiGianHocGTATM1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'GTATM1' , '2017-5-8' , '2017-6-18' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocGTATM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'GTATM2' , '2017-4-17' , '2017-4-30' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocGTATM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'GTATM2' , '2017-5-1' , '2017-5-7' , 3 , '0-3-4' )";
        String insertKhoangThoiGianHocGTATM2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'GTATM2' , '2017-5-8' , '2017-6-18' , 3 , '0-0-1' )";
        String insertKhoangThoiGianHocGTATM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'GTATM3' , '2017-4-17' , '2017-4-30' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocGTATM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'GTATM3' , '2017-5-1' , '2017-5-7' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocGTATM3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'GTATM3' , '2017-5-8' , '2017-6-18' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocGTATM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'GTATM4' , '2017-4-17' , '2017-6-18' , 5 , '0-0-3' )";


        //KTGT1
        String insertKhoangThoiGianHocKTGT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT1' , '2017-4-17' , '2017-4-30' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocKTGT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT1' , '2017-5-1' , '2017-5-7' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocKTGT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT1' , '2017-5-8' , '2017-5-28' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocKTGT1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT1' , '2017-5-29' , '2017-6-18' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocKTGT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT1' , '2017-4-17' , '2017-4-30' , 3 , '0-0-2' )";
        String insertKhoangThoiGianHocKTGT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT2' , '2017-5-1' , '2017-5-7' , 4 , '0-0-3' )";
        String insertKhoangThoiGianHocKTGT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT2' , '2017-5-8' , '2017-5-28' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocKTGT2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT2' , '2017-5-29' , '2017-6-18' , 3 , '0-0-2' )";
        String insertKhoangThoiGianHocKTGT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT3' , '2017-4-17' , '2017-4-30' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocKTGT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT3' , '2017-5-1' , '2017-5-7' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocKTGT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT3' , '2017-5-8' , '2017-6-18' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocKTGT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTGT4' , '2017-4-17' , '2017-6-18' , 5 , '0-0-5' )";


        //PTTKM1
        String insertKhoangThoiGianHocPTTKM1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTTKM1' , '2017-1-2' , '2017-1-22' , 2 , '0-0-2' )";
        String insertKhoangThoiGianHocPTTKM1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTTKM1' , '2017-2-6' , '2017-2-12' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocPTTKM1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTTKM1' , '2017-2-13' , '2017-4-2' , 2 , '0-0-2' )";
        String insertKhoangThoiGianHocPTTKM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTTKM2' , '2017-1-2' , '2017-1-8' , 4 , '0-0-3' )";
        String insertKhoangThoiGianHocPTTKM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTTKM2' , '2017-1-9' , '2017-1-22' , 4 , '0-0-1' )";
        String insertKhoangThoiGianHocPTTKM2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTTKM2' , '2017-2-6' , '2017-2-12' , 4 , '0-1-3' )";
        String insertKhoangThoiGianHocPTTKM2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTTKM2' , '2017-2-13' , '2017-4-2' , 4 , '0-0-1' )";
        String insertKhoangThoiGianHocPTTKM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTTKM3' , '2017-1-2' , '2017-1-22' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocPTTKM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTTKM3' , '2017-2-6' , '2017-3-19' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocPTTKM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTTKM4' , '2017-1-2' , '2017-1-22' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocPTTKM4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTTKM4' , '2017-2-6' , '2017-3-19' , 5 , '0-0-3' )";


        //TTANM1
        String insertKhoangThoiGianHocTTANM1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM1' , '2017-4-17' , '2017-4-30' , 1 , '0-4-5' )";
        String insertKhoangThoiGianHocTTANM1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM1' , '2017-4-17' , '2017-4-30' , 2 , '0-0-2' )";
        String insertKhoangThoiGianHocTTANM1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM1' , '2017-5-1' , '2017-6-18' , 2 , '0-4-5' )";
        String insertKhoangThoiGianHocTTANM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM2' , '2017-4-17' , '2017-4-30' , 3 , '0-4-5' )";
        String insertKhoangThoiGianHocTTANM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM2' , '2017-4-17' , '2017-4-30' , 4 , '0-0-2' )";
        String insertKhoangThoiGianHocTTANM2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM2' , '2017-5-1' , '2017-6-18' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocTTANM2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM2' , '2017-5-1' , '2017-6-18' , 4 , '0-0-4' )";
        String insertKhoangThoiGianHocTTANM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM3' , '2017-4-17' , '2017-4-30' , 5 , '0-3-5' )";
        String insertKhoangThoiGianHocTTANM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM3' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTTANM3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM3' , '2017-5-8' , '2017-5-14' , 5 , '0-3-5' )";
        String insertKhoangThoiGianHocTTANM3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM3' , '2017-5-15' , '2017-5-21' , 5 , '3-4-5' )";
        String insertKhoangThoiGianHocTTANM3_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM3' , '2017-5-22' , '2017-5-28' , 5 , '0-3-4' )";
        String insertKhoangThoiGianHocTTANM3_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM3' , '2017-5-29' , '2017-6-18' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTTANM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM4' , '2017-4-17' , '2017-4-30' , 5 , '0-1-2' )";
        String insertKhoangThoiGianHocTTANM4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM4' , '2017-5-1' , '2017-5-7' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocTTANM4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM4' , '2017-5-8' , '2017-6-4' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocTTANM4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TTANM4' , '2017-6-5' , '2017-6-18' , 5 , '1-2-4' )";


        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_7);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_7);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT4_3);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT4_4);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM4_1);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT4_1);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM4_2);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM4_4);


        /*******************************************END KHOA 10**********************************/


        /*******************************************KHOA 11**********************************/

        //Insert dữ liệu cho bảng Môn học (KHÓA 11)
        String insertMonHTVT = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('HTVT' , 'Hệ thống viễn thông' , null , 5 , 3 , 'Đang cập nhật...' , 'NAM32' , 4)";
        String insertMonKTMT = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('KTMT' , 'Kiến trúc máy tính' , null , 3 , 2 , 'Đang cập nhật...' , 'NAM32' , 4)";
        String insertMonKTDL = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('KTDL' , 'Kỹ thuật đo lường điện tử' , null , 5 , 2 , 'Đang cập nhật...' , 'NAM32' , 4)";
        String insertMonKTTSL = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('KTTSL' , 'Kỹ thuật truyền số liệu' , null , 5 , 2 , 'Đang cập nhật...' , 'NAM32' , 4)";
        String insertMonLTM = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('LTM' , 'Lập trình mạng' , null , 4 , 2 , 'Đang cập nhật...' , 'NAM32' , 4)";
        String insertMonPTW = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('PTW' , 'Phát triển ứng dụng web' , null , 4 , 2 , 'Đang cập nhật...' , 'NAM32' , 4)";
        String insertMonTKHT = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('TKHT' , 'Phân tích, thiết kế hệ thống' , null , 4 , 2 , 'Đang cập nhật...' , 'NAM32' , 4)";
        String insertMonTACN = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('TACN' , 'Tiếng Anh chuyên ngành' , null , 3 , 3 , 'Đang cập nhật...' , 'NAM32' , 8)";
        String insertMonTHS = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('THS' , 'Xử lý tín hiệu số' , null , 4 , 2 , 'Đang cập nhật...' , 'NAM32' , 4)";

        sqLiteDatabase.execSQL(insertMonHTVT);
        sqLiteDatabase.execSQL(insertMonKTMT);
        sqLiteDatabase.execSQL(insertMonKTDL);
        sqLiteDatabase.execSQL(insertMonKTTSL);
        sqLiteDatabase.execSQL(insertMonLTM);
        sqLiteDatabase.execSQL(insertMonTKHT);
        sqLiteDatabase.execSQL(insertMonTACN);
        sqLiteDatabase.execSQL(insertMonPTW);
        sqLiteDatabase.execSQL(insertMonTHS);


        //insert dữ liệu vào table Lớp học (KHÓA 11)
        String insertLopHTVT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'HTVT1' , 'L01' , '103_AT1 TA1' , 'No Name' , 'HTVT' , 'none')";
        String insertLopHTVT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'HTVT2' , 'L02' , '103_AT1 TA1' , 'No Name' , 'HTVT' , 'none')";
        String insertLopHTVT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'HTVT3' , 'L03' , '103_AT1 TA1' , 'No Name' , 'HTVT' , 'none')";
        String insertLopHTVT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'HTVT4' , 'L04' , '304_TA2 TA2' , 'No Name' , 'HTVT' , 'none')";
        String insertLopKTMT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTMT1' , 'L01' , '103_AT1 TA1' , 'Nguyễn Đào Trường' , 'KTMT' , 'NDT')";
        String insertLopKTMT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTMT2' , 'L02' , '103_AT1 TA1' , 'Nguyễn Đào Trường' , 'KTMT' , 'NDT')";
        String insertLopKTMT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTMT3' , 'L03' , '103_AT1 TA1' , 'Lê Đức Thuận' , 'KTMT' , 'LDT')";
        String insertLopKTMT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTMT4' , 'L04' , '304_TA2 TA2' , 'Lê Đức Thuận' , 'KTMT' , 'LDT')";
        String insertLopKTDL1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTDL1' , 'L01' , '103_AT1 TA1' , 'Tô Thị Tuyết Nhung' , 'KTDL' , 'TTTN')";
        String insertLopKTDL2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTDL2' , 'L02' , '103_AT1 TA1' , 'Tô Thị Tuyết Nhung' , 'KTDL' , 'TTTN')";
        String insertLopKTDL3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTDL3' , 'L03' , '103_AT1 TA1' , 'Tô Thị Tuyết Nhung' , 'KTDL' , 'TTTN')";
        String insertLopKTDL4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTDL4' , 'L04' , '304_TA2 TA2' , 'Tô Thị Tuyết Nhung' , 'KTDL' , 'TTTN')";
        String insertLopKTTSL1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTTSL1' , 'L01' , '103_AT1 TA1' , 'Dương Tuấn Đạt' , 'KTTSL' , 'DTD')";
        String insertLopKTTSL2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTTSL2' , 'L02' , '103_AT1 TA1' , 'Dương Tuấn Đạt' , 'KTTSL' , 'DTD')";
        String insertLopKTTSL3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTTSL3' , 'L03' , '103_AT1 TA1' , 'Dương Tuấn Đạt' , 'KTTSL' , 'DTD')";
        String insertLopKTTSL4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTTSL4' , 'L04' , '304_TA2 TA2' , 'Dương Tuấn Đạt' , 'KTTSL' , 'DTD')";
        String insertLopLTM1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTM1' , 'L01' , '103_AT1 TA1' , 'Lê Bá Cường' , 'LTM' , 'LBC')";
        String insertLopLTM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTM2' , 'L02' , '103_AT1 TA1' , 'Lê Bá Cường' , 'LTM' , 'LBC')";
        String insertLopLTM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTM3' , 'L03' , '103_AT1 TA1' , 'Lê Bá Cường' , 'LTM' , 'LBC')";
        String insertLopLTM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTM4' , 'L04' , '304_TA2 TA2' , 'Lê Bá Cường' , 'LTM' , 'LBC')";
        String insertLopPTW1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'PTW1' , 'L01' , '103_AT1 TA1' , 'Phạm Văn Hưởng' , 'PTW' , 'PVH')";
        String insertLopPTW2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'PTW2' , 'L02' , '103_AT1 TA1' , 'Lê Đức Thuận' , 'PTW' , 'LDT')";
        String insertLopPTW3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'PTW3' , 'L03' , '103_AT1 TA1' , 'Phạm Văn Hưởng' , 'PTW' , 'PVH')";
        String insertLopPTW4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'PTW4' , 'L04' , '304_TA2 TA2' , 'Phạm Văn Hưởng' , 'PTW' , 'PVH')";
        String insertLopTKHT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TKHT1' , 'L01' , '103_AT1 TA1' , 'Vũ Thị Đào' , 'TKHT' , 'VTD')";
        String insertLopTKHT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TKHT2' , 'L02' , '103_AT1 TA1' , 'Vũ Thị Đào' , 'TKHT' , 'VTD')";
        String insertLopTKHT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TKHT3' , 'L03' , '103_AT1 TA1' , 'Vũ Thị Đào' , 'TKHT' , 'VTD')";
        String insertLopTKHT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TKHT4' , 'L04' , '304_TA2 TA2' , 'Vũ Thị Đào' , 'TKHT' , 'VTD')";
        String insertLopTACN1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TACN1' , 'L01' , '103_AT1 TA1' , 'Nguyễn Thị Tý' , 'TACN' , 'NTT')";
        String insertLopTACN2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TACN2' , 'L02' , '103_AT1 TA1' , 'Mai Thị Hảo' , 'TACN' , 'MTH')";
        String insertLopTACN3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TACN3' , 'L03' , '103_AT1 TA1' , 'Phan Bích Thuận' , 'TACN' , 'PBT')";
        String insertLopTACN4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TACN4' , 'L04' , '304_TA2 TA2' , 'Nguyễn Thị Minh Thu' , 'TACN' , 'NTMT')";
        String insertLopTACN5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TACN5' , 'L05' , '201_TA2 TA2' , 'Phan Bích Thuận' , 'TACN' , 'PBT')";
        String insertLopTACN6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TACN6' , 'L06' , '104_TA1 TA1' , 'No Name' , 'TACN' , 'none')";
        String insertLopTACN7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TACN7' , 'L07' , '104_TA1 TA1' , 'Nguyễn Thị Tý' , 'TACN' , 'NTT')";
        String insertLopTACN8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TACN8' , 'L08' , 'unknow location' , 'Mai Thị Hảo' , 'TACN' , 'MTH')";
        String insertLopTHS1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'THS1' , 'L01' , '103_AT1 TA1' , 'Chu Thị Ngọc Quỳnh' , 'THS' , 'CTNQ')";
        String insertLopTHS2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'THS2' , 'L02' , '103_AT1 TA1' , 'Trần Văn Cường' , 'THS' , 'TVC')";
        String insertLopTHS3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'THS3' , 'L03' , '103_AT1 TA1' , 'Chu Thị Ngọc Quỳnh' , 'THS' , 'CTNQ')";
        String insertLopTHS4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'THS4' , 'L04' , '304_TA2 TA2' , 'Trần Văn Cường' , 'THS' , 'TVC')";

        sqLiteDatabase.execSQL(insertLopHTVT1); //
        sqLiteDatabase.execSQL(insertLopHTVT2);
        sqLiteDatabase.execSQL(insertLopHTVT3);
        sqLiteDatabase.execSQL(insertLopHTVT4);
        sqLiteDatabase.execSQL(insertLopKTMT1); //
        sqLiteDatabase.execSQL(insertLopKTMT2);
        sqLiteDatabase.execSQL(insertLopKTMT3);
        sqLiteDatabase.execSQL(insertLopKTMT4);
        sqLiteDatabase.execSQL(insertLopKTDL1); //
        sqLiteDatabase.execSQL(insertLopKTDL2);
        sqLiteDatabase.execSQL(insertLopKTDL3);
        sqLiteDatabase.execSQL(insertLopKTDL4);
        sqLiteDatabase.execSQL(insertLopKTTSL1);//
        sqLiteDatabase.execSQL(insertLopKTTSL2);
        sqLiteDatabase.execSQL(insertLopKTTSL3);
        sqLiteDatabase.execSQL(insertLopKTTSL4);
        sqLiteDatabase.execSQL(insertLopLTM1);  //
        sqLiteDatabase.execSQL(insertLopLTM2);
        sqLiteDatabase.execSQL(insertLopLTM3);
        sqLiteDatabase.execSQL(insertLopLTM4);
        sqLiteDatabase.execSQL(insertLopTKHT1); //
        sqLiteDatabase.execSQL(insertLopTKHT2);
        sqLiteDatabase.execSQL(insertLopTKHT3);
        sqLiteDatabase.execSQL(insertLopTKHT4);
        sqLiteDatabase.execSQL(insertLopTACN1); //
        sqLiteDatabase.execSQL(insertLopTACN2);
        sqLiteDatabase.execSQL(insertLopTACN3);
        sqLiteDatabase.execSQL(insertLopTACN4);
        sqLiteDatabase.execSQL(insertLopTACN5);
        sqLiteDatabase.execSQL(insertLopTACN6);
        sqLiteDatabase.execSQL(insertLopTACN7);
        sqLiteDatabase.execSQL(insertLopTACN8);
        sqLiteDatabase.execSQL(insertLopPTW1); //
        sqLiteDatabase.execSQL(insertLopPTW2);
        sqLiteDatabase.execSQL(insertLopPTW3);
        sqLiteDatabase.execSQL(insertLopPTW4);
        sqLiteDatabase.execSQL(insertLopTHS1);  //
        sqLiteDatabase.execSQL(insertLopTHS2);
        sqLiteDatabase.execSQL(insertLopTHS3);
        sqLiteDatabase.execSQL(insertLopTHS4);


        //insert dữ liệu vào table KHOANGTHOIGIANHOC cho khóa 11
        //HTVT
        String insertKhoangThoiGianHocHTVT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT1' , '2017-4-17' , '2017-4-30' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocHTVT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT1' , '2017-5-1' , '2017-5-7' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocHTVT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT1' , '2017-5-8' , '2017-6-18' , 1 , '0-1-3' )";

        String insertKhoangThoiGianHocHTVT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT2' , '2017-4-17' , '2017-4-30' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocHTVT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT2' , '2017-5-1' , '2017-5-7' , 3 , '0-0-3' )";
        String insertKhoangThoiGianHocHTVT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT2' , '2017-5-8' , '2017-6-18' , 3 , '0-1-3' )";

        String insertKhoangThoiGianHocHTVT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT3' , '2017-4-17' , '2017-4-30' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocHTVT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT3' , '2017-5-8' , '2017-5-28' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocHTVT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT3' , '2017-5-29' , '2017-6-4' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocHTVT3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT3' , '2017-6-5' , '2017-6-11' , 6 , '0-0-1' )";

        String insertKhoangThoiGianHocHTVT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT4' , '2017-4-17' , '2017-4-30' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocHTVT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT4' , '2017-5-1' , '2017-5-7' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocHTVT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT4' , '2017-5-8' , '2017-6-4' , 5 , '0-2-4' )";
        String insertKhoangThoiGianHocHTVT4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT4' , '2017-6-5' , '2017-6-11' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocHTVT4_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'HTVT4' , '2017-6-12' , '2017-6-18' , 5 , '0-0-1' )";


        //KTMT
        String insertKhoangThoiGianHocKTMT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT1' , '2017-4-17' , '2017-4-30' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocKTMT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT1' , '2017-5-1' , '2017-5-7' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocKTMT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT1' , '2017-5-8' , '2017-6-4' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocKTMT1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT1' , '2017-6-5' , '2017-6-18' , 1 , '0-0-2' )";

        String insertKhoangThoiGianHocKTMT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT2' , '2017-4-17' , '2017-4-30' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocKTMT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT2' , '2017-5-1' , '2017-5-7' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocKTMT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT2' , '2017-5-8' , '2017-6-4' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocKTMT2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT2' , '2017-6-5' , '2017-6-18' , 3 , '0-0-2' )";

        String insertKhoangThoiGianHocKTMT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT3' , '2017-4-17' , '2017-4-30' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocKTMT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT3' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocKTMT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT3' , '2017-5-8' , '2017-6-4' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocKTMT3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT3' , '2017-6-5' , '2017-6-18' , 5 , '0-2-4' )";

        String insertKhoangThoiGianHocKTMT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT4' , '2017-4-17' , '2017-4-23' , 5 , '0-1-4' )";
        String insertKhoangThoiGianHocKTMT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT4' , '2017-4-24' , '2017-4-30' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocKTMT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT4' , '2017-5-8' , '2017-6-4' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocKTMT4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTMT4' , '2017-6-5' , '2017-6-18' , 5 , '0-3-5' )";


        //KTDL
        String insertKhoangThoiGianHocKTDL1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL1' , '2017-2-6' , '2017-2-12' , 1 , '1-3-5' )";
        String insertKhoangThoiGianHocKTDL1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL1' , '2017-2-13' , '2017-2-19' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocKTDL1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL1' , '2017-2-20' , '2017-2-26' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocKTDL1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL1' , '2017-2-27' , '2017-4-2' , 1 , '0-0-1' )";

        String insertKhoangThoiGianHocKTDL2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL2' , '2017-2-6' , '2017-2-19' , 3 , '0-1-5' )";
        String insertKhoangThoiGianHocKTDL2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL2' , '2017-2-20' , '2017-2-26' , 3 , '0-1-5' )";
        String insertKhoangThoiGianHocKTDL2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL2' , '2017-2-20' , '2017-2-26' , 4 , '0-0-3' )";
        String insertKhoangThoiGianHocKTDL2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL2' , '2017-2-27' , '2017-4-2' , 3 , '0-0-1' )";

        String insertKhoangThoiGianHocKTDL3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL3' , '2017-1-9' , '2017-1-22' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocKTDL3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL3' , '2017-2-6' , '2017-3-12' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocKTDL3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL3' , '2017-3-13' , '2017-3-19' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocKTDL3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL3' , '2017-3-20' , '2017-3-26' , 6 , '0-0-2' )";

        String insertKhoangThoiGianHocKTDL4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL4' , '2017-2-6' , '2017-3-12' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocKTDL4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL4' , '2017-3-13' , '2017-3-19' , 5 , '0-2-3' )";
        String insertKhoangThoiGianHocKTDL4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTDL4' , '2017-3-20' , '2017-4-2' , 5 , '0-0-4' )";


        //KTTSL
        String insertKhoangThoiGianHocKTTSL1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL1' , '2017-1-2' , '2017-1-22' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocKTTSL1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL1' , '2017-2-6' , '2017-3-26' , 1 , '0-0-2' )";

        String insertKhoangThoiGianHocKTTSL2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL2' , '2017-1-2' , '2017-1-8' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocKTTSL2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL2' , '2017-1-9' , '2017-1-22' , 3 , '0-0-1' )";
        String insertKhoangThoiGianHocKTTSL2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL2' , '2017-2-6' , '2017-3-26' , 4 , '0-0-1' )";

        String insertKhoangThoiGianHocKTTSL3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL3' , '2017-2-6' , '2017-3-19' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocKTTSL3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL3' , '2017-3-20' , '2017-3-26' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocKTTSL3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL3' , '2017-3-27' , '2017-4-2' , 5 , '0-0-2' )";

        String insertKhoangThoiGianHocKTTSL4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL4' , '2017-1-9' , '2017-1-15' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocKTTSL4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL4' , '2017-1-16' , '2017-1-22' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocKTTSL4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL4' , '2017-2-6' , '2017-3-5' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocKTTSL4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTTSL4' , '2017-3-6' , '2017-3-12' , 5 , '0-0-3' )";


        //LTM
        String insertKhoangThoiGianHocLTM1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM1' , '2017-4-17' , '2017-4-30' , 2 , '0-1-5' )";
        String insertKhoangThoiGianHocLTM1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM1' , '2017-5-1' , '2017-5-7' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocLTM1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM1' , '2017-5-8' , '2017-6-4' , 2 , '0-1-5' )";
        String insertKhoangThoiGianHocLTM1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM1' , '2017-6-5' , '2017-6-18' , 2 , '0-0-5' )";

        String insertKhoangThoiGianHocLTM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM2' , '2017-4-17' , '2017-4-30' , 4 , '0-0-4' )";
        String insertKhoangThoiGianHocLTM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM2' , '2017-4-17' , '2017-4-30' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocLTM2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM2' , '2017-5-1' , '2017-5-7' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocLTM2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM2' , '2017-5-8' , '2017-6-4' , 4 , '0-0-4' )";
        String insertKhoangThoiGianHocLTM2_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM2' , '2017-5-8' , '2017-6-4' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocLTM2_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM2' , '2017-6-5' , '2017-6-18' , 3 , '0-0-5' )";

        String insertKhoangThoiGianHocLTM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM3' , '2017-4-17' , '2017-6-4' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocLTM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM3' , '2017-6-5' , '2017-6-18' , 5 , '0-3-5' )";

        String insertKhoangThoiGianHocLTM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM4' , '2017-4-17' , '2017-6-4' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocLTM4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTM4' , '2017-6-5' , '2017-6-18' , 5 , '0-2-4' )";


        //TKHT
        String insertKhoangThoiGianHocTKHT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT1' , '2017-1-2' , '2017-1-8' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocTKHT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT1' , '2017-1-9' , '2017-1-22' , 2 , '0-0-1' )";
        String insertKhoangThoiGianHocTKHT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT1' , '2017-2-6' , '2017-2-26' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocTKHT1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT1' , '2017-2-27' , '2017-3-26' , 2 , '0-0-1' )";
        String insertKhoangThoiGianHocTKHT1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT1' , '2017-2-27' , '2017-3-26' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocTKHT1_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT1' , '2017-3-27' , '2017-4-2' , 1 , '0-0-4' )";

        String insertKhoangThoiGianHocTKHT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT2' , '2017-1-2' , '2017-1-22' , 3 , '0-0-2' )";
        String insertKhoangThoiGianHocTKHT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT2' , '2017-2-6' , '2017-2-26' , 3 , '0-0-2' )";
        String insertKhoangThoiGianHocTKHT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT2' , '2017-2-27' , '2017-3-26' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocTKHT2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT2' , '2017-3-27' , '2017-4-2' , 3 , '0-0-2' )";

        String insertKhoangThoiGianHocTKHT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT3' , '2017-1-2' , '2017-1-22' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTKHT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT3' , '2017-2-6' , '2017-4-2' , 5 , '0-0-3' )";

        String insertKhoangThoiGianHocTKHT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT4' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocTKHT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT4' , '2017-2-6' , '2017-3-19' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocTKHT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT4' , '2017-3-20' , '2017-3-26' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocTKHT4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TKHT4' , '2017-3-27' , '2017-4-2' , 5 , '0-0-2' )";


        //TACN
        String insertKhoangThoiGianHocTACN1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN1' , '2017-1-2' , '2017-1-8' , 2 , '0-0-5' )";
        String insertKhoangThoiGianHocTACN1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN1' , '2017-1-9' , '2017-1-15' , 2 , '0-0-5' )";
        String insertKhoangThoiGianHocTACN1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN1' , '2017-1-16' , '2017-1-22' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocTACN1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN1' , '2017-2-6' , '2017-2-19' , 2 , '0-2-5' )";
        String insertKhoangThoiGianHocTACN1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN1' , '2017-2-20' , '2017-3-5' , 2 , '2-4-5' )";
        String insertKhoangThoiGianHocTACN1_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN1' , '2017-3-6' , '2017-3-12' , 2 , '0-2-5' )";
        String insertKhoangThoiGianHocTACN1_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN1' , '2017-3-13' , '2017-3-26' , 2 , '0-0-2' )";
        String insertKhoangThoiGianHocTACN1_8 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN1' , '2017-3-27' , '2017-4-2' , 2 , '2-4-5' )";

        String insertKhoangThoiGianHocTACN2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN2' , '2017-1-2' , '2017-1-22' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocTACN2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN2' , '2017-2-6' , '2017-2-26' , 4 , '0-2-5' )";
        String insertKhoangThoiGianHocTACN2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN2' , '2017-2-6' , '2017-2-26' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocTACN2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN2' , '2017-2-27' , '2017-3-19' , 4 , '0-2-4' )";
        String insertKhoangThoiGianHocTACN2_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN2' , '2017-3-20' , '2017-4-2' , 4 , '0-0-4' )";

        String insertKhoangThoiGianHocTACN3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN3' , '2017-1-2' , '2017-1-15' , 5 , '0-4-5' )";
        String insertKhoangThoiGianHocTACN3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN3' , '2017-1-16' , '2017-1-22' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocTACN3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN3' , '2017-2-6' , '2017-3-19' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocTACN3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN3' , '2017-3-20' , '2017-4-2' , 5 , '0-1-5' )";

        String insertKhoangThoiGianHocTACN4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN4' , '2017-1-2' , '2017-1-15' , 5 , '0-3-4' )";
        String insertKhoangThoiGianHocTACN4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN4' , '2017-1-16' , '2017-1-22' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTACN4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN4' , '2017-2-6' , '2017-3-19' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocTACN4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN4' , '2017-3-20' , '2017-2-4' , 5 , '0-1-3' )";

        String insertKhoangThoiGianHocTACN5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN5' , '2017-1-2' , '2017-1-15' , 2 , '0-0-5' )";
        String insertKhoangThoiGianHocTACN5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN5' , '2017-1-16' , '2017-1-22' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocTACN5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN5' , '2017-2-6' , '2017-2-19' , 2 , '0-2-5' )";
        String insertKhoangThoiGianHocTACN5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN5' , '2017-2-20' , '2017-3-5' , 2 , '2-4-5' )";
        String insertKhoangThoiGianHocTACN5_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN5' , '2017-3-6' , '2017-3-12' , 2 , '0-2-5' )";
        String insertKhoangThoiGianHocTACN5_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN5' , '2017-3-13' , '2017-3-26' , 2 , '0-0-2' )";
        String insertKhoangThoiGianHocTACN5_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN5' , '2017-3-27' , '2017-4-2' , 2 , '2-4-5' )";

        String insertKhoangThoiGianHocTACN6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN6' , '2017-1-2' , '2017-1-22' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocTACN6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN6' , '2017-2-6' , '2017-2-19' , 4 , '0-2-5' )";
        String insertKhoangThoiGianHocTACN6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN6' , '2017-2-6' , '2017-2-19' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocTACN6_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN6' , '2017-2-20' , '2017-2-26' , 4 , '0-2-5' )";
        String insertKhoangThoiGianHocTACN6_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN6' , '2017-2-20' , '2017-2-26' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocTACN6_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN6' , '2017-2-27' , '2017-3-19' , 4 , '0-2-4' )";
        String insertKhoangThoiGianHocTACN6_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN6' , '2017-3-20' , '2017-4-2' , 4 , '0-0-4' )";

        String insertKhoangThoiGianHocTACN7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN7' , '2017-1-2' , '2017-1-15' , 5 , '0-4-5' )";
        String insertKhoangThoiGianHocTACN7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN7' , '2017-1-16' , '2017-1-22' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocTACN7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN7' , '2017-2-6' , '2017-3-19' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocTACN7_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN7' , '2017-3-20' , '2017-4-2' , 5 , '0-1-5' )";

        String insertKhoangThoiGianHocTACN8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN8' , '2017-1-2' , '2017-1-15' , 5 , '0-3-4' )";
        String insertKhoangThoiGianHocTACN8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN8' , '2017-1-16' , '2017-1-22' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTACN8_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN8' , '2017-2-6' , '2017-3-19' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocTACN8_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TACN8' , '2017-3-20' , '2017-4-2' , 5 , '0-1-3' )";


        //PTW
        String insertKhoangThoiGianHocPTW1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW1' , '2017-1-2' , '2017-1-22' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocPTW1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW1' , '2017-2-6' , '2017-2-26' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocPTW1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW1' , '2017-2-27' , '2017-3-26' , 1 , '0-3-5' )";
        String insertKhoangThoiGianHocPTW1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW1' , '2017-3-27' , '2017-4-2' , 1 , '0-0-3' )";

        String insertKhoangThoiGianHocPTW2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW2' , '2017-1-2' , '2017-1-22' , 3 , '0-0-3' )";
        String insertKhoangThoiGianHocPTW2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW2' , '2017-2-6' , '2017-2-26' , 3 , '0-0-3' )";
        String insertKhoangThoiGianHocPTW2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW2' , '2017-2-27' , '2017-3-26' , 3 , '0-3-5' )";
        String insertKhoangThoiGianHocPTW2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW2' , '2017-3-27' , '2017-4-2' , 3 , '0-0-3' )";

        String insertKhoangThoiGianHocPTW3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW3' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocPTW3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW3' , '2017-2-6' , '2017-4-2' , 5 , '0-0-4' )";

        String insertKhoangThoiGianHocPTW4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW4' , '2017-1-2' , '2017-1-15' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocPTW4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW4' , '2017-1-16' , '2017-1-22' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocPTW4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'PTW4' , '2017-2-6' , '2017-4-2' , 5 , '0-0-5' )";


        //THS
        String insertKhoangThoiGianHocTHS1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'THS1' , '2017-4-17' , '2017-4-30' , 2 , '0-0-2' )";
        String insertKhoangThoiGianHocTHS1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'THS1' , '2017-4-17' , '2017-4-23' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocTHS1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'THS1' , '2017-5-1' , '2017-5-1' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocTHS1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'THS1' , '2017-5-8' , '2017-6-18' , 2 , '0-0-2' )";

        String insertKhoangThoiGianHocTHS2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'THS2' , '2017-4-17' , '2017-4-23' , 4 , '0-1-3' )";
        String insertKhoangThoiGianHocTHS2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'THS2' , '2017-4-24' , '2017-6-18' , 4 , '0-0-3' )";

        String insertKhoangThoiGianHocTHS3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'THS3' , '2017-4-17' , '2017-6-4' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocTHS3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'THS3' , '2017-6-5' , '2017-6-11' , 5 , '0-0-1' )";

        String insertKhoangThoiGianHocTHS4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'THS4' , '2017-4-17' , '2017-6-4' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTHS4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'THS4' , '2017-6-5' , '2017-6-11' , 6 , '0-0-1' )";


        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT4_5);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT4_4);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL4_3);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL4_4);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM4_2);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT4_4);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_7);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_8);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN2_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_7);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_7);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN7_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN7_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN7_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN7_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN8_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN8_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN8_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN8_4);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTW4_3);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS4_2);


        /*******************************************END KHOA 11**********************************/


        /*******************************************KHOA 12**********************************/
        //Insert dữ liệu cho bảng Môn học (KHÓA 12)
        String insertMonTCPIP = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('TCPIP' , 'Bộ giao thức TCP/IP và định tuyến mạng' , null , 4 , 3 , 'Đang cập nhật...' , 'NAM22' , 6)";
        String insertMonDTTT = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('DTTT' , 'Điện tử tương tự và điện tử số' , null , 5 , 3 , 'Đang cập nhật...' , 'NAM22' , 6)";
        String insertMonTD4 = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('TD4' , 'Giáo dục thể chất 4' , null , 2 , 1 , 'Đang cập nhật...' , 'NAM22' , 8)";
        String insertMonKTLT = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('KTLT' , 'Kỹ thuật lập trình' , null , 4 , 2 , 'Đang cập nhật...' , 'NAM22' , 6)";
        String insertMonLTCSDL = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('LTCSDL' , 'Lý thuyết cơ sở dữ liệu' , null , 3 , 2 , 'Đang cập nhật...' , 'NAM22' , 6)";
        String insertMonOTO = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('OTO' , 'Otomat và ngôn ngữ hình thức' , null , 4 , 2 , 'Đang cập nhật...' , 'NAM22' , 6)";
        String insertMonQTM = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('QTM' , 'Quản trị mạng máy tính' , null , 3 , 3 , 'Đang cập nhật...' , 'NAM22' , 6)";
        String insertMonTA2 = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('TA2' , 'Tiếng Anh 1 (học phần 2)' , null , 3 , 3 , 'Đang cập nhật...' , 'NAM22' , 11)";
        String insertMonTRR = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('TRR' , 'Toán rời rạc' , null , 3 , 2 , 'Đang cập nhật...' , 'NAM22' , 6)";


        sqLiteDatabase.execSQL(insertMonTCPIP);     //
        sqLiteDatabase.execSQL(insertMonDTTT);      //
        sqLiteDatabase.execSQL(insertMonTD4);       //
        sqLiteDatabase.execSQL(insertMonKTLT);      //
        sqLiteDatabase.execSQL(insertMonLTCSDL);    //
        sqLiteDatabase.execSQL(insertMonOTO);       //
        sqLiteDatabase.execSQL(insertMonQTM);       //
        sqLiteDatabase.execSQL(insertMonTA2);      //
        sqLiteDatabase.execSQL(insertMonTRR);       //


        //insert dữ liệu vào table Lớp học (KHÓA 12)
        String insertLopTCPIP1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TCPIP1' , 'L01' , '203_TA2 TA2' , 'Lê Thị Hồng Vân' , 'TCPIP' , 'LTHV')";
        String insertLopTCPIP2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TCPIP2' , 'L02' , '203_TA2 TA2' , 'Lê Thị Hồng Vân' , 'TCPIP' , 'LTHV')";
        String insertLopTCPIP3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TCPIP3' , 'L03' , '202_TA2 TA2' , 'Lê Thị Hồng Vân' , 'TCPIP' , 'LTHV')";
        String insertLopTCPIP4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TCPIP4' , 'L04' , '601_TA3 TA3' , 'Lê Thị Hồng Vân' , 'TCPIP' , 'LTHV')";
        String insertLopTCPIP5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TCPIP5' , 'L05' , '102_TA2 TA2' , 'Lê Thị Hồng Vân' , 'TCPIP' , 'LTHV')";
        String insertLopTCPIP6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TCPIP6' , 'L06' , '202_TA2 TA2' , 'Lê Thị Hồng Vân' , 'TCPIP' , 'LTHV')";

        String insertLopDTTT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DTTT1' , 'L01' , '203_TA2 TA2' , 'Đặng Văn Hải' , 'DTTT' , 'DVH')";
        String insertLopDTTT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DTTT2' , 'L02' , '203_TA2 TA2' , 'Lại Hồng Nhung' , 'DTTT' , 'LHN')";
        String insertLopDTTT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DTTT3' , 'L03' , '202_TA2 TA2' , 'Lại Hồng Nhung' , 'DTTT' , 'LHN')";
        String insertLopDTTT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DTTT4' , 'L04' , '601_TA3 TA3' , 'Lại Hồng Nhung' , 'DTTT' , 'LHN')";
        String insertLopDTTT5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DTTT5' , 'L05' , '102_TA2 TA2' , 'Đặng Văn Hải' , 'DTTT' , 'DVH')";
        String insertLopDTTT6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DTTT6' , 'L06' , '202_TA2 TA2' , 'Lại Hồng Nhung' , 'DTTT' , 'LHN')";

        String insertLopTD4_1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD4_1' , 'L01' , 'Sân bãi' , 'Đỗ Văn Hiếu' , 'TD4' , 'DVH1')";
        String insertLopTD4_2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD4_2' , 'L02' , 'Sân bãi' , 'Đỗ Văn Hiếu' , 'TD4' , 'DVH1')";
        String insertLopTD4_3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD4_3' , 'L03' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD4' , 'HMH')";
        String insertLopTD4_4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD4_4' , 'L04' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD4' , 'HMH')";
        String insertLopTD4_5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD4_5' , 'L05' , 'Sân bãi' , 'Đỗ Văn Hiếu' , 'TD4' , 'DVH1')";
        String insertLopTD4_6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD4_6' , 'L06' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD4' , 'HMH')";
        String insertLopTD4_7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD4_7' , 'L07' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD4' , 'HMH')";
        String insertLopTD4_8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD4_8' , 'L08' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD4' , 'HMH')";

        String insertLopKTLT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTLT1' , 'L01' , '203_TA2 TA2' , 'Thái Thanh Vân' , 'KTLT' , 'TTV')";
        String insertLopKTLT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTLT2' , 'L02' , '203_TA2 TA2' , 'Thái Thanh Vân' , 'KTLT' , 'TTV')";
        String insertLopKTLT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTLT3' , 'L03' , '202_TA2 TA2' , 'Thái Thanh Vân' , 'KTLT' , 'TTV')";
        String insertLopKTLT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTLT4' , 'L04' , '601_TA3 TA3' , 'Thái Thanh Vân' , 'KTLT' , 'TTV')";
        String insertLopKTLT5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTLT5' , 'L05' , '102_TA2 TA2' , 'Thái Thanh Vân' , 'KTLT' , 'TTV')";
        String insertLopKTLT6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KTLT6' , 'L06' , '202_TA2 TA2' , 'Thái Thanh Vân' , 'KTLT' , 'TTV')";

        String insertLopLTCSDL1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTCSDL1' , 'L01' , '203_TA2 TA2' , 'Thái Thanh Vân' , 'LTCSDL' , 'TTV')";
        String insertLopLTCSDL2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTCSDL2' , 'L02' , '203_TA2 TA2' , 'Thái Thanh Vân' , 'LTCSDL' , 'TTV')";
        String insertLopLTCSDL3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTCSDL3' , 'L03' , '202_TA2 TA2' , 'Thái Thanh Vân' , 'LTCSDL' , 'TTV')";
        String insertLopLTCSDL4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTCSDL4' , 'L04' , '601_TA3 TA3' , 'Thái Thanh Vân' , 'LTCSDL' , 'TTV')";
        String insertLopLTCSDL5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTCSDL5' , 'L05' , '102_TA2 TA2' , 'Thái Thanh Vân' , 'LTCSDL' , 'TTV')";
        String insertLopLTCSDL6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTCSDL6' , 'L06' , '202_TA2 TA2' , 'Thái Thanh Vân' , 'LTCSDL' , 'TTV')";

        String insertLopOTO1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'OTO1' , 'L01' , '203_TA2 TA2' , 'Nguyễn Văn Phác' , 'OTO' , 'NVP')";
        String insertLopOTO2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'OTO2' , 'L02' , '203_TA2 TA2' , 'Nguyễn Văn Phác' , 'OTO' , 'NVP')";
        String insertLopOTO3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'OTO3' , 'L03' , '202_TA2 TA2' , 'Nguyễn Văn Phác' , 'OTO' , 'NVP')";
        String insertLopOTO4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'OTO4' , 'L04' , '601_TA3 TA3' , 'Nguyễn Văn Phác' , 'OTO' , 'NVP')";
        String insertLopOTO5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'OTO5' , 'L05' , '102_TA2 TA2' , 'Nguyễn Văn Phác' , 'OTO' , 'NVP')";
        String insertLopOTO6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'OTO6' , 'L06' , '202_TA2 TA2' , 'Nguyễn Văn Phác' , 'OTO' , 'NVP')";

        String insertLopQTM_N02 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'QTM_N02' , 'N02' , '203_TA2 TA2' , 'Nguyễn Hồng Việt' , 'QTM' , 'NHV')";
        String insertLopQTM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'QTM2' , 'L02' , '203_TA2 TA2' , 'Nguyễn Hồng Việt' , 'QTM' , 'NHV')";
        String insertLopQTM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'QTM3' , 'L03' , '202_TA2 TA2' , 'Nguyễn Hồng Việt' , 'QTM' , 'NHV')";
        String insertLopQTM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'QTM4' , 'L04' , '601_TA3 TA3' , 'Nguyễn Hồng Việt' , 'QTM' , 'NHV')";
        String insertLopQTM5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'QTM5' , 'L05' , '102_TA2 TA2' , 'Nguyễn Hồng Việt' , 'QTM' , 'NHV')";
        String insertLopQTM6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'QTM6' , 'L06' , '202_TA2 TA2' , 'Nguyễn Hồng Việt' , 'QTM' , 'NHV')";

        String insertLopTA2_1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TA2_1' , 'L01' , '203_TA2 TA2' , 'Nguyễn Thị Huyền Trang' , 'TA2' , 'NTHT')";
        String insertLopTA2_2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TA2_2' , 'L02' , '203_TA2 TA2' , 'Nguyễn Thị Huyền Trang' , 'TA2' , 'NTHT')";
        String insertLopTA2_3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TA2_3' , 'L03' , '202_TA2 TA2' , 'Nguyễn Thị Hải Hà' , 'TA2' , 'NTHH')";
        String insertLopTA2_4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TA2_4' , 'L04' , '601_TA3 TA3' , 'Nguyễn Thị Minh Thu' , 'TA2' , 'NTMT')";
        String insertLopTA2_5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TA2_5' , 'L05' , '102_TA2 TA2' , 'No Name' , 'TA2' , 'none')";
        String insertLopTA2_6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TA2_6' , 'L06' , '202_TA2 TA2' , 'Đoàn Hồng Phương' , 'TA2' , 'DHP')";
        String insertLopTA2_7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TA2_7' , 'L07' , '201_TA2 TA2' , 'Đoàn Hồng Phương' , 'TA2' , 'DHP')";
        String insertLopTA2_8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TA2_8' , 'L08' , '103_TA2 TA1' , 'Đoàn Hồng Phương' , 'TA2' , 'DHP')";
        String insertLopTA2_9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TA2_9' , 'L09' , '201_TA2 TA2' , 'No Name' , 'TA2' , 'none')";
        String insertLopTA2_10 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TA2_10' , 'L10' , '103_AT1 TA1' , 'No Name' , 'TA2' , 'none')";
        String insertLopTA2_11 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TA2_11' , 'L11' , '104_TA1 TA1' , 'No Name' , 'TA2' , 'none')";

        String insertLopTRR1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TRR1' , 'L01' , '203_TA2 TA2' , 'Phạm Thị Phương Thảo' , 'TRR' , 'PTPT')";
        String insertLopTRR2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TRR2' , 'L02' , '203_TA2 TA2' , 'Phạm Thị Phương Thảo' , 'TRR' , 'PTPT')";
        String insertLopTRR3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TRR3' , 'L03' , '202_TA2 TA2' , 'Bùi Thị Giang' , 'TRR' , 'BTG')";
        String insertLopTRR4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TRR4' , 'L04' , '601_TA3 TA3' , 'Bùi Thị Giang' , 'TRR' , 'BTG')";
        String insertLopTRR5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TRR5' , 'L05' , '102_TA2 TA2' , 'No Name' , 'TRR' , 'none')";
        String insertLopTRR6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TRR6' , 'L06' , '202_TA2 TA2' , 'No Name' , 'TRR' , 'none')";


        sqLiteDatabase.execSQL(insertLopTCPIP1);        //
        sqLiteDatabase.execSQL(insertLopTCPIP2);
        sqLiteDatabase.execSQL(insertLopTCPIP3);
        sqLiteDatabase.execSQL(insertLopTCPIP4);
        sqLiteDatabase.execSQL(insertLopTCPIP5);
        sqLiteDatabase.execSQL(insertLopTCPIP6);
        sqLiteDatabase.execSQL(insertLopDTTT1);         //
        sqLiteDatabase.execSQL(insertLopDTTT2);
        sqLiteDatabase.execSQL(insertLopDTTT3);
        sqLiteDatabase.execSQL(insertLopDTTT4);
        sqLiteDatabase.execSQL(insertLopDTTT5);
        sqLiteDatabase.execSQL(insertLopDTTT6);
        sqLiteDatabase.execSQL(insertLopTD4_1);         //
        sqLiteDatabase.execSQL(insertLopTD4_2);
        sqLiteDatabase.execSQL(insertLopTD4_3);
        sqLiteDatabase.execSQL(insertLopTD4_4);
        sqLiteDatabase.execSQL(insertLopTD4_5);
        sqLiteDatabase.execSQL(insertLopTD4_6);
        sqLiteDatabase.execSQL(insertLopTD4_7);
        sqLiteDatabase.execSQL(insertLopTD4_8);
        sqLiteDatabase.execSQL(insertLopKTLT1);         //
        sqLiteDatabase.execSQL(insertLopKTLT2);
        sqLiteDatabase.execSQL(insertLopKTLT3);
        sqLiteDatabase.execSQL(insertLopKTLT4);
        sqLiteDatabase.execSQL(insertLopKTLT5);
        sqLiteDatabase.execSQL(insertLopKTLT6);
        sqLiteDatabase.execSQL(insertLopLTCSDL1);       //
        sqLiteDatabase.execSQL(insertLopLTCSDL2);
        sqLiteDatabase.execSQL(insertLopLTCSDL3);
        sqLiteDatabase.execSQL(insertLopLTCSDL4);
        sqLiteDatabase.execSQL(insertLopLTCSDL5);
        sqLiteDatabase.execSQL(insertLopLTCSDL6);
        sqLiteDatabase.execSQL(insertLopOTO1);          //
        sqLiteDatabase.execSQL(insertLopOTO2);
        sqLiteDatabase.execSQL(insertLopOTO3);
        sqLiteDatabase.execSQL(insertLopOTO4);
        sqLiteDatabase.execSQL(insertLopOTO5);
        sqLiteDatabase.execSQL(insertLopOTO6);
        sqLiteDatabase.execSQL(insertLopQTM_N02);          //
        sqLiteDatabase.execSQL(insertLopQTM2);
        sqLiteDatabase.execSQL(insertLopQTM3);
        sqLiteDatabase.execSQL(insertLopQTM4);
        sqLiteDatabase.execSQL(insertLopQTM5);
        sqLiteDatabase.execSQL(insertLopQTM6);
        sqLiteDatabase.execSQL(insertLopTA2_1);        //
        sqLiteDatabase.execSQL(insertLopTA2_2);
        sqLiteDatabase.execSQL(insertLopTA2_3);
        sqLiteDatabase.execSQL(insertLopTA2_4);
        sqLiteDatabase.execSQL(insertLopTA2_5);
        sqLiteDatabase.execSQL(insertLopTA2_6);
        sqLiteDatabase.execSQL(insertLopTA2_7);
        sqLiteDatabase.execSQL(insertLopTA2_8);
        sqLiteDatabase.execSQL(insertLopTA2_9);
        sqLiteDatabase.execSQL(insertLopTA2_10);
        sqLiteDatabase.execSQL(insertLopTA2_11);
        sqLiteDatabase.execSQL(insertLopTRR1);          //
        sqLiteDatabase.execSQL(insertLopTRR2);
        sqLiteDatabase.execSQL(insertLopTRR3);
        sqLiteDatabase.execSQL(insertLopTRR4);
        sqLiteDatabase.execSQL(insertLopTRR5);
        sqLiteDatabase.execSQL(insertLopTRR6);


        //Insert KHOANGTHOIGIANHOC cho KHOA 12

        //TCPIP
        String insertKhoangThoiGianHocTCPIP1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP1' , '2017-4-17' , '2017-4-30' , 1 , '1-3-5' )";
        String insertKhoangThoiGianHocTCPIP1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP1' , '2017-5-1' , '2017-6-18' , 1 , '0-3-5' )";
        String insertKhoangThoiGianHocTCPIP2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP2' , '2017-4-17' , '2017-4-30' , 3 , '1-3-5' )";
        String insertKhoangThoiGianHocTCPIP2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP2' , '2017-5-1' , '2017-5-7' , 3 , '0-3-5' )";
        String insertKhoangThoiGianHocTCPIP2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP2' , '2017-5-8' , '2017-6-18' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocTCPIP3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP3' , '2017-4-17' , '2017-4-30' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocTCPIP3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP3' , '2017-4-17' , '2017-6-18' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocTCPIP3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP3' , '2017-5-1' , '2017-6-18' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocTCPIP4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP4' , '2017-4-17' , '2017-4-30' , 4 , '0-1-3' )";
        String insertKhoangThoiGianHocTCPIP4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP4' , '2017-5-1' , '2017-5-7' , 4 , '0-0-3' )";
        String insertKhoangThoiGianHocTCPIP4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP4' , '2017-5-1' , '2017-5-7' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocTCPIP4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP4' , '2017-5-8' , '2017-6-4' , 4 , '0-1-3' )";
        String insertKhoangThoiGianHocTCPIP4_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP4' , '2017-6-5' , '2017-6-18' , 3 , '2-3-5' )";
        String insertKhoangThoiGianHocTCPIP5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP5' , '2017-2-27' , '2017-4-2' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocTCPIP5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP5' , '2017-4-10' , '2017-4-16' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocTCPIP5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP5' , '2017-4-17' , '2017-4-30' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocTCPIP5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP5' , '2017-5-1' , '2017-5-7' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocTCPIP5_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP5' , '2017-5-8' , '2017-5-14' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocTCPIP5_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP5' , '2017-5-15' , '2017-6-4' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocTCPIP6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP6' , '2017-2-27' , '2017-4-30' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocTCPIP6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP6' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTCPIP6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TCPIP6' , '2017-5-8' , '2017-6-11' , 5 , '0-0-2' )";


        //DTTT
        String insertKhoangThoiGianHocDTTT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT1' , '2017-1-2' , '2017-1-8' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocDTTT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT1' , '2017-1-9' , '2017-1-15' , 1 , '0-1-2' )";
        String insertKhoangThoiGianHocDTTT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT1' , '2017-1-16' , '2017-1-22' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocDTTT1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT1' , '2017-2-6' , '2017-3-5' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocDTTT1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT1' , '2017-3-6' , '2017-4-2' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocDTTT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT2' , '2017-1-2' , '2017-1-8' , 3 , '0-2-3' )";
        String insertKhoangThoiGianHocDTTT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT2' , '2017-1-9' , '2017-1-22' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocDTTT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT2' , '2017-2-6' , '2017-3-5' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocDTTT2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT2' , '2017-3-6' , '2017-4-2' , 3 , '0-0-1' )";
        String insertKhoangThoiGianHocDTTT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT3' , '2017-1-2' , '2017-1-22' , 2 , '0-0-2' )";
        String insertKhoangThoiGianHocDTTT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT3' , '2017-1-2' , '2017-1-15' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocDTTT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT3' , '2017-1-9' , '2017-1-22' , 2 , '0-0-4' )";
        String insertKhoangThoiGianHocDTTT3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT3' , '2017-2-6' , '2017-4-2' , 2 , '0-0-2' )";
        String insertKhoangThoiGianHocDTTT3_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT3' , '2017-2-6' , '2017-3-5' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocDTTT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT4' , '2017-1-2' , '2017-1-8' , 4 , '0-0-3' )";
        String insertKhoangThoiGianHocDTTT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT4' , '2017-1-2' , '2017-1-22' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocDTTT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT4' , '2017-1-9' , '2017-1-22' , 4 , '0-0-1' )";
        String insertKhoangThoiGianHocDTTT4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT4' , '2017-2-6' , '2017-4-2' , 4 , '0-0-1' )";
        String insertKhoangThoiGianHocDTTT4_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT4' , '2017-2-6' , '2017-3-5' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocDTTT5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT5' , '2017-1-2' , '2017-1-8' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocDTTT5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT5' , '2017-1-9' , '2017-1-22' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocDTTT5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT5' , '2017-2-6' , '2017-2-26' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocDTTT5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT5' , '2017-2-27' , '2017-4-2' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocDTTT6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT6' , '2017-1-2' , '2017-1-8' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocDTTT6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT6' , '2017-1-9' , '2017-1-22' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocDTTT6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT6' , '2017-2-6' , '2017-2-26' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocDTTT6_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DTTT6' , '2017-2-27' , '2017-4-2' , 5 , '0-0-1' )";


        //TD4
        String insertKhoangThoiGianHocTD4_1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_1' , '2017-1-9' , '2017-1-22' , 4 , '0-0-4' )";
        String insertKhoangThoiGianHocTD4_1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_1' , '2017-2-6' , '2017-4-2' , 4 , '0-0-4' )";
        String insertKhoangThoiGianHocTD4_2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_2' , '2017-1-9' , '2017-1-22' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocTD4_2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_2' , '2017-2-6' , '2017-4-2' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocTD4_3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_3' , '2017-1-9' , '2017-1-22' , 4 , '0-0-2' )";
        String insertKhoangThoiGianHocTD4_3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_3' , '2017-2-6' , '2017-4-2' , 4 , '0-0-2' )";
        String insertKhoangThoiGianHocTD4_4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_4' , '2017-1-9' , '2017-1-15' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocTD4_4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_4' , '2017-1-16' , '2017-1-22' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocTD4_4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_4' , '2017-2-6' , '2017-4-2' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocTD4_5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_5' , '2017-1-9' , '2017-1-22' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocTD4_5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_5' , '2017-2-6' , '2017-4-2' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocTD4_6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_6' , '2017-1-9' , '2017-1-22' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocTD4_6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_6' , '2017-2-6' , '2017-4-2' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocTD4_7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_7' , '2017-1-9' , '2017-1-22' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocTD4_7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_7' , '2017-2-6' , '2017-4-2' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocTD4_8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_8' , '2017-1-9' , '2017-1-22' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocTD4_8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD4_8' , '2017-2-6' , '2017-4-2' , 3 , '0-0-4' )";


        //KTLT
        String insertKhoangThoiGianHocKTLT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT1' , '2017-4-17' , '2017-4-30' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocKTLT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT1' , '2017-5-1' , '2017-5-7' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocKTLT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT1' , '2017-5-1' , '2017-5-7' , 2 , '0-0-4' )";
        String insertKhoangThoiGianHocKTLT1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT1' , '2017-5-8' , '2017-5-28' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocKTLT1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT1' , '2017-5-29' , '2017-6-18' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocKTLT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT2' , '2017-4-17' , '2017-4-30' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocKTLT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT2' , '2017-5-1' , '2017-5-7' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocKTLT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT2' , '2017-5-1' , '2017-5-7' , 4 , '0-0-4' )";
        String insertKhoangThoiGianHocKTLT2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT2' , '2017-5-8' , '2017-5-28' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocKTLT2_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT2' , '2017-5-29' , '2017-6-18' , 3 , '0-0-2' )";
        String insertKhoangThoiGianHocKTLT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT3' , '2017-4-17' , '2017-4-30' , 1 , '0-1-5' )";
        String insertKhoangThoiGianHocKTLT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT3' , '2017-5-1' , '2017-5-7' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocKTLT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT3' , '2017-5-1' , '2017-5-7' , 2 , '0-0-5' )";
        String insertKhoangThoiGianHocKTLT3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT3' , '2017-5-8' , '2017-5-28' , 1 , '0-1-5' )";
        String insertKhoangThoiGianHocKTLT3_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT3' , '2017-5-29' , '2017-6-18' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocKTLT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT4' , '2017-4-17' , '2017-4-30' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocKTLT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT4' , '2017-5-1' , '2017-5-7' , 3 , '0-0-3' )";
        String insertKhoangThoiGianHocKTLT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT4' , '2017-5-1' , '2017-5-7' , 4 , '0-0-5' )";
        String insertKhoangThoiGianHocKTLT4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT4' , '2017-5-8' , '2017-5-28' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocKTLT4_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT4' , '2017-5-29' , '2017-6-18' , 3 , '0-0-1' )";
        String insertKhoangThoiGianHocKTLT5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT5' , '2017-4-17' , '2017-4-30' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocKTLT5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT5' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocKTLT5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT5' , '2017-5-8' , '2017-6-4' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocKTLT5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT5' , '2017-6-5' , '2017-6-18' , 5 , '0-2-4' )";
        String insertKhoangThoiGianHocKTLT6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT6' , '2017-4-17' , '2017-6-4' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocKTLT6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KTLT6' , '2017-6-5' , '2017-6-18' , 5 , '0-1-4' )";


        //LTCSDL
        String insertKhoangThoiGianHocLTCSDL1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL1' , '2017-1-2' , '2017-1-22' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocLTCSDL1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL1' , '2017-1-2' , '2017-1-15' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocLTCSDL1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL1' , '2017-1-16' , '2017-1-22' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocLTCSDL1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL1' , '2017-2-6' , '2017-2-12' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocLTCSDL1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL1' , '2017-2-6' , '2017-4-2' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocLTCSDL2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL2' , '2017-1-2' , '2017-1-22' , 4 , '0-0-2' )";
        String insertKhoangThoiGianHocLTCSDL2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL2' , '2017-1-2' , '2017-1-22' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocLTCSDL2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL2' , '2017-2-6' , '2017-4-2' , 4 , '0-0-2' )";
        String insertKhoangThoiGianHocLTCSDL2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL2' , '2017-2-6' , '2017-2-12' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocLTCSDL3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL3' , '2017-1-2' , '2017-1-22' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocLTCSDL3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL3' , '2017-1-2' , '2017-1-8' , 2 , '0-0-5' )";
        String insertKhoangThoiGianHocLTCSDL3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL3' , '2017-1-9' , '2017-1-22' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocLTCSDL3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL3' , '2017-2-6' , '2017-2-12' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocLTCSDL3_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL3' , '2017-2-6' , '2017-4-2' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocLTCSDL4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL4' , '2017-1-2' , '2017-1-22' , 3 , '0-0-2' )";
        String insertKhoangThoiGianHocLTCSDL4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL4' , '2017-1-2' , '2017-1-15' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocLTCSDL4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL4' , '2017-1-16' , '2017-1-22' , 4 , '0-0-3' )";
        String insertKhoangThoiGianHocLTCSDL4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL4' , '2017-2-6' , '2017-2-12' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocLTCSDL4_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL4' , '2017-2-6' , '2017-4-2' , 3 , '0-0-2' )";
        String insertKhoangThoiGianHocLTCSDL5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL5' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocLTCSDL5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL5' , '2017-2-6' , '2017-4-2' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocLTCSDL6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL6' , '2017-1-2' , '2017-1-22' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocLTCSDL6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTCSDL6' , '2017-2-6' , '2017-4-2' , 5 , '0-0-5' )";


        //OTO
        String insertKhoangThoiGianHocOTO1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO1' , '2017-2-6' , '2017-4-2' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocOTO1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO1' , '2017-2-6' , '2017-2-12' , 2 , '0-0-4' )";
        String insertKhoangThoiGianHocOTO1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO1' , '2017-2-20' , '2017-4-2' , 2 , '0-0-4' )";
        String insertKhoangThoiGianHocOTO2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO2' , '2017-2-6' , '2017-4-2' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocOTO2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO2' , '2017-2-13' , '2017-4-2' , 4 , '0-0-3' )";
        String insertKhoangThoiGianHocOTO3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO3' , '2017-2-6' , '2017-2-12' , 2 , '0-1-3' )";
        String insertKhoangThoiGianHocOTO3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO3' , '2017-2-13' , '2017-2-19' , 2 , '0-0-1' )";
        String insertKhoangThoiGianHocOTO3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO3' , '2017-2-20' , '2017-4-2' , 2 , '0-3-5' )";
        String insertKhoangThoiGianHocOTO4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO4' , '2017-2-6' , '2017-3-26' , 3 , '0-0-3' )";
        String insertKhoangThoiGianHocOTO4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO4' , '2017-2-6' , '2017-3-26' , 4 , '0-0-5' )";
        String insertKhoangThoiGianHocOTO4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO4' , '2017-3-27' , '2017-4-2' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocOTO5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO5' , '2017-1-2' , '2017-1-15' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocOTO5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO5' , '2017-1-9' , '2017-1-22' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocOTO5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO5' , '2017-2-6' , '2017-3-26' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocOTO6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO6' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocOTO6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'OTO6' , '2017-2-6' , '2017-4-2' , 5 , '0-0-4' )";


        //QTM
        String insertKhoangThoiGianHocQTM_N02_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM_N02' , '2017-1-2' , '2017-1-15' , 2 , '0-0-5' )";
        String insertKhoangThoiGianHocQTM_N02_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM_N02' , '2017-1-9' , '2017-1-22' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocQTM_N02_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM_N02' , '2017-2-6' , '2017-3-26' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocQTM_N02_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM_N02' , '2017-2-6' , '2017-3-5' , 2 , '0-0-5' )";
        String insertKhoangThoiGianHocQTM_N02_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM_N02' , '2017-2-20' , '2017-3-5' , 2 , '0-0-4' )";
        String insertKhoangThoiGianHocQTM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM2' , '2017-4-17' , '2017-4-30' , 4 , '0-1-3' )";
        String insertKhoangThoiGianHocQTM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM2' , '2017-5-1' , '2017-5-7' , 4 , '0-3-5' )";
        String insertKhoangThoiGianHocQTM2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM2' , '2017-5-8' , '2017-6-4' , 4 , '0-1-3' )";
        String insertKhoangThoiGianHocQTM2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM2' , '2017-6-5' , '2017-6-18' , 4 , '0-0-3' )";
        String insertKhoangThoiGianHocQTM2_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM2' , '2017-6-5' , '2017-6-18' , 3 , '0-4-5' )";
        String insertKhoangThoiGianHocQTM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM3' , '2017-4-17' , '2017-4-30' , 2 , '0-2-4' )";
        String insertKhoangThoiGianHocQTM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM3' , '2017-5-1' , '2017-5-7' , 2 , '0-0-4' )";
        String insertKhoangThoiGianHocQTM3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM3' , '2017-5-1' , '2017-5-7' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocQTM3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM3' , '2017-5-8' , '2017-6-4' , 2 , '0-2-4' )";
        String insertKhoangThoiGianHocQTM3_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM3' , '2017-6-5' , '2017-6-18' , 2 , '0-2-4' )";
        String insertKhoangThoiGianHocQTM3_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM3' , '2017-6-5' , '2017-6-18' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocQTM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM4' , '2017-4-17' , '2017-4-30' , 4 , '2-4-5' )";
        String insertKhoangThoiGianHocQTM4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM4' , '2017-5-1' , '2017-5-7' , 3 , '0-0-4' )";
        String insertKhoangThoiGianHocQTM4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM4' , '2017-5-1' , '2017-5-7' , 4 , '0-0-4' )";
        String insertKhoangThoiGianHocQTM4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM4' , '2017-5-8' , '2017-6-18' , 4 , '0-2-4' )";
        String insertKhoangThoiGianHocQTM5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM5' , '2017-3-27' , '2017-4-2' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocQTM5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM5' , '2017-4-3' , '2017-4-9' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocQTM5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM5' , '2017-4-10' , '2017-4-16' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocQTM5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM5' , '2017-4-17' , '2017-4-30' , 5 , '0-3-4' )";
        String insertKhoangThoiGianHocQTM5_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM5' , '2017-5-8' , '2017-5-14' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocQTM5_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM5' , '2017-5-15' , '2017-5-28' , 5 , '0-3-4' )";
        String insertKhoangThoiGianHocQTM5_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM5' , '2017-5-29' , '2017-6-18' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocQTM6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM6' , '2017-3-27' , '2017-4-2' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocQTM6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM6' , '2017-4-3' , '2017-4-9' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocQTM6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM6' , '2017-4-10' , '2017-4-16' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocQTM6_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM6' , '2017-4-17' , '2017-4-30' , 5 , '0-3-5' )";
        String insertKhoangThoiGianHocQTM6_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM6' , '2017-5-1' , '2017-5-28' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocQTM6_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM6' , '2017-5-29' , '2017-6-4' , 5 , '0-1-5' )";
        String insertKhoangThoiGianHocQTM6_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'QTM6' , '2017-6-5' , '2017-6-18' , 5 , '0-0-5' )";


        //TA2
        String insertKhoangThoiGianHocTA2_1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_1' , '2017-1-2' , '2017-1-15' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocTA2_1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_1' , '2017-1-9' , '2017-1-15' , 2 , '0-0-5' )";
        String insertKhoangThoiGianHocTA2_1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_1' , '2017-1-16' , '2017-1-22' , 2 , '0-0-1' )";
        String insertKhoangThoiGianHocTA2_1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_1' , '2017-2-6' , '2017-4-2' , 2 , '0-1-5' )";
        String insertKhoangThoiGianHocTA2_2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_2' , '2017-1-2' , '2017-1-15' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocTA2_2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_2' , '2017-1-9' , '2017-1-22' , 4 , '0-0-1' )";
        String insertKhoangThoiGianHocTA2_2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_2' , '2017-2-6' , '2017-4-2' , 4 , '0-1-5' )";
        String insertKhoangThoiGianHocTA2_3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_3' , '2017-1-2' , '2017-1-22' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocTA2_3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_3' , '2017-1-9' , '2017-1-15' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocTA2_3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_3' , '2017-2-6' , '2017-4-2' , 1 , '0-2-5' )";
        String insertKhoangThoiGianHocTA2_4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_4' , '2017-1-2' , '2017-1-15' , 4 , '0-0-2' )";
        String insertKhoangThoiGianHocTA2_4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_4' , '2017-1-9' , '2017-1-22' , 4 , '0-0-4' )";
        String insertKhoangThoiGianHocTA2_4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_4' , '2017-2-6' , '2017-4-2' , 4 , '0-2-4' )";
        String insertKhoangThoiGianHocTA2_5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_5' , '2017-4-3' , '2017-4-9' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocTA2_5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_5' , '2017-4-3' , '2017-4-16' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocTA2_5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_5' , '2017-4-17' , '2017-5-28' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocTA2_5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_5' , '2017-5-29' , '2017-6-4' , 5 , '0-4-5' )";
        String insertKhoangThoiGianHocTA2_5_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_5' , '2017-6-5' , '2017-6-18' , 5 , '0-1-5' )";
        String insertKhoangThoiGianHocTA2_6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_6' , '2017-4-3' , '2017-4-9' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTA2_6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_6' , '2017-4-10' , '2017-4-16' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocTA2_6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_6' , '2017-4-17' , '2017-4-30' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocTA2_6_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_6' , '2017-5-8' , '2017-5-28' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocTA2_6_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_6' , '2017-5-29' , '2017-6-11' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTA2_6_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_6' , '2017-6-12' , '2017-6-18' , 5 , '0-2-3' )";
        String insertKhoangThoiGianHocTA2_7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_7' , '2017-1-2' , '2017-1-15' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocTA2_7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_7' , '2017-1-9' , '2017-1-15' , 2 , '0-0-5' )";
        String insertKhoangThoiGianHocTA2_7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_7' , '2017-1-16' , '2017-1-22' , 2 , '0-0-1' )";
        String insertKhoangThoiGianHocTA2_7_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_7' , '2017-2-6' , '2017-4-2' , 2 , '0-1-5' )";
        String insertKhoangThoiGianHocTA2_8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_8' , '2017-1-2' , '2017-1-15' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocTA2_8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_8' , '2017-1-9' , '2017-1-22' , 4 , '0-0-1' )";
        String insertKhoangThoiGianHocTA2_8_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_8' , '2017-2-6' , '2017-4-2' , 4 , '0-1-5' )";
        String insertKhoangThoiGianHocTA2_9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_9' , '2017-1-2' , '2017-1-22' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocTA2_9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_9' , '2017-1-9' , '2017-1-15' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocTA2_9_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_9' , '2017-2-6' , '2017-4-2' , 1 , '0-2-5' )";
        String insertKhoangThoiGianHocTA2_10_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_10' , '2017-4-3' , '2017-4-9' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocTA2_10_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_10' , '2017-4-3' , '2017-4-16' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocTA2_10_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_10' , '2017-4-17' , '2017-5-28' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocTA2_10_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_10' , '2017-5-29' , '2017-6-4' , 5 , '0-4-5' )";
        String insertKhoangThoiGianHocTA2_10_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_10' , '2017-6-5' , '2017-6-18' , 5 , '0-1-5' )";
        String insertKhoangThoiGianHocTA2_11_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_11' , '2017-4-3' , '2017-4-9' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTA2_11_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_11' , '2017-4-10' , '2017-4-16' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocTA2_11_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_11' , '2017-4-17' , '2017-4-30' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTA2_11_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_11' , '2017-5-8' , '2017-5-28' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocTA2_11_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_11' , '2017-5-29' , '2017-6-11' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTA2_11_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TA2_11' , '2017-6-12' , '2017-6-18' , 5 , '0-2-3' )";


        //TRR
        String insertKhoangThoiGianHocTRR1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR1' , '2017-1-2' , '2017-1-22' , 2 , '0-2-4' )";
        String insertKhoangThoiGianHocTRR1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR1' , '2017-2-6' , '2017-4-2' , 2 , '0-0-2' )";
        String insertKhoangThoiGianHocTRR1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR1' , '2017-3-27' , '2017-4-2' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocTRR2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR2' , '2017-1-2' , '2017-1-15' , 4 , '0-4-5' )";
        String insertKhoangThoiGianHocTRR2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR2' , '2017-1-16' , '2017-1-22' , 4 , '0-3-4' )";
        String insertKhoangThoiGianHocTRR2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR2' , '2017-2-6' , '2017-4-2' , 4 , '0-0-4' )";
        String insertKhoangThoiGianHocTRR2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR2' , '2017-3-27' , '2017-4-2' , 3 , '0-0-2' )";
        String insertKhoangThoiGianHocTRR3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR3' , '2017-1-2' , '2017-1-8' , 2 , '0-3-4' )";
        String insertKhoangThoiGianHocTRR3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR3' , '2017-1-9' , '2017-1-22' , 2 , '0-1-3' )";
        String insertKhoangThoiGianHocTRR3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR3' , '2017-2-6' , '2017-4-2' , 2 , '0-0-4' )";
        String insertKhoangThoiGianHocTRR3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR3' , '2017-3-27' , '2017-4-2' , 2 , '0-0-1' )";
        String insertKhoangThoiGianHocTRR4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR4' , '2017-1-2' , '2017-1-15' , 3 , '0-0-3' )";
        String insertKhoangThoiGianHocTRR4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR4' , '2017-1-2' , '2017-1-15' , 4 , '0-0-5' )";
        String insertKhoangThoiGianHocTRR4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR4' , '2017-1-16' , '2017-1-22' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocTRR4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR4' , '2017-2-6' , '2017-4-2' , 3 , '0-0-1' )";
        String insertKhoangThoiGianHocTRR4_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR4' , '2017-3-27' , '2017-4-2' , 3 , '0-0-3' )";
        String insertKhoangThoiGianHocTRR5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR5' , '2017-1-2' , '2017-1-22' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocTRR5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR5' , '2017-2-6' , '2017-4-2' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocTRR6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR6' , '2017-1-2' , '2017-1-8' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTRR6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR6' , '2017-1-9' , '2017-1-15' , 5 , '0-3-5' )";
        String insertKhoangThoiGianHocTRR6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR6' , '2017-1-16' , '2017-1-22' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTRR6_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR6' , '2017-2-6' , '2017-2-26' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocTRR6_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TRR6' , '2017-2-27' , '2017-3-26' , 5 , '0-0-3' )";


        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP4_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP5_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP5_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP5_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTCPIP6_3);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT1_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT3_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT4_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT5_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT6_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT6_4);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_7_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_7_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_8_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD4_8_2);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT1_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT2_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT3_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT4_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT5_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTLT6_2);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL1_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL3_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL4_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTCSDL6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOTO6_2);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM_N02_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM_N02_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM_N02_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM_N02_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM_N02_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM2_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM3_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM3_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM5_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM5_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM5_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM5_7);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM6_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM6_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM6_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM6_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM6_7);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_5_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_5_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_6_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_6_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_6_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_6_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_7_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_7_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_7_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_7_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_8_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_8_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_8_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_9_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_9_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_9_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_10_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_10_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_10_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_10_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_10_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_11_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_11_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_11_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_11_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_11_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA2_11_6);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR4_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR6_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR6_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTRR6_5);


        /*******************************************END KHOA 12**********************************/


        /*******************************************KHOA 13**********************************/

        //Insert dữ liệu cho bảng Môn học (KHÓA 13)
        String insertMonDCSVN = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('DCSVN' , 'Đường lối cách mạng của Đảng CSVN' , null , 3 , 3 , 'Đang cập nhật...' , 'NAM12' , 9)";
        String insertMonTD2 = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('TD2' , 'Giáo dục thể chất 2' , null , 1 , 1 , 'Đang cập nhật...' , 'NAM12' , 9)";
        String insertMonKNM = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('KNM' , 'Kỹ năng mềm' , null , 2 , 2 , 'Đang cập nhật...' , 'NAM12' , 9)";
        String insertMonLTC = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('LTC' , 'Lập trình căn bản' , null , 3 , 3 , 'Đang cập nhật...' , 'NAM12' , 9)";
        String insertMonCNMLN2 = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('CNMLN2' , 'Những NLCB của CNMLN (HP2)' , null , 2 , 3 , 'Đang cập nhật...' , 'NAM12' , 9)";
        String insertMonTOAN2 = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('TOAN2' , 'Toán cao cấp A2' , null , 3 , 3 , 'Đang cập nhật...' , 'NAM12' , 9)";
        String insertMonVL2 = " INSERT INTO " + TB_MONHOC + " VALUES "
                + " ('VL1' , 'Vật lý đại cương A1' , null , 3 , 3 , 'Đang cập nhật...' , 'NAM12' , 9)";

        sqLiteDatabase.execSQL(insertMonDCSVN);
        sqLiteDatabase.execSQL(insertMonTD2);
        sqLiteDatabase.execSQL(insertMonKNM);
        sqLiteDatabase.execSQL(insertMonLTC);
        sqLiteDatabase.execSQL(insertMonCNMLN2);
        sqLiteDatabase.execSQL(insertMonTOAN2);
        sqLiteDatabase.execSQL(insertMonVL2);

        //insert dữ liệu vào table Lớp học (KHÓA 13)
        String insertLopDCSVN1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DCSVN1' , 'L01' , '103_TA2 TA1' , 'Cù Thị Tặng' , 'DCSVN' , 'CTT')";
        String insertLopDCSVN2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DCSVN2' , 'L02' , '103_TA2 TA1' , 'No Name' , 'DCSVN' , 'none')";
        String insertLopDCSVN3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DCSVN3' , 'L03' , '104_TA2 TA2' , 'No Name' , 'DCSVN' , 'none')";
        String insertLopDCSVN4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DCSVN4' , 'L04' , '104_TA2 TA2' , 'Trần Thị Thuyết' , 'DCSVN' , 'TTT')";
        String insertLopDCSVN5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DCSVN5' , 'L05' , '202_TA2 TA2' , 'Trần Thị Thuyết' , 'DCSVN' , 'TTT')";
        String insertLopDCSVN6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DCSVN6' , 'L06' , '103_TA2 TA1' , 'Nguyễn Việt Cường' , 'DCSVN' , 'NVC')";
        String insertLopDCSVN7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DCSVN7' , 'L07' , '104_TA2 TA2' , 'No Name' , 'DCSVN' , 'none')";
        String insertLopDCSVN8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DCSVN8' , 'L08' , '601_TA3 TA3' , 'No Name' , 'DCSVN' , 'none')";
        String insertLopDCSVN9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'DCSVN9' , 'L09' , '601_TA3 TA3' , 'Cù Thị Tặng' , 'DCSVN' , 'CTT')";

        String insertLopTD2_1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD2_1' , 'L01' , 'Sân bãi' , 'Đỗ Văn Hiếu' , 'TD2' , 'DVH1')";
        String insertLopTD2_2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD2_2' , 'L02' , 'Sân bãi' , 'Đỗ Văn Hiếu' , 'TD2' , 'DVH1')";
        String insertLopTD2_3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD2_3' , 'L03' , 'Sân bãi' , 'Đỗ Văn Hiếu' , 'TD2' , 'DVH1')";
        String insertLopTD2_4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD2_4' , 'L04' , 'Sân bãi' , 'Đỗ Văn Hiếu' , 'TD2' , 'DVH1')";
        String insertLopTD2_5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD2_5' , 'L05' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD2' , 'HMH')";
        String insertLopTD2_6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD2_6' , 'L06' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD2' , 'HMH')";
        String insertLopTD2_7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD2_7' , 'L07' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD2' , 'HMH')";
        String insertLopTD2_8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD2_8' , 'L08' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD2' , 'HMH')";
        String insertLopTD2_9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TD2_9' , 'L09' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD2' , 'HMH')";

        String insertLopKNM1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KNM1' , 'L01' , '103_TA2 TA1' , 'Lê Tiến Phương' , 'KNM' , 'LTP')";
        String insertLopKNM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KNM2' , 'L02' , '103_TA2 TA1' , 'Nguyễn Thị Hồng Phương' , 'KNM' , 'NTHP')";
        String insertLopKNM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KNM3' , 'L03' , '104_TA2 TA2' , 'Nguyễn Thị Hồng Phương' , 'KNM' , 'NTHP')";
        String insertLopKNM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KNM4' , 'L04' , '104_TA2 TA2' , 'Nguyễn Thị Hồng Phương' , 'KNM' , 'NTHP')";
        String insertLopKNM5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KNM5' , 'L05' , '202_TA2 TA2' , 'Nguyễn Anh Thắng' , 'KNM' , 'NAT')";
        String insertLopKNM6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KNM6' , 'L06' , '103_TA2 TA1' , 'Nguyễn Anh Thắng' , 'KNM' , 'NAT')";
        String insertLopKNM7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KNM7' , 'L07' , '104_TA2 TA2' , 'Nguyễn Anh Thắng' , 'KNM' , 'NAT')";
        String insertLopKNM8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KNM8' , 'L08' , '601_TA3 TA3' , 'Lê Tiến Phương' , 'KNM' , 'LTP')";
        String insertLopKNM9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'KNM9' , 'L09' , '601_TA3 TA3' , 'Nguyễn Thị Hồng Phương' , 'KNM' , 'NTHP')";

        String insertLopLTC1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTC1' , 'L01' , '103_TA2 TA1' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
        String insertLopLTC2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTC2' , 'L02' , '103_TA2 TA1' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
        String insertLopLTC3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTC3' , 'L03' , '104_TA2 TA2' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
        String insertLopLTC4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTC4' , 'L04' , '104_TA2 TA2' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
        String insertLopLTC5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTC5' , 'L05' , '202_TA2 TA2' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
        String insertLopLTC6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTC6' , 'L06' , '103_TA2 TA1' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
        String insertLopLTC7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTC7' , 'L07' , '104_TA2 TA2' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
        String insertLopLTC8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTC8' , 'L08' , '601_TA3 TA3' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
        String insertLopLTC9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'LTC9' , 'L09' , '601_TA3 TA3' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";

        String insertLopCNMLN2_1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'CNMLN2_1' , 'L01' , '103_TA2 TA1' , 'No Name' , 'CNMLN2' , 'none')";
        String insertLopCNMLN2_2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'CNMLN2_2' , 'L02' , '103_TA2 TA1' , 'No Name' , 'CNMLN2' , 'none')";
        String insertLopCNMLN2_3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'CNMLN2_3' , 'L03' , '104_TA2 TA2' , 'No Name' , 'CNMLN2' , 'none')";
        String insertLopCNMLN2_4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'CNMLN2_4' , 'L04' , '104_TA2 TA2' , 'Đặng Thị Thu Hiền' , 'CNMLN2' , 'DTTH')";
        String insertLopCNMLN2_5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'CNMLN2_5' , 'L05' , '202_TA2 TA2' , 'Lê Tiến Phương' , 'CNMLN2' , 'LTP')";
        String insertLopCNMLN2_6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'CNMLN2_6' , 'L06' , '103_TA2 TA1' , 'Lê Tiến Phương' , 'CNMLN2' , 'LTP')";
        String insertLopCNMLN2_7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'CNMLN2_7' , 'L07' , '104_TA2 TA2' , 'Lê Tiến Phương' , 'CNMLN2' , 'LTP')";
        String insertLopCNMLN2_8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'CNMLN2_8' , 'L08' , '601_TA3 TA3' , 'No Name' , 'CNMLN2' , 'none')";
        String insertLopCNMLN2_9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'CNMLN2_9' , 'L09' , '601_TA3 TA3' , 'Đặng Thị Thu Hiền' , 'CNMLN2' , 'DTTH')";

        String insertLopTOAN2_1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TOAN2_1' , 'L01' , '103_TA2 TA1' , 'Nguyễn Văn Sơn' , 'TOAN2' , 'NVS')";
        String insertLopTOAN2_2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TOAN2_2' , 'L02' , '103_TA2 TA1' , 'Nguyễn Văn Sơn' , 'TOAN2' , 'NVS')";
        String insertLopTOAN2_3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TOAN2_3' , 'L03' , '104_TA2 TA2' , 'Thái Thị Kim Dung' , 'TOAN2' , 'TTKD')";
        String insertLopTOAN2_4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TOAN2_4' , 'L04' , '104_TA2 TA2' , 'Trần Thị Phương Thảo' , 'TOAN2' , 'TTPT')";
        String insertLopTOAN2_5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TOAN2_5' , 'L05' , '202_TA2 TA2' , 'Thái Thị Kim Dung' , 'TOAN2' , 'TTKD')";
        String insertLopTOAN2_6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TOAN2_6' , 'L06' , '103_TA2 TA1' , 'No Name' , 'TOAN2' , 'none')";
        String insertLopTOAN2_7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TOAN2_7' , 'L07' , '104_TA2 TA2' , 'No Name' , 'TOAN2' , 'none')";
        String insertLopTOAN2_8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TOAN2_8' , 'L08' , '601_TA3 TA3' , 'No Name' , 'TOAN2' , 'none')";
        String insertLopTOAN2_9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'TOAN2_9' , 'L09' , '601_TA3 TA3' , 'No Name' , 'TOAN2' , 'none')";

        String insertLopVL1_1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'VL1_1' , 'L01' , '103_TA2 TA1' , 'Nguyễn Duy Phương' , 'VL1' , 'NDP')";
        String insertLopVL1_2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'VL1_2' , 'L02' , '103_TA2 TA1' , 'Đỗ Minh Nam' , 'VL1' , 'DMN')";
        String insertLopVL1_3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'VL1_3' , 'L03' , '104_TA2 TA2' , 'Nguyễn Thị Thanh Vân' , 'VL1' , 'NTTV')";
        String insertLopVL1_4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'VL1_4' , 'L04' , '104_TA2 TA2' , 'Dương Thị Hồng Gấm' , 'VL1' , 'DTHG')";
        String insertLopVL1_5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'VL1_5' , 'L05' , '202_TA2 TA2' , 'Dương Thị Hồng Gấm' , 'VL1' , 'DTHG')";
        String insertLopVL1_6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'VL1_6' , 'L06' , '103_TA2 TA1' , 'Đào Xuân Dương' , 'VL1' , 'DXD')";
        String insertLopVL1_7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'VL1_7' , 'L07' , '104_TA2 TA2' , 'Đào Xuân Dương' , 'VL1' , 'DXD')";
        String insertLopVL1_8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'VL1_8' , 'L08' , '601_TA3 TA3' , 'No Name' , 'VL1' , 'none')";
        String insertLopVL1_9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
                + " ( 'VL1_9' , 'L09' , '601_TA3 TA3' , 'Đỗ Minh Nam' , 'VL1' , 'DMN')";

        sqLiteDatabase.execSQL(insertLopDCSVN1);    //
        sqLiteDatabase.execSQL(insertLopDCSVN2);
        sqLiteDatabase.execSQL(insertLopDCSVN3);
        sqLiteDatabase.execSQL(insertLopDCSVN4);
        sqLiteDatabase.execSQL(insertLopDCSVN5);
        sqLiteDatabase.execSQL(insertLopDCSVN6);
        sqLiteDatabase.execSQL(insertLopDCSVN7);
        sqLiteDatabase.execSQL(insertLopDCSVN8);
        sqLiteDatabase.execSQL(insertLopDCSVN9);
        sqLiteDatabase.execSQL(insertLopTD2_1);     //
        sqLiteDatabase.execSQL(insertLopTD2_2);
        sqLiteDatabase.execSQL(insertLopTD2_3);
        sqLiteDatabase.execSQL(insertLopTD2_4);
        sqLiteDatabase.execSQL(insertLopTD2_5);
        sqLiteDatabase.execSQL(insertLopTD2_6);
        sqLiteDatabase.execSQL(insertLopTD2_7);
        sqLiteDatabase.execSQL(insertLopTD2_8);
        sqLiteDatabase.execSQL(insertLopTD2_9);
        sqLiteDatabase.execSQL(insertLopKNM1);      //
        sqLiteDatabase.execSQL(insertLopKNM2);
        sqLiteDatabase.execSQL(insertLopKNM3);
        sqLiteDatabase.execSQL(insertLopKNM4);
        sqLiteDatabase.execSQL(insertLopKNM5);
        sqLiteDatabase.execSQL(insertLopKNM6);
        sqLiteDatabase.execSQL(insertLopKNM7);
        sqLiteDatabase.execSQL(insertLopKNM8);
        sqLiteDatabase.execSQL(insertLopKNM9);
        sqLiteDatabase.execSQL(insertLopLTC1);        //
        sqLiteDatabase.execSQL(insertLopLTC2);
        sqLiteDatabase.execSQL(insertLopLTC3);
        sqLiteDatabase.execSQL(insertLopLTC4);
        sqLiteDatabase.execSQL(insertLopLTC5);
        sqLiteDatabase.execSQL(insertLopLTC6);
        sqLiteDatabase.execSQL(insertLopLTC7);
        sqLiteDatabase.execSQL(insertLopLTC8);
        sqLiteDatabase.execSQL(insertLopLTC9);
        sqLiteDatabase.execSQL(insertLopCNMLN2_1);  //
        sqLiteDatabase.execSQL(insertLopCNMLN2_2);
        sqLiteDatabase.execSQL(insertLopCNMLN2_3);
        sqLiteDatabase.execSQL(insertLopCNMLN2_4);
        sqLiteDatabase.execSQL(insertLopCNMLN2_5);
        sqLiteDatabase.execSQL(insertLopCNMLN2_6);
        sqLiteDatabase.execSQL(insertLopCNMLN2_7);
        sqLiteDatabase.execSQL(insertLopCNMLN2_8);
        sqLiteDatabase.execSQL(insertLopCNMLN2_9);
        sqLiteDatabase.execSQL(insertLopTOAN2_1);   //
        sqLiteDatabase.execSQL(insertLopTOAN2_2);
        sqLiteDatabase.execSQL(insertLopTOAN2_3);
        sqLiteDatabase.execSQL(insertLopTOAN2_4);
        sqLiteDatabase.execSQL(insertLopTOAN2_5);
        sqLiteDatabase.execSQL(insertLopTOAN2_6);
        sqLiteDatabase.execSQL(insertLopTOAN2_7);
        sqLiteDatabase.execSQL(insertLopTOAN2_8);
        sqLiteDatabase.execSQL(insertLopTOAN2_9);
        sqLiteDatabase.execSQL(insertLopVL1_1);     //
        sqLiteDatabase.execSQL(insertLopVL1_2);
        sqLiteDatabase.execSQL(insertLopVL1_3);
        sqLiteDatabase.execSQL(insertLopVL1_4);
        sqLiteDatabase.execSQL(insertLopVL1_5);
        sqLiteDatabase.execSQL(insertLopVL1_6);
        sqLiteDatabase.execSQL(insertLopVL1_7);
        sqLiteDatabase.execSQL(insertLopVL1_8);
        sqLiteDatabase.execSQL(insertLopVL1_9);


        //Insert KHOANGTHOIGIANHOC cho KHOA 13

        //DCSVN
        String insertKhoangThoiGianHocDCSVN1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN1' , '2017-2-6' , '2017-3-26' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocDCSVN1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN1' , '2017-3-27' , '2017-4-2' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocDCSVN2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN2' , '2017-2-6' , '2017-3-26' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocDCSVN2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN2' , '2017-3-27' , '2017-4-2' , 3 , '0-0-1' )";
        String insertKhoangThoiGianHocDCSVN3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN3' , '2017-2-6' , '2017-3-26' , 4 , '0-1-3' )";
        String insertKhoangThoiGianHocDCSVN3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN3' , '2017-3-27' , '2017-4-2' , 4 , '0-0-1' )";
        String insertKhoangThoiGianHocDCSVN4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN4' , '2017-2-6' , '2017-3-26' , 1 , '0-1-4' )";
        String insertKhoangThoiGianHocDCSVN4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN4' , '2017-3-27' , '2017-4-2' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocDCSVN5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN5' , '2017-2-6' , '2017-3-26' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocDCSVN5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN5' , '2017-3-27' , '2017-4-2' , 3 , '0-0-2' )";
        String insertKhoangThoiGianHocDCSVN6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN6' , '2017-2-6' , '2017-3-12' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocDCSVN6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN6' , '2017-3-13' , '2017-4-2' , 5 , '0-4-5' )";
        String insertKhoangThoiGianHocDCSVN7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN7' , '2017-2-6' , '2017-3-12' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocDCSVN7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN7' , '2017-3-13' , '2017-4-2' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocDCSVN8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN8' , '2017-2-6' , '2017-3-12' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocDCSVN8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN8' , '2017-3-13' , '2017-4-2' , 5 , '0-4-5' )";
        String insertKhoangThoiGianHocDCSVN9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN9' , '2017-2-6' , '2017-3-26' , 2 , '0-4-5' )";
        String insertKhoangThoiGianHocDCSVN9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'DCSVN9' , '2017-3-27' , '2017-4-2' , 2 , '0-0-4' )";


        //TD2
        String insertKhoangThoiGianHocTD2_1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_1' , '2017-4-10' , '2017-6-18' , 4 , '0-0-3' )";
        String insertKhoangThoiGianHocTD2_2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_2' , '2017-4-10' , '2017-6-18' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocTD2_3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_3' , '2017-4-10' , '2017-6-18' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocTD2_4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_4' , '2017-4-10' , '2017-6-18' , 4 , '0-0-4' )";
        String insertKhoangThoiGianHocTD2_5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_5' , '2017-4-10' , '2017-6-18' , 1 , '0-0-4' )";
        String insertKhoangThoiGianHocTD2_6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_6' , '2017-4-10' , '2017-6-18' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocTD2_9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_9' , '2017-4-10' , '2017-6-18' , 4 , '0-0-3' )";
        String insertKhoangThoiGianHocTD2_7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_7' , '2017-4-10' , '2017-4-30' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocTD2_7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_7' , '2017-5-1' , '2017-5-7' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocTD2_7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_7' , '2017-5-8' , '2017-6-18' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocTD2_8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_8' , '2017-4-10' , '2017-4-30' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocTD2_8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_8' , '2017-5-1' , '2017-5-7' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocTD2_8_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TD2_8' , '2017-5-8' , '2017-6-18' , 1 , '0-0-1' )";


        //KNM1
        String insertKhoangThoiGianHocKNM1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM1' , '2017-4-17' , '2017-4-30' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM1' , '2017-5-1' , '2017-5-7' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocKNM1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM1' , '2017-5-8' , '2017-6-11' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM1' , '2017-6-12' , '2017-6-18' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocKNM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM2' , '2017-4-17' , '2017-4-30' , 3 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM2' , '2017-5-1' , '2017-5-7' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocKNM2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM2' , '2017-5-8' , '2017-6-11' , 3 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM2' , '2017-6-12' , '2017-6-18' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocKNM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM3' , '2017-4-17' , '2017-4-30' , 4 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM3' , '2017-5-1' , '2017-5-7' , 3 , '0-0-3' )";
        String insertKhoangThoiGianHocKNM3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM3' , '2017-5-8' , '2017-6-11' , 4 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM3' , '2017-6-12' , '2017-6-18' , 4 , '0-1-3' )";
        String insertKhoangThoiGianHocKNM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM4' , '2017-4-17' , '2017-4-30' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocKNM4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM4' , '2017-5-1' , '2017-5-7' , 2 , '0-0-5' )";
        String insertKhoangThoiGianHocKNM4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM4' , '2017-5-8' , '2017-6-4' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocKNM4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM4' , '2017-6-5' , '2017-6-11' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocKNM4_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM4' , '2017-6-12' , '2017-6-18' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocKNM5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM5' , '2017-4-17' , '2017-4-30' , 3 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM5' , '2017-5-1' , '2017-5-7' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocKNM5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM5' , '2017-5-8' , '2017-6-11' , 3 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM5' , '2017-6-12' , '2017-6-18' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocKNM6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM6' , '2017-4-17' , '2017-4-30' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM6' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocKNM6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM6' , '2017-5-8' , '2017-6-11' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM7' , '2017-4-17' , '2017-4-30' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocKNM7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM7' , '2017-5-1' , '2017-5-7' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocKNM7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM7' , '2017-5-8' , '2017-6-11' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocKNM8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM8' , '2017-4-17' , '2017-6-11' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocKNM9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM9' , '2017-4-17' , '2017-4-30' , 2 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM9' , '2017-5-1' , '2017-5-7' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocKNM9_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM9' , '2017-5-8' , '2017-6-11' , 2 , '0-0-1' )";
        String insertKhoangThoiGianHocKNM9_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'KNM9' , '2017-6-12' , '2017-6-18' , 2 , '0-1-3' )";


        //C
        String insertKhoangThoiGianHocLTC1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC1' , '2017-1-2' , '2017-1-22' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocLTC1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC1' , '2017-1-2' , '2017-1-8' , 2 , '0-0-5' )";
        String insertKhoangThoiGianHocLTC1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC1' , '2017-1-9' , '2017-1-22' , 1 , '0-0-1' )";
        String insertKhoangThoiGianHocLTC1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC1' , '2017-2-6' , '2017-3-5' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocLTC1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC1' , '2017-3-6' , '2017-4-2' , 1 , '0-0-1' )";

        String insertKhoangThoiGianHocLTC2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC2' , '2017-1-2' , '2017-1-22' , 3 , '0-0-3' )";
        String insertKhoangThoiGianHocLTC2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC2' , '2017-1-2' , '2017-1-8' , 4 , '0-0-5' )";
        String insertKhoangThoiGianHocLTC2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC2' , '2017-1-9' , '2017-1-22' , 3 , '0-0-1' )";
        String insertKhoangThoiGianHocLTC2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC2' , '2017-2-6' , '2017-3-5' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocLTC2_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC2' , '2017-3-6' , '2017-3-12' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocLTC2_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC2' , '2017-3-13' , '2017-4-2' , 3 , '0-0-2' )";

        String insertKhoangThoiGianHocLTC3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC3' , '2017-1-2' , '2017-1-22' , 4 , '0-2-4' )";
        String insertKhoangThoiGianHocLTC3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC3' , '2017-2-6' , '2017-3-5' , 4 , '0-2-4' )";
        String insertKhoangThoiGianHocLTC3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC3' , '2017-3-6' , '2017-3-26' , 4 , '0-0-2' )";
        String insertKhoangThoiGianHocLTC3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC3' , '2017-3-27' , '2017-4-2' , 3 , '0-0-4' )";

        String insertKhoangThoiGianHocLTC4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC4' , '2017-1-2' , '2017-1-22' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocLTC4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC4' , '2017-2-6' , '2017-3-5' , 1 , '0-2-5' )";
        String insertKhoangThoiGianHocLTC4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC4' , '2017-3-6' , '2017-3-12' , 1 , '0-0-2' )";
        String insertKhoangThoiGianHocLTC4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC4' , '2017-3-13' , '2017-4-2' , 1 , '0-0-2' )";

        String insertKhoangThoiGianHocLTC5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC5' , '2017-1-2' , '2017-1-15' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocLTC5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC5' , '2017-1-2' , '2017-1-8' , 4 , '0-0-3' )";
        String insertKhoangThoiGianHocLTC5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC5' , '2017-1-9' , '2017-1-15' , 4 , '0-0-1' )";
        String insertKhoangThoiGianHocLTC5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC5' , '2017-1-16' , '2017-1-22' , 4 , '0-1-3' )";
        String insertKhoangThoiGianHocLTC5_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC5' , '2017-2-6' , '2017-3-5' , 4 , '0-0-1' )";
        String insertKhoangThoiGianHocLTC5_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC5' , '2017-2-6' , '2017-3-5' , 3 , '0-0-5' )";
        String insertKhoangThoiGianHocLTC5_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC5' , '2017-3-6' , '2017-4-2' , 3 , '0-0-1' )";

        String insertKhoangThoiGianHocLTC6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC6' , '2017-1-2' , '2017-1-8' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocLTC6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC6' , '2017-1-9' , '2017-1-22' , 5 , '0-2-4' )";
        String insertKhoangThoiGianHocLTC6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC6' , '2017-2-6' , '2017-3-5' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocLTC6_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC6' , '2017-3-6' , '2017-3-12' , 5 , '0-2-5' )";
        String insertKhoangThoiGianHocLTC6_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC6' , '2017-3-13' , '2017-4-2' , 5 , '0-0-2' )";

        String insertKhoangThoiGianHocLTC7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC7' , '2017-1-2' , '2017-1-8' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocLTC7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC7' , '2017-1-9' , '2017-1-22' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocLTC7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC7' , '2017-2-6' , '2017-3-5' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocLTC7_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC7' , '2017-3-6' , '2017-3-12' , 5 , '0-3-4' )";
        String insertKhoangThoiGianHocLTC7_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC7' , '2017-3-13' , '2017-4-2' , 5 , '0-0-4' )";

        String insertKhoangThoiGianHocLTC8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC8' , '2017-1-2' , '2017-1-8' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocLTC8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC8' , '2017-1-9' , '2017-1-15' , 5 , '0-3-5' )";
        String insertKhoangThoiGianHocLTC8_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC8' , '2017-1-16' , '2017-1-22' , 5 , '0-3-4' )";
        String insertKhoangThoiGianHocLTC8_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC8' , '2017-2-6' , '2017-3-5' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocLTC8_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC8' , '2017-3-6' , '2017-3-12' , 5 , '0-3-5' )";
        String insertKhoangThoiGianHocLTC8_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC8' , '2017-3-13' , '2017-4-2' , 5 , '0-0-3' )";

        String insertKhoangThoiGianHocLTC9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC9' , '2017-1-2' , '2017-1-8' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocLTC9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC9' , '2017-1-2' , '2017-1-8' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocLTC9_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC9' , '2017-1-9' , '2017-1-22' , 2 , '0-1-3' )";
        String insertKhoangThoiGianHocLTC9_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC9' , '2017-2-6' , '2017-3-5' , 2 , '0-1-3' )";
        String insertKhoangThoiGianHocLTC9_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC9' , '2017-3-6' , '2017-3-12' , 1 , '0-0-3' )";
        String insertKhoangThoiGianHocLTC9_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'LTC9' , '2017-3-13' , '2017-4-2' , 2 , '0-0-3' )";


        //CNMLN2_1
        String insertKhoangThoiGianHocCNMLN2_1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_1' , '2017-1-2' , '2017-1-8' , 1 , '2-4-5' )";
        String insertKhoangThoiGianHocCNMLN2_1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_1' , '2017-1-9' , '2017-1-22' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocCNMLN2_1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_1' , '2017-2-6' , '2017-4-2' , 2 , '0-0-4' )";

        String insertKhoangThoiGianHocCNMLN2_2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_2' , '2017-1-2' , '2017-1-8' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocCNMLN2_2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_2' , '2017-1-9' , '2017-1-15' , 3 , '2-4-5' )";
        String insertKhoangThoiGianHocCNMLN2_2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_2' , '2017-1-16' , '2017-1-22' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocCNMLN2_2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_2' , '2017-2-6' , '2017-4-2' , 2 , '0-0-5' )";

        String insertKhoangThoiGianHocCNMLN2_3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_3' , '2017-1-2' , '2017-1-8' , 3 , '0-3-5' )";
        String insertKhoangThoiGianHocCNMLN2_3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_3' , '2017-1-9' , '2017-1-15' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocCNMLN2_3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_3' , '2017-1-9' , '2017-1-15' , 4 , '0-0-5' )";
        String insertKhoangThoiGianHocCNMLN2_3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_3' , '2017-1-16' , '2017-1-22' , 3 , '0-1-3' )";
        String insertKhoangThoiGianHocCNMLN2_3_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_3' , '2017-2-6' , '2017-4-2' , 3 , '0-0-1' )";

        String insertKhoangThoiGianHocCNMLN2_4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_4' , '2017-1-2' , '2017-1-8' , 2 , '0-3-5' )";
        String insertKhoangThoiGianHocCNMLN2_4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_4' , '2017-1-9' , '2017-1-15' , 1 , '1-3-5' )";
        String insertKhoangThoiGianHocCNMLN2_4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_4' , '2017-1-16' , '2017-1-22' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocCNMLN2_4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_4' , '2017-2-6' , '2017-4-2' , 1 , '0-0-3' )";

        String insertKhoangThoiGianHocCNMLN2_5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_5' , '2017-1-2' , '2017-1-8' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocCNMLN2_5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_5' , '2017-1-9' , '2017-1-15' , 3 , '2-3-4' )";
        String insertKhoangThoiGianHocCNMLN2_5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_5' , '2017-1-16' , '2017-1-22' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocCNMLN2_5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_5' , '2017-2-6' , '2017-4-2' , 3 , '0-0-3' )";

        String insertKhoangThoiGianHocCNMLN2_6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_6' , '2017-1-2' , '2017-1-8' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocCNMLN2_6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_6' , '2017-1-9' , '2017-1-22' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocCNMLN2_6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_6' , '2017-2-6' , '2017-4-2' , 5 , '0-0-1' )";

        String insertKhoangThoiGianHocCNMLN2_7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_7' , '2017-1-2' , '2017-1-15' , 5 , '0-0-5' )";
        String insertKhoangThoiGianHocCNMLN2_7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_7' , '2017-1-16' , '2017-1-22' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocCNMLN2_7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_7' , '2017-2-6' , '2017-4-2' , 5 , '0-0-5' )";

        String insertKhoangThoiGianHocCNMLN2_8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_8' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocCNMLN2_8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_8' , '2017-2-6' , '2017-4-2' , 5 , '0-0-2' )";

        String insertKhoangThoiGianHocCNMLN2_9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_9' , '2017-1-2' , '2017-1-8' , 2 , '0-2-4' )";
        String insertKhoangThoiGianHocCNMLN2_9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_9' , '2017-1-9' , '2017-1-15' , 2 , '2-4-5' )";
        String insertKhoangThoiGianHocCNMLN2_9_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_9' , '2017-1-16' , '2017-1-22' , 2 , '0-2-4' )";
        String insertKhoangThoiGianHocCNMLN2_9_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'CNMLN2_9' , '2017-2-6' , '2017-4-2' , 2 , '0-0-2' )";


        //TOAN2_1
        String insertKhoangThoiGianHocTOAN2_1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_1' , '2017-2-6' , '2017-3-5' , 2 , '0-0-3' )";
        String insertKhoangThoiGianHocTOAN2_1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_1' , '2017-2-6' , '2017-3-5' , 1 , '0-0-5' )";
        String insertKhoangThoiGianHocTOAN2_1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_1' , '2017-3-6' , '2017-3-26' , 1 , '0-3-5' )";
        String insertKhoangThoiGianHocTOAN2_1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_1' , '2017-3-27' , '2017-4-2' , 1 , '3-4-5' )";

        String insertKhoangThoiGianHocTOAN2_2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_2' , '2017-2-6' , '2017-3-26' , 4 , '0-1-3' )";
        String insertKhoangThoiGianHocTOAN2_2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_2' , '2017-3-27' , '2017-4-2' , 4 , '1-3-5' )";

        String insertKhoangThoiGianHocTOAN2_3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_3' , '2017-2-6' , '2017-3-26' , 3 , '0-2-5' )";
        String insertKhoangThoiGianHocTOAN2_3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_3' , '2017-3-27' , '2017-4-2' , 3 , '2-3-5' )";

        String insertKhoangThoiGianHocTOAN2_4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_4' , '2017-2-6' , '2017-3-26' , 2 , '0-1-3' )";
        String insertKhoangThoiGianHocTOAN2_4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_4' , '2017-3-27' , '2017-4-2' , 2 , '1-3-5' )";

        String insertKhoangThoiGianHocTOAN2_5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_5' , '2017-2-6' , '2017-3-19' , 4 , '0-3-5' )";
        String insertKhoangThoiGianHocTOAN2_5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_5' , '2017-3-20' , '2017-3-26' , 4 , '1-3-5' )";
        String insertKhoangThoiGianHocTOAN2_5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_5' , '2017-3-27' , '2017-4-2' , 4 , '0-3-5' )";

        String insertKhoangThoiGianHocTOAN2_6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_6' , '2017-1-2' , '2017-1-22' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocTOAN2_6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_6' , '2017-2-6' , '2017-2-19' , 5 , '0-3-5' )";
        String insertKhoangThoiGianHocTOAN2_6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_6' , '2017-2-20' , '2017-4-2' , 5 , '0-0-3' )";

        String insertKhoangThoiGianHocTOAN2_7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_7' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocTOAN2_7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_7' , '2017-2-6' , '2017-2-19' , 5 , '0-2-4' )";
        String insertKhoangThoiGianHocTOAN2_7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_7' , '2017-2-20' , '2017-4-2' , 5 , '0-0-2' )";

        String insertKhoangThoiGianHocTOAN2_8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_8' , '2017-1-2' , '2017-1-8' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocTOAN2_8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_8' , '2017-1-9' , '2017-1-22' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocTOAN2_8_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_8' , '2017-2-6' , '2017-2-19' , 5 , '0-1-3' )";
        String insertKhoangThoiGianHocTOAN2_8_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_8' , '2017-2-20' , '2017-4-2' , 5 , '0-0-1' )";

        String insertKhoangThoiGianHocTOAN2_9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_9' , '2017-2-6' , '2017-3-26' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocTOAN2_9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'TOAN2_9' , '2017-3-27' , '2017-4-2' , 1 , '2-4-5' )";


        //VL2
        String insertKhoangThoiGianHocVL2_1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_1' , '2017-4-17' , '2017-4-30' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocVL2_1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_1' , '2017-5-1' , '2017-5-7' , 1 , '0-4-5' )";
        String insertKhoangThoiGianHocVL2_1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_1' , '2017-5-8' , '2017-6-11' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocVL2_1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_1' , '2017-6-12' , '2017-6-18' , 1 , '0-0-2' )";

        String insertKhoangThoiGianHocVL2_2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_2' , '2017-4-17' , '2017-4-30' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocVL2_2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_2' , '2017-5-1' , '2017-5-7' , 3 , '0-3-4' )";
        String insertKhoangThoiGianHocVL2_2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_2' , '2017-5-8' , '2017-6-11' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocVL2_2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_2' , '2017-6-12' , '2017-6-18' , 3 , '0-0-2' )";

        String insertKhoangThoiGianHocVL2_3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_3' , '2017-4-17' , '2017-4-30' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocVL2_3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_3' , '2017-5-1' , '2017-5-7' , 3 , '0-4-5' )";
        String insertKhoangThoiGianHocVL2_3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_3' , '2017-5-8' , '2017-6-11' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocVL2_3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_3' , '2017-6-12' , '2017-6-18' , 3 , '0-0-2' )";

        String insertKhoangThoiGianHocVL2_4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_4' , '2017-4-17' , '2017-4-30' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocVL2_4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_4' , '2017-5-1' , '2017-5-7' , 1 , '0-3-4' )";
        String insertKhoangThoiGianHocVL2_4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_4' , '2017-5-8' , '2017-6-11' , 1 , '0-1-3' )";
        String insertKhoangThoiGianHocVL2_4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_4' , '2017-6-12' , '2017-6-18' , 1 , '0-0-3' )";

        String insertKhoangThoiGianHocVL2_5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_5' , '2017-4-17' , '2017-4-30' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocVL2_5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_5' , '2017-5-1' , '2017-5-7' , 3 , '0-4-5' )";
        String insertKhoangThoiGianHocVL2_5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_5' , '2017-5-8' , '2017-6-11' , 3 , '0-2-4' )";
        String insertKhoangThoiGianHocVL2_5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_5' , '2017-6-12' , '2017-6-18' , 3 , '0-0-4' )";

        String insertKhoangThoiGianHocVL2_6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_6' , '2017-4-17' , '2017-4-30' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocVL2_6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_6' , '2017-5-1' , '2017-5-7' , 5 , '0-0-4' )";
        String insertKhoangThoiGianHocVL2_6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_6' , '2017-5-8' , '2017-5-21' , 5 , '0-0-2' )";
        String insertKhoangThoiGianHocVL2_6_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_6' , '2017-5-22' , '2017-6-18' , 5 , '0-2-4' )";

        String insertKhoangThoiGianHocVL2_7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_7' , '2017-4-17' , '2017-4-30' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocVL2_7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_7' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocVL2_7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_7' , '2017-5-8' , '2017-5-21' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocVL2_7_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_7' , '2017-5-22' , '2017-6-18' , 5 , '0-1-3' )";

        String insertKhoangThoiGianHocVL2_8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_8' , '2017-4-17' , '2017-4-30' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocVL2_8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_8' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
        String insertKhoangThoiGianHocVL2_8_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_8' , '2017-5-8' , '2017-5-21' , 5 , '0-0-1' )";
        String insertKhoangThoiGianHocVL2_8_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_8' , '2017-5-22' , '2017-6-18' , 5 , '0-1-3' )";

        String insertKhoangThoiGianHocVL2_9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_9' , '2017-4-17' , '2017-4-30' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocVL2_9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_9' , '2017-5-1' , '2017-5-7' , 1 , '0-3-4' )";
        String insertKhoangThoiGianHocVL2_9_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_9' , '2017-5-8' , '2017-6-11' , 1 , '0-2-4' )";
        String insertKhoangThoiGianHocVL2_9_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
                + " ( 'VL2_9' , '2017-6-12' , '2017-6-18' , 1 , '0-0-2' )";


        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN7_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN7_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN8_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN8_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN9_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN9_2);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_7_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_7_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_7_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_8_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_8_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_8_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_9_1);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM4_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM5_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM6_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM7_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM7_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM7_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM8_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM9_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM9_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM9_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM9_4);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC1_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_7);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC6_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC6_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC6_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC7_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC7_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC7_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC7_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC7_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_6);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_6);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_3_5);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_5_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_6_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_7_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_7_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_7_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_8_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_8_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_9_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_9_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_9_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_9_4);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_6_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_7_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_7_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_7_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_8_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_8_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_8_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_8_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_9_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_9_2);

        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_1_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_1_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_1_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_1_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_2_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_2_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_2_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_2_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_3_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_3_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_3_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_3_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_4_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_4_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_4_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_4_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_5_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_5_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_5_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_5_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_6_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_6_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_6_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_6_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_7_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_7_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_7_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_7_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_8_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_8_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_8_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_8_4);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_9_1);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_9_2);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_9_3);
        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_9_4);


        /******************************************* END KHOA 13 **********************************/





//        //Insert dữ liệu vào Table Lớp Học
//        String insertLopDVM1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DVM1' , 'L01' , 'Phòng thực hành CNTT' , 'Nguyễn Đào Trường' , 'DVM' , 'NDT')";
//        String insertLopDVM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('DVM2' , 'L02' , 'Phòng thực hành CNTT' , 'Nguyễn Đào Trường' , 'DVM' , 'NDT')";
//        String insertLopDVM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('DVM3' , 'L03' , '203_TA2 TA2' , 'Nguyễn Đào Trường' , 'DVM' , 'NDT')";
//        String insertLopDVM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('DVM4' , 'L04' , '304_TA2 TA2' , 'Nguyễn Đào Trường' , 'DVM' , 'NDT')";
//        String insertLopCTDL1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('CTDL1' , 'L01' , '103_TA2 TA1' , 'Nguyễn Thị Phương Anh' , 'CTDL' , 'NTPA')";
//        String insertLopCTDL2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('CTDL2' , 'L02' , '103_TA2 TA1' , 'Nguyễn Văn Phác' , 'CTDL' , 'NVP')";
//        String insertLopCTDL3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('CTDL3' , 'L03' , '203_TA2 TA2' , 'Nguyễn Văn Phác' , 'CTDL' , 'NVP')";
//        String insertLopCTDL4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('CTDL4' , 'L04' , '304_TA2 TA2' , 'Nguyễn Thị Phương Anh' , 'CTDL' , 'NTPA')";
//        String insertLopCSTT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('CSTT1' , 'L01' , '103_TA2 TA1' , 'Vũ Xuân Đoàn' , 'CSTT' , 'VXD')";
//        String insertLopCSTT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('CSTT2' , 'L02' , '103_TA2 TA1' , 'Vũ Xuân Đoàn' , 'CSTT' , 'VXD')";
//        String insertLopCSTT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('CSTT3' , 'L03' , '203_TA2 TA2' , 'Vũ Xuân Đoàn' , 'CSTT' , 'VXD')";
//        String insertLopCSTT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('CSTT4' , 'L04' , '304_TA2 TA2' , 'Vũ Xuân Đoàn' , 'CSTT' , 'VXD')";
//        String insertLopDTTT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('DTTT1' , 'L01' , '103_TA2 TA1' , 'Nguyễn Thanh Ngọc' , 'DTTT' , 'NTN')";
//        String insertLopDTTT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('DTTT2' , 'L02' , '103_TA2 TA1' , 'Nguyễn Thanh Ngọc' , 'DTTT' , 'NTN')";
//        String insertLopDTTT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('DTTT3' , 'L03' , '203_TA2 TA2' , 'Lại Hồng Nhung' , 'DTTT' , 'LHN')";
//        String insertLopDTTT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('DTTT4' , 'L04' , '304_TA2 TA2' , 'Đặng Văn Hải' , 'DTTT' , 'DVH')";
//        String insertLopTD51 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('TD51' , 'L01' , 'Sân Bãi 1' , 'Đỗ Văn Hiếu' , 'TD5' , 'DVH1')";
//        String insertLopTD52 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('TD52' , 'L02' , 'Sân Bãi 1' , 'Hà Mai Hoa' , 'TD5' , 'HMH')";
//        String insertLopTD53 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('TD53' , 'L03' , 'Sân Bãi 3' , 'Đỗ Văn Hiếu' , 'TD5' , 'DVH1')";
//        String insertLopTD54 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('TD54' , 'L04' , 'Sân Bãi 2' , 'Hà Mai Hoa' , 'TD5' , 'HMH')";
//        String insertLopVXL1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('VXL1' , 'L01' , '103_TA2 TA1' , 'Đặng Hùng Việt' , 'VXL' , 'DHV')";
//        String insertLopVXL2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('VXL2' , 'L02' , '103_TA2 TA1' , 'Chu Thị Ngọc Quỳnh' , 'VXL' , 'CTNQ')";
//        String insertLopVXL3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('VXL3' , 'L03' , '203_TA2 TA2' , 'Đặng Hùng Việt' , 'VXL' , 'DHV')";
//        String insertLopVXL4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('VXL4' , 'L04' , '304_TA2 TA2' , 'Chu Thị Ngọc Quỳnh' , 'VXL' , 'CTNQ')";
//        String insertLopOOP1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('OOP1' , 'L01' , '103_TA2 TA1' , 'Lê Bá Cường' , 'OOP' , 'LBC')";
//        String insertLopOOP2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('OOP2' , 'L02' , '103_TA2 TA1' , 'Vũ Thị Đào' , 'OOP' , 'VTD')";
//        String insertLopOOP3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('OOP3' , 'L03' , '203_TA2 TA2' , 'Unknown' , 'OOP' , '???')";
//        String insertLopOOP4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('OOP4' , 'L04' , '304_TA2 TA2' , 'Unknown' , 'OOP' , '???')";
//        String insertLopQTM_N02 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('QTM_N02' , 'L01' , 'Phòng thực hành CNTT' , 'Nguyễn Hồng Việt' , 'QTM' , 'NHV')";
//        String insertLopQTM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('QTM2' , 'L02' , 'Phòng thực hành CNTT' , 'Nguyễn Hồng Việt' , 'QTM' , 'NHV')";
//        String insertLopQTM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('QTM3' , 'L03' , 'Phòng thực hành CNTT' , 'Nguyễn Hồng Việt' , 'QTM' , 'NHV')";
//        String insertLopQTM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('QTM4' , 'L04' , 'Phòng thực hành CNTT' , 'Nguyễn Hồng Việt' , 'QTM' , 'NHV')";
//        String insertLopTA31 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('TA31' , 'L01' , '103_TA2 TA1' , 'Nguyễn Thị Tý' , 'TA3' , 'NTT')";
//        String insertLopTA32 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('TA32' , 'L02' , '103_TA2 TA1' , 'Mai Thị Hảo' , 'TA3' , 'MTH')";
//        String insertLopTA33 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('TA33' , 'L03' , '203_TA2 TA2' , 'Unknown' , 'TA3' , '???')";
//        String insertLopTA34 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('TA34' , 'L04' , '304_TA2 TA2' , 'Nguyễn Thị Tý' , 'TA3' , 'NTT')";
//        String insertLopTA36 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('TA36' , 'L06' , '2.1_TB (nhà 2 tầng) TB1' , 'Nguyễn Thị Hải Hà' , 'TA3' , 'NTHH')";
//        String insertLopTA37 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('TA37' , 'L07' , '2.1_TB (nhà 2 tầng) TB1' , 'Phan Bích Thuận' , 'TA3' , 'PBT')";
//        String insertLopTA38 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ('TA38' , 'L08' , '201_TA2 TA2' , 'Mai Thị Hảo' , 'TA3' , 'MTH')";
//
//        sqLiteDatabase.execSQL(insertLopDVM1);
//        sqLiteDatabase.execSQL(insertLopDVM2);
//        sqLiteDatabase.execSQL(insertLopDVM3);
//        sqLiteDatabase.execSQL(insertLopDVM4);
//        sqLiteDatabase.execSQL(insertLopCTDL1);
//        sqLiteDatabase.execSQL(insertLopCTDL2);
//        sqLiteDatabase.execSQL(insertLopCTDL3);
//        sqLiteDatabase.execSQL(insertLopCTDL4);
//        sqLiteDatabase.execSQL(insertLopCSTT1);
//        sqLiteDatabase.execSQL(insertLopCSTT2);
//        sqLiteDatabase.execSQL(insertLopCSTT3);
//        sqLiteDatabase.execSQL(insertLopCSTT4);
//        sqLiteDatabase.execSQL(insertLopDTTT1);
//        sqLiteDatabase.execSQL(insertLopDTTT2);
//        sqLiteDatabase.execSQL(insertLopDTTT3);
//        sqLiteDatabase.execSQL(insertLopDTTT4);
//        sqLiteDatabase.execSQL(insertLopTD51);
//        sqLiteDatabase.execSQL(insertLopTD52);
//        sqLiteDatabase.execSQL(insertLopTD53);
//        sqLiteDatabase.execSQL(insertLopTD54);
//        sqLiteDatabase.execSQL(insertLopVXL1);
//        sqLiteDatabase.execSQL(insertLopVXL2);
//        sqLiteDatabase.execSQL(insertLopVXL3);
//        sqLiteDatabase.execSQL(insertLopVXL4);
//        sqLiteDatabase.execSQL(insertLopOOP1);
//        sqLiteDatabase.execSQL(insertLopOOP2);
//        sqLiteDatabase.execSQL(insertLopOOP3);
//        sqLiteDatabase.execSQL(insertLopOOP4);
//        sqLiteDatabase.execSQL(insertLopQTM_N02);
//        sqLiteDatabase.execSQL(insertLopQTM2);
//        sqLiteDatabase.execSQL(insertLopQTM3);
//        sqLiteDatabase.execSQL(insertLopQTM4);
//        sqLiteDatabase.execSQL(insertLopTA31);
//        sqLiteDatabase.execSQL(insertLopTA32);
//        sqLiteDatabase.execSQL(insertLopTA33);
//        sqLiteDatabase.execSQL(insertLopTA34);
//        sqLiteDatabase.execSQL(insertLopTA36);
//        sqLiteDatabase.execSQL(insertLopTA37);
//        sqLiteDatabase.execSQL(insertLopTA38);
//


//        // Insert dữ liệu sẵn cho bảng Môn Học :
//        String insertMonDVM = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('DVM' , 'Các dịch vụ mạng' , null , 3 , 2 , 'Tổng quan về các dịch vụ mạng phổ biến' , 'NAM31' , 4)";
//        String insertMonCTDL = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('CTDL' , 'Cấu trúc dữ liệu - giải thuật' , null , 4 , 2 , 'Các thuật toán hay sử dụng và cấu trúc dữ liệu trong lập trình' , 'NAM31' , 4)";
//        String insertMonCSTT = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('CSTT' , 'Cơ sở lý thuyết truyền tin' , null , 4 , 2 , 'Lý thuyết cơ bản về truyền tin' , 'NAM31' , 4)";
//        String insertMonDTTT = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('DTTT' , 'Điện tử tương tự và điện tử số' , null , 5 , 3 , 'Sử dụng tài liệu vô tư, thi lại thoải mái' , 'NAM31' , 4)";
//        String insertMonTD5 = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('TD5' , 'Giáo dục thể chất 5' , null , 3 , 1 , 'Cầu lông' , 'NAM31' , 4)";
//        String insertMonVXL = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('VXL' , 'Kỹ thuật vi xử lý' , null , 4 , 2 , 'Assembly' , 'NAM31' , 4)";
//        String insertMonOOP = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('OOP' , 'Lập trình hướng đối tượng' , null , 4 , 2 , 'Lập trình hướng đối tượng với Java' , 'NAM31' , 4)";
//        String insertMonQTM = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('QTM' , 'Quản trị mạng máy tính' , null , 4 , 3 , 'Hệ thống quản trị mạng máy tính' , 'NAM31' , 4)";
//        String insertMonTA3 = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('TA3' , 'Tiếng anh 3' , null , 3 , 3 , 'Không phải Tiếng Việt' , 'NAM31' , 7)";
//
//        sqLiteDatabase.execSQL(insertMonDVM);
//        sqLiteDatabase.execSQL(insertMonCTDL);
//        sqLiteDatabase.execSQL(insertMonCSTT);
//        sqLiteDatabase.execSQL(insertMonDTTT);
//        sqLiteDatabase.execSQL(insertMonTD5);
//        sqLiteDatabase.execSQL(insertMonVXL);
//        sqLiteDatabase.execSQL(insertMonOOP);
//        sqLiteDatabase.execSQL(insertMonQTM);
//        sqLiteDatabase.execSQL(insertMonTA3);
//
//
//        //Them du lieu vao table Khoang thoi gian hoc
//        //DVM1 + DVM3
//        String insertKhoangThoiGianHocDVM1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DVM1' , '2016-8-1' , '2016-8-28' , 2 , '1-3-5' )";
//        String insertKhoangThoiGianHocDVM1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DVM1' , '2016-8-29' , '2016-9-4' , 2 , '0-1-3' )";
//
//        String insertKhoangThoiGianHocDVM3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DVM3' , '2016-8-1' , '2016-10-9' , 5 , '0-0-3' )";
//
//
//        //CTDL1 + CTDL4
//        String insertKhoangThoiGianHocCTDL1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL1' , '2016-8-1' , '2016-9-11' , 2 , '0-2-4' )";
//        String insertKhoangThoiGianHocCTDL1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL1' , '2016-9-12' , '2016-9-18' , 2 , '0-0-2' )";
//        String insertKhoangThoiGianHocCTDL1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL1' , '2016-9-12' , '2016-9-18' , 1 , '0-0-5' )";
//        String insertKhoangThoiGianHocCTDL1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL1' , '2016-9-19' , '2016-9-25' , 2 , '0-0-5' )";
//
//        String insertKhoangThoiGianHocCTDL4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL4' , '2016-11-14' , '2016-12-4' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocCTDL4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL4' , '2016-12-5' , '2017-1-1' , 5 , '0-2-4' )";
//
//        //CSTT2 + CSTT4
//        String insertKhoangThoiGianHocCSTT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CSTT2' , '2016-10-17' , '2016-11-20' , 3 , '0-2-4' )";
//
//        String insertKhoangThoiGianHocCSTT4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CSTT4' , '2016-10-17' , '2016-12-4' , 5 , '0-0-2' )";
//
//
//        //DTTT1 + DTTT4
//        String insertKhoangThoiGianHocDTTT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT1' , '2016-8-15' , '2016-9-11' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocDTTT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT1' , '2016-9-12' , '2016-9-18' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocDTTT1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT1' , '2016-9-12' , '2016-9-18' , 2 , '0-0-5' )";
//        String insertKhoangThoiGianHocDTTT1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT1' , '2016-9-19' , '2016-9-25' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocDTTT1_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT1' , '2016-9-26' , '2016-10-9' , 2 , '1-3-5' )";
//
//        String insertKhoangThoiGianHocDTTT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT4' , '2016-8-15' , '2016-9-4' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocDTTT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT4' , '2016-9-5' , '2016-9-10' , 5 , '0-1-3' )";
//
//
//        //TD53 + TD51
//        String insertKhoangThoiGianHocTD53_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD53' , '2016-8-1' , '2016-8-28' , 1 , '0-0-6' )";
//        String insertKhoangThoiGianHocTD53_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD53' , '2016-9-5' , '2016-9-11' , 1 , '0-0-6' )";
//        String insertKhoangThoiGianHocTD53_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD53' , '2016-9-19' , '2016-10-23' , 1 , '0-0-6' )";
//
//        String insertKhoangThoiGianHocTD51_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD51' , '2016-10-17' , '2016-11-13' , 1 , '0-0-6' )";
//        String insertKhoangThoiGianHocTD51_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD51' , '2016-11-21' , '2017-1-1' , 1 , '0-0-6' )";
//
//
//        //VXL2 + VXL1
//        String insertKhoangThoiGianHocVXL2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VXL2' , '2016-10-17' , '2016-11-27' , 4 , '0-2-4' )";
//
//        String insertKhoangThoiGianHocVXL1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VXL1' , '2016-10-17' , '2016-11-27' , 1 , '0-1-3' )";
//
//
//        //OOP2 + OOP1
//        String insertKhoangThoiGianHocOOP2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP2' , '2016-10-17' , '2016-12-4' , 3 , '0-1-3' )";
//        String insertKhoangThoiGianHocOOP2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP2' , '2016-12-5' , '2016-12-11' , 4 , '0-0-4' )";
//
//        String insertKhoangThoiGianHocOOP1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP1' , '2016-10-17' , '2016-12-4' , 2 , '0-1-3' )";
//        String insertKhoangThoiGianHocOOP1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP1' , '2016-12-5' , '2016-12-11' , 2 , '0-0-2' )";
//
//
//        //QTM_N02 + QTM2
//        String insertKhoangThoiGianHocQTM_N02_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'QTM_N02' , '2016-10-17' , '2016-12-25' , 4 , '0-1-3' )";
//
//        String insertKhoangThoiGianHocQTM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'QTM2' , '2016-10-17' , '2016-12-25' , 1 , '0-2-4' )";
//
//
//        //TA36 + TA31
//        String insertKhoangThoiGianHocTA36_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA36' , '2016-8-1' , '2016-10-9' , 1 , '0-1-3' )";
//
//        String insertKhoangThoiGianHocTA31_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA31' , '2016-8-1' , '2016-10-9' , 1 , '0-1-3' )";
//
//        //insert into table KHOANGTHOIGIANHOC part 2 (for testing)
//        //DVM2 + DVM4
//        String insertKhoangThoiGianHocDVM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DVM2' , '2016-8-1' , '2016-8-28' , 4 , '1-3-5' )";
//        String insertKhoangThoiGianHocDVM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DVM2' , '2016-8-29' , '2016-9-4' , 4 , '0-1-3' )";
//
//        String insertKhoangThoiGianHocDVM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DVM4' , '2016-8-1' , '2016-9-11' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocDVM4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DVM4' , '2016-9-12' , '2016-9-18' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocDVM4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DVM4' , '2016-9-19' , '2016-10-9' , 5 , '0-0-4' )";
//
//
//        //CTDL2 + CTDL3
//        String insertKhoangThoiGianHocCTDL2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL2' , '2016-8-1' , '2016-9-11' , 4 , '0-0-2' )";
//        String insertKhoangThoiGianHocCTDL2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL2' , '2016-8-1' , '2016-9-11' , 3 , '0-0-4' )";
//        String insertKhoangThoiGianHocCTDL2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL2' , '2016-9-12' , '2016-9-18' , 4 , '0-0-2' )";
//        String insertKhoangThoiGianHocCTDL2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL2' , '2016-9-12' , '2016-9-18' , 3 , '0-0-5' )";
//        String insertKhoangThoiGianHocCTDL2_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL2' , '2016-9-19' , '2016-9-25' , 3 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocCTDL3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL3' , '2016-11-14' , '2016-12-4' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocCTDL3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CTDL3' , '2016-12-5' , '2017-1-1' , 5 , '0-1-4' )";
//
//
//        //CSTT1 + CSTT3
//        String insertKhoangThoiGianHocCSTT1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CSTT1' , '2016-10-17' , '2016-11-20' , 2 , '0-2-4' )";
//
//        String insertKhoangThoiGianHocCSTT3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CSTT3' , '2016-10-17' , '2016-12-4' , 5 , '0-0-4' )";
//
//
//        //DTTT2 + DTTT3
//        String insertKhoangThoiGianHocDTTT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT2' , '2016-8-15' , '2016-9-25' , 3 , '0-1-3' )";
//        String insertKhoangThoiGianHocDTTT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT2' , '2016-9-26' , '2016-10-9' , 4 , '0-0-1' )";
//        String insertKhoangThoiGianHocDTTT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT2' , '2016-9-26' , '2016-10-9' , 3 , '0-2-5' )";
//
//        String insertKhoangThoiGianHocDTTT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT3' , '2016-8-15' , '2016-9-4' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocDTTT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT3' , '2016-9-5' , '2016-9-11' , 5 , '0-1-4' )";
//        String insertKhoangThoiGianHocDTTT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT3' , '2016-9-12' , '2016-9-18' , 5 , '0-1-5' )";
//        String insertKhoangThoiGianHocDTTT3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DTTT3' , '2016-9-19' , '2016-10-9' , 5 , '0-1-4' )";
//
//
//        //TD52 + TD54
//        String insertKhoangThoiGianHocTD52_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD52' , '2016-10-17' , '2016-11-13' , 4 , '0-0-6' )";
//        String insertKhoangThoiGianHocTD52_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD52' , '2016-11-21' , '2017-1-1' , 4 , '0-0-6' )";
//
//        String insertKhoangThoiGianHocTD54_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD54' , '2016-8-1' , '2016-8-28' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocTD54_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD54' , '2016-9-5' , '2016-9-11' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocTD54_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD54' , '2016-9-19' , '2016-10-23' , 1 , '0-0-2' )";
//
//
//        //VXL3 + VXL4
//        String insertKhoangThoiGianHocVXL3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VXL3' , '2016-10-17' , '2016-11-13' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocVXL3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VXL3' , '2016-11-21' , '2016-12-18' , 5 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocVXL4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VXL4' , '2016-10-17' , '2016-11-13' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocVXL4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VXL4' , '2016-11-21' , '2016-12-18' , 5 , '0-0-3' )";
//
//
//        //OOP3 + OOP4
//        String insertKhoangThoiGianHocOOP3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP3' , '2016-10-17' , '2016-11-13' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocOOP3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP3' , '2016-10-14' , '2016-11-20' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocOOP3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP3' , '2016-11-21' , '2016-12-4' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocOOP3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP3' , '2016-12-5' , '2016-12-18' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocOOP3_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP3' , '2016-12-19' , '2016-12-25' , 5 , '0-2-5' )";
//
//        String insertKhoangThoiGianHocOOP4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP4' , '2016-10-17' , '2016-12-4' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocOOP4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP4' , '2016-12-5' , '2016-12-18' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocOOP4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'OOP4' , '2016-12-19' , '2016-12-25' , 5 , '0-1-3' )";
//
//
//        //QTM3 + QTM4
//        String insertKhoangThoiGianHocQTM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'QTM3' , '2016-10-17' , '2016-11-13' , 5 , '0-1-3' )";
//        String insertKhoangThoiGianHocQTM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'QTM3' , '2016-11-14' , '2016-11-20' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocQTM3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'QTM3' , '2016-11-21' , '2016-12-25' , 5 , '0-1-3' )";
//
//        String insertKhoangThoiGianHocQTM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'QTM4' , '2016-10-17' , '2016-11-13' , 5 , '0-1-3' )";
//        String insertKhoangThoiGianHocQTM4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'QTM4' , '2016-11-14' , '2016-11-20' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocQTM4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'QTM4' , '2016-11-21' , '2016-12-25' , 5 , '0-0-5' )";
//
//
//        //TA32 + TA33 + TA34 + TA37 + TA38
//        String insertKhoangThoiGianHocTA32_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA32' , '2016-8-1' , '2016-9-11' , 3 , '0-0-2' )";
//        String insertKhoangThoiGianHocTA32_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA32' , '2016-8-1' , '2016-9-11' , 4 , '0-0-4' )";
//        String insertKhoangThoiGianHocTA32_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA32' , '2016-9-12' , '2016-9-18' , 3 , '0-0-2' )";
//        String insertKhoangThoiGianHocTA32_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA32' , '2016-9-12' , '2016-9-18' , 4 , '0-0-5' )";
//        String insertKhoangThoiGianHocTA32_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA32' , '2016-9-19' , '2016-10-9' , 4 , '0-4-5' )";
//
//        String insertKhoangThoiGianHocTA33_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA33' , '2016-8-1' , '2016-9-4' , 5 , '0-1-2' )";
//        String insertKhoangThoiGianHocTA33_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA33' , '2016-9-5' , '2016-10-9' , 5 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocTA34_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA34' , '2016-8-1' , '2016-9-4' , 5 , '0-1-2' )";
//        String insertKhoangThoiGianHocTA34_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA33' , '2016-9-5' , '2016-10-9' , 5 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocTA37_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA33' , '2016-8-1' , '2016-9-11' , 3 , '0-0-2' )";
//        String insertKhoangThoiGianHocTA37_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA33' , '2016-8-1' , '2016-9-11' , 4 , '0-0-4' )";
//        String insertKhoangThoiGianHocTA37_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA33' , '2016-9-12' , '2016-9-18' , 4 , '0-2-5' )";
//        String insertKhoangThoiGianHocTA37_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA33' , '2016-9-19' , '2016-10-9' , 3 , '0-2-4' )";
//
//        String insertKhoangThoiGianHocTA38_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA33' , '2016-8-1' , '2016-9-4' , 5 , '0-1-2' )";
//        String insertKhoangThoiGianHocTA38_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TA33' , '2016-9-5' , '2016-10-9' , 5 , '0-0-2' )";
//
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDVM1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDVM1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDVM2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDVM2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDVM3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDVM4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDVM4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDVM4_3);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL1_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL2_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCTDL4_2);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCSTT1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCSTT2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCSTT3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCSTT4);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT1_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT1_7);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDTTT4_2);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD51_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD51_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD52_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD52_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD53_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD53_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD53_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD54_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD54_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD54_3);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVXL1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVXL2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVXL3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVXL3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVXL4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVXL4_2);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP3_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocOOP4_3);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM_N02_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocQTM4_3);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA31_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA32_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA32_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA32_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA32_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA32_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA33_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA33_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA34_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA34_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA36_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA37_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA37_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA37_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA37_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA38_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTA38_2);



//        /*******************************************KHOA 11**********************************/
//
//        //Insert dữ liệu cho bảng Môn học (KHÓA 11)
//        String insertMonHTVT = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('HTVT' , 'Hệ thống viễn thông' , null , 5 , 3 , 'trống trơn' , 'NAM32' , 4)";
//        String insertMonKTMT = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('KTMT' , 'Kiến trúc máy tính' , null , 3 , 2 , 'trống trơn' , 'NAM32' , 4)";
//        String insertMonKTDL = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('KTDL' , 'Kỹ thuật đo lường điện tử' , null , 5 , 2 , 'trống trơn' , 'NAM32' , 4)";
//        String insertMonKTTSL = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('KTTSL' , 'Kỹ thuật truyền số liệu' , null , 5 , 2 , 'trống trơn' , 'NAM32' , 4)";
//        String insertMonLTM = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('LTM' , 'Lập trình mạng' , null , 4 , 2 , 'trống trơn' , 'NAM32' , 4)";
//        String insertMonPTUD = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('PTUD' , 'Phát triển ứng dụng web' , null , 4 , 2 , 'trống trơn' , 'NAM32' , 4)";
//        String insertMonTKHT = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('TKHT' , 'Phân tích, thiết kế hệ thống' , null , 4 , 2 , 'trống trơn' , 'NAM32' , 4)";
//        String insertMonTACN = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('TACN' , 'Tiếng Anh chuyên ngành' , null , 3 , 3 , 'trống trơn' , 'NAM32' , 8)";
//        String insertMonTHS = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('THS' , 'Xử lý tín hiệu số' , null , 4 , 2 , 'trống trơn' , 'NAM32' , 4)";
//
//        sqLiteDatabase.execSQL(insertMonHTVT);
//        sqLiteDatabase.execSQL(insertMonKTMT);
//        sqLiteDatabase.execSQL(insertMonKTDL);
//        sqLiteDatabase.execSQL(insertMonKTTSL);
//        sqLiteDatabase.execSQL(insertMonLTM);
//        sqLiteDatabase.execSQL(insertMonTKHT);
//        sqLiteDatabase.execSQL(insertMonTACN);
//        sqLiteDatabase.execSQL(insertMonPTUD);
//        sqLiteDatabase.execSQL(insertMonTHS);
//
//
//
//
//
//
//        //insert dữ liệu vào table Lớp học (KHÓA 11)
//        String insertLopHTVT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'HTVT1' , 'L01' , '103_AT1 TA1' , 'No Name' , 'HTVT' , 'none')";
//        String insertLopHTVT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'HTVT2' , 'L02' , '103_AT1 TA1' , 'No Name' , 'HTVT' , 'none')";
//        String insertLopHTVT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'HTVT3' , 'L03' , '103_AT1 TA1' , 'No Name' , 'HTVT' , 'none')";
//        String insertLopHTVT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'HTVT4' , 'L04' , '304_TA2 TA2' , 'No Name' , 'HTVT' , 'none')";
//        String insertLopKTMT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTMT1' , 'L01' , '103_AT1 TA1' , 'Nguyễn Đào Trường' , 'KTMT' , 'NDT')";
//        String insertLopKTMT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTMT2' , 'L02' , '103_AT1 TA1' , 'Nguyễn Đào Trường' , 'KTMT' , 'NDT')";
//        String insertLopKTMT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTMT3' , 'L03' , '103_AT1 TA1' , 'Lê Đức Thuận' , 'KTMT' , 'LDT')";
//        String insertLopKTMT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTMT4' , 'L04' , '304_TA2 TA2' , 'Lê Đức Thuận' , 'KTMT' , 'LDT')";
//        String insertLopKTDL1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTDL1' , 'L01' , '103_AT1 TA1' , 'Tô Thị Tuyết Nhung' , 'KTDL' , 'TTTN')";
//        String insertLopKTDL2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTDL2' , 'L02' , '103_AT1 TA1' , 'Tô Thị Tuyết Nhung' , 'KTDL' , 'TTTN')";
//        String insertLopKTDL3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTDL3' , 'L03' , '103_AT1 TA1' , 'Tô Thị Tuyết Nhung' , 'KTDL' , 'TTTN')";
//        String insertLopKTDL4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTDL4' , 'L04' , '304_TA2 TA2' , 'Tô Thị Tuyết Nhung' , 'KTDL' , 'TTTN')";
//        String insertLopKTTSL1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTTSL1' , 'L01' , '103_AT1 TA1' , 'Dương Tuấn Đạt' , 'KTTSL' , 'DTD')";
//        String insertLopKTTSL2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTTSL2' , 'L02' , '103_AT1 TA1' , 'Dương Tuấn Đạt' , 'KTTSL' , 'DTD')";
//        String insertLopKTTSL3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTTSL3' , 'L03' , '103_AT1 TA1' , 'Dương Tuấn Đạt' , 'KTTSL' , 'DTD')";
//        String insertLopKTTSL4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTTSL4' , 'L04' , '304_TA2 TA2' , 'Dương Tuấn Đạt' , 'KTTSL' , 'DTD')";
//        String insertLopLTM1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTM1' , 'L01' , '103_AT1 TA1' , 'Lê Bá Cường' , 'LTM' , 'LBC')";
//        String insertLopLTM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTM2' , 'L02' , '103_AT1 TA1' , 'Lê Bá Cường' , 'LTM' , 'LBC')";
//        String insertLopLTM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTM3' , 'L03' , '103_AT1 TA1' , 'Lê Bá Cường' , 'LTM' , 'LBC')";
//        String insertLopLTM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTM4' , 'L04' , '304_TA2 TA2' , 'Lê Bá Cường' , 'LTM' , 'LBC')";
//        String insertLopPTUD1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'PTUD1' , 'L01' , '103_AT1 TA1' , 'Phạm Văn Hưởng' , 'PTUD' , 'PVH')";
//        String insertLopPTUD2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'PTUD2' , 'L02' , '103_AT1 TA1' , 'Lê Đức Thuận' , 'PTUD' , 'LDT')";
//        String insertLopPTUD3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'PTUD3' , 'L03' , '103_AT1 TA1' , 'Phạm Văn Hưởng' , 'PTUD' , 'PVH')";
//        String insertLopPTUD4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'PTUD4' , 'L04' , '304_TA2 TA2' , 'Phạm Văn Hưởng' , 'PTUD' , 'PVH')";
//        String insertLopTKHT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TKHT1' , 'L01' , '103_AT1 TA1' , 'Vũ Thị Đào' , 'TKHT' , 'VTD')";
//        String insertLopTKHT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TKHT2' , 'L02' , '103_AT1 TA1' , 'Vũ Thị Đào' , 'TKHT' , 'VTD')";
//        String insertLopTKHT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TKHT3' , 'L03' , '103_AT1 TA1' , 'Vũ Thị Đào' , 'TKHT' , 'VTD')";
//        String insertLopTKHT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TKHT4' , 'L04' , '304_TA2 TA2' , 'Vũ Thị Đào' , 'TKHT' , 'VTD')";
//        String insertLopTACN1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TACN1' , 'L01' , '103_AT1 TA1' , 'Nguyễn Thị Tý' , 'TACN' , 'NTT')";
//        String insertLopTACN2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TACN2' , 'L02' , '103_AT1 TA1' , 'Mai Thị Hảo' , 'TACN' , 'MTH')";
//        String insertLopTACN3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TACN3' , 'L03' , '103_AT1 TA1' , 'Phan Bích Thuận' , 'TACN' , 'PBT')";
//        String insertLopTACN4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TACN4' , 'L04' , '304_TA2 TA2' , 'Nguyễn Thị Minh Thu' , 'TACN' , 'NTMT')";
//        String insertLopTACN5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TACN5' , 'L05' , '201_TA2 TA2' , 'Phan Bích Thuận' , 'TACN' , 'PBT')";
//        String insertLopTACN6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TACN6' , 'L06' , '104_TA1 TA1' , 'No Name' , 'TACN' , 'none')";
//        String insertLopTACN7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TACN7' , 'L07' , '104_TA1 TA1' , 'Nguyễn Thị Tý' , 'TACN' , 'NTT')";
//        String insertLopTACN8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TACN8' , 'L08' , 'unknow location' , 'Mai Thị Hảo' , 'TACN' , 'MTH')";
//        String insertLopTHS1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'THS1' , 'L01' , '103_AT1 TA1' , 'Chu Thị Ngọc Quỳnh' , 'THS' , 'CTNQ')";
//        String insertLopTHS2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'THS2' , 'L02' , '103_AT1 TA1' , 'Trần Văn Cường' , 'THS' , 'TVC')";
//        String insertLopTHS3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'THS3' , 'L03' , '103_AT1 TA1' , 'Chu Thị Ngọc Quỳnh' , 'THS' , 'CTNQ')";
//        String insertLopTHS4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'THS4' , 'L04' , '304_TA2 TA2' , 'Trần Văn Cường' , 'THS' , 'TVC')";
//
//        sqLiteDatabase.execSQL(insertLopHTVT1); //
//        sqLiteDatabase.execSQL(insertLopHTVT2);
//        sqLiteDatabase.execSQL(insertLopHTVT3);
//        sqLiteDatabase.execSQL(insertLopHTVT4);
//        sqLiteDatabase.execSQL(insertLopKTMT1); //
//        sqLiteDatabase.execSQL(insertLopKTMT2);
//        sqLiteDatabase.execSQL(insertLopKTMT3);
//        sqLiteDatabase.execSQL(insertLopKTMT4);
//        sqLiteDatabase.execSQL(insertLopKTDL1); //
//        sqLiteDatabase.execSQL(insertLopKTDL2);
//        sqLiteDatabase.execSQL(insertLopKTDL3);
//        sqLiteDatabase.execSQL(insertLopKTDL4);
//        sqLiteDatabase.execSQL(insertLopKTTSL1);//
//        sqLiteDatabase.execSQL(insertLopKTTSL2);
//        sqLiteDatabase.execSQL(insertLopKTTSL3);
//        sqLiteDatabase.execSQL(insertLopKTTSL4);
//        sqLiteDatabase.execSQL(insertLopLTM1);  //
//        sqLiteDatabase.execSQL(insertLopLTM2);
//        sqLiteDatabase.execSQL(insertLopLTM3);
//        sqLiteDatabase.execSQL(insertLopLTM4);
//        sqLiteDatabase.execSQL(insertLopTKHT1); //
//        sqLiteDatabase.execSQL(insertLopTKHT2);
//        sqLiteDatabase.execSQL(insertLopTKHT3);
//        sqLiteDatabase.execSQL(insertLopTKHT4);
//        sqLiteDatabase.execSQL(insertLopTACN1); //
//        sqLiteDatabase.execSQL(insertLopTACN2);
//        sqLiteDatabase.execSQL(insertLopTACN3);
//        sqLiteDatabase.execSQL(insertLopTACN4);
//        sqLiteDatabase.execSQL(insertLopTACN5);
//        sqLiteDatabase.execSQL(insertLopTACN6);
//        sqLiteDatabase.execSQL(insertLopTACN7);
//        sqLiteDatabase.execSQL(insertLopTACN8);
//        sqLiteDatabase.execSQL(insertLopPTUD1); //
//        sqLiteDatabase.execSQL(insertLopPTUD2);
//        sqLiteDatabase.execSQL(insertLopPTUD3);
//        sqLiteDatabase.execSQL(insertLopPTUD4);
//        sqLiteDatabase.execSQL(insertLopTHS1);  //
//        sqLiteDatabase.execSQL(insertLopTHS2);
//        sqLiteDatabase.execSQL(insertLopTHS3);
//        sqLiteDatabase.execSQL(insertLopTHS4);
//
//
//
//
//        //insert dữ liệu vào table KHOANGTHOIGIANHOC cho khóa 11
//        //HTVT
//        String insertKhoangThoiGianHocHTVT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT1' , '2017-4-17' , '2017-4-30' , 1 , '0-1-3' )";
//        String insertKhoangThoiGianHocHTVT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT1' , '2017-5-1' , '2017-5-7' , 1 , '0-0-3' )";
//        String insertKhoangThoiGianHocHTVT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT1' , '2017-5-8' , '2017-6-18' , 1 , '0-1-3' )";
//
//        String insertKhoangThoiGianHocHTVT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT2' , '2017-4-17' , '2017-4-30' , 3 , '0-1-3' )";
//        String insertKhoangThoiGianHocHTVT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT2' , '2017-5-1' , '2017-5-7' , 3 , '0-0-3' )";
//        String insertKhoangThoiGianHocHTVT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT2' , '2017-5-8' , '2017-6-18' , 3 , '0-1-3' )";
//
//        String insertKhoangThoiGianHocHTVT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT3' , '2017-4-17' , '2017-4-30' , 5 , '0-1-3' )";
//        String insertKhoangThoiGianHocHTVT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT3' , '2017-5-8' , '2017-5-28' , 5 , '0-1-3' )";
//        String insertKhoangThoiGianHocHTVT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT3' , '2017-5-29' , '2017-6-4' , 5 , '0-1-3' )";
//        String insertKhoangThoiGianHocHTVT3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT3' , '2017-6-5' , '2017-6-11' , 6 , '0-0-1' )";
//
//        String insertKhoangThoiGianHocHTVT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT4' , '2017-4-17' , '2017-4-30' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocHTVT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT4' , '2017-5-1' , '2017-5-7' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocHTVT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT4' , '2017-5-8' , '2017-6-4' , 5 , '0-2-4' )";
//        String insertKhoangThoiGianHocHTVT4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT4' , '2017-6-5' , '2017-6-11' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocHTVT4_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'HTVT4' , '2017-6-12' , '2017-6-18' , 5 , '0-0-1' )";
//
//
//        //KTMT
//        String insertKhoangThoiGianHocKTMT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT1' , '2017-4-17' , '2017-4-30' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocKTMT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT1' , '2017-5-1' , '2017-5-7' , 1 , '0-0-4' )";
//        String insertKhoangThoiGianHocKTMT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT1' , '2017-5-8' , '2017-6-4' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocKTMT1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT1' , '2017-6-5' , '2017-6-18' , 1 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocKTMT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT2' , '2017-4-17' , '2017-4-30' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocKTMT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT2' , '2017-5-1' , '2017-5-7' , 3 , '0-0-4' )";
//        String insertKhoangThoiGianHocKTMT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT2' , '2017-5-8' , '2017-6-4' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocKTMT2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT2' , '2017-6-5' , '2017-6-18' , 3 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocKTMT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT3' , '2017-4-17' , '2017-4-30' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocKTMT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT3' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocKTMT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT3' , '2017-5-8' , '2017-6-4' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocKTMT3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT3' , '2017-6-5' , '2017-6-18' , 5 , '0-2-4' )";
//
//        String insertKhoangThoiGianHocKTMT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT4' , '2017-4-17' , '2017-4-23' , 5 , '0-1-4' )";
//        String insertKhoangThoiGianHocKTMT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT4' , '2017-4-24' , '2017-4-30' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocKTMT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT4' , '2017-5-8' , '2017-6-4' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocKTMT4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTMT4' , '2017-6-5' , '2017-6-18' , 5 , '0-3-5' )";
//
//
//        //KTDL
//        String insertKhoangThoiGianHocKTDL1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL1' , '2017-2-6' , '2017-2-12' , 1 , '1-3-5' )";
//        String insertKhoangThoiGianHocKTDL1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL1' , '2017-2-13' , '2017-2-19' , 1 , '0-1-3' )";
//        String insertKhoangThoiGianHocKTDL1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL1' , '2017-2-20' , '2017-2-26' , 1 , '0-1-3' )";
//        String insertKhoangThoiGianHocKTDL1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL1' , '2017-2-27' , '2017-4-2' , 1 , '0-0-1' )";
//
//        String insertKhoangThoiGianHocKTDL2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL2' , '2017-2-6' , '2017-2-19' , 3 , '0-1-5' )";
//        String insertKhoangThoiGianHocKTDL2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL2' , '2017-2-20' , '2017-2-26' , 3 , '0-1-5' )";
//        String insertKhoangThoiGianHocKTDL2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL2' , '2017-2-20' , '2017-2-26' , 4 , '0-0-3' )";
//        String insertKhoangThoiGianHocKTDL2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL2' , '2017-2-27' , '2017-4-2' , 3 , '0-0-1' )";
//
//        String insertKhoangThoiGianHocKTDL3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL3' , '2017-1-9' , '2017-1-22' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocKTDL3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL3' , '2017-2-6' , '2017-3-12' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocKTDL3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL3' , '2017-3-13' , '2017-3-19' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocKTDL3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL3' , '2017-3-20' , '2017-3-26' , 6 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocKTDL4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL4' , '2017-2-6' , '2017-3-12' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocKTDL4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL4' , '2017-3-13' , '2017-3-19' , 5 , '0-2-3' )";
//        String insertKhoangThoiGianHocKTDL4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTDL4' , '2017-3-20' , '2017-4-2' , 5 , '0-0-4' )";
//
//
//        //KTTSL
//        String insertKhoangThoiGianHocKTTSL1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL1' , '2017-1-2' , '2017-1-22' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocKTTSL1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL1' , '2017-2-6' , '2017-3-26' , 1 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocKTTSL2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL2' , '2017-1-2' , '2017-1-8' , 3 , '0-0-5' )";
//        String insertKhoangThoiGianHocKTTSL2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL2' , '2017-1-9' , '2017-1-22' , 3 , '0-0-1' )";
//        String insertKhoangThoiGianHocKTTSL2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL2' , '2017-2-6' , '2017-3-26' , 4 , '0-0-1' )";
//
//        String insertKhoangThoiGianHocKTTSL3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL3' , '2017-2-6' , '2017-3-19' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocKTTSL3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL3' , '2017-3-20' , '2017-3-26' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocKTTSL3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL3' , '2017-3-27' , '2017-4-2' , 5 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocKTTSL4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL4' , '2017-1-9' , '2017-1-15' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocKTTSL4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL4' , '2017-1-16' , '2017-1-22' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocKTTSL4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL4' , '2017-2-6' , '2017-3-5' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocKTTSL4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTTSL4' , '2017-3-6' , '2017-3-12' , 5 , '0-0-3' )";
//
//
//        //LTM
//        String insertKhoangThoiGianHocLTM1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM1' , '2017-4-17' , '2017-4-30' , 2 , '0-1-5' )";
//        String insertKhoangThoiGianHocLTM1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM1' , '2017-5-1' , '2017-5-7' , 1 , '0-0-5' )";
//        String insertKhoangThoiGianHocLTM1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM1' , '2017-5-8' , '2017-6-4' , 2 , '0-1-5' )";
//        String insertKhoangThoiGianHocLTM1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM1' , '2017-6-5' , '2017-6-18' , 2 , '0-0-5' )";
//
//        String insertKhoangThoiGianHocLTM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM2' , '2017-4-17' , '2017-4-30' , 4 , '0-0-4' )";
//        String insertKhoangThoiGianHocLTM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM2' , '2017-4-17' , '2017-4-30' , 3 , '0-0-5' )";
//        String insertKhoangThoiGianHocLTM2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM2' , '2017-5-1' , '2017-5-7' , 3 , '0-0-5' )";
//        String insertKhoangThoiGianHocLTM2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM2' , '2017-5-8' , '2017-6-4' , 4 , '0-0-4' )";
//        String insertKhoangThoiGianHocLTM2_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM2' , '2017-5-8' , '2017-6-4' , 3 , '0-0-5' )";
//        String insertKhoangThoiGianHocLTM2_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM2' , '2017-6-5' , '2017-6-18' , 3 , '0-0-5' )";
//
//        String insertKhoangThoiGianHocLTM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM3' , '2017-4-17' , '2017-6-4' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocLTM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM3' , '2017-6-5' , '2017-6-18' , 5 , '0-3-5' )";
//
//        String insertKhoangThoiGianHocLTM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM4' , '2017-4-17' , '2017-6-4' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocLTM4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTM4' , '2017-6-5' , '2017-6-18' , 5 , '0-2-4' )";
//
//
//        //TKHT
//        String insertKhoangThoiGianHocTKHT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT1' , '2017-1-2' , '2017-1-8' , 1 , '0-0-4' )";
//        String insertKhoangThoiGianHocTKHT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT1' , '2017-1-9' , '2017-1-22' , 2 , '0-0-1' )";
//        String insertKhoangThoiGianHocTKHT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT1' , '2017-2-6' , '2017-2-26' , 1 , '0-0-4' )";
//        String insertKhoangThoiGianHocTKHT1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT1' , '2017-2-27' , '2017-3-26' , 2 , '0-0-1' )";
//        String insertKhoangThoiGianHocTKHT1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT1' , '2017-2-27' , '2017-3-26' , 1 , '0-0-4' )";
//        String insertKhoangThoiGianHocTKHT1_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT1' , '2017-3-27' , '2017-4-2' , 1 , '0-0-4' )";
//
//        String insertKhoangThoiGianHocTKHT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT2' , '2017-1-2' , '2017-1-22' , 3 , '0-0-2' )";
//        String insertKhoangThoiGianHocTKHT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT2' , '2017-2-6' , '2017-2-26' , 3 , '0-0-2' )";
//        String insertKhoangThoiGianHocTKHT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT2' , '2017-2-27' , '2017-3-26' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocTKHT2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT2' , '2017-3-27' , '2017-4-2' , 3 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocTKHT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT3' , '2017-1-2' , '2017-1-22' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocTKHT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT3' , '2017-2-6' , '2017-4-2' , 5 , '0-0-3' )";
//
//        String insertKhoangThoiGianHocTKHT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT4' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocTKHT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT4' , '2017-2-6' , '2017-3-19' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocTKHT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT4' , '2017-3-20' , '2017-3-26' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocTKHT4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TKHT4' , '2017-3-27' , '2017-4-2' , 5 , '0-0-2' )";
//
//
//        //TACN
//        String insertKhoangThoiGianHocTACN1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN1' , '2017-1-2' , '2017-1-8' , 2 , '0-0-5' )";
//        String insertKhoangThoiGianHocTACN1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN1' , '2017-1-9' , '2017-1-15' , 2 , '0-0-5' )";
//        String insertKhoangThoiGianHocTACN1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN1' , '2017-1-16' , '2017-1-22' , 1 , '0-0-4' )";
//        String insertKhoangThoiGianHocTACN1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN1' , '2017-2-6' , '2017-2-19' , 2 , '0-2-5' )";
//        String insertKhoangThoiGianHocTACN1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN1' , '2017-2-20' , '2017-3-5' , 2 , '2-4-5' )";
//        String insertKhoangThoiGianHocTACN1_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN1' , '2017-3-6' , '2017-3-12' , 2 , '0-2-5' )";
//        String insertKhoangThoiGianHocTACN1_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN1' , '2017-3-13' , '2017-3-26' , 2 , '0-0-2' )";
//        String insertKhoangThoiGianHocTACN1_8 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN1' , '2017-3-27' , '2017-4-2' , 2 , '2-4-5' )";
//
//        String insertKhoangThoiGianHocTACN2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN2' , '2017-1-2' , '2017-1-22' , 3 , '0-0-4' )";
//        String insertKhoangThoiGianHocTACN2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN2' , '2017-2-6' , '2017-2-26' , 4 , '0-2-5' )";
//        String insertKhoangThoiGianHocTACN2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN2' , '2017-2-6' , '2017-2-26' , 3 , '0-0-4' )";
//        String insertKhoangThoiGianHocTACN2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN2' , '2017-2-27' , '2017-3-19' , 4 , '0-2-4' )";
//        String insertKhoangThoiGianHocTACN2_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN2' , '2017-3-20' , '2017-4-2' , 4 , '0-0-4' )";
//
//        String insertKhoangThoiGianHocTACN3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN3' , '2017-1-2' , '2017-1-15' , 5 , '0-4-5' )";
//        String insertKhoangThoiGianHocTACN3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN3' , '2017-1-16' , '2017-1-22' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocTACN3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN3' , '2017-2-6' , '2017-3-19' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocTACN3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN3' , '2017-3-20' , '2017-4-2' , 5 , '0-1-5' )";
//
//        String insertKhoangThoiGianHocTACN4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN4' , '2017-1-2' , '2017-1-15' , 5 , '0-3-4' )";
//        String insertKhoangThoiGianHocTACN4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN4' , '2017-1-16' , '2017-1-22' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocTACN4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN4' , '2017-2-6' , '2017-3-19' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocTACN4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN4' , '2017-3-20' , '2017-2-4' , 5 , '0-1-3' )";
//
//        String insertKhoangThoiGianHocTACN5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN5' , '2017-1-2' , '2017-1-15' , 2 , '0-0-5' )";
//        String insertKhoangThoiGianHocTACN5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN5' , '2017-1-16' , '2017-1-22' , 1 , '0-0-4' )";
//        String insertKhoangThoiGianHocTACN5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN5' , '2017-2-6' , '2017-2-19' , 2 , '0-2-5' )";
//        String insertKhoangThoiGianHocTACN5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN5' , '2017-2-20' , '2017-3-5' , 2 , '2-4-5' )";
//        String insertKhoangThoiGianHocTACN5_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN5' , '2017-3-6' , '2017-3-12' , 2 , '0-2-5' )";
//        String insertKhoangThoiGianHocTACN5_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN5' , '2017-3-13' , '2017-3-26' , 2 , '0-0-2' )";
//        String insertKhoangThoiGianHocTACN5_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN5' , '2017-3-27' , '2017-4-2' , 2 , '2-4-5' )";
//
//        String insertKhoangThoiGianHocTACN6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN6' , '2017-1-2' , '2017-1-22' , 3 , '0-0-4' )";
//        String insertKhoangThoiGianHocTACN6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN6' , '2017-2-6' , '2017-2-19' , 4 , '0-2-5' )";
//        String insertKhoangThoiGianHocTACN6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN6' , '2017-2-6' , '2017-2-19' , 3 , '0-0-4' )";
//        String insertKhoangThoiGianHocTACN6_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN6' , '2017-2-20' , '2017-2-26' , 4 , '0-2-5' )";
//        String insertKhoangThoiGianHocTACN6_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN6' , '2017-2-20' , '2017-2-26' , 3 , '0-0-4' )";
//        String insertKhoangThoiGianHocTACN6_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN6' , '2017-2-27' , '2017-3-19' , 4 , '0-2-4' )";
//        String insertKhoangThoiGianHocTACN6_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN6' , '2017-3-20' , '2017-4-2' , 4 , '0-0-4' )";
//
//        String insertKhoangThoiGianHocTACN7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN7' , '2017-1-2' , '2017-1-15' , 5 , '0-4-5' )";
//        String insertKhoangThoiGianHocTACN7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN7' , '2017-1-16' , '2017-1-22' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocTACN7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN7' , '2017-2-6' , '2017-3-19' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocTACN7_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN7' , '2017-3-20' , '2017-4-2' , 5 , '0-1-5' )";
//
//        String insertKhoangThoiGianHocTACN8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN8' , '2017-1-2' , '2017-1-15' , 5 , '0-3-4' )";
//        String insertKhoangThoiGianHocTACN8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN8' , '2017-1-16' , '2017-1-22' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocTACN8_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN8' , '2017-2-6' , '2017-3-19' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocTACN8_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TACN8' , '2017-3-20' , '2017-4-2' , 5 , '0-1-3' )";
//
//
//        //PTUD
//        String insertKhoangThoiGianHocPTUD1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD1' , '2017-1-2' , '2017-1-22' , 1 , '0-0-3' )";
//        String insertKhoangThoiGianHocPTUD1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD1' , '2017-2-6' , '2017-2-26' , 2 , '0-0-3' )";
//        String insertKhoangThoiGianHocPTUD1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD1' , '2017-2-27' , '2017-3-26' , 1 , '0-3-5' )";
//        String insertKhoangThoiGianHocPTUD1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD1' , '2017-3-27' , '2017-4-2' , 1 , '0-0-3' )";
//
//        String insertKhoangThoiGianHocPTUD2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD2' , '2017-1-2' , '2017-1-22' , 3 , '0-0-3' )";
//        String insertKhoangThoiGianHocPTUD2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD2' , '2017-2-6' , '2017-2-26' , 3 , '0-0-3' )";
//        String insertKhoangThoiGianHocPTUD2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD2' , '2017-2-27' , '2017-3-26' , 3 , '0-3-5' )";
//        String insertKhoangThoiGianHocPTUD2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD2' , '2017-3-27' , '2017-4-2' , 3 , '0-0-3' )";
//
//        String insertKhoangThoiGianHocPTUD3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD3' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocPTUD3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD3' , '2017-2-6' , '2017-4-2' , 5 , '0-0-4' )";
//
//        String insertKhoangThoiGianHocPTUD4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD4' , '2017-1-2' , '2017-1-15' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocPTUD4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD4' , '2017-1-16' , '2017-1-22' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocPTUD4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTUD4' , '2017-2-6' , '2017-4-2' , 5 , '0-0-5' )";
//
//
//        //THS
//        String insertKhoangThoiGianHocTHS1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'THS1' , '2017-4-17' , '2017-4-30' , 2 , '0-0-2' )";
//        String insertKhoangThoiGianHocTHS1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'THS1' , '2017-4-17' , '2017-4-23' , 1 , '0-0-5' )";
//        String insertKhoangThoiGianHocTHS1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'THS1' , '2017-5-1' , '2017-5-1' , 2 , '0-0-3' )";
//        String insertKhoangThoiGianHocTHS1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'THS1' , '2017-5-8' , '2017-6-18' , 2 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocTHS2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'THS2' , '2017-4-17' , '2017-4-23' , 4 , '0-1-3' )";
//        String insertKhoangThoiGianHocTHS2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'THS2' , '2017-4-24' , '2017-6-18' , 4 , '0-0-3' )";
//
//        String insertKhoangThoiGianHocTHS3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'THS3' , '2017-4-17' , '2017-6-4' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocTHS3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'THS3' , '2017-6-5' , '2017-6-11' , 5 , '0-0-1' )";
//
//        String insertKhoangThoiGianHocTHS4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'THS4' , '2017-4-17' , '2017-6-4' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocTHS4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'THS4' , '2017-6-5' , '2017-6-11' , 6 , '0-0-1' )";
//
//
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT4_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT4_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocHTVT4_5);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT4_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTMT4_4);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTDL4_3);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL4_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTTSL4_4);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM2_6);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTM4_2);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT1_6);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT4_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTKHT4_4);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_6);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_7);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN1_8);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN2_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN4_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN4_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_6);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN5_7);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_6);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN6_7);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN7_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN7_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN7_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN7_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN8_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN8_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN8_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTACN8_4);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTUD4_3);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTHS4_2);
//
//
//
//        /*******************************************END KHOA 11**********************************/
//
//
//
//
//        /*******************************************KHOA 13**********************************/
//
//        //Insert dữ liệu cho bảng Môn học (KHÓA 13)
//        String insertMonDCSVN = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('DCSVN' , 'Đường lối cách mạng của Đảng CSVN' , null , 3 , 3 , 'trống trơn' , 'NAM12' , 9)";
//        String insertMonTD2 = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('TD2' , 'Giáo dục thể chất 2' , null , 1 , 1 , 'trống trơn' , 'NAM12' , 9)";
//        String insertMonKNM = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('KNM' , 'Kỹ năng mềm' , null , 2 , 2 , 'trống trơn' , 'NAM12' , 9)";
//        String insertMonLTC = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('LTC' , 'Lập trình căn bản' , null , 3 , 3 , 'trống trơn' , 'NAM12' , 9)";
//        String insertMonCNMLN2 = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('CNMLN2' , 'Những NLCB của CNMLN (HP2)' , null , 2 , 3 , 'trống trơn' , 'NAM12' , 9)";
//        String insertMonTOAN2 = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('TOAN2' , 'Toán cao cấp A2' , null , 3 , 3 , 'trống trơn' , 'NAM12' , 9)";
//        String insertMonVL2 = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('VL1' , 'Vật lý đại cương A1' , null , 3 , 3 , 'trống trơn' , 'NAM12' , 9)";
//
//        sqLiteDatabase.execSQL(insertMonDCSVN);
//        sqLiteDatabase.execSQL(insertMonTD2);
//        sqLiteDatabase.execSQL(insertMonKNM);
//        sqLiteDatabase.execSQL(insertMonLTC);
//        sqLiteDatabase.execSQL(insertMonCNMLN2);
//        sqLiteDatabase.execSQL(insertMonTOAN2);
//        sqLiteDatabase.execSQL(insertMonVL2);
//
//        //insert dữ liệu vào table Lớp học (KHÓA 13)
//        String insertLopDCSVN1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DCSVN1' , 'L01' , '103_TA2 TA1' , 'Cù Thị Tặng' , 'DCSVN' , 'CTT')";
//        String insertLopDCSVN2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DCSVN2' , 'L02' , '103_TA2 TA1' , 'No Name' , 'DCSVN' , 'none')";
//        String insertLopDCSVN3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DCSVN3' , 'L03' , '104_TA2 TA2' , 'No Name' , 'DCSVN' , 'none')";
//        String insertLopDCSVN4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DCSVN4' , 'L04' , '104_TA2 TA2' , 'Trần Thị Thuyết' , 'DCSVN' , 'TTT')";
//        String insertLopDCSVN5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DCSVN5' , 'L05' , '202_TA2 TA2' , 'Trần Thị Thuyết' , 'DCSVN' , 'TTT')";
//        String insertLopDCSVN6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DCSVN6' , 'L06' , '103_TA2 TA1' , 'Nguyễn Việt Cường' , 'DCSVN' , 'NVC')";
//        String insertLopDCSVN7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DCSVN7' , 'L07' , '104_TA2 TA2' , 'No Name' , 'DCSVN' , 'none')";
//        String insertLopDCSVN8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DCSVN8' , 'L08' , '601_TA3 TA3' , 'No Name' , 'DCSVN' , 'none')";
//        String insertLopDCSVN9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DCSVN9' , 'L09' , '601_TA3 TA3' , 'Cù Thị Tặng' , 'DCSVN' , 'CTT')";
//
//        String insertLopTD2_1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TD2_1' , 'L01' , 'Sân bãi' , 'Đỗ Văn Hiếu' , 'TD2' , 'DVH1')";
//        String insertLopTD2_2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TD2_2' , 'L02' , 'Sân bãi' , 'Đỗ Văn Hiếu' , 'TD2' , 'DVH1')";
//        String insertLopTD2_3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TD2_3' , 'L03' , 'Sân bãi' , 'Đỗ Văn Hiếu' , 'TD2' , 'DVH1')";
//        String insertLopTD2_4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TD2_4' , 'L04' , 'Sân bãi' , 'Đỗ Văn Hiếu' , 'TD2' , 'DVH1')";
//        String insertLopTD2_5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TD2_5' , 'L05' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD2' , 'HMH')";
//        String insertLopTD2_6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TD2_6' , 'L06' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD2' , 'HMH')";
//        String insertLopTD2_7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TD2_7' , 'L07' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD2' , 'HMH')";
//        String insertLopTD2_8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TD2_8' , 'L08' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD2' , 'HMH')";
//        String insertLopTD2_9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TD2_9' , 'L09' , 'Sân bãi' , 'Hà Mai Hoa' , 'TD2' , 'HMH')";
//
//        String insertLopKNM1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KNM1' , 'L01' , '103_TA2 TA1' , 'Lê Tiến Phương' , 'KNM' , 'LTP')";
//        String insertLopKNM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KNM2' , 'L02' , '103_TA2 TA1' , 'Nguyễn Thị Hồng Phương' , 'KNM' , 'NTHP')";
//        String insertLopKNM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KNM3' , 'L03' , '104_TA2 TA2' , 'Nguyễn Thị Hồng Phương' , 'KNM' , 'NTHP')";
//        String insertLopKNM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KNM4' , 'L04' , '104_TA2 TA2' , 'Nguyễn Thị Hồng Phương' , 'KNM' , 'NTHP')";
//        String insertLopKNM5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KNM5' , 'L05' , '202_TA2 TA2' , 'Nguyễn Anh Thắng' , 'KNM' , 'NAT')";
//        String insertLopKNM6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KNM6' , 'L06' , '103_TA2 TA1' , 'Nguyễn Anh Thắng' , 'KNM' , 'NAT')";
//        String insertLopKNM7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KNM7' , 'L07' , '104_TA2 TA2' , 'Nguyễn Anh Thắng' , 'KNM' , 'NAT')";
//        String insertLopKNM8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KNM8' , 'L08' , '601_TA3 TA3' , 'Lê Tiến Phương' , 'KNM' , 'LTP')";
//        String insertLopKNM9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KNM9' , 'L09' , '601_TA3 TA3' , 'Nguyễn Thị Hồng Phương' , 'KNM' , 'NTHP')";
//
//        String insertLopLTC1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTC1' , 'L01' , '103_TA2 TA1' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
//        String insertLopLTC2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTC2' , 'L02' , '103_TA2 TA1' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
//        String insertLopLTC3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTC3' , 'L03' , '104_TA2 TA2' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
//        String insertLopLTC4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTC4' , 'L04' , '104_TA2 TA2' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
//        String insertLopLTC5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTC5' , 'L05' , '202_TA2 TA2' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
//        String insertLopLTC6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTC6' , 'L06' , '103_TA2 TA1' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
//        String insertLopLTC7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTC7' , 'L07' , '104_TA2 TA2' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
//        String insertLopLTC8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTC8' , 'L08' , '601_TA3 TA3' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
//        String insertLopLTC9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'LTC9' , 'L09' , '601_TA3 TA3' , 'Lê Đức Thuận' , 'LTC' , 'LDT')";
//
//        String insertLopCNMLN2_1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'CNMLN2_1' , 'L01' , '103_TA2 TA1' , 'No Name' , 'CNMLN2' , 'none')";
//        String insertLopCNMLN2_2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'CNMLN2_2' , 'L02' , '103_TA2 TA1' , 'No Name' , 'CNMLN2' , 'none')";
//        String insertLopCNMLN2_3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'CNMLN2_3' , 'L03' , '104_TA2 TA2' , 'No Name' , 'CNMLN2' , 'none')";
//        String insertLopCNMLN2_4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'CNMLN2_4' , 'L04' , '104_TA2 TA2' , 'Đặng Thị Thu Hiền' , 'CNMLN2' , 'DTTH')";
//        String insertLopCNMLN2_5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'CNMLN2_5' , 'L05' , '202_TA2 TA2' , 'Lê Tiến Phương' , 'CNMLN2' , 'LTP')";
//        String insertLopCNMLN2_6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'CNMLN2_6' , 'L06' , '103_TA2 TA1' , 'Lê Tiến Phương' , 'CNMLN2' , 'LTP')";
//        String insertLopCNMLN2_7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'CNMLN2_7' , 'L07' , '104_TA2 TA2' , 'Lê Tiến Phương' , 'CNMLN2' , 'LTP')";
//        String insertLopCNMLN2_8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'CNMLN2_8' , 'L08' , '601_TA3 TA3' , 'No Name' , 'CNMLN2' , 'none')";
//        String insertLopCNMLN2_9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'CNMLN2_9' , 'L09' , '601_TA3 TA3' , 'Đặng Thị Thu Hiền' , 'CNMLN2' , 'DTTH')";
//
//        String insertLopTOAN2_1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TOAN2_1' , 'L01' , '103_TA2 TA1' , 'Nguyễn Văn Sơn' , 'TOAN2' , 'NVS')";
//        String insertLopTOAN2_2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TOAN2_2' , 'L02' , '103_TA2 TA1' , 'Nguyễn Văn Sơn' , 'TOAN2' , 'NVS')";
//        String insertLopTOAN2_3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TOAN2_3' , 'L03' , '104_TA2 TA2' , 'Thái Thị Kim Dung' , 'TOAN2' , 'TTKD')";
//        String insertLopTOAN2_4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TOAN2_4' , 'L04' , '104_TA2 TA2' , 'Trần Thị Phương Thảo' , 'TOAN2' , 'TTPT')";
//        String insertLopTOAN2_5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TOAN2_5' , 'L05' , '202_TA2 TA2' , 'Thái Thị Kim Dung' , 'TOAN2' , 'TTKD')";
//        String insertLopTOAN2_6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TOAN2_6' , 'L06' , '103_TA2 TA1' , 'No Name' , 'TOAN2' , 'none')";
//        String insertLopTOAN2_7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TOAN2_7' , 'L07' , '104_TA2 TA2' , 'No Name' , 'TOAN2' , 'none')";
//        String insertLopTOAN2_8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TOAN2_8' , 'L08' , '601_TA3 TA3' , 'No Name' , 'TOAN2' , 'none')";
//        String insertLopTOAN2_9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TOAN2_9' , 'L09' , '601_TA3 TA3' , 'No Name' , 'TOAN2' , 'none')";
//
//        String insertLopVL1_1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'VL1_1' , 'L01' , '103_TA2 TA1' , 'Nguyễn Duy Phương' , 'VL1' , 'NDP')";
//        String insertLopVL1_2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'VL1_2' , 'L02' , '103_TA2 TA1' , 'Đỗ Minh Nam' , 'VL1' , 'DMN')";
//        String insertLopVL1_3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'VL1_3' , 'L03' , '104_TA2 TA2' , 'Nguyễn Thị Thanh Vân' , 'VL1' , 'NTTV')";
//        String insertLopVL1_4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'VL1_4' , 'L04' , '104_TA2 TA2' , 'Dương Thị Hồng Gấm' , 'VL1' , 'DTHG')";
//        String insertLopVL1_5 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'VL1_5' , 'L05' , '202_TA2 TA2' , 'Dương Thị Hồng Gấm' , 'VL1' , 'DTHG')";
//        String insertLopVL1_6 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'VL1_6' , 'L06' , '103_TA2 TA1' , 'Đào Xuân Dương' , 'VL1' , 'DXD')";
//        String insertLopVL1_7 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'VL1_7' , 'L07' , '104_TA2 TA2' , 'Đào Xuân Dương' , 'VL1' , 'DXD')";
//        String insertLopVL1_8 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'VL1_8' , 'L08' , '601_TA3 TA3' , 'No Name' , 'VL1' , 'none')";
//        String insertLopVL1_9 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'VL1_9' , 'L09' , '601_TA3 TA3' , 'Đỗ Minh Nam' , 'VL1' , 'DMN')";
//
//        sqLiteDatabase.execSQL(insertLopDCSVN1);    //
//        sqLiteDatabase.execSQL(insertLopDCSVN2);
//        sqLiteDatabase.execSQL(insertLopDCSVN3);
//        sqLiteDatabase.execSQL(insertLopDCSVN4);
//        sqLiteDatabase.execSQL(insertLopDCSVN5);
//        sqLiteDatabase.execSQL(insertLopDCSVN6);
//        sqLiteDatabase.execSQL(insertLopDCSVN7);
//        sqLiteDatabase.execSQL(insertLopDCSVN8);
//        sqLiteDatabase.execSQL(insertLopDCSVN9);
//        sqLiteDatabase.execSQL(insertLopTD2_1);     //
//        sqLiteDatabase.execSQL(insertLopTD2_2);
//        sqLiteDatabase.execSQL(insertLopTD2_3);
//        sqLiteDatabase.execSQL(insertLopTD2_4);
//        sqLiteDatabase.execSQL(insertLopTD2_5);
//        sqLiteDatabase.execSQL(insertLopTD2_6);
//        sqLiteDatabase.execSQL(insertLopTD2_7);
//        sqLiteDatabase.execSQL(insertLopTD2_8);
//        sqLiteDatabase.execSQL(insertLopTD2_9);
//        sqLiteDatabase.execSQL(insertLopKNM1);      //
//        sqLiteDatabase.execSQL(insertLopKNM2);
//        sqLiteDatabase.execSQL(insertLopKNM3);
//        sqLiteDatabase.execSQL(insertLopKNM4);
//        sqLiteDatabase.execSQL(insertLopKNM5);
//        sqLiteDatabase.execSQL(insertLopKNM6);
//        sqLiteDatabase.execSQL(insertLopKNM7);
//        sqLiteDatabase.execSQL(insertLopKNM8);
//        sqLiteDatabase.execSQL(insertLopKNM9);
//        sqLiteDatabase.execSQL(insertLopLTC1);        //
//        sqLiteDatabase.execSQL(insertLopLTC2);
//        sqLiteDatabase.execSQL(insertLopLTC3);
//        sqLiteDatabase.execSQL(insertLopLTC4);
//        sqLiteDatabase.execSQL(insertLopLTC5);
//        sqLiteDatabase.execSQL(insertLopLTC6);
//        sqLiteDatabase.execSQL(insertLopLTC7);
//        sqLiteDatabase.execSQL(insertLopLTC8);
//        sqLiteDatabase.execSQL(insertLopLTC9);
//        sqLiteDatabase.execSQL(insertLopCNMLN2_1);  //
//        sqLiteDatabase.execSQL(insertLopCNMLN2_2);
//        sqLiteDatabase.execSQL(insertLopCNMLN2_3);
//        sqLiteDatabase.execSQL(insertLopCNMLN2_4);
//        sqLiteDatabase.execSQL(insertLopCNMLN2_5);
//        sqLiteDatabase.execSQL(insertLopCNMLN2_6);
//        sqLiteDatabase.execSQL(insertLopCNMLN2_7);
//        sqLiteDatabase.execSQL(insertLopCNMLN2_8);
//        sqLiteDatabase.execSQL(insertLopCNMLN2_9);
//        sqLiteDatabase.execSQL(insertLopTOAN2_1);   //
//        sqLiteDatabase.execSQL(insertLopTOAN2_2);
//        sqLiteDatabase.execSQL(insertLopTOAN2_3);
//        sqLiteDatabase.execSQL(insertLopTOAN2_4);
//        sqLiteDatabase.execSQL(insertLopTOAN2_5);
//        sqLiteDatabase.execSQL(insertLopTOAN2_6);
//        sqLiteDatabase.execSQL(insertLopTOAN2_7);
//        sqLiteDatabase.execSQL(insertLopTOAN2_8);
//        sqLiteDatabase.execSQL(insertLopTOAN2_9);
//        sqLiteDatabase.execSQL(insertLopVL1_1);     //
//        sqLiteDatabase.execSQL(insertLopVL1_2);
//        sqLiteDatabase.execSQL(insertLopVL1_3);
//        sqLiteDatabase.execSQL(insertLopVL1_4);
//        sqLiteDatabase.execSQL(insertLopVL1_5);
//        sqLiteDatabase.execSQL(insertLopVL1_6);
//        sqLiteDatabase.execSQL(insertLopVL1_7);
//        sqLiteDatabase.execSQL(insertLopVL1_8);
//        sqLiteDatabase.execSQL(insertLopVL1_9);
//
//
//        //Insert KHOANGTHOIGIANHOC cho KHOA 13
//
//        //DCSVN
//        String insertKhoangThoiGianHocDCSVN1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN1' , '2017-2-6' , '2017-3-26' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocDCSVN1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN1' , '2017-3-27' , '2017-4-2' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocDCSVN2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN2' , '2017-2-6' , '2017-3-26' , 3 , '0-1-3' )";
//        String insertKhoangThoiGianHocDCSVN2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN2' , '2017-3-27' , '2017-4-2' , 3 , '0-0-1' )";
//        String insertKhoangThoiGianHocDCSVN3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN3' , '2017-2-6' , '2017-3-26' , 4 , '0-1-3' )";
//        String insertKhoangThoiGianHocDCSVN3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN3' , '2017-3-27' , '2017-4-2' , 4 , '0-0-1' )";
//        String insertKhoangThoiGianHocDCSVN4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN4' , '2017-2-6' , '2017-3-26' , 1 , '0-1-4' )";
//        String insertKhoangThoiGianHocDCSVN4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN4' , '2017-3-27' , '2017-4-2' , 1 , '0-0-1' )";
//        String insertKhoangThoiGianHocDCSVN5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN5' , '2017-2-6' , '2017-3-26' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocDCSVN5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN5' , '2017-3-27' , '2017-4-2' , 3 , '0-0-2' )";
//        String insertKhoangThoiGianHocDCSVN6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN6' , '2017-2-6' , '2017-3-12' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocDCSVN6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN6' , '2017-3-13' , '2017-4-2' , 5 , '0-4-5' )";
//        String insertKhoangThoiGianHocDCSVN7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN7' , '2017-2-6' , '2017-3-12' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocDCSVN7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN7' , '2017-3-13' , '2017-4-2' , 5 , '0-1-3' )";
//        String insertKhoangThoiGianHocDCSVN8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN8' , '2017-2-6' , '2017-3-12' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocDCSVN8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN8' , '2017-3-13' , '2017-4-2' , 5 , '0-4-5' )";
//        String insertKhoangThoiGianHocDCSVN9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN9' , '2017-2-6' , '2017-3-26' , 2 , '0-4-5' )";
//        String insertKhoangThoiGianHocDCSVN9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DCSVN9' , '2017-3-27' , '2017-4-2' , 2 , '0-0-4' )";
//
//
//        //TD2
//        String insertKhoangThoiGianHocTD2_1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_1' , '2017-4-10' , '2017-6-18' , 4 , '0-0-3' )";
//        String insertKhoangThoiGianHocTD2_2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_2' , '2017-4-10' , '2017-6-18' , 1 , '0-0-4' )";
//        String insertKhoangThoiGianHocTD2_3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_3' , '2017-4-10' , '2017-6-18' , 1 , '0-0-3' )";
//        String insertKhoangThoiGianHocTD2_4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_4' , '2017-4-10' , '2017-6-18' , 4 , '0-0-4' )";
//        String insertKhoangThoiGianHocTD2_5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_5' , '2017-4-10' , '2017-6-18' , 1 , '0-0-4' )";
//        String insertKhoangThoiGianHocTD2_6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_6' , '2017-4-10' , '2017-6-18' , 1 , '0-0-3' )";
//        String insertKhoangThoiGianHocTD2_9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_9' , '2017-4-10' , '2017-6-18' , 4 , '0-0-3' )";
//        String insertKhoangThoiGianHocTD2_7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_7' , '2017-4-10' , '2017-4-30' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocTD2_7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_7' , '2017-5-1' , '2017-5-7' , 1 , '0-0-5' )";
//        String insertKhoangThoiGianHocTD2_7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_7' , '2017-5-8' , '2017-6-18' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocTD2_8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_8' , '2017-4-10' , '2017-4-30' , 1 , '0-0-1' )";
//        String insertKhoangThoiGianHocTD2_8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_8' , '2017-5-1' , '2017-5-7' , 2 , '0-0-3' )";
//        String insertKhoangThoiGianHocTD2_8_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TD2_8' , '2017-5-8' , '2017-6-18' , 1 , '0-0-1' )";
//
//
//        //KNM1
//        String insertKhoangThoiGianHocKNM1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM1' , '2017-4-17' , '2017-4-30' , 1 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM1' , '2017-5-1' , '2017-5-7' , 1 , '0-0-3' )";
//        String insertKhoangThoiGianHocKNM1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM1' , '2017-5-8' , '2017-6-11' , 1 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM1' , '2017-6-12' , '2017-6-18' , 1 , '0-1-3' )";
//        String insertKhoangThoiGianHocKNM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM2' , '2017-4-17' , '2017-4-30' , 3 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM2' , '2017-5-1' , '2017-5-7' , 3 , '0-0-5' )";
//        String insertKhoangThoiGianHocKNM2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM2' , '2017-5-8' , '2017-6-11' , 3 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM2' , '2017-6-12' , '2017-6-18' , 3 , '0-1-3' )";
//        String insertKhoangThoiGianHocKNM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM3' , '2017-4-17' , '2017-4-30' , 4 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM3' , '2017-5-1' , '2017-5-7' , 3 , '0-0-3' )";
//        String insertKhoangThoiGianHocKNM3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM3' , '2017-5-8' , '2017-6-11' , 4 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM3' , '2017-6-12' , '2017-6-18' , 4 , '0-1-3' )";
//        String insertKhoangThoiGianHocKNM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM4' , '2017-4-17' , '2017-4-30' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocKNM4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM4' , '2017-5-1' , '2017-5-7' , 2 , '0-0-5' )";
//        String insertKhoangThoiGianHocKNM4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM4' , '2017-5-8' , '2017-6-4' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocKNM4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM4' , '2017-6-5' , '2017-6-11' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocKNM4_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM4' , '2017-6-12' , '2017-6-18' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocKNM5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM5' , '2017-4-17' , '2017-4-30' , 3 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM5' , '2017-5-1' , '2017-5-7' , 3 , '0-0-5' )";
//        String insertKhoangThoiGianHocKNM5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM5' , '2017-5-8' , '2017-6-11' , 3 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM5' , '2017-6-12' , '2017-6-18' , 3 , '0-1-3' )";
//        String insertKhoangThoiGianHocKNM6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM6' , '2017-4-17' , '2017-4-30' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM6' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocKNM6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM6' , '2017-5-8' , '2017-6-11' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM7' , '2017-4-17' , '2017-4-30' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocKNM7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM7' , '2017-5-1' , '2017-5-7' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocKNM7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM7' , '2017-5-8' , '2017-6-11' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocKNM8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM8' , '2017-4-17' , '2017-6-11' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocKNM9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM9' , '2017-4-17' , '2017-4-30' , 2 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM9' , '2017-5-1' , '2017-5-7' , 1 , '0-0-5' )";
//        String insertKhoangThoiGianHocKNM9_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM9' , '2017-5-8' , '2017-6-11' , 2 , '0-0-1' )";
//        String insertKhoangThoiGianHocKNM9_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KNM9' , '2017-6-12' , '2017-6-18' , 2 , '0-1-3' )";
//
//
//        //C
//        String insertKhoangThoiGianHocLTC1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC1' , '2017-1-2' , '2017-1-22' , 1 , '0-0-3' )";
//        String insertKhoangThoiGianHocLTC1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC1' , '2017-1-2' , '2017-1-8' , 2 , '0-0-5' )";
//        String insertKhoangThoiGianHocLTC1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC1' , '2017-1-9' , '2017-1-22' , 1 , '0-0-1' )";
//        String insertKhoangThoiGianHocLTC1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC1' , '2017-2-6' , '2017-3-5' , 1 , '0-1-3' )";
//        String insertKhoangThoiGianHocLTC1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC1' , '2017-3-6' , '2017-4-2' , 1 , '0-0-1' )";
//
//        String insertKhoangThoiGianHocLTC2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC2' , '2017-1-2' , '2017-1-22' , 3 , '0-0-3' )";
//        String insertKhoangThoiGianHocLTC2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC2' , '2017-1-2' , '2017-1-8' , 4 , '0-0-5' )";
//        String insertKhoangThoiGianHocLTC2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC2' , '2017-1-9' , '2017-1-22' , 3 , '0-0-1' )";
//        String insertKhoangThoiGianHocLTC2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC2' , '2017-2-6' , '2017-3-5' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocLTC2_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC2' , '2017-3-6' , '2017-3-12' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocLTC2_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC2' , '2017-3-13' , '2017-4-2' , 3 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocLTC3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC3' , '2017-1-2' , '2017-1-22' , 4 , '0-2-4' )";
//        String insertKhoangThoiGianHocLTC3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC3' , '2017-2-6' , '2017-3-5' , 4 , '0-2-4' )";
//        String insertKhoangThoiGianHocLTC3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC3' , '2017-3-6' , '2017-3-26' , 4 , '0-0-2' )";
//        String insertKhoangThoiGianHocLTC3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC3' , '2017-3-27' , '2017-4-2' , 3 , '0-0-4' )";
//
//        String insertKhoangThoiGianHocLTC4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC4' , '2017-1-2' , '2017-1-22' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocLTC4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC4' , '2017-2-6' , '2017-3-5' , 1 , '0-2-5' )";
//        String insertKhoangThoiGianHocLTC4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC4' , '2017-3-6' , '2017-3-12' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocLTC4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC4' , '2017-3-13' , '2017-4-2' , 1 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocLTC5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC5' , '2017-1-2' , '2017-1-15' , 3 , '0-0-5' )";
//        String insertKhoangThoiGianHocLTC5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC5' , '2017-1-2' , '2017-1-8' , 4 , '0-0-3' )";
//        String insertKhoangThoiGianHocLTC5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC5' , '2017-1-9' , '2017-1-15' , 4 , '0-0-1' )";
//        String insertKhoangThoiGianHocLTC5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC5' , '2017-1-16' , '2017-1-22' , 4 , '0-1-3' )";
//        String insertKhoangThoiGianHocLTC5_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC5' , '2017-2-6' , '2017-3-5' , 4 , '0-0-1' )";
//        String insertKhoangThoiGianHocLTC5_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC5' , '2017-2-6' , '2017-3-5' , 3 , '0-0-5' )";
//        String insertKhoangThoiGianHocLTC5_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC5' , '2017-3-6' , '2017-4-2' , 3 , '0-0-1' )";
//
//        String insertKhoangThoiGianHocLTC6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC6' , '2017-1-2' , '2017-1-8' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocLTC6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC6' , '2017-1-9' , '2017-1-22' , 5 , '0-2-4' )";
//        String insertKhoangThoiGianHocLTC6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC6' , '2017-2-6' , '2017-3-5' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocLTC6_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC6' , '2017-3-6' , '2017-3-12' , 5 , '0-2-5' )";
//        String insertKhoangThoiGianHocLTC6_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC6' , '2017-3-13' , '2017-4-2' , 5 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocLTC7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC7' , '2017-1-2' , '2017-1-8' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocLTC7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC7' , '2017-1-9' , '2017-1-22' , 5 , '0-1-3' )";
//        String insertKhoangThoiGianHocLTC7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC7' , '2017-2-6' , '2017-3-5' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocLTC7_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC7' , '2017-3-6' , '2017-3-12' , 5 , '0-3-4' )";
//        String insertKhoangThoiGianHocLTC7_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC7' , '2017-3-13' , '2017-4-2' , 5 , '0-0-4' )";
//
//        String insertKhoangThoiGianHocLTC8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC8' , '2017-1-2' , '2017-1-8' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocLTC8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC8' , '2017-1-9' , '2017-1-15' , 5 , '0-3-5' )";
//        String insertKhoangThoiGianHocLTC8_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC8' , '2017-1-16' , '2017-1-22' , 5 , '0-3-4' )";
//        String insertKhoangThoiGianHocLTC8_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC8' , '2017-2-6' , '2017-3-5' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocLTC8_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC8' , '2017-3-6' , '2017-3-12' , 5 , '0-3-5' )";
//        String insertKhoangThoiGianHocLTC8_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC8' , '2017-3-13' , '2017-4-2' , 5 , '0-0-3' )";
//
//        String insertKhoangThoiGianHocLTC9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC9' , '2017-1-2' , '2017-1-8' , 2 , '0-0-3' )";
//        String insertKhoangThoiGianHocLTC9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC9' , '2017-1-2' , '2017-1-8' , 1 , '0-0-5' )";
//        String insertKhoangThoiGianHocLTC9_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC9' , '2017-1-9' , '2017-1-22' , 2 , '0-1-3' )";
//        String insertKhoangThoiGianHocLTC9_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC9' , '2017-2-6' , '2017-3-5' , 2 , '0-1-3' )";
//        String insertKhoangThoiGianHocLTC9_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC9' , '2017-3-6' , '2017-3-12' , 1 , '0-0-3' )";
//        String insertKhoangThoiGianHocLTC9_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'LTC9' , '2017-3-13' , '2017-4-2' , 2 , '0-0-3' )";
//
//
//        //CNMLN2_1
//        String insertKhoangThoiGianHocCNMLN2_1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_1' , '2017-1-2' , '2017-1-8' , 1 , '2-4-5' )";
//        String insertKhoangThoiGianHocCNMLN2_1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_1' , '2017-1-9' , '2017-1-22' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocCNMLN2_1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_1' , '2017-2-6' , '2017-4-2' , 2 , '0-0-4' )";
//
//        String insertKhoangThoiGianHocCNMLN2_2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_2' , '2017-1-2' , '2017-1-8' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocCNMLN2_2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_2' , '2017-1-9' , '2017-1-15' , 3 , '2-4-5' )";
//        String insertKhoangThoiGianHocCNMLN2_2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_2' , '2017-1-16' , '2017-1-22' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocCNMLN2_2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_2' , '2017-2-6' , '2017-4-2' , 2 , '0-0-5' )";
//
//        String insertKhoangThoiGianHocCNMLN2_3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_3' , '2017-1-2' , '2017-1-8' , 3 , '0-3-5' )";
//        String insertKhoangThoiGianHocCNMLN2_3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_3' , '2017-1-9' , '2017-1-15' , 3 , '0-1-3' )";
//        String insertKhoangThoiGianHocCNMLN2_3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_3' , '2017-1-9' , '2017-1-15' , 4 , '0-0-5' )";
//        String insertKhoangThoiGianHocCNMLN2_3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_3' , '2017-1-16' , '2017-1-22' , 3 , '0-1-3' )";
//        String insertKhoangThoiGianHocCNMLN2_3_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_3' , '2017-2-6' , '2017-4-2' , 3 , '0-0-1' )";
//
//        String insertKhoangThoiGianHocCNMLN2_4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_4' , '2017-1-2' , '2017-1-8' , 2 , '0-3-5' )";
//        String insertKhoangThoiGianHocCNMLN2_4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_4' , '2017-1-9' , '2017-1-15' , 1 , '1-3-5' )";
//        String insertKhoangThoiGianHocCNMLN2_4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_4' , '2017-1-16' , '2017-1-22' , 1 , '0-1-3' )";
//        String insertKhoangThoiGianHocCNMLN2_4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_4' , '2017-2-6' , '2017-4-2' , 1 , '0-0-3' )";
//
//        String insertKhoangThoiGianHocCNMLN2_5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_5' , '2017-1-2' , '2017-1-8' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocCNMLN2_5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_5' , '2017-1-9' , '2017-1-15' , 3 , '2-3-4' )";
//        String insertKhoangThoiGianHocCNMLN2_5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_5' , '2017-1-16' , '2017-1-22' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocCNMLN2_5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_5' , '2017-2-6' , '2017-4-2' , 3 , '0-0-3' )";
//
//        String insertKhoangThoiGianHocCNMLN2_6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_6' , '2017-1-2' , '2017-1-8' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocCNMLN2_6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_6' , '2017-1-9' , '2017-1-22' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocCNMLN2_6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_6' , '2017-2-6' , '2017-4-2' , 5 , '0-0-1' )";
//
//        String insertKhoangThoiGianHocCNMLN2_7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_7' , '2017-1-2' , '2017-1-15' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocCNMLN2_7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_7' , '2017-1-16' , '2017-1-22' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocCNMLN2_7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_7' , '2017-2-6' , '2017-4-2' , 5 , '0-0-5' )";
//
//        String insertKhoangThoiGianHocCNMLN2_8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_8' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocCNMLN2_8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_8' , '2017-2-6' , '2017-4-2' , 5 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocCNMLN2_9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_9' , '2017-1-2' , '2017-1-8' , 2 , '0-2-4' )";
//        String insertKhoangThoiGianHocCNMLN2_9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_9' , '2017-1-9' , '2017-1-15' , 2 , '2-4-5' )";
//        String insertKhoangThoiGianHocCNMLN2_9_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_9' , '2017-1-16' , '2017-1-22' , 2 , '0-2-4' )";
//        String insertKhoangThoiGianHocCNMLN2_9_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'CNMLN2_9' , '2017-2-6' , '2017-4-2' , 2 , '0-0-2' )";
//
//
//        //TOAN2_1
//        String insertKhoangThoiGianHocTOAN2_1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_1' , '2017-2-6' , '2017-3-5' , 2 , '0-0-3' )";
//        String insertKhoangThoiGianHocTOAN2_1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_1' , '2017-2-6' , '2017-3-5' , 1 , '0-0-5' )";
//        String insertKhoangThoiGianHocTOAN2_1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_1' , '2017-3-6' , '2017-3-26' , 1 , '0-3-5' )";
//        String insertKhoangThoiGianHocTOAN2_1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_1' , '2017-3-27' , '2017-4-2' , 1 , '3-4-5' )";
//
//        String insertKhoangThoiGianHocTOAN2_2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_2' , '2017-2-6' , '2017-3-26' , 4 , '0-1-3' )";
//        String insertKhoangThoiGianHocTOAN2_2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_2' , '2017-3-27' , '2017-4-2' , 4 , '1-3-5' )";
//
//        String insertKhoangThoiGianHocTOAN2_3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_3' , '2017-2-6' , '2017-3-26' , 3 , '0-2-5' )";
//        String insertKhoangThoiGianHocTOAN2_3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_3' , '2017-3-27' , '2017-4-2' , 3 , '2-3-5' )";
//
//        String insertKhoangThoiGianHocTOAN2_4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_4' , '2017-2-6' , '2017-3-26' , 2 , '0-1-3' )";
//        String insertKhoangThoiGianHocTOAN2_4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_4' , '2017-3-27' , '2017-4-2' , 2 , '1-3-5' )";
//
//        String insertKhoangThoiGianHocTOAN2_5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_5' , '2017-2-6' , '2017-3-19' , 4 , '0-3-5' )";
//        String insertKhoangThoiGianHocTOAN2_5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_5' , '2017-3-20' , '2017-3-26' , 4 , '1-3-5' )";
//        String insertKhoangThoiGianHocTOAN2_5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_5' , '2017-3-27' , '2017-4-2' , 4 , '0-3-5' )";
//
//        String insertKhoangThoiGianHocTOAN2_6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_6' , '2017-1-2' , '2017-1-22' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocTOAN2_6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_6' , '2017-2-6' , '2017-2-19' , 5 , '0-3-5' )";
//        String insertKhoangThoiGianHocTOAN2_6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_6' , '2017-2-20' , '2017-4-2' , 5 , '0-0-3' )";
//
//        String insertKhoangThoiGianHocTOAN2_7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_7' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocTOAN2_7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_7' , '2017-2-6' , '2017-2-19' , 5 , '0-2-4' )";
//        String insertKhoangThoiGianHocTOAN2_7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_7' , '2017-2-20' , '2017-4-2' , 5 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocTOAN2_8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_8' , '2017-1-2' , '2017-1-8' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocTOAN2_8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_8' , '2017-1-9' , '2017-1-22' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocTOAN2_8_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_8' , '2017-2-6' , '2017-2-19' , 5 , '0-1-3' )";
//        String insertKhoangThoiGianHocTOAN2_8_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_8' , '2017-2-20' , '2017-4-2' , 5 , '0-0-1' )";
//
//        String insertKhoangThoiGianHocTOAN2_9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_9' , '2017-2-6' , '2017-3-26' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocTOAN2_9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TOAN2_9' , '2017-3-27' , '2017-4-2' , 1 , '2-4-5' )";
//
//
//        //VL2
//        String insertKhoangThoiGianHocVL2_1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_1' , '2017-4-17' , '2017-4-30' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocVL2_1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_1' , '2017-5-1' , '2017-5-7' , 1 , '0-4-5' )";
//        String insertKhoangThoiGianHocVL2_1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_1' , '2017-5-8' , '2017-6-11' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocVL2_1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_1' , '2017-6-12' , '2017-6-18' , 1 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocVL2_2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_2' , '2017-4-17' , '2017-4-30' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocVL2_2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_2' , '2017-5-1' , '2017-5-7' , 3 , '0-3-4' )";
//        String insertKhoangThoiGianHocVL2_2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_2' , '2017-5-8' , '2017-6-11' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocVL2_2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_2' , '2017-6-12' , '2017-6-18' , 3 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocVL2_3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_3' , '2017-4-17' , '2017-4-30' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocVL2_3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_3' , '2017-5-1' , '2017-5-7' , 3 , '0-4-5' )";
//        String insertKhoangThoiGianHocVL2_3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_3' , '2017-5-8' , '2017-6-11' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocVL2_3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_3' , '2017-6-12' , '2017-6-18' , 3 , '0-0-2' )";
//
//        String insertKhoangThoiGianHocVL2_4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_4' , '2017-4-17' , '2017-4-30' , 1 , '0-1-3' )";
//        String insertKhoangThoiGianHocVL2_4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_4' , '2017-5-1' , '2017-5-7' , 1 , '0-3-4' )";
//        String insertKhoangThoiGianHocVL2_4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_4' , '2017-5-8' , '2017-6-11' , 1 , '0-1-3' )";
//        String insertKhoangThoiGianHocVL2_4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_4' , '2017-6-12' , '2017-6-18' , 1 , '0-0-3' )";
//
//        String insertKhoangThoiGianHocVL2_5_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_5' , '2017-4-17' , '2017-4-30' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocVL2_5_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_5' , '2017-5-1' , '2017-5-7' , 3 , '0-4-5' )";
//        String insertKhoangThoiGianHocVL2_5_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_5' , '2017-5-8' , '2017-6-11' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocVL2_5_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_5' , '2017-6-12' , '2017-6-18' , 3 , '0-0-4' )";
//
//        String insertKhoangThoiGianHocVL2_6_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_6' , '2017-4-17' , '2017-4-30' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocVL2_6_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_6' , '2017-5-1' , '2017-5-7' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocVL2_6_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_6' , '2017-5-8' , '2017-5-21' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocVL2_6_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_6' , '2017-5-22' , '2017-6-18' , 5 , '0-2-4' )";
//
//        String insertKhoangThoiGianHocVL2_7_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_7' , '2017-4-17' , '2017-4-30' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocVL2_7_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_7' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocVL2_7_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_7' , '2017-5-8' , '2017-5-21' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocVL2_7_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_7' , '2017-5-22' , '2017-6-18' , 5 , '0-1-3' )";
//
//        String insertKhoangThoiGianHocVL2_8_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_8' , '2017-4-17' , '2017-4-30' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocVL2_8_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_8' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocVL2_8_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_8' , '2017-5-8' , '2017-5-21' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocVL2_8_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_8' , '2017-5-22' , '2017-6-18' , 5 , '0-1-3' )";
//
//        String insertKhoangThoiGianHocVL2_9_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_9' , '2017-4-17' , '2017-4-30' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocVL2_9_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_9' , '2017-5-1' , '2017-5-7' , 1 , '0-3-4' )";
//        String insertKhoangThoiGianHocVL2_9_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_9' , '2017-5-8' , '2017-6-11' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocVL2_9_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'VL2_9' , '2017-6-12' , '2017-6-18' , 1 , '0-0-2' )";
//
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN5_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN5_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN6_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN6_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN7_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN7_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN8_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN8_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN9_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDCSVN9_2);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_5_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_6_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_7_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_7_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_7_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_8_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_8_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_8_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTD2_9_1);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM4_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM4_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM4_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM5_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM5_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM5_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM5_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM6_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM6_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM6_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM7_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM7_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM7_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM8_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM9_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM9_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM9_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKNM9_4);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC1_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC2_6);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC4_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC4_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_6);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC5_7);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC6_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC6_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC6_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC6_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC6_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC7_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC7_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC7_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC7_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC7_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC8_6);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocLTC9_6);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_3_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_4_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_4_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_5_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_5_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_5_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_5_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_6_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_6_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_6_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_7_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_7_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_7_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_8_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_8_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_9_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_9_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_9_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocCNMLN2_9_4);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_5_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_5_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_5_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_6_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_6_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_6_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_7_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_7_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_7_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_8_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_8_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_8_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_8_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_9_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTOAN2_9_2);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_4_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_4_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_5_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_5_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_5_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_5_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_6_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_6_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_6_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_6_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_7_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_7_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_7_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_7_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_8_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_8_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_8_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_8_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_9_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_9_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_9_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocVL2_9_4);
//
//
//        /******************************************* END KHOA 13 **********************************/
//
//
//
//        /*******************************************KHOA 10**********************************/
//
//        //Insert dữ liệu cho bảng Môn học (KHÓA 10)
//        String insertMonATMMT = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('ATMMT' , 'An toàn mạng máy tính' , null , 5 , 4 , 'trống trơn' , 'NAM42' , 4)";
//        String insertMonDGKDHT = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('DGKDHT' , 'Đánh giá & kiểm định AT hệ TTT' , null , 5 , 3 , 'trống trơn' , 'NAM42' , 4)";
//        String insertMonGTATM = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('GTATM' , 'Giao thức an toàn mạng' , null , 4 , 2 , 'trống trơn' , 'NAM42' , 4)";
//        String insertMonKTGT = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('KTGT' , 'Kỹ thuật giấu tin' , null , 4 , 2 , 'trống trơn' , 'NAM42' , 4)";
//        String insertMonPTTKM = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('PTTKM' , 'Phân tích, thiết kế an toàn mạng máy tính' , null , 5 , 2 , 'trống trơn' , 'NAM42' , 4)";
//        String insertMonTTANM = " INSERT INTO " + TB_MONHOC + " VALUES "
//                + " ('TTANM' , 'Thu thập và phân tích TT AN mạng' , null , 5 , 3 , 'trống trơn' , 'NAM42' , 4)";
//
//        sqLiteDatabase.execSQL(insertMonATMMT);
//        sqLiteDatabase.execSQL(insertMonDGKDHT);
//        sqLiteDatabase.execSQL(insertMonGTATM);
//        sqLiteDatabase.execSQL(insertMonKTGT);
//        sqLiteDatabase.execSQL(insertMonPTTKM);
//        sqLiteDatabase.execSQL(insertMonTTANM);
//
//
//        //insert dữ liệu vào table Lớp học (KHÓA 10)
//        String insertLopATMMT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'ATMMT1' , 'L01' , '102_TA2 TA2' , 'Hoàng Thanh Nam' , 'ATMMT' , 'HTN')";
//        String insertLopATMMT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'ATMMT2' , 'L02' , '102_TA2 TA2' , 'Cao Minh Tuấn' , 'ATMMT' , 'CMT')";
//        String insertLopATMMT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'ATMMT3' , 'L03' , '204-TA2 TA2' , 'Hoàng Thanh Nam' , 'ATMMT' , 'HTN')";
//        String insertLopATMMT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'ATMMT4' , 'L04' , '303_TA2 TA2' , 'Cao Minh Tuấn' , 'ATMMT' , 'CMT')";
//
//        String insertLopDGKDHT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DGKDHT1' , 'L01' , '102_TA2 TA2' , 'Phạm Minh Thuấn' , 'DGKDHT' , 'PMT')";
//        String insertLopDGKDHT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DGKDHT2' , 'L02' , '102_TA2 TA2' , 'Trần Anh Tú' , 'DGKDHT' , 'TAT')";
//        String insertLopDGKDHT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DGKDHT3' , 'L03' , '204-TA2 TA2' , 'Trần Anh Tú' , 'DGKDHT' , 'TAT')";
//        String insertLopDGKDHT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'DGKDHT4' , 'L04' , '303_TA2 TA2' , 'Phạm Minh Thuấn' , 'DGKDHT' , 'PMT')";
//
//        String insertLopGTATM1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'GTATM1' , 'L01' , '102_TA2 TA2' , 'Trần Thị Lượng' , 'GTATM' , 'TTL')";
//        String insertLopGTATM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'GTATM2' , 'L02' , '102_TA2 TA2' , 'Trần Thị Lượng' , 'GTATM' , 'TTL')";
//        String insertLopGTATM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'GTATM3' , 'L03' , '204-TA2 TA2' , 'Trần Thị Lượng' , 'GTATM' , 'TTL')";
//        String insertLopGTATM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'GTATM4' , 'L04' , '303_TA2 TA2' , 'Nguyễn Tuấn Anh' , 'GTATM' , 'NTA')";
//
//        String insertLopKTGT1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTGT1' , 'L01' , '102_TA2 TA2' , 'Hoàng Thu Phương' , 'KTGT' , 'HTP')";
//        String insertLopKTGT2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTGT2' , 'L02' , '102_TA2 TA2' , 'Trần Thị Xuyên' , 'KTGT' , 'TTX')";
//        String insertLopKTGT3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTGT3' , 'L03' , '204-TA2 TA2' , 'Hoàng Thu Phương' , 'KTGT' , 'HTP')";
//        String insertLopKTGT4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'KTGT4' , 'L04' , '303_TA2 TA2' , 'Trần Thị Xuyên' , 'KTGT' , 'TTX')";
//
//        String insertLopPTTKM1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'PTTKM1' , 'L01' , '102_TA2 TA2' , 'Nguyễn Mạnh Thắng' , 'PTTKM' , 'NMT')";
//        String insertLopPTTKM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'PTTKM2' , 'L02' , '102_TA2 TA2' , 'Nguyễn Mạnh Thắng' , 'PTTKM' , 'NMT')";
//        String insertLopPTTKM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'PTTKM3' , 'L03' , '204-TA2 TA2' , 'Nguyễn Mạnh Thắng' , 'PTTKM' , 'NMT')";
//        String insertLopPTTKM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'PTTKM4' , 'L04' , '303_TA2 TA2' , 'Nguyễn Mạnh Thắng' , 'PTTKM' , 'NMT')";
//
//        String insertLopTTANM1 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TTANM1' , 'L01' , '102_TA2 TA2' , 'Hoàng Thanh Nam' , 'TTANM' , 'HTN')";
//        String insertLopTTANM2 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TTANM2' , 'L02' , '102_TA2 TA2' , 'Hoàng Thanh Nam' , 'TTANM' , 'HTN')";
//        String insertLopTTANM3 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TTANM3' , 'L03' , '204-TA2 TA2' , 'Hoàng Thanh Nam' , 'TTANM' , 'HTN')";
//        String insertLopTTANM4 = " INSERT INTO " + TB_LOPHOC + " VALUES "
//                + " ( 'TTANM4' , 'L04' , '303_TA2 TA2' , 'Hoàng Thanh Nam' , 'TTANM' , 'HTN')";
//
//
//        sqLiteDatabase.execSQL(insertLopATMMT1);    //
//        sqLiteDatabase.execSQL(insertLopATMMT2);
//        sqLiteDatabase.execSQL(insertLopATMMT3);
//        sqLiteDatabase.execSQL(insertLopATMMT4);
//        sqLiteDatabase.execSQL(insertLopDGKDHT1);   //
//        sqLiteDatabase.execSQL(insertLopDGKDHT2);
//        sqLiteDatabase.execSQL(insertLopDGKDHT3);
//        sqLiteDatabase.execSQL(insertLopDGKDHT4);
//        sqLiteDatabase.execSQL(insertLopGTATM1);    //
//        sqLiteDatabase.execSQL(insertLopGTATM2);
//        sqLiteDatabase.execSQL(insertLopGTATM3);
//        sqLiteDatabase.execSQL(insertLopGTATM4);
//        sqLiteDatabase.execSQL(insertLopKTGT1);     //
//        sqLiteDatabase.execSQL(insertLopKTGT2);
//        sqLiteDatabase.execSQL(insertLopKTGT3);
//        sqLiteDatabase.execSQL(insertLopKTGT4);
//        sqLiteDatabase.execSQL(insertLopPTTKM1);    //
//        sqLiteDatabase.execSQL(insertLopPTTKM2);
//        sqLiteDatabase.execSQL(insertLopPTTKM3);
//        sqLiteDatabase.execSQL(insertLopPTTKM4);
//        sqLiteDatabase.execSQL(insertLopTTANM1);    //
//        sqLiteDatabase.execSQL(insertLopTTANM2);
//        sqLiteDatabase.execSQL(insertLopTTANM3);
//        sqLiteDatabase.execSQL(insertLopTTANM4);
//
//
//
//
//        //Insert KHOANGTHOIGIANHOC cho KHOA 10
//
//        //ATMMT
//        String insertKhoangThoiGianHocATMMT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT1' , '2017-1-2' , '2017-1-8' , 1 , '0-3-5' )";
//        String insertKhoangThoiGianHocATMMT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT1' , '2017-1-2' , '2017-1-8' , 2 , '0-0-4' )";
//        String insertKhoangThoiGianHocATMMT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT1' , '2017-1-9' , '2017-1-15' , 1 , '1-3-5' )";
//        String insertKhoangThoiGianHocATMMT1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT1' , '2017-1-16' , '2017-1-22' , 1 , '0-1-3' )";
//        String insertKhoangThoiGianHocATMMT1_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT1' , '2017-1-16' , '2017-1-22' , 2 , '0-0-4' )";
//        String insertKhoangThoiGianHocATMMT1_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT1' , '2017-2-6' , '2017-3-12' , 1 , '1-3-5' )";
//        String insertKhoangThoiGianHocATMMT1_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT1' , '2017-3-13' , '2017-4-2' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocATMMT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT2' , '2017-1-2' , '2017-1-8' , 3 , '0-3-5' )";
//        String insertKhoangThoiGianHocATMMT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT2' , '2017-1-2' , '2017-1-8' , 4 , '0-0-2' )";
//        String insertKhoangThoiGianHocATMMT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT2' , '2017-1-9' , '2017-1-15' , 3 , '1-3-5' )";
//        String insertKhoangThoiGianHocATMMT2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT2' , '2017-1-16' , '2017-1-22' , 3 , '0-1-3' )";
//        String insertKhoangThoiGianHocATMMT2_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT2' , '2017-1-16' , '2017-1-22' , 4 , '0-0-4' )";
//        String insertKhoangThoiGianHocATMMT2_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT2' , '2017-2-6' , '2017-3-12' , 3 , '1-3-5' )";
//        String insertKhoangThoiGianHocATMMT2_7 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT2' , '2017-3-13' , '2017-4-2' , 3 , '0-1-3' )";
//        String insertKhoangThoiGianHocATMMT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT3' , '2017-1-2' , '2017-1-8' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocATMMT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT3' , '2017-1-9' , '2017-1-22' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocATMMT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT3' , '2017-2-6' , '2017-3-5' , 5 , '0-1-3' )";
//        String insertKhoangThoiGianHocATMMT3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT3' , '2017-3-6' , '2017-4-2' , 5 , '1-3-4' )";
//        String insertKhoangThoiGianHocATMMT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT4' , '2017-1-2' , '2017-1-22' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocATMMT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT4' , '2017-2-6' , '2017-3-5' , 5 , '0-2-4' )";
//        String insertKhoangThoiGianHocATMMT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'ATMMT4' , '2017-3-6' , '2017-4-2' , 5 , '2-4-5' )";
//
//
//        //DGKDHT
//        String insertKhoangThoiGianHocDGKDHT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT1' , '2017-1-2' , '2017-1-22' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocDGKDHT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT1' , '2017-2-6' , '2017-3-19' , 2 , '0-3-5' )";
//        String insertKhoangThoiGianHocDGKDHT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT1' , '2017-3-20' , '2017-4-2' , 1 , '0-0-3' )";
//        String insertKhoangThoiGianHocDGKDHT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT2' , '2017-1-2' , '2017-1-22' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocDGKDHT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT2' , '2017-2-6' , '2017-3-19' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocDGKDHT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT2' , '2017-3-20' , '2017-4-2' , 3 , '0-0-2' )";
//        String insertKhoangThoiGianHocDGKDHT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT3' , '2017-1-2' , '2017-1-22' , 5 , '0-2-4' )";
//        String insertKhoangThoiGianHocDGKDHT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT3' , '2017-2-6' , '2017-2-12' , 5 , '0-2-4' )";
//        String insertKhoangThoiGianHocDGKDHT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT3' , '2017-2-13' , '2017-4-2' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocDGKDHT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT4' , '2017-1-2' , '2017-1-8' , 5 , '0-3-5' )";
//        String insertKhoangThoiGianHocDGKDHT4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT4' , '2017-1-9' , '2017-1-22' , 5 , '0-1-3' )";
//        String insertKhoangThoiGianHocDGKDHT4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT4' , '2017-2-6' , '2017-3-26' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocDGKDHT4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'DGKDHT4' , '2017-3-27' , '2017-4-2' , 3 , '0-1-3' )";
//
//
//        //GTATM1
//        String insertKhoangThoiGianHocGTATM1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'GTATM1' , '2017-4-17' , '2017-4-30' , 1 , '0-1-3' )";
//        String insertKhoangThoiGianHocGTATM1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'GTATM1' , '2017-5-1' , '2017-5-7' , 1 , '0-3-4' )";
//        String insertKhoangThoiGianHocGTATM1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'GTATM1' , '2017-5-8' , '2017-6-18' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocGTATM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'GTATM2' , '2017-4-17' , '2017-4-30' , 3 , '0-1-3' )";
//        String insertKhoangThoiGianHocGTATM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'GTATM2' , '2017-5-1' , '2017-5-7' , 3 , '0-3-4' )";
//        String insertKhoangThoiGianHocGTATM2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'GTATM2' , '2017-5-8' , '2017-6-18' , 3 , '0-0-1' )";
//        String insertKhoangThoiGianHocGTATM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'GTATM3' , '2017-4-17' , '2017-4-30' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocGTATM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'GTATM3' , '2017-5-1' , '2017-5-7' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocGTATM3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'GTATM3' , '2017-5-8' , '2017-6-18' , 5 , '0-0-1' )";
//        String insertKhoangThoiGianHocGTATM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'GTATM4' , '2017-4-17' , '2017-6-18' , 5 , '0-0-3' )";
//
//
//        //KTGT1
//        String insertKhoangThoiGianHocKTGT1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT1' , '2017-4-17' , '2017-4-30' , 1 , '0-0-2' )";
//        String insertKhoangThoiGianHocKTGT1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT1' , '2017-5-1' , '2017-5-7' , 2 , '0-0-3' )";
//        String insertKhoangThoiGianHocKTGT1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT1' , '2017-5-8' , '2017-5-28' , 1 , '0-1-3' )";
//        String insertKhoangThoiGianHocKTGT1_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT1' , '2017-5-29' , '2017-6-18' , 1 , '0-0-1' )";
//        String insertKhoangThoiGianHocKTGT2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT1' , '2017-4-17' , '2017-4-30' , 3 , '0-0-2' )";
//        String insertKhoangThoiGianHocKTGT2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT2' , '2017-5-1' , '2017-5-7' , 4 , '0-0-3' )";
//        String insertKhoangThoiGianHocKTGT2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT2' , '2017-5-8' , '2017-5-28' , 3 , '0-2-4' )";
//        String insertKhoangThoiGianHocKTGT2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT2' , '2017-5-29' , '2017-6-18' , 3 , '0-0-2' )";
//        String insertKhoangThoiGianHocKTGT3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT3' , '2017-4-17' , '2017-4-30' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocKTGT3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT3' , '2017-5-1' , '2017-5-7' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocKTGT3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT3' , '2017-5-8' , '2017-6-18' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocKTGT4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'KTGT4' , '2017-4-17' , '2017-6-18' , 5 , '0-0-5' )";
//
//
//        //PTTKM1
//        String insertKhoangThoiGianHocPTTKM1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTTKM1' , '2017-1-2' , '2017-1-22' , 2 , '0-0-2' )";
//        String insertKhoangThoiGianHocPTTKM1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTTKM1' , '2017-2-6' , '2017-2-12' , 1 , '0-2-4' )";
//        String insertKhoangThoiGianHocPTTKM1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTTKM1' , '2017-2-13' , '2017-4-2' , 2 , '0-0-2' )";
//        String insertKhoangThoiGianHocPTTKM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTTKM2' , '2017-1-2' , '2017-1-8' , 4 , '0-0-3' )";
//        String insertKhoangThoiGianHocPTTKM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTTKM2' , '2017-1-9' , '2017-1-22' , 4 , '0-0-1' )";
//        String insertKhoangThoiGianHocPTTKM2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTTKM2' , '2017-2-6' , '2017-2-12' , 4 , '0-1-3' )";
//        String insertKhoangThoiGianHocPTTKM2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTTKM2' , '2017-2-13' , '2017-4-2' , 4 , '0-0-1' )";
//        String insertKhoangThoiGianHocPTTKM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTTKM3' , '2017-1-2' , '2017-1-22' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocPTTKM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTTKM3' , '2017-2-6' , '2017-3-19' , 5 , '0-0-5' )";
//        String insertKhoangThoiGianHocPTTKM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTTKM4' , '2017-1-2' , '2017-1-22' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocPTTKM4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'PTTKM4' , '2017-2-6' , '2017-3-19' , 5 , '0-0-3' )";
//
//
//        //TTANM1
//        String insertKhoangThoiGianHocTTANM1_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM1' , '2017-4-17' , '2017-4-30' , 1 , '0-4-5' )";
//        String insertKhoangThoiGianHocTTANM1_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM1' , '2017-4-17' , '2017-4-30' , 2 , '0-0-2' )";
//        String insertKhoangThoiGianHocTTANM1_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM1' , '2017-5-1' , '2017-6-18' , 2 , '0-4-5' )";
//        String insertKhoangThoiGianHocTTANM2_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM2' , '2017-4-17' , '2017-4-30' , 3 , '0-4-5' )";
//        String insertKhoangThoiGianHocTTANM2_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM2' , '2017-4-17' , '2017-4-30' , 4 , '0-0-2' )";
//        String insertKhoangThoiGianHocTTANM2_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM2' , '2017-5-1' , '2017-6-18' , 3 , '0-0-5' )";
//        String insertKhoangThoiGianHocTTANM2_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM2' , '2017-5-1' , '2017-6-18' , 4 , '0-0-4' )";
//        String insertKhoangThoiGianHocTTANM3_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM3' , '2017-4-17' , '2017-4-30' , 5 , '0-3-5' )";
//        String insertKhoangThoiGianHocTTANM3_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM3' , '2017-5-1' , '2017-5-7' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocTTANM3_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM3' , '2017-5-8' , '2017-5-14' , 5 , '0-3-5' )";
//        String insertKhoangThoiGianHocTTANM3_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM3' , '2017-5-15' , '2017-5-21' , 5 , '3-4-5' )";
//        String insertKhoangThoiGianHocTTANM3_5 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM3' , '2017-5-22' , '2017-5-28' , 5 , '0-3-4' )";
//        String insertKhoangThoiGianHocTTANM3_6 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM3' , '2017-5-29' , '2017-6-18' , 5 , '0-0-3' )";
//        String insertKhoangThoiGianHocTTANM4_1 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM4' , '2017-4-17' , '2017-4-30' , 5 , '0-1-2' )";
//        String insertKhoangThoiGianHocTTANM4_2 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM4' , '2017-5-1' , '2017-5-7' , 5 , '0-0-4' )";
//        String insertKhoangThoiGianHocTTANM4_3 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM4' , '2017-5-8' , '2017-6-4' , 5 , '0-0-2' )";
//        String insertKhoangThoiGianHocTTANM4_4 = " INSERT INTO " + TB_KHOANGTHOIGIANHOC + " VALUES "
//                + " ( 'TTANM4' , '2017-6-5' , '2017-6-18' , 5 , '1-2-4' )";
//
//
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_6);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT1_7);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_6);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT2_7);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocATMMT4_3);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT4_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocDGKDHT4_4);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocGTATM4_1);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT1_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocKTGT4_1);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocPTTKM4_2);
//
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM1_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM1_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM1_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM2_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM2_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM2_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM2_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_4);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_5);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM3_6);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM4_1);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM4_2);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM4_3);
//        sqLiteDatabase.execSQL(insertKhoangThoiGianHocTTANM4_4);
//
//
//
//
//        /*******************************************END KHOA 10**********************************/



    }

    public SQLiteDatabase db;

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // ví dụ cách update database dành cho database version < 2
//        if (oldVersion < 2) {
//            db.execSQL("ALTER TABLE " + TABLE_NOTE + " ADD COLUMN " + KEY_LAST_MODIFIED_NOTE + " TEXT DEFAULT \'\'");
//        }
    }

    //open database
    public void open() {
        try {
            db = getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  SQLiteDatabase open1(){
        return this.getWritableDatabase();
    }

    //close database
    public void close() {
        if (db != null && db.isOpen()) {
            try {
                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /************************* Các phương thức dùng cho database *******************/

    /**
     * lấy all row of table với câu lênh sql rồi trả về cursor
     * cursor chạy từ đầu đến nơi nới cần lấy data
     */
    public Cursor getAll(String sql) {
        open();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        close();
        return cursor;
    }

    /**
     * insert contentvaluse to table
     * value of data want insert
     * trả về : index row insert
     */
    public long insert(String table, ContentValues values) {
        open();
        long index = db.insert(table, null, values);
        close();
        return index;
    }

    /**
     * update giá trị cho table
     * return index row update
     */
    public boolean update(String table, ContentValues values, String where) {
        open();
        long index = db.update(table, values, where, null);
        close();
        return index > 0;
    }

    /**
     * delete id row of table
     */
    public boolean delete(String table, String where) {
        open();
        long index = db.delete(table, where, null);
        close();
        return index > 0;
    }


    /************************* end *******************/

    /*********************** Các phương thức dùng cho note table *******************/

    //lấy Note từ sql command
    public Note getNote(String sql) {
        Note note = null;
        Cursor cursor = getAll(sql);
        if (cursor != null) {
            note = cursorToNote(cursor);
            cursor.close();
        }
        return note;
    }

    /**
     * lấy danh sách note bằng sql command
     * return về danh sách note
     */
    public ArrayList<Note> getListNote(String sql) {
        ArrayList<Note> list = new ArrayList<>();
        Cursor cursor = getAll(sql);

        while (!cursor.isAfterLast()) {
            list.add(cursorToNote(cursor));
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }

    /**
     * insert note vào table
     * return id của note
     */
    public long insertNote(Note note) {
        return insert(TB_NOTE, noteToValues(note));
    }

    /**
     * note to update
     * return id của note update
     */
    public boolean updateNote(Note note) {
        return update(TB_NOTE, noteToValues(note), TB_NOTE_ID + " = " + note.getId());
    }


    //delete id row of table
    public boolean deleteNote(String where) {
        return delete(TB_NOTE, where);
    }


    /**
     * chuyển note sang contentvalues
     * ko gửi id của note bởi vì
     * khi insert id sẽ tự động dc tạo ( do để id tự tăng)
     * khi update ta ko update id
     */
    private ContentValues noteToValues(Note note) {
        ContentValues values = new ContentValues();
        values.put(TB_NOTE_TITLE, note.getTitle());
        values.put(TB_NOTE_CONTENT, note.getContent());
        values.put(TB_NOTE_LAST_MODIFIED, note.getLastModified());
        return values;
    }

    /**
     * chuyển contentvalues sang note
     */
    public Note cursorToNote(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getInt(cursor.getColumnIndex(TB_NOTE_ID)))
                .setTitle(cursor.getString(cursor.getColumnIndex(TB_NOTE_TITLE)))
                .setContent(cursor.getString(cursor.getColumnIndex(TB_NOTE_CONTENT)))
                .setLastModified(cursor.getString(cursor.getColumnIndex(TB_NOTE_LAST_MODIFIED)));
        return note;
    }
    /************************* end *******************/
}




