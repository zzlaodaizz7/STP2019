package com.example.doan2019.Retrofit;

import com.example.doan2019.DoiBongClass;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class DangTin implements Serializable {
//    private int doidangtin_id,san_id,khunggio_id;
//    private String ngay,keo;
//    private String type;
//    private String title;
//    private String content;
    private int id;
    private int doidangtin_id;
    private int doibatdoi_id;
    private DoiBongClass doibong1,doibong2;
    private List<User> listgoiy;
    private String ngay;
    private String keo;
    private int san_id;
    private int khunggio_id;
    private int voted;
    private int banthangdoidangtin,banthangdoibatdoi;
    private Timestamp created_at;
    private Timestamp updated_at;
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

    public DangTin(int doidangtin_id, String ngay, String keo, int san_id, int khunggio_id) {
        this.doidangtin_id = doidangtin_id;
        this.ngay = ngay;
        this.keo = keo;
        this.san_id = san_id;
        this.khunggio_id = khunggio_id;
    }

    public DangTin(int id, int doidangtin_id, int doibatdoi_id, String ngay, String keo, int san_id, int khunggio_id, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.doidangtin_id = doidangtin_id;
        this.doibatdoi_id = doibatdoi_id;
        this.ngay = ngay;
        this.keo = keo;
        this.san_id = san_id;
        this.khunggio_id = khunggio_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public DangTin(int id, String ngay, int san_id, int khunggio_id, String keo, Timestamp created_at, Timestamp updated_at, int doibatdoi_id) {
    }
    public List<User> getListgoiy() {

        return listgoiy;
    }

    public void setListgoiy(List<User> listgoiy) {
        this.listgoiy = listgoiy;
    }
    public DoiBongClass getDoibong1() {
        return doibong1;
    }

    public void setDoibong1(DoiBongClass doibong1) {
        this.doibong1 = doibong1;
    }

    public DoiBongClass getDoibong2() {
        return doibong2;
    }

    public void setDoibong2(DoiBongClass doibong2) {
        this.doibong2 = doibong2;
    }

    public int getBanthangdoidangtin() {
        return banthangdoidangtin;
    }

    public void setBanthangdoidangtin(int banthangdoidangtin) {
        this.banthangdoidangtin = banthangdoidangtin;
    }

    public int getBanthangdoibatdoi() {
        return banthangdoibatdoi;
    }

    public void setBanthangdoibatdoi(int banthangdoibatdoi) {
        this.banthangdoibatdoi = banthangdoibatdoi;
    }

    public int getVoted() {
        return voted;
    }

    public void setVoted(int voted) {
        this.voted = voted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getDoidangtin_id() {
        return doidangtin_id;
    }

    public void setDoidangtin_id(int doidangtin_id) {
        this.doidangtin_id = doidangtin_id;
    }

    public int getDoibatdoi_id() {
        return doibatdoi_id;
    }

    public void setDoibatdoi_id(int doibatdoi_id) {
        this.doibatdoi_id = doibatdoi_id;
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
}
