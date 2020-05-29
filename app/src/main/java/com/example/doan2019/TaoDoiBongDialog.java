package com.example.doan2019;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.JsonApiSanBong;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaoDoiBongDialog extends DialogFragment {
    private View view;
    TextView txtXong, tvChonKhungGio, tvQuayLai, tvXongChonGio;
    ImageView imvClose;
    EditText edtTen, edtDiaChi, edtDienThoai;
    Button btnTrinhDo;
    Dialog dialogChonTrinhDo;
    ListView listViewTrinhDo;
    Dialog dialogChonGio;
    ListView lvChonGio;
    ArrayList<String> levelArrayList;
    SharedPreferences sharedPreferences;
    Retrofit retrofit;
    JsonApiSanBong jsonApiSanBong;
    ArrayList<Boolean> arrGioDaChon;
    ArrayList<String> arrGio;
    String Auth = "";
    Integer IDUser = -1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_tao_doi_bong, container, false);
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);

        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        IDUser = sharedPreferences.getInt("id",0);
        Auth = sharedPreferences.getString("token","");

        Mapping();

        ClickButtonTrinhDo();

        ClickTextViewChonKhungGio();

        ClickXong();

        ClickClosePopup();

        return view;
    }

    private void ClickTextViewChonKhungGio() {
        tvChonKhungGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogChonGio();
                BatSuKienClickDialogChonGio();
            }
        });
    }

    private void BatSuKienClickDialogChonGio() {
        lvChonGio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(arrGioDaChon.get(position) == false)
                    arrGioDaChon.set(position, true);
                else
                    arrGioDaChon.set(position, false);
            }
        });
    }

    private void ShowDialogChonGio() {
        dialogChonGio = new Dialog(getActivity());
        dialogChonGio.setContentView(R.layout.dialog_chon_gio_tao_doi_bong);
        lvChonGio = dialogChonGio.findViewById(R.id.ListViewGio);
        tvQuayLai = dialogChonGio.findViewById(R.id.TextViewBack);
        tvXongChonGio = dialogChonGio.findViewById(R.id.TextViewXong);

        tvQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogChonGio.cancel();
            }
        });

        tvXongChonGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gioDaChon = "";
                int j = 1;
                for(int i = 7; i <= 21; i++){
                    if(arrGioDaChon.get(i - 7) == true && i < 10){
                        gioDaChon += "0" + i + ":00";
                        j = i;
                        break;
                    }
                    else if(arrGioDaChon.get(i - 7) == true && i >= 10){
                        gioDaChon += i + ":00";
                        j = i;
                        break;
                    }
                }
                if(j != 1) {
                    for (int i = j + 1; i <= 21; i++) {
                        if (arrGioDaChon.get(i - 7) == true && i < 10) {
                            gioDaChon += ", 0" + i + ":00";
                        } else if (arrGioDaChon.get(i - 7) == true && i >= 10) {
                            gioDaChon += ", " + i + ":00";
                        }
                    }
                }
                if(!gioDaChon.equals(""))
                    tvChonKhungGio.setText(gioDaChon);
                dialogChonGio.cancel();
            }
        });

        lvChonGio.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        arrGio = new ArrayList<>();
        for(int i = 7; i <= 21; i++){
            if(i < 10){
                arrGio.add("0" + i + ":00");
            }
            else{
                arrGio.add(i + ":00");
            }
        }
        ArrayAdapter adapterGio = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_multiple_choice, arrGio);
        lvChonGio.setAdapter(adapterGio);
        for(int i = 7; i <= 21; i++){
            lvChonGio.setItemChecked(i - 7, arrGioDaChon.get(i - 7));
        }
        dialogChonGio.show();
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
//                System.out.println(edtTen.getText());
//                System.out.println(btnTrinhDo.getText());
//                System.out.println(Auth);
//                System.out.println(IDUser);
                String content="";
                if (edtTen.getText().toString().matches("")) {
                    content += "Bạn chưa nhập tên đội! \n";
                }
                if (btnTrinhDo.getText().equals("Trình độ")){
                    content += "Bạn chưa chọn trình độ";
                }
                if (content == ""){
                    Map<String,String> header = new HashMap<>();
                    header.put("value","application/json");
                    header.put("Accept","application/json");
                    header.put("Authorization","Bearer "+Auth);
                    DoiBong doiBong = new DoiBong(edtTen.getText().toString(),btnTrinhDo.getText().toString(),edtDiaChi.getText().toString(),edtDienThoai.getText().toString(),IDUser);
                    Call<DoiBong> call = jsonApiSanBong.postTaodoibongs(header, doiBong);
                    try {
                        call.enqueue(new Callback<DoiBong>() {
                            @Override
                            public void onResponse(Call<DoiBong> call, Response<DoiBong> response) {
//                            System.out.println(response.body().getContent());
//                            System.out.println(response.body().getType());
                                try {
                                    if (response.body().getType() == "success") {
                                        TaoDoiBongDialog.this.getDialog().cancel();
                                        Toast.makeText(getContext(), "Tạo đội thành công!", Toast.LENGTH_SHORT).show();
                                    } else {
//                                System.out.println("aaa");
//                            Toast.makeText(getContext(), response.body().getContent(), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception ex) {
                                    Log.e("BBB", ex.toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<DoiBong> call, Throwable t) {

                            }
                        });
                    }
                    catch (Exception ex){
                        Log.e("BBB", ex.toString());
                    }
                    TaoDoiBongDialog.this.getDialog().cancel();
                }else{
                    Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
                }

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
        arrGioDaChon = new ArrayList<>();
        for(int i = 7; i <= 21; i++){
            arrGioDaChon.add(false);
        }
        tvChonKhungGio = view.findViewById(R.id.TextViewChonKhungGio);
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
