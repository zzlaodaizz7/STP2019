package com.example.doan2019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    ListView listViewNotification;
    ArrayList<Notification> notificationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mapping();
    }

    private void mapping(){
        listViewNotification = (ListView) findViewById(R.id.listViewNotification);

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

        NotificationAdapter notificationAdapter = new NotificationAdapter(this, R.layout._notification, notificationArrayList);
        listViewNotification.setAdapter(notificationAdapter);

    }
}
