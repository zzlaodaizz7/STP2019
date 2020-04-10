package com.example.doan2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class XepHangFragment extends Fragment {
    private View view;
    ListView lvxepHangDoiBong;
    ArrayList<XepHangDoiBongClass> listDoiBong;
    ArrayList<ThanhVienDoiBongClass> listThanhVienDoiBong;
    XepHangAdapter adapter;
    LangNgheSuKienChuyenFragment langNgheSuKienChuyenFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xep_hang_doi_bong, container, false);
        langNgheSuKienChuyenFragment = (LangNgheSuKienChuyenFragment) getActivity();
        AnhXa();
        KhoiTaoListView();
        ClickListview();

        return view;
    }

    private void ClickListview() {
        lvxepHangDoiBong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietDoiBongXepHangFragment chiTietDoiBongXepHangFragment = new ChiTietDoiBongXepHangFragment();

                Bundle bundle = new Bundle();
                XepHangDoiBongClass xepHangDoiBongClass = listDoiBong.get(i);
                bundle.putSerializable("doibong", xepHangDoiBongClass);
                chiTietDoiBongXepHangFragment.setArguments(bundle);

                langNgheSuKienChuyenFragment.ChuyenHuongFragment(chiTietDoiBongXepHangFragment);
            }
        });
    }

    private void KhoiTaoListView() {
        listDoiBong = new ArrayList<>();
        listThanhVienDoiBong = new ArrayList<>();

        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn A", "Đội trưởng", 1));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn B", "Cầu thủ", 2));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn C", "Cầu thủ", 3));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn D", "Cầu thủ", 4));
        listThanhVienDoiBong.add(new ThanhVienDoiBongClass("Nguyễn Văn E", "Đội phó", 5));

        listDoiBong.add(new XepHangDoiBongClass("FC fb", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", listThanhVienDoiBong));
        listDoiBong.add(new XepHangDoiBongClass("FC Linh Đàm", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", listThanhVienDoiBong));
        listDoiBong.add(new XepHangDoiBongClass("FC Cầu Giấy", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", listThanhVienDoiBong));
        listDoiBong.add(new XepHangDoiBongClass("FC Mễ Trì", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", listThanhVienDoiBong));
        listDoiBong.add(new XepHangDoiBongClass("FC Lê Đức Thọ", 3.02, "Hà Nội, Việt Nam", "Khá", "11/10/2010", "0123456789", listThanhVienDoiBong));

        adapter = new XepHangAdapter(getActivity(), R.layout.dong_xep_hang, listDoiBong);
        lvxepHangDoiBong.setAdapter(adapter);
        setListViewHeightBasedOnChildren(adapter, lvxepHangDoiBong);
    }

    private void setListViewHeightBasedOnChildren(XepHangAdapter matchAdapter, ListView listView) {

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

    private void AnhXa() {
        lvxepHangDoiBong = view.findViewById(R.id.ListViewXepHangDoiBong);
    }

}
