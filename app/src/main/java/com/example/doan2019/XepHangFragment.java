package com.example.doan2019;

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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.JsonApiSanBong;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XepHangFragment extends Fragment {
    private View view;
    ListView lvxepHangDoiBong;
    ArrayList<DoiBong> listDoiBong;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;
    XepHangAdapter adapter;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    JsonApiSanBong jsonApiSanBong;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xep_hang_doi_bong, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        Mapping();

        KhoiTaoListView();

        ClickListview();

        return view;
    }

    private void ClickListview() {
        lvxepHangDoiBong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietDoiBongXepHangFragment chiTietDoiBongXepHangFragment = new ChiTietDoiBongXepHangFragment();

                Bundle bundle = new Bundle();
                DoiBong doiBongClass = listDoiBong.get(i);
                bundle.putSerializable("doibong", doiBongClass);
                chiTietDoiBongXepHangFragment.setArguments(bundle);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietDoiBongXepHangFragment);
            }
        });
    }

    private void KhoiTaoListView() {
        listDoiBong = new ArrayList<>();
        listThanhVienDoiBong = new ArrayList<>();

////        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
////        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);
////        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", "dateConvert", "0123456789"));
////        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", "dateConvert", "0123456789"));
////        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", "dateConvert", "0123456789"));
//        long ngayTemp = 1234596789;
//        Date dateConvert = new Date(ngayTemp);
        Call<List<DoiBong>> call = jsonApiSanBong.getBangxephang();
        call.enqueue(new Callback<List<DoiBong>>() {
            @Override
            public void onResponse(Call<List<DoiBong>> call, Response<List<DoiBong>> response) {
                List<DoiBong> doiBongs = response.body();
                try {
                    for (DoiBong doiBong : doiBongs) {
//                    int id, String ten, String trinhdo, String diachi, String sdt
//                    String anhbia, String anhdaidien, int sodiem, int hanhkiem
//                    Timestamp created_at, java.sql.Timestamp updated_at
                        listDoiBong.add(new DoiBong(doiBong.getId(), doiBong.getTen(), doiBong.getTrinhdo(), doiBong.getDiachi(), doiBong.getSdt(), doiBong.getAnhbia(), doiBong.getAnhdaidien()
                                , doiBong.getSodiem(), doiBong.getHanhkiem(), doiBong.getCreated_at(), doiBong.getUpdated_at()));
                    }
                }
                catch (Exception ex){
                    Log.e("BBB", ex.toString());
                }
                adapter = new XepHangAdapter(getActivity(), R.layout.dong_xep_hang, listDoiBong);
                lvxepHangDoiBong.setAdapter(adapter);
                setListViewHeightBasedOnChildren(adapter, lvxepHangDoiBong);
            }

            @Override
            public void onFailure(Call<List<DoiBong>> call, Throwable t) {

            }
        });
//
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn F", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn G", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn H", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn I", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));


    }

    private void setListViewHeightBasedOnChildren(XepHangAdapter matchAdapter, ListView listView) {
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
        lvxepHangDoiBong = view.findViewById(R.id.ListViewXepHangDoiBong);
    }

}
