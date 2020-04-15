package com.example.doan2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class DanhSachCacTinDaDangAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DangTinDuongClass> arrDangTin;
    private int idDoiBatDoi;

    public DanhSachCacTinDaDangAdapter(Context context, int layout, List<DangTinDuongClass> arrDangTin) {
        this.context = context;
        this.layout = layout;
        this.arrDangTin = arrDangTin;
    }

    public DanhSachCacTinDaDangAdapter() {
    }

    @Override
    public int getCount() {
        return arrDangTin.size();
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
        TextView txtThuTuTinDang;
        TextView txtNgayDangTin;
        TextView txtDoiBatDoi;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.txtThuTuTinDang = view.findViewById(R.id.TextViewThuTuDongDangTin);
            viewHolder.txtNgayDangTin = view.findViewById(R.id.TextViewNgayDangTin);
            viewHolder.txtDoiBatDoi = view.findViewById(R.id.TextViewDoiBongBatDoi);

            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();

        //Gan gia tri
        DangTinDuongClass dangTin = arrDangTin.get(i);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String time = dateFormat.format(dangTin.getCreated_at());

        viewHolder.txtThuTuTinDang.setText(i + 1 + "");
        viewHolder.txtNgayDangTin.setText(time);
        if(dangTin.getIdDoiBatDoi() != -1){
            viewHolder.txtDoiBatDoi.setText("Tên đội bóng bắt đối");
        }
        else{
            viewHolder.txtDoiBatDoi.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
