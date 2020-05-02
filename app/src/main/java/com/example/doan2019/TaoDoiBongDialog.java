package com.example.doan2019;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
    TextView txtXong;
    ImageView imvClose;
    EditText edtTen, edtDiaChi, edtDienThoai;
    Button btnTrinhDo;
    Dialog dialogChonTrinhDo;
    ListView listViewTrinhDo;
    ArrayList<String> levelArrayList;
    SharedPreferences sharedPreferences;
    Retrofit retrofit;
    JsonApiSanBong jsonApiSanBong;
    String Auth = "";
    Integer IDUser = -1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_tao_doi_bong, container, false);
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.4/DoAn/public/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        IDUser = sharedPreferences.getInt("id",0);
        Auth = sharedPreferences.getString("token","");
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
                    call.enqueue(new Callback<DoiBong>() {
                        @Override
                        public void onResponse(Call<DoiBong> call, Response<DoiBong> response) {
                            System.out.println(response.body().getContent());
                            System.out.println(response.body().getType());
                            if (response.body().getType() == "success"){
                                TaoDoiBongDialog.this.getDialog().cancel();
                                Toast.makeText(getContext(), "Tạo đội thành công!", Toast.LENGTH_SHORT).show();
                            }else{
                                System.out.println("aaa");
//                            Toast.makeText(getContext(), response.body().getContent(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DoiBong> call, Throwable t) {

                        }
                    });
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
