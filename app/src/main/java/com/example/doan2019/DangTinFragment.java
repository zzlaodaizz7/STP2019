package com.example.doan2019;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DangTin;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.SanBong;

import androidx.fragment.app.FragmentManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DangTinFragment extends Fragment {
    EditText edtKeoDau;
    ListView lvDoiBong, lvSanBong;
    TextView tvChonFC, tvChonNgay, tvChonSan, tvChonGio, tvBack;
    Button tvTime1, tvTime2, tvTime3, btnDangTin;
    Dialog dialogChonDoiBong, dialogChonSan, dialogChonGio;
    ArrayList<String> arrayDoiBong, arraySanBong;
    ConstraintLayout layoutChonNgay, layoutChonSan;
    Switch btnCoSan;
    List<SanBong> sanBongs;
    List<DoiBong> doiBongs;
    int IDSanBong = -1;
    int IDDoiBong = -1;
    int IDKhungGio = -1;
    int IDUser = -1;
    int cosan = 0;
    String keo = "";
    JsonApiSanBong jsonApiSanBong;
    String d = "-1";
    String Auth = "";
    SharedPreferences sharedPreferences;
    private View view;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    Retrofit retrofit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_dang_tin, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
//        Toast.makeText(getContext(),sharedPreferences.getString("token",""), Toast.LENGTH_SHORT).show();
        IDUser = sharedPreferences.getInt("id",0);
        Auth = sharedPreferences.getString("token","");

        jsonApiSanBong = APIUtils.getJsonApiSanBong();

        LoadListSanBong();

        LoadListDoiBong();

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

    private void LoadListDoiBong() {
        arrayDoiBong = new ArrayList<>();
        Call<List<DoiBong>> call = jsonApiSanBong.getDoitruongcacdois(IDUser);
        call.enqueue(new Callback<List<DoiBong>>() {
            @Override
            public void onResponse(Call<List<DoiBong>> call, Response<List<DoiBong>> response) {
                try {
                    doiBongs = response.body();
//                    System.out.println("COde" + response.code());
                    for (DoiBong doiBong : doiBongs) {
//                        System.out.println(doiBong.getTen());
                        arrayDoiBong.add(doiBong.getTen());
                    }
                }
                catch (Exception ex){
                    Log.e("BBB", ex.toString());
                }
            }
            @Override
            public void onFailure(Call<List<DoiBong>> call, Throwable t) {
//                System.out.println("loi: "+t.getMessage());
            }
        });
    }

    private void LoadListSanBong() {
        arraySanBong = new ArrayList<>();
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        Call<List<SanBong>> call = jsonApiSanBong.getSanbongs();
        call.enqueue(new Callback<List<SanBong>>() {
            @Override
            public void onResponse(Call<List<SanBong>> call, retrofit2.Response<List<SanBong>> response) {
                sanBongs = response.body();
                for (SanBong sanBong : sanBongs){
                    arraySanBong.add(sanBong.getTen());
                }
            }
            @Override
            public void onFailure(Call<List<SanBong>> call, Throwable t) {
//                System.out.println("loi: "+t.getMessage());
            }
        });
    }

    private void ClickDangTin() {
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keo = edtKeoDau.getText().toString();
//                System.out.println(IDDoiBong);
//                System.out.println(d);
//                System.out.println(keo);
//                System.out.println(IDSanBong);
//                System.out.println(IDKhungGio);
                String content = "";
                if (IDDoiBong == -1){
                    content += "Bạn chưa chọn đội bóng \n";
                }
                if(d == "-1"){
                    content += "Bạn chưa chọn ngày \n";
                }
                if(keo == ""){
                    content += "Bạn chưa nhập kèo \n";
                }
                if (IDKhungGio == -1){
                    content += "Bạn chưa chọn khung giờ \n";
                }
                if (content ==""){
                    DangTin dangTin;
                    if (cosan == 1) { dangTin = new DangTin(IDDoiBong,IDSanBong,IDKhungGio,d,keo);}
                    else { dangTin = new DangTin(IDDoiBong,-1,IDKhungGio,d,keo);}
                    Map<String,String> header = new HashMap<>();
                    header.put("value","application/json");
                    header.put("Accept","application/json");
                    header.put("Authorization","Bearer "+Auth);
                    Call<DangTin> call = jsonApiSanBong.postDangTin(header,dangTin);
                    call.enqueue(new Callback<DangTin>() {
                        @Override
                        public void onResponse(Call<DangTin> call, Response<DangTin> response) {
                            try {
                                if (response.body().getType() == "success") {
                                    Toast.makeText(getContext(), response.body().getContent(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), response.body().getContent(), Toast.LENGTH_SHORT).show();
                                }
                                getFragmentManager().popBackStack();
                            }
                            catch (Exception ex){
                                Log.e("BBB", ex.toString());
                            }
                        }
                        @Override
                        public void onFailure(Call<DangTin> call, Throwable t) {

                        }
                    });
                }else{
                    Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void ClickTextViewBack() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
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
                IDKhungGio = 1;
                dialogChonGio.cancel();
            }
        });
        tvTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvChonGio.setText(tvTime2.getText());
                IDKhungGio = 2;
                dialogChonGio.cancel();
            }
        });
        tvTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvChonGio.setText(tvTime3.getText());
                IDKhungGio = 3;
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
                if (isChecked){
                    layoutChonSan.setVisibility(View.VISIBLE);
                    cosan = 1;}
                else{
                    cosan = 0;
                    layoutChonSan.setVisibility(View.GONE);}
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
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
                        tvChonNgay.setText(simpleDateFormat.format(calendar.getTime()));
                        d = simpleDateFormat1.format(calendar.getTime());
//                        System.out.println(d);
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
                IDSanBong = sanBongs.get(position).getId();
                tvChonSan.setText(arraySanBong.get(position));
                dialogChonSan.cancel();
            }
        });
    }

    private void ShowDialogChonSan() {
        dialogChonSan = new Dialog(getActivity());
        dialogChonSan.setContentView(R.layout.dialog_chon_san);
        lvSanBong = dialogChonSan.findViewById(R.id.ListViewSanBong);
        ArrayAdapter adapterSanBong = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arraySanBong);
        lvSanBong.setAdapter(adapterSanBong);
        dialogChonSan.show();
    }

    void BaSuKienClickListViewDoiBong() {
        lvDoiBong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IDDoiBong = doiBongs.get(position).getId();
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
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayDoiBong);
        lvDoiBong.setAdapter(adapter);
    }
}