package com.example.doan2019.Retrofit;

public class DangTin {
    private int doidangtin_id,san_id,khunggio_id;
    private String ngay,keo;
    private String type;
    private String title;
    private String content;


    public DangTin(int doidangtin_id, int san_id, int khunggio_id, String ngay, String keo) {
        this.doidangtin_id = doidangtin_id;
        this.san_id = san_id;
        this.khunggio_id = khunggio_id;
        this.ngay = ngay;
        this.keo = keo;
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
    public int getDoidangtin_id() {
        return doidangtin_id;
    }

    public void setDoidangtin_id(int doidangtin_id) {
        this.doidangtin_id = doidangtin_id;
    }

    public int getSan_id() {
        return san_id;
    }

    public void setSan_id(int san_id) {
        this.san_id = san_id;
    }

    public int getKhunggio_id() {
        return khunggio_id;
    }

    public void setKhunggio_id(int khunggio_id) {
        this.khunggio_id = khunggio_id;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getKeo() {
        return keo;
    }

    public void setKeo(String keo) {
        this.keo = keo;
    }
}
