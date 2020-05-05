package com.example.doan2019;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
    ArrayList<Notification> notificationArrayList;
    SharedPreferences sharedPreferences;
    String Auth ="";
    Integer IDUser;
    JsonApiSanBong jsonApiSanBong;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thong_bao, container, false);
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        IDUser = sharedPreferences.getInt("id",0);
        Auth = sharedPreferences.getString("token","");
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        mapping();

        return view;
    }

    private void mapping(){
        listViewNotification = (ListView) view.findViewById(R.id.listViewNotification);

        notificationArrayList = new ArrayList<>();

        Call<List<ThongBao>> call = jsonApiSanBong.getThongbao(IDUser);
        call.enqueue(new Callback<List<ThongBao>>() {
            @Override
            public void onResponse(Call<List<ThongBao>> call, Response<List<ThongBao>> response) {
                System.out.println(response.code());
                List<ThongBao> thongBao = response.body();
                for (ThongBao thongBao1 : thongBao){
                    notificationArrayList.add(new Notification(thongBao1.getNoidung()));
                }
                NotificationAdapter notificationAdapter = new NotificationAdapter(getActivity(), R.layout._notification, notificationArrayList);
                listViewNotification.setAdapter(notificationAdapter);
            }

            @Override
            public void onFailure(Call<List<ThongBao>> call, Throwable t) {

            }
        });




    }

}
