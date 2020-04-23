package com.example.doan2019;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DanhSachCacTinDaDangFragment extends Fragment {
    private View view;
    ListView lvDanhSachCacTinDaDang;
    ArrayList<DangTinDuongClass> arrDangTin;
    DanhSachCacTinDaDangAdapter adapter;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    Bundle bundle;
    TextView txtQuayLai;
    DoiBong doiBong;
    Retrofit retrofit;
    JsonApiSanBong jsonApiSanBong;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cac_tin_da_dang, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
//        Gson gson = new GsonBuilder()
//                .setDateFormat("dd-MM-yyyy")
//                .create();
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.4/DoAn/public/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        Mapping();

        ClickQuayLai();

        GetDuLieuDoiBong();

        KhoiTaoDuLieu();

        ClickListView();

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

    private void ClickListView() {
        lvDanhSachCacTinDaDang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DanhSachCacDoiBatDoiFragment danhSachCacDoiBatDoiFragment = new DanhSachCacDoiBatDoiFragment();

                Bundle bundleTinDang = new Bundle();
                DangTinDuongClass tinDang = arrDangTin.get(i);
                bundleTinDang.putSerializable("tindang", tinDang);
                danhSachCacDoiBatDoiFragment.setArguments(bundleTinDang);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(danhSachCacDoiBatDoiFragment);
            }
        });
    }

    private void GetDuLieuDoiBong() {
        bundle = getArguments();
        doiBong = (DoiBong) bundle.getSerializable("doibong");
        Toast.makeText(getActivity(), doiBong.getTen() + "", Toast.LENGTH_SHORT).show();
    }

    private void KhoiTaoDuLieu() {
        Log.e("AAA", "Khoi tao du lieu");
        arrDangTin = new ArrayList<DangTinDuongClass>();

        Call<List<DangTinDuongClass>> call = jsonApiSanBong.getDanhsachdangtins(doiBong.getId());
        call.enqueue(new Callback<List<DangTinDuongClass>>() {
            @Override
            public void onResponse(Call<List<DangTinDuongClass>> call, Response<List<DangTinDuongClass>> response) {

                List<DangTinDuongClass> dangTinDuongClass = response.body();
                System.out.println("code: "+response.code());
                for (DangTinDuongClass dangTinDuongClass1 : dangTinDuongClass){
                    System.out.println("ngay: "+dangTinDuongClass1.getNgay()+dangTinDuongClass1.getCreated_at());
                    arrDangTin.add(new DangTinDuongClass(dangTinDuongClass1.getId(),
                            dangTinDuongClass1.getNgay(),dangTinDuongClass1.getSanbong_id(),
                            dangTinDuongClass1.getKhunggio_id(), dangTinDuongClass1.getKeo(),
                            dangTinDuongClass1.getCreated_at(), dangTinDuongClass1.getUpdated_at(),
                            dangTinDuongClass1.getDoibatdoi_id()));
                }
                adapter = new DanhSachCacTinDaDangAdapter(getActivity(), R.layout.dong_tin_da_dang, arrDangTin);
                lvDanhSachCacTinDaDang.setAdapter(adapter);
                SetListViewHeightBasedOnChildren(adapter, lvDanhSachCacTinDaDang);
            }

            @Override
            public void onFailure(Call<List<DangTinDuongClass>> call, Throwable t) {
                System.out.println("aaa"+t.getMessage());
            }
        });
//        long date = 123456789;
//        Date convertDate = new Date(date);
//        Timestamp convertTimestamp = new Timestamp(date);
//
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));
//        date++;
//        convertDate = new Date(date);
//        convertTimestamp = new Timestamp(date);
//        arrDangTin.add(new DangTinDuongClass(1, convertDate, 2, 1, "1/2 nước", convertTimestamp, convertTimestamp, -1));


    }

    private void SetListViewHeightBasedOnChildren(DanhSachCacTinDaDangAdapter matchAdapter, ListView listView) {
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
        lvDanhSachCacTinDaDang = view.findViewById(R.id.ListViewDanhSachCacTinDaDang);
    }
}
