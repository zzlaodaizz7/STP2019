package com.example.doan2019;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.sql.Date;

public class ThanhVienDoiBongClass implements Serializable {
    private String ten;
    private String chucVu;
    private int ID;
    private Bitmap imageDaiDien;
    private String diaChi;
    private Date ngayRaNhap;
    private String soDienThoai;

    public ThanhVienDoiBongClass(String ten, String chucVu, int ID, Bitmap imageDaiDien, String diaChi, Date ngayRaNhap, String soDienThoai) {
        this.ten = ten;
        this.chucVu = chucVu;
        this.ID = ID;
        this.imageDaiDien = imageDaiDien;
        this.diaChi = diaChi;
        this.ngayRaNhap = ngayRaNhap;
        this.soDienThoai = soDienThoai;
    }

    public ThanhVienDoiBongClass() {
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Bitmap getImageDaiDien() {
        return imageDaiDien;
    }

    public void setImageDaiDien(Bitmap imageDaiDien) {
        this.imageDaiDien = imageDaiDien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayRaNhap() {
        return ngayRaNhap;
    }

    public void setNgayRaNhap(Date ngayRaNhap) {
        this.ngayRaNhap = ngayRaNhap;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
