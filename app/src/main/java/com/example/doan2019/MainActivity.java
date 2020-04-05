package com.example.doan2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements LangNgheSuKienTuFragmentDangNhapDenActivity{
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapping();
        DangKySuKienLangNghe();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TimDoiFragment()).commit();
    }

    private void DangKySuKienLangNghe() {
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private void Mapping() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_timDoi:
                            selectedFragment = new TimDoiFragment();
                            break;
                        case R.id.nav_timSan:
                            selectedFragment = new TimSanFragment();
                            break;
                        case R.id.nav_taiKhoan:
                            AccessToken accessToken = AccessToken.getCurrentAccessToken();
                            boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
                            if(isLoggedIn == false) {
                                Log.d("JSON", "Tai khoan chua dang nhap");
                                selectedFragment = new TaiKhoanChuaLoginFragment();
                            }
                            else {
                                Log.d("JSON", "Tai khoan da dang nhap");
                                selectedFragment = new TaiKhoanDaLoginFragment();
                            }
                            break;
                        case R.id.nav_xepHang:
                            selectedFragment = new XepHangFragment();
                            break;
                        case R.id.nav_caiDat:
                            selectedFragment = new CaiDatFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public void ChuyenHuongTuChuaDangNhapSangDaDangNhapVaNguocLai() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        Fragment fragment = null;
        if(isLoggedIn == true)
            fragment = new TaiKhoanDaLoginFragment();
        else
            fragment = new TaiKhoanChuaLoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment).commit();
    }
}
