package com.example.doan2019;

import java.io.Serializable;

public class TranDauDuongClass implements Serializable {
    int idTranDau;
    DoiBongClass doiBongDoiThu;
    DoiBongClass doiMinh;
    String ngay;
    int khungGio;
    int idSan;
    int banthangdoidangtin;
    int banthangdoibatdoi;
    String keo;
    int voted;

    public TranDauDuongClass(int idTranDau, DoiBongClass doiBongDoiThu, DoiBongClass doiMinh, String ngay, int khungGio, int idSan, int banthangdoidangtin, int banthangdoibatdoi, String keo, int voted) {
        this.idTranDau = idTranDau;
        this.doiBongDoiThu = doiBongDoiThu;
        this.doiMinh = doiMinh;
        this.ngay = ngay;
        this.khungGio = khungGio;
        this.idSan = idSan;
        this.banthangdoidangtin = banthangdoidangtin;
        this.banthangdoibatdoi = banthangdoibatdoi;
        this.keo = keo;
        this.voted = voted;
    }

    public int getVoted() {
        return voted;
    }

    public void setVoted(int voted) {
        this.voted = voted;
    }

    public String getKeo() {
        return keo;
    }

    public void setKeo(String keo) {
        this.keo = keo;
    }

    public int getIdSan() {
        return idSan;
    }

    public void setIdSan(int idSan) {
        this.idSan = idSan;
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

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getKhungGio() {
        return khungGio;
    }

    public void setKhungGio(int khungGio) {
        this.khungGio = khungGio;
    }

    public int getBanthangdoidangtin() {
        return banthangdoidangtin;
    }

    public void setBanthangdoidangtin(int banthangdoidangtin) {
        this.banthangdoidangtin = banthangdoidangtin;
    }

    public int getBanthangdoibatdoi() {
        return banthangdoibatdoi;
    }

    public void setBanthangdoibatdoi(int banthangdoibatdoi) {
        this.banthangdoibatdoi = banthangdoibatdoi;
    }
}
