package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChiTietThanhVienFragment extends Fragment {
    Bundle bundle;
    private View view;
    TextView txtTen, txtngayGiaNhap, txtDiaChi, txtQuayLai, txtSoDienThoai;
    ImageView imgDaiDien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_thanh_vien, container, false);

        Mapping();

        GetDuLieuThanhVien();

        ClickQuayLai();

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

    private void GetDuLieuThanhVien() {
        bundle = getArguments();

        ThanhVienDoiBongClass thanhVienDoiBongClass = (ThanhVienDoiBongClass) bundle.getSerializable("thanhvien");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String time = dateFormat.format(thanhVienDoiBongClass.getNgayRaNhap());

        imgDaiDien.setImageBitmap(thanhVienDoiBongClass.getImageDaiDien());
        txtTen.setText(thanhVienDoiBongClass.getTen());
        txtngayGiaNhap.setText(time);
        txtDiaChi.setText(thanhVienDoiBongClass.getDiaChi());
        txtSoDienThoai.setText(thanhVienDoiBongClass.getSoDienThoai());
    }

    private void Mapping() {
        txtSoDienThoai = view.findViewById(R.id.TextViewSoDienThoai);
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
        imgDaiDien = view.findViewById(R.id.ImageViewDaiDienThanhVien);
        txtTen = view.findViewById(R.id.TextViewName);
        txtngayGiaNhap = view.findViewById(R.id.TextViewNgayGiaNhap);
        txtDiaChi = view.findViewById(R.id.TextViewDiaChi);
    }
}
