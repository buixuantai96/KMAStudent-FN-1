package com.bpass.kmastudent.DTO;

/**
 * Created by Hoang Nguyen on 16-Nov-16.
 */


public class SinhVienDTO {
    String MASV;
    String TENSV;
    String MATKHAU;
    String MAIL;
    /* Phục vụ việc đánh dấu chuỗi biến cờ thứ 2 */
    String LOGINSTATUS;

    public String getMASV() {
        return MASV;
    }

    public void setMASV(String MASV) {
        this.MASV = MASV;
    }

    public String getTENSV() {
        return TENSV;
    }

    public void setTENSV(String TENSV) {
        this.TENSV = TENSV;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public String getMAIL() {
        return MAIL;
    }

    public void setMAIL(String MAIL) {
        this.MAIL = MAIL;
    }

    public String getLOGINSTATUS() {
        return LOGINSTATUS;
    }

    public void setLOGINSTATUS(String LOGINSTATUS) {
        this.LOGINSTATUS = LOGINSTATUS;
    }
}
