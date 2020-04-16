package com.example.doan2019;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.internal.InAppPurchaseActivityLifecycleTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DangNhapFragment extends Fragment {
    private Button btnQuenMatKhau, btnDangNhap;
    LoginButton loginButton;
    private View view;
    private CallbackManager callbackManager;
    private EditText edtTaiKhoan, edtMatKhau;
    private CheckBox cbLuuThongTin, cbHienMatKhau;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    ImageView imgVisibility;
    SharedPreferences sharedPreferences;
    TextView txtTrangThaiPass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        view = inflater.inflate(R.layout.fragment_dang_nhap, container, false);

        Mapping();

        LayGiaTriDaLuu();

        ClickDangNhapFaceBook();

        ClickDangNhap();

        ClickQuenMatKhau();

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
                    imgVisibility.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    txtTrangThaiPass.setText("Ẩn");

                } else {
                    cbHienMatKhau.setChecked(true);
                    edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT);
                    imgVisibility.setImageResource(R.drawable.ic_visibility_black_24dp);
                    txtTrangThaiPass.setText("Hiện");
                }
            }
        });
    }

    private void LayGiaTriDaLuu() {
        edtTaiKhoan.setText(sharedPreferences.getString("taikhoan", ""));
        edtMatKhau.setText(sharedPreferences.getString("matkhau", ""));
        cbLuuThongTin.setChecked(sharedPreferences.getBoolean("checked", false));
        Toast.makeText(getActivity(), "Token: " + sharedPreferences.getString("token", ""), Toast.LENGTH_SHORT).show();
    }

    private void ClickDangNhap() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taiKhoan = edtTaiKhoan.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                String token = "123456";
                String BatLoi = "";
                if(edtTaiKhoan.getText().toString().equals(""))
                    BatLoi += "Bạn chưa nhập Tài khoản";
                if(edtMatKhau.getText().toString().equals("")){
                    if(!BatLoi.equals(""))
                        BatLoi += ", Mật khẩu";
                    else
                        BatLoi += "Bạn chưa nhập Mật khẩu";
                }
                if(!BatLoi.equals("")){
                    Toast.makeText(getActivity(), BatLoi, Toast.LENGTH_SHORT).show();
                }
                else {
                    if (taiKhoan.equals("admin") && matKhau.equals("1234")) {
                        Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        if (cbLuuThongTin.isChecked()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("taikhoan", taiKhoan);
                            editor.putString("matkhau", matKhau);
                            editor.putBoolean("checked", true);
                            editor.putString("token", token);
                            editor.commit();
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("taikhoan");
                            editor.remove("matkhau");
                            editor.remove("checked");
                            editor.remove("token");
                            editor.commit();
                        }
                    } else
                        Toast.makeText(getActivity(), "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ClickQuenMatKhau() {
        btnQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Chức năng Quên mật khẩu đang phát triển", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ClickDangNhapFaceBook() {
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        SetLogin_Button();
    }

    private void SetLogin_Button() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Goi den ham MainActivity de chuyen huong
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(new TaiKhoanDaLoginFragment());
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    private void Mapping() {
        txtTrangThaiPass = view.findViewById(R.id.TextViewTrangThaiPass);
        imgVisibility = view.findViewById(R.id.ImageViewVisibility);
        cbHienMatKhau = view.findViewById(R.id.CheckBoxHienMatKhau);
        cbLuuThongTin = view.findViewById(R.id.CheckBoxLuuThongTinDangNhap);
        edtTaiKhoan = view.findViewById(R.id.EditTextTaiKhoan);
        edtMatKhau = view.findViewById(R.id.EditTextMatKhau);
        btnDangNhap = view.findViewById(R.id.ButtonDangNhap);
        btnQuenMatKhau = view.findViewById(R.id.ButtonQuenMatKhau);
        loginButton = view.findViewById(R.id.login_button);
        loginButton.setFragment(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
