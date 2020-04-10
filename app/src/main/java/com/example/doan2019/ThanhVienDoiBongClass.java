package com.example.doan2019;

public class ThanhVienDoiBongClass {
    private String ten;
    private String chucVu;
    private int ID;

    public ThanhVienDoiBongClass(String ten, String chucVu, int ID) {
        this.ten = ten;
        this.chucVu = chucVu;
        this.ID = ID;
    }

    public ThanhVienDoiBongClass() {
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
