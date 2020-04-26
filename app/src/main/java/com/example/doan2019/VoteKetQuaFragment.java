package com.example.doan2019;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.KetQua;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteKetQuaFragment extends Fragment {
    private View view;
    TextView txtBack, txtNameTeamMinh, txtNameTeamBan;
    EditText edtBanThangDoiMinh, edtBanThangDoiBan;
    ImageView imgTeamMinh, imgTeamBan;
    Bundle bundle;
    Button btnXong;
    JsonApiSanBong jsonApiSanBong;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vote_ket_qua_tran_dau, container, false);
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
                bundle = getArguments();
                TranDauDuongClass tranDau = (TranDauDuongClass) bundle.getSerializable("trandauduong");
                sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                String Auth = sharedPreferences.getString("token","");
                Map<String,String> header = new HashMap<>();
                    header.put("value","application/json");
                    header.put("Accept","application/json");
                    header.put("Authorization","Bearer "+Auth);
                    KetQua ketQua = new KetQua(tranDau.getIdTranDau(),Integer.parseInt(edtBanThangDoiMinh.getText().toString())
                            ,Integer.parseInt(edtBanThangDoiBan.getText().toString()));
                Call<KetQua> call = jsonApiSanBong.postVoted(header,ketQua);
                call.enqueue(new Callback<KetQua>() {
                    @Override
                    public void onResponse(Call<KetQua> call, Response<KetQua> response) {
                        System.out.println(response.code());
                        Toast.makeText(getActivity(), "Vote KQ xong", Toast.LENGTH_SHORT).show();
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onFailure(Call<KetQua> call, Throwable t) {
                        System.out.println("loi: "+t.getMessage());
                    }
                });
                System.out.println(""+edtBanThangDoiBan.getText());
                System.out.println(""+edtBanThangDoiMinh.getText());

            }
        });
    }

    private void GetDuLieu() {
        bundle = getArguments();

        TranDauDuongClass tranDau = (TranDauDuongClass) bundle.getSerializable("trandauduong");
        DoiBongClass doiMinh = tranDau.getDoiMinh();
        DoiBongClass doiBan = tranDau.getDoiBongDoiThu();
        imgTeamMinh.setImageBitmap(doiMinh.getImageDaiDien());
        imgTeamBan.setImageBitmap(doiBan.getImageDaiDien());
        txtNameTeamMinh.setText(doiMinh.getTen());
        txtNameTeamBan.setText(doiBan.getTen());
    }

    private void ClickBack() {
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void Mapping() {
        txtBack = view.findViewById(R.id.TextViewBack);
        txtNameTeamMinh = view.findViewById(R.id.txtTeamMinh);
        txtNameTeamBan = view.findViewById(R.id.txtTeamBan);
        edtBanThangDoiMinh = view.findViewById(R.id.EditTextKetQuaTeamMinh);
        edtBanThangDoiBan = view.findViewById(R.id.EditTextKetQuaTeamBan);
        imgTeamMinh = view.findViewById(R.id.ImageviewAnhDaiDienDoiMinh);
        imgTeamBan = view.findViewById(R.id.ImageviewAnhDaiDienDoiDoiThu);
        btnXong = view.findViewById(R.id.ButtonXong);
    }
}
