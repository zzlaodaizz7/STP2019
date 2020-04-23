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

public class DanhSachDonXinGiaNhapFCAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ThanhVienDoiBongClass> listThanhVien;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;

    public DanhSachDonXinGiaNhapFCAdapter(Context context, int layout, List<ThanhVienDoiBongClass> listThanhVien) {
        this.context = context;
        this.layout = layout;
        this.listThanhVien = listThanhVien;
    }

    public DanhSachDonXinGiaNhapFCAdapter() {
    }

    @Override
    public int getCount() {
        return listThanhVien.size();
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
        ImageView imgThanhVien;
        TextView txtTenThanhVien;
        TextView txtSoDienThoai;
        Button btnDongY;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) context;

        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.imgThanhVien = view.findViewById(R.id.ImageViewDongDoiBatDoi);
            viewHolder.txtTenThanhVien = view.findViewById(R.id.TextViewTenDoiBongBatDoi);
            viewHolder.txtSoDienThoai = view.findViewById(R.id.TextViewSoDienThoai);
            viewHolder.btnDongY = view.findViewById(R.id.ButtonDongY);

            view.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) view.getTag();

        //Gan gia tri
        ThanhVienDoiBongClass thanhVien = listThanhVien.get(i);
        viewHolder.imgThanhVien.setImageBitmap(thanhVien.getImageDaiDien());
        viewHolder.txtTenThanhVien.setText(thanhVien.getTen());
        viewHolder.txtSoDienThoai.setText(thanhVien.getSoDienThoai());

        viewHolder.btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Click Đồng ý: " + i + "\nBắt sự kiện trong Adapter nhé", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChiTietThanhVienFragment chiTietThanhVienFragment = new ChiTietThanhVienFragment();

                Bundle bundle = new Bundle();
                ThanhVienDoiBongClass thanhVienSend = listThanhVien.get(i);
                bundle.putSerializable("thanhvien", thanhVienSend);
                chiTietThanhVienFragment.setArguments(bundle);

                Toast.makeText(context, "Bắt sự kiện trong Adapter nhé", Toast.LENGTH_SHORT).show();
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietThanhVienFragment);
            }
        });

        return view;
    }
}
