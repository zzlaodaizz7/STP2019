package com.example.doan2019;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class DangTinDuongClass implements Serializable {
    private int ID;
    private Date ngayDienRaTranDau;
    private int idSanBong;
    private int khungGio;
    private String keo;
    private Timestamp created_at, updated_at;
    private int idDoiBatDoi;

    public DangTinDuongClass(int ID, Date ngayDienRaTranDau, int idSanBong, int khungGio, String keo, Timestamp created_at, Timestamp updated_at, int idDoiBatDoi) {
        this.ID = ID;
        this.ngayDienRaTranDau = ngayDienRaTranDau;
        this.idSanBong = idSanBong;
        this.khungGio = khungGio;
        this.keo = keo;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.idDoiBatDoi = idDoiBatDoi;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getIdDoiBatDoi() {
        return idDoiBatDoi;
    }

    public void setIdDoiBatDoi(int idDoiBatDoi) {
        this.idDoiBatDoi = idDoiBatDoi;
    }

    public Date getNgayDienRaTranDau() {
        return ngayDienRaTranDau;
    }

    public void setNgayDienRaTranDau(Date ngayDienRaTranDau) {
        this.ngayDienRaTranDau = ngayDienRaTranDau;
    }

    public int getIdSanBong() {
        return idSanBong;
    }

    public void setIdSanBong(int idSanBong) {
        this.idSanBong = idSanBong;
    }

    public int getKhungGio() {
        return khungGio;
    }

    public void setKhungGio(int khungGio) {
        this.khungGio = khungGio;
    }

    public String getKeo() {
        return keo;
    }

    public void setKeo(String keo) {
        this.keo = keo;
    }
}
