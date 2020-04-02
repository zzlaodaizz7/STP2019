package com.example.doan2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class NotificationAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Notification> notificationList;

    public NotificationAdapter(Context context, int layout, List<Notification> notificationList) {
        this.context = context;
        this.layout = layout;
        this.notificationList = notificationList;
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtNoiDung;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NotificationAdapter.ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder = new NotificationAdapter.ViewHolder();
            viewHolder.txtNoiDung = (TextView) convertView.findViewById(R.id.txtNoiDung);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (NotificationAdapter.ViewHolder) convertView.getTag();
        }

        Notification notification = notificationList.get(position);

        viewHolder.txtNoiDung.setText(notification.getNoidung());
        return convertView;
    }
}