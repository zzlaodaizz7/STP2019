package com.example.doan2019.Retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface JsonApiBatDoi {
    @GET("batdoi")
    Call<List<BatDoi>> getBatDois(@HeaderMap Map<String, String> headers);


    @POST("batdoi")
    Call<BatDoi> createBatDoi(@HeaderMap Map<String, String> headers, @Body BatDoi batDoi);
}
