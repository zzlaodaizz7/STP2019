package com.example.doan2019;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DangTin;
import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.DoiBong_NguoiDung;
import com.example.doan2019.Retrofit.JsonApiDoiBongNGuoiDung;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.common.util.JsonUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaiKhoanDaLoginFragment extends Fragment {
    ProfilePictureView profilePictureView;
    Button btnTaoDoiBong, btnTimDoi;
    TextView txtName, txtEmail, txtDiaChi, txtDangXuat, txtChinhSua, txtNoiDungCacFCDangThamGia, txtSDT;
    private View view;
    ListView lvCacFCDangThamGia, lvTranDauSapToi;
    TranDauSapToiAdapter adapterTranDauSapToi;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    ArrayList<DoiBong> arrFCThamGia;
    ArrayList<String> arrTenCacFCThamGia;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;
    ArrayList<TranDauDuongClass> arrTranDauSapToi;
    SharedPreferences sharedPreferences, sharedPreferencesOneSignal;
    DoiBongClass d1, d2;
    JsonApiSanBong jsonApiSanBong;
    JsonApiDoiBongNGuoiDung jsonApiDoiBongNGuoiDung;
    ArrayAdapter arrayAdapter;
    ArrayList<DoiBong_NguoiDung> arrDoiBongDangThamGia;
    ImageView imageProfilePicture2;
    CacFCDangThamGiaAdapter cacFCDangThamGiaAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_tai_khoan_da_login, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        jsonApiSanBong = APIUtils.getJsonApiSanBong();

        Mapping();

        GanNoiDung();

        GanNoiDungListViewTranDauSapToi();

        GanNoiDungListCacFCDangThamGia();

        ClickListViewTranDauSapToi();

        ClickTaoDoiBong();

        ClickDangXuat();

        ClickChinhSua();

        ClickTimKiemDoi();

        ClickListViewCacFCDangThamGia();

        return view;
    }

    private void ClickListViewTranDauSapToi() {
        lvTranDauSapToi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietTranDauSapToiFragment chiTietTranDauSapToiFragment = new ChiTietTranDauSapToiFragment();

                Bundle bundleTranDauSapToi = new Bundle();
                TranDauDuongClass tranDau = arrTranDauSapToi.get(i);
                bundleTranDauSapToi.putSerializable("trandauduong", tranDau);
                chiTietTranDauSapToiFragment.setArguments(bundleTranDauSapToi);
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietTranDauSapToiFragment);
            }
        });
    }

    private void GanNoiDungListViewTranDauSapToi() {
        try {
            arrTranDauSapToi = new ArrayList<>();
            Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
            Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);
            Call<List<DangTin>> call = jsonApiSanBong.getCactransapdienra(sharedPreferences.getInt("id", -1));
            call.enqueue(new Callback<List<DangTin>>() {
                @Override
                public void onResponse(Call<List<DangTin>> call, Response<List<DangTin>> response) {
                    try {
                        List<DangTin> dangTin = response.body();
                        //System.out.println("size: "+dangTin.size());
                        if (response.body() == null) return;
                        for (int i = 0; i < dangTin.size(); i++) {
                            dangTin.get(i).getDoibong1().setImageBia(anhBia);
                            dangTin.get(i).getDoibong1().setImageDaiDien(anhDaiDien);
                            dangTin.get(i).getDoibong2().setImageBia(anhBia);
                            dangTin.get(i).getDoibong2().setImageDaiDien(anhDaiDien);
                            arrTranDauSapToi.add(new TranDauDuongClass(dangTin.get(i).getId(), dangTin.get(i).getDoibong1(), dangTin.get(i).getDoibong2(), dangTin.get(i).getNgay(), dangTin.get(i).getKhunggio_id(), dangTin.get(i).getSan_id(), 0, 0, dangTin.get(i).getKeo(), 0));
                        }
                        adapterTranDauSapToi = new TranDauSapToiAdapter(getActivity(), R.layout.dong_tran_dau_sap_toi, arrTranDauSapToi);
                        lvTranDauSapToi.setAdapter(adapterTranDauSapToi);
                        SetListViewHeightBasedOnChildrenTranDauSapToi(adapterTranDauSapToi, lvTranDauSapToi);
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
        catch (Exception ex){
            Log.e("BBB", ex.toString());
        }
    }

    private void SetListViewHeightBasedOnChildrenTranDauSapToi(TranDauSapToiAdapter matchAdapter, ListView listView) {
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
        } catch (Exception ex) {
            ex.toString();
        }
    }

    private void ClickListViewCacFCDangThamGia() {
        lvCacFCDangThamGia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietDoiBongDaThamGiaFragment chiTietDoiBongDaThamGiaFragment = new ChiTietDoiBongDaThamGiaFragment();
//
                Bundle bundle = new Bundle();
                DoiBong_NguoiDung doiBong = arrDoiBongDangThamGia.get(i);
                bundle.putSerializable("doibong1", doiBong);
                chiTietDoiBongDaThamGiaFragment.setArguments(bundle);
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietDoiBongDaThamGiaFragment);
            }
        });
    }


    private void ClickTimKiemDoi() {
        btnTimDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(new TimKiemDoiBongFragment());
            }
        });
    }
    private void GanNoiDungListCacFCDangThamGia() {
        arrFCThamGia = new ArrayList<>();
        listThanhVienDoiBong = new ArrayList<>();
        arrTenCacFCThamGia = new ArrayList<>();
        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);
        //gan ten email
        txtName.setText(sharedPreferences.getString("ten", ""));
        txtEmail.setText(sharedPreferences.getString("email", ""));
        txtDiaChi.setText(sharedPreferences.getString("diachi", ""));
        txtSDT.setText(sharedPreferences.getString("sdt", ""));
//        if(sharedPreferences.getString("anhbia", "") != ""){
//            profilePictureView.setVisibility(View.INVISIBLE);
//            imageProfilePicture2.setVisibility(View.VISIBLE);
//            Picasso.get().load(APIUtils.BASE_URL+sharedPreferences.getString("anhbia", "")).into(imageProfilePicture2);
//        }
        if (!sharedPreferences.getString("anhbia", "").equals("")) {
            profilePictureView.setVisibility(View.INVISIBLE);
            imageProfilePicture2.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(APIUtils.BASE_URL+sharedPreferences.getString("anhbia", ""))
                    .rotate(0)
                    .into(imageProfilePicture2);
        }

        Call<List<DoiBong_NguoiDung>> call = jsonApiDoiBongNGuoiDung.getCacDoiDangThamGia(sharedPreferences.getInt("id", -1));
        call.enqueue(new Callback<List<DoiBong_NguoiDung>>() {
            @Override
            public void onResponse(Call<List<DoiBong_NguoiDung>> call, Response<List<DoiBong_NguoiDung>> response) {
                try {
                    List<DoiBong_NguoiDung> doiBongDangTGS = response.body();
//                System.out.println("size: " + response.body().size());
                    if (response.body() == null) return;
                    txtNoiDungCacFCDangThamGia.setVisibility(View.GONE);
                    for (DoiBong_NguoiDung doiBongDangTG : doiBongDangTGS) {
                        arrDoiBongDangThamGia.add(doiBongDangTG);
                        cacFCDangThamGiaAdapter = new CacFCDangThamGiaAdapter(getActivity(), R.layout.dong_doi_bong_dang_tham_gia, arrDoiBongDangThamGia);
                        lvCacFCDangThamGia.setAdapter(cacFCDangThamGiaAdapter);
                        setListViewHeightBasedOnChildren(cacFCDangThamGiaAdapter, lvCacFCDangThamGia);
                        Log.d("dangthamgia", doiBongDangTG.getTrangthai() + " " + doiBongDangTG.getDoibong().getId());
                    }
                }
                catch (Exception ex){
                    Log.e("BBB", ex.toString());
                }
            }

            @Override
            public void onFailure(Call<List<DoiBong_NguoiDung>> call, Throwable t) {
                System.out.println("error: " + t.getMessage());
            }
        });


//        System.out.println(arrFCThamGia.get(1).getTen())
    }

    private void setListViewHeightBasedOnChildren(CacFCDangThamGiaAdapter matchAdapter, ListView listView) {
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
            Log.d("BBB", ex.toString());
        }
    }

    private void ClickTaoDoiBong() {
        btnTaoDoiBong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaoDoiBongDialog taoDoiBongDialog = new TaoDoiBongDialog();
                taoDoiBongDialog.show(getActivity().getSupportFragmentManager(), "Dialog_Popup");
            }
        });
    }

    public void GanNoiDung() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (object != null) {
                    try {
                        txtName.setText(object.getString("name"));
                        txtEmail.setText(object.getString("email"));
                        profilePictureView.setProfileId(object.getString("id"));
                        imageProfilePicture2.setVisibility(View.INVISIBLE);
                        profilePictureView.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    private void ClickChinhSua() {
        txtChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(new ChinhSuaTaiKhoanDaLoginFragment());
            }
        });
    }

    private void ClickDangXuat() {
        txtDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("ten");
                editor.remove("email");
                editor.remove("token");
                editor.remove("id");
                editor.remove("sdt");
                editor.remove("diachi");
                editor.commit();
                editor = sharedPreferencesOneSignal.edit();
                editor.putString("changed", "true");
                editor.commit();
                Log.d("dangnhap", "changed: " + sharedPreferencesOneSignal.getString("changed", ""));
                LoginManager.getInstance().logOut();
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(new TaiKhoanFragment());
            }
        });
    }

    public void Mapping() {
        arrDoiBongDangThamGia = new ArrayList<>();
        jsonApiDoiBongNGuoiDung = APIUtils.getJsonApiDoiBongNguoiDung();
        lvTranDauSapToi = view.findViewById(R.id.ListViewTranDauSapToi);
        txtNoiDungCacFCDangThamGia = view.findViewById(R.id.TextViewNoiDungCacFCDangThamGia);
        sharedPreferencesOneSignal = getActivity().getSharedPreferences("OneSignalId", Context.MODE_PRIVATE);
        btnTimDoi = view.findViewById(R.id.ButtonTimKiemDoi);
        lvCacFCDangThamGia = view.findViewById(R.id.ListViewCacDoiThamGia);
        btnTaoDoiBong = view.findViewById(R.id.ButtonTaoDoiBong);
        profilePictureView = view.findViewById(R.id.ImageProfilePicture);
        txtName = view.findViewById(R.id.TextViewName);
        txtEmail = view.findViewById(R.id.TextViewEmail);
        txtDiaChi = view.findViewById(R.id.TextViewDiaChi);
        txtDangXuat = view.findViewById(R.id.TextViewDangXuat);
        txtChinhSua = view.findViewById(R.id.TextViewChinhSua);
        imageProfilePicture2 = view.findViewById(R.id.ImageProfilePicture2);
        txtSDT = view.findViewById(R.id.TextViewSoDienThoai);
    }
}
