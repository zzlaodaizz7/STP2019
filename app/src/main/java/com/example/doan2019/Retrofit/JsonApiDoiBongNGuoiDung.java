package com.example.doan2019.Retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonApiDoiBongNGuoiDung {
    @GET("thanhvien")
    Call<List<DoiBong_NguoiDung>> getThanhViens(@HeaderMap Map<String, String> headers);

    @GET("doitruongcacdoi/{id}")
    Call<List<DoiBong>> getDoitruongcacdois(@Path("id") int groupId);

    @GET("danhsachthanhvien/{id}")
    Call<List<DoiBong_NguoiDung>> getDanhSachThanhVien(@Path("id") int id);

    @POST("thanhvien")
    Call<DoiBong_NguoiDung> postThanhVien(@Body DoiBong_NguoiDung doiBong_nguoiDung);

    @GET("cacdoidangthamgia/{id}")
    Call<List<DoiBong_NguoiDung>> getCacDoiDangThamGia(@Path("id") int id);

    @PUT("thanhvien/{id}")
    Call<DoiBong_NguoiDung> updateThanhVien(@Path("id") int id);

    @DELETE("thanhvien/{id}")
    Call<DoiBong_NguoiDung> deleteThanhVien(@Path("id") int id);
}
