package com.example.doan2019.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonApiThongBao {
    @GET("thongbao")
    Call<List<ThongBao>> getThongBaos();

    @POST("thongbao")
    Call<ThongBao> createThongBao(@Body ThongBao thongBao);
}
