package com.example.doan2019;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DangKyFragment extends Fragment {
    private View view;
    private EditText edtTen, edtTaiKhoan, edtMatKhau, edtNhapLaiMatKhau;
    private Button btnDangKy;
    private CheckBox cbHienMatKhau;
    private ImageView imgVisibility;
    private TextView txtTrangThaiPass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dang_ky, container, false);

        Mapping();

        ClickDangKy();

        ClickHienMatKhau();

        return view;
    }

    private void ClickDangKy() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String BaoLoi = "";
                if(edtTen.getText().toString().equals(""))
                    BaoLoi += "Bạn chưa nhập Tên";
                if(edtTaiKhoan.getText().toString().equals("")){
                    if(!BaoLoi.equals(""))
                        BaoLoi += ", Tài khoản";
                    else
                        BaoLoi += "Bạn chưa nhập Tài khoản";
                }
                if(!edtMatKhau.getText().toString().equals(edtNhapLaiMatKhau.getText().toString()) ||
                edtMatKhau.getText().toString().equals("") || edtNhapLaiMatKhau.getText().toString().equals("")){
                    if(!BaoLoi.equals(""))
                        BaoLoi += "\n";
                    BaoLoi += "Nhập lại mật khẩu chưa đúng";
                }
                if(!BaoLoi.equals("")){
                    Toast.makeText(getActivity(), BaoLoi, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Cho phép đăng ký", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ClickHienMatKhau() {
        imgVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbHienMatKhau.isChecked()) {
                    cbHienMatKhau.setChecked(false);
                    edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    edtNhapLaiMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgVisibility.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    txtTrangThaiPass.setText("Ẩn");

                } else {
                    cbHienMatKhau.setChecked(true);
                    edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT);
                    edtNhapLaiMatKhau.setInputType(InputType.TYPE_CLASS_TEXT);
                    imgVisibility.setImageResource(R.drawable.ic_visibility_black_24dp);
                    txtTrangThaiPass.setText("Hiện");
                }
            }
        });
    }

    private void Mapping() {
        txtTrangThaiPass = view.findViewById(R.id.TextViewTrangThaiPass);
        imgVisibility = view.findViewById(R.id.ImageViewVisibility);
        cbHienMatKhau = view.findViewById(R.id.CheckBoxHienMatKhau);
        edtTen = view.findViewById(R.id.EditTextTen);
        edtTaiKhoan = view.findViewById(R.id.EditTextTaiKhoan);
        edtMatKhau = view.findViewById(R.id.EditTextMatKhau);
        edtNhapLaiMatKhau = view.findViewById(R.id.EditTextNhapLaiMatKhau);
        btnDangKy = view.findViewById(R.id.ButtonDangKy);
    }
}
