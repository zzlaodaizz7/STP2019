package com.example.doan2019;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.JsonApiUser;
import com.example.doan2019.Retrofit.User;
import com.example.doan2019.Retrofit.UserLogin;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.internal.InAppPurchaseActivityLifecycleTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DangNhapFragment extends Fragment {
    private Button btnQuenMatKhau, btnDangNhap;
    LoginButton loginButton;
    private View view;
    private CallbackManager callbackManager;
    private EditText edtTaiKhoan, edtMatKhau;
    private String token;
    private CheckBox cbLuuThongTin, cbHienMatKhau;
    private int id;
    User user;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    ImageView imgVisibility;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesOneSignal;
    Retrofit retrofit;
    JsonApiSanBong jsonApiSanBong;
    JsonApiUser jsonApiUser;
    TextView txtTrangThaiPass;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        view = inflater.inflate(R.layout.fragment_dang_nhap, container, false);
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.4/DoAn/public/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        jsonApiUser = APIUtils.getJsonApiUser();
        jsonApiSanBong = APIUtils.getJsonApiSanBong();


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
//        Toast.makeText(getActivity(), "Token: " + sharedPreferences.getString("token", ""), Toast.LENGTH_SHORT).show();
    }

    private void ClickDangNhap() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taiKhoan = edtTaiKhoan.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
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
                }else{
                    Map<String,String> header = new HashMap<>();
                    header.put("value","application/json");
                    header.put("Accept","application/json");
                    UserLogin userLogin = new UserLogin(taiKhoan,matKhau);
                    Call<UserLogin> call = jsonApiSanBong.postLogin(header,userLogin);
                    call.enqueue(new Callback<UserLogin>() {
                        @Override
                        public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                            if (response.code() == 200){
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                                editor.putBoolean("checked", true);
                                editor.putString("token", response.body().getToken());
                                editor.putInt("id",response.body().getId());
                                editor.putString("email",response.body().getEmail());
                                editor.putString("ten",response.body().getTen());
                                editor.putString("anhbia", response.body().getAnhbia());
                                editor.putString("sdt", response.body().getSdt());
                                editor.putString("diachi", response.body().getDiachi());

                                //Log.e("diachidangnhap", response.body().getDiachi());

                                editor.commit();
                                Log.d("anhbia", "anh bia: "+sharedPreferences.getString("anhbia",""));
                                if(sharedPreferencesOneSignal.getString("changed", "").equals("true")){
                                    user = new User();
                                    Call<User> calluser = jsonApiUser.getNguoiDung(sharedPreferences.getInt("id", -1));
                                    calluser.enqueue(new Callback<User>() {
                                        @Override
                                        public void onResponse(Call<User> call, Response<User> response) {
                                            user = response.body();

                                            user.setDevice(sharedPreferencesOneSignal.getString("id", ""));
                                            Call<String> call1 = jsonApiUser.update(user, sharedPreferences.getInt("id", -1));
                                            call1.enqueue(new Callback<String>() {
                                                @Override
                                                public void onResponse(Call<String> call, Response<String> response) {
                                                    SharedPreferences.Editor editor = sharedPreferencesOneSignal.edit();
                                                    editor.putString("changed", "false");
                                                    editor.commit();
                                                    Log.d("dangnhap", "changed: "+sharedPreferencesOneSignal.getString("changed", ""));
                                                    Log.d("dangnhap", "success"+response.body());
                                                }
                                                @Override
                                                public void onFailure(Call<String> call, Throwable t) {
                                                    Log.d("dangnhap", "failed "+t);
                                                }
                                            });
                                        }
                                        @Override
                                        public void onFailure(Call<User> call, Throwable t) {
                                        }
                                    });
                                }
//                                System.out.println("token:" +response.body().getToken());

                                langNgheSuKienChuyenFragment.ChuyenHuongFragment(new TaiKhoanDaLoginFragment());
                            }else{
                                Toast.makeText(getContext(), "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<UserLogin> call, Throwable t) {
                            System.out.println("Quá thời gian truy xuất");
                        }
                    });
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
        sharedPreferencesOneSignal = getActivity().getSharedPreferences("OneSignalId", Context.MODE_PRIVATE);
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
