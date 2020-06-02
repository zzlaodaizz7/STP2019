package com.example.doan2019.Retrofit;

import android.net.wifi.WifiManager;

import com.example.doan2019.MainActivity;

public class APIUtils {
    MainActivity mainActivity;
    //public static final String BASE_URL ="https://e81419860da2.ngrok.io/DoAn/public/";
    public static final String BASE_URL = "http://192.168.1.6:8008/DoAn/public/";
    //public static final String BASE_URL = "http://" + MainActivity.ipDx + "DoAn/public/";

    public static JsonApiKhungGio getJsonApiKhungGio() {
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL + "api/").create(JsonApiKhungGio.class);
    }

    public static JsonApiSanBong getJsonApiSanBong() {
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL + "api/").create(JsonApiSanBong.class);
    }

    public static JsonApiDangTin getJsonApiDangTin() {
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL + "api/").create(JsonApiDangTin.class);
    }

    public static JsonApiDoiBong getJsonApiDoiBong() {
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL + "api/").create(JsonApiDoiBong.class);
    }

    public static JsonApiBatDoi getJsonApiBatDoi() {
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL + "api/").create(JsonApiBatDoi.class);
    }

    public static JsonApiUser getJsonApiUser() {
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL + "api/").create(JsonApiUser.class);
    }

    public static JsonApiDoiBongNGuoiDung getJsonApiDoiBongNguoiDung() {
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL + "api/").create(JsonApiDoiBongNGuoiDung.class);
    }

    public static JsonApiThongBao getJsonApiThongBao() {
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL + "api/").create(JsonApiThongBao.class);
    }

}
