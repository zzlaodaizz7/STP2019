package com.example.doan2019.Retrofit;

import android.graphics.Bitmap;

import java.sql.Timestamp;

public class UserLogin {
    private int id;
    private String ten,email,sdt,password,token,error;
    private Bitmap anhbia;
    private Timestamp created_at,updated_at;



    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getError() {
        return error;
    }
    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Bitmap getAnhbia() {
        return anhbia;
    }

    public void setAnhbia(Bitmap anhbia) {
        this.anhbia = anhbia;
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
}
