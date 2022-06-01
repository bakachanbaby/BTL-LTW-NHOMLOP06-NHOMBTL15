package com.example.qlvp.service;

import com.example.qlvp.model.dto.NhanVienCongTyRequestDto;
import com.example.qlvp.model.dto.NhanVienCongTyResponseDto;
import com.example.qlvp.model.entity.CongTy;
import com.example.qlvp.model.entity.NhanVienCongTy;

import java.util.List;

// service sẽ chứa các hàm xử lí lấy request để cuối cùng trả về response
public interface NhanVienCongTyService {

    public NhanVienCongTyResponseDto addNhanVienCongTy(NhanVienCongTyRequestDto requestDto);

    public NhanVienCongTyResponseDto updateNhanVienCongTy(NhanVienCongTyRequestDto requestDto);

    public boolean deleteNhanVienCongTy(int id);

    public List<NhanVienCongTyResponseDto> getAllNhanVienCongTy();

    public NhanVienCongTyResponseDto getNhanVienCongTyById(int id);

    public int getSoNhanVienCongTyByCongTy(CongTy congTy);

    public void deleteAllNhanVienCongTyByCongTy(CongTy congTy);

    public List<NhanVienCongTyResponseDto> getAllNhanVienCongTyByCongTy(int idCongTy);

}
