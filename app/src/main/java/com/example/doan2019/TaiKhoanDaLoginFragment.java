package com.example.doan2019;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TaiKhoanDaLoginFragment extends Fragment {
    ProfilePictureView profilePictureView;
    Button btnTaoDoiBong, btnTimDoi;
    TextView txtName, txtEmail, txtDiaChi, txtDangXuat, txtChinhSua;
    private View view;
    ListView lvCacFCDangThamGia;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    ArrayList<DoiBongClass> arrFCThamGia;
    ArrayList<String> arrTenCacFCThamGia;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tai_khoan_da_login, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();

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

                Bundle bundle = new Bundle();
                DoiBongClass doiBong = arrFCThamGia.get(i);
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
        long ngayTemp = 1234596789;
        Date dateConvert = new Date(ngayTemp);

        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn F", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn G", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn H", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn I", "Thành viên", 1, anhDaiDien, "Hà Nội, Việt Nam", dateConvert));

        arrFCThamGia.add(new DoiBongClass("FC fb", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", anhBia, anhDaiDien, listThanhVienDoiBong));
        arrFCThamGia.add(new DoiBongClass("FC Linh Đàm", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));
        arrFCThamGia.add(new DoiBongClass("FC Cầu Giấy", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));
        arrFCThamGia.add(new DoiBongClass("FC Mễ Trì", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));
        arrFCThamGia.add(new DoiBongClass("FC Lê Đức Thọ", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789",anhBia, anhDaiDien, listThanhVienDoiBong));

        for(int i = 0; i < arrFCThamGia.size(); i++){
            arrTenCacFCThamGia.add(arrFCThamGia.get(i).getTen());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrTenCacFCThamGia);
        lvCacFCDangThamGia.setAdapter(arrayAdapter);
        setListViewHeightBasedOnChildren(arrayAdapter, lvCacFCDangThamGia);
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
                LoginManager.getInstance().logOut();
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(new TaiKhoanFragment());
            }
        });
    }

    public void Mapping() {
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
