package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChiTietDoiBongBatDoiFragment extends Fragment {
    private View view;
    TextView txtTenDoiBong, txtDiem, txtDiaChi, txtTrinhDo, txtNgayThanhlap, txtPhone, txtDongYBatDoi;
    Bundle bundle;
    ImageView imgAnhBia, imgDaiDien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_doi_bong_bat_doi, container, false);

        Mapping();

        GanDuLieu();

        ClickDongYBatDoi();

        return view;
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
