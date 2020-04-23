package com.example.doan2019;

import android.content.Context;
import android.widget.ListView;

import com.example.doan2019.DTO.DangTinDTO;
import com.example.doan2019.Retrofit.DangTin;

import java.util.ArrayList;

public class DangTinService {

    public static void loadDangTin(ListView mainLV, ArrayList<DangTinDTO> dangTinDTOArrayList){
        for(int i = 0; i < mainLV.getAdapter().getCount(); i++){
            DangTinDTO dangTinDTO = (DangTinDTO) mainLV.getAdapter().getItem(i);
            dangTinDTOArrayList.add(dangTinDTO);
        }
    }
    // sau khi xoa mot dangtinDTO, lv cua main duoc cap nhat
    public static void deleteDangTin(Context context, ListView mainLV, DangTinDTO dangTinDTO){
        ArrayList<DangTinDTO> dangTinDTOArrayList = new ArrayList<>();
        loadDangTin(mainLV, dangTinDTOArrayList);
        dangTinDTOArrayList.remove(dangTinDTO);
        DangTinAdapter dangTinAdapter = new DangTinAdapter(context, R.layout._match, dangTinDTOArrayList);
        mainLV.setAdapter(dangTinAdapter);
    }
    // sau khi cap nhat mot dangtinDTO, cap nhat lai lv cua main
    public static void updateDangTin(Context context, ListView mainLV, DangTinDTO oldDT, DangTinDTO newDT){}
}
