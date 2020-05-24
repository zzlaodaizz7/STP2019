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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.SanBong;

import pl.droidsonroids.gif.GifTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TimSanFragment extends Fragment {
    private View view;
    private ListView lvSanBong, lvSoNguoi;
    Button btnTimKiem, btnSoNguoi;
    ArrayList<SanBongClass> arrSanBong,arrSanBong1;
    TimSanAdapter adapter;
    Dialog dialogSoNguoi;
    ArrayList<Integer> arrSoNguoi;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    Retrofit retrofit;
    JsonApiSanBong jsonApiSanBong;
    Dialog dialogChonTrangThai, dialogChonTrinhDo, dialogChonTimKiemTheoTenHaySan, dialogTinNhan;
    TextView txtChonNgay, tvTinNhan, tvHuy;
    EditText edtTimKiem;
    GifTextView gifLoading;
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
                arrSanBong1 = new ArrayList<>();
                for (int i = 0; i< arrSanBong.size(); i++){
                    if (!btnSoNguoi.getText().toString().equals("Số người")){
                        if (arrSanBong.get(i).getSoNguoi() == Integer.parseInt(btnSoNguoi.getText().toString())){
                            int[] images = {R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4};
                            Random rand = new Random();
                            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), images[rand.nextInt(images.length)]);
                            arrSanBong1.add(new SanBongClass(arrSanBong.get(i).getId(), arrSanBong.get(i).getTenSanBong(), arrSanBong.get(i).getSoNguoi(), arrSanBong.get(i).getDiaChi(), arrSanBong.get(i).getMoTa(), arrSanBong.get(i).getSoDienThoai(), largeIcon));
                        }
                    }else if (!edtTimKiem.getText().toString().equals("")) {
                        if (arrSanBong.get(i).getTenSanBong().toLowerCase().contains(edtTimKiem.getText().toString().toLowerCase())) {
                            int[] images = {R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4};
                            Random rand = new Random();
                            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), images[rand.nextInt(images.length)]);
                            arrSanBong1.add(new SanBongClass(arrSanBong.get(i).getId(), arrSanBong.get(i).getTenSanBong(), arrSanBong.get(i).getSoNguoi(), arrSanBong.get(i).getDiaChi(), arrSanBong.get(i).getMoTa(), arrSanBong.get(i).getSoDienThoai(), largeIcon));
                        }
                    }
                }
                if (arrSanBong1.size()==0){
                    showDialogTinNhan("Không có dữ liệu");
                    hideDialogTinNhan();
                }else{
                    adapter = new TimSanAdapter(getActivity(), R.layout.dong_san_bong, arrSanBong1);
                    lvSanBong.setAdapter(adapter);
                    SetListViewHeightBasedOnChildren(adapter, lvSanBong);
                }
            }
        });
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
                btnSoNguoi.setText(arrSoNguoi.get(i).toString());
                dialogSoNguoi.cancel();
            }
        });
    }
    private void showLoadingGif() {
        gifLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoadinggif() {
        gifLoading.setVisibility(View.INVISIBLE);
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
        showLoadingGif();
        arrSanBong = new ArrayList<>();
//         = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        int[] images = {R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4};

        Call<List<SanBong>> call = jsonApiSanBong.getSanbongs();
        call.enqueue(new Callback<List<SanBong>>() {
            @Override
            public void onResponse(Call<List<SanBong>> call, Response<List<SanBong>> response) {
                try {
                    List<SanBong> sanBong = response.body();
                    for (SanBong sanBongs : sanBong) {
                        Random rand = new Random();
                        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), images[rand.nextInt(images.length)]);
                        arrSanBong.add(new SanBongClass(sanBongs.getId(), sanBongs.getTen(), sanBongs.getSonguoi(), sanBongs.getDiachi(), sanBongs.getMota(), sanBongs.getSdt(), largeIcon));
                    }
                    adapter = new TimSanAdapter(getActivity(), R.layout.dong_san_bong, arrSanBong);
                    lvSanBong.setAdapter(adapter);
                    SetListViewHeightBasedOnChildren(adapter, lvSanBong);
                    hideLoadinggif();
                }
                catch (Exception ex){
                    Log.d("BBB", ex.toString());
                }
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
        gifLoading = (GifTextView) view.findViewById(R.id.gifloading);
        btnTimKiem = view.findViewById(R.id.ButtonTimKiemSanBong);
        edtTimKiem = view.findViewById(R.id.EditTextTimKiemSanBong);
        btnSoNguoi = view.findViewById(R.id.ButtonSoNguoi);
        lvSanBong = view.findViewById(R.id.ListViewSanBong);
    }
}
