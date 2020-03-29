package com.example.doan2019;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Date;

public class ViewListMatchActivity extends AppCompatActivity {

    ScrollView scrollView;
    ListView listViewMatch;
    ArrayList<Match> matchArrayList;
    MatchAdapter matchAdapter;
    Button btnDangTin;
    Button btnChonTrangThai;
    Button btnChonTrinhDo;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_match);

        anhxa();

        //su kien chon mot tran dau
        listViewMatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewListMatchActivity.this, ViewMatchActivity.class);
                Match match = matchArrayList.get(position);
                intent.putExtra("matchIsChosen", match);
                startActivity(intent);
            }
        });

        // dung nay ne :))))
        //su kien hien nut tim kiem va dang tin khi cuon xuong ( chua lam)
//        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                System.out.println(scrollX+" "+scrollY);
//                if(scrollY==300){
//                    hien button, an tim kiem va dang tin
//                }
//            }
//        });

        //su kien chon nut dang tin
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //su kien chon trnag thai de tim kiem tran dau
        btnChonTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuChonTrangThai();
            }
        });

        //su kien chon trinh do tim kiem tran dau
        btnChonTrinhDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuChonTrinhDo();
            }
        });

    }
    private void anhxa(){
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        btnDangTin = (Button) findViewById(R.id.btnDangtin);
        btnChonTrangThai = (Button) findViewById(R.id.btnChonTrangThai);
        btnChonTrinhDo = (Button) findViewById(R.id.btnChonTrinhDo);
        listViewMatch = (ListView) findViewById(R.id.listViewMatch);

        matchArrayList = new ArrayList<>();
        matchArrayList.add(new Match("1", "FC Red", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match("2", "FC White", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match("3", "FC 22", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match("4", "FC Apple", "Samsung", new Date(), "3 : 4",  "San Thanh Long", "Trung binh", "Da co doi thu"));
        matchArrayList.add(new Match("5", "FC Samsung", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match("6", "FC Xiaomi", "", new Date(), "3 : 4",  "San Chau Trinh Tri", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match("7", "FC Blue", "Green", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Da co doi thu"));

        matchAdapter = new MatchAdapter(this, R.layout._match, matchArrayList);
        listViewMatch.setAdapter(matchAdapter);
        setListViewHeightBasedOnChildren(matchAdapter, listViewMatch);
    }

    private void showMenuChonTrangThai(){
        PopupMenu popupMenu = new PopupMenu(this, btnChonTrangThai);
        popupMenu.getMenuInflater().inflate(R.menu.menu_trangthai, popupMenu.getMenu());
        popupMenu.show();
    }

    private void showMenuChonTrinhDo(){
        PopupMenu popupMenu = new PopupMenu(this, btnChonTrinhDo);
        popupMenu.getMenuInflater().inflate(R.menu.menu_trinhdo, popupMenu.getMenu());
        popupMenu.show();
    }

    private void setListViewHeightBasedOnChildren(MatchAdapter matchAdapter, ListView listView) {

        if (matchAdapter == null) {
            return;
        }
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < matchAdapter.getCount(); i++) {
            view = matchAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (matchAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}