package com.example.doan2019.Retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;

public interface JsonApiDoiBongNGuoiDung {
    @GET("thanhvien")
    Call<List<DoiBong_NguoiDung>> getThanhViens(@HeaderMap Map<String, String> headers);

    @GET("doitruongcacdoi/{id}")
    Call<List<DoiBong>> getDoitruongcacdois(@Path("id") int groupId);
}
