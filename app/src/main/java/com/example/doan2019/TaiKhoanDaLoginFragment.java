package com.example.doan2019;

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
    TextView txtName, txtEmail, txtDiaChi, txtDangXuat, txtChinhSua, txtNoiDungCacFCDangThamGia;
    private View view;
    ListView lvCacFCDangThamGia, lvTranDauSapToi;
    TranDauSapToiAdapter adapterTranDauSapToi;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    ArrayList<DoiBong> arrFCThamGia;
    ArrayList<String> arrTenCacFCThamGia;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;
    ArrayList<TranDauDuongClass> arrTranDauSapToi;
    SharedPreferences sharedPreferences, sharedPreferencesOneSignal;
    DoiBongClass d1,d2;
    JsonApiSanBong jsonApiSanBong;
    ArrayAdapter arrayAdapter;
    ImageView imageProfilePicture2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_tai_khoan_da_login, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

        jsonApiSanBong = APIUtils.getJsonApiSanBong();

        Mapping();

        GanNoiDung();

        GanNoiDungListCacFCDangThamGia();

        GanNoiDungListViewTranDauSapToi();

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
        arrTranDauSapToi = new ArrayList<>();

        Bitmap anhDaiDien = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        Bitmap anhBia = BitmapFactory.decodeResource(getResources(), R.drawable.anh_test_doi_bong);
        Call<List<DangTin>> call = jsonApiSanBong.getCactransapdienra(sharedPreferences.getInt("id",-1));
        call.enqueue(new Callback<List<DangTin>>() {
            @Override
            public void onResponse(Call<List<DangTin>> call, Response<List<DangTin>> response) {
                List<DangTin> dangTin = response.body();
                System.out.println("size: "+dangTin.size());
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

            @Override
            public void onFailure(Call<List<DangTin>> call, Throwable t) {

            }
        });
    }

    private void SetListViewHeightBasedOnChildrenTranDauSapToi(TranDauSapToiAdapter matchAdapter, ListView listView) {
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

    private void ClickListViewCacFCDangThamGia() {
        lvCacFCDangThamGia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietDoiBongDaThamGiaFragment chiTietDoiBongDaThamGiaFragment = new ChiTietDoiBongDaThamGiaFragment();
//
                Bundle bundle = new Bundle();
                DoiBong doiBong = arrFCThamGia.get(i);
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
        txtName.setText(sharedPreferences.getString("ten",""));
        txtEmail.setText(sharedPreferences.getString("email",""));
        Log.d("anhbia", "anh bia" + sharedPreferences.getString("anhbia",""));
        if(!sharedPreferences.getString("anhbia","").equals("")){
            Picasso.get().load(sharedPreferences.getString("anhbia","")).into(imageProfilePicture2);
        }
        long ngayTemp = 1234596789;
        Date dateConvert = new Date(ngayTemp);
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn F", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn G", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn H", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn I", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));

        Call<List<DoiBong>> call = jsonApiSanBong.getCacdoidathamgias(sharedPreferences.getInt("id",1));
        call.enqueue(new Callback<List<DoiBong>>() {
            @Override
            public void onResponse(Call<List<DoiBong>> call, Response<List<DoiBong>> response) {
                List<DoiBong> doiBongs = response.body();
                for (DoiBong doiBong : doiBongs){
                    arrFCThamGia.add(new DoiBong(doiBong.getId(), doiBong.getTen(),doiBong.getTrinhdo(),doiBong.getDiachi(),doiBong.getSdt(),anhBia,anhDaiDien,doiBong.getSodiem(),0,doiBong.getCreated_at(), doiBong.getUpdated_at()));
                    arrTenCacFCThamGia.add(doiBong.getTen());
                }
                listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
                listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
                listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
                listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert, "0123456789"));
                System.out.println(arrTenCacFCThamGia.size());
                if (getActivity()!=null){
                    arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrTenCacFCThamGia);
                    lvCacFCDangThamGia.setAdapter(arrayAdapter);
                    setListViewHeightBasedOnChildren(arrayAdapter, lvCacFCDangThamGia);
                }

            }
            @Override
            public void onFailure(Call<List<DoiBong>> call, Throwable t) {
                System.out.println("loi: "+ t.getMessage());
            }
        });




//        System.out.println(arrFCThamGia.get(1).getTen())
    }

    private void setListViewHeightBasedOnChildren(ArrayAdapter matchAdapter, ListView listView) {

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
        txtDiaChi.setText("Hà Nội, Việt Nam");
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
                editor.commit();
                editor = sharedPreferencesOneSignal.edit();
                editor.putString("changed", "true");
                editor.commit();
                Log.d("dangnhap", "changed: "+sharedPreferencesOneSignal.getString("changed", ""));
                LoginManager.getInstance().logOut();
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(new TaiKhoanFragment());
            }
        });
    }

    public void Mapping() {
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
    }
}
