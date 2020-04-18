package com.example.doan2019.Mapper;

import com.example.doan2019.DTO.DangTinDTO;
import com.example.doan2019.Retrofit.DangTin;

public class ModelMapper {
    public static DangTinDTO toDangTinDTO(DangTin dangTin, String doidangtin_ten, int doitruongdoidangtin_id, String doibatdoi_ten, int doitruongdoibatdoi_id, String trangthai, String trinhdo, String san_ten, String khunggio_thoigian){
        DangTinDTO dangTinDTO = new DangTinDTO(dangTin.getId(), dangTin.getDoidangtin_id(), doidangtin_ten, doitruongdoidangtin_id, trangthai, trinhdo, dangTin.getDoibatdoi_id(), doitruongdoibatdoi_id, doibatdoi_ten,
                dangTin.getNgay(), dangTin.getKeo(), dangTin.getSan_id(), san_ten, dangTin.getKhunggio_id(), khunggio_thoigian,
                dangTin.getCreated_at(), dangTin.getUpdated_at());
        return dangTinDTO;
    }
}
