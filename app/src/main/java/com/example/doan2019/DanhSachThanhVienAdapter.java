package com.example.doan2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DanhSachThanhVienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ThanhVienDoiBongClass> arrThanhVien;

    public DanhSachThanhVienAdapter(Context context, int layout, List<ThanhVienDoiBongClass> arrThanhVien) {
        this.context = context;
        this.layout = layout;
        this.arrThanhVien = arrThanhVien;
    }

    @Override
    public int getCount() {
        return arrThanhVien.size();
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
        ImageView imgDaiDien;
        TextView txtTen;
        TextView txtChucVu;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            viewHolder.imgDaiDien = view.findViewById(R.id.ImageViewDaiDienThanhVienDoiBong);
            viewHolder.txtTen = view.findViewById(R.id.TextViewTenThanhVien);
            viewHolder.txtChucVu = view.findViewById(R.id.TextViewChucVu);

            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        ThanhVienDoiBongClass thanhVien = arrThanhVien.get(i);
        viewHolder.imgDaiDien.setImageBitmap(thanhVien.getImageDaiDien());
        viewHolder.txtTen.setText(thanhVien.getTen());
        viewHolder.txtChucVu.setText(thanhVien.getChucVu());

        return view;
    }
}
