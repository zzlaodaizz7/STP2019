package com.example.doan2019.Retrofit;

public class KetQua {
    private int dangtin_id;
    private int doidangtin,doibatdoi;
    private String type;
    private String title;
    private String content;

    public KetQua(int dangtin_id, int doidangtin, int doibatdoi) {
        this.dangtin_id = dangtin_id;
        this.doidangtin = doidangtin;
        this.doibatdoi = doibatdoi;
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

    public int getDangtin_id() {
        return dangtin_id;
    }

    public void setDangtin_id(int dangtin_id) {
        this.dangtin_id = dangtin_id;
    }

    public int getDoidangtin() {
        return doidangtin;
    }

    public void setDoidangtin(int doidangtin) {
        this.doidangtin = doidangtin;
    }

    public int getDoibatdoi() {
        return doibatdoi;
    }

    public void setDoibatdoi(int doibatdoi) {
        this.doibatdoi = doibatdoi;
    }
}
