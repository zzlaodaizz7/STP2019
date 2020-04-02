package com.example.doan2019;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ViewListMatchActivity extends AppCompatActivity {

    ScrollView scrollView;
    EditText edtSearch;
    ListView listViewMatch, listViewStatus, listViewLevel;
    ArrayList<Match> matchArrayList;
    MatchAdapter matchAdapter;
    Button btnCreatMatch;
    Button btnChooseStatus, btnChooseLevel;
    Dialog dialogChooseStatus, dialogChooseLevel;
    ArrayList<String> statusArrayList, levelArrayList;
    TextView txtChooseTime;
    ImageButton btnNotification;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_match);

        mapping();

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

        //su kien chon nut dang tin
        btnCreatMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewListMatchActivity.this, Activity_Dang_Tin_Tim_Doi_Thu.class);
                startActivity(intent);
            }
        });

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewListMatchActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });


        //su kien chon trnag thai de tim kiem tran dau
        clickStatus();

        //su kien chon trinh do tim kiem tran dau
        clickLevel();

        //su kien chon thoi gian
        clickChooseTime();

    }
    private void mapping(){
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        btnCreatMatch = (Button) findViewById(R.id.btnCreateMatch);
        btnChooseStatus = (Button) findViewById(R.id.btnChooseStatus);
        btnChooseLevel = (Button) findViewById(R.id.btnChooseLevel);
        listViewMatch = (ListView) findViewById(R.id.listViewMatch);
        txtChooseTime = (TextView) findViewById(R.id.txtChooseTime);
        edtSearch = (EditText) findViewById(R.id.edtxtSearch);
        btnNotification = (ImageButton) findViewById(R.id.btnNotification);

        matchArrayList = new ArrayList<>();
        matchArrayList.add(new Match(1, "FC Red", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match(2, "FC White", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match(3, "FC 22", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match(4, "FC Apple", "Samsung", new Date(), "3 : 4",  "San Thanh Long", "Trung binh", "Da co doi thu"));
        matchArrayList.add(new Match(5, "FC Samsung", "", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match(6, "FC Xiaomi", "", new Date(), "3 : 4",  "San Chau Trinh Tri", "Trung binh", "Chua co doi thu"));
        matchArrayList.add(new Match(7, "FC Blue", "Green", new Date(), "3 : 4",  "San My Dinh", "Trung binh", "Da co doi thu"));

        matchAdapter = new MatchAdapter(this, R.layout._match, matchArrayList);
        listViewMatch.setAdapter(matchAdapter);
        setListViewHeightBasedOnChildren(matchAdapter, listViewMatch);
    }

    private void clickChooseTime() {
        final Calendar calendar = Calendar.getInstance();
        final int ngay = calendar.get(Calendar.DATE);
        final int thang = calendar.get(Calendar.MONTH);
        final int nam = calendar.get(Calendar.YEAR);
        txtChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ViewListMatchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        txtChooseTime.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
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

    private void clickStatus() {
        btnChooseStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogChooseStatus();
                clickDialogChooseStatus();
            }
        });
    }

    void clickDialogChooseStatus() {
        listViewStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnChooseStatus.setText(statusArrayList.get(position));
                dialogChooseStatus.cancel();
            }
        });
    }

    private void showDialogChooseStatus() {
        dialogChooseStatus = new Dialog(this);
        dialogChooseStatus.setContentView(R.layout.dialog_choose_status);
        dialogChooseStatus.show();

        listViewStatus = dialogChooseStatus.findViewById(R.id.listViewStatus);
        statusArrayList = new ArrayList<>();

        statusArrayList.add("Chưa có đối thủ");
        statusArrayList.add("Đã có đối thủ");

        ArrayAdapter statusAdapter = new ArrayAdapter(ViewListMatchActivity.this, android.R.layout.simple_list_item_1, statusArrayList);

        listViewStatus.setAdapter(statusAdapter);
    }

    private void clickLevel() {
        btnChooseLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogChooseLevel();
                clickDialogChooseLevel();
            }
        });
    }

    void clickDialogChooseLevel() {
        listViewLevel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnChooseLevel.setText(levelArrayList.get(position));
                dialogChooseLevel.cancel();
            }
        });
    }

    private void showDialogChooseLevel() {
        dialogChooseLevel = new Dialog(this);
        dialogChooseLevel.setContentView(R.layout.dialog_choose_level);
        dialogChooseLevel.show();

        listViewLevel = dialogChooseLevel.findViewById(R.id.listViewLevel);
        levelArrayList = new ArrayList<>();

        levelArrayList.add("Trung bình");
        levelArrayList.add("Khá");

        ArrayAdapter levelAdapter = new ArrayAdapter(ViewListMatchActivity.this, android.R.layout.simple_list_item_1, levelArrayList);

        listViewLevel.setAdapter(levelAdapter);
    }

}