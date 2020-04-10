package com.example.doan2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class XepHangAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DoiBongClass> doiBongList;

    public XepHangAdapter(Context context, int layout, List<DoiBongClass> doiBongList) {
        this.context = context;
        this.layout = layout;
        this.doiBongList = doiBongList;
    }

    public XepHangAdapter() {
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

    private class ViewHolder {
        TextView txtSoThuTu;
        TextView txtTen;
        TextView txtDiem;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.txtSoThuTu = view.findViewById(R.id.TextViewThuTuDongXepHang);
            viewHolder.txtTen = view.findViewById(R.id.TextViewTenDoiBongDongXepHang);
            viewHolder.txtDiem = view.findViewById(R.id.TextViewDiemDoiBong);

            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();

        //Gan gia tri
        DoiBongClass doiBong = doiBongList.get(i);
        viewHolder.txtSoThuTu.setText(i + 1 + "");
        viewHolder.txtTen.setText(doiBong.getTen());
        viewHolder.txtDiem.setText(doiBong.getDiem() + " Điểm");

        return view;
    }
}
