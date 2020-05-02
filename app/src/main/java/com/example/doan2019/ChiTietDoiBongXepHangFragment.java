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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChiTietDoiBongXepHangFragment extends Fragment {
    private View view;
    TextView txtTenDoiBong, txtDiem, txtDiaChi, txtTrinhDo, txtNgayThanhlap, txtPhone, txtQuayLai;
    Bundle bundle;
    Button btnThamGiaFC;
    ImageView imgAnhBia, imgDaiDien;
    ListView lvDanhSachThanhVien, lvLichSuTranDau;
    ArrayList<ThanhVienDoiBongClass> arrThanhVien;
    DanhSachThanhVienAdapter adapter;
    ArrayList<TranDauDuongClass> arrLichSuTranDau;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    LichSuTranDauAdapter adapterLichSuTranDau;
    RatingBar rtb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_doi_bong_xep_hang, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        Mapping();

        ClickQuayLai();

        GanDuLieu();

        GanNoiDungListViewLichSuTranDau();

        ClickListViewLichSuTranDau();

        ClickThamGiaFC();

        ClickListViewThanhVien();

        ClickSao();

        return view;
    }

    private void ClickSao() {
        rtb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rtb.getRating() == 0.5)
                    rtb.setRating(1);
                else if(rtb.getRating() == 1.5)
                    rtb.setRating(2);
                else if(rtb.getRating() == 2.5)
                    rtb.setRating(3);
                else if(rtb.getRating() == 3.5)
                    rtb.setRating(4);
                else if(rtb.getRating() == 4.5)
                    rtb.setRating(5);
                Toast.makeText(getActivity(), "Số sao vote: " + rtb.getRating(), Toast.LENGTH_SHORT).show();
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
                bundleTranDauSapToi.putSerializable("checkXepHangFragment", true);
                chiTietLichSuTranDauFragment.setArguments(bundleTranDauSapToi);
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietLichSuTranDauFragment);
            }
        });
    }

    private void ClickListViewThanhVien() {
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

    private void GanNoiDungListViewLichSuTranDau() {
        arrLichSuTranDau = new ArrayList<>();

        DoiBongClass doiBong1, doiBong2, doiBong3, doiBong4, doiBong5;
        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);

        doiBong1 = new DoiBongClass("FC fb", 3, "Hà Nội, Việt Nam", "Khá", "11/10/2010",
                "0123456789", anhBia, anhDaiDien, arrThanhVien);
        doiBong2 = new DoiBongClass("FC Lê Đức Thọ", 3, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789", anhBia, anhDaiDien, arrThanhVien);
        doiBong3 = new DoiBongClass("FC Linh Đàm", 3, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789", anhBia, anhDaiDien, arrThanhVien);
        doiBong4 = new DoiBongClass("FC Cầu Giấy", 3, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789", anhBia, anhDaiDien, arrThanhVien);
        doiBong5 = new DoiBongClass("FC Mễ Trì", 3, "Hà Nội, Việt Nam", "Khá",
                "11/10/2010", "0123456789", anhBia, anhDaiDien, arrThanhVien);
        long date = 123456789;
        Date convertDate = new Date(date);

        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong1, doiBong5, "convertDate", 1, 1, 0, 0, "Nước", 0));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong2, doiBong4, "convertDate", 2, 2, 0, 0, "Nước", 0));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong3, doiBong1, "convertDate", 3, 3, 4, 2, "Nước", 0));
        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong5, doiBong2, "convertDate", 1, 4, 3, 2, "Nước", 0));
//        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong4, doiBong3, convertDate, 2, 5, 2,  2, "Nước", 0));
//        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong1, doiBong4, convertDate, 3, 6, 1,  2, "Nước", 0));
//        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong1, doiBong5, convertDate, 1, 1, 6, 2, "Nước", 0));
//        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong2, doiBong4, convertDate, 2, 2, 5,  2, "Nước", 0));
//        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong3, doiBong1, convertDate, 3, 3, 4,  2, "Nước", 0));
//        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong5, doiBong2, convertDate, 1, 4, 3,  2, "Nước", 0));
//        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong4, doiBong3, convertDate, 2, 5, 2,  2, "Nước", 0));
//        arrLichSuTranDau.add(new TranDauDuongClass(1, doiBong1, doiBong4, convertDate, 3, 6, 1,  2, "Nước", 0));

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

    private void ClickQuayLai() {
        txtQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void ClickThamGiaFC() {
        btnThamGiaFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Xin tham gia FC", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GanDuLieu() {
        bundle = getArguments();

        DoiBongClass doiBongClass = (DoiBongClass) bundle.getSerializable("doibong");
        imgAnhBia.setImageBitmap(doiBongClass.getImageBia());
        imgDaiDien.setImageBitmap(doiBongClass.getImageDaiDien());
        txtTenDoiBong.setText(doiBongClass.getTen());
        txtDiem.setText(doiBongClass.getDiem() + "");
        txtDiaChi.setText(doiBongClass.getDiaChi());
        txtTrinhDo.setText(doiBongClass.getTrinhDo());
        txtNgayThanhlap.setText(doiBongClass.getNgayThanhLap());
        txtPhone.setText(doiBongClass.getSoDienThoai());

        arrThanhVien = doiBongClass.getListThanhVien();
        adapter = new DanhSachThanhVienAdapter(getActivity(), R.layout.dong_thanh_vien, arrThanhVien);
        lvDanhSachThanhVien.setAdapter(adapter);
        SetListViewHeightBasedOnChildren(adapter, lvDanhSachThanhVien);
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

    private void Mapping() {
        rtb = view.findViewById(R.id.RatingBarDoiBongXepHang);
        lvLichSuTranDau = view.findViewById(R.id.ListViewLichSuTranDau);
        lvDanhSachThanhVien = view.findViewById(R.id.ListViewDanhSachThanhVien);
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
        imgAnhBia = view.findViewById(R.id.ImageViewBiaDoiBong);
        imgDaiDien = view.findViewById(R.id.ImageViewDaiDienDoiBong);
        btnThamGiaFC = view.findViewById(R.id.ButtonThamGiaVaoFC);
        txtTenDoiBong = view.findViewById(R.id.TextViewName);
        txtDiem = view.findViewById(R.id.TextViewDiem);
        txtDiaChi = view.findViewById(R.id.TextViewDiaChi);
        txtTrinhDo = view.findViewById(R.id.TextViewTrinhDo);
        txtNgayThanhlap = view.findViewById(R.id.TextViewNgayThanhlap);
        txtPhone = view.findViewById(R.id.TextViewPhone);
    }
}
