package com.example.doan2019;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.sql.Date;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimKiemDoiBongFragment extends Fragment {
    private View view;
    ImageView imgBack;
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
        long ngayTemp = 1234596789;
        Date dateConvert = new Date(ngayTemp);

        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn F", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn G", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn H", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn I", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));

        listDoiBong.add(new DoiBongClass("FC fb", 3, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Linh Đàm", 3, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Cầu Giấy", 3, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Mễ Trì", 3, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));
        listDoiBong.add(new DoiBongClass("FC Lê Đức Thọ", 3, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));

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
        imgBack = view.findViewById(R.id.ImageViewQuayLai);
        lvDoiBong = view.findViewById(R.id.ListViewDoiBong);
    }
}
