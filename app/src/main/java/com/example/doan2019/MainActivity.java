package com.example.doan2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LangNgheSuKienChuyenFragment {
    BottomNavigationView bottomNavigationView;
    int menuHienTai = 1, menuDaChon = 0;
    int rootFragment = 0;

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
                            isLoggedIn = true;
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
}
