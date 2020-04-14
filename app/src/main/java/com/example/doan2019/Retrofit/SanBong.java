package com.example.doan2019.Retrofit;

import java.sql.Date;
import java.sql.Timestamp;

public class SanBong {
    private int id;
    private String ten;
    private int songuoi;
    private String diachi;
    private String mota;
    private String sdt;
    private String link;
    private Timestamp created_at;
    private Timestamp updated_at;

    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public int getSonguoi() {
        return songuoi;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getMota() {
        return mota;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setSonguoi(int songuoi) {
        this.songuoi = songuoi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getSdt() {
        return sdt;
    }

    public String getLink() {
        return link;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }
}
