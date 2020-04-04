package com.example.doan2019;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TaiKhoanDaLoginFragment extends Fragment {
    ProfilePictureView profilePictureView;
    TextView txtName, txtEmail, txtDiaChi, txtDangXuat, txtChinhSua;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("JSON", "CHECK create view");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tai_khoan_da_login, container, false);
        Mapping();

        BatSuKienClickDangXuat();
        BatSuKienClickChinhSua();
        return view;
    }

    public void GanNoiDung(String id, String name, String email) {
        Log.d("JSON", "Gan: " + id);
        Log.d("JSON", "Gan: " + name);
        Log.d("JSON", "Gan: " + email);
        txtName.setText(name);
        txtEmail.setText(email);
        txtDiaChi.setText("Hà Nội, Việt Nam");
        profilePictureView.setProfileId(id);
    }

    private void BatSuKienClickChinhSua() {

    }

    private void BatSuKienClickDangXuat() {

    }

    public void Mapping() {
        profilePictureView = view.findViewById(R.id.ImageProfilePicture);
        txtName = view.findViewById(R.id.TextViewName);
        txtEmail = view.findViewById(R.id.TextViewEmail);
        txtDiaChi = view.findViewById(R.id.TextViewDiaChi);
        txtDangXuat = view.findViewById(R.id.TextViewDangXuat);
        txtChinhSua = view.findViewById(R.id.TextViewChinhSua);
    }
}
