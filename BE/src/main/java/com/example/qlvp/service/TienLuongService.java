package com.example.qlvp.service;

import com.example.qlvp.model.dto.HoaDonResponseDto;
import com.example.qlvp.model.dto.TienLuongResponseDto;

import java.util.List;

public interface TienLuongService {
    public List<TienLuongResponseDto> getThongKeTienLuong();

    public List<TienLuongResponseDto> getThongKeChiTietTienLuongByID(int id);


}
