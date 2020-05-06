package com.example.doan2019;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.DTO.DangTinDTO;
import com.example.doan2019.Mapper.ModelMapper;
import com.example.doan2019.Retrofit.DangTin;
import com.example.doan2019.Retrofit.DoiBong_NguoiDung;
import com.example.doan2019.Retrofit.JsonApiDoiBongNGuoiDung;
import com.example.doan2019.Retrofit.KhungGio;
import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.JsonApiDangTin;
import com.example.doan2019.Retrofit.JsonApiDoiBong;
import com.example.doan2019.Retrofit.JsonApiKhungGio;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.SanBong;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.droidsonroids.gif.GifTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimDoiFragment extends Fragment {

    ScrollView scrollView;
    GifTextView gifLoading;
    EditText editTextTimTheoTenDoiHoacTenSan;
    ListView listViewTinTimDoi, listViewTrangThai, listViewTrinhDo, listViewChonTimKiemTheoTenHaySan, mainLV;
    ArrayList<DangTinDTO> dangTinDTOArrayList, dangTinDTOTimKiemArrayList;
    ArrayList<SanBong> sanBongArrayList;
    ArrayList<DangTin> dangTinArrayList;
    ArrayList<DoiBong> doiBongArrayList;
    ArrayList<KhungGio> khungGioArrayList;
    DangTinAdapter dangTinAdapter;
    Button btnDangTin, btnTimTranDau, btnChonTimKiemTheoTenHaySan, btnChonTrangThai, btnChonTrinhDo;
    Dialog dialogChonTrangThai, dialogChonTrinhDo, dialogChonTimKiemTheoTenHaySan, dialogTinNhan;
    ArrayList<String> statusArrayList, levelArrayList, danhMucTimKiemTheoTenHaySanArrayList;
    ArrayList<DoiBong_NguoiDung> doiBong_nguoiDungArrayList;
    TextView txtChonNgay, tvTinNhan, tvHuy;
    ImageButton btnThongBao;
    String doibongTK, sanbongTK, trangthaiTK, trinhdoTK, thoigianTK, danhmucTK, Auth = "";
    int doitruongdoidangtin_id, doitruongdoibatdoi_id;
    View view;
    Map<String, String> header;
    JsonApiKhungGio jsonApiKhungGio;
    JsonApiSanBong jsonApiSanBong;
    JsonApiDoiBong jsonApiDoiBong;
    JsonApiDangTin jsonApiDangTin;
    JsonApiDoiBongNGuoiDung jsonApiDoiBongNGuoiDung;
    SharedPreferences sharedPreferences, sharedPreferencesLoadTimDoi;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_tim_doi, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        mapping();

        ClickEditTextTimKiem();

        showLoadingGif();

        loadListViewTinTimDoi();

        clickChonNgay();

        clickChonTrangThai();

        clickChonTimKiemTheoTenHaySan();

        clickChonTrinhDo();

        clickChonDangTin();

        clickChonThongBao();

        clickChonTimTranDau();

        clickChonListViewTinTimDoi();

        return view;
    }

    private void ClickEditTextTimKiem() {
        editTextTimTheoTenDoiHoacTenSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextTimTheoTenDoiHoacTenSan.setFocusableInTouchMode(true);
            }
        });
    }

    private void loadListViewTinTimDoi() {
        if (sharedPreferencesLoadTimDoi.getString("isLoaded", "").equals("true")) {

            DangTinService.loadDangTin(mainLV, dangTinDTOArrayList);
            dangTinAdapter = new DangTinAdapter(getActivity(), R.layout._match, dangTinDTOArrayList);
            listViewTinTimDoi.setAdapter(dangTinAdapter);
            setListViewHeightBasedOnChildren(dangTinAdapter, listViewTinTimDoi);
            //listViewTinTimDoi.getAdapter().ge
        } else {
            loadListDoiBong();
            loadListKhungGio();
            loadListViewTinTimDoi1();
        }
    }

    private void loadListKhungGio() {
        Call<List<KhungGio>> call1 = jsonApiKhungGio.getKhungGios(header);
        call1.enqueue(new Callback<List<KhungGio>>() {
            @Override
            public void onResponse(Call<List<KhungGio>> call, Response<List<KhungGio>> response) {
                try {
                    List<KhungGio> khungGios = response.body();
                    if (khungGios.size() == 0) {
                        hideLoadinggif();
                        showDialogTinNhan("Không có tin mới nào");
                        hideDialogTinNhan();
                        return;
                    }
                    for (KhungGio khungGio : khungGios) {
                        khungGioArrayList.add(khungGio);
                        Log.d("test", khungGioArrayList + "");
                    }
                }
                catch (Exception ex){
                    Log.e("BBB", ex.toString());
                }
            }

            @Override
            public void onFailure(Call<List<KhungGio>> call, Throwable t) {
            }
        });
    }

    private void loadListDoiBong() {
        Call<List<DoiBong>> call2 = jsonApiDoiBong.getDoiBongs(header);
        call2.enqueue(new Callback<List<DoiBong>>() {
            @Override
            public void onResponse(Call<List<DoiBong>> call, Response<List<DoiBong>> response) {
                try {
//                System.out.println("test");
                    List<DoiBong> doiBongs = response.body();
                    if (doiBongs.size() == 0) {
                        hideLoadinggif();
                        showDialogTinNhan("Không có tin mới nào");
                        hideDialogTinNhan();
                        return;
                    }
                    for (DoiBong doiBong : doiBongs) {
                        doiBongArrayList.add(doiBong);
                        Log.d("test", doiBong.getTen() + "");
                    }
                }
                catch (Exception ex){
                    Log.e("BBB", ex.toString());
                }
            }

            @Override
            public void onFailure(Call<List<DoiBong>> call, Throwable t) {
            }
        });
    }


    private void loadListViewTinTimDoi1() {
        // lay du lieu san bong
        sanBongArrayList = new ArrayList<>();
        Call<List<SanBong>> call = jsonApiSanBong.getSanbongs();
        call.enqueue(new Callback<List<SanBong>>() {
            @Override
            public void onResponse(Call<List<SanBong>> call, Response<List<SanBong>> response) {
                try {
                    List<SanBong> sanBongs = response.body();
                    if (sanBongs.size() == 0) {
                        hideLoadinggif();
                        showDialogTinNhan("Không có tin mới nào");
                        hideDialogTinNhan();
                        return;
                    }
                    for (SanBong sanBong : sanBongs) {
                        sanBongArrayList.add(sanBong);
                    }
                } catch (Exception ex) {
                    Log.e("BBB", ex.toString());
                }
                Call<List<DoiBong_NguoiDung>> call3 = jsonApiDoiBongNGuoiDung.getThanhViens(header);
                call3.enqueue(new Callback<List<DoiBong_NguoiDung>>() {
                    @Override
                    public void onResponse(Call<List<DoiBong_NguoiDung>> call, Response<List<DoiBong_NguoiDung>> response) {
                        try {
                            List<DoiBong_NguoiDung> doiBong_nguoiDungs = response.body();
                            if (doiBong_nguoiDungs.size() == 0) {
                                hideLoadinggif();
                                showDialogTinNhan("Không có tin mới nào");
                                hideDialogTinNhan();
                                return;
                            }
                            for (DoiBong_NguoiDung doiBong_nguoiDung : doiBong_nguoiDungs) {
                                doiBong_nguoiDungArrayList.add(doiBong_nguoiDung);
                            }
                        }
                        catch (Exception ex){
                            Log.e("BBB", ex.toString());
                        }
                        Call<List<DangTin>> callDangTin = jsonApiDangTin.getDangTins(header);
                        callDangTin.enqueue(new Callback<List<DangTin>>() {
                            @Override
                            public void onResponse(Call<List<DangTin>> call, Response<List<DangTin>> response) {
                                try {
                                    List<DangTin> dangTins = response.body();
                                    Log.d("timdoi", response.body() + " " + dangTins.size());
                                    if (dangTins.size() == 0) {
                                        Log.d("timdoi", "ko co tin moi");
                                        hideLoadinggif();
                                        showDialogTinNhan("Không có tin mới nào");
                                        hideDialogTinNhan();
                                        return;
                                    }
                                    for (DangTin dangTin : dangTins) {
                                        dangTinArrayList.add(dangTin);
                                        String doidangtin_ten = "";
                                        String trinhdo = "";
                                        String trangthai = "";
                                        String san_ten = "";
                                        String khunggio_giatri = "";
                                        String doibatdoi_ten = "";
                                        doitruongdoidangtin_id = -1;
                                        doitruongdoibatdoi_id = -1;

                                        for (DoiBong_NguoiDung doiBong_nguoiDung : doiBong_nguoiDungArrayList) {
                                            if (doiBong_nguoiDung.getDoibongId() == dangTin.getDoidangtin_id() && doiBong_nguoiDung.getPhanquyenId() == 1) {
                                                doitruongdoidangtin_id = doiBong_nguoiDung.getUserId();
                                                break;
                                            }
                                        }

                                        for (KhungGio khungGio : khungGioArrayList) {
                                            if (khungGio.getId() == dangTin.getKhunggio_id()) {
                                                khunggio_giatri = khungGio.getThoigian();
                                                break;
                                            }
                                        }
                                        if (dangTin.getSan_id() != -1) {
                                            trangthai = "Có sân nhà";
                                            for (SanBong sanBong : sanBongArrayList) {
                                                if (sanBong.getId() == dangTin.getSan_id()) {
                                                    san_ten = sanBong.getTen();
                                                    break;
                                                }
                                            }
                                        } else {
                                            san_ten = "Không có sân nhà";
                                            trangthai = "Cần đi khách";
                                        }
                                        if (dangTin.getDoibatdoi_id() != -1) {
                                            for (DoiBong doiBong : doiBongArrayList) {
                                                if (doiBong.getId() == dangTin.getDoibatdoi_id()) {
                                                    doibatdoi_ten = doiBong.getTen();
                                                    break;
                                                }
                                            }
                                            for (DoiBong_NguoiDung doiBong_nguoiDung : doiBong_nguoiDungArrayList) {
                                                if (doiBong_nguoiDung.getDoibongId() == dangTin.getDoibatdoi_id() && doiBong_nguoiDung.getPhanquyenId() == 1) {
                                                    doitruongdoibatdoi_id = doiBong_nguoiDung.getUserId();
                                                    break;
                                                }
                                            }
                                        }
                                        for (DoiBong doiBong : doiBongArrayList) {
                                            if (doiBong.getId() == dangTin.getDoidangtin_id()) {
                                                doidangtin_ten = doiBong.getTen();
                                                trinhdo = doiBong.getTrinhdo();
                                                break;
                                            }
                                        }


                                        DangTinDTO dangTinDTO = ModelMapper.toDangTinDTO(dangTin, doidangtin_ten, doitruongdoidangtin_id, doibatdoi_ten, doitruongdoibatdoi_id, trangthai, trinhdo, san_ten, khunggio_giatri);
                                        dangTinDTOArrayList.add(dangTinDTO);

                                        //Log.d("uu", dangTinDTO.getDoitruongdoibatdoi_id()+" "+dangTinDTO.getDoitruongdoidangtin_id());
                                    }
                                    SharedPreferences.Editor editor = sharedPreferencesLoadTimDoi.edit();
                                    editor.putString("isLoaded", "true");
                                    editor.commit();
                                    dangTinAdapter = new DangTinAdapter(getActivity(), R.layout._match, dangTinDTOArrayList);
                                    mainLV.setAdapter(dangTinAdapter);
                                    listViewTinTimDoi.setAdapter(dangTinAdapter);
                                    setListViewHeightBasedOnChildren(dangTinAdapter, listViewTinTimDoi);
                                }
                                catch (Exception ex){
                                    Log.e("BBB", ex.toString());
                                }
                            }


                            @Override
                            public void onFailure(Call<List<DangTin>> call, Throwable t) {
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<DoiBong_NguoiDung>> call, Throwable t) {
                    }
                });
            }

            @Override
            public void onFailure(Call<List<SanBong>> call, Throwable t) {
                Log.d("retrofit", "loi: " + t);
            }
        });
    }

    private void mapping() {
        sharedPreferencesLoadTimDoi = getActivity().getSharedPreferences("LoadDataDangTin", Context.MODE_PRIVATE);

        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        Auth = sharedPreferences.getString("token", "");
        header = new HashMap<>();
        header.put("value", "application/json");
        header.put("Accept", "application/json");
        header.put("Authorization", "Bearer " + Auth);

        jsonApiKhungGio = APIUtils.getJsonApiKhungGio();
        jsonApiDoiBong = APIUtils.getJsonApiDoiBong();
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        jsonApiDangTin = APIUtils.getJsonApiDangTin();
        jsonApiDoiBongNGuoiDung = APIUtils.getJsonApiDoiBongNguoiDung();

        doibongTK = "";
        sanbongTK = "";
        trangthaiTK = "";
        trinhdoTK = "";
        thoigianTK = "";
        danhmucTK = "";

        dangTinArrayList = new ArrayList<>();
        khungGioArrayList = new ArrayList<>();
        doiBongArrayList = new ArrayList<>();
        sanBongArrayList = new ArrayList<>();
        dangTinDTOArrayList = new ArrayList<>();
        doiBong_nguoiDungArrayList = new ArrayList<>();
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        btnDangTin = (Button) view.findViewById(R.id.btnDangTin);
        btnChonTrangThai = (Button) view.findViewById(R.id.btnChonTrangThai);
        btnChonTrinhDo = (Button) view.findViewById(R.id.btnChonTrinhDo);
        listViewTinTimDoi = (ListView) view.findViewById(R.id.listViewTinTimDoi);
        txtChonNgay = (TextView) view.findViewById(R.id.txtChonNgay);
        editTextTimTheoTenDoiHoacTenSan = (EditText) view.findViewById(R.id.editTextTimTheoTenDoiHoacTenSan);
        editTextTimTheoTenDoiHoacTenSan.setFocusableInTouchMode(false);
        btnThongBao = (ImageButton) view.findViewById(R.id.btnThongBao);
        btnTimTranDau = (Button) view.findViewById(R.id.btnTimTranDau);
        btnChonTimKiemTheoTenHaySan = (Button) view.findViewById(R.id.btnChonTimKiemTheoTenHaySan);
        //list view cua mainActivity
        mainLV = (ListView) getActivity().findViewById(R.id.mainLV);
        gifLoading = (GifTextView) view.findViewById(R.id.gifloading);
    }

    private void showLoadingGif() {
        gifLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoadinggif() {
        gifLoading.setVisibility(View.INVISIBLE);
    }

    private void clickChonNgay() {
        final Calendar calendar = Calendar.getInstance();
        final int ngay = calendar.get(Calendar.DATE);
        final int thang = calendar.get(Calendar.MONTH);
        final int nam = calendar.get(Calendar.YEAR);
        txtChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        thoigianTK = simpleDateFormat.format(calendar.getTime());
                        txtChonNgay.setText(thoigianTK);
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
    }

    private void setListViewHeightBasedOnChildren(DangTinAdapter dangTinAdapter, ListView listView) {
        try {
            if (dangTinAdapter == null) {
                return;
            }
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            int totalHeight = 0;
            View view = null;
            for (int i = 0; i < dangTinAdapter.getCount(); i++) {
                view = dangTinAdapter.getView(i, view, listView);
                if (i == 0)
                    view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

                view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                //Log.d("timsan", totalHeight+"");
                totalHeight += view.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (dangTinAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        } catch (Exception ex) {
            Log.e("BBB", ex.toString());
        }
    }

    private void clickChonTrangThai() {
        btnChonTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogChonTrangThai();
                clickDialogChonTrangThai();
            }
        });
    }

    void clickDialogChonTrangThai() {
        listViewTrangThai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                trangthaiTK = statusArrayList.get(position);
                btnChonTrangThai.setText(trangthaiTK);
                dialogChonTrangThai.cancel();
            }
        });
    }

    private void showDialogChonTrangThai() {
        dialogChonTrangThai = new Dialog(getActivity());
        dialogChonTrangThai.setContentView(R.layout.dialog_chon_trang_thai);
        dialogChonTrangThai.show();

        listViewTrangThai = dialogChonTrangThai.findViewById(R.id.listViewTrangThai);
        statusArrayList = new ArrayList<>();

        statusArrayList.add("Có sân nhà");
        statusArrayList.add("Cần đi khách");

        ArrayAdapter statusAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, statusArrayList);

        listViewTrangThai.setAdapter(statusAdapter);
    }

    private void clickChonTrinhDo() {
        btnChonTrinhDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogChonTrinhDo();
                clickDialogChonTrinhDo();
            }
        });
    }

    void clickDialogChonTrinhDo() {
        listViewTrinhDo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                trinhdoTK = levelArrayList.get(position);
                btnChonTrinhDo.setText(trinhdoTK);
                dialogChonTrinhDo.cancel();
            }
        });
    }

    private void showDialogChonTrinhDo() {
        dialogChonTrinhDo = new Dialog(getActivity());
        dialogChonTrinhDo.setContentView(R.layout.dialog_chon_trinh_do);
        dialogChonTrinhDo.show();

        listViewTrinhDo = dialogChonTrinhDo.findViewById(R.id.listViewTrinhDo);
        levelArrayList = new ArrayList<>();

        levelArrayList.add("Trung bình");
        levelArrayList.add("Khá");

        ArrayAdapter levelAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, levelArrayList);
        listViewTrinhDo.setAdapter(levelAdapter);
    }

    private void clickChonTimKiemTheoTenHaySan() {
        btnChonTimKiemTheoTenHaySan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogChonTimKiemTheoTenHaySan();
                clickDialogChonTimKiemTheoTenHaySan();
            }
        });
    }

    void clickDialogChonTimKiemTheoTenHaySan() {
        listViewChonTimKiemTheoTenHaySan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                danhmucTK = danhMucTimKiemTheoTenHaySanArrayList.get(position);
                btnChonTimKiemTheoTenHaySan.setText(danhmucTK);
                dialogChonTimKiemTheoTenHaySan.cancel();
            }
        });
    }

    private void showDialogChonTimKiemTheoTenHaySan() {
        dialogChonTimKiemTheoTenHaySan = new Dialog(getActivity());
        dialogChonTimKiemTheoTenHaySan.setContentView(R.layout.dialog_chon_tim_kiem_theo_ten_hay_san);
        dialogChonTimKiemTheoTenHaySan.show();

        listViewChonTimKiemTheoTenHaySan = dialogChonTimKiemTheoTenHaySan.findViewById(R.id.listViewChonTimKiemTheoTenHaySan);
        danhMucTimKiemTheoTenHaySanArrayList = new ArrayList<>();

        danhMucTimKiemTheoTenHaySanArrayList.add("Đội bóng");
        danhMucTimKiemTheoTenHaySanArrayList.add("Sân bóng");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, danhMucTimKiemTheoTenHaySanArrayList);
        listViewChonTimKiemTheoTenHaySan.setAdapter(arrayAdapter);
    }

    private void showDialogTinNhan(String text) {
        dialogTinNhan = new Dialog(getActivity());
        dialogTinNhan.setContentView(R.layout.dialog_message);
        dialogTinNhan.show();
        tvTinNhan = (TextView) dialogTinNhan.findViewById(R.id.tvTinNhan);
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

    private void clickChonThongBao() {
        btnThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getString("token", "") == "") {
                    showDialogTinNhan("Bạn chưa đăng nhập");
                    hideDialogTinNhan();
                }else
                    langNgheSuKienChuyenFragment.ChuyenHuongFragment(new ThongBaoFragment());

            }
        });
    }

    private void clickChonDangTin() {
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getString("token", "") == "") {
                    showDialogTinNhan("Bạn chưa đăng nhập");
                    hideDialogTinNhan();
                }else langNgheSuKienChuyenFragment.ChuyenHuongFragment(new DangTinFragment());
            }
        });
    }

    private void clickChonListViewTinTimDoi() {
        listViewTinTimDoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BatDoiFragment batDoiFragment = new BatDoiFragment();

                Bundle bundle = new Bundle();
                DangTinDTO dangTinDTO;
                dangTinDTO = dangTinDTOArrayList.get(position);
                bundle.putSerializable("batdoi", dangTinDTO);
                batDoiFragment.setArguments(bundle);

                //getFragmentManager().beginTransaction().replace(R.id.fragment_container, batDoiFragment).commit();
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(batDoiFragment);
            }
        });
    }

    private void clickChonTimTranDau() {

        btnTimTranDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dangTinDTOArrayList.size() == 0) {
                    showDialogTinNhan("Không tìm được tin nào.");
                    hideDialogTinNhan();
                }
                dangTinDTOTimKiemArrayList = new ArrayList<>();
                sanbongTK = editTextTimTheoTenDoiHoacTenSan.getText().toString();
                doibongTK = editTextTimTheoTenDoiHoacTenSan.getText().toString();
                for (DangTinDTO dangTinDTO : dangTinDTOArrayList) {
                    dangTinDTOTimKiemArrayList.add(dangTinDTO);
                }
                if (!thoigianTK.equals("")) {
                    for (int i = 0; i < dangTinDTOTimKiemArrayList.size(); i++) {
                        if (!thoigianTK.equals(dangTinDTOTimKiemArrayList.get(i).getNgay())) {
                            dangTinDTOTimKiemArrayList.remove(dangTinDTOTimKiemArrayList.get(i));
                            Log.d("timkiem", dangTinDTOTimKiemArrayList + "");
                            i--;
                        }
                    }
                }
                if (danhmucTK.equals("Sân bóng")) {
                    if (!sanbongTK.equals("")) {
                        for (int i = 0; i < dangTinDTOTimKiemArrayList.size(); i++) {
                            if (!sanbongTK.equals(dangTinDTOTimKiemArrayList.get(i).getSan_ten())) {
                                dangTinDTOTimKiemArrayList.remove(dangTinDTOTimKiemArrayList.get(i));
                                i--;
                            }
                        }
                    }
                } else if (danhmucTK.equals("Đội bóng")) {
                    if (!doibongTK.equals("")) {
                        for (int i = 0; i < dangTinDTOTimKiemArrayList.size(); i++) {
                            if (!doibongTK.equals(dangTinDTOTimKiemArrayList.get(i).getDoidangtin_ten())) {
                                dangTinDTOTimKiemArrayList.remove(dangTinDTOTimKiemArrayList.get(i));
                                i--;
                            }
                        }
                    }
                }
                if (!trangthaiTK.equals("")) {
                    for (int i = 0; i < dangTinDTOTimKiemArrayList.size(); i++) {
                        if (!trangthaiTK.equals(dangTinDTOTimKiemArrayList.get(i).getTrangthai())) {
                            dangTinDTOTimKiemArrayList.remove(dangTinDTOTimKiemArrayList.get(i));
                            i--;
                        }
                    }
                }
                if (!trinhdoTK.equals("")) {
                    for (int i = 0; i < dangTinDTOTimKiemArrayList.size(); i++) {
                        if (!trinhdoTK.equals(dangTinDTOTimKiemArrayList.get(i).getTrinhdo())) {
                            dangTinDTOTimKiemArrayList.remove(dangTinDTOTimKiemArrayList.get(i));
                            i--;
                        }
                    }
                }
                if (dangTinDTOTimKiemArrayList.size() == 0) {
                    showDialogTinNhan("Không tìm được tin nào");
                    hideDialogTinNhan();
                    return;
                }
                DangTinAdapter dangTinAdapter = new DangTinAdapter(getActivity(), R.layout._match, dangTinDTOTimKiemArrayList);
                listViewTinTimDoi.setAdapter(dangTinAdapter);
                setListViewHeightBasedOnChildren(dangTinAdapter, listViewTinTimDoi);
            }
        });
    }


}