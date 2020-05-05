package com.example.doan2019;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doan2019.Retrofit.DoiBong;

import java.util.List;

public class XepHangAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DoiBong> doiBongList;

    public XepHangAdapter(Context context, int layout, List<DoiBong> doiBongList) {
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
        try {
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
            DoiBong doiBong = doiBongList.get(i);
            viewHolder.txtSoThuTu.setText(i + 1 + "");
            viewHolder.txtTen.setText(doiBong.getTen());
            viewHolder.txtDiem.setText(doiBong.getSodiem() + " Điểm");
        } catch (Exception ex) {
            Log.d("BBB", ex.toString());
        }

        return view;
    }
}
