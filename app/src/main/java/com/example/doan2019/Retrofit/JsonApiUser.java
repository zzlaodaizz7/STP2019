package com.example.doan2019.Retrofit;

import android.content.Context;
import android.widget.Toast;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface JsonApiUser {
    @POST("register")
    Call<String> register(@Body User user);

    @PUT("capnhatnguoidung/{id}")
    Call<String> update(@Body User user, @Path("id") int id);

    @GET("nguoidung/{id}")
    Call<User> getNguoiDung(@Path("id") int id);

    @Multipart
    @POST("upload")
    Call<String> upload(@Part MultipartBody.Part photo);
}
