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

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.JsonApiDangTin;
import com.example.doan2019.Retrofit.JsonApiUser;
import com.example.doan2019.Retrofit.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyFragment extends Fragment {
    private View view;
    private EditText edtTen, edtTaiKhoan, edtMatKhau, edtNhapLaiMatKhau;
    String ten, email, matkhau, nhaplaimatkhau;
    private Button btnDangKy;
    JsonApiUser jsonApiUser;
    private CheckBox cbHienMatKhau;
    private ImageView imgVisibility;
    private TextView txtTrangThaiPass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dang_ky, container, false);
        jsonApiUser = APIUtils.getJsonApiUser();
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



                if(ten.equals("") || email.equals("") || matkhau.equals("") || nhaplaimatkhau.equals("")){
                    Toast.makeText(getActivity(), "bạn hãy nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }
                else if(!matkhau.equals(nhaplaimatkhau)){
                    Toast.makeText(getActivity(), "Mật khẩu nhập lại không trùng khớp", Toast.LENGTH_LONG).show();
                }
                else{
                    User user = new User(ten, email, matkhau);
                    Call<String> call = jsonApiUser.register(user);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(getActivity(), "Bạn đã đăng ký thành công", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
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
