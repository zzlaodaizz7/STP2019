package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.DoiBong;

public class DanhSachCacTinDaDangFragment extends Fragment {
    private View view;
    ListView lvDanhSachCacTinDaDang;
    ArrayList<DangTinDuongClass> arrDangTin;
    DanhSachCacTinDaDangAdapter adapter;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    Bundle bundle;
    TextView txtQuayLai;
    DoiBong doiBong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cac_tin_da_dang, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        Mapping();

        ClickQuayLai();

        GetDuLieuDoiBong();

        KhoiTaoDuLieu();

        ClickListView();

        return view;
    }

    private void ClickQuayLai() {
        txtQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void ClickListView() {
        lvDanhSachCacTinDaDang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DanhSachCacDoiBatDoiFragment danhSachCacDoiBatDoiFragment = new DanhSachCacDoiBatDoiFragment();

                Bundle bundleTinDang = new Bundle();
                DangTinDuongClass tinDang = arrDangTin.get(i);
                bundleTinDang.putSerializable("tindang", tinDang);
                danhSachCacDoiBatDoiFragment.setArguments(bundleTinDang);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(danhSachCacDoiBatDoiFragment);
            }
        });
    }

    private void GetDuLieuDoiBong() {
        bundle = getArguments();
        doiBong = (DoiBong) bundle.getSerializable("doibong");
        Toast.makeText(getActivity(), doiBong.getTen(), Toast.LENGTH_SHORT).show();
    }

    private void KhoiTaoDuLieu() {
        arrDangTin = new ArrayList<>();
        long date = 123456789;
        Date convertDate = new Date(date);
        Timestamp convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
        date++;
        convertDate = new Date(date);
        convertTimestamp = new Timestamp(date);
        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));

        adapter = new DanhSachCacTinDaDangAdapter(getActivity(), R.layout.dong_tin_da_dang, arrDangTin);
        lvDanhSachCacTinDaDang.setAdapter(adapter);
        SetListViewHeightBasedOnChildren(adapter, lvDanhSachCacTinDaDang);
    }

    private void SetListViewHeightBasedOnChildren(DanhSachCacTinDaDangAdapter matchAdapter, ListView listView) {
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
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
        lvDanhSachCacTinDaDang = view.findViewById(R.id.ListViewDanhSachCacTinDaDang);
    }
}
