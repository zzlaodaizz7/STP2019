package com.example.doan2019.DTO;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class DangTinDTO implements Serializable {
    private int id;
    private int doidangtin_id;
    private String doidangtin_ten,device;
    private int doitruongdoidangtin_id;
    private String trangthai, trinhdo;
    private int doibatdoi_id;
    private  int doitruongdoibatdoi_id;
    private String doibatdoi_ten;
    private String ngay;
    private String keo;
    private int san_id;
    private String san_ten;
    private int khunggio_id;
    private String khunggio_thoigian;
    private Timestamp created_at;
    private Timestamp updated_at;


    public DangTinDTO(int id, int doidangtin_id, String doidangtin_ten, int doitruongdoidangtin_id, String trangthai, String trinhdo, int doibatdoi_id,
                      int doitruongdoibatdoi_id, String doibatdoi_ten, String ngay, String keo, int san_id, String san_ten, int khunggio_id,
                      String khunggio_thoigian, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.doidangtin_id = doidangtin_id;
        this.doidangtin_ten = doidangtin_ten;
        this.trangthai = trangthai;
        this.trinhdo = trinhdo;
        this.doibatdoi_id = doibatdoi_id;
        this.doibatdoi_ten = doibatdoi_ten;
        this.ngay = ngay;
        this.keo = keo;
        this.san_id = san_id;
        this.san_ten = san_ten;
        this.khunggio_id = khunggio_id;
        this.khunggio_thoigian = khunggio_thoigian;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.doitruongdoibatdoi_id = doitruongdoibatdoi_id;
        this.doitruongdoidangtin_id = doitruongdoidangtin_id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDoidangtin_ten() {
        return doidangtin_ten;
    }

    public void setDoidangtin_ten(String doidangtin_ten) {
        this.doidangtin_ten = doidangtin_ten;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTrinhdo() {
        return trinhdo;
    }

    public void setTrinhdo(String trinhdo) {
        this.trinhdo = trinhdo;
    }

    public String getDoibatdoi_ten() {
        return doibatdoi_ten;
    }

    public void setDoibatdoi_ten(String doibatdoi_ten) {
        this.doibatdoi_ten = doibatdoi_ten;
    }

    public String getSan_ten() {
        return san_ten;
    }

    public void setSan_ten(String san_ten) {
        this.san_ten = san_ten;
    }

    public String getKhunggio_thoigian() {
        return khunggio_thoigian;
    }

    public void setKhunggio_thoigian(String khunggio_thoigian) {
        this.khunggio_thoigian = khunggio_thoigian;
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

    public int getDoitruongdoidangtin_id() {
        return doitruongdoidangtin_id;
    }

    public void setDoitruongdoidangtin_id(int doitruongdoidangtin_id) {
        this.doitruongdoidangtin_id = doitruongdoidangtin_id;
    }

    public int getDoitruongdoibatdoi_id() {
        return doitruongdoibatdoi_id;
    }

    public void setDoitruongdoibatdoi_id(int doitruongdoibatdoi_id) {
        this.doitruongdoibatdoi_id = doitruongdoibatdoi_id;
    }
}
