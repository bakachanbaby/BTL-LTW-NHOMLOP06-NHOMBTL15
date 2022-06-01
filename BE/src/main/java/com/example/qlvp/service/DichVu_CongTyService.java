package com.example.qlvp.service;

import com.example.qlvp.model.dto.DichVu_CongTyRequestDto;
import com.example.qlvp.model.dto.DichVu_CongTyResponseDto;
import com.example.qlvp.model.entity.CongTy;
import com.example.qlvp.model.entity.DichVu;
import com.example.qlvp.model.entity.DichVu_CongTy;

import java.util.List;

// service sẽ chứa các hàm xử lí lấy request để cuối cùng trả về response
public interface DichVu_CongTyService {

    public DichVu_CongTyResponseDto addDichVu_CongTy(DichVu_CongTyRequestDto requestDto);

    public DichVu_CongTyResponseDto updateDichVu_CongTy(DichVu_CongTyRequestDto requestDto);

    public boolean deleteDichVu_CongTy(int id);

    public List<DichVu_CongTyResponseDto> getAllDichVu_CongTy(int id);

    public DichVu_CongTyResponseDto getDichVu_CongTyById(int id);

    public void deleteAllDichVu_CongTyByCongTy(CongTy congTy);

    public void deleteAllDichVu_CongTyByDichVu(DichVu dichVu);

    void addDichVuChoCongTyMoi(DichVu_CongTy dichVu_CongTy);

    public List<DichVu> getAllDichVuByCongTy(CongTy congTy);

}
