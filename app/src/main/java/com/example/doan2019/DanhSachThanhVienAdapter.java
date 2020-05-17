package com.example.doan2019;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.DoiBong_NguoiDung;
import com.example.doan2019.Retrofit.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DanhSachThanhVienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DoiBong_NguoiDung> arrThanhVien;

    public DanhSachThanhVienAdapter(Context context, int layout, List<DoiBong_NguoiDung> arrThanhVien) {
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
        try {
            ViewHolder viewHolder = new ViewHolder();
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(layout, null);

                viewHolder.imgDaiDien = view.findViewById(R.id.ImageViewDaiDienThanhVienDoiBong);
                viewHolder.txtTen = view.findViewById(R.id.TextViewTenThanhVien);
                viewHolder.txtChucVu = view.findViewById(R.id.TextViewChucVu);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            DoiBong_NguoiDung thanhVien = arrThanhVien.get(i);
            Log.d("danhsach", thanhVien + "");
            Log.d("danhsach", thanhVien.getUser().getId() + " " + thanhVien.getUser().getAnhbia());
            if (thanhVien.getUser().getAnhbia() != null) {
                Picasso.get().load(APIUtils.BASE_URL+thanhVien.getUser().getAnhbia()).into(viewHolder.imgDaiDien);
            }
            viewHolder.txtTen.setText(thanhVien.getUser().getTen());
            if (thanhVien.getTrangthai() == 0) {
                viewHolder.txtChucVu.setText("Đang chờ phê duyệt");
            } else if (thanhVien.getPhanquyenId() == 1) {
                viewHolder.txtChucVu.setText("Đội trưởng đội bóng");
            } else viewHolder.txtChucVu.setText("Thành viên đội bóng");

        }
        catch (Exception ex){
            Log.e("BBB", ex.toString());
        }
        return view;
    }
}
