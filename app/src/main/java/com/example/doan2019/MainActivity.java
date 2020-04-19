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

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.JsonApiUser;
import com.example.doan2019.Retrofit.User;
import com.facebook.AccessToken;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LangNgheSuKienChuyenFragment, OSSubscriptionObserver {
    BottomNavigationView bottomNavigationView;
    int menuHienTai = 1, menuDaChon = 0;
    int rootFragment = 0;
    SharedPreferences sharedPreferencesDataLogin, sharedPreferencesOSId;
    String Auth;
    User user;
    JsonApiUser jsonApiUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonApiUser = APIUtils.getJsonApiUser();
        Mapping();

        OneSignal.addSubscriptionObserver(this);

        Log.d("OS", sharedPreferencesOSId.getString("id", "")+"");


        DangKySuKienLangNghe();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TimDoiFragment()).commit();
    }

    private void DangKySuKienLangNghe() {
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private void Mapping() {

        sharedPreferencesOSId = this.getSharedPreferences("OneSignalId", Context.MODE_PRIVATE);
        sharedPreferencesDataLogin = this.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        Auth = sharedPreferencesDataLogin.getString("token","");

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
