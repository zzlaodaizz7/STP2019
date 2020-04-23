package com.example.doan2019;

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

public class ChiTietLichSuTranDauFragment extends Fragment {
    private View view;
    ImageView imgTeamMinh, imgTeamBan;
    TextView txtNameTeamMinh, txtNameTeamBan, txtBack, txtVoteKetQua,
            txtThoiGian, txtSan, txtKeo, txtSoBanThangTeamMinh, txtSoBanThangTeamBan;
    Button btnXong;
    TranDauDuongClass tranDau;
    Boolean checkVoted;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    Bundle bundle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_lich_su_tran_dau, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        Mapping();

        GetDuLieu();

        ClickVoteKetQua();

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

    private void GetDuLieu() {
        bundle = getArguments();

        tranDau = (TranDauDuongClass) bundle.getSerializable("trandauduong");
        DoiBongClass doiMinh = tranDau.getDoiMinh();
        DoiBongClass doiBan = tranDau.getDoiBongDoiThu();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String time = dateFormat.format(tranDau.getNgay());
        if(tranDau.getKhungGio() == 1)
            time += "  17:30 - 19:00";
        else if(tranDau.getKhungGio() == 2)
            time += "  19:00 - 20:30";
        else if(tranDau.getKhungGio() == 3)
            time += "  20:30 - 22:00";

        imgTeamMinh.setImageBitmap(doiMinh.getImageDaiDien());
        imgTeamBan.setImageBitmap(doiBan.getImageDaiDien());
        txtNameTeamMinh.setText(doiMinh.getTen());
        txtNameTeamBan.setText(doiBan.getTen());
        txtThoiGian.setText(time);
        txtSan.setText("ID Sân: " + tranDau.getIdSan());
        txtKeo.setText(tranDau.getKeo());
        txtSoBanThangTeamMinh.setText(tranDau.getSoBanThangBenMinh() + "");
        txtSoBanThangTeamBan.setText(tranDau.getSoBanThangDoiThu() + "");
        checkVoted = tranDau.getVoted();

        Boolean checkXepHangFragment = (Boolean) bundle.getSerializable("checkXepHangFragment");

        if(checkXepHangFragment != null || checkVoted == true)
            txtVoteKetQua.setVisibility(View.INVISIBLE);
    }

    private void ClickBack() {
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void ClickVoteKetQua() {
        txtVoteKetQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VoteKetQuaFragment voteKetQuaFragment = new VoteKetQuaFragment();

                voteKetQuaFragment.setArguments(bundle);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(voteKetQuaFragment);
            }
        });
    }

    private void Mapping() {
        txtSoBanThangTeamMinh = view.findViewById(R.id.txtBanThangDoiMinh);
        txtSoBanThangTeamBan = view.findViewById(R.id.txtBanThangDoiBan);
        txtBack = view.findViewById(R.id.TextViewBack);
        txtVoteKetQua = view.findViewById(R.id.TextViewVoteKetQua);
        imgTeamMinh = view.findViewById(R.id.ImageviewAnhDaiDienDoiMinh);
        imgTeamBan = view.findViewById(R.id.ImageviewAnhDaiDienDoiDoiThu);
        txtNameTeamMinh = view.findViewById(R.id.txtTeamMinh);
        txtNameTeamBan = view.findViewById(R.id.txtTeamBan);
        txtThoiGian = view.findViewById(R.id.txtTime);
        txtSan = view.findViewById(R.id.txtPitch);
        txtKeo = view.findViewById(R.id.txtKeo);
        btnXong = view.findViewById(R.id.ButtonXong);
    }
}
