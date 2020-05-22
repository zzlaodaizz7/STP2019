package com.example.doan2019;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.JsonApiSanBong;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachCacTinDaDangAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DangTinDuongClass> arrDangTin;
    private int idDoiBatDoi;
    JsonApiSanBong jsonApiSanBong;

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
        ViewHolder viewHolder = new ViewHolder();
        if (view == null) {
            try {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(layout, null);

                viewHolder.txtThuTuTinDang = view.findViewById(R.id.TextViewThuTuDongDangTin);
                viewHolder.txtNgayDangTin = view.findViewById(R.id.TextViewNgayDangTin);
                viewHolder.txtDoiBatDoi = view.findViewById(R.id.TextViewDoiBongBatDoi);

                view.setTag(viewHolder);
            } catch (Exception ex) {
                ex.toString();
            }
        } else
            viewHolder = (ViewHolder) view.getTag();

        //Gan gia tri
        DangTinDuongClass dangTin = arrDangTin.get(i);
        int timeId = dangTin.getKhunggio_id();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        System.out.println("AAAA"+dangTin.getNgay());
        String time = dangTin.getNgay();
        Log.e("AAA", time);
        if (timeId == 1)
            time += " 17:30 - 19:00";
        else if (timeId == 2) {
            time += " 19:00 - 20:30";
        } else if (timeId == 3) {
            time += " 20:30 - 22:00";
        }

        viewHolder.txtThuTuTinDang.setText(i + 1 + "");
        viewHolder.txtNgayDangTin.setText(time);
        if (dangTin.getDoibatdoi_id() != -1) {
            jsonApiSanBong = APIUtils.getJsonApiSanBong();
            Call<DoiBong> call = jsonApiSanBong.getChitietdoibong(dangTin.getDoibatdoi_id());
            ViewHolder finalViewHolder = viewHolder;
            call.enqueue(new Callback<DoiBong>() {
                @Override
                public void onResponse(Call<DoiBong> call, Response<DoiBong> response) {
                    try {
                        finalViewHolder.txtDoiBatDoi.setText(response.body().getTen());
                    }
                    catch (Exception ex){
                        Log.e("BBB", ex.toString());
                    }
                }

                @Override
                public void onFailure(Call<DoiBong> call, Throwable t) {

                }
            });

        } else {
            viewHolder.txtDoiBatDoi.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}
