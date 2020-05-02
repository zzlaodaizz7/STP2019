package com.example.doan2019;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.JsonApiUser;
import com.example.doan2019.Retrofit.User;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinhSuaTaiKhoanDaLoginFragment extends Fragment {
    private View view;
    private LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;
    private TextView txtQuayLai, txtTen;
    private ProfilePictureView profilePictureView;
    private Button btnThayAnh, btnHoanThanhChinhSua;
    private EditText edtTen, edtEmail, edtDiaChi, edtGioiThieuBanThan;
    private ImageView imageProfilePicture2;
    private String ten, email, anhbia;
    String base_Url = "http://192.168.1.15/ApiDoAn/public/images/";
    String realPath = "";
    JsonApiUser jsonApiUser;
    User user;
    SharedPreferences sharedPreferences;
    Dialog dialogTinNhan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chinh_sua_tai_khoan_da_login, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        Mapping();
        loadUser();
        GanNoiDung();
        ClickQuayLai();
        ClickThayAnh();
        ClickHoanThanhChinhSua();
        return view;
    }

    private void loadUser(){
        Call<User> calluser = jsonApiUser.getNguoiDung(sharedPreferences.getInt("id", -1));
        calluser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
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

                ten = edtTen.getText().toString();
                email = edtEmail.getText().toString();

                user.setTen(ten);
                user.setEmail(email);

                if(ten.equals("") || email.equals("")){
                    showDialogTinNhan("Tên và Email không được để trống.");
                    hideDialogTinNhan();
                }
                else{
                    if(realPath.equals("")){

                        Call<String> call1 = jsonApiUser.update(user, sharedPreferences.getInt("id", -1));
                        call1.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                if(!email.equals("")){
                                    editor.putString("email",email);
                                }
                                if(!ten.equals("")){
                                    editor.putString("ten",ten);
                                }
                                //editor.putString("anhbia", "http://192.168.0.103/ApiDoAn/public/images/"+anhbia);
                                editor.commit();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }
                    else {
                        File file = new File(realPath);
                        String file_path = file.getAbsolutePath();
                        String[] tenFileArray = file_path.split("\\.");
                        file_path = tenFileArray[0] + System.currentTimeMillis() + "." + tenFileArray[1];
                        Log.d("path", file_path);
                        RequestBody requestBody =RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", file_path, requestBody);

                        Log.d("anhbia", body+"");

                        Call<String> call = jsonApiUser.upload(body);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                anhbia = response.body();
                                user.setAnhbia(base_Url+anhbia);
                                //Log.d("update", user.getAnhbia());
                                Call<String> call1 = jsonApiUser.update(user, sharedPreferences.getInt("id", -1));
                                call1.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        Log.d("update", "thanh cong" + response.body());
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        if(!email.equals("")){
                                            editor.putString("email",email);
                                        }
                                        if(!ten.equals("")){
                                            editor.putString("ten",ten);
                                        }
                                        editor.putString("anhbia", base_Url+anhbia);
                                        editor.commit();
                                        Toast.makeText(getActivity(), "Update tai khoan", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                    }
                                });
                                //Log.d("upload", "thanh cong"+response.body());
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("upload", "khong thanh cong"+t);
                            }
                        });
                    }
                }
            }
        });
    }

    private void ClickThayAnh() {
        btnThayAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 123);
                Toast.makeText(getActivity(), "Thay ảnh", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == 123 ) {
            Uri uri = data.getData();
            realPath = getRealPathFromURI(uri);
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageProfilePicture2.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    private void Mapping() {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        jsonApiUser = APIUtils.getJsonApiUser();
        profilePictureView = view.findViewById(R.id.ImageProfilePicture);
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
        btnThayAnh = view.findViewById(R.id.ButtonThayAnh);
        btnHoanThanhChinhSua = view.findViewById(R.id.ButtonHoanThanhChinhSua);
        edtTen = view.findViewById(R.id.EditTextTen);
        edtEmail = view.findViewById(R.id.EditTextEmail);
        edtDiaChi = view.findViewById(R.id.EditTextDiaChi);
        edtGioiThieuBanThan = view.findViewById(R.id.EditTextGioiThieuVeBan);
        imageProfilePicture2 = view.findViewById(R.id.ImageProfilePicture2);
        txtTen = view.findViewById(R.id.TextViewTen);
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
                getActivity().onBackPressed();
            }
        });
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
}
