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
import android.media.ExifInterface;
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
import com.squareup.picasso.Picasso;

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
    private TextView txtQuayLai, txtTen;
    private ProfilePictureView profilePictureView;
    private Button btnThayAnh, btnHoanThanhChinhSua;
    private EditText edtTen, edtEmail, edtDiaChi, edtGioiThieuBanThan, edtMatKhau, edtNhapLaiMatKhau, edtSDT;
    private ImageView imageProfilePicture2;
    private String ten, email, anhbia, matKhauMoi, nhapLaiMatKhauMoi, diachi, sdt;
    String base_Url = "/images/";
    String realPath = "";
    JsonApiUser jsonApiUser;
    User user;
    SharedPreferences sharedPreferences;
    Dialog dialogTinNhan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chinh_sua_tai_khoan_da_login, container, false);

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
                try {
                    user = response.body();
                    if(user.getAnhbia() != null){
                        Picasso.get().load(APIUtils.BASE_URL+user.getAnhbia()).into(imageProfilePicture2);
                    }
                    edtTen.setText(user.getTen());
                    edtEmail.setText(user.getEmail());
                    if (!user.getDiachi().equals("------"))
                        edtDiaChi.setText(user.getDiachi());
                    if(user.getSdt() != null){
                        edtSDT.setText(user.getSdt());
                    }
                }
                catch (Exception ex){
                    Log.e("BBB", ex.toString());
                }
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
                ten = edtTen.getText().toString();
                email = edtEmail.getText().toString();
                diachi = edtDiaChi.getText().toString();
                sdt = edtSDT.getText().toString();
                matKhauMoi = edtMatKhau.getText().toString();
                nhapLaiMatKhauMoi = edtNhapLaiMatKhau.getText().toString();

                user.setTen(ten);
                user.setEmail(email);
                user.setDiachi(diachi);
                user.setSdt(sdt);

                if(ten.equals("") || email.equals("")){
                    showDialogTinNhan("Tên, Email");
                    hideDialogTinNhan();
                }
                else{
                    if(!matKhauMoi.equals("") || !nhapLaiMatKhauMoi.equals("")){
                        if(!matKhauMoi.equals(nhapLaiMatKhauMoi)){
                            showDialogTinNhan("Mật khẩu nhập lại chưa đúng");
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
                                        if(!diachi.equals("")){
                                            editor.putString("diachi", diachi);
                                        }
                                        if(!sdt.equals("")){
                                            editor.putString("sdt", sdt);
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
                                        Log.e("LinkAnh", base_Url+anhbia);
                                        user.setAnhbia(base_Url+anhbia);
                                        //Log.d("update", user.getAnhbia());
                                        Call<String> call1 = jsonApiUser.update(user, sharedPreferences.getInt("id", -1));
                                        call1.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {
                                                Log.d("update", "thanh cong" + response.body());
                                                Toast.makeText(getActivity(), "Update thành công", Toast.LENGTH_SHORT).show();
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                if(!email.equals("")){
                                                    editor.putString("email",email);
                                                }
                                                if(!ten.equals("")){
                                                    editor.putString("ten",ten);
                                                }
                                                if(!diachi.equals("")){
                                                    editor.putString("diachi", diachi);
                                                }
                                                if(!sdt.equals("")){
                                                    editor.putString("sdt", sdt);
                                                }
                                                editor.putString("anhbia", base_Url+anhbia);
                                                editor.commit();
                                                try {
                                                    //Toast.makeText(getActivity(), "Update tai khoan", Toast.LENGTH_LONG).show();
                                                }
                                                catch (Exception ex){
                                                    Log.e("BBB", ex.toString());
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {
                                                Log.e("Lỗi", t.getMessage());
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
                    else{
                        //Toast.makeText(getActivity(), "Không đổi mật khẩu", Toast.LENGTH_SHORT).show();
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
                                    if(!diachi.equals("")){
                                        editor.putString("diachi", diachi);
                                    }
                                    if(!sdt.equals("")){
                                        editor.putString("sdt", sdt);
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
                                    Log.e("LinkAnh", base_Url+anhbia);
                                    user.setAnhbia(base_Url+anhbia);
                                    //Log.d("update", user.getAnhbia());
                                    Call<String> call1 = jsonApiUser.update(user, sharedPreferences.getInt("id", -1));
                                    call1.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            Toast.makeText(getActivity(), "Update thành công", Toast.LENGTH_SHORT).show();
                                            Log.d("update", "thanh cong" + response.body());
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            if(!email.equals("")){
                                                editor.putString("email",email);
                                            }
                                            if(!ten.equals("")){
                                                editor.putString("ten",ten);
                                            }
                                            if(!diachi.equals("")){
                                                editor.putString("diachi", diachi);
                                            }
                                            if(!sdt.equals("")){
                                                editor.putString("sdt", sdt);
                                            }
                                            editor.putString("anhbia", base_Url+anhbia);
                                            editor.commit();
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
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == 123 ) {
            try {
                Uri uri = data.getData();
                realPath = getRealPathFromURI(uri);

                int rotateImage = getCameraPhotoOrientation(getActivity(), uri, realPath);

                try {
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageProfilePicture2.setImageBitmap(bitmap);
                    imageProfilePicture2.setRotation(rotateImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception ex){
                Log.e("BBB", ex.toString());
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

    public int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath){
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

            Log.i("RotateImage", "Exif orientation: " + orientation);
            Log.i("RotateImage", "Rotate value: " + rotate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

    private void Mapping() {
        edtSDT = view.findViewById(R.id.EditTextSoDienThoai);
        edtMatKhau = view.findViewById(R.id.EditTextMatKhauMoi);
        edtNhapLaiMatKhau = view.findViewById(R.id.EditTextNhapLaiMatKhauMoi);
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
