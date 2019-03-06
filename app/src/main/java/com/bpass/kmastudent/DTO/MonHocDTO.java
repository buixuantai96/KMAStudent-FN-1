package com.bpass.kmastudent.DTO;

import java.io.Serializable;

/**
 * Created by VietDung-KMA-AT11D on 11/16/16.
 */

public class MonHocDTO implements Serializable {
    private String idMonHoc;
    private String idNamKy;
    private String tenMonHoc;
    private String taiLieuThamKhao;
    private int soTinChi;
    private float doKho;
    private String gioiThieuMonHoc;
    private int soLuongLop;
    private int hinhAnh;
    private String tenLopHoc;
    private int doUuTien;


    public MonHocDTO() {

    }

    public int getDoUuTien() {
        return doUuTien;
    }

    public void setDoUuTien(int doUuTien) {
        this.doUuTien = doUuTien;
    }

    public String getIdMonHoc() {
        return idMonHoc;
    }

    public void setIdMonHoc(String idMonHoc) {
        this.idMonHoc = idMonHoc;
    }

    public String getIdNamKy() {
        return idNamKy;
    }

    public void setIdNamKy(String idNamKy) {
        this.idNamKy = idNamKy;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getTaiLieuThamKhao() {
        return taiLieuThamKhao;
    }

    public void setTaiLieuThamKhao(String taiLieuThamKhao) {
        this.taiLieuThamKhao = taiLieuThamKhao;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public float getDoKho() {
        return doKho;
    }

    public void setDoKho(float doKho) {
        this.doKho = doKho;
    }

    public String getGioiThieuMonHoc() {
        return gioiThieuMonHoc;
    }

    public void setGioiThieuMonHoc(String gioiThieuMonHoc) {
        this.gioiThieuMonHoc = gioiThieuMonHoc;
    }

    public int getSoLuongLop() {
        return soLuongLop;
    }

    public void setSoLuongLop(int soLuongLop) {
        this.soLuongLop = soLuongLop;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenLopHoc() {
        return tenLopHoc;
    }

    public void setTenLopHoc(String tenLopHoc) {
        this.tenLopHoc = tenLopHoc;
    }

    @Override
    public String toString() {
        return this.tenLopHoc;
    }
}
