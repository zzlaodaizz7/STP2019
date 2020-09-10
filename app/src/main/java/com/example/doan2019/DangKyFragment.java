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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.JsonApiDangTin;
import com.example.doan2019.Retrofit.JsonApiUser;
import com.example.doan2019.Retrofit.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyFragment extends Fragment {
    private View view;
    private EditText edtTen, edtTaiKhoan, edtMatKhau, edtNhapLaiMatKhau, edtSDT, edtDiaChi;
    String ten, email, matkhau, nhaplaimatkhau, sdt, diachi;
    private Button btnDangKy;
    JsonApiUser jsonApiUser;
    private CheckBox cbHienMatKhau;
    private ImageView imgVisibility;
    private TextView txtTrangThaiPass;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dang_ky, container, false);
        jsonApiUser = APIUtils.getJsonApiUser();
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        Mapping();

        ClickDangKy();

        ClickHienMatKhau();

        return view;
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
    private void ClickDangKy() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ten = edtTen.getText().toString();
                email = edtTaiKhoan.getText().toString();
                matkhau = edtMatKhau.getText().toString();
                nhaplaimatkhau = edtNhapLaiMatKhau.getText().toString();
                sdt = edtSDT.getText().toString();
                diachi = edtDiaChi.getText().toString();

                if(ten.equals("") || email.equals("") || matkhau.equals("") || nhaplaimatkhau.equals("") || edtSDT.equals("")){
                    Toast.makeText(getActivity(), "Bạn hãy nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }
                else if(!matkhau.equals(nhaplaimatkhau)){
                    Toast.makeText(getActivity(), "Mật khẩu nhập lại không trùng khớp", Toast.LENGTH_LONG).show();
                }
                else{
                    User user = new User(ten, email, matkhau, diachi, sdt);
                    Call<String> call = jsonApiUser.register(user);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(getActivity(), "Bạn đã đăng ký thành công!", Toast.LENGTH_LONG).show();
                            ResetCacTruong();
                            langNgheSuKienChuyenFragment.ChuyenHuongFragment(new TaiKhoanFragment());
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getActivity(), "Bạn đã đăng ký thành công", Toast.LENGTH_LONG).show();
                            ResetCacTruong();
                            langNgheSuKienChuyenFragment.ChuyenHuongFragment(new TaiKhoanFragment());
                        }
                    });
                }
            }
        });
    }

    private void ResetCacTruong() {
        edtMatKhau.setText("");
        edtNhapLaiMatKhau.setText("");
        edtTaiKhoan.setText("");
        edtTen.setText("");
        edtSDT.setText("");
        edtDiaChi.setText("");
    }

    private void Mapping() {
        edtSDT = view.findViewById(R.id.EditTextSoDienThoai);
        txtTrangThaiPass = view.findViewById(R.id.TextViewTrangThaiPass);
        imgVisibility = view.findViewById(R.id.ImageViewVisibility);
        cbHienMatKhau = view.findViewById(R.id.CheckBoxHienMatKhau);
        edtTen = view.findViewById(R.id.EditTextTen);
        edtTaiKhoan = view.findViewById(R.id.EditTextTaiKhoan);
        edtMatKhau = view.findViewById(R.id.EditTextMatKhau);
        edtNhapLaiMatKhau = view.findViewById(R.id.EditTextNhapLaiMatKhau);
        btnDangKy = view.findViewById(R.id.ButtonDangKy);
        edtDiaChi = view.findViewById(R.id.EditTextDiaChi);
    }
}
