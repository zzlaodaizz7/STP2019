package com.example.doan2019.Retrofit;

public class KhungGio {
    private int id;
    private String thoigian;
    private String created_at;
    private String updated_at;
    private int khunggio_id;

    public KhungGio(int id, String thoigian, String created_at, String updated_at) {
        this.id = id;
        this.thoigian = thoigian;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getKhunggio_id() {
        return khunggio_id;
    }

    public void setKhunggio_id(int khunggio_id) {
        this.khunggio_id = khunggio_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
