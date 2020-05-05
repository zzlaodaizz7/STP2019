package com.example.doan2019;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

public class ChiTietSanBongFragment extends Fragment {
    private View view;
    ImageView imgAnhBiaSanBong;
    TextView tvTenSanBong, tvDiaChi, tvPhone, tvSoNguoi, tvGioiThieu, tvQuayLai;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_san_bong, container, false);

        Mapping();

        GanDuLieu();

        ClickQuayLai();

        ClickTextViewSDT();

        return view;
    }

    private void ClickTextViewSDT() {
        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvPhone.getText().toString().equals("")) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + tvPhone.getText().toString()));
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
        tvQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void GanDuLieu() {
            bundle = getArguments();

            SanBongClass sanBong = (SanBongClass) bundle.getSerializable("sanbong");
            imgAnhBiaSanBong.setImageBitmap(sanBong.getImgSanBong());
            tvTenSanBong.setText(sanBong.getTenSanBong());
            tvDiaChi.setText(sanBong.getDiaChi());
            tvPhone.setText(sanBong.getSoDienThoai());
            tvSoNguoi.setText(sanBong.getSoNguoi() + " người");
            tvGioiThieu.setText(sanBong.getMoTa());
    }

    private void Mapping() {
        tvTenSanBong = view.findViewById(R.id.TextViewNameSanBong);
        tvQuayLai = view.findViewById(R.id.TextViewQuayLai);
        imgAnhBiaSanBong = view.findViewById(R.id.ImageViewBiaSanBong);
        tvDiaChi = view.findViewById(R.id.TextViewDiaChiSanBong);
        tvPhone = view.findViewById(R.id.TextViewPhoneSanBong);
        tvSoNguoi = view.findViewById(R.id.TextViewSoNguoi);
        tvGioiThieu = view.findViewById(R.id.TextViewGioiThieu);
    }
}
