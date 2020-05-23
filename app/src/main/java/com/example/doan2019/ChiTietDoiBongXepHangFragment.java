package com.example.doan2019;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DangTin;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.DoiBong_NguoiDung;
import com.example.doan2019.Retrofit.HanhKiem;
import com.example.doan2019.Retrofit.JsonApiDoiBongNGuoiDung;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.example.doan2019.Retrofit.User;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDoiBongXepHangFragment extends Fragment {
    private View view;
    TextView txtTenDoiBong, txtDiem, txtDiaChi, txtTrinhDo, txtNgayThanhlap, txtPhone, txtQuayLai;
    Bundle bundle;
    Button btnThamGiaFC;
    ImageView imgAnhBia, imgDaiDien;
    ListView lvDanhSachThanhVien, lvLichSuTranDau;
    ArrayList<DoiBong_NguoiDung> arrThanhVien;
    DanhSachThanhVienAdapter adapter;
    ArrayList<TranDauDuongClass> arrLichSuTranDau;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    LichSuTranDauAdapter adapterLichSuTranDau;
    JsonApiDoiBongNGuoiDung jsonApiDoiBongNGuoiDung; JsonApiSanBong jsonApiSanBong;
    SharedPreferences sharedPreferences;
    Dialog dialogTinNhan;
    DoiBong doiBong;
    int mode, dbnd_id;
    RatingBar rtb;
    String Auth ="";
    Integer IDUser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_chi_tiet_doi_bong_xep_hang, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        jsonApiDoiBongNGuoiDung = APIUtils.getJsonApiDoiBongNguoiDung();
        jsonApiSanBong = APIUtils.getJsonApiSanBong();
        IDUser = sharedPreferences.getInt("id",0);
        Auth = sharedPreferences.getString("token","");
        Mapping();

        ClickQuayLai();

        GanDuLieu();

        GanNoiDungListViewLichSuTranDau();

        ClickListViewLichSuTranDau();

        ClickThamGiaFC();

        ClickListViewThanhVien();

        ClickSao();

        return view;
    }

    private void ClickSao() {
        rtb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rtb.getRating() == 0.5)
                    rtb.setRating(1);
                else if(rtb.getRating() == 1.5)
                    rtb.setRating(2);
                else if(rtb.getRating() == 2.5)
                    rtb.setRating(3);
                else if(rtb.getRating() == 3.5)
                    rtb.setRating(4);
                else if(rtb.getRating() == 4.5)
                    rtb.setRating(5);
//                Toast.makeText(getActivity(), "Số sao vote: " + rtb.getRating(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Thông báo");
                alertDialogBuilder.setMessage("Bạn chắc chắn về quyết định của mình chưa?");
                //null should be your on click listener
                alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HanhKiem hanhKiem = new HanhKiem(IDUser,doiBong.getId(),Math.round(rtb.getRating()));
                        Map<String,String> header = new HashMap<>();
                        header.put("value","application/json");
                        header.put("Accept","application/json");
                        header.put("Authorization","Bearer "+Auth);
                        Call<HanhKiem> call = jsonApiSanBong.postHanhKiem(header,hanhKiem);
                        call.enqueue(new Callback<HanhKiem>() {
                            @Override
                            public void onResponse(Call<HanhKiem> call, Response<HanhKiem> response) {
//                                System.out.println(response.code());
                                Toast.makeText(getContext(), "Voted", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(Call<HanhKiem> call, Throwable t) {
                                try {
                                    Toast.makeText(getContext(), "Đánh giá thành công", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception ex){
                                    Log.e("BBB", ex.toString());
                                }
                            }
                        });
                    }
                });
                alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialogBuilder.create().show();
            }
        });
    }

    private void ClickListViewLichSuTranDau() {
        lvLichSuTranDau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietLichSuTranDauFragment chiTietLichSuTranDauFragment = new ChiTietLichSuTranDauFragment();

                Bundle bundleTranDauSapToi = new Bundle();
                TranDauDuongClass tranDau = arrLichSuTranDau.get(i);
                bundleTranDauSapToi.putSerializable("trandauduong", tranDau);
                bundleTranDauSapToi.putSerializable("checkXepHangFragment", true);
                chiTietLichSuTranDauFragment.setArguments(bundleTranDauSapToi);
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietLichSuTranDauFragment);
            }
        });
    }

    private void ClickListViewThanhVien() {
        lvDanhSachThanhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietThanhVienFragment chiTietThanhVienFragment = new ChiTietThanhVienFragment();

                Bundle bundleThanhVien = new Bundle();
                User thanhVien = arrThanhVien.get(i).getUser();
                bundleThanhVien.putSerializable("thanhvien", thanhVien);
                chiTietThanhVienFragment.setArguments(bundleThanhVien);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietThanhVienFragment);
            }
        });
    }

    private void GanNoiDungListViewLichSuTranDau() {
        arrLichSuTranDau = new ArrayList<>();
        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);
        Call<List<DangTin>> call = jsonApiSanBong.getCactrandaketthuc(doiBong.getId());
        call.enqueue(new Callback<List<DangTin>>() {
            @Override
            public void onResponse(Call<List<DangTin>> call, Response<List<DangTin>> response) {
                try {
                    List<DangTin> dangTin = response.body();
                    if (response.body() == null) {
                        return;
                    }
                    if (dangTin.size() == 0) {
                        return;
                    }
                    for (int i = 0; i < dangTin.size(); i++) {
                        dangTin.get(i).getDoibong1().setImageBia(anhBia);
                        dangTin.get(i).getDoibong1().setImageDaiDien(anhDaiDien);
                        dangTin.get(i).getDoibong2().setImageBia(anhBia);
                        dangTin.get(i).getDoibong2().setImageDaiDien(anhDaiDien);
                        int a = 0, b = 0;
//                    System.out.println("Voted :"+dangTin.get(i).getVoted());
                        if (dangTin.get(i).getVoted() == 1) {
//                        System.out.println("a: "+dangTin.get(i).getBanthangdoidangtin());
                            a = dangTin.get(i).getBanthangdoidangtin();
                            b = dangTin.get(i).getBanthangdoibatdoi();
                        }
                        arrLichSuTranDau.add(new TranDauDuongClass(dangTin.get(i).getId(), dangTin.get(i).getDoibong1(), dangTin.get(i).getDoibong2(), dangTin.get(i).getNgay(), dangTin.get(i).getKhunggio_id(), dangTin.get(i).getSan_id(), a, b, dangTin.get(i).getKeo(), dangTin.get(i).getVoted()));
                    }
                    adapterLichSuTranDau = new LichSuTranDauAdapter(getActivity(), R.layout.dong_lich_su_tran_dau, arrLichSuTranDau);
                    lvLichSuTranDau.setAdapter(adapterLichSuTranDau);
                    SetListViewHeightBasedOnChildrenLichSuTranDau(adapterLichSuTranDau, lvLichSuTranDau);
                }
                catch (Exception ex){
                    Log.e("BBB", ex.toString());
                }
            }

            @Override
            public void onFailure(Call<List<DangTin>> call, Throwable t) {

            }
        });
    }

    private void SetListViewHeightBasedOnChildrenLichSuTranDau(LichSuTranDauAdapter matchAdapter, ListView listView) {
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
        totalHeight -= view.getMeasuredHeight();
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (matchAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private void ClickQuayLai() {
        txtQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void ClickThamGiaFC() {
        btnThamGiaFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("xephang", mode+"");
                if(mode==1){
                    DoiBong_NguoiDung doiBong_nguoiDung = new DoiBong_NguoiDung(doiBong.getId(), sharedPreferences.getInt("id",-1));
                    Call<DoiBong_NguoiDung> call = jsonApiDoiBongNGuoiDung.postThanhVien(doiBong_nguoiDung);
                    call.enqueue(new Callback<DoiBong_NguoiDung>() {
                        @Override
                        public void onResponse(Call<DoiBong_NguoiDung> call, Response<DoiBong_NguoiDung> response) {
                            mode = 2;
                            dbnd_id = response.body().getId();
                            showDialogTinNhan("Bạn đã xin tham gia vào đội bóng.");
                            hideDialogTinNhan();
                            btnThamGiaFC.setText("Hủy tham gia");
                        }
                        @Override
                        public void onFailure(Call<DoiBong_NguoiDung> call, Throwable t) {
                        }
                    });
                }
                else{
                    //delete
                    Call<DoiBong_NguoiDung> call = jsonApiDoiBongNGuoiDung.deleteThanhVien(dbnd_id);
                    call.enqueue(new Callback<DoiBong_NguoiDung>() {
                        @Override
                        public void onResponse(Call<DoiBong_NguoiDung> call, Response<DoiBong_NguoiDung> response) {
                            if(mode == 2){
                                showDialogTinNhan("Bạn đã hủy xin tham gia vào đội bóng.");
                            }
                            if(mode == 3){
                                showDialogTinNhan("Bạn đã rời khỏi đội bóng.");
                            }
                            mode = 1;
                            btnThamGiaFC.setText("Tham gia vào FC");
                            hideDialogTinNhan();
                        }
                        @Override
                        public void onFailure(Call<DoiBong_NguoiDung> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    private void GanDuLieu() {
        bundle = getArguments();

        doiBong = (DoiBong) bundle.getSerializable("doibong");
        if(doiBong.getAnhbia() != null){
            Picasso.get().load(APIUtils.BASE_URL+doiBong.getAnhbia()).into(imgAnhBia);
        }
        if(doiBong.getAnhdaidien() != null){
            Picasso.get().load(APIUtils.BASE_URL+doiBong.getAnhdaidien()).into(imgDaiDien);
        }

        if(doiBong.getHanhkiem() == 1)
            rtb.setRating(1);
        else if(doiBong.getHanhkiem() == 2)
            rtb.setRating(2);
        else if(doiBong.getHanhkiem() == 3)
            rtb.setRating(3);
        else if(doiBong.getHanhkiem() == 4)
            rtb.setRating(4);
        else if(doiBong.getHanhkiem() == 5)
            rtb.setRating(5);
        System.out.println(doiBong.getHanhkiem());
        txtTenDoiBong.setText(doiBong.getTen());
        txtDiem.setText(doiBong.getSodiem() + "");
        txtDiaChi.setText(doiBong.getDiachi());
        txtTrinhDo.setText(doiBong.getTrinhdo());
        String time = doiBong.getCreated_at()+"";
        time = time.substring(0, 10);
        txtNgayThanhlap.setText(time);
        txtPhone.setText(doiBong.getSdt());

        Call<List<DoiBong_NguoiDung>> call = jsonApiDoiBongNGuoiDung.getDanhSachThanhVien(doiBong.getId());
        call.enqueue(new Callback<List<DoiBong_NguoiDung>>() {
            @Override
            public void onResponse(Call<List<DoiBong_NguoiDung>> call, Response<List<DoiBong_NguoiDung>> response) {
                List<DoiBong_NguoiDung> doiBong_nguoiDungs = response.body();
                if(doiBong_nguoiDungs.size() == 0){
                    return;
                }
                for(DoiBong_NguoiDung doiBong_nguoiDung : doiBong_nguoiDungs){
                    if(mode == 1 && doiBong_nguoiDung.getUser().getId()==sharedPreferences.getInt("id",-1)
                            && doiBong_nguoiDung.getTrangthai() == 0){
                        btnThamGiaFC.setText("Hủy tham gia ");
                        dbnd_id = doiBong_nguoiDung.getId();
                        mode = 2;
                    }
                    else if(mode == 1 && doiBong_nguoiDung.getUser().getId()==sharedPreferences.getInt("id",-1)
                            && doiBong_nguoiDung.getTrangthai() == 1){
                        btnThamGiaFC.setText("Rời khỏi đội bóng");
                        dbnd_id = doiBong_nguoiDung.getId();
                        mode = 3;
                    }
                    arrThanhVien.add(doiBong_nguoiDung);
                }
                adapter = new DanhSachThanhVienAdapter(getActivity(), R.layout.dong_thanh_vien, arrThanhVien, 2);
                lvDanhSachThanhVien.setAdapter(adapter);
                SetListViewHeightBasedOnChildren(adapter, lvDanhSachThanhVien);
            }
            @Override
            public void onFailure(Call<List<DoiBong_NguoiDung>> call, Throwable t) {
            }
        });
    }

    private void SetListViewHeightBasedOnChildren(DanhSachThanhVienAdapter matchAdapter, ListView listView) {
        try {
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
        catch (Exception ex){
            Log.e("BBB", ex.toString());
        }
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

    private void Mapping() {
        rtb = view.findViewById(R.id.RatingBarDoiBongXepHang);
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        mode = 1;
        arrThanhVien = new ArrayList<>();
        lvLichSuTranDau = view.findViewById(R.id.ListViewLichSuTranDau);
        lvDanhSachThanhVien = view.findViewById(R.id.ListViewDanhSachThanhVien);
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
        imgAnhBia = view.findViewById(R.id.ImageViewBiaDoiBong);
        imgDaiDien = view.findViewById(R.id.ImageViewDaiDienDoiBong);
        btnThamGiaFC = view.findViewById(R.id.ButtonThamGiaVaoFC);
        txtTenDoiBong = view.findViewById(R.id.TextViewName);
        txtDiem = view.findViewById(R.id.TextViewDiem);
        txtDiaChi = view.findViewById(R.id.TextViewDiaChi);
        txtTrinhDo = view.findViewById(R.id.TextViewTrinhDo);
        txtNgayThanhlap = view.findViewById(R.id.TextViewNgayThanhlap);
        txtPhone = view.findViewById(R.id.TextViewPhone);
    }
}
