package com.example.doan2019;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.SanBong;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietTranDauSapToiFragment extends Fragment {
    private View view;
    ImageView imgTeamMinh, imgTeamBan;
    TextView txtNameTeamMinh, txtNameTeamBan,
            txtThoiGian, txtSan, txtKeo, txtBack;
    Button btnXong;
    JsonApiSanBong jsonApiSanBong;
    Bundle bundle;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("ZZZ", "onCreateView");
        view = inflater.inflate(R.layout.fragment_chi_tiet_tran_dau_sap_toi, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        Mapping();
        GetDuLieu();

        ClickBack();

        ClickButtonXong();

        return view;
    }

    private void ClickButtonXong() {
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void ClickBack() {
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void GetDuLieu() {
        bundle = getArguments();
        TranDauDuongClass tranDau = (TranDauDuongClass) bundle.getSerializable("trandauduong");
        if(tranDau != null) {
            DoiBongClass doiMinh = tranDau.getDoiMinh();
            DoiBongClass doiBan = tranDau.getDoiBongDoiThu();
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String time = dateFormat.format(tranDau.getNgay());
            String time = tranDau.getNgay();
            if (tranDau.getKhungGio() == 1)
                time += "  17:30 - 19:00";
            else if (tranDau.getKhungGio() == 2)
                time += "  19:00 - 20:30";
            else if (tranDau.getKhungGio() == 3)
                time += "  20:30 - 22:00";

            imgTeamMinh.setImageBitmap(doiMinh.getImageDaiDien());
            imgTeamBan.setImageBitmap(doiBan.getImageDaiDien());
            txtNameTeamMinh.setText(doiMinh.getTen());
            txtNameTeamBan.setText(doiBan.getTen());
            txtThoiGian.setText(time);
            Call<SanBong> call = jsonApiSanBong.getChitietsanbong(tranDau.getIdSan());
            call.enqueue(new Callback<SanBong>() {
                @Override
                public void onResponse(Call<SanBong> call, Response<SanBong> response) {
                    SanBong sanBong = response.body();
                    txtSan.setText(sanBong.getTen());
                }

                @Override
                public void onFailure(Call<SanBong> call, Throwable t) {

                }
            });
            txtKeo.setText(tranDau.getKeo());
        }
        else{
            DangTinDuongClass tinDang = (DangTinDuongClass) bundle.getSerializable("haidoibong");
//            Log.e("DxTN", tinDang.getDoidangtin_id()+"");
//            Log.e("DxTN", tinDang.getDoibatdoi_id()+"");
            String time = tinDang.getNgay();
            if (tinDang.getKhunggio_id() == 1)
                time += "  17:30 - 19:00";
            else if (tinDang.getKhunggio_id() == 2)
                time += "  19:00 - 20:30";
            else if (tinDang.getKhunggio_id() == 3)
                time += "  20:30 - 22:00";
            Call<DoiBong> call1 = jsonApiSanBong.getChitietdoibong(tinDang.getDoidangtin_id());
            call1.enqueue(new Callback<DoiBong>() {
                @Override
                public void onResponse(Call<DoiBong> call, Response<DoiBong> response) {
                    if(response.body().getAnhdaidien() != null) {
                        Picasso.get().load(APIUtils.BASE_URL+response.body().getAnhbia()).into(imgTeamMinh);
                        txtNameTeamMinh.setText(response.body().getTen());
                    }

                }

                @Override
                public void onFailure(Call<DoiBong> call, Throwable t) {

                }
            });
            Call<DoiBong> call2 = jsonApiSanBong.getChitietdoibong(tinDang.getDoibatdoi_id());
            call2.enqueue(new Callback<DoiBong>() {
                @Override
                public void onResponse(Call<DoiBong> call, Response<DoiBong> response) {
                    if(response.body().getAnhdaidien() != null) {
                        Picasso.get().load(APIUtils.BASE_URL+response.body().getAnhbia()).into(imgTeamBan);
                    }
                    txtNameTeamBan.setText(response.body().getTen());
                }

                @Override
                public void onFailure(Call<DoiBong> call, Throwable t) {

                }
            });
//            if(doiBong.getAnhbia() != null){
//                Picasso.get().load(doiBong.getAnhbia()).into(imgAnhBia);
//            }
//            if(doiBong.getAnhdaidien() != null){
//                Picasso.get().load(doiBong.getAnhdaidien()).into(imgDaiDien);
//            }
            txtThoiGian.setText(time);
            Call<SanBong> call = jsonApiSanBong.getChitietsanbong(tinDang.getSanbong_id());
            call.enqueue(new Callback<SanBong>() {
                @Override
                public void onResponse(Call<SanBong> call, Response<SanBong> response) {
                    SanBong sanBong = response.body();
                    txtSan.setText(sanBong.getTen());
                }

                @Override
                public void onFailure(Call<SanBong> call, Throwable t) {

                }
            });
            txtKeo.setText(tinDang.getKeo());
        }
    }

    private void Mapping() {
        txtBack = view.findViewById(R.id.TextViewBack);
        imgTeamMinh = view.findViewById(R.id.ImageviewAnhDaiDienDoiMinh);
        imgTeamBan = view.findViewById(R.id.ImageviewAnhDaiDienDoiDoiThu);
        txtNameTeamMinh = view.findViewById(R.id.txtTeamMinh);
        txtNameTeamBan = view.findViewById(R.id.txtTeamBan);
        txtThoiGian = view.findViewById(R.id.txtTime);
        txtSan = view.findViewById(R.id.txtPitch);
        txtKeo = view.findViewById(R.id.txtKeo);
        btnXong = view.findViewById(R.id.ButtonXong);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("ZZZ", "onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("ZZZ", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("ZZZ", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("ZZZ", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        getFragmentManager().popBackStack();
        Log.e("ZZZ", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("ZZZ", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("ZZZ", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ZZZ", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("ZZZ", "onDetach");
    }
}
