package com.example.doan2019.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DoiBong_NguoiDung implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("doibong_id")
    @Expose
    private Integer doibongId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("phanquyen_id")
    @Expose
    private Integer phanquyenId;
    @SerializedName("trangthai")
    @Expose
    private Integer trangthai;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    private User user;
    private DoiBong doibong;

    public DoiBong_NguoiDung(Integer doibongId, Integer userId) {
        this.doibongId = doibongId;
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DoiBong getDoibong() {
        return doibong;
    }

    public void setDoibong(DoiBong doibong) {
        this.doibong = doibong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoibongId() {
        return doibongId;
    }

    public void setDoibongId(Integer doibongId) {
        this.doibongId = doibongId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPhanquyenId() {
        return phanquyenId;
    }

    public void setPhanquyenId(Integer phanquyenId) {
        this.phanquyenId = phanquyenId;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
