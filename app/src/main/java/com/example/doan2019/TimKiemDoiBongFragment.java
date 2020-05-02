package com.example.doan2019;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.JsonApiDoiBong;
import com.example.doan2019.Retrofit.JsonApiDoiBongNGuoiDung;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemDoiBongFragment extends Fragment {
    private View view;
    ImageView imgBack;
    ListView lvDoiBong;
    ArrayList<DoiBongClass> listDoiBong;
    ArrayList<DoiBong> doiBongArrayList;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;
    TimKiemDoiBongAdapter adapter;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    JsonApiDoiBong jsonApiDoiBong;
    JsonApiDoiBongNGuoiDung jsonApiDoiBongNGuoiDung;
    SharedPreferences sharedPreferences;
    Map<String,String> header;
    String Auth="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem_doi_bong, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        jsonApiDoiBong = APIUtils.getJsonApiDoiBong();
        Mapping();

        ClickQuayLai();

        KhoiTaoListView();

        ClickListView();

        return view;
    }

    private void ClickQuayLai() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void ClickListView() {
        lvDoiBong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietDoiBongXepHangFragment chiTietDoiBongXepHangFragment = new ChiTietDoiBongXepHangFragment();

                Bundle bundle = new Bundle();
                DoiBong doiBongClass = doiBongArrayList.get(i);
                bundle.putSerializable("doibong", doiBongClass);
                chiTietDoiBongXepHangFragment.setArguments(bundle);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietDoiBongXepHangFragment);
            }
        });
    }

    private void KhoiTaoListView() {
        Call<List<DoiBong>> call2 = jsonApiDoiBong.getDoiBongs(header);
        call2.enqueue(new Callback<List<DoiBong>>() {
            @Override
            public void onResponse(Call<List<DoiBong>> call, Response<List<DoiBong>> response) {
                List<DoiBong> doiBongs = response.body();
                for (DoiBong doiBong : doiBongs) {
                    doiBongArrayList.add(doiBong);
                    Log.d("timkiemdoibong", doiBong.getId()+"");
                }
                adapter = new TimKiemDoiBongAdapter(getActivity(), R.layout.dong_tim_kiem_doi_bong_trong_fragment_tai_khoan_da_login, doiBongArrayList);
                lvDoiBong.setAdapter(adapter);
                setListViewHeightBasedOnChildren(adapter, lvDoiBong);
            }
            @Override
            public void onFailure(Call<List<DoiBong>> call, Throwable t) {
            }
        });
    }

    private void setListViewHeightBasedOnChildren(TimKiemDoiBongAdapter matchAdapter, ListView listView) {

        if (matchAdapter == null) {
            return;
        }
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < matchAdapter.getCount(); i++) {
            view = matchAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (matchAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private void Mapping() {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        Auth = sharedPreferences.getString("token","");
        header = new HashMap<>();
        header.put("value","application/json");
        header.put("Accept","application/json");
        header.put("Authorization","Bearer "+Auth);
        imgBack = view.findViewById(R.id.ImageViewQuayLai);
        lvDoiBong = view.findViewById(R.id.ListViewDoiBong);
        doiBongArrayList = new ArrayList<>();
    }
}
