package com.example.doan2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.view.View;

import com.example.doan2019.DTO.DangTinDTO;
import com.example.doan2019.Mapper.ModelMapper;
import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DangTin;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.DoiBong_NguoiDung;
import com.example.doan2019.Retrofit.JsonApiDangTin;
import com.example.doan2019.Retrofit.JsonApiDoiBong;
import com.example.doan2019.Retrofit.JsonApiDoiBongNGuoiDung;
import com.example.doan2019.Retrofit.JsonApiKhungGio;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.JsonApiUser;
import com.example.doan2019.Retrofit.KhungGio;
import com.example.doan2019.Retrofit.SanBong;
import com.example.doan2019.Retrofit.User;
import com.facebook.AccessToken;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements LangNgheSuKienChuyenFragment, OSSubscriptionObserver {
    BottomNavigationView bottomNavigationView;
    int menuHienTai = 1, menuDaChon = 0;
    int rootFragment = 0;
    SharedPreferences sharedPreferencesDataLogin, sharedPreferencesOSId, sharedPreferencesLoadTimDoi;
    String Auth;
    User user;
    JsonApiKhungGio jsonApiKhungGio;
    JsonApiSanBong jsonApiSanBong;
    JsonApiDoiBong jsonApiDoiBong;
    JsonApiDangTin jsonApiDangTin;
    JsonApiDoiBongNGuoiDung jsonApiDoiBongNGuoiDung;
    JsonApiUser jsonApiUser;
    Map<String,String> header;
    ArrayList<DoiBong_NguoiDung> doiBong_nguoiDungArrayList;
    ArrayList<DangTinDTO> dangTinDTOArrayList;
    ArrayList<SanBong> sanBongArrayList;
    ArrayList<DangTin> dangTinArrayList;
    ArrayList<DoiBong> doiBongArrayList;
    ArrayList<KhungGio> khungGioArrayList;
    Fragment selectedFragment;
    // luu list dang tin sau khi load duoc tai timdoifragment
    // sau moi luc cap nhat, them moi dang tin: set lai gia tri cua sharePre..LoadTimDoi.
    ListView mainLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!checkPermission()) {
            openActivity();
        } else {
            if (checkPermission()) {
                requestPermissionAndContinue();
            } else {
                openActivity();
            }
        }
        Mapping();

        OneSignal.addSubscriptionObserver(this);

        Log.d("OS", sharedPreferencesOSId.getString("id", "")+"");


        DangKySuKienLangNghe();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TimDoiFragment()).commit();
    }

    private static final int PERMISSION_REQUEST_CODE = 200;
    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ;
    }

    private void requestPermissionAndContinue() {
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)) {
            if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
                        && ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("permission_necessary");
                    alertBuilder.setMessage("storage_permission_is_encessary_to_wrote_event");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE
                                    , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                    Log.e("", "permission denied, show dialog");
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE,
                            READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                }
            } else {
                openActivity();
            }
        } else {
            openActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.length > 0 && grantResults.length > 0) {

                boolean flag = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        flag = false;
                    }
                }
                if (flag) {
                    openActivity();
                } else {
                    finish();
                }

            } else {
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void openActivity() {
        //add your further process after giving permission or to download images from remote server.
    }

    private void DangKySuKienLangNghe() {
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private void Mapping() {
        sharedPreferencesLoadTimDoi = this.getSharedPreferences("LoadDataDangTin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesLoadTimDoi.edit();
        editor.putString("isLoaded", "false");
        editor.commit();

        jsonApiUser = APIUtils.getJsonApiUser();
        jsonApiKhungGio = APIUtils.getJsonApiKhungGio();
        jsonApiDoiBong = APIUtils.getJsonApiDoiBong();
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        jsonApiDangTin = APIUtils.getJsonApiDangTin();
        jsonApiDoiBongNGuoiDung = APIUtils.getJsonApiDoiBongNguoiDung();

        doiBong_nguoiDungArrayList = new ArrayList<>();
        sanBongArrayList = new ArrayList<>();
        khungGioArrayList = new ArrayList<>();
        doiBongArrayList = new ArrayList<>();
        dangTinArrayList = new ArrayList<>();
        dangTinDTOArrayList = new ArrayList<>();
        sharedPreferencesOSId = this.getSharedPreferences("OneSignalId", Context.MODE_PRIVATE);
        sharedPreferencesDataLogin = this.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);

        Auth = sharedPreferencesDataLogin.getString("token","");
        header = new HashMap<>();
        header.put("value","application/json");
        header.put("Accept","application/json");
        header.put("Authorization","Bearer "+Auth);

        mainLV = (ListView) findViewById(R.id.mainLV);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_timDoi:
                            menuDaChon = 1;
                            selectedFragment = new TimDoiFragment();
                            break;
                        case R.id.nav_timSan:
                            menuDaChon = 2;
                            selectedFragment = new TimSanFragment();
                            break;
                        case R.id.nav_taiKhoan:
                            menuDaChon = 3;
                            AccessToken accessToken = AccessToken.getCurrentAccessToken();
                            boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
                            if (sharedPreferencesDataLogin.getString("token","")==""){
                                isLoggedIn = false;
                            }else {
                                isLoggedIn = true;
                            }
                            if (isLoggedIn == false) {
                                selectedFragment = new TaiKhoanFragment();
                            } else {
                                selectedFragment = new TaiKhoanDaLoginFragment();
                            }
                            break;
                        case R.id.nav_xepHang:
                            menuDaChon = 4;
                            selectedFragment = new XepHangFragment();
                            break;
                        case R.id.nav_caiDat:
                            menuDaChon = 5;
                            selectedFragment = new CaiDatFragment();
                            break;
                    }
                    if (menuDaChon != menuHienTai) {
                        getSupportFragmentManager().popBackStack(0,0);
                        getSupportFragmentManager().popBackStack();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        menuHienTai = menuDaChon;
                    }
                    return true;
                }
            };

    @Override
    public void ChuyenHuongFragment(Fragment x) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, x)
                .addToBackStack(rootFragment + "").commit();
    }
    @Override
    public void onOSSubscriptionChanged(OSSubscriptionStateChanges stateChanges) {
        if (!stateChanges.getFrom().getSubscribed() &&
                stateChanges.getTo().getSubscribed()) {
            SharedPreferences.Editor editor = sharedPreferencesOSId.edit();
            editor.putString("id", stateChanges.getTo().getUserId());
            editor.putString("changed", "true");
            editor.commit();

            //truong hop cap nhat ung dung
            if(sharedPreferencesDataLogin.getInt("id", -1) != -1){
                Log.d("Debug", "da change");
                user = new User();
                Call<User> call = jsonApiUser.getNguoiDung(sharedPreferencesDataLogin.getInt("id", -1));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        user = response.body();
                        user.setDevice(stateChanges.getTo().getUserId());
                        Call<String> call1 = jsonApiUser.update(user, sharedPreferencesDataLogin.getInt("id", -1));
                        call1.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d("Debug", "success"+response.body());
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("Debug", ""+t);
                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                });
            }
            Log.d("OS", sharedPreferencesOSId.getString("id", "")+"");

            Log.i("Debug", "onOSPermissionChanged: " + stateChanges);

        }
    }
}
