package com.example.doan2019.Retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonApiSanBong {
    @GET("sanbong")
    Call<List<SanBong>> getSanbongs();

    @GET("doitruongcacdoi/{id}")
    Call<List<DoiBong>> getDoitruongcacdois(@Path("id") int groupId);

//    @Headers({"value: application/json","Accept: application/json","Authorization: Bearer {Auth}"})
    @POST("dangtin")
    Call<DangTin> postDangTin(@HeaderMap Map<String, String> headers,
                              @Body DangTin dangTin
                              );
}
