package com.example.qlvp.mapper;

import com.example.qlvp.mapper.CongTyMapper;
import com.example.qlvp.mapper.DichVuMapper;
import com.example.qlvp.model.dto.DichVu_CongTyRequestDto;
import com.example.qlvp.model.dto.DichVu_CongTyResponseDto;
import com.example.qlvp.model.dto.DichVuResponseDto;
import com.example.qlvp.model.entity.DichVu_CongTy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DichVu_CongTyMapper {
    private static final Logger logger = LoggerFactory.getLogger(DichVu_CongTyMapper.class);

    @Autowired
    private CongTyMapper congTyMapper;

    @Autowired
    private DichVuMapper serviceMapper;

    public DichVu_CongTy requestMapToEntity(DichVu_CongTyRequestDto requestDto) {
        DichVu_CongTy dichVu_CongTy = null;
        if (requestDto != null) {
            dichVu_CongTy = DichVu_CongTy.builder()
                    .id(requestDto.getId())
                    .congTy(congTyMapper.convertToEntity(requestDto.getCongTy()))
                    .dichVu(serviceMapper.convertToEntity(requestDto.getDichVu()))
                    .thangThue(requestDto.getThangthue())
                    .namThue(requestDto.getNamthue())
                    .build();
            logger.info("từ request:{} sang entity {}", requestDto, dichVu_CongTy);
        }
        return dichVu_CongTy;
    }

    public DichVu_CongTyResponseDto entityMapToResponse(DichVu_CongTy entity) {
        DichVu_CongTyResponseDto responseDto = null;
        if (entity != null) {
            DichVuResponseDto dichVuResponseDto = serviceMapper.convertToResponseDto(entity.getDichVu());
            responseDto = DichVu_CongTyResponseDto.builder()
                    .id(entity.getId())
                    .ma(dichVuResponseDto.getMa())
                    .ten(dichVuResponseDto.getTen())
                    .loaiDichVu(dichVuResponseDto.getLoaiDichVu())
                    .donGia(dichVuResponseDto.getDonGia())
                    .moTa(dichVuResponseDto.getMoTa())
                    .thangthue(entity.getThangThue())
                    .namthue(entity.getNamThue())
                    .build();
            logger.info("từ entity:{} sang response {}", entity, responseDto);
        }

        return responseDto;
    }

    public List<DichVu_CongTyResponseDto> entityMapToResponseList(List<DichVu_CongTy> entities) {
        List<DichVu_CongTyResponseDto> result = null;
        if (entities != null && !entities.isEmpty()) {
            result = entities.stream().map(this::entityMapToResponse).collect(Collectors.toList());
            logger.info("từ entity:{} sang list response {}", result);
        }
        return result;
    }
}
