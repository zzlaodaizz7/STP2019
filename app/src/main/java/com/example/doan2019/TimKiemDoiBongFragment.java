package com.example.doan2019;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimKiemDoiBongFragment extends Fragment {
    private View view;
    ListView lvDoiBong;
    ArrayList<DoiBongClass> listDoiBong;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;
    TimKiemDoiBongAdapter adapter;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem_doi_bong, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        Mapping();
        KhoiTaoListView();
        ClickListView();

        return view;
    }

    private void ClickListView() {
        lvDoiBong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietDoiBongXepHangFragment chiTietDoiBongXepHangFragment = new ChiTietDoiBongXepHangFragment();

                Bundle bundle = new Bundle();
                DoiBongClass doiBongClass = listDoiBong.get(i);
                bundle.putSerializable("doibong", doiBongClass);
                chiTietDoiBongXepHangFragment.setArguments(bundle);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietDoiBongXepHangFragment);
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

        listDoiBong.add(new DoiBongClass("FC fb", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Linh Đàm", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Cầu Giấy", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Mễ Trì", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Lê Đức Thọ", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));

        adapter = new TimKiemDoiBongAdapter(getActivity(), R.layout.dong_tim_kiem_doi_bong_trong_fragment_tai_khoan_da_login, listDoiBong);
        lvDoiBong.setAdapter(adapter);
        setListViewHeightBasedOnChildren(adapter, lvDoiBong);
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
        lvDoiBong = view.findViewById(R.id.ListViewDoiBong);
    }
}
