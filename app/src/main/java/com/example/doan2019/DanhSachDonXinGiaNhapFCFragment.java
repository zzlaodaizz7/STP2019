package com.example.doan2019;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DanhSachDonXinGiaNhapFCFragment extends Fragment {
    private View view;
    TextView txtBack;
    ListView lvDonXinGiaNhap;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;
    DanhSachDonXinGiaNhapFCAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danh_sach_don_xin_gia_nhap_fc, container, false);

        Mapping();

        ClickBack();

        GanDuLieu();

        return view;
    }

    private void GanDuLieu() {
        listThanhVienDoiBong = new ArrayList<>();
        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);

        long ngayTemp = 1234596789;
        Date dateConvert = new Date(ngayTemp);

        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", "dateConvert", "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", "dateConvert", "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", "dateConvert", "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", "dateConvert", "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn F", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn G", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn H", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn I", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));

        adapter = new DanhSachDonXinGiaNhapFCAdapter(getActivity(), R.layout.dong_don_xin_gia_nhap_fc, listThanhVienDoiBong);
        lvDonXinGiaNhap.setAdapter(adapter);
        SetListViewHeightBasedOnChildren(adapter, lvDonXinGiaNhap);
    }

    private void SetListViewHeightBasedOnChildren(DanhSachDonXinGiaNhapFCAdapter matchAdapter, ListView listView) {
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

    private void ClickBack() {
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void Mapping() {
        txtBack = view.findViewById(R.id.TextViewQuayLai);
        lvDonXinGiaNhap = view.findViewById(R.id.ListViewDonXinGiaNhap);
    }
}
