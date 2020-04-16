package com.example.doan2019;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DanhSachCacDoiBatDoiFragment extends Fragment {
    private View view;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    ListView lvDoiBongBatDoi;
    TextView txtQuayLai;
    ArrayList<DoiBongClass> listDoiBong;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;
    DanhSachCacDoiBatDoiAdapter adapter;
    Bundle bundle;
    private int IDTinDang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cac_doi_bat_doi, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        Mapping();

        ClickQuayLai();

        ClickListview();

        GetDuLieuIDDangTin();

        KhoiTaoListView();

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

    private void GetDuLieuIDDangTin() {
        bundle = getArguments();

        DangTinDuongClass dangTin = (DangTinDuongClass) bundle.getSerializable("tindang");
        Toast.makeText(getActivity(), "ID tin đăng: " + dangTin.getID(), Toast.LENGTH_SHORT).show();
    }

    private void ClickListview() {
        lvDoiBongBatDoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), i + "", Toast.LENGTH_SHORT).show();

                ChiTietDoiBongBatDoiFragment chiTietDoiBongBatDoiFragment = new ChiTietDoiBongBatDoiFragment();
                Bundle bundleChiTietDoiBong = new Bundle();
                DoiBongClass doiBongClass = listDoiBong.get(i);
                bundleChiTietDoiBong.putSerializable("doibong", doiBongClass);
                chiTietDoiBongBatDoiFragment.setArguments(bundleChiTietDoiBong);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietDoiBongBatDoiFragment);
            }
        });
    }

    private void KhoiTaoListView() {
        listDoiBong = new ArrayList<>();
        listThanhVienDoiBong = new ArrayList<>();
        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);

        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Đội trưởng", 1));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Cầu thủ", 2));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Cầu thủ", 3));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Cầu thủ", 4));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Đội phó", 5));

        listDoiBong.add(new DoiBongClass("FC fb", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Linh Đàm", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Cầu Giấy", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Mễ Trì", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Lê Đức Thọ", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", anhBia, anhDaiDien, listThanhVienDoiBong));

        adapter = new DanhSachCacDoiBatDoiAdapter(getActivity(), R.layout.dong_doi_bat_doi, listDoiBong);
        lvDoiBongBatDoi.setAdapter(adapter);
        SetListViewHeightBasedOnChildren(adapter, lvDoiBongBatDoi);
    }

    private void SetListViewHeightBasedOnChildren(DanhSachCacDoiBatDoiAdapter matchAdapter, ListView listView) {
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
        lvDoiBongBatDoi = view.findViewById(R.id.ListViewCacDoiBatDoi);
    }
}
