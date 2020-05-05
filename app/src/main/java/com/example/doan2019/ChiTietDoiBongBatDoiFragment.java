package com.example.doan2019;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.HanhKiem;
import com.example.doan2019.Retrofit.JsonApiSanBong;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDoiBongBatDoiFragment extends Fragment {
    private View view;
    TextView txtTenDoiBong, txtDiem, txtDiaChi, txtTrinhDo, txtNgayThanhlap, txtPhone, txtDongYBatDoi, txtQuayLai;
    Bundle bundle;
    ImageView imgAnhBia, imgDaiDien;
    RatingBar rtb;
    SharedPreferences sharedPreferences;
    JsonApiSanBong jsonApiSanBong;
    String Auth ="";
    Integer IDUser;
    DoiBongClass doiBongClass;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_chi_tiet_doi_bong_bat_doi, container, false);
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        IDUser = sharedPreferences.getInt("id",0);
        Auth = sharedPreferences.getString("token","");
        Mapping();

        ClickQuayLai();

        GanDuLieu();

        ClickDongYBatDoi();

        ClickDanhGia();

        ClickSao();

        return view;
    }

    private void ClickSao() {
        rtb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rtb.getRating() == 0.5)
                    rtb.setRating(1);
                else if(rtb.getRating() == 1.5)
                    rtb.setRating(2);
                else if(rtb.getRating() == 2.5)
                    rtb.setRating(3);
                else if(rtb.getRating() == 3.5)
                    rtb.setRating(4);
                else if(rtb.getRating() == 4.5)
                    rtb.setRating(5);
                android.app.AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Thông báo");
                alertDialogBuilder.setMessage("Bạn chắc chắn về quyết định của mình chưa?");
                //null should be your on click listener
                alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HanhKiem hanhKiem = new HanhKiem(IDUser,doiBongClass.getId(),Math.round(rtb.getRating()));
                        Map<String,String> header = new HashMap<>();
                        header.put("value","application/json");
                        header.put("Accept","application/json");
                        header.put("Authorization","Bearer "+Auth);
                        Call<HanhKiem> call = jsonApiSanBong.postHanhKiem(header,hanhKiem);
                        call.enqueue(new Callback<HanhKiem>() {
                            @Override
                            public void onResponse(Call<HanhKiem> call, Response<HanhKiem> response) {
                                System.out.println(response.code());
                                Toast.makeText(getContext(), "Voted", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(Call<HanhKiem> call, Throwable t) {
                                Toast.makeText(getContext(), "Đánh giá thành công", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialogBuilder.create().show();
            }
        });
    }

    private void ClickDanhGia() {
        rtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Số sao vote: " + rtb.getNumStars()
                        + "Số người vote: " + rtb.getRating(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ClickQuayLai() {
        txtQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void ClickDongYBatDoi() {
        txtDongYBatDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Đồng ý bắt đối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GanDuLieu() {
        bundle = getArguments();

        doiBongClass = (DoiBongClass) bundle.getSerializable("doibong");
        imgAnhBia.setImageBitmap(doiBongClass.getImageBia());
        imgDaiDien.setImageBitmap(doiBongClass.getImageDaiDien());
        txtTenDoiBong.setText(doiBongClass.getTen());
        txtDiem.setText(doiBongClass.getDiem() + "");
        txtDiaChi.setText(doiBongClass.getDiaChi());
        txtTrinhDo.setText(doiBongClass.getTrinhDo());
        txtNgayThanhlap.setText(doiBongClass.getNgayThanhLap());
        txtPhone.setText(doiBongClass.getSoDienThoai());
    }

    private void Mapping() {
        rtb = view.findViewById(R.id.RatingBarDoiBongBatDoi);
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
        imgAnhBia = view.findViewById(R.id.ImageViewBiaDoiBong);
        imgDaiDien = view.findViewById(R.id.ImageViewDaiDienDoiBong);
        txtDongYBatDoi = view.findViewById(R.id.TextViewChapNhanBatDoi);
        txtTenDoiBong = view.findViewById(R.id.TextViewName);
        txtDiem = view.findViewById(R.id.TextViewDiem);
        txtDiaChi = view.findViewById(R.id.TextViewDiaChi);
        txtTrinhDo = view.findViewById(R.id.TextViewTrinhDo);
        txtNgayThanhlap = view.findViewById(R.id.TextViewNgayThanhlap);
        txtPhone = view.findViewById(R.id.TextViewPhone);
    }
}
