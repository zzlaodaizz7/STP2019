package com.example.doan2019;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.DoiBong_NguoiDung;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachThanhVienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DoiBong_NguoiDung> arrThanhVien;
    private int quyen;
    Dialog dialogTinNhan;
    JsonApiSanBong jsonApiSanBong;

    public DanhSachThanhVienAdapter(Context context, int layout, List<DoiBong_NguoiDung> arrThanhVien, int chucVu) {
        this.context = context;
        this.layout = layout;
        this.arrThanhVien = arrThanhVien;
        this.quyen = chucVu;
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
        ImageView imgDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            ViewHolder viewHolder = new ViewHolder();
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(layout, null);

                viewHolder.imgDelete = view.findViewById(R.id.ImageViewDeleteThanhVien);
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
            if(quyen == 2)
                viewHolder.imgDelete.setVisibility(View.INVISIBLE);
            viewHolder.txtTen.setText(thanhVien.getUser().getTen());
            if (thanhVien.getTrangthai() == 0) {
                viewHolder.txtChucVu.setText("Đang chờ phê duyệt");
            } else if (thanhVien.getPhanquyenId() == 1) {
                viewHolder.txtChucVu.setText("Đội trưởng đội bóng");
                viewHolder.imgDelete.setVisibility(View.INVISIBLE);
            } else viewHolder.txtChucVu.setText("Thành viên đội bóng");
            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("");
                    builder.setMessage("Bạn có đồng ý xóa");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Xóa",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    jsonApiSanBong = APIUtils.getJsonApiSanBong();
//                                    DoiBong_NguoiDung a = new DoiBong_NguoiDung(arrThanhVien.get(i).getDoibongId(),arrThanhVien.get(i).getUserId());
                                    DoiBong_NguoiDung a = arrThanhVien.get(i);
                                    Call<DoiBong_NguoiDung> doiBong_nguoiDungCall = jsonApiSanBong.deleteThanhvien(a.getId());
                                    doiBong_nguoiDungCall.enqueue(new Callback<DoiBong_NguoiDung>() {
                                        @Override
                                        public void onResponse(Call<DoiBong_NguoiDung> call, Response<DoiBong_NguoiDung> response) {
                                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<DoiBong_NguoiDung> call, Throwable t) {
                                            //Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    });
//                                    Toast.makeText(context, arrThanhVien.get(i).getDoibongId() + "", Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(context, arrThanhVien.get(i).getUserId() + "", Toast.LENGTH_SHORT).show();
                                }
                            });
                    builder.setNegativeButton("Hủy",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
        catch (Exception ex){
            Log.e("BBB", ex.toString());
        }
        return view;
    }
}
