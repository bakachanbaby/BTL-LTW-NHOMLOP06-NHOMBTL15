package com.example.qlvp.service;

import com.example.qlvp.model.dto.DichVuRequestDto;
import com.example.qlvp.model.dto.DichVuResponseDto;
import com.example.qlvp.model.entity.DichVu;

import java.util.List;

// service sẽ chứa các hàm xử lí lấy request để cuối cùng trả về response
public interface DichVuService {

    public DichVuResponseDto addDichVu(DichVuRequestDto requestDto);

    public DichVuResponseDto updateDichVu(DichVuRequestDto requestDto);

    public boolean deleteDichVu(int id);

    public List<DichVuResponseDto> getAllDichVu();

    public DichVu getByTen(String ten);

    public DichVuResponseDto getDichVuById(int id);


}
