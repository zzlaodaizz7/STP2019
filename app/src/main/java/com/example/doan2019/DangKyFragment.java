package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DangKyFragment extends Fragment {
    private View view;
    private EditText edtTen, edtTaiKhoan, edtMatKhau, edtNhapLaiMatKhau;
    private Button btnDangKy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dang_ky, container, false);
        Mapping();
        ClickDangKy();


        return view;
    }

    private void ClickDangKy() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), edtTen.getText() + "\n"
                        + edtTaiKhoan.getText() + "\n"
                        + edtMatKhau.getText() + "\n"
                        +edtNhapLaiMatKhau.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Mapping() {
        edtTen = view.findViewById(R.id.EditTextTen);
        edtTaiKhoan = view.findViewById(R.id.EditTextTaiKhoan);
        edtMatKhau = view.findViewById(R.id.EditTextMatKhau);
        edtNhapLaiMatKhau = view.findViewById(R.id.EditTextNhapLaiMatKhau);
        btnDangKy = view.findViewById(R.id.ButtonDangKy);
    }
}
