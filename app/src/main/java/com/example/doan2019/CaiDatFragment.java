package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CaiDatFragment extends Fragment {
    private View view;
    private TextView txtChiaSe, txtPhanHoi, txtDieuKhoan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cai_dat, container, false);

        Mapping();

        ClickChiaSe();

        ClickPhanHoi();

        ClickDieuKhoan();

        return view;
    }

    private void ClickDieuKhoan() {
        txtDieuKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Chức năng Điều Khoản đang phát triển", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ClickPhanHoi() {
        txtPhanHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Chức năng Phản Hồi đang phát triển", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ClickChiaSe() {
        txtChiaSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Chức năng Chia Sẻ đang phát triển", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Mapping() {
        txtChiaSe = view.findViewById(R.id.TextViewChiaSe);
        txtPhanHoi = view.findViewById(R.id.TextViewPhanHoi);
        txtDieuKhoan = view.findViewById(R.id.TextViewDieuKhoan);
    }
}
