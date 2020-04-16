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

public class ChiTietDoiBongXepHangFragment extends Fragment {
    private View view;
    TextView txtTenDoiBong, txtDiem, txtDiaChi, txtTrinhDo, txtNgayThanhlap, txtPhone, txtQuayLai;
    Bundle bundle;
    Button btnThamGiaFC;
    ImageView imgAnhBia, imgDaiDien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_xep_hang_doi_bong, container, false);

        Mapping();

        ClickQuayLai();

        GanDuLieu();

        ClickThamGiaFC();

        return view;
    }

    private void ClickQuayLai() {
        txtQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void ClickThamGiaFC() {
        btnThamGiaFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Xin tham gia FC", Toast.LENGTH_SHORT).show();
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
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
        imgAnhBia = view.findViewById(R.id.ImageViewBiaDoiBong);
        imgDaiDien = view.findViewById(R.id.ImageViewDaiDienDoiBong);
        btnThamGiaFC = view.findViewById(R.id.ButtonThamGiaVaoFC);
        txtTenDoiBong = view.findViewById(R.id.TextViewName);
        txtDiem = view.findViewById(R.id.TextViewDiem);
        txtDiaChi = view.findViewById(R.id.TextViewDiaChi);
        txtTrinhDo = view.findViewById(R.id.TextViewTrinhDo);
        txtNgayThanhlap = view.findViewById(R.id.TextViewNgayThanhlap);
        txtPhone = view.findViewById(R.id.TextViewPhone);
    }
}
