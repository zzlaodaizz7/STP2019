package com.example.doan2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TimSanAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<SanBongClass> sanBongList;

    public TimSanAdapter(Context context, int layout, List<SanBongClass> sanBongList) {
        this.context = context;
        this.layout = layout;
        this.sanBongList = sanBongList;
    }

    public TimSanAdapter() {
    }

    @Override
    public int getCount() {
        return sanBongList.size();
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
        ImageView imgSanBong;
        TextView txtTenSanBong, txtDiaChiSanBong, txtSoDienThoaiSanBong;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.imgSanBong = view.findViewById(R.id.ImageViewDongSanBong);
            viewHolder.txtTenSanBong = view.findViewById(R.id.TextViewTenSanBong);
            viewHolder.txtDiaChiSanBong = view.findViewById(R.id.TextViewDiaChiSanBong);
            viewHolder.txtSoDienThoaiSanBong = view.findViewById(R.id.TextViewPhoneSanBong);

            view.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) view.getTag();

        SanBongClass sanBong = sanBongList.get(i);
        viewHolder.imgSanBong.setImageBitmap(sanBong.getImgSanBong());
        viewHolder.txtTenSanBong.setText(sanBong.getTenSanBong());
        viewHolder.txtDiaChiSanBong.setText(sanBong.getDiaChi());
        viewHolder.txtSoDienThoaiSanBong.setText(sanBong.getSoDienThoai());

        return view;
    }
}
