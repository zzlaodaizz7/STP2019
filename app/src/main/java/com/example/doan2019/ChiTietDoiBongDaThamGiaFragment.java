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
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.DangTin;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChiTietDoiBongDaThamGiaFragment extends Fragment {
    private View view;
    TextView txtTenDoiBong, txtDiem, txtDiaChi, txtTrinhDo, txtNgayThanhlap, txtPhone, txtDanhSachCacTinDaDang, txtQuayLai;
    ImageView imgAnhBia, imgAnhDaiDien;
    Bundle bundle;
    ListView lvDanhSachThanhVien,lvTranDauSapToi, lvLichSuTranDau;
    DoiBong doiBong;
    DanhSachThanhVienAdapter adapter;
    TranDauSapToiAdapter adapterTranDauSapToi;
    LichSuTranDauAdapter adapterLichSuTranDau;
    ArrayList<ThanhVienDoiBongClass> arrThanhVien;
    ArrayList<TranDauDuongClass> arrTranDauSapToi;
    ArrayList<TranDauDuongClass> arrLichSuTranDau;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    Button btnDonXinGiaNhap;
    Retrofit retrofit;
    JsonApiSanBong jsonApiSanBong;
    int idItMe;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_doi_bong_da_tham_gia, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4/DoAn/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonApiSanBong = retrofit.create(JsonApiSanBong.class);
        Mapping();

        KhoiTaoListViewThanhVien();

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
        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);
        Call<List<DangTin>> call = jsonApiSanBong.getCactrandaketthuc(doiBong.getId());
        call.enqueue(new Callback<List<DangTin>>() {
            @Override
            public void onResponse(Call<List<DangTin>> call, Response<List<DangTin>> response) {
                List<DangTin> dangTin = response.body();
                for (int i = 0; i < dangTin.size(); i++) {
                    dangTin.get(i).getDoibong1().setImageBia(anhBia);
                    dangTin.get(i).getDoibong1().setImageDaiDien(anhDaiDien);
                    dangTin.get(i).getDoibong2().setImageBia(anhBia);
                    dangTin.get(i).getDoibong2().setImageDaiDien(anhDaiDien);
                    int a=0,b=0;
                    System.out.println("Voted :"+dangTin.get(i).getVoted());
                    if (dangTin.get(i).getVoted()==1){
                        System.out.println("a: "+dangTin.get(i).getBanthangdoidangtin());
                        a = dangTin.get(i).getBanthangdoidangtin();
                        b = dangTin.get(i).getBanthangdoibatdoi();
                    }
                    arrLichSuTranDau.add(new TranDauDuongClass(dangTin.get(i).getId(), dangTin.get(i).getDoibong1(), dangTin.get(i).getDoibong2(), dangTin.get(i).getNgay(), dangTin.get(i).getKhunggio_id(), dangTin.get(i).getSan_id(), a, b, dangTin.get(i).getKeo(), dangTin.get(i).getVoted()));
                }
                adapterLichSuTranDau = new LichSuTranDauAdapter(getActivity(), R.layout.dong_lich_su_tran_dau, arrLichSuTranDau);
                lvLichSuTranDau.setAdapter(adapterLichSuTranDau);
                SetListViewHeightBasedOnChildrenLichSuTranDau(adapterLichSuTranDau, lvLichSuTranDau);
            }

            @Override
            public void onFailure(Call<List<DangTin>> call, Throwable t) {

            }
        });
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
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (matchAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private void GanNoiDungListViewTranDauSapToi() {
        arrTranDauSapToi = new ArrayList<>();
        DoiBongClass doiBong1, doiBong2, doiBong3, doiBong4, doiBong5;

        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);

        Call<List<DangTin>> call = jsonApiSanBong.getCactransapdienracuadoi(doiBong.getId());
        call.enqueue(new Callback<List<DangTin>>() {
            @Override
            public void onResponse(Call<List<DangTin>> call, Response<List<DangTin>> response) {
                List<DangTin> dangTin = response.body();
                for (int i = 0; i < dangTin.size(); i++) {
                    dangTin.get(i).getDoibong1().setImageBia(anhBia);
                    dangTin.get(i).getDoibong1().setImageDaiDien(anhDaiDien);
                    dangTin.get(i).getDoibong2().setImageBia(anhBia);
                    dangTin.get(i).getDoibong2().setImageDaiDien(anhDaiDien);

                    arrTranDauSapToi.add(new TranDauDuongClass(dangTin.get(i).getId(), dangTin.get(i).getDoibong1(), dangTin.get(i).getDoibong2(), dangTin.get(i).getNgay(), dangTin.get(i).getKhunggio_id(), dangTin.get(i).getSan_id(), 0, 0, dangTin.get(i).getKeo(), 0));
//                    arrTranDauSapToi.add(new TranDauDuongClass(1, doiBong1, doiBong2, "convertDate", 3, 0, 0, 0, "Nước", 0));
                }
                System.out.println("Size: "+arrTranDauSapToi.get(0).getIdTranDau());
                System.out.println("Ngay: "+arrTranDauSapToi.get(0).getNgay());
                adapterTranDauSapToi = new TranDauSapToiAdapter(getActivity(), R.layout.dong_tran_dau_sap_toi, arrTranDauSapToi);
                lvTranDauSapToi.setAdapter(adapterTranDauSapToi);
                SetListViewHeightBasedOnChildrenTranDauSapToi(adapterTranDauSapToi, lvTranDauSapToi);
            }

            @Override
            public void onFailure(Call<List<DangTin>> call, Throwable t) {

            }
        });
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

    private void KhoiTaoListViewThanhVien() {
        arrThanhVien = new ArrayList<>();
        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        long ngayTemp = 1234596789;
        Date dateConvert = new Date(ngayTemp);
//        Call<List<UserLogin>> call = jsonApiSanBong.getDanhsachthanhviens("");
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn F", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn G", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn H", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn I", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));

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

        doiBong = (DoiBong) bundle.getSerializable("doibong1");
        imgAnhBia.setImageBitmap(doiBong.getAnhbia());
        imgAnhDaiDien.setImageBitmap(doiBong.getAnhdaidien());
        txtTenDoiBong.setText(doiBong.getTen());
        txtDiem.setText(doiBong.getSodiem() + " Điểm");
        txtDiaChi.setText(doiBong.getDiachi());
        txtTrinhDo.setText(doiBong.getTrinhdo());
        txtNgayThanhlap.setText(doiBong.getCreated_at().toString());
        System.out.println("ngaytao "+doiBong.getCreated_at());
        txtPhone.setText(doiBong.getSdt());
//        arrThanhVien = doiBong.getListThanhVien();
//        adapter = new DanhSachThanhVienAdapter(getActivity(), R.layout.dong_thanh_vien, arrThanhVien);
//        lvDanhSachThanhVien.setAdapter(adapter);
//        SetListViewHeightBasedOnChildren(adapter, lvDanhSachThanhVien);
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
