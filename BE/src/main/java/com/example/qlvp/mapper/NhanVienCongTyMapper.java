package com.example.qlvp.mapper;

import com.example.qlvp.model.dto.NhanVienCongTyRequestDto;
import com.example.qlvp.model.dto.NhanVienCongTyResponseDto;
import com.example.qlvp.model.entity.NhanVienCongTy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class NhanVienCongTyMapper {

    private static final Logger logger = LoggerFactory.getLogger(NhanVienCongTyMapper.class);

    @Autowired
    private CongTyMapper congTyMapper;

    public NhanVienCongTy convertToEntity(NhanVienCongTyRequestDto nhanVienCongTyRequestDto){
        NhanVienCongTy nhanVienCongTy = null;
        if (nhanVienCongTyRequestDto != null){
            nhanVienCongTy = NhanVienCongTy.builder()
                    .id(nhanVienCongTyRequestDto.getId())
                    .ma(nhanVienCongTyRequestDto.getMa())
                    .ten(nhanVienCongTyRequestDto.getTen())
                    .cccd(nhanVienCongTyRequestDto.getCccd())
                    .sdt(nhanVienCongTyRequestDto.getSdt())
                    .ngaySinh(nhanVienCongTyRequestDto.getNgaySinh())
                    .congTy(congTyMapper.convertToEntity(nhanVienCongTyRequestDto.getCongTy()))
                    .build();
        }
        logger.info("NhanVienCongTy: {}", nhanVienCongTy);
        return nhanVienCongTy;
    }

    public NhanVienCongTyResponseDto convertToResponseDto(NhanVienCongTy nhanVienCongTy){
        NhanVienCongTyResponseDto responseDto = null;
        if (nhanVienCongTy != null){
            responseDto = NhanVienCongTyResponseDto.builder()
                    .id(nhanVienCongTy.getId())
                    .ma(nhanVienCongTy.getMa())
                    .ten(nhanVienCongTy.getTen())
                    .cccd(nhanVienCongTy.getCccd())
                    .ngaySinh(nhanVienCongTy.getNgaySinh())
                    .sdt(nhanVienCongTy.getSdt())
                    .build();
        }
        return responseDto;
    }

    public List<NhanVienCongTyResponseDto> convertToResponseDtoList(List<NhanVienCongTy> nhanVienCongTyList){
        if (nhanVienCongTyList != null){
            return nhanVienCongTyList.stream().map(this::convertToResponseDto).collect(toList());
        }
        return null;
    }

}
