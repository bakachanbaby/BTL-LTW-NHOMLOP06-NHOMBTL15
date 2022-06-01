package com.example.qlvp.service;

import com.example.qlvp.model.dto.HoaDonResponseDto;
import com.example.qlvp.model.dto.NhanVienToaNhaRequestDto;
import com.example.qlvp.model.dto.NhanVienToaNhaResponseDto;
import com.example.qlvp.model.entity.NhanVienToaNha;

import java.text.ParseException;
import java.util.List;

// service sẽ chứa các hàm xử lí lấy request để cuối cùng trả về response
public interface NhanVienToaNhaService {

    public NhanVienToaNhaResponseDto addNhanVienToaNha(NhanVienToaNhaRequestDto requestDto);

    public NhanVienToaNhaResponseDto updateNhanVienToaNha(NhanVienToaNhaRequestDto requestDto);

    public boolean deleteNhanVienToaNha(int id);

    public List<NhanVienToaNhaResponseDto> getAllNhanVienToaNha() throws ParseException;

    public NhanVienToaNhaResponseDto getNhanVienToaNhaById(int id);

}
