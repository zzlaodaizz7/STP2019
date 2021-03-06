package com.example.doan2019;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DangTin;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.DoiBong_NguoiDung;
import com.example.doan2019.Retrofit.JsonApiDoiBongNGuoiDung;
import com.example.doan2019.Retrofit.JsonApiKhungGio;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.KhungGio;
import com.example.doan2019.Retrofit.User;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDoiBongDaThamGiaFragment extends Fragment {
    private View view;
    private TextView txtTenDoiBong, txtDiem, txtDiaChi, txtTrinhDo, txtNgayThanhlap, txtPhone, txtDanhSachCacTinDaDang, txtQuayLai, txtKhungGioChoi;
    private ImageView imgAnhBia, imgAnhDaiDien;
    private Bundle bundle;
    private ListView lvDanhSachThanhVien, lvTranDauSapToi, lvLichSuTranDau;
    private DoiBong doiBong;
    private Dialog dialogTinNhan;
    private int dbnd_id, mode;
    private DanhSachThanhVienAdapter adapter;
    private TranDauSapToiAdapter adapterTranDauSapToi;
    private LichSuTranDauAdapter adapterLichSuTranDau;
    private ArrayList<DoiBong_NguoiDung> arrThanhVien;
    private ArrayList<TranDauDuongClass> arrTranDauSapToi;
    private ArrayList<TranDauDuongClass> arrLichSuTranDau;
    private LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    private Button btnDonXinGiaNhap, btnRoiKhoiDoiBong, btnSuaThongTinDoiBong;
    private LinearLayout LLButtonDuyetDonXin, LLButtonRoiKhoiDoi;
    private SharedPreferences sharedPreferences;
    private JsonApiSanBong jsonApiSanBong;
    private JsonApiDoiBongNGuoiDung jsonApiDoiBongNGuoiDung;
    private int quyen;
    private ArrayList<Integer> arrIDKhungGioDaChon = new ArrayList<>();
    ArrayList<String> arrGio;
    private int idDoiBong;
    JsonApiKhungGio jsonApiKhungGio;
    List<KhungGio> khungGios;
    private ArrayList<Boolean> arrGioDaChon = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_doi_bong_da_tham_gia, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        jsonApiSanBong = APIUtils.getJsonApiSanBong();

        Mapping();

        KhoiTaoListViewThanhVien();

        ClickDanhSachThanhVien();

        ClickQuayLai();

        GanNoiDungThongTinDoiBong();
//        KhoiTaoListViewThanhVien();

        GanNoiDungListViewTranDauSapToi();

        GanNoiDungListViewLichSuTranDau();

        ClickListViewTranDauSapToi();

        ClickListViewLichSuTranDau();

        ClickDanhSachCacTinDaDang();

        ClickDuyetDon();

        clickRoiKhoiDoiBong();

        ClickSuaThongTinDoiBong();

        ClickSDT();

        LoadListKhungGio();

        return view;
    }

    private void LoadListKhungGio() {
        arrGio = new ArrayList<>();
        jsonApiKhungGio = APIUtils.getJsonApiKhungGio();
        Call<List<KhungGio>> call = jsonApiKhungGio.getKhungGios();
        try {
            call.enqueue(new Callback<List<KhungGio>>() {
                @Override
                public void onResponse(Call<List<KhungGio>> call, Response<List<KhungGio>> response) {
                    try {
                        khungGios = response.body();
                        for (KhungGio khungGio : khungGios) {
                            arrGio.add(khungGio.getThoigian());
                        }
                        arrGioDaChon = new ArrayList<>();
                        for (int i = 0; i < arrGio.size(); i++) {
                            arrGioDaChon.add(false);
                        }
                    } catch (Exception ex) {
                        Log.e("BBB", ex.toString());
                    }
                    Call<List<KhungGio>> call1 = jsonApiSanBong.getKhunggiodachon(idDoiBong);
                    try {
                        call1.enqueue(new Callback<List<KhungGio>>() {
                            @Override
                            public void onResponse(Call<List<KhungGio>> call, Response<List<KhungGio>> response) {
                                try {
                                    List<KhungGio> khungGios = response.body();
                                    for (KhungGio khungGio : khungGios) {
                                        Log.d("TAG", "onResponse: " + khungGio.getKhunggio_id());
                                        arrIDKhungGioDaChon.add(khungGio.getKhunggio_id());
                                        arrGioDaChon.set(khungGio.getKhunggio_id(), true);
                                    }
                                    String gioDaChon = "";
                                    int j = -1;
                                    for (int i = 0; i < arrGio.size(); i++) {
                                        if (arrGioDaChon.get(i) == true) {
                                            arrIDKhungGioDaChon.add(i);
                                            gioDaChon += arrGio.get(i);
                                            Log.e("DxTrue", arrGio.get(i));
                                            j = i;
                                            break;
                                        }
                                    }
                                    if (j != -1) {
                                        for (int i = j + 1; i < arrGio.size(); i++) {
                                            if (arrGioDaChon.get(i) == true) {
                                                arrIDKhungGioDaChon.add(i);
                                                gioDaChon += ", " + arrGio.get(i);
                                                Log.e("DxTrue", arrGio.get(i));
                                            }
                                        }
                                    }
                                    txtKhungGioChoi.setText(gioDaChon);
                                    Log.e("Dx", gioDaChon);
                                }
                                catch (Exception ex){
                                    Log.e("BBB", ex.toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<List<KhungGio>> call, Throwable t) {

                            }
                        });
                    } catch (Exception ex) {
                        Log.e("BBB", ex.toString());
                    }
                }

                @Override
                public void onFailure(Call<List<KhungGio>> call, Throwable t) {

                }
            });
        } catch (Exception ex) {
            Log.e("BBB", ex.toString());
        }
    }

    private void ClickSDT() {
        txtPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtPhone.getText().toString().equals("")) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + txtPhone.getText().toString()));
                    if (ActivityCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    getActivity().startActivity(callIntent);
                }
            }
        });
    }

    private void ClickSuaThongTinDoiBong() {
        btnSuaThongTinDoiBong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChinhSuaThongTinDoiBongFragment chinhSuaThongTinDoiBongFragment = new ChinhSuaThongTinDoiBongFragment();
                Bundle bundleSuaThongTinDoiBong = new Bundle();
                bundleSuaThongTinDoiBong.putSerializable("doibong", doiBong);
                chinhSuaThongTinDoiBongFragment.setArguments(bundleSuaThongTinDoiBong);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chinhSuaThongTinDoiBongFragment);
            }
        });
    }

    private void clickRoiKhoiDoiBong() {
        btnRoiKhoiDoiBong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == 3) {
                    DoiBong_NguoiDung doiBong_nguoiDung = new DoiBong_NguoiDung(doiBong.getId(), sharedPreferences.getInt("id", -1));
                    Call<DoiBong_NguoiDung> call = jsonApiDoiBongNGuoiDung.postThanhVien(doiBong_nguoiDung);
                    call.enqueue(new Callback<DoiBong_NguoiDung>() {
                        @Override
                        public void onResponse(Call<DoiBong_NguoiDung> call, Response<DoiBong_NguoiDung> response) {
                            mode = 2;
                            dbnd_id = response.body().getId();
                            showDialogTinNhan("Bạn đã xin tham gia vào đội bóng.");
                            hideDialogTinNhan();
                            btnRoiKhoiDoiBong.setText("Hủy tham gia");
                        }

                        @Override
                        public void onFailure(Call<DoiBong_NguoiDung> call, Throwable t) {
                        }
                    });
                } else {
                    Call<DoiBong_NguoiDung> call = jsonApiDoiBongNGuoiDung.deleteThanhVien(dbnd_id);
                    call.enqueue(new Callback<DoiBong_NguoiDung>() {
                        @Override
                        public void onResponse(Call<DoiBong_NguoiDung> call, Response<DoiBong_NguoiDung> response) {
                            if (mode == 2) {
                                showDialogTinNhan("Bạn đã hủy xin tham gia vào đội bóng.");
                            }
                            if (mode == 1) {
                                showDialogTinNhan("Bạn đã rời khỏi đội bóng.");
                            }
                            mode = 3;

                            btnRoiKhoiDoiBong.setText("Tham gia vào FC");
                            hideDialogTinNhan();
                        }

                        @Override
                        public void onFailure(Call<DoiBong_NguoiDung> call, Throwable t) {

                        }
                    });
                }
                LLButtonDuyetDonXin.setVisibility(View.GONE);
            }
        });

    }

    private void ClickDuyetDon() {
        btnDonXinGiaNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DanhSachDonXinGiaNhapFCFragment danhSachDonXinGiaNhapFCFragment = new DanhSachDonXinGiaNhapFCFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("doibong_id", doiBong.getId());
                danhSachDonXinGiaNhapFCFragment.setArguments(bundle);

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
        try {
            arrLichSuTranDau = new ArrayList<>();
            Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
            Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);
            Call<List<DangTin>> call = jsonApiSanBong.getCactrandaketthuc(doiBong.getId());
            call.enqueue(new Callback<List<DangTin>>() {
                @Override
                public void onResponse(Call<List<DangTin>> call, Response<List<DangTin>> response) {
                    try {
                        List<DangTin> dangTin = response.body();
                        if (response.body() == null) {
                            return;
                        }
                        if (dangTin.size() == 0) {
                            return;
                        }
                        for (int i = 0; i < dangTin.size(); i++) {
                            dangTin.get(i).getDoibong1().setImageBia(anhBia);
                            dangTin.get(i).getDoibong1().setImageDaiDien(anhDaiDien);
                            dangTin.get(i).getDoibong2().setImageBia(anhBia);
                            dangTin.get(i).getDoibong2().setImageDaiDien(anhDaiDien);
                            int a = 0, b = 0;
                            if (dangTin.get(i).getVoted() == 1) {
                                a = dangTin.get(i).getBanthangdoidangtin();
                                b = dangTin.get(i).getBanthangdoibatdoi();
                            }
                            arrLichSuTranDau.add(new TranDauDuongClass(dangTin.get(i).getId(), dangTin.get(i).getDoibong1(), dangTin.get(i).getDoibong2(), dangTin.get(i).getNgay(), dangTin.get(i).getKhunggio_id(), dangTin.get(i).getSan_id(), a, b, dangTin.get(i).getKeo(), dangTin.get(i).getVoted()));
                        }
                        adapterLichSuTranDau = new LichSuTranDauAdapter(getActivity(), R.layout.dong_lich_su_tran_dau, arrLichSuTranDau);
                        lvLichSuTranDau.setAdapter(adapterLichSuTranDau);
                        SetListViewHeightBasedOnChildrenLichSuTranDau(adapterLichSuTranDau, lvLichSuTranDau);
                    } catch (Exception ex) {
                        Log.e("BBB", ex.toString());
                    }
                }

                @Override
                public void onFailure(Call<List<DangTin>> call, Throwable t) {

                }
            });
        } catch (Exception ex) {
            Log.e("BBB", ex.toString());
        }
    }

    private void SetListViewHeightBasedOnChildrenLichSuTranDau(LichSuTranDauAdapter matchAdapter, ListView listView) {
        try {
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
        } catch (Exception ex) {
            Log.e("BBB", ex.toString());
        }
    }

    private void GanNoiDungListViewTranDauSapToi() {
        try {
            arrTranDauSapToi = new ArrayList<>();
            Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
            Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);

            Call<List<DangTin>> call = jsonApiSanBong.getCactransapdienracuadoi(doiBong.getId());
            call.enqueue(new Callback<List<DangTin>>() {
                @Override
                public void onResponse(Call<List<DangTin>> call, Response<List<DangTin>> response) {
                    List<DangTin> dangTin = response.body();
                    try {
                        if (dangTin.size() != 0) {
                            for (int i = 0; i < dangTin.size(); i++) {
                                dangTin.get(i).getDoibong1().setImageBia(anhBia);
                                dangTin.get(i).getDoibong1().setImageDaiDien(anhDaiDien);
                                dangTin.get(i).getDoibong2().setImageBia(anhBia);
                                dangTin.get(i).getDoibong2().setImageDaiDien(anhDaiDien);

                                arrTranDauSapToi.add(new TranDauDuongClass(dangTin.get(i).getId(), dangTin.get(i).getDoibong1(), dangTin.get(i).getDoibong2(), dangTin.get(i).getNgay(), dangTin.get(i).getKhunggio_id(), dangTin.get(i).getSan_id(), 0, 0, dangTin.get(i).getKeo(), 0));
                            }

                            adapterTranDauSapToi = new TranDauSapToiAdapter(getActivity(), R.layout.dong_tran_dau_sap_toi, arrTranDauSapToi);
                            lvTranDauSapToi.setAdapter(adapterTranDauSapToi);
                            SetListViewHeightBasedOnChildrenTranDauSapToi(adapterTranDauSapToi, lvTranDauSapToi);
                        }
                    } catch (Exception ex) {
                        Log.d("BBB", ex.toString());
                    }
                }

                @Override
                public void onFailure(Call<List<DangTin>> call, Throwable t) {
                    System.out.println("Loi~: " + t.getMessage());
                }
            });
        } catch (Exception ex) {
            Log.e("BBB", ex.toString());
        }
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
                User thanhVien = arrThanhVien.get(i).getUser();
                bundleThanhVien.putSerializable("thanhvien", thanhVien);
                chiTietThanhVienFragment.setArguments(bundleThanhVien);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietThanhVienFragment);
            }
        });
    }

    private void KhoiTaoListViewThanhVien() {
        try {
            arrThanhVien = new ArrayList<>();
            Call<List<DoiBong_NguoiDung>> call = jsonApiDoiBongNGuoiDung.getDanhSachThanhVien(doiBong.getId());
            call.enqueue(new Callback<List<DoiBong_NguoiDung>>() {
                @Override
                public void onResponse(Call<List<DoiBong_NguoiDung>> call, Response<List<DoiBong_NguoiDung>> response) {
                    List<DoiBong_NguoiDung> doiBong_nguoiDungs = response.body();
                    if (doiBong_nguoiDungs.size() == 0) {
                        return;
                    }
                    for (DoiBong_NguoiDung doiBong_nguoiDung : doiBong_nguoiDungs) {
                        if (mode == 1 && doiBong_nguoiDung.getTrangthai() == 0 &&
                                doiBong_nguoiDung.getUserId() == sharedPreferences.getInt("id", -1)) {
                            mode = 2;
                            btnRoiKhoiDoiBong.setText("Hủy tham gia");
                        }

                        arrThanhVien.add(doiBong_nguoiDung);
                    }
                    adapter = new DanhSachThanhVienAdapter(getActivity(), R.layout.dong_thanh_vien, arrThanhVien, quyen);
                    lvDanhSachThanhVien.setAdapter(adapter);
                    SetListViewHeightBasedOnChildren(adapter, lvDanhSachThanhVien);
                }

                @Override
                public void onFailure(Call<List<DoiBong_NguoiDung>> call, Throwable t) {
                }
            });
        } catch (Exception ex) {
            Log.e("BBB", ex.toString());
        }


//        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
//        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
//        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
//        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
//        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn F", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
//        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn G", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
//        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn H", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));
//        arrThanhVien.add(new ThanhVienDoiBongClass("Nguyễn Văn I", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert,"123"));


    }

    private void SetListViewHeightBasedOnChildren(DanhSachThanhVienAdapter matchAdapter, ListView listView) {
        try {
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
        } catch (Exception ex) {
            Log.d("BBB", ex.toString());
        }
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
        try {
            idDoiBong = doiBong.getId();
            bundle = getArguments();

            if (doiBong.getAnhdaidien() != null) {
                Log.e("BBB", "anhDaiDienDoiBong: " + doiBong.getAnhdaidien());
                Picasso.get().load(APIUtils.BASE_URL + doiBong.getAnhdaidien()).into(imgAnhDaiDien);
            }
            if (doiBong.getAnhbia() != null) {
                Log.e("BBB", "anhBiaDoiBong: " + doiBong.getAnhbia());
                Picasso.get().load(APIUtils.BASE_URL + doiBong.getAnhbia()).into(imgAnhBia);
            }
            txtTenDoiBong.setText(doiBong.getTen());
            txtDiem.setText(doiBong.getSodiem() + " Điểm");
            txtDiaChi.setText(doiBong.getDiachi());
            txtTrinhDo.setText(doiBong.getTrinhdo());
            txtNgayThanhlap.setText(doiBong.getCreated_at().toString());

//        System.out.println("ngaytao " + doiBong.getCreated_at());
            txtPhone.setText(doiBong.getSdt());
//        arrThanhVien = doiBong.getListThanhVien();
//        adapter = new DanhSachThanhVienAdapter(getActivity(), R.layout.dong_thanh_vien, arrThanhVien);
//        lvDanhSachThanhVien.setAdapter(adapter);
//        SetListViewHeightBasedOnChildren(adapter, lvDanhSachThanhVien);
        } catch (Exception ex) {
            Log.e("BBB", ex.toString());
        }
    }

    private void Mapping() {
        txtKhungGioChoi = view.findViewById(R.id.TextViewKhungGioChoi);
        bundle = getArguments();

        DoiBong_NguoiDung doiBong_nguoiDung = (DoiBong_NguoiDung) bundle.getSerializable("doibong1");
        quyen = doiBong_nguoiDung.getPhanquyenId();

        dbnd_id = doiBong_nguoiDung.getId();

        doiBong = doiBong_nguoiDung.getDoibong();

        jsonApiDoiBongNGuoiDung = APIUtils.getJsonApiDoiBongNguoiDung();

        btnSuaThongTinDoiBong = view.findViewById(R.id.ButtonSuaThongTinDoiBong);
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
        btnRoiKhoiDoiBong = view.findViewById(R.id.ButtonRoiKhoiDoi);
        LLButtonDuyetDonXin = view.findViewById(R.id.LLButtonDuyetDonXin);
        LLButtonRoiKhoiDoi = view.findViewById(R.id.LLButtonRoiKhoiDoi);

        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);


        mode = 1;
        // 1 la roi khoi doi bong, 2 la huy tham gia doi bong, 3 là tham gia FC

        if (doiBong_nguoiDung.getPhanquyenId() != 1) {
            LLButtonDuyetDonXin.setVisibility(View.GONE);
        }

        if (quyen == 2) {
            txtDanhSachCacTinDaDang.setVisibility(View.INVISIBLE);
            btnSuaThongTinDoiBong.setVisibility(View.INVISIBLE);
            btnDonXinGiaNhap.setVisibility(View.INVISIBLE);
        }
    }

    private void showDialogTinNhan(String text) {
        dialogTinNhan = new Dialog(getActivity());
        dialogTinNhan.setContentView(R.layout.dialog_message);
        dialogTinNhan.show();
        TextView tvTinNhan = (TextView) dialogTinNhan.findViewById(R.id.tvTinNhan);
        tvTinNhan.setText(text);
    }

    private void hideDialogTinNhan() {
        TextView tvHuy = dialogTinNhan.findViewById(R.id.tvHuy);
        tvHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTinNhan.cancel();
            }
        });
    }
}
