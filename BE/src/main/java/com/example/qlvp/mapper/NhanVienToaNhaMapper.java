package com.example.qlvp.mapper;

import com.example.qlvp.model.dto.NhanVienToaNhaRequestDto;
import com.example.qlvp.model.dto.NhanVienToaNhaResponseDto;
import com.example.qlvp.model.entity.NhanVienToaNha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

// requestDto sang entity va entity sang responeDto / list entity sang list responseDto
@Component
public class NhanVienToaNhaMapper {
    private static final Logger logger = LoggerFactory.getLogger(NhanVienToaNha.class);

    public NhanVienToaNhaResponseDto convertToResponseDto(NhanVienToaNha entity) {
        NhanVienToaNhaResponseDto responseDto = null;
        if (entity != null) {
            responseDto = NhanVienToaNhaResponseDto.builder()//doi tuong bang lop build len
                    .id(entity.getId())
                    .ma(entity.getMa())
                    .ten(entity.getTen())
                    .cccd(entity.getCccd())
                    .ngaySinh(entity.getNgaySinh())
                    .sdt(entity.getSdt())
                    .viTri(entity.getViTri())
                    .build();
        }
        return responseDto;
    }

    public List<NhanVienToaNhaResponseDto> aconvertToResponseDtoList(List<NhanVienToaNha> entity) {
        List<NhanVienToaNhaResponseDto> listResponseDto = null;
        if (entity != null) {
            listResponseDto = entity.stream()
                    .map(this::convertToResponseDto).collect(toList());
            logger.info("từ list entity:{} sang list response {} {}", listResponseDto);
        }
        return listResponseDto;
    }

    public NhanVienToaNha convertToEntity(NhanVienToaNhaRequestDto requestDto) {//convertRequestDtoToEntity
        NhanVienToaNha entity = null;
        if (requestDto != null) {
            entity = NhanVienToaNha.builder()
                    .id(requestDto.getId())
                    .ma(requestDto.getMa())
                    .ten(requestDto.getTen())
                    .cccd(requestDto.getCccd())
                    .ngaySinh((Date)requestDto.getNgaySinh())
                    .sdt(requestDto.getSdt())
                    .viTri(requestDto.getViTri())
                    .build();
            logger.info("từ response:{} sang list entity {}", requestDto, entity);
        }
        return entity;
    }


}
