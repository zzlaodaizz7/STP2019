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

public class ChiTietDoiBongDaThamGiaFragment extends Fragment {
    private View view;
    TextView txtTenDoiBong, txtDiem, txtDiaChi, txtTrinhDo, txtNgayThanhlap, txtPhone, txtDanhSachCacTinDaDang;
    Button btnThamGiaFC;
    ImageView imgAnhBia, imgAnhDaiDien;
    Bundle bundle;
    DoiBongClass doiBong;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_doi_bong_da_tham_gia, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        Mapping();

        GanNoiDung();

        ClickDanhSachCacTinDaDang();

        return view;
    }

    private void ClickDanhSachCacTinDaDang() {
        txtDanhSachCacTinDaDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DanhSachCacTinDaDangFragment danhSachCacTinDaDangFragment = new DanhSachCacTinDaDangFragment();
                Bundle bundleDanhSachCacTinDaDangFragment = new Bundle();
                bundleDanhSachCacTinDaDangFragment.putSerializable("doibong", doiBong);
                danhSachCacTinDaDangFragment.setArguments(bundleDanhSachCacTinDaDangFragment);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(danhSachCacTinDaDangFragment);
            }
        });
    }

    private void GanNoiDung() {
        bundle = getArguments();

        doiBong = (DoiBongClass) bundle.getSerializable("doibong");
        imgAnhBia.setImageBitmap(doiBong.getImageBia());
        imgAnhDaiDien.setImageBitmap(doiBong.getImageDaiDien());
        txtTenDoiBong.setText(doiBong.getTen());
        txtDiem.setText(doiBong.getDiem() + " Điểm");
        txtDiaChi.setText(doiBong.getDiaChi());
        txtTrinhDo.setText(doiBong.getTrinhDo());
        txtNgayThanhlap.setText(doiBong.getNgayThanhLap());
        txtPhone.setText(doiBong.getSoDienThoai());
    }

    private void Mapping() {
        txtDanhSachCacTinDaDang = view.findViewById(R.id.TextViewDanhSachCacTinDaDang);
        imgAnhBia = view.findViewById(R.id.ImageViewBiaDoiBong);
        imgAnhDaiDien = view.findViewById(R.id.ImageViewDaiDienDoiBong);
        txtTenDoiBong = view.findViewById(R.id.TextViewName);
        txtDiem = view.findViewById(R.id.TextViewDiem);
        txtDiaChi = view.findViewById(R.id.TextViewDiaChi);
        txtTrinhDo = view.findViewById(R.id.TextViewTrinhDo);
        txtNgayThanhlap = view.findViewById(R.id.TextViewNgayThanhlap);
        txtPhone = view.findViewById(R.id.TextViewPhone);
    }
}
