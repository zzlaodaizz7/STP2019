package com.example.doan2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
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
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_timDoi:
                            selectedFragment = new TimDoiFragment();
                            break;
                        case R.id.nav_timSan:
                            selectedFragment = new TimSanFragment();
                            break;
                        case R.id.nav_taiKhoan:
                            selectedFragment = new TaiKhoanFragment();
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
}
