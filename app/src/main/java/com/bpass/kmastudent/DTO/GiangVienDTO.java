package com.bpass.kmastudent.DTO;

import java.io.Serializable;

/**
 * Created by VietDung-KMA-AT11D on 11/18/16.
 */

public class GiangVienDTO implements Serializable {
    private String IDGiangVien;
    private String tenGiangVien;
    private String khoaGiangVien;
    private String emailGiangVien;
    private String gioiThieuGiangVien;
    private byte[] anhGiangVien;
    private String gioiTinhGiangVien;

    public GiangVienDTO() {

    }

    public GiangVienDTO(String IDGiangVien, String tenGiangVien, String khoaGiangVien, String emailGiangVien, String gioiThieuGiangVien, byte[] anhGiangVien) {
        this.IDGiangVien = IDGiangVien;
        this.tenGiangVien = tenGiangVien;
        this.khoaGiangVien = khoaGiangVien;
        this.emailGiangVien = emailGiangVien;
        this.gioiThieuGiangVien = gioiThieuGiangVien;
        this.anhGiangVien = anhGiangVien;

    }

    public String getIDGiangVien() {
        return IDGiangVien;
    }

    public void setIDGiangVien(String IDGiangVien) {
        this.IDGiangVien = IDGiangVien;
    }

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }

    public String getKhoaGiangVien() {
        return khoaGiangVien;
    }

    public void setKhoaGiangVien(String khoaGiangVien) {
        this.khoaGiangVien = khoaGiangVien;
    }

    public String getEmailGiangVien() {
        return emailGiangVien;
    }

    public void setEmailGiangVien(String emailGiangVien) {
        this.emailGiangVien = emailGiangVien;
    }

    public String getGioiThieuGiangVien() {
        return gioiThieuGiangVien;
    }

    public void setGioiThieuGiangVien(String gioiThieuGiangVien) {
        this.gioiThieuGiangVien = gioiThieuGiangVien;
    }

    public byte[] getAnhGiangVien() {
        return anhGiangVien;
    }

    public void setAnhGiangVien(byte[] anhGiangVien) {
        this.anhGiangVien = anhGiangVien;
    }

    public String getGioiTinhGiangVien() {
        return gioiTinhGiangVien;
    }

    public void setGioiTinhGiangVien(String gioiTinhGiangVien) {
        this.gioiTinhGiangVien = gioiTinhGiangVien;
    }
}
