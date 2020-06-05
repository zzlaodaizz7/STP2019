package com.example.doan2019.Retrofit;

import java.sql.Timestamp;

public class ThongBao {
    private int id, user_id;
    private String noidung, loaithongbao, device;
    private Timestamp created_at, updated_at;
    private String type, tittle, content;

    public ThongBao(int user_id, String noidung, String loaithongbao, String device) {
        this.user_id = user_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
