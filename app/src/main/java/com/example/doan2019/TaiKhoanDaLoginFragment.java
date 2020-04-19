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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan2019.Retrofit.DoiBong;
import com.example.doan2019.Retrofit.JsonApiSanBong;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.common.util.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

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
    TextView txtName, txtEmail, txtDiaChi, txtDangXuat, txtChinhSua;
    private View view;
    ListView lvCacFCDangThamGia;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    ArrayList<DoiBong> arrFCThamGia;
    ArrayList<String> arrTenCacFCThamGia;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;
    SharedPreferences sharedPreferences, sharedPreferencesOneSignal;
    Retrofit retrofit;
    JsonApiSanBong jsonApiSanBong;
    ArrayAdapter arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_tai_khoan_da_login, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4/DoAn/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonApiSanBong = retrofit.create(JsonApiSanBong.class);
        Mapping();

        GanNoiDung();
        GanNoiDungListCacFCDangThamGia();

        ClickTaoDoiBong();

        ClickDangXuat();

        ClickChinhSua();

        ClickTimKiemDoi();

        ClickListViewCacFCDangThamGia();

        return view;
    }

    private void ClickListViewCacFCDangThamGia() {
        lvCacFCDangThamGia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietDoiBongDaThamGiaFragment chiTietDoiBongDaThamGiaFragment = new ChiTietDoiBongDaThamGiaFragment();
//
                Bundle bundle = new Bundle();
                DoiBong doiBong = arrFCThamGia.get(i);
                bundle.putSerializable("doibong", doiBong);
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

        Call<List<DoiBong>> call = jsonApiSanBong.getCacdoidathamgias(sharedPreferences.getInt("id",1));
        call.enqueue(new Callback<List<DoiBong>>() {
            @Override
            public void onResponse(Call<List<DoiBong>> call, Response<List<DoiBong>> response) {
                List<DoiBong> doiBongs = response.body();
                for (DoiBong doiBong : doiBongs){
                    System.out.println("id doi da tham gia: "+doiBong.getId() + doiBong.getTen());
                    arrFCThamGia.add(new DoiBong(doiBong.getTen(),doiBong.getTrinhdo(),doiBong.getDiachi(),doiBong.getSdt(),anhBia,anhDaiDien,doiBong.getSodiem(),0,doiBong.getCreated_at()));
                    arrTenCacFCThamGia.add(doiBong.getTen());
                }
                listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Đội trưởng", 1));
                listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Cầu thủ", 2));
                listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Cầu thủ", 3));
                listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Cầu thủ", 4));
                listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Đội phó", 5));
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
    }
}
