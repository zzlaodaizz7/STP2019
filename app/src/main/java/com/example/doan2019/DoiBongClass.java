package com.example.doan2019;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DoiBongClass implements Serializable {
    private int id,batdoi_id;
    private String ten;
    private int diem;
    private String diaChi;
    private String trinhDo;
    private String ngayThanhLap;
    private String soDienThoai;
    private Bitmap ImageBia, ImageDaiDien;
    private Timestamp created_at,updated_at;
    private ArrayList<ThanhVienDoiBongClass> listThanhVien;

    public DoiBongClass(int id, String ten, int diem, String diaChi, String trinhDo, String ngayThanhLap, String soDienThoai, Bitmap imageBia, Bitmap imageDaiDien, Timestamp created_at, Timestamp updated_at, ArrayList<ThanhVienDoiBongClass> listThanhVien) {
        this.id = id;
        this.ten = ten;
        this.diem = diem;
        this.diaChi = diaChi;
        this.trinhDo = trinhDo;
        this.ngayThanhLap = ngayThanhLap;
        this.soDienThoai = soDienThoai;
        ImageBia = imageBia;
        ImageDaiDien = imageDaiDien;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.listThanhVien = listThanhVien;
    }

    public DoiBongClass(int id, String ten, int diem, String diaChi, String trinhDo, String ngayThanhLap, String soDienThoai, Bitmap imageBia, Bitmap imageDaiDien, ArrayList<ThanhVienDoiBongClass> listThanhVien) {
        this.id = id;
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

    public DoiBongClass(String ten, int diem, String diaChi, String trinhDo, String ngayThanhLap, String soDienThoai, Bitmap imageBia, Bitmap imageDaiDien, ArrayList<ThanhVienDoiBongClass> listThanhVien) {
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

    public DoiBongClass(int id, int batdoi_id, String ten, int diem, String diaChi, String trinhDo, String soDienThoai, Timestamp created_at, Bitmap anhBia, Bitmap anhDaiDien, ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong) {
        this.id = id;
        this.batdoi_id = batdoi_id;
        this.ten = ten;
        this.diem = diem;
        this.diaChi = diaChi;
        this.trinhDo = trinhDo;
        this.soDienThoai = soDienThoai;
        this.created_at = created_at;
        ImageBia  = anhBia;
        ImageDaiDien = anhDaiDien;
        this.listThanhVien = listThanhVienDoiBong;
    }


    public int getBatdoi_id() {
        return batdoi_id;
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

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getDiem() {
        return diem;
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

    public void setDiem(int diem) {
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
