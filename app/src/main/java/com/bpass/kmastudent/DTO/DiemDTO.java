package com.bpass.kmastudent.DTO;

import java.io.Serializable;

/**
 * Created by buixu on 16/11/2016.
 */

public class DiemDTO implements Serializable {

//    public DiemDTO(int IDMONHOC, String TENMH, int SoTin, float DIEMCC, float DIEMGK, float DIEMKTHP, float DIEMTK){
//        this.IDMONHOC = IDMONHOC;
//        this.TENMH = TENMH;
//        this.SoTin = SoTin;
//        this.DIEMCC = DIEMCC;
//        this.DIEMKTHP = DIEMKTHP;
//        this.DIEMGK = DIEMGK;
//        this.DIEMTK = DIEMTK;
//    }
//    public DiemDTO( String TENMH, int SoTin, float DIEMCC, float DIEMGK, float DIEMKTHP, float DIEMTK){
//
//        this.TENMH = TENMH;
//        this.SoTin = SoTin;
//        this.DIEMCC = DIEMCC;
//        this.DIEMKTHP = DIEMKTHP;
//        this.DIEMGK = DIEMGK;
//        this.DIEMTK = DIEMTK;
//    }


    public String getIDMONHOC() {
        return IDMONHOC;
    }

    public void setIDMONHOC(String IDMONHOC) {
        this.IDMONHOC = IDMONHOC;
    }
    private String IDMONHOC;
    private String TENMH;
    public static String MASV;
    private int SoTin;
    private String IDLOP;
    private String IDNamKy;

    public String getIDNamKy() {
        return IDNamKy;
    }

    public void setIDNamKy(String IDNamKy) {
        this.IDNamKy = IDNamKy;
    }

    public static String getMASV() {
        return MASV;
    }

    public static void setMASV(String MASV) {
        DiemDTO.MASV = MASV;
    }


    public String getIDLOP() {
        return IDLOP;
    }

    public void setIDLOP(String IDLOP) {
        this.IDLOP = IDLOP;
    }



    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    private int STT;
    private float DIEMCC;
    private float DIEMGK;
    private float DIEMKTHP;
    public static float DIEMTK;

    public float getDIEMTB() {
        return DIEMTB;
    }

    public void setDIEMTB(float DIEMTB) {
        this.DIEMTB = DIEMTB;
    }

    private float DIEMTB;


    public int getSoTin() {
        return SoTin;
    }

    public void setSoTin(int soTin) {
        SoTin = soTin;
    }


    public static float getDIEMTK() {
        return DiemDTO.DIEMTK;
    }

    public static void setDIEMTK(float DIEMTK) {
        DiemDTO.DIEMTK = DIEMTK;
    }

    public float getDIEMKTHP() {
        return DIEMKTHP;
    }

    public void setDIEMKTHP(float DIEMKTHP) {
        this.DIEMKTHP = DIEMKTHP;
    }

    public float getDIEMGK() {
        return DIEMGK;
    }

    public void setDIEMGK(float DIEMGK) {
        this.DIEMGK = DIEMGK;
    }

    public float getDIEMCC() {
        return DIEMCC;
    }

    public void setDIEMCC(float DIEMCC) {
        this.DIEMCC = DIEMCC;
    }

    public String getTENMH() {
        return TENMH;
    }

    public void setTENMH(String TENMH) {
        this.TENMH = TENMH;
    }


}
