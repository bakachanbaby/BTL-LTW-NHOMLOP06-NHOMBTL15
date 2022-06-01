package com.example.qlvp.mapper;
import com.example.qlvp.model.dto.CongTyRequestDto;
import com.example.qlvp.model.dto.CongTyResponseDto;
import com.example.qlvp.model.entity.CongTy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import static java.util.stream.Collectors.toList;
// requestDto sang entity va entity sang responeDto / list entity sang list responseDto
@Component
public class CongTyMapper {
    private static final Logger logger=LoggerFactory.getLogger(CongTy.class);

    public CongTyResponseDto convertToResponseDto(CongTy entity){
        CongTyResponseDto responseDto=null;
        if(entity!=null){
            responseDto=CongTyResponseDto.builder()//doi tuong bang lop build len
                    .id(entity.getId())
                    .ma(entity.getMa())
                    .ten(entity.getTen())
                    .linhVucHoatDong(entity.getLinhVucHoatDong())
                    .diaChiTrongToaNha(entity.getDiaChiTrongToaNha())
                    .sdt(entity.getSdt())
                    .dienTichThue(entity.getDienTichThue())
                    .build();
        }
        return responseDto;
    }

    public List<CongTyResponseDto> convertToResponseDtoList(List<CongTy> entity){
        List<CongTyResponseDto> listResponseDto = null;
        if (entity != null){
            listResponseDto = entity.stream()
                    .map(this::convertToResponseDto).collect(toList());
            logger.info("Convert list of company to list of responseDto");
            logger.info("List of company response: {}", listResponseDto);
        }
        return listResponseDto;
    }

    public CongTy convertToEntity(CongTyRequestDto requestDto){
        CongTy entity = null;
        if (requestDto != null){
            entity = CongTy.builder()
                    .id(requestDto.getId())
                    .ma(requestDto.getMa())
                    .ten(requestDto.getTen())
                    .linhVucHoatDong(requestDto.getLinhVucHoatDong())
                    .diaChiTrongToaNha(requestDto.getDiaChiTrongToaNha())
                    .sdt(requestDto.getSdt())
                    .dienTichThue(requestDto.getDienTichThue())
                    .build();
            logger.info("từ request:{} sang entity {}", requestDto, entity);
        }
        return entity;
    }

    public CongTy convertResponseDtoToEntity(CongTyResponseDto responseDto){
        CongTy entity = null;
        if (responseDto != null){
            entity = entity.builder()
                    .id(responseDto.getId())
                    .ma(responseDto.getMa())
                    .ten(responseDto.getTen())
                    .linhVucHoatDong(responseDto.getLinhVucHoatDong())
                    .diaChiTrongToaNha(responseDto.getDiaChiTrongToaNha())
                    .sdt(responseDto.getSdt())
                    .dienTichThue(responseDto.getDienTichThue())
                    .build();

            logger.info("từ response:{} sang entity {}", responseDto, entity);
        }
        return entity;
    }
//    public CongTy convertResponseDtoToRequest(CongTyResponseDto responseDto){
//        CongTyRequestDto entity = null;
//        if (responseDto != null){
//            entity = entity.builder()
//                    .id(responseDto.getId())
//                    .ma(responseDto.getMa())
//                    .ten(responseDto.getTen())
//                    .linhVucHoatDong(responseDto.getLinhVucHoatDong())
//                    .diaChiTrongToaNha(responseDto.getDiaChiTrongToaNha())
//                    .sdt(responseDto.getSdt())
//                    .dienTichThue(responseDto.getDienTichThue())
//                    .build();
//
//            logger.info("từ response:{} sang request {}", responseDto, requestDto);
//        }
//        return entity;
//    }


}
