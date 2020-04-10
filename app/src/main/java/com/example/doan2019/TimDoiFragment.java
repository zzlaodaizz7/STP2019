package com.example.doan2019;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TimDoiFragment extends Fragment {

    ScrollView scrollView;
    EditText editTextTimTheoTenDoiHoacTenSan;
    ListView listViewTinTimDoi, listViewTrangThai, listViewTrinhDo;
    ArrayList<Match> matchArrayList;
    MatchAdapter matchAdapter;
    Button btnDangTin;
    Button btnChonTrangThai, btnChonTrinhDo;
    Dialog dialogChonTrangThai, dialogChonTrinhDo;
    ArrayList<String> statusArrayList, levelArrayList;
    TextView txtChonNgay;
    ImageButton btnThongBao;
    View view;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_doi, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        mapping();

        clickChonNgay();

        clickChonTrangThai();

        clickChonTrinhDo();

        clickChonDangTin();

        clickChonThongBao();

        clickChonListViewTinTimDoi();

        return view;
    }

    private void mapping(){

        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        btnDangTin = (Button) view.findViewById(R.id.btnDangTin);
        btnChonTrangThai = (Button) view.findViewById(R.id.btnChonTrangThai);
        btnChonTrinhDo = (Button) view.findViewById(R.id.btnChonTrinhDo);
        listViewTinTimDoi = (ListView) view.findViewById(R.id.listViewTinTimDoi);
        txtChonNgay = (TextView) view.findViewById(R.id.txtChonNgay);
        editTextTimTheoTenDoiHoacTenSan = (EditText) view.findViewById(R.id.editTextTimTheoTenDoiHoacTenSan);
        btnThongBao = (ImageButton) view.findViewById(R.id.btnThongBao);

        matchArrayList = new ArrayList<>();
        matchArrayList.add(new Match(1, "FC Red", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match(2, "FC White", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match(3, "FC 22", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match(4, "FC Apple", "Samsung", new Date(), "3 : 4",  "San Thanh Long", "Trung binh", "Da co doi thu"));
        matchArrayList.add(new Match(5, "FC Samsung", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match(6, "FC Xiaomi", "", new Date(), "3 : 4",  "San Chau Trinh Tri", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match(7, "FC Blue", "Green", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Da co doi thu"));

        matchAdapter = new MatchAdapter(getActivity(), R.layout._match, matchArrayList);
        listViewTinTimDoi.setAdapter(matchAdapter);
        setListViewHeightBasedOnChildren(matchAdapter, listViewTinTimDoi);
    }

    private void clickChonNgay() {
        final Calendar calendar = Calendar.getInstance();
        final int ngay = calendar.get(Calendar.DATE);
        final int thang = calendar.get(Calendar.MONTH);
        final int nam = calendar.get(Calendar.YEAR);
        txtChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        txtChonNgay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
    }

    private void setListViewHeightBasedOnChildren(MatchAdapter matchAdapter, ListView listView) {

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

    private void clickChonTrangThai() {
        btnChonTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogChonTrangThai();
                clickDialogChonTrangThai();
            }
        });
    }

    void clickDialogChonTrangThai() {
        listViewTrangThai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnChonTrangThai.setText(statusArrayList.get(position));
                dialogChonTrangThai.cancel();
            }
        });
    }

    private void showDialogChonTrangThai() {
        dialogChonTrangThai = new Dialog(getActivity());
        dialogChonTrangThai.setContentView(R.layout.dialog_chon_trang_thai);
        dialogChonTrangThai.show();

        listViewTrangThai = dialogChonTrangThai.findViewById(R.id.listViewTrangThai);
        statusArrayList = new ArrayList<>();

        statusArrayList.add("Chưa có đối thủ");
        statusArrayList.add("Đã có đối thủ");

        ArrayAdapter statusAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, statusArrayList);

        listViewTrangThai.setAdapter(statusAdapter);
    }

    private void clickChonTrinhDo() {
        btnChonTrinhDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogChonTrinhDo();
                clickDialogChonTrinhDo();
            }
        });
    }

    void clickDialogChonTrinhDo() {
        listViewTrinhDo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnChonTrinhDo.setText(levelArrayList.get(position));
                dialogChonTrinhDo.cancel();
            }
        });
    }

    private void showDialogChonTrinhDo() {
        dialogChonTrinhDo = new Dialog(getActivity());
        dialogChonTrinhDo.setContentView(R.layout.dialog_chon_trinh_do);
        dialogChonTrinhDo.show();

        listViewTrinhDo = dialogChonTrinhDo.findViewById(R.id.listViewTrinhDo);
        levelArrayList = new ArrayList<>();

        levelArrayList.add("Trung bình");
        levelArrayList.add("Khá");

        ArrayAdapter levelAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, levelArrayList);
        listViewTrinhDo.setAdapter(levelAdapter);
    }

    private void clickChonThongBao(){
        btnThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ThongBaoFragment()).commit();
            }
        });
    }

    private void clickChonDangTin(){
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(new DangTinFragment());
            }
        });
    }

    private void clickChonListViewTinTimDoi(){
        listViewTinTimDoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BatDoiFragment batDoiFragment = new BatDoiFragment();

                Bundle bundle = new Bundle();
                Match match = matchArrayList.get(position);
                bundle.putSerializable("batdoi", match);
                batDoiFragment.setArguments(bundle);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(batDoiFragment);
            }
        });
    }

}