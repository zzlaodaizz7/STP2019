package com.example.doan2019;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class TaiKhoanChuaLoginFragment extends Fragment {
    private View view;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tai_khoan_chua_login, container, false);
        Mapping();
        return view;
    }

    private void Mapping() {
        viewPager = view.findViewById(R.id.ViewPagerTaiKhoan);
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getChildFragmentManager());
        viewPager.setAdapter(adapterTaiKhoan);
        TabLayout tabLayout = view.findViewById(R.id.TabLayoutTaiKhoan);
        tabLayout.setupWithViewPager(viewPager);
    }
}