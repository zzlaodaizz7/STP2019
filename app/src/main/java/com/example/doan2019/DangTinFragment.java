package com.example.doan2019;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import android.support.v4.media.MediaMetadataCompat;
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
    int IDUser = 17;
    int cosan = 0;
    String keo = "";
    JsonApiSanBong jsonApiSanBong;
    String d = "-1";
    String Auth = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNzJiMDMyOTNjNzMzYThlYmQ3OTJmMTA4Y2VkMzdmODdiODY0MTc2ODg5ZDI0YjhlZDA3Y2MwN2FlZmZkNmMwOGY0NmM2MjY2ZTVjYzBjZmMiLCJpYXQiOjE1ODY4NzA2MTEsIm5iZiI6MTU4Njg3MDYxMSwiZXhwIjoxNjE4NDA2NjExLCJzdWIiOiIxNyIsInNjb3BlcyI6W119.NBvZh-c-RYTWSMeWtIb90C9JxXlae5bHTSs211Z5K1NwXBvnZGhoUr51_bygtBjGOFnzniCu8kIstIvfG2SMkyLrSFDhWDoC5mPHkElLYuQ0iFtnfWGYJKZvxHDDQTU2eHgrscGEW_VQS7uqAfRPNzcMYv4Z8nz5TgdZpms_dTqz4GWiUuWoR_ZVa2qL4WHGSu6xqm-9YUwznuweJdJHkiEMUTPbYZlZ07hcRHnwrq_bmHKPAeK623gli28cvZ1tuyEErLkHRRCUKFajgHEVfuv9MAOEm6d0RoBV7CokdCBDX4fLA2BGPzYA_jwCtnBBzvHKbkgeuDAIOqCZVsiJiRog-d3thH309ovrHs4Jvg5XXJHvZLOmK9GVRpmP_CAgbbTG3YtrwI90umG9y01XidUuPupyK4PIFwscWrJO1ozEBzoGEEhTyp7-zS0WdYdra6RJNkWp1dy2SMWVBQYtEwPgXeHIVp9CUkIdJTtmQ_iNMavDkawZ1rix67ICyeFEMfjOt8pRMNtu6LCaziMKOkG_I2qW8O3YXY5p4hWF8blu-wMTeCTegywDk45NHU3ZY02yGDyBAhvsv2K31LIHfv3d6Ut-EibTzH_yugvHPaBhQ_ej8bfY-1UL8LXDTltWSFXKNKFQQvPrwJHgx9nFSqRXcfg3SJxmlWDuL1QYNAo";
    private View view;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    Retrofit retrofit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dang_tin, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4/DoAn/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonApiSanBong = retrofit.create(JsonApiSanBong.class);

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
                doiBongs = response.body();
                System.out.println("COde"+response.code());
                for (DoiBong doiBong : doiBongs){
                    System.out.println(doiBong.getTen());
                    arrayDoiBong.add(doiBong.getTen());
                }
            }
            @Override
            public void onFailure(Call<List<DoiBong>> call, Throwable t) {
                System.out.println("loi: "+t.getMessage());
            }
        });
    }

    private void LoadListSanBong() {
        arraySanBong = new ArrayList<>();
        jsonApiSanBong = retrofit.create(JsonApiSanBong.class);
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
                System.out.println("loi: "+t.getMessage());
            }
        });
    }

    private void ClickDangTin() {
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keo = edtKeoDau.getText().toString();
                System.out.println(IDDoiBong);
                System.out.println(d);
                System.out.println(keo);
                System.out.println(IDSanBong);
                System.out.println(IDKhungGio);
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
                            if (response.body().getTitle() == "success"){
                                Toast.makeText(getContext(), response.body().getContent(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getContext(), response.body().getContent(), Toast.LENGTH_SHORT).show();
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
                        System.out.println(d);
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