package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChinhSuaTaiKhoanDaLoginFragment extends Fragment {
    private View view;
    private LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    private TextView txtQuayLai;
    private ProfilePictureView profilePictureView;
    private Button btnThayAnh, btnHoanThanhChinhSua;
    private EditText edtTen, edtEmail, edtDiaChi, edtGioiThieuBanThan;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chinh_sua_tai_khoan_da_login, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        Mapping();
        GanNoiDung();
        ClickQuayLai();
        ClickThayAnh();
        ClickHoanThanhChinhSua();
        return view;
    }

    private void ClickHoanThanhChinhSua() {
        btnHoanThanhChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),
                        edtTen.getText() + "\n" +
                        edtEmail.getText() + "\n" +
                        edtDiaChi.getText() + "\n" +
                        edtGioiThieuBanThan.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ClickThayAnh() {
        btnThayAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Thay ảnh", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Mapping() {
        profilePictureView = view.findViewById(R.id.ImageProfilePicture);
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
        btnThayAnh = view.findViewById(R.id.ButtonThayAnh);
        btnHoanThanhChinhSua = view.findViewById(R.id.ButtonHoanThanhChinhSua);
        edtTen = view.findViewById(R.id.EditTextTen);
        edtEmail = view.findViewById(R.id.EditTextEmail);
        edtDiaChi = view.findViewById(R.id.EditTextDiaChi);
        edtGioiThieuBanThan = view.findViewById(R.id.EditTextGioiThieuVeBan);
    }

    private void GanNoiDung() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (object != null) {
                    try {
                        edtTen.setText(object.getString("name"));
                        edtEmail.setText(object.getString("email"));
                        edtDiaChi.setText("Hà Nội, Việt Nam");
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
    }

    private void ClickQuayLai() {
        txtQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                langNgheSuKienChuyenFragment.ChuyenHuongFragment(new TaiKhoanDaLoginFragment());
            }
        });
    }
}
