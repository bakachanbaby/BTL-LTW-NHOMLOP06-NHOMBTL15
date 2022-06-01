package com.example.qlvp.mapper;
import com.example.qlvp.model.dto.DichVuRequestDto;
import com.example.qlvp.model.dto.DichVuResponseDto;
import com.example.qlvp.model.entity.DichVu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import static java.util.stream.Collectors.toList;
// requestDto sang entity va entity sang responeDto / list entity sang list responseDto
@Component
public class DichVuMapper {
    private static final Logger logger=LoggerFactory.getLogger(DichVu.class);

    public DichVuResponseDto convertToResponseDto(DichVu entity){
        DichVuResponseDto responseDto=null;
        if(entity!=null){
            responseDto=DichVuResponseDto.builder()//doi tuong bang lop build len
                    .id(entity.getId())
                    .ma(entity.getMa())
                    .ten(entity.getTen())
                    .loaiDichVu(entity.getLoaiDichVu())
                    .donGia(entity.getDonGia())
                    .moTa(entity.getMoTa())

                    .build();
        }
        return responseDto;
    }

    public List<DichVuResponseDto> convertToResponseDtoList(List<DichVu> entity){
        List<DichVuResponseDto> listResponseDto = null;
        if (entity != null){
            listResponseDto = entity.stream()
                    .map(this::convertToResponseDto).collect(toList());
            logger.info("từ entity sang list response  {}", listResponseDto);
        }
        return listResponseDto;
    }

    public DichVu convertToEntity(DichVuRequestDto requestDto){//convertRequestDtoToEntity
        DichVu entity = null;
        if (requestDto != null){
            entity = DichVu.builder()
                    .id(requestDto.getId())
                    .ma(requestDto.getMa())
                    .ten(requestDto.getTen())
                    .loaiDichVu(requestDto.getLoaiDichVu())
                    .donGia(requestDto.getDonGia())
                    .moTa(requestDto.getMoTa())
                    .build();
            logger.info("từ request:{} sang entity {}", requestDto, entity);
        }
        return entity;
    }

    public DichVu convertResponseDtoToEntity(DichVuResponseDto responseDto){
        DichVu entity = null;
        if (responseDto != null){
            entity = entity.builder()
                    .id(responseDto.getId())
                    .ma(responseDto.getMa())
                    .ten(responseDto.getTen())
                    .loaiDichVu(responseDto.getLoaiDichVu())
                    .donGia(responseDto.getDonGia())
                    .moTa(responseDto.getMoTa())
                    .build();
            logger.info("từ response:{} sang entity {}", responseDto, entity);
        }
        return entity;
    }



}
