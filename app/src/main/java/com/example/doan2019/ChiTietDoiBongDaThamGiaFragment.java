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
import android.widget.Toast;

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
    ListView lvDanhSachThanhVien;
    DoiBongClass doiBong;
    DanhSachThanhVienAdapter adapter;
    ArrayList<ThanhVienDoiBongClass> arrThanhVien;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_doi_bong_da_tham_gia, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        Mapping();

        KhoiTaoListViewThanhVien();

        ClickDanhSachThanhVien();

        ClickQuayLai();

        GanNoiDungThongTinDoiBong();

        ClickDanhSachCacTinDaDang();

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

    private void KhoiTaoListViewThanhVien() {
        arrThanhVien = new ArrayList<>();
        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        long ngayTemp = 1234596789;
        Date dateConvert = new Date(ngayTemp);

        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn F", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn G", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn H", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn I", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));

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
    }

    private void Mapping() {
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
