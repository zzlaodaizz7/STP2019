package com.example.doan2019.Retrofit;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String ten;
    private String email;
    private String email_verified_at;
    private String password;
    private String device;
    private String sdt;
    private String anh_bia;
    private String remember_token;
    private String created_at;
    private String update_at;

    public User(){}
    public User(int id, String ten, String email, String email_verified_at, String password, String sdt, String anh_bia, String remember_token, String created_at, String update_at) {
        this.id = id;
        this.ten = ten;
        this.email = email;
        this.email_verified_at = email_verified_at;
        this.password = password;
        this.sdt = sdt;
        this.anh_bia = anh_bia;
        this.remember_token = remember_token;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public User(String ten, String email, String password) {
        this.ten = ten;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAnh_bia() {
        return anh_bia;
    }

    public void setAnh_bia(String anh_bia) {
        this.anh_bia = anh_bia;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
