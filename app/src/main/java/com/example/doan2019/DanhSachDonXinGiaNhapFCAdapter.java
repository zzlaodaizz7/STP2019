package com.example.doan2019;

import android.app.Dialog;
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

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong_NguoiDung;
import com.example.doan2019.Retrofit.JsonApiDoiBongNGuoiDung;
import com.example.doan2019.Retrofit.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachDonXinGiaNhapFCAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DoiBong_NguoiDung> listThanhVien;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    JsonApiDoiBongNGuoiDung jsonApiDoiBongNGuoiDung;
    Dialog dialogTinNhan;

    public DanhSachDonXinGiaNhapFCAdapter(Context context, int layout, List<DoiBong_NguoiDung> listThanhVien) {
        jsonApiDoiBongNGuoiDung = APIUtils.getJsonApiDoiBongNguoiDung();
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
        User thanhVien = listThanhVien.get(i).getUser();
        if(thanhVien.getAnhbia() != null){
            Picasso.get().load(thanhVien.getAnhbia()).into(viewHolder.imgThanhVien);
        }

        viewHolder.txtTenThanhVien.setText(thanhVien.getTen());
        viewHolder.txtSoDienThoai.setText(thanhVien.getSdt());

        viewHolder.btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<DoiBong_NguoiDung> call = jsonApiDoiBongNGuoiDung.updateThanhVien(listThanhVien.get(i).getId());
                call.enqueue(new Callback<DoiBong_NguoiDung>() {
                    @Override
                    public void onResponse(Call<DoiBong_NguoiDung> call, Response<DoiBong_NguoiDung> response) {
                        viewHolder.btnDongY.setVisibility(View.INVISIBLE);
                        showDialogTinNhan(thanhVien.getTen()+" giờ là thành viên của đội bóng.");
                        hideDialogTinNhan();
                    }

                    @Override
                    public void onFailure(Call<DoiBong_NguoiDung> call, Throwable t) {

                    }
                });
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChiTietThanhVienFragment chiTietThanhVienFragment = new ChiTietThanhVienFragment();

                Bundle bundle = new Bundle();
                User thanhVienSend = listThanhVien.get(i).getUser();
                bundle.putSerializable("thanhvien", thanhVienSend);
                chiTietThanhVienFragment.setArguments(bundle);

                Toast.makeText(context, "Bắt sự kiện trong Adapter nhé", Toast.LENGTH_SHORT).show();
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietThanhVienFragment);
            }
        });

        return view;
    }
    private void showDialogTinNhan(String text){
        dialogTinNhan = new Dialog(context);
        dialogTinNhan.setContentView(R.layout.dialog_message);
        dialogTinNhan.show();
        TextView tvTinNhan = (TextView) dialogTinNhan.findViewById(R.id.tvTinNhan);
        tvTinNhan.setText(text);
    }
    private void hideDialogTinNhan(){
        TextView tvHuy = dialogTinNhan.findViewById(R.id.tvHuy);
        tvHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTinNhan.cancel();
            }
        });
    }
}
