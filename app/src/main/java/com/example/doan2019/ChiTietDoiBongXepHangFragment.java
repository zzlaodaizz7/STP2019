package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChiTietDoiBongXepHangFragment extends Fragment {
    private View view;
    TextView txtTenDoiBong, txtDiem, txtDiaChi, txtTrinhDo, txtNgayThanhlap, txtPhone;
    Bundle bundle;
    Button btnThamGiaFC;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_xep_hang_doi_bong, container, false);
        Mapping();
        GanDuLieu();
        ClickThamGiaFC();

        return view;
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

        XepHangDoiBongClass xepHangDoiBongClass = (XepHangDoiBongClass) bundle.getSerializable("doibong");
        txtTenDoiBong.setText(xepHangDoiBongClass.getTen());
        txtDiem.setText(xepHangDoiBongClass.getDiem() + "");
        txtDiaChi.setText(xepHangDoiBongClass.getDiaChi());
        txtTrinhDo.setText(xepHangDoiBongClass.getTrinhDo());
        txtNgayThanhlap.setText(xepHangDoiBongClass.getNgayThanhLap());
        txtPhone.setText(xepHangDoiBongClass.getSoDienThoai());
    }

    private void Mapping() {
        btnThamGiaFC = view.findViewById(R.id.ButtonThamGiaVaoFC);
        txtTenDoiBong = view.findViewById(R.id.TextViewName);
        txtDiem = view.findViewById(R.id.TextViewDiem);
        txtDiaChi = view.findViewById(R.id.TextViewDiaChi);
        txtTrinhDo = view.findViewById(R.id.TextViewTrinhDo);
        txtNgayThanhlap = view.findViewById(R.id.TextViewNgayThanhlap);
        txtPhone = view.findViewById(R.id.TextViewPhone);
    }
}
