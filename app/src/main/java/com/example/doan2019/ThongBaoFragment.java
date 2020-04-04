package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ThongBaoFragment extends Fragment {
    View view;
    ListView listViewNotification;
    ArrayList<Notification> notificationArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thong_bao, container, false);

        mapping();

        return view;
    }

    private void mapping(){
        listViewNotification = (ListView) view.findViewById(R.id.listViewNotification);

        notificationArrayList = new ArrayList<>();
        notificationArrayList.add(new Notification("Đội N muốn bắt đối với đội bóng của bạn"));
        notificationArrayList.add(new Notification("Đội N muốn bắt đối với đội bóng của bạn"));
        notificationArrayList.add(new Notification("Đội N muốn bắt đối với đội bóng của bạn"));
        notificationArrayList.add(new Notification("Đội N muốn bắt đối với đội bóng của bạn"));
        notificationArrayList.add(new Notification("Đội N muốn bắt đối với đội bóng của bạn"));
        notificationArrayList.add(new Notification("Đội N muốn bắt đối với đội bóng của bạn"));
        notificationArrayList.add(new Notification("Đội N muốn bắt đối với đội bóng của bạn"));
        notificationArrayList.add(new Notification("Đội N muốn bắt đối với đội bóng của bạn"));
        notificationArrayList.add(new Notification("Đội N muốn bắt đối với đội bóng của bạn"));
        notificationArrayList.add(new Notification("Đội N muốn bắt đối với đội bóng của bạn"));

        NotificationAdapter notificationAdapter = new NotificationAdapter(getActivity(), R.layout._notification, notificationArrayList);
        listViewNotification.setAdapter(notificationAdapter);

    }

}
