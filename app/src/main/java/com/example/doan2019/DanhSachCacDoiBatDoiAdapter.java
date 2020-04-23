package com.example.doan2019;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DanhSachCacDoiBatDoiAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<DoiBongClass> doiBongList;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;

    public DanhSachCacDoiBatDoiAdapter(Context context, int layout, List<DoiBongClass> doiBongList) {
        this.context = context;
        this.layout = layout;
        this.doiBongList = doiBongList;
    }

    public DanhSachCacDoiBatDoiAdapter() {
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
        ImageView imgDoiBong;
        TextView txtTen;
        TextView txtDiem;
        Button btnDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) context;
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.imgDoiBong = view.findViewById(R.id.ImageViewDongDoiBatDoi);
            viewHolder.txtTen = view.findViewById(R.id.TextViewTenDoiBongBatDoi);
            viewHolder.txtDiem = view.findViewById(R.id.TextViewDiemDoiBongBatDoi);
            viewHolder.btnDelete = view.findViewById(R.id.ButtonDongYBatDoi);

            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();

        //Gan gia tri
        DoiBongClass doiBong = doiBongList.get(i);
        viewHolder.imgDoiBong.setImageBitmap(doiBong.getImageDaiDien());
        viewHolder.txtTen.setText(doiBong.getTen());
        viewHolder.txtDiem.setText(doiBong.getDiem() + " Điểm");

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Click Đồng ý: " + i + "\nBắt sự kiện trong Adapter nhé", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChiTietDoiBongBatDoiFragment chiTietDoiBongBatDoiFragment = new ChiTietDoiBongBatDoiFragment();

                Bundle bundle = new Bundle();
                DoiBongClass doiBongClass = doiBongList.get(i);
                bundle.putSerializable("doibong", doiBongClass);
                chiTietDoiBongBatDoiFragment.setArguments(bundle);

                Toast.makeText(context, "Bắt sự kiện trong Adapter nhé", Toast.LENGTH_SHORT).show();
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietDoiBongBatDoiFragment);
            }
        });

        return view;
    }
}
