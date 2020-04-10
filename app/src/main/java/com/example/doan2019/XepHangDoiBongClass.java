package com.example.doan2019;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XepHangDoiBongClass implements Serializable {
    private String ten;
    private double diem;
    private String diaChi;
    private String trinhDo;
    private String ngayThanhLap;
    private String soDienThoai;
    private ArrayList<ThanhVienDoiBongClass> listThanhVien;

    public XepHangDoiBongClass(String ten, double diem, String diaChi, String trinhDo, String ngayThanhLap, String soDienThoai, ArrayList<ThanhVienDoiBongClass> listThanhVien) {
        this.ten = ten;
        this.diem = diem;
        this.diaChi = diaChi;
        this.trinhDo = trinhDo;
        this.ngayThanhLap = ngayThanhLap;
        this.soDienThoai = soDienThoai;
        this.listThanhVien = listThanhVien;
    }

    public XepHangDoiBongClass(String fc_fb, double v, String diaChi, String khá, int i, String soDienThoai, ArrayList<ThanhVienDoiBongClass> thanhVienDoiBongClasses) {
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

    public void setListThanhVien(ArrayList<ThanhVienDoiBongClass> listThanhVien) {
        this.listThanhVien = listThanhVien;
    }
}
