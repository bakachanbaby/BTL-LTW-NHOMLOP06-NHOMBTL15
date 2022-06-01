package com.example.qlvp.service;

import com.example.qlvp.model.dto.CongTyRequestDto;
import com.example.qlvp.model.dto.CongTyResponseDto;
import com.example.qlvp.model.dto.HoaDonResponseDto;
import com.example.qlvp.model.entity.CongTy;

import java.util.List;

// service sẽ chứa các hàm xử lí lấy request để cuối cùng trả về response
public interface CongTyService {

    public CongTyResponseDto addCongTy(CongTyRequestDto requestDto);

    public CongTyResponseDto updateCongTy(CongTyRequestDto requestDto);

    public boolean deleteCongTy(int id);

    public List<CongTyResponseDto> getAllCongTy();

    public CongTy getById(int id);

    public CongTyResponseDto getCongTyById(int id);


}
