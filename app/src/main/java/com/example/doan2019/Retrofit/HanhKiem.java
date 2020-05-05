package com.example.doan2019.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HanhKiem {
    @SerializedName("id")

    private int id;
    @SerializedName("user_id")

    private int user_id;
    @SerializedName("doiduocvote_id")

    private int doiduocvote_id;
    @SerializedName("hanhkiem")

    private int hanhkiem;
    private String type,title,content;
    public HanhKiem(int user_id, int doiduocvote_id, int hanhkiem) {
        this.user_id = user_id;
        this.doiduocvote_id = doiduocvote_id;
        this.hanhkiem = hanhkiem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDoiduocvote_id() {
        return doiduocvote_id;
    }

    public void setDoiduocvote_id(int doiduocvote_id) {
        this.doiduocvote_id = doiduocvote_id;
    }

    public int getHanhkiem() {
        return hanhkiem;
    }

    public void setHanhkiem(int hanhkiem) {
        this.hanhkiem = hanhkiem;
    }
}
