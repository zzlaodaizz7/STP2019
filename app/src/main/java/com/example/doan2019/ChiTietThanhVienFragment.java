package com.example.doan2019;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.User;
import com.squareup.picasso.Picasso;

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

        ClickSDT();

        return view;
    }

    private void ClickSDT() {
        txtSoDienThoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtSoDienThoai.getText().toString().equals("")) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + txtSoDienThoai.getText().toString()));
                    if (ActivityCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    getActivity().startActivity(callIntent);
                }
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

    private void GetDuLieuThanhVien() {
        bundle = getArguments();

        User thanhVienDoiBongClass = (User) bundle.getSerializable("thanhvien");
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String time = dateFormat.format(thanhVienDoiBongClass.getCreated_at());

        if (thanhVienDoiBongClass.getAnhbia() != null) {
            Picasso.get().load(thanhVienDoiBongClass.getAnhbia()).into(imgDaiDien);
        }

        String time = thanhVienDoiBongClass.getCreated_at();
        time = time.substring(0, 10);

        txtTen.setText(thanhVienDoiBongClass.getTen());
        txtngayGiaNhap.setText(time + "");
        txtDiaChi.setText("Địa chỉ");
        txtSoDienThoai.setText(thanhVienDoiBongClass.getSdt());
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
