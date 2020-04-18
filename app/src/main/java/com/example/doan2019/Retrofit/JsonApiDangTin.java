package com.example.doan2019.Retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface JsonApiDangTin {
    @GET("dangtin")
    Call<List<DangTin>> getDangTins(@HeaderMap Map<String, String> headers);
}
