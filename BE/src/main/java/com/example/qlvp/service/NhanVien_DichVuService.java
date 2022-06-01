package com.example.qlvp.service;

import com.example.qlvp.model.dto.DichVuRequestDto;
import com.example.qlvp.model.dto.NhanVienToaNhaRequestDto;
import com.example.qlvp.model.dto.NhanVien_DichVuRequestDto;
import com.example.qlvp.model.dto.NhanVien_DichVuResponseDto;
import com.example.qlvp.model.entity.DichVu;
import com.example.qlvp.model.entity.NhanVienToaNha;
import com.example.qlvp.model.entity.NhanVien_DichVu;

import java.util.List;

// service sẽ chứa các hàm xử lí lấy request để cuối cùng trả về response
public interface NhanVien_DichVuService {

    public NhanVien_DichVuResponseDto addNhanVien_DichVu(NhanVien_DichVuRequestDto requestDto);

    public NhanVien_DichVuResponseDto updateNhanVien_DichVu(NhanVien_DichVuRequestDto requestDto);

    public boolean deleteNhanVien_DichVu(int id);

    public void deleteAllNhanVien_DichVuByNhanVien(NhanVienToaNha NhanVienToaNha);

    public void deleteAllNhanVien_DichVuByDichVu(DichVu dichVu);

    public List<NhanVien_DichVuResponseDto> getAllNhanVienDichVuByDichVu(DichVuRequestDto requestDto);
    public List<NhanVien_DichVuResponseDto> getAllNhanVienDichVu( );
    public List<NhanVien_DichVuResponseDto> getAllNhanVienDichVuByNhanVienToaNha_Id(int id );

    public List<NhanVien_DichVuResponseDto> getAllDichVuByNhanVienToaNha(NhanVienToaNhaRequestDto requestDto);

}
