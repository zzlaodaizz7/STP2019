package com.example.doan2019;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.DTO.DangTinDTO;
import com.example.doan2019.Retrofit.BatDoi;
import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.JsonApiBatDoi;
import com.example.doan2019.Retrofit.JsonApiDoiBongNGuoiDung;
import com.example.doan2019.Retrofit.JsonApiUser;
import com.example.doan2019.Retrofit.User;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatDoiFragment extends Fragment {

    View view;
    TextView txtTime, txtPitch, txtRatio, txtState, txtLevel, txtTeamHost, txtTeamGuest, txtVS;
    Button btnBatDoi;
    Bundle bundle;
    JsonApiBatDoi jsonApiBatDoi;
    JsonApiUser jsonApiUser;
    DangTinDTO dangTinDTO;
    String Auth = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiMzc1NGJmYTFiNjI4YmRiZjA1ZGNhY2UwZjdjYmEyZjFlMzcyMjNiNTJiMTE4ZjI5MDZjYWRkNjMwMjAzM2RmYTNjMzZjNmJhM2YxMTE5OTgiLCJpYXQiOjE1ODcxMzU3ODQsIm5iZiI6MTU4NzEzNTc4NCwiZXhwIjoxNjE4NjcxNzg0LCJzdWIiOiIxNyIsInNjb3BlcyI6W119.cxhKdPiQEmmpeDq01XQ3ea59yYJFsylDzaABUsft3dQ8KWK1RFFsYQzX6lFyZ4SqE5T3Li4tCvk_yjE4NxkEiYUbTA3YpDgckbRh9Bzbjjpc6_0fI-mZyQyxc8GhNzz1XhhBEwOIt4zKrgy6t-iS8Km3NPyMs65UwqL_FrlB0P830uLh9-W8uOYVpGo99oXGPcweW50D_H2cO0JX-ZCUpkhppVjy-xjg9Eh_0xm3NtYyBdHQ1O6meWxdf6-MzO6btCJouIyi8ASsiuUerg6OPRfDc9NzkUjWZRGIy1jSeD307sZI5DNX3fg6-dykiowj1qHeb_ufIaSg3M-6KsN-1HLS3gNkrVrChRq7StOf77l-YVBrXEdITiQQnJTmqajkIfHz2wJQtxCNLg7AVx75fbIBbj9S1U79I9hGVxxtTMJPMvoyFT5y7VopDblwbiO8Ef2wg4kRHroUirg3ECzDEZD7TdIwCYLL-vSeWdgNqhyvknMmeY8LDSKZOkM86AhUElLTOAWmwwDmIzF04Ao-B4uLWdRM5S0WIpstvRe2imLDdQ8jkZzIVcMclVorSoUMUdZ0Zf92twYZtkB--FBRQqyKhB2qzSbxoOt5UjbhLtDhkOr67shbGO1PJGWtNmpZv0cOhJwEFEgpcp3_JSOzQ2WYhOg3VqYDXEyNUZkd4cE";
    int dangtin_id, doitimdoi_id, doibatdoi_id;
    Map<String,String> header;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bat_doi, container, false);
        jsonApiBatDoi = APIUtils.getJsonApiBatDoi();
        jsonApiUser = APIUtils.getJsonApiUser();
        mapping();
        clickBtnBatDoi();

        return view;
    }

    private void mapping(){
        header = new HashMap<>();
        header.put("value","application/json");
        header.put("Accept","application/json");
        header.put("Authorization","Bearer "+Auth);
        txtLevel = view.findViewById(R.id.txtLevel);
        txtTime = view.findViewById(R.id.txtTime);
        txtPitch = view.findViewById(R.id.txtPitch);
        txtRatio = view.findViewById(R.id.txtRatio);
        txtState = view.findViewById(R.id.txtState);
        txtVS = view.findViewById(R.id.txtVS);
        txtTeamHost = view.findViewById(R.id.txtTeamHost);
        txtTeamGuest = view.findViewById(R.id.txtTeamGuest);
        btnBatDoi = view.findViewById(R.id.btnBatDoi);

        bundle = getArguments();

        dangTinDTO = (DangTinDTO) bundle.get("batdoi");
        if(dangTinDTO.getDoibatdoi_id() != -1){
            btnBatDoi.setVisibility(View.GONE);
        }
        else{
        }
        dangtin_id = dangTinDTO.getId();
        doitimdoi_id = dangTinDTO.getDoidangtin_id();
        doibatdoi_id = 13;
        txtTeamHost.setText(dangTinDTO.getDoidangtin_ten());
        txtTeamGuest.setText(dangTinDTO.getDoibatdoi_ten());
        txtTime.setText(dangTinDTO.getNgay()+" "+dangTinDTO.getKhunggio_thoigian());
        txtState.setText(dangTinDTO.getTrangthai());
        txtRatio.setText(dangTinDTO.getKeo());
        txtPitch.setText(dangTinDTO.getSan_ten());
        txtLevel.setText(dangTinDTO.getTrinhdo());
    }

    // tao thong bao
    private void clickBtnBatDoi(){
        btnBatDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tao bat doi
                BatDoi batDoi = new BatDoi(doibatdoi_id, doitimdoi_id, dangtin_id);

                Call<BatDoi> call1 = jsonApiBatDoi.createBatDoi(header, batDoi);
                call1.enqueue(new Callback<BatDoi>() {
                    @Override
                    public void onResponse(Call<BatDoi> call, Response<BatDoi> response) {
                        Log.d("batdoi", "Bat doi thanh cong");
                    }

                    @Override
                    public void onFailure(Call<BatDoi> call, Throwable t) {
                        Log.d("batdoi", "Bat doi that bai "+t );
                    }
                });


//                Call<List<BatDoi>> call = jsonApiBatDoi.getBatDois(header);
//                call.enqueue(new Callback<List<BatDoi>>() {
//                    @Override
//                    public void onResponse(Call<List<BatDoi>> call, Response<List<BatDoi>> response) {
//                        List<BatDoi> batDois = response.body();
//                        int check = 0;
//                        for(BatDoi batDoi1 : batDois){
//                            if(batDoi1.getDangtin_id() == dangtin_id && batDoi1.getDoibatdoi_id()==doibatdoi_id && batDoi1.getDoitimdoi_id()==doitimdoi_id){
//                                check = 1;
//                                Toast.makeText(getActivity(), "Bạn đã thực hiện bắt đối, hãy chờ câu trả lời của đội" + dangTinDTO.getDoidangtin_ten() , Toast.LENGTH_LONG).show();
//                            }
//                        }
//                        if(true){
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<BatDoi>> call, Throwable t) {
//
//                    }
//                });




                //push thong bao qua onesignal

                //dayThongBaoOneSignal();
            }
        });

    }
    //day thong bao voi onesignal
    private void dayThongBaoOneSignal(String userId ){
        try {
            //
             // id người nhận, id do oneSignal cấp cho thiết bị android ngay sau khi ứng dụng thiết lập trên thiết bị
            String headings = "Đây là tiêu đề thông báo";
            String contents = "Đây là nội dung thông báo. Bạn vừa bắt đối.";
            JSONObject notification = new JSONObject("{'contents': {'en':'"+contents+"'}, " +
                    "'include_player_ids': ['" + userId + "']," +
                    "'headings':{'en':'"+headings+"'}}");
            OneSignal.postNotification(notification, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
