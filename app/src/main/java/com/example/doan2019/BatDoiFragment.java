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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.DTO.DangTinDTO;
import com.example.doan2019.Retrofit.BatDoi;
import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DangTin;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.JsonApiBatDoi;
import com.example.doan2019.Retrofit.JsonApiDangTin;
import com.example.doan2019.Retrofit.JsonApiDoiBongNGuoiDung;
import com.example.doan2019.Retrofit.JsonApiThongBao;
import com.example.doan2019.Retrofit.JsonApiUser;
import com.example.doan2019.Retrofit.ThongBao;
import com.example.doan2019.Retrofit.User;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatDoiFragment extends Fragment {

    View view;
    TextView txtTime, txtPitch, txtRatio, txtState, txtLevel, txtTeamHost, txtTeamGuest, txtVS, txtBack;
    Button btnBatDoi;
    Bundle bundle;
    JsonApiDangTin jsonApiDangTin; JsonApiBatDoi jsonApiBatDoi; JsonApiUser jsonApiUser; JsonApiThongBao jsonApiThongBao; JsonApiDoiBongNGuoiDung jsonApiDoiBongNGuoiDung;
    DangTinDTO dangTinDTO;
    Dialog dialogChonDoi;
    int dangtin_id, doitimdoi_id, doibatdoi_id, mode;
    String doidangtin_device;
    SharedPreferences sharedPreferences, sharedPreferencesLoadTimDoi;
    String Auth = "";
    ArrayList<DoiBong> doiBongArrayList;
    Map<String,String> header;
    ListView listViewDoiBong, mainLV;
    ArrayList<String> tenDoiBongArrayList;
    String doibatdoi_ten;
    Dialog dialogTinNhan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bat_doi, container, false);
        jsonApiBatDoi = APIUtils.getJsonApiBatDoi();
        jsonApiUser = APIUtils.getJsonApiUser();
        jsonApiThongBao = APIUtils.getJsonApiThongBao();
        jsonApiDoiBongNGuoiDung = APIUtils.getJsonApiDoiBongNguoiDung();
        jsonApiDangTin = APIUtils.getJsonApiDangTin();

        mapping();
        loadDoiDangTin_device();
        LoadListDoiBong();
        clickBtnBatDoi();
        ClickBack();

        return view;
    }

    private void ClickBack() {
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void mapping(){
        sharedPreferencesLoadTimDoi = getActivity().getSharedPreferences("LoadDataDangTin", Context.MODE_PRIVATE);
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        Auth = sharedPreferences.getString("token","");
        header = new HashMap<>();
        header.put("value","application/json");
        header.put("Accept","application/json");
        header.put("Authorization","Bearer "+Auth);

        mainLV = (ListView) getActivity().findViewById(R.id.mainLV);
        txtBack = view.findViewById(R.id.TextViewBack);
        txtLevel = view.findViewById(R.id.txtLevel);
        txtTime = view.findViewById(R.id.txtTime);
        txtPitch = view.findViewById(R.id.txtPitch);
        txtRatio = view.findViewById(R.id.txtRatio);
        txtState = view.findViewById(R.id.txtState);
        txtVS = view.findViewById(R.id.txtVS);
        txtTeamHost = view.findViewById(R.id.txtTeamHost);
        txtTeamGuest = view.findViewById(R.id.txtTeamGuest);
        btnBatDoi = view.findViewById(R.id.btnBatDoi);
        mode = 1;

        bundle = getArguments();

        dangTinDTO = (DangTinDTO) bundle.get("batdoi");
        if(dangTinDTO.getDoibatdoi_id() != -1){
            btnBatDoi.setVisibility(View.GONE);
        }
        else{
        }

        if(sharedPreferences.getInt("id", -1)==dangTinDTO.getDoitruongdoidangtin_id()){
            btnBatDoi.setText("Hủy trận đấu");
            mode = 2;
        }
        dangTinDTO.getDoitruongdoidangtin_id();
        dangtin_id = dangTinDTO.getId();
        doitimdoi_id = dangTinDTO.getDoidangtin_id();
        doibatdoi_id = -1;
        txtTeamHost.setText(dangTinDTO.getDoidangtin_ten());
        txtTeamGuest.setText(dangTinDTO.getDoibatdoi_ten());
        txtTime.setText(dangTinDTO.getNgay()+" "+dangTinDTO.getKhunggio_thoigian());
        txtState.setText(dangTinDTO.getTrangthai());
        txtRatio.setText(dangTinDTO.getKeo());
        txtPitch.setText(dangTinDTO.getSan_ten());
        txtLevel.setText(dangTinDTO.getTrinhdo());
    }

    private void loadDoiDangTin_device(){
        Call<User> userCall = jsonApiUser.getNguoiDung(dangTinDTO.getDoitruongdoidangtin_id());
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                doidangtin_device = response.body().getDevice();
                Log.d("batdoi", "da lay duoc thiet bi doi dang tin");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void LoadListDoiBong() {
        doiBongArrayList = new ArrayList<>();
        Call<List<DoiBong>> call = jsonApiDoiBongNGuoiDung.getDoitruongcacdois(sharedPreferences.getInt("id", -1));
        call.enqueue(new Callback<List<DoiBong>>() {
            @Override
            public void onResponse(Call<List<DoiBong>> call, Response<List<DoiBong>> response) {
                try {
                    List<DoiBong> doiBongs = response.body();
                    for (DoiBong doiBong : doiBongs) {
                        doiBongArrayList.add(doiBong);
                        if (doiBong.getUser_id() == dangTinDTO.getDoitruongdoidangtin_id()) {
                            btnBatDoi.setText("Hủy trận đấu");
                        }
                    }
                    Log.d("batdoi", doiBongArrayList + "555");
                }
                catch (Exception ex){
                    Log.e("BBB", ex.toString());
                }
            }
            @Override
            public void onFailure(Call<List<DoiBong>> call, Throwable t) {
                System.out.println("loi: "+t.getMessage());
            }
        });
    }

    void clickDialogChonDoi() {
        listViewDoiBong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //btnBatDoi.setText();
                doibatdoi_ten = tenDoiBongArrayList.get(position);
                Log.d("batdoi", "tendoibat" + doibatdoi_ten);
                dialogChonDoi.cancel();
            }
        });
    }

    private void showDialogChonDoi() {
        dialogChonDoi = new Dialog(getActivity());
        dialogChonDoi.setContentView(R.layout.dialog_chon_doi);
        dialogChonDoi.show();

        listViewDoiBong = dialogChonDoi.findViewById(R.id.ListViewDoiBong);
        tenDoiBongArrayList = new ArrayList<>();

        for(DoiBong doiBong2 : doiBongArrayList){
            tenDoiBongArrayList.add(doiBong2.getTen());
        }

        if(doiBongArrayList.size() == 0){
            showDialogTinNhan("Bạn không có đội bóng.");
            hideDialogTinNhan();
            dialogChonDoi.cancel();
            return;
        }

        ArrayAdapter statusAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, tenDoiBongArrayList);

        listViewDoiBong.setAdapter(statusAdapter);
    }

    // tao thong bao
    private void clickBtnBatDoi(){
        if(mode == 1){
            btnBatDoi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //tao bat doi
                    showDialogChonDoi();
                    listViewDoiBong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //btnBatDoi.setText();
                            doibatdoi_ten = tenDoiBongArrayList.get(position);
                            dialogChonDoi.cancel();

                            for(DoiBong doiBong : doiBongArrayList){
                                if(doiBong.getTen().equals(doibatdoi_ten)){
                                    BatDoi batDoi = new BatDoi(doiBong.getId(), doitimdoi_id, dangtin_id);
                                    Call<BatDoi> call1 = jsonApiBatDoi.createBatDoi(header, batDoi);
                                    call1.enqueue(new Callback<BatDoi>() {
                                        @Override
                                        public void onResponse(Call<BatDoi> call, Response<BatDoi> response) {
                                            Log.d("batdoi", "Bat doi thanh cong");
                                            // tao thong bao
                                            ThongBao thongBao = new ThongBao(dangTinDTO.getDoitruongdoidangtin_id(), doibatdoi_ten + " muốn bắt đối với bạn", "batdoi", doidangtin_device);
                                            Call<ThongBao> call2 = jsonApiThongBao.createThongBao(thongBao);
                                            call2.enqueue(new Callback<ThongBao>() {
                                                @Override
                                                public void onResponse(Call<ThongBao> call, Response<ThongBao> response) {
                                                    Log.d("batdoi", "tao thong bao thanh cong");
                                                    showDialogTinNhan("Gửi yêu cầu thành công.");
                                                    hideDialogTinNhan();
                                                    dayThongBaoOneSignal(doibatdoi_ten+" muốn bắt đối với bạn", doidangtin_device);
                                                }

                                                @Override
                                                public void onFailure(Call<ThongBao> call, Throwable t) {

                                                }
                                            });
                                            //
                                        }

                                        @Override
                                        public void onFailure(Call<BatDoi> call, Throwable t) {
                                        }
                                    });
                                    break;
                                }

                            }
                        }
                    });
                }
            });
        }
        else{
//            Toast.makeText(getActivity(), "Xóa tin "+dangTinDTO.getId(), Toast.LENGTH_LONG).show();
            btnBatDoi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DangTinService.deleteDangTin(getActivity(), mainLV, dangTinDTO);
                    Call<DangTin> call = jsonApiDangTin.deleteDangTins(header, dangTinDTO.getId());
                    call.enqueue(new Callback<DangTin>() {
                        @Override
                        public void onResponse(Call<DangTin> call, Response<DangTin> response) {
                            //Toast.makeText(getActivity(), "Chuyen Fragment", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<DangTin> call, Throwable t) {

                        }
                    });
                }
            });
        }

    }
    //day thong bao voi onesignal
    private void dayThongBaoOneSignal(String noidung, String userId ){
        try {
            //
             // id người nhận, id do oneSignal cấp cho thiết bị android ngay sau khi ứng dụng thiết lập trên thiết bị
            String headings = "Thông báo";
            String contents = noidung;
            JSONObject notification = new JSONObject("{'contents': {'en':'"+contents+"'}, " +
                    "'include_player_ids': ['" + userId + "']," +
                    "'headings':{'en':'"+headings+"'}}");
            OneSignal.postNotification(notification, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void showDialogTinNhan(String text){
        dialogTinNhan = new Dialog(getActivity());
        dialogTinNhan.setContentView(R.layout.dialog_message);
        dialogTinNhan.show();
        TextView tvTinNhan = (TextView) dialogTinNhan.findViewById(R.id.tvTinNhan);
        tvTinNhan.setText(text);
    }
    private void hideDialogTinNhan(){
        TextView tvHuy = dialogTinNhan.findViewById(R.id.tvHuy);
        tvHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTinNhan.cancel();
            }
        });
    }

}
