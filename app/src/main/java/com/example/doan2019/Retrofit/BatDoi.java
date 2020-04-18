package com.example.doan2019.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class BatDoi {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("doibatdoi_id")
    @Expose
    private int doibatdoi_id;
    @SerializedName("doitimdoi_id")
    @Expose
    private int doitimdoi_id;
    @SerializedName("dangtin_id")
    @Expose
    private int dangtin_id;
    @SerializedName("trangtahi")
    @Expose
    private String trangthai;
    @SerializedName("created_at")
    @Expose
    private Timestamp created_at;
    @SerializedName("updated_at")
    @Expose
    private Timestamp updated_at;
    private String type;
    private String title;
    private String content;

    public BatDoi(int doibatdoi_id, int doitimdoi_id, int dangtin_id) {
        this.doibatdoi_id = doibatdoi_id;
        this.doitimdoi_id = doitimdoi_id;
        this.dangtin_id = dangtin_id;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoibatdoi_id() {
        return doibatdoi_id;
    }

    public void setDoibatdoi_id(int doibatdoi_id) {
        this.doibatdoi_id = doibatdoi_id;
    }

    public int getDoitimdoi_id() {
        return doitimdoi_id;
    }

    public void setDoitimdoi_id(int doitimdoi_id) {
        this.doitimdoi_id = doitimdoi_id;
    }

    public int getDangtin_id() {
        return dangtin_id;
    }

    public void setDangtin_id(int dangtin_id) {
        this.dangtin_id = dangtin_id;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }


}
