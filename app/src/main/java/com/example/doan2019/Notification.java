package com.example.doan2019;

public class Notification {
    private int id;
    private int id_teamHost;
    private int id_teamGuest;
    private String noidung;
    private int loaiThongBao;

    public Notification(int id, int id_teamHost, int id_teamGuest, String noidung, int loaiThongBao) {
        this.id = id;
        this.id_teamHost = id_teamHost;
        this.id_teamGuest = id_teamGuest;
        this.noidung = noidung;
        this.loaiThongBao = loaiThongBao;
    }

    public Notification(String noidung){
        this.noidung = noidung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_teamHost() {
        return id_teamHost;
    }

    public void setId_teamHost(int id_teamHost) {
        this.id_teamHost = id_teamHost;
    }

    public int getId_teamGuest() {
        return id_teamGuest;
    }

    public void setId_teamGuest(int id_teamGuest) {
        this.id_teamGuest = id_teamGuest;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getLoaiThongBao() {
        return loaiThongBao;
    }

    public void setLoaiThongBao(int loaiThongBao) {
        this.loaiThongBao = loaiThongBao;
    }
}
