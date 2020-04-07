package com.example.doan2019;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DangTinFragment extends Fragment {
    EditText edtKeoDau;
    ListView lvDoiBong, lvSanBong;
    TextView tvChonFC, tvChonNgay, tvChonSan, tvChonGio, tvBack;
    Button tvTime1, tvTime2, tvTime3, btnDangTin;
    Dialog dialogChonDoiBong, dialogChonSan, dialogChonGio;
    ArrayList<String> arrayDoiBong, arraySanBong;
    ConstraintLayout layoutChonNgay, layoutChonSan;
    Switch btnCoSan;
    private View view;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dang_tin, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        Mapping();

        ClickChonFC();
        BatSuKienClickChonNgay();
        ClickChonSan();
        ClickCoHoacKhongSan();
        ClickChonGio();
        ClickTextViewBack();
        ClickDangTin();

        return view;
    }

    private void ClickDangTin() {
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Đăng tin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ClickTextViewBack() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(new TimDoiFragment());
            }
        });
    }

    private void ClickChonGio() {
        tvChonGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogChonGio();
                BatSuKienClickDialogChonGio();
            }
        });
    }

    private void BatSuKienClickDialogChonGio() {
        tvTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvChonGio.setText(tvTime1.getText());
                dialogChonGio.cancel();
            }
        });
        tvTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvChonGio.setText(tvTime2.getText());
                dialogChonGio.cancel();
            }
        });
        tvTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvChonGio.setText(tvTime3.getText());
                dialogChonGio.cancel();
            }
        });
    }

    private void ShowDialogChonGio() {
        dialogChonGio = new Dialog(getActivity());
        dialogChonGio.setContentView(R.layout.dialog_chon_gio);
        dialogChonGio.show();
        tvTime1 = dialogChonGio.findViewById(R.id.TextViewTime1);
        tvTime2 = dialogChonGio.findViewById(R.id.TextViewTime2);
        tvTime3 = dialogChonGio.findViewById(R.id.TextViewTime3);
    }

    private void ClickCoHoacKhongSan() {
        btnCoSan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    layoutChonSan.setVisibility(View.VISIBLE);
                else
                    layoutChonSan.setVisibility(View.GONE);
            }
        });
    }

    private void BatSuKienClickChonNgay() {
        final Calendar calendar = Calendar.getInstance();
        final int ngay = calendar.get(Calendar.DATE);
        final int thang = calendar.get(Calendar.MONTH);
        final int nam = calendar.get(Calendar.YEAR);
        layoutChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        tvChonNgay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
    }

    void Mapping() {
        btnDangTin = view.findViewById(R.id.ButtonDangTin);
        tvChonFC = view.findViewById(R.id.TextViewChonFC);
        layoutChonNgay = view.findViewById(R.id.ConstrainLayoutChonNgay);
        tvChonNgay = view.findViewById(R.id.BenPhaiTextViewChonNgay);
        tvChonSan = view.findViewById(R.id.TextViewChonSan);
        btnCoSan = view.findViewById(R.id.ButtonCoSan);
        layoutChonSan = view.findViewById(R.id.ConstrainLayoutChonSanBong);
        tvChonGio = view.findViewById(R.id.TextViewChonKhungGio);
        edtKeoDau = view.findViewById(R.id.EditTextNhapKeo);
        tvBack = view.findViewById(R.id.TextViewBack);
    }

    void ClickChonFC() {
        tvChonFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogChonDoiBong();
                BaSuKienClickListViewDoiBong();
            }
        });
    }

    private void ClickChonSan() {
        tvChonSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogChonSan();
                BatSuKienClickListViewSanBong();
            }
        });
    }

    void BatSuKienClickListViewSanBong() {
        lvSanBong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvChonSan.setText(arraySanBong.get(position));
                dialogChonSan.cancel();
            }
        });
    }

    private void ShowDialogChonSan() {
        dialogChonSan = new Dialog(getActivity());
        dialogChonSan.setContentView(R.layout.dialog_chon_san);
        dialogChonSan.show();

        lvSanBong = dialogChonSan.findViewById(R.id.ListViewSanBong);
        arraySanBong = new ArrayList<>();

        arraySanBong.add("Sân bóng 1");
        arraySanBong.add("Sân bóng 2");
        arraySanBong.add("Sân bóng 3");
        arraySanBong.add("Sân bóng 4");
        arraySanBong.add("Sân bóng 5");
        arraySanBong.add("Sân bóng 6");
        arraySanBong.add("Sân bóng 1");
        arraySanBong.add("Sân bóng 2");
        arraySanBong.add("Sân bóng 3");
        arraySanBong.add("Sân bóng 4");
        arraySanBong.add("Sân bóng 5");
        arraySanBong.add("Sân bóng 6");
        arraySanBong.add("Sân bóng 1");
        arraySanBong.add("Sân bóng 2");
        arraySanBong.add("Sân bóng 3");
        arraySanBong.add("Sân bóng 4");
        arraySanBong.add("Sân bóng 5");
        arraySanBong.add("Sân bóng 6");
        arraySanBong.add("Sân bóng 1");
        arraySanBong.add("Sân bóng 2");
        arraySanBong.add("Sân bóng 3");
        arraySanBong.add("Sân bóng 4");
        arraySanBong.add("Sân bóng 5");
        arraySanBong.add("Sân bóng 6");
        arraySanBong.add("Sân bóng 1");
        arraySanBong.add("Sân bóng 2");
        arraySanBong.add("Sân bóng 3");
        arraySanBong.add("Sân bóng 4");
        arraySanBong.add("Sân bóng 5");
        arraySanBong.add("Sân bóng 6");
        arraySanBong.add("Sân bóng 1");
        arraySanBong.add("Sân bóng 2");
        arraySanBong.add("Sân bóng 3");
        arraySanBong.add("Sân bóng 4");
        arraySanBong.add("Sân bóng 5");
        arraySanBong.add("Sân bóng 6");

        ArrayAdapter adapterSanBong = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arraySanBong);

        lvSanBong.setAdapter(adapterSanBong);
    }

    void BaSuKienClickListViewDoiBong() {
        lvDoiBong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvChonFC.setText(arrayDoiBong.get(position));
                dialogChonDoiBong.cancel();
            }
        });
    }

    void ShowDialogChonDoiBong() {
        dialogChonDoiBong = new Dialog(getActivity());
        dialogChonDoiBong.setContentView(R.layout.dialog_chon_doi);
        dialogChonDoiBong.show();

        lvDoiBong = dialogChonDoiBong.findViewById(R.id.ListViewDoiBong);
        arrayDoiBong = new ArrayList<>();

        arrayDoiBong.add("Đội bóng 1");
        arrayDoiBong.add("Đội bóng 2");
        arrayDoiBong.add("Đội bóng 3");
        arrayDoiBong.add("Đội bóng 4");
        arrayDoiBong.add("Đội bóng 5");
        arrayDoiBong.add("Đội bóng 6");
        arrayDoiBong.add("Đội bóng 1");
        arrayDoiBong.add("Đội bóng 2");
        arrayDoiBong.add("Đội bóng 3");
        arrayDoiBong.add("Đội bóng 4");
        arrayDoiBong.add("Đội bóng 5");
        arrayDoiBong.add("Đội bóng 6");
        arrayDoiBong.add("Đội bóng 1");
        arrayDoiBong.add("Đội bóng 2");
        arrayDoiBong.add("Đội bóng 3");
        arrayDoiBong.add("Đội bóng 4");
        arrayDoiBong.add("Đội bóng 5");
        arrayDoiBong.add("Đội bóng 6");
        arrayDoiBong.add("Đội bóng 1");
        arrayDoiBong.add("Đội bóng 2");
        arrayDoiBong.add("Đội bóng 3");
        arrayDoiBong.add("Đội bóng 4");
        arrayDoiBong.add("Đội bóng 5");
        arrayDoiBong.add("Đội bóng 6");
        arrayDoiBong.add("Đội bóng 1");
        arrayDoiBong.add("Đội bóng 2");
        arrayDoiBong.add("Đội bóng 3");
        arrayDoiBong.add("Đội bóng 4");
        arrayDoiBong.add("Đội bóng 5");
        arrayDoiBong.add("Đội bóng 6");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayDoiBong);

        lvDoiBong.setAdapter(adapter);
    }
}