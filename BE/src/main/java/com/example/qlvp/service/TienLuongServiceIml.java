package com.example.qlvp.service;


import com.example.qlvp.model.dto.TienLuongResponseDto;
import com.example.qlvp.model.entity.NhanVienToaNha;
import com.example.qlvp.model.entity.NhanVien_DichVu;
import com.example.qlvp.repository.NhanVienToaNhaRepository;
import com.example.qlvp.repository.NhanVien_DichVuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class TienLuongServiceIml implements TienLuongService {
    private static final Logger logger = LoggerFactory.getLogger(NhanVienToaNhaServiceIml.class);
    @Autowired
    private NhanVienToaNhaRepository nhanVienToaNhaRepository;
    @Autowired
    private NhanVien_DichVuRepository nhanVien_DichVuRepository;


    @Override
    public List<TienLuongResponseDto> getThongKeTienLuong() {
        logger.info("Bắt đầu lấy ds công ty...");
        List<NhanVienToaNha> nhanVienToaNhaList = nhanVienToaNhaRepository.findAll();

        List<TienLuongResponseDto> tienLuongResponseDtoList = new ArrayList<>();
        for (NhanVienToaNha nhanVienToaNha : nhanVienToaNhaList) {
            TienLuongResponseDto tienLuongResponseDto=null;
            List<NhanVien_DichVu> nhanVien_DichVuList =
                    nhanVien_DichVuRepository.findByNhanVienToaNha_Id(nhanVienToaNha.getId());
            double tongTienLuong=0;
            for (NhanVien_DichVu i: nhanVien_DichVuList ) {
                tongTienLuong+= i.getMucLuong();
            }

//            if(nhanVienToaNha!=null){
                tienLuongResponseDto=TienLuongResponseDto.builder()//doi tuong bang lop build len
                        .id(nhanVienToaNha.getId())
                        .maNhanVien(nhanVienToaNha.getMa())
                        .tenNhanVien(nhanVienToaNha.getTen())
                        .viTri(nhanVienToaNha.getViTri())
                        .tongTienLuong(tongTienLuong)
                        .thang(0)
                        .build();
//            }
            tienLuongResponseDtoList.add(tienLuongResponseDto);

        }
        return tienLuongResponseDtoList;
    }

    @Override
    public List<TienLuongResponseDto> getThongKeChiTietTienLuongByID(int id) {
        List<TienLuongResponseDto> tienLuongResponseDtoList = new ArrayList<>();

        List<NhanVien_DichVu> nhanVien_DichVuList =
                nhanVien_DichVuRepository.findByNhanVienToaNha_Id(id);
        for (int i=1;i<=12;i++){
            TienLuongResponseDto tienLuongResponseDto=null;
            double tongTienLuong=0;
            for (NhanVien_DichVu nhanVien_dichVu: nhanVien_DichVuList ) {
                if(nhanVien_dichVu.getThangLam()==i)
                    tongTienLuong+= nhanVien_dichVu.getMucLuong();
            }
            tienLuongResponseDto=TienLuongResponseDto.builder()//doi tuong bang lop build len
                    .tongTienLuong(tongTienLuong)
                    .thang(i)
                    .build();
            tienLuongResponseDtoList.add(tienLuongResponseDto);

        }
        return tienLuongResponseDtoList;
    }


}
