package com.example.doan2019;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.DoiBongClass;
import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.JsonApiSanBong;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DanhSachCacDoiBatDoiFragment extends Fragment {
    private View view;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    ListView lvDoiBongBatDoi;
    TextView txtQuayLai;
    ArrayList<DoiBongClass> listDoiBong;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;
    DanhSachCacDoiBatDoiAdapter adapter;
    Bundle bundle;
    private int IDTinDang;
    Retrofit retrofit;
    JsonApiSanBong jsonApiSanBong;
    SharedPreferences sharedPreferences;
    String Auth = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_cac_doi_bat_doi, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.4/DoAn/public/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        Auth = sharedPreferences.getString("token","");
        Mapping();

        ClickQuayLai();

        ClickListview();

        GetDuLieuIDDangTin();

        KhoiTaoListView();

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

    private void GetDuLieuIDDangTin() {
        bundle = getArguments();
        DangTinDuongClass dangTin = (DangTinDuongClass) bundle.getSerializable("tindang");
        Toast.makeText(getActivity(), "ID tin đăng: " + dangTin.getId(), Toast.LENGTH_SHORT).show();
    }

    private void ClickListview() {
        lvDoiBongBatDoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), i + "", Toast.LENGTH_SHORT).show();

                ChiTietDoiBongBatDoiFragment chiTietDoiBongBatDoiFragment = new ChiTietDoiBongBatDoiFragment();
                Bundle bundleChiTietDoiBong = new Bundle();
                DoiBongClass doiBongClass = listDoiBong.get(i);
                bundleChiTietDoiBong.putSerializable("doibong", doiBongClass);
                chiTietDoiBongBatDoiFragment.setArguments(bundleChiTietDoiBong);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietDoiBongBatDoiFragment);
            }
        });
    }

    private void KhoiTaoListView() {
        listDoiBong = new ArrayList<>();
        listThanhVienDoiBong = new ArrayList<>();
        bundle = getArguments();
        DangTinDuongClass dangTin = (DangTinDuongClass) bundle.getSerializable("tindang");
        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);
        Map<String,String> header = new HashMap<>();
        header.put("value","application/json");
        header.put("Accept","application/json");
        header.put("Authorization","Bearer "+Auth);
//        System.out.println("GAN LIST VIEW: "+dangTin.getId());
        Call<List<DoiBongClass>> call = jsonApiSanBong.getCacdoibatdoi(header,dangTin.getId());
        call.enqueue(new Callback<List<DoiBongClass>>() {
            @Override
            public void onResponse(Call<List<DoiBongClass>> call, Response<List<DoiBongClass>> response) {
                try {
//                    System.out.println("Code: " + response.body().size());
                    List<DoiBongClass> doiBongClasses = response.body();
                    for (DoiBongClass doiBongClass : doiBongClasses) {

                        listDoiBong.add(new DoiBongClass(doiBongClass.getId(), doiBongClass.getBatdoi_id(), doiBongClass.getTen(), doiBongClass.getDiem(), doiBongClass.getDiaChi(), doiBongClass.getTrinhDo(), doiBongClass.getSoDienThoai(), doiBongClass.getCreated_at(), anhBia, anhDaiDien, listThanhVienDoiBong));

                    }
                    long ngayTemp = 1234596789;
                    Date dateConvert = new Date(ngayTemp);

                    listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", "dateConvert", "0123456789"));
                    listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", "dateConvert", "0123456789"));
                    listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", "dateConvert", "0123456789"));

                    adapter = new DanhSachCacDoiBatDoiAdapter(getActivity(), R.layout.dong_doi_bat_doi, listDoiBong);
                    lvDoiBongBatDoi.setAdapter(adapter);
                    SetListViewHeightBasedOnChildren(adapter, lvDoiBongBatDoi);
                }
                catch (Exception ex){
                    Log.e("BBB", ex.toString());
                }
            }

            @Override
            public void onFailure(Call<List<DoiBongClass>> call, Throwable t) {
//                System.out.println("Loi: "+t.getMessage());
            }
        });



//        listDoiBong.add(new DoiBongClass("FC Linh Đàm", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", anhBia, anhDaiDien, listThanhVienDoiBong));
//        listDoiBong.add(new DoiBongClass("FC Cầu Giấy", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", anhBia, anhDaiDien, listThanhVienDoiBong));
//        listDoiBong.add(new DoiBongClass("FC Mễ Trì", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", anhBia, anhDaiDien, listThanhVienDoiBong));
//        listDoiBong.add(new DoiBongClass("FC Lê Đức Thọ", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", anhBia, anhDaiDien, listThanhVienDoiBong));


    }

    private void SetListViewHeightBasedOnChildren(DanhSachCacDoiBatDoiAdapter matchAdapter, ListView listView) {
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
        }
        catch (Exception ex){
            Log.e("BBB", ex.toString());
        }
    }

    private void Mapping() {
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
        lvDoiBongBatDoi = view.findViewById(R.id.ListViewCacDoiBatDoi);
    }
}
