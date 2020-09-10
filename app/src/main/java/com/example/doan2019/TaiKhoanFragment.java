package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class TaiKhoanFragment extends Fragment {
    private static ViewPager viewPager;
    private static View view;
    public static TaiKhoanAdapter taiKhoanAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        Mapping();
        XuLy();
        return view;
    }

    public static void XuLy() {
        viewPager.setAdapter(taiKhoanAdapter);
        TabLayout tabLayout = view.findViewById(R.id.TabLayoutTaiKhoan);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void Mapping() {
        viewPager = view.findViewById(R.id.ViewPagerTaiKhoan);
        taiKhoanAdapter= new TaiKhoanAdapter(getChildFragmentManager());
    }
}