package com.example.doan2019;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import com.example.doan2019.Retrofit.DoiBong;
import com.squareup.picasso.Picasso;

public class TimKiemDoiBongAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DoiBong> doiBongList;

    public TimKiemDoiBongAdapter(Context context, int layout, List<DoiBong> doiBongList) {
        this.context = context;
        this.layout = layout;
        this.doiBongList = doiBongList;
    }

    public TimKiemDoiBongAdapter() {
    }



    @Override
    public int getCount() {
        return doiBongList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgAnhDaiDien;
        TextView txtTenDoiBong;
        TextView txtDiaChiDoiBong;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.imgAnhDaiDien = view.findViewById(R.id.ImageViewTimKiemDoiBong);
            viewHolder.txtDiaChiDoiBong = view.findViewById(R.id.TextViewDiaChiDoiBong);
            viewHolder.txtTenDoiBong = view.findViewById(R.id.TextViewTenDoiBongDong);

            view.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) view.getTag();

        DoiBong doiBong = doiBongList.get(i);
        if(doiBong.getAnhdaidien() != null){
            Picasso.get().load(doiBong.getAnhdaidien()).into(viewHolder.imgAnhDaiDien);
        }

        viewHolder.txtTenDoiBong.setText(doiBong.getTen());
        viewHolder.txtDiaChiDoiBong.setText(doiBong.getDiachi());

        return view;
    }
}
