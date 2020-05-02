package com.example.doan2019;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doan2019.Retrofit.DoiBong_NguoiDung;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CacFCDangThamGiaAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DoiBong_NguoiDung> doiBongDangTGList;

    public CacFCDangThamGiaAdapter(Context context, int layout, List<DoiBong_NguoiDung> doiBongDangTGList) {
        this.context = context;
        this.layout = layout;
        this.doiBongDangTGList = doiBongDangTGList;
    }

    public CacFCDangThamGiaAdapter() {
    }

    @Override
    public int getCount() {
        return doiBongDangTGList.size();
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
        ImageView imgDoiBong;
        TextView txtTenDoiBong, txtTrangThai;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.imgDoiBong = view.findViewById(R.id.ImageViewDoiBong);
            viewHolder.txtTenDoiBong = view.findViewById(R.id.TextViewTenDoiBong);
            viewHolder.txtTrangThai = view.findViewById(R.id.TextViewTrangThai);

            view.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) view.getTag();

        DoiBong_NguoiDung doiBongDangTG = doiBongDangTGList.get(i);
        if(doiBongDangTG.getDoibong().getAnhbia()!=null){
            Picasso.get().load(doiBongDangTG.getDoibong().getAnhbia()).into(viewHolder.imgDoiBong);
        }
        Log.d("dangthamgia", doiBongDangTG.getDoibong().getTen()+ " "+ doiBongDangTG.getDoibong().getId());
        viewHolder.txtTenDoiBong.setText(doiBongDangTG.getDoibong().getTen());
        if(doiBongDangTG.getTrangthai() == 0){
            viewHolder.txtTrangThai.setText("Chờ phê duyệt từ đội bóng");
        }
        else if(doiBongDangTG.getPhanquyenId()==1){
            viewHolder.txtTrangThai.setText("Bạn là đội trưởng đội bóng");
        }
        else {
            viewHolder.txtTrangThai.setText("Bạn là thành viên đội bóng");
        }


        return view;
    }
}
