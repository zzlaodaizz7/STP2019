package com.example.doan2019;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LichSuTranDauAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TranDauDuongClass> arrTranDau;

    public LichSuTranDauAdapter(Context context, int layout, List<TranDauDuongClass> arrTranDau) {
        this.context = context;
        this.layout = layout;
        this.arrTranDau = arrTranDau;
    }

    @Override
    public int getCount() {
        return arrTranDau.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    private class ViewHolder{
        TextView txtDoiThuNhat, txtDoiThuHai, txtNgayTranDauDienRa, txtGioTranDauDienRa,
        txtBanThangDoiThuNhat, txtBanThangDoiThuHai;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            viewHolder.txtDoiThuNhat = view.findViewById(R.id.TextViewDoiThuBa);
            viewHolder.txtDoiThuHai = view.findViewById(R.id.TextViewDoiThuTu);
            viewHolder.txtNgayTranDauDienRa = view.findViewById(R.id.TextViewNgayTranDauDaDienRa);
            viewHolder.txtGioTranDauDienRa = view.findViewById(R.id.TextViewGioTranDauDaDienRa);
            viewHolder.txtBanThangDoiThuNhat = view.findViewById(R.id.TextViewSoBanThangDoiMot);
            viewHolder.txtBanThangDoiThuHai = view.findViewById(R.id.TextViewSoBanThangDoiHai);

            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        TranDauDuongClass tranDau = arrTranDau.get(i);
        DoiBongClass doiThuNhat = tranDau.getDoiMinh();
        DoiBongClass doiThuHai = tranDau.getDoiBongDoiThu();

        Bitmap img = doiThuNhat.getImageDaiDien();
        Drawable d = new BitmapDrawable(img);
        d.setBounds(0, 0, 180, 180);

        viewHolder.txtDoiThuNhat.setText(doiThuNhat.getTen());
        viewHolder.txtDoiThuNhat.setCompoundDrawables(null, d, null, null);

        img = doiThuHai.getImageDaiDien();
        d = new BitmapDrawable(img);
        d.setBounds(0, 0, 180, 180);

        viewHolder.txtDoiThuHai.setText(doiThuHai.getTen());
        viewHolder.txtDoiThuHai.setCompoundDrawables(null, d, null, null);


        viewHolder.txtNgayTranDauDienRa.setText(tranDau.getNgay());
        if(tranDau.getKhungGio() == 1){
            viewHolder.txtGioTranDauDienRa.setText("17:30 - 19:00");
        }
        else if(tranDau.getKhungGio() == 2){
            viewHolder.txtGioTranDauDienRa.setText("19:00 - 20:30");
        }
        else if(tranDau.getKhungGio() == 3){
            viewHolder.txtGioTranDauDienRa.setText("20:30 - 22:00");
        }
        if (tranDau.getVoted() == 0){
            viewHolder.txtBanThangDoiThuNhat.setText("X");
            viewHolder.txtBanThangDoiThuHai.setText("X");
        }else {
            viewHolder.txtBanThangDoiThuNhat.setText(tranDau.getBanthangdoidangtin() + "");
            viewHolder.txtBanThangDoiThuHai.setText(tranDau.getBanthangdoibatdoi() + "");
        }
        return view;
    }
}
