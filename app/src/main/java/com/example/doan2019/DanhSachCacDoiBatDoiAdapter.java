package com.example.doan2019;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DangTin;
import com.example.doan2019.Retrofit.JsonApiSanBong;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DanhSachCacDoiBatDoiAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<DoiBongClass> doiBongList;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    Retrofit retrofit;
    JsonApiSanBong jsonApiSanBong;
    SharedPreferences sharedPreferences;
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
        ViewHolder viewHolder = new ViewHolder();
        if (view == null) {
            try {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(layout, null);

                viewHolder.imgDoiBong = view.findViewById(R.id.ImageViewDongDoiBatDoi);
                viewHolder.txtTen = view.findViewById(R.id.TextViewTenDoiBongBatDoi);
                viewHolder.txtDiem = view.findViewById(R.id.TextViewDiemDoiBongBatDoi);
                viewHolder.btnDelete = view.findViewById(R.id.ButtonDongYBatDoi);

                view.setTag(viewHolder);
            }
            catch (Exception ex){
                Log.e("BBB", ex.toString());
            }
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
                jsonApiSanBong = APIUtils.getJsonApiSanBong();
                Map<String,String> header = new HashMap<>();
                header.put("value","application/json");
                header.put("Accept","application/json");
                sharedPreferences = view.getContext().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
//                System.out.println(sharedPreferences.getString("token",""));
                header.put("Authorization","Bearer "+sharedPreferences.getString("token",""));
//                System.out.println();
                Call<DangTin> call = jsonApiSanBong.chotkeo(header,doiBongList.get(i).getBatdoi_id());
                call.enqueue(new Callback<DangTin>() {
                    @Override
                    public void onResponse(Call<DangTin> call, Response<DangTin> response) {
                        Toast.makeText(view.getContext(), response.body().getContent(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<DangTin> call, Throwable t) {

                    }
                });
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
