package com.bpass.kmastudent.DTO;


public class KhoangThoiGianDTO {

    public int getCAHOC() {
        return CAHOC;
    }

    public void setCAHOC(int CAHOC) {
        this.CAHOC = CAHOC;
    }

    public int CAHOC;
    public String getNGAYBATDAU() {
        return NGAYBATDAU;
    }

    public  void setNGAYBATDAU(String NGAYBATDAU) {
        this.NGAYBATDAU = NGAYBATDAU;
    }

    public String getNGAYKETTHUC() {
        return NGAYKETTHUC;
    }

    public  void setNGAYKETTHUC(String NGAYKETTHUC) {
        this.NGAYKETTHUC = NGAYKETTHUC;
    }


    private String NGAYBATDAU;
    private String NGAYKETTHUC;

    public String getTHU() {
        return THU;
    }

    public void setTHU(String THU) {
        this.THU = THU;
    }

    private String THU;

    public String getIDLOPHOC() {
        return IDLOPHOC;
    }

    public  void setIDLOPHOC(String IDLOPHOC) {
        this.IDLOPHOC = IDLOPHOC;
    }

    private String IDLOPHOC;

}
