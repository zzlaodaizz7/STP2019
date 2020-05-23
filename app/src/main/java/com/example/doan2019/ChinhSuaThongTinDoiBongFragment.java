package com.example.doan2019;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChinhSuaThongTinDoiBongFragment extends Fragment {
    private View view;
    private ArrayList<String> levelArrayList;
    private ImageView imgDaiDien, imgBia;
    private Button btnThayAnhDaiDien, btnThayAnhBia, btnLuu, btnTrinhDo;
    private TextView txtQuayLai;
    private EditText edtTen, edtDiaChi, edtSoDienThoai;
    private int idDoiBong;
    private Bundle bundle;
    private DoiBong doibong;
    private ListView listViewTrinhDo;
    private Dialog dialogChonTrinhDo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sua_thong_tin_doi_bong, container, false);

        Mapping();

        GanNoiDungDoiBong();

        ClickQuayLai();

        ClickTrinhDo();

        ClickThayAnhBia();

        return view;
    }

    private void ClickThayAnhBia() {
        btnThayAnhBia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void ClickTrinhDo() {
        btnTrinhDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogChonTrinhDo();
                ClickDialogChonTrinhDo();
            }
        });
    }

    private void ShowDialogChonTrinhDo() {
        dialogChonTrinhDo = new Dialog(getContext());
        dialogChonTrinhDo.setContentView(R.layout.dialog_chon_trinh_do);
        dialogChonTrinhDo.show();

        listViewTrinhDo = dialogChonTrinhDo.findViewById(R.id.listViewTrinhDo);
        levelArrayList = new ArrayList<>();

        levelArrayList.add("Trung bình");
        levelArrayList.add("Khá");

        ArrayAdapter levelAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, levelArrayList);
        listViewTrinhDo.setAdapter(levelAdapter);
    }

    void ClickDialogChonTrinhDo() {
        listViewTrinhDo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnTrinhDo.setText(levelArrayList.get(position));
                dialogChonTrinhDo.cancel();
            }
        });
    }

    private void ClickQuayLai() {
        txtQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void GanNoiDungDoiBong() {
        try{
            bundle = getArguments();
            doibong = (DoiBong) bundle.getSerializable("doibong");
            idDoiBong = doibong.getId();

            if (doibong.getAnhdaidien() != null) {
                Picasso.get().load(APIUtils.BASE_URL+doibong.getAnhdaidien()).into(imgDaiDien);
            }
            if (doibong.getAnhbia() != null) {
                Picasso.get().load(APIUtils.BASE_URL+doibong.getAnhbia()).into(imgBia);
            }
            edtTen.setText(doibong.getTen());
            edtDiaChi.setText(doibong.getDiachi());
            edtSoDienThoai.setText(doibong.getSdt());
            btnTrinhDo.setText(doibong.getTrinhdo());
        }catch (Exception ex){
            Log.e("BBB", ex.toString());
        }
    }

    private void Mapping() {
        imgBia = view.findViewById(R.id.ImageViewBiaDoiBong);
        imgDaiDien = view.findViewById(R.id.ImageViewDaiDienDoiBong);
        edtTen = view.findViewById(R.id.EditTextTen);
        edtDiaChi = view.findViewById(R.id.EditTextDiaChi);
        edtSoDienThoai = view.findViewById(R.id.EditTextSoDienThoai);
        btnLuu = view.findViewById(R.id.ButtonSuaXongThongTinDoiBong);
        btnThayAnhDaiDien = view.findViewById(R.id.ButtonThayAnhDaiDien);
        btnThayAnhBia = view.findViewById(R.id.ButtonThayAnhBia);
        btnTrinhDo = view.findViewById(R.id.ButtonTrinhDoDoiBong);
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
    }
}
