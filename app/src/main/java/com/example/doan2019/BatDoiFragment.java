package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.zip.Inflater;

public class BatDoiFragment extends Fragment {

    View view;
    TextView txtTime;
    TextView txtPitch;
    TextView txtRatio;
    TextView txtState;
    TextView txtLevel;
    TextView txtTeamHost;
    TextView txtTeamGuest;
    Button btnBatDoi;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bat_doi, container, false);

        mapping();

        clickBtnBatDoi();

        return view;
    }

    private void mapping(){
        txtLevel = view.findViewById(R.id.txtLevel);
        txtTime = view.findViewById(R.id.txtTime);
        txtPitch = view.findViewById(R.id.txtPitch);
        txtRatio = view.findViewById(R.id.txtRatio);
        txtState = view.findViewById(R.id.txtState);
        txtTeamHost = view.findViewById(R.id.txtTeamHost);
        txtTeamGuest = view.findViewById(R.id.txtTeamGuest);
        btnBatDoi = view.findViewById(R.id.btnBatDoi);

        bundle = getArguments();

        Match match = (Match) bundle.getSerializable("batdoi");
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm");
        String time = dateFormat.format(match.getTime());
        txtTeamHost.setText(match.getTeamHost());
        txtTeamGuest.setText(match.getTeamGuest());
        txtTime.setText(time);
        txtState.setText(match.getState());
        txtRatio.setText(match.getRatio());
        txtPitch.setText(match.getPitch());
        txtLevel.setText(match.getLevel());
    }

    // tao thong bao
    private void clickBtnBatDoi(){
        btnBatDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayThongBaoOneSignal();
            }
        });

    }
    //day thong bao voi onesignal
    private void dayThongBaoOneSignal(){
        try {
            String userId = "c148672d-8aa6-4989-9caa-0ca1c99c1686"; // id người nhận, id do oneSignal cấp cho thiết bị android ngay sau khi ứng dụng thiết lập trên thiết bị
            String headings = "Đây là tiêu đề thông báo";
            String contents = "Đây là nội dung thông báo. Bạn vừa bắt đối.";
            JSONObject notification = new JSONObject("{'contents': {'en':'"+contents+"'}, " +
                    "'include_player_ids': ['" + userId + "']," +
                    "'headings':{'en':'"+headings+"'}}");
            OneSignal.postNotification(notification, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
