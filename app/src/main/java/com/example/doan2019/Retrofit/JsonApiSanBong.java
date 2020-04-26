package com.example.doan2019.Retrofit;

import com.example.doan2019.DangTinDuongClass;
import com.example.doan2019.DoiBongClass;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonApiSanBong {
    @GET("sanbong")
    Call<List<SanBong>> getSanbongs();
    @GET("sanbong/{id}")
    Call<SanBong> getChitietsanbong(@Path("id") int id);
    @GET("doitruongcacdoi/{id}")
    Call<List<DoiBong>> getDoitruongcacdois(@Path("id") int groupId);

//    @Headers({"value: application/json","Accept: application/json","Authorization: Bearer {Auth}"})
    @POST("dangtin")
    Call<DangTin> postDangTin(@HeaderMap Map<String, String> headers,
                              @Body DangTin dangTin
                              );
    @POST("login")
    Call<UserLogin> postLogin(@HeaderMap Map<String, String> header,
                     @Body UserLogin dangnhap
                     );
    @GET("cacdoidathamgia/{id}")
    Call<List<DoiBong>> getCacdoidathamgias(@Path("id") int id);
    @GET("danhsachthanhvien/{id}")
    Call<List<UserLogin>> getDanhsachthanhviens(@Path("id") int id);
    @POST("doibong")
    Call<DoiBong> postTaodoibongs(@HeaderMap Map<String, String> headers,
                                  @Body DoiBong doiBong
                                    );
    @GET("cactindadang/{id}")
    Call<List<DangTinDuongClass>> getDanhsachdangtins(@Path("id") int id);
    @GET("batdoi/{id}")
    Call<List<DoiBongClass>> getCacdoibatdoi(@HeaderMap Map<String,String> headers,
            @Path("id") int id);
    @PUT("batdoi/{id}")
    Call<DangTin> chotkeo(@HeaderMap Map<String,String> headers,
                   @Path("id") int id);
    @GET("chitietdoibong/{id}")
    Call<DoiBong> getChitietdoibong(@Path("id") int id);
    @GET("cactransapdienra/{id}")
    Call<List<DangTin>>  getCactransapdienra(@Path("id") int id);
    @GET("cactransapdienracuadoi/{id}")
    Call<List<DangTin>> getCactransapdienracuadoi(@Path("id") int id);
    @GET("cactrandaketthuc/{id}")
    Call<List<DangTin>> getCactrandaketthuc(@Path("id") int id);
    @POST("voteketqua")
    Call<KetQua> postVoted(@HeaderMap Map<String, String> headers,
                            @Body KetQua ketqua);
}
