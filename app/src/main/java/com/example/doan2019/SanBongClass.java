package com.example.doan2019;

import android.graphics.Bitmap;

import java.io.Serializable;

public class SanBongClass implements Serializable {
    private int id;
    private String tenSanBong;
    private int soNguoi;
    private String diaChi;
    private String moTa;
    private String soDienThoai;
    private Bitmap imgSanBong;

    public SanBongClass(int id, String tenSanBong, int soNguoi, String diaChi, String moTa, String soDienThoai, Bitmap imgSanBong) {
        this.id = id;
        this.tenSanBong = tenSanBong;
        this.soNguoi = soNguoi;
        this.diaChi = diaChi;
        this.moTa = moTa;
        this.soDienThoai = soDienThoai;
        this.imgSanBong = imgSanBong;
    }

    public SanBongClass() {
    }

    public String getTenSanBong() {
        return tenSanBong;
    }

    public void setTenSanBong(String tenSanBong) {
        this.tenSanBong = tenSanBong;
    }

    public Bitmap getImgSanBong() {
        return imgSanBong;
    }

    public void setImgSanBong(Bitmap imgSanBong) {
        this.imgSanBong = imgSanBong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
