package com.example.doan2019;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.ThongBao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongBaoFragment extends Fragment {
    View view;
    ListView listViewNotification;
    ArrayList<Notification> notificationArrayList, arrIDNotification;
    SharedPreferences sharedPreferences;
    String Auth ="";
    TextView txtBack;
    Integer IDUser;
    JsonApiSanBong jsonApiSanBong;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thong_bao, container, false);
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        IDUser = sharedPreferences.getInt("id",0);
        Auth = sharedPreferences.getString("token","");
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        mapping();

        ClickBack();

        KhoiTaoListViewThongBao();

        ClickListViewThongBao();

        return view;
    }

    private void ClickListViewThongBao() {
        listViewNotification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), arrIDNotification.get(position).getId() + "", Toast.LENGTH_SHORT).show();
                BatDoiFragment batDoiFragment = new BatDoiFragment();
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(batDoiFragment);
            }
        });
    }

    private void KhoiTaoListViewThongBao() {
        notificationArrayList = new ArrayList<>();
        arrIDNotification = new ArrayList<>();

        Call<List<ThongBao>> call = jsonApiSanBong.getThongbao(IDUser);
        call.enqueue(new Callback<List<ThongBao>>() {
            @Override
            public void onResponse(Call<List<ThongBao>> call, Response<List<ThongBao>> response) {
                try {
//                System.out.println(response.code());
                    List<ThongBao> thongBao = response.body();
                    for (ThongBao thongBao1 : thongBao) {
                        notificationArrayList.add(new Notification(thongBao1.getNoidung()));
                        arrIDNotification.add(new Notification(thongBao1.getId()));
                    }
                    NotificationAdapter notificationAdapter = new NotificationAdapter(getActivity(), R.layout._notification, notificationArrayList);
                    listViewNotification.setAdapter(notificationAdapter);
                }
                catch (Exception ex){
                    Log.e("BBB", ex.toString());
                }
            }

            @Override
            public void onFailure(Call<List<ThongBao>> call, Throwable t) {

            }
        });
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
        txtBack = view.findViewById(R.id.TextViewBack);
        listViewNotification = (ListView) view.findViewById(R.id.listViewNotification);
    }

}
