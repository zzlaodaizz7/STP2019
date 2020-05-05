package com.example.doan2019.Retrofit;

public class APIUtils {
    public static final String BASE_URL ="http://192.168.1.9/DoAn/public/api/";
    public static JsonApiKhungGio getJsonApiKhungGio(){
        return  RetrofitClientInstance.getRetrofitInstance(BASE_URL).create(JsonApiKhungGio.class);
    }
    public static JsonApiSanBong getJsonApiSanBong(){
        return  RetrofitClientInstance.getRetrofitInstance(BASE_URL).create(JsonApiSanBong.class);
    }
    public static JsonApiDangTin getJsonApiDangTin(){
        return  RetrofitClientInstance.getRetrofitInstance(BASE_URL).create(JsonApiDangTin.class);
    }
    public static JsonApiDoiBong getJsonApiDoiBong(){
        return  RetrofitClientInstance.getRetrofitInstance(BASE_URL).create(JsonApiDoiBong.class);
    }
    public static JsonApiBatDoi getJsonApiBatDoi(){
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL).create(JsonApiBatDoi.class);
    }
    public static JsonApiUser getJsonApiUser(){
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL).create(JsonApiUser.class);
    }
    public static JsonApiDoiBongNGuoiDung getJsonApiDoiBongNguoiDung(){
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL).create(JsonApiDoiBongNGuoiDung.class);
    }
    public static JsonApiThongBao getJsonApiThongBao(){
        return RetrofitClientInstance.getRetrofitInstance(BASE_URL).create(JsonApiThongBao.class);
    }

}
