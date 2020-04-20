package com.example.doan2019;

import java.io.Serializable;
import java.sql.Date;

public class TranDauDuongClass implements Serializable {
    int idTranDau;
    DoiBongClass doiBongDoiThu;
    DoiBongClass doiMinh;
    Date ngay;
    int khungGio;
    int soBanThangBenMinh;
    int soBanThangDoiThu;

    public TranDauDuongClass(int idTranDau, DoiBongClass doiBongDoiThu, DoiBongClass doiMinh, Date ngay, int khungGio, int soBanThangBenMinh, int soBanThangDoiThu) {
        this.idTranDau = idTranDau;
        this.doiBongDoiThu = doiBongDoiThu;
        this.doiMinh = doiMinh;
        this.ngay = ngay;
        this.khungGio = khungGio;
        this.soBanThangBenMinh = soBanThangBenMinh;
        this.soBanThangDoiThu = soBanThangDoiThu;
    }

    public DoiBongClass getDoiMinh() {
        return doiMinh;
    }

    public void setDoiMinh(DoiBongClass doiMinh) {
        this.doiMinh = doiMinh;
    }

    public int getIdTranDau() {
        return idTranDau;
    }

    public void setIdTranDau(int idTranDau) {
        this.idTranDau = idTranDau;
    }

    public DoiBongClass getDoiBongDoiThu() {
        return doiBongDoiThu;
    }

    public void setDoiBongDoiThu(DoiBongClass doiBongDoiThu) {
        this.doiBongDoiThu = doiBongDoiThu;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getKhungGio() {
        return khungGio;
    }

    public void setKhungGio(int khungGio) {
        this.khungGio = khungGio;
    }

    public int getSoBanThangBenMinh() {
        return soBanThangBenMinh;
    }

    public void setSoBanThangBenMinh(int soBanThangBenMinh) {
        this.soBanThangBenMinh = soBanThangBenMinh;
    }

    public int getSoBanThangDoiThu() {
        return soBanThangDoiThu;
    }

    public void setSoBanThangDoiThu(int soBanThangDoiThu) {
        this.soBanThangDoiThu = soBanThangDoiThu;
    }
}
