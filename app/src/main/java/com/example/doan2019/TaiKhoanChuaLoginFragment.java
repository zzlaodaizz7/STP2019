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

    //    Check da dang nhap chua
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (isLoggedIn == false) {
            view = inflater.inflate(R.layout.fragment_tai_khoan_chua_login, container, false);
            Mapping();
        } else {
            LayDuLieuDaLuu();
            view = inflater.inflate(R.layout.fragment_tai_khoan_da_login, container, false);
        }
        return view;
    }

    private void LayDuLieuDaLuu() {
        final TaiKhoanDaLoginFragment taiKhoanDaLoginFragment = new TaiKhoanDaLoginFragment();

        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    Log.d("JSON", object.getString("id"));
                    Log.d("JSON", object.getString("name"));
                    Log.d("JSON", object.getString("email"));
                    taiKhoanDaLoginFragment.GanNoiDung(object.getString("id"),
                            object.getString("name"), object.getString("email"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    private void Mapping() {
        viewPager = view.findViewById(R.id.ViewPagerTaiKhoan);
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getChildFragmentManager());
        viewPager.setAdapter(adapterTaiKhoan);
        TabLayout tabLayout = view.findViewById(R.id.TabLayoutTaiKhoan);
        tabLayout.setupWithViewPager(viewPager);
    }
}