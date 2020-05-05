package com.example.doan2019;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class DangTinDuongClass implements Serializable {
    private int id;
    private String ngay;
    private int doidangtin_id;
    private int sanbong_id;
    private int khunggio_id;
    private String keo;
    private Timestamp created_at, updated_at;
    private int doibatdoi_id;

    public DangTinDuongClass(int id, String ngay, int doidangtin_id, int sanbong_id, int khunggio_id, String keo, Timestamp created_at, Timestamp updated_at, int doibatdoi_id) {
        this.id = id;
        this.ngay = ngay;
        this.doidangtin_id = doidangtin_id;
        this.sanbong_id = sanbong_id;
        this.khunggio_id = khunggio_id;
        this.keo = keo;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.doibatdoi_id = doibatdoi_id;
    }

    public int getDoidangtin_id() {
        return doidangtin_id;
    }

    public void setDoidangtin_id(int doidangtin_id) {
        this.doidangtin_id = doidangtin_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDoibatdoi_id() {
        return doibatdoi_id;
    }

    public void setDoibatdoi_id(int doibatdoi_id) {
        this.doibatdoi_id = doibatdoi_id;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getSanbong_id() {
        return sanbong_id;
    }

    public void setSanbong_id(int sanbong_id) {
        this.sanbong_id = sanbong_id;
    }

    public int getKhunggio_id() {
        return khunggio_id;
    }

    public void setKhunggio_id(int khunggio_id) {
        this.khunggio_id = khunggio_id;
    }

    public String getKeo() {
        return keo;
    }

    public void setKeo(String keo) {
        this.keo = keo;
    }
}
