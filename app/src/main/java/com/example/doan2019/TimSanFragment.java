package com.example.doan2019;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.SanBong;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TimSanFragment extends Fragment {
    private View view;
    private ListView lvSanBong, lvSoNguoi;
    Button btnTimKiem, btnSoNguoi;
    ArrayList<SanBongClass> arrSanBong;
    TimSanAdapter adapter;
    Dialog dialogSoNguoi;
    ArrayList<Integer> arrSoNguoi;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    Retrofit retrofit;
    JsonApiSanBong jsonApiSanBong;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_san, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        Mapping();

        KhoiTaoListViewSanBong();

        ClickListViewSanBong();

        ClickSoNguoi();
        
        ClickTimKiem();

        return view;
    }

    private void ClickTimKiem() {
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Click Button Tìm Kiếm", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ClickSoNguoi() {
        btnSoNguoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogChonSoNguoi();
                ClickDialogChonSoNguoi();
            }
        });
    }

    private void ClickDialogChonSoNguoi() {
        lvSoNguoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                btnSoNguoi.setText(arrSoNguoi.get(i).toString() + " người");
                dialogSoNguoi.cancel();
            }
        });
    }

    private void ShowDialogChonSoNguoi() {
        dialogSoNguoi = new Dialog(getContext());
        dialogSoNguoi.setContentView(R.layout.dialog_so_nguoi_trong_san_bong);
        dialogSoNguoi.show();

        lvSoNguoi = dialogSoNguoi.findViewById(R.id.ListViewSoNguoiTrongSanBong);
        arrSoNguoi = new ArrayList<>();

        arrSoNguoi.add(7);
        arrSoNguoi.add(9);
        arrSoNguoi.add(11);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrSoNguoi);
        lvSoNguoi.setAdapter(adapter);
    }

    private void ClickListViewSanBong() {
        lvSanBong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietSanBongFragment chiTietSanBongFragment = new ChiTietSanBongFragment();

                Bundle bundle = new Bundle();
                SanBongClass sanBong = arrSanBong.get(i);
                bundle.putSerializable("sanbong", sanBong);
                chiTietSanBongFragment.setArguments(bundle);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietSanBongFragment);
            }
        });
    }

    private void KhoiTaoListViewSanBong() {
        arrSanBong = new ArrayList<>();
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Call<List<SanBong>> call = jsonApiSanBong.getSanbongs();
        call.enqueue(new Callback<List<SanBong>>() {
            @Override
            public void onResponse(Call<List<SanBong>> call, Response<List<SanBong>> response) {
                List<SanBong> sanBong = response.body();
                for (SanBong sanBongs : sanBong){
                    arrSanBong.add(new SanBongClass(sanBongs.getId(), sanBongs.getTen(), sanBongs.getSonguoi(), sanBongs.getDiachi(), sanBongs.getMota(), sanBongs.getSdt(), largeIcon));
                }
                adapter = new TimSanAdapter(getActivity(), R.layout.dong_san_bong, arrSanBong);
                lvSanBong.setAdapter(adapter);
                SetListViewHeightBasedOnChildren(adapter, lvSanBong);
            }

            @Override
            public void onFailure(Call<List<SanBong>> call, Throwable t) {

            }
        });

//        arrSanBong.add(new SanBongClass(1, "Sân Cầu Giấy", 11, "Số 123, Đường 321, Cầu Giấy, Hà Nội, Việt Nam", "", "0123456789", largeIcon));
//        arrSanBong.add(new SanBongClass(1, "Sân Mễ Trì", 11, "Số 123, Đường 321, Mễ Trì, Hà Nội, Việt Nam", "", "0123456789", largeIcon));
//        arrSanBong.add(new SanBongClass(1, "Sân Thành Phát", 11, "Số 123, Đường 321, Cầu Giấy, Hà Nội, Việt Nam", "", "0123456789", largeIcon));
//        arrSanBong.add(new SanBongClass(1, "Sân Hoà Phát", 11, "Số 123, Đường 321, Cầu Giấy, Hà Nội, Việt Nam", "", "0123456789", largeIcon));
//        arrSanBong.add(new SanBongClass(1, "Sân Kim Long", 11, "Số 123, Đường 321, Cầu Giấy, Hà Nội, Việt Nam", "", "0123456789", largeIcon));
//        arrSanBong.add(new SanBongClass(1, "Sân VINA", 11, "Số 123, Đường 321, Cầu Giấy, Hà Nội, Việt Nam", "", "0123456789", largeIcon));
//        arrSanBong.add(new SanBongClass(1, "Sân Láng Hạ", 11, "Số 123, Đường 321, Cầu Giấy, Hà Nội, Việt Nam", "", "0123456789", largeIcon));
//
//        adapter = new TimSanAdapter(getActivity(), R.layout.dong_san_bong, arrSanBong);
//        lvSanBong.setAdapter(adapter);
//        SetListViewHeightBasedOnChildren(adapter, lvSanBong);
    }

    private void SetListViewHeightBasedOnChildren(TimSanAdapter matchAdapter, ListView listView) {

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
        btnTimKiem = view.findViewById(R.id.ButtonTimKiemSanBong);
        btnSoNguoi = view.findViewById(R.id.ButtonSoNguoi);
        lvSanBong = view.findViewById(R.id.ListViewSanBong);
    }
}
