package com.example.doan2019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ViewMatchActivity extends AppCompatActivity {

    TextView txtTime;
    TextView txtPitch;
    TextView txtRatio;
    TextView txtState;
    TextView txtLevel;
    TextView txtTeamHost;
    TextView txtTeamGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_match);

        anhxa();
        Match match = (Match) getIntent().getSerializableExtra("matchIsChosen");
        //System.out.println(match.getId()+" "+match.getTeamHost());
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
    private void anhxa(){
        txtLevel = (TextView) findViewById(R.id.txtLevel);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtPitch = (TextView) findViewById(R.id.txtPitch);
        txtRatio = (TextView) findViewById(R.id.txtRatio);
        txtState = (TextView) findViewById(R.id.txtState);
        txtTeamHost = (TextView) findViewById(R.id.txtTeamHost);
        txtTeamGuest = (TextView) findViewById(R.id.txtTeamGuest);
    }
}
