package com.example.doan2019;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan2019.Retrofit.APIUtils;
import com.example.doan2019.Retrofit.DoiBong;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ChinhSuaThongTinDoiBongFragment extends Fragment {
    private View view;
    private ArrayList<String> levelArrayList;
    private ImageView imgDaiDien, imgBia;
    private Button btnThayAnhDaiDien, btnThayAnhBia, btnLuu, btnTrinhDo;
    private TextView txtQuayLai;
    private EditText edtTen, edtDiaChi, edtSoDienThoai;
    private int idDoiBong;
    private Bundle bundle;
    private DoiBong doibong;
    private ListView listViewTrinhDo;
    private Dialog dialogChonTrinhDo;
    private int REQUEST_CODE_ANH_BIA = 123;
    private int REQUEST_CODE_ANH_DAI_DIEN = 456;
    private String realPath = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chinh_sua_thong_tin_doi_bong, container, false);

        Mapping();

        GanNoiDungDoiBong();

        ClickQuayLai();

        ClickTrinhDo();

        ClickThayAnhBia();

        ClickThayAnhDaiDien();

        return view;
    }

    private void ClickThayAnhDaiDien() {
        btnThayAnhDaiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_ANH_DAI_DIEN);
            }
        });
    }

    private void ClickThayAnhBia() {
        btnThayAnhBia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_ANH_BIA);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == REQUEST_CODE_ANH_BIA ) {
            try {
                Uri uri = data.getData();
                realPath = getRealPathFromURI(uri);

                int rotateImage = getCameraPhotoOrientation(getActivity(), uri, realPath);

                try {
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imgBia.setImageBitmap(bitmap);
                    imgBia.setRotation(rotateImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception ex){
                Log.e("BBB", ex.toString());
            }
        }
        else if(requestCode == REQUEST_CODE_ANH_DAI_DIEN){
            try {
                Uri uri = data.getData();
                realPath = getRealPathFromURI(uri);

                int rotateImage = getCameraPhotoOrientation(getActivity(), uri, realPath);

                try {
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imgDaiDien.setImageBitmap(bitmap);
                    imgDaiDien.setRotation(rotateImage);
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

    private void ClickTrinhDo() {
        btnTrinhDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogChonTrinhDo();
                ClickDialogChonTrinhDo();
            }
        });
    }

    private void ShowDialogChonTrinhDo() {
        dialogChonTrinhDo = new Dialog(getContext());
        dialogChonTrinhDo.setContentView(R.layout.dialog_chon_trinh_do);
        dialogChonTrinhDo.show();

        listViewTrinhDo = dialogChonTrinhDo.findViewById(R.id.listViewTrinhDo);
        levelArrayList = new ArrayList<>();

        levelArrayList.add("Trung bình");
        levelArrayList.add("Khá");

        ArrayAdapter levelAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, levelArrayList);
        listViewTrinhDo.setAdapter(levelAdapter);
    }

    void ClickDialogChonTrinhDo() {
        listViewTrinhDo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnTrinhDo.setText(levelArrayList.get(position));
                dialogChonTrinhDo.cancel();
            }
        });
    }

    private void ClickQuayLai() {
        txtQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void GanNoiDungDoiBong() {
        try{
            bundle = getArguments();
            doibong = (DoiBong) bundle.getSerializable("doibong");
            idDoiBong = doibong.getId();

            if (doibong.getAnhdaidien() != null) {
                Picasso.get().load(APIUtils.BASE_URL+doibong.getAnhdaidien()).into(imgDaiDien);
            }
            if (doibong.getAnhbia() != null) {
                Picasso.get().load(APIUtils.BASE_URL+doibong.getAnhbia()).into(imgBia);
            }
            edtTen.setText(doibong.getTen());
            edtDiaChi.setText(doibong.getDiachi());
            edtSoDienThoai.setText(doibong.getSdt());
            btnTrinhDo.setText(doibong.getTrinhdo());
        }catch (Exception ex){
            Log.e("BBB", ex.toString());
        }
    }

    private void Mapping() {
        imgBia = view.findViewById(R.id.ImageViewBiaDoiBong);
        imgDaiDien = view.findViewById(R.id.ImageViewDaiDienDoiBong);
        edtTen = view.findViewById(R.id.EditTextTen);
        edtDiaChi = view.findViewById(R.id.EditTextDiaChi);
        edtSoDienThoai = view.findViewById(R.id.EditTextSoDienThoai);
        btnLuu = view.findViewById(R.id.ButtonSuaXongThongTinDoiBong);
        btnThayAnhDaiDien = view.findViewById(R.id.ButtonThayAnhDaiDien);
        btnThayAnhBia = view.findViewById(R.id.ButtonThayAnhBia);
        btnTrinhDo = view.findViewById(R.id.ButtonTrinhDoDoiBong);
        txtQuayLai = view.findViewById(R.id.TextViewQuayLai);
    }
}
