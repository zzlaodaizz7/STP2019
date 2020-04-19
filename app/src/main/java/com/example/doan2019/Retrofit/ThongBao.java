package com.example.doan2019.Retrofit;

import java.sql.Timestamp;

public class ThongBao {
    private int id, nguoidung_id;
    private String noidung, loaithongbao, device;
    private Timestamp created_at, updated_at;
    private String type, tittle, content;

    public ThongBao(int nguoidung_id, String noidung, String loaithongbao, String device) {
        this.nguoidung_id = nguoidung_id;
        this.noidung = noidung;
        this.loaithongbao = loaithongbao;
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNguoidung_id() {
        return nguoidung_id;
    }

    public void setNguoidung_id(int nguoidung_id) {
        this.nguoidung_id = nguoidung_id;
    }

    public String getLoaithongbao() {
        return loaithongbao;
    }

    public void setLoaithongbao(String loaithongbao) {
        this.loaithongbao = loaithongbao;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
