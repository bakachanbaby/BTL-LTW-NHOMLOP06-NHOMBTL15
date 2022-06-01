package com.example.qlvp.mapper;


import com.example.qlvp.mapper.NhanVienToaNhaMapper;
import com.example.qlvp.model.dto.NhanVien_DichVuRequestDto;
import com.example.qlvp.model.dto.NhanVien_DichVuResponseDto;
import com.example.qlvp.model.entity.NhanVien_DichVu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class NhanVien_DichVuMapper {

    @Autowired
    private DichVuMapper dichVuMapper;

    @Autowired
    private NhanVienToaNhaMapper nhanVienToaNhaMapper;

    public NhanVien_DichVu convertToEntity(NhanVien_DichVuRequestDto requestDto){
        NhanVien_DichVu nhanVienDichVu = null;
        if (requestDto != null){
            nhanVienDichVu = NhanVien_DichVu.builder()
                    .id(requestDto.getId())
                    .dichVu(dichVuMapper.convertToEntity(requestDto.getDichVu()))
                    .nhanVienToaNha(nhanVienToaNhaMapper.convertToEntity(requestDto.getNhanVienToaNha()))
                    .mucLuong(requestDto.getMucLuong())
                    .thangLam(requestDto.getThangLam())
                    .namLam(requestDto.getNamLam())
                    .build();
        }
        return nhanVienDichVu;
    }

    public NhanVien_DichVuResponseDto convertToResponseDto(NhanVien_DichVu nhanVienDichV){
        NhanVien_DichVuResponseDto nhanVienDichVuResponseDto = null;
        if (nhanVienDichV != null){
            nhanVienDichVuResponseDto = NhanVien_DichVuResponseDto.builder()
                    .id(nhanVienDichV.getId())
                    .mucLuong(nhanVienDichV.getMucLuong())
                    .thangLam(nhanVienDichV.getThangLam())
                    .namLam(nhanVienDichV.getNamLam())
//                    .dichVu(dichVuMapper.convertToResponseDto(nhanVienDichV.getDichVu()))
//                    .nhanVienToaNha(nhanVienToaNhaMapper.convertToResponseDto(nhanVienDichV.getNhanVienToaNha()))
                    .tenDichVu(nhanVienDichV.getDichVu().getTen())
                    .maDichVu(nhanVienDichV.getDichVu().getMa())
                    .build();
        }
        return nhanVienDichVuResponseDto;
    }

    public List<NhanVien_DichVuResponseDto> convertToResponseDtoList(List<NhanVien_DichVu> nhanVienDichVuList){
        List<NhanVien_DichVuResponseDto> responseDtoList = null;
        if (nhanVienDichVuList != null){
            responseDtoList = nhanVienDichVuList.stream().map(this::convertToResponseDto).collect(toList());
        }
        return responseDtoList;
    }

}
