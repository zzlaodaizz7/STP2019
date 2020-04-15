package com.example.doan2019;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class DoiBongClass implements Serializable {
    private int ID;
    private String ten;
    private double diem;
    private String diaChi;
    private String trinhDo;
    private String ngayThanhLap;
    private String soDienThoai;
    private Bitmap ImageBia, ImageDaiDien;
    private ArrayList<ThanhVienDoiBongClass> listThanhVien;

    public DoiBongClass(int ID, String ten, double diem, String diaChi, String trinhDo, String ngayThanhLap, String soDienThoai, Bitmap imageBia, Bitmap imageDaiDien, ArrayList<ThanhVienDoiBongClass> listThanhVien) {
        this.ID = ID;
        this.ten = ten;
        this.diem = diem;
        this.diaChi = diaChi;
        this.trinhDo = trinhDo;
        this.ngayThanhLap = ngayThanhLap;
        this.soDienThoai = soDienThoai;
        ImageBia = imageBia;
        ImageDaiDien = imageDaiDien;
        this.listThanhVien = listThanhVien;
    }

    public DoiBongClass(String ten, double diem, String diaChi, String trinhDo, String ngayThanhLap, String soDienThoai, Bitmap imageBia, Bitmap imageDaiDien, ArrayList<ThanhVienDoiBongClass> listThanhVien) {
        this.ten = ten;
        this.diem = diem;
        this.diaChi = diaChi;
        this.trinhDo = trinhDo;
        this.ngayThanhLap = ngayThanhLap;
        this.soDienThoai = soDienThoai;
        ImageBia = imageBia;
        ImageDaiDien = imageDaiDien;
        this.listThanhVien = listThanhVien;
    }

    public DoiBongClass() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getNgayThanhLap() {
        return ngayThanhLap;
    }

    public void setNgayThanhLap(String ngayThanhLap) {
        this.ngayThanhLap = ngayThanhLap;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public ArrayList<ThanhVienDoiBongClass> getListThanhVien() {
        return listThanhVien;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public Bitmap getImageBia() {
        return ImageBia;
    }

    public void setImageBia(Bitmap imageBia) {
        ImageBia = imageBia;
    }

    public Bitmap getImageDaiDien() {
        return ImageDaiDien;
    }

    public void setImageDaiDien(Bitmap imageDaiDien) {
        ImageDaiDien = imageDaiDien;
    }

    public void setListThanhVien(ArrayList<ThanhVienDoiBongClass> listThanhVien) {
        this.listThanhVien = listThanhVien;
    }
}
