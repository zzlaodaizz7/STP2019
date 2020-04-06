package com.example.doan2019;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TaiKhoanDaLoginFragment extends Fragment {
    ProfilePictureView profilePictureView;
    Button btnTaoDoiBong;
    TextView txtName, txtEmail, txtDiaChi, txtDangXuat, txtChinhSua;
    private View view;
    LangNgheSuKienTuFragmentDangNhapDenActivity langNgheSuKienTuFragmentDangNhapDenActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tai_khoan_da_login, container, false);
        langNgheSuKienTuFragmentDangNhapDenActivity = (LangNgheSuKienTuFragmentDangNhapDenActivity) getActivity();
        Mapping();
        GanNoiDung();
        ClickTaoDoiBong();
        ClickDangXuat();
        ClickChinhSua();
        return view;
    }

    private void ClickTaoDoiBong() {
        btnTaoDoiBong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Tạo đội bóng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void GanNoiDung() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if(object != null) {
                    try {
                        txtName.setText(object.getString("name"));
                        txtEmail.setText(object.getString("email"));
                        profilePictureView.setProfileId(object.getString("id"));
                        Log.d("JSON", "Gan: " + object.getString("id"));
                        Log.d("JSON", "Gan: " + object.getString("name"));
                        Log.d("JSON", "Gan: " + object.getString("email"));
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

    }

    private void ClickDangXuat() {
        txtDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                langNgheSuKienTuFragmentDangNhapDenActivity.ChuyenHuongTuChuaDangNhapSangDaDangNhapVaNguocLai();
            }
        });
    }

    public void Mapping() {
        btnTaoDoiBong = view.findViewById(R.id.ButtonTaoDoiBong);
        profilePictureView = view.findViewById(R.id.ImageProfilePicture);
        txtName = view.findViewById(R.id.TextViewName);
        txtEmail = view.findViewById(R.id.TextViewEmail);
        txtDiaChi = view.findViewById(R.id.TextViewDiaChi);
        txtDangXuat = view.findViewById(R.id.TextViewDangXuat);
        txtChinhSua = view.findViewById(R.id.TextViewChinhSua);
    }
}
