package com.example.doan2019;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong_NguoiDung;
import com.example.doan2019.Retrofit.JsonApiDoiBongNGuoiDung;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachDonXinGiaNhapFCFragment extends Fragment {
    private View view;
    TextView txtBack;
    ListView lvDonXinGiaNhap;
    ArrayList<DoiBong_NguoiDung> arrThanhVien;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;
    DanhSachDonXinGiaNhapFCAdapter adapter;
    Dialog dialogTinNhan;
    JsonApiDoiBongNGuoiDung jsonApiDoiBongNGuoiDung;
    int doibong_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danh_sach_don_xin_gia_nhap_fc, container, false);
        jsonApiDoiBongNGuoiDung = APIUtils.getJsonApiDoiBongNguoiDung();

        Mapping();

        ClickBack();

        GanDuLieu();

        return view;
    }

    private void showDialogTinNhan(String text){
        dialogTinNhan = new Dialog(getActivity());
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

    private void GanDuLieu() {
        arrThanhVien = new ArrayList<>();

        Bundle bundle = getArguments();
        doibong_id = bundle.getInt("doibong_id");

        Log.d("gianhap", doibong_id+"");

        Call<List<DoiBong_NguoiDung>> call = jsonApiDoiBongNGuoiDung.getDanhSachThanhVien(doibong_id);
        call.enqueue(new Callback<List<DoiBong_NguoiDung>>() {
            @Override
            public void onResponse(Call<List<DoiBong_NguoiDung>> call, Response<List<DoiBong_NguoiDung>> response) {
                List<DoiBong_NguoiDung> doiBong_nguoiDungs = response.body();
                if(doiBong_nguoiDungs.size() == 0){
                    showDialogTinNhan("Không có người dùng xin gia nhập đội bóng.");
                    hideDialogTinNhan();
                    return;
                }
                for(DoiBong_NguoiDung doiBong_nguoiDung : doiBong_nguoiDungs){
                    if(doiBong_nguoiDung.getTrangthai() ==0 ){
                        arrThanhVien.add(doiBong_nguoiDung);
                    }
                    adapter = new DanhSachDonXinGiaNhapFCAdapter(getActivity(), R.layout.dong_don_xin_gia_nhap_fc, arrThanhVien);
                    lvDonXinGiaNhap.setAdapter(adapter);
                    SetListViewHeightBasedOnChildren(adapter, lvDonXinGiaNhap);
                }if(arrThanhVien.size() == 0){
                    showDialogTinNhan("Không có người dùng xin gia nhập đội bóng.");
                    hideDialogTinNhan();
                    return;
                }

            }

            @Override
            public void onFailure(Call<List<DoiBong_NguoiDung>> call, Throwable t) {
            }
        });
//        listThanhVienDoiBong = new ArrayList<>();
//        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
//        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);
//
//        long ngayTemp = 1234596789;
//        Date dateConvert = new Date(ngayTemp);
//
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn F", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn G", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn H", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn I", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
//
//        adapter = new DanhSachDonXinGiaNhapFCAdapter(getActivity(), R.layout.dong_don_xin_gia_nhap_fc, listThanhVienDoiBong);
//        lvDonXinGiaNhap.setAdapter(adapter);
//        SetListViewHeightBasedOnChildren(adapter, lvDonXinGiaNhap);
    }

    private void SetListViewHeightBasedOnChildren(DanhSachDonXinGiaNhapFCAdapter matchAdapter, ListView listView) {
        if (matchAdapter == null) {
            return;
        }
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < matchAdapter.getCount(); i++) {
            view = matchAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (matchAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private void ClickBack() {
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void Mapping() {
        arrThanhVien = new ArrayList<>();
        txtBack = view.findViewById(R.id.TextViewQuayLai);
        lvDonXinGiaNhap = view.findViewById(R.id.ListViewDonXinGiaNhap);
    }
}
