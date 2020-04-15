package com.example.doan2019;

import android.app.Dialog;
import android.os.Bundle;
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

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TaoDoiBongDialog extends DialogFragment {
    private View view;
    TextView txtXong;
    ImageView imvClose;
    EditText edtTen, edtDiaChi, edtDienThoai;
    Button btnTrinhDo;
    Dialog dialogChonTrinhDo;
    ListView listViewTrinhDo;
    ArrayList<String> levelArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_tao_doi_bong, container, false);

        Mapping();

        ClickButtonTrinhDo();

        ClickXong();

        ClickClosePopup();

        return view;
    }

    private void ClickClosePopup() {
        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaoDoiBongDialog.this.getDialog().cancel();
            }
        });
    }

    private void ClickXong() {
        txtXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaoDoiBongDialog.this.getDialog().cancel();
            }
        });
    }

    private void ClickButtonTrinhDo() {
        btnTrinhDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogChonTrinhDo();
                ClickDialogChonTrinhDo();
            }
        });
    }

    private void Mapping() {
        txtXong = view.findViewById(R.id.TextViewXongTaoDoiBong);
        imvClose = view.findViewById(R.id.ImageViewCLosePopup);
        edtTen = view.findViewById(R.id.EditTextTenDoiBong);
        edtDiaChi = view.findViewById(R.id.EditTextDiaChiDoiBong);
        edtDienThoai = view.findViewById(R.id.EditTextDienThoaiDoiBong);
        btnTrinhDo = view.findViewById(R.id.ButtonTrinhDoDoiBong);
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
}
