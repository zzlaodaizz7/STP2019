package com.example.doan2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HelloActivity extends AppCompatActivity {
    ImageView imgGif, img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        Mapping();

        DatTime();
    }

    private void DatTime() {
        Thread bamgio = new Thread(){
            public void run(){
                try{
                    sleep(3400);
                    imgGif.setVisibility(View.INVISIBLE);
                }
                catch (Exception ex){

                }
                finally {
                    startActivity(new Intent(HelloActivity.this, MainActivity.class));
                }
            }
        };
        bamgio.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void Mapping() {
        imgGif = findViewById(R.id.ImageViewGif);
        img = findViewById(R.id.ImageView);
    }
}
