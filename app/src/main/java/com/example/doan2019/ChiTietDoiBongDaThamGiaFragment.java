package com.example.doan2019;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChiTietDoiBongDaThamGiaFragment extends Fragment {
    private View view;
    TextView txtTenDoiBong, txtDiem, txtDiaChi, txtTrinhDo, txtNgayThanhlap, txtPhone, txtDanhSachCacTinDaDang, txtQuayLai;
    ImageView imgAnhBia, imgAnhDaiDien;
    Bundle bundle;
    ListView lvDanhSachThanhVien, lvTranDauSapToi, lvLichSuTranDau;
    DoiBongClass doiBong;
    DanhSachThanhVienAdapter adapter;
    TranDauSapToiAdapter adapterTranDauSapToi;
    LichSuTranDauAdapter adapterLichSuTranDau;
    ArrayList<ThanhVienDoiBongClass> arrThanhVien;
    ArrayList<TranDauDuongClass> arrTranDauSapToi;
    ArrayList<TranDauDuongClass> arrLichSuTranDau;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    Button btnDonXinGiaNhap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_doi_bong_da_tham_gia, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        Mapping();

        ClickDanhSachThanhVien();

        ClickQuayLai();

        GanNoiDungThongTinDoiBong();

        GanNoiDungListViewTranDauSapToi();

        GanNoiDungListViewLichSuTranDau();

        ClickListViewTranDauSapToi();

        ClickListViewLichSuTranDau();

        ClickDanhSachCacTinDaDang();

        ClickDuyetDon();

        return view;
    }

    private void ClickDuyetDon() {
        btnDonXinGiaNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DanhSachDonXinGiaNhapFCFragment danhSachDonXinGiaNhapFCFragment = new DanhSachDonXinGiaNhapFCFragment();

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(danhSachDonXinGiaNhapFCFragment);
            }
        });
    }

    private void ClickListViewLichSuTranDau() {
        lvLichSuTranDau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietLichSuTranDauFragment chiTietLichSuTranDauFragment = new ChiTietLichSuTranDauFragment();

                Bundle bundleTranDauSapToi = new Bundle();
                TranDauDuongClass tranDau = arrLichSuTranDau.get(i);
                bundleTranDauSapToi.putSerializable("trandauduong", tranDau);
                chiTietLichSuTranDauFragment.setArguments(bundleTranDauSapToi);
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietLichSuTranDauFragment);
            }
        });
    }

    private void ClickListViewTranDauSapToi() {
        lvTranDauSapToi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietTranDauSapToiFragment chiTietTranDauSapToiFragment = new ChiTietTranDauSapToiFragment();

                Bundle bundleTranDauSapToi = new Bundle();
                TranDauDuongClass tranDau = arrTranDauSapToi.get(i);
                bundleTranDauSapToi.putSerializable("trandauduong", tranDau);
                chiTietTranDauSapToiFragment.setArguments(bundleTranDauSapToi);
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietTranDauSapToiFragment);
            }
        });
    }

    private void GanNoiDungListViewLichSuTranDau() {
        arrLichSuTranDau = new ArrayList<>();

        DoiBongClass doiBong1, doiBong2, doiBong3, doiBong4, doiBong5;
        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);

        doiBong1 = new DoiBongClass("FC fb", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010",
                "0123456789", anhBia, anhDaiDien, arrThanhVien);
        doiBong2 = new DoiBongClass("FC Lê Đức Thọ", 3.02, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789",anhBia, anhDaiDien, arrThanhVien);
        doiBong3 = new DoiBongClass("FC Linh Đàm", 3.02, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789",anhBia, anhDaiDien, arrThanhVien);
        doiBong4 = new DoiBongClass("FC Cầu Giấy", 3.02, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789",anhBia, anhDaiDien, arrThanhVien);
        doiBong5 = new DoiBongClass("FC Mễ Trì", 3.02, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789",anhBia, anhDaiDien, arrThanhVien);
        long date = 123456789;
        Date convertDate = new Date(date);

        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong1, doiBong5, convertDate, 1, 1, 0, 0, "Nước", false));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong2, doiBong4, convertDate, 2, 2, 0,  0, "Nước", false));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong3, doiBong1, convertDate, 3, 3, 4,  2, "Nước", true));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong5, doiBong2, convertDate, 1, 4, 3,  2, "Nước", true));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong4, doiBong3, convertDate, 2, 5, 2,  2, "Nước", true));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong1, doiBong4, convertDate, 3, 6, 1,  2, "Nước", true));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong1, doiBong5, convertDate, 1, 1, 6, 2, "Nước", true));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong2, doiBong4, convertDate, 2, 2, 5,  2, "Nước", true));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong3, doiBong1, convertDate, 3, 3, 4,  2, "Nước", true));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong5, doiBong2, convertDate, 1, 4, 3,  2, "Nước", true));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong4, doiBong3, convertDate, 2, 5, 2,  2, "Nước", true));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong1, doiBong4, convertDate, 3, 6, 1,  2, "Nước", true));

        adapterLichSuTranDau = new LichSuTranDauAdapter(getActivity(), R.layout.dong_lich_su_tran_dau, arrLichSuTranDau);
        lvLichSuTranDau.setAdapter(adapterLichSuTranDau);
        SetListViewHeightBasedOnChildrenLichSuTranDau(adapterLichSuTranDau, lvLichSuTranDau);
    }

    private void SetListViewHeightBasedOnChildrenLichSuTranDau(LichSuTranDauAdapter matchAdapter, ListView listView) {
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
        totalHeight -= view.getMeasuredHeight();
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (matchAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private void GanNoiDungListViewTranDauSapToi() {
        arrTranDauSapToi = new ArrayList<>();

        DoiBongClass doiBong1, doiBong2, doiBong3, doiBong4, doiBong5;
        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);

        doiBong1 = new DoiBongClass("FC fb", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010",
                "0123456789", anhBia, anhDaiDien, arrThanhVien);
        doiBong2 = new DoiBongClass("FC Lê Đức Thọ", 3.02, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789",anhBia, anhDaiDien, arrThanhVien);
        doiBong3 = new DoiBongClass("FC Linh Đàm", 3.02, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789",anhBia, anhDaiDien, arrThanhVien);
        doiBong4 = new DoiBongClass("FC Cầu Giấy", 3.02, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789",anhBia, anhDaiDien, arrThanhVien);
        doiBong5 = new DoiBongClass("FC Mễ Trì", 3.02, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789",anhBia, anhDaiDien, arrThanhVien);
        long date = 123456789;
        Date convertDate = new Date(date);

        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong3, doiBong4, convertDate, 3, 0, 0, 0, "Nước", false));
        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong1, doiBong2, convertDate, 3, 0, 0, 0, "Nước", false));
        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong4, doiBong5, convertDate, 3, 0, 0, 0, "Nước", false));
        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong2, doiBong5, convertDate, 3, 0, 0, 0, "Nước", false));
        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong1, doiBong3, convertDate, 3, 0, 0, 0, "Nước", false));
        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong3, doiBong5, convertDate, 3, 0, 0, 0, "Nước", false));
        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong3, doiBong4, convertDate, 3, 0, 0, 0, "Nước", false));
        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong1, doiBong2, convertDate, 3, 0, 0, 0, "Nước", false));
        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong4, doiBong5, convertDate, 3, 0, 0, 0, "Nước", false));
        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong2, doiBong5, convertDate, 3, 0, 0, 0, "Nước", false));
        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong1, doiBong3, convertDate, 3, 0, 0, 0, "Nước", false));
        arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong3, doiBong5, convertDate, 3, 0, 0, 0, "Nước", false));

        adapterTranDauSapToi = new TranDauSapToiAdapter(getActivity(), R.layout.dong_tran_dau_sap_toi, arrTranDauSapToi);
        lvTranDauSapToi.setAdapter(adapterTranDauSapToi);
        SetListViewHeightBasedOnChildrenTranDauSapToi(adapterTranDauSapToi, lvTranDauSapToi);
    }

    private void SetListViewHeightBasedOnChildrenTranDauSapToi(TranDauSapToiAdapter matchAdapter, ListView listView) {
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
        totalHeight -= view.getMeasuredHeight();
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (matchAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private void ClickQuayLai() {
        txtQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void ClickDanhSachThanhVien() {
        lvDanhSachThanhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietThanhVienFragment chiTietThanhVienFragment = new ChiTietThanhVienFragment();

                Bundle bundleThanhVien = new Bundle();
                ThanhVienDoiBongClass thanhVien = arrThanhVien.get(i);
                bundleThanhVien.putSerializable("thanhvien", thanhVien);
                chiTietThanhVienFragment.setArguments(bundleThanhVien);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietThanhVienFragment);
            }
        });
    }

    private void SetListViewHeightBasedOnChildren(DanhSachThanhVienAdapter matchAdapter, ListView listView) {
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

    private void ClickDanhSachCacTinDaDang() {
        txtDanhSachCacTinDaDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DanhSachCacTinDaDangFragment danhSachCacTinDaDangFragment = new DanhSachCacTinDaDangFragment();
                Bundle bundleDanhSachCacTinDaDangFragment = new Bundle();
                bundleDanhSachCacTinDaDangFragment.putSerializable("doibong", doiBong);
                danhSachCacTinDaDangFragment.setArguments(bundleDanhSachCacTinDaDangFragment);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(danhSachCacTinDaDangFragment);
            }
        });
    }

    private void GanNoiDungThongTinDoiBong() {
        bundle = getArguments();

        doiBong = (DoiBongClass) bundle.getSerializable("doibong");
        imgAnhBia.setImageBitmap(doiBong.getImageBia());
        imgAnhDaiDien.setImageBitmap(doiBong.getImageDaiDien());
        txtTenDoiBong.setText(doiBong.getTen());
        txtDiem.setText(doiBong.getDiem() + " Điểm");
        txtDiaChi.setText(doiBong.getDiaChi());
        txtTrinhDo.setText(doiBong.getTrinhDo());
        txtNgayThanhlap.setText(doiBong.getNgayThanhLap());
        txtPhone.setText(doiBong.getSoDienThoai());

        arrThanhVien = doiBong.getListThanhVien();
        adapter = new DanhSachThanhVienAdapter(getActivity(), R.layout.dong_thanh_vien, arrThanhVien);
        lvDanhSachThanhVien.setAdapter(adapter);
        SetListViewHeightBasedOnChildren(adapter, lvDanhSachThanhVien);

    }

    private void Mapping() {
        btnDonXinGiaNhap = view.findViewById(R.id.ButtonDuyetDon);
        lvTranDauSapToi = view.findViewById(R.id.ListViewTranDauSapToi);
        lvLichSuTranDau = view.findViewById(R.id.ListViewLichSuTranDau);
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
        lvDanhSachThanhVien = view.findViewById(R.id.ListViewDanhSachThanhVien);
        txtDanhSachCacTinDaDang = view.findViewById(R.id.TextViewDanhSachCacTinDaDang);
        imgAnhBia = view.findViewById(R.id.ImageViewBiaDoiBong);
        imgAnhDaiDien = view.findViewById(R.id.ImageViewDaiDienDoiBong);
        txtTenDoiBong = view.findViewById(R.id.TextViewName);
        txtDiem = view.findViewById(R.id.TextViewDiem);
        txtDiaChi = view.findViewById(R.id.TextViewDiaChi);
        txtTrinhDo = view.findViewById(R.id.TextViewTrinhDo);
        txtNgayThanhlap = view.findViewById(R.id.TextViewNgayThanhlap);
        txtPhone = view.findViewById(R.id.TextViewPhone);
    }
}
