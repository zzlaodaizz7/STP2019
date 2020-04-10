package com.example.doan2019;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TaiKhoanAdapter extends FragmentStatePagerAdapter {
    private String listTab[] = {"Đăng Nhập", "Đăng Ký"};
    private DangNhapFragment dangNhapFragment;
    private DangKyFragment dangKyFragment;

    public TaiKhoanAdapter(FragmentManager fm) {
        super(fm);
        dangNhapFragment = new DangNhapFragment();
        dangKyFragment = new DangKyFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return dangNhapFragment;
        else if(position == 1)
            return dangKyFragment;
        return null;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}