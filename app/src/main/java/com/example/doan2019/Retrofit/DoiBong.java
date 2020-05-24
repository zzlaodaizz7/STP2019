package com.example.doan2019.Retrofit;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.sql.Timestamp;

public class DoiBong implements Serializable {
    private int id;
    private String ten;
    private String trinhdo;
    private String diachi;
    private String sdt;
    private String anhbia;
    private String anhdaidien;
    private int sodiem;
    private int hanhkiem;
    private Timestamp created_at,updated_at;
    private String type;
    private String title;
    private String content;
    private int user_id;
    public DoiBong(String ten, String trinhdo, String diachi, String sdt,int user_id) {
        this.ten = ten;
        this.trinhdo = trinhdo;
        this.diachi = diachi;
        this.sdt = sdt;
        this.user_id = user_id;
    }

    public DoiBong(int id, String ten, String trinhdo, String diachi, String sdt, String anhbia, String anhdaidien, int sodiem, int hanhkiem, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.ten = ten;
        this.trinhdo = trinhdo;
        this.diachi = diachi;
        this.sdt = sdt;
        this.anhbia = anhbia;
        this.anhdaidien = anhdaidien;
        this.sodiem = sodiem;
        this.hanhkiem = hanhkiem;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public DoiBong(String ten, String trinhdo, String diachi, String sdt, String anhbia, String anhdaidien, int sodiem, int hanhkiem, Timestamp created_at) {
        this.ten = ten;
        this.trinhdo = trinhdo;
        this.diachi = diachi;
        this.sdt = sdt;
        this.anhbia = anhbia;
        this.anhdaidien = anhdaidien;
        this.sodiem = sodiem;
        this.hanhkiem = hanhkiem;
        this.created_at = created_at;
    }

    public DoiBong(String ten, String trinhdo, String diachi, String sdt) {
        this.ten = ten;
        this.trinhdo = trinhdo;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAnhbia() {
        return anhbia;
    }

    public void setAnhbia(String anhbia) {
        this.anhbia = anhbia;
    }

    public String getAnhdaidien() {
        return anhdaidien;
    }

    public void setAnhdaidien(String anhdaidien) {
        this.anhdaidien = anhdaidien;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdate_at() {
        return updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTrinhdo() {
        return trinhdo;
    }

    public void setTrinhdo(String trinhdo) {
        this.trinhdo = trinhdo;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getSodiem() {
        return sodiem;
    }

    public void setSodiem(int sodiem) {
        this.sodiem = sodiem;
    }

    public int getHanhkiem() {
        return hanhkiem;
    }

    public void setHanhkiem(int hanhkiem) {
        this.hanhkiem = hanhkiem;
    }
}
