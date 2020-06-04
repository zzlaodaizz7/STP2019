package com.example.doan2019;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.doan2019.Retrofit.APIUtils;

public class SuaIPDialog extends DialogFragment {
    private View view;
    EditText edtIP;
    TextView txtXong, txtIPHienTai;
    ImageView imgQuayLai;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_nhap_ip, container, false);
        sharedPreferences = getActivity().getSharedPreferences("ipServer", Context.MODE_PRIVATE);

        //Toast.makeText(getActivity(), APIUtils.BASE_URL, Toast.LENGTH_SHORT).show();

        Mapping();

        GanIpHienTai();

        ClickClousePopup();

        ClickXong();

        return view;
    }

    private void GanIpHienTai() {
        sharedPreferences = getActivity().getSharedPreferences("ipServer", Context.MODE_PRIVATE);
        txtIPHienTai.setText(sharedPreferences.getString("baseurl", "http://0.0.0.0/DoAn/public/"));
    }

    private void ClickXong() {
        txtXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtIP.getText().toString().equals("")){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("baseurl", "http://" + edtIP.getText().toString() + "/DoAn/public/");
                    editor.commit();
                }

                //Toast.makeText(getActivity(), APIUtils.BASE_URL, Toast.LENGTH_SHORT).show();
                Log.e("ABC", "BASE_URL: " + APIUtils.BASE_URL);
                SuaIPDialog.this.getDialog().cancel();
            }
        });
    }

    private void ClickClousePopup() {
        imgQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaIPDialog.this.getDialog().cancel();
            }
        });
    }

    private void Mapping() {
        txtIPHienTai = view.findViewById(R.id.TextViewIPICHienTai);
        edtIP = view.findViewById(R.id.EditTextIP);
        txtXong = view.findViewById(R.id.TextViewFixXongIP);
        imgQuayLai = view.findViewById(R.id.ImageViewCLosePopup);
    }
}