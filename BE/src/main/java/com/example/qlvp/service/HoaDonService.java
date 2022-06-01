package com.example.qlvp.service;

import com.example.qlvp.model.dto.HoaDonResponseDto;

import java.util.List;

public interface HoaDonService {

    public List<HoaDonResponseDto> getHoaDon();
    public List<HoaDonResponseDto> getThongKeChiTietHoaDonByID(int id);


}
