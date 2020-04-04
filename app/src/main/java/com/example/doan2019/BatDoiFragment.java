package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bat_doi, container, false);

        mapping();

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
}
