package com.example.doan2019;

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

public class ChiTietDoiBongBatDoiFragment extends Fragment {
    private View view;
    TextView txtTenDoiBong, txtDiem, txtDiaChi, txtTrinhDo, txtNgayThanhlap, txtPhone, txtDongYBatDoi, txtQuayLai;
    Bundle bundle;
    ImageView imgAnhBia, imgDaiDien;
    RatingBar rtb;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_doi_bong_bat_doi, container, false);

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
                Toast.makeText(getActivity(), "Số sao vote: " + rtb.getRating(), Toast.LENGTH_SHORT).show();
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

        DoiBongClass doiBongClass = (DoiBongClass) bundle.getSerializable("doibong");
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
