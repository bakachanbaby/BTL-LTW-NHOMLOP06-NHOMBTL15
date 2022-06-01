package com.example.qlvp.service;

import com.example.qlvp.mapper.CongTyMapper;
import com.example.qlvp.model.dto.HoaDonResponseDto;
import com.example.qlvp.model.dto.TienLuongResponseDto;
import com.example.qlvp.model.entity.CongTy;
import com.example.qlvp.model.entity.DichVu;
import com.example.qlvp.model.entity.DichVu_CongTy;
import com.example.qlvp.model.entity.NhanVien_DichVu;
import com.example.qlvp.repository.CongTyRepository;
import com.example.qlvp.repository.DichVu_CongTyRepository;
import com.example.qlvp.util.ThanhToanCongTy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HoaDonServiceIml implements HoaDonService {

    private static final Logger logger = LoggerFactory.getLogger(CongTyServiceIml.class);

    @Autowired
    private CongTyRepository congTyRepository;

    @Autowired
    private CongTyMapper congTyMapper;

    @Autowired
    private NhanVienCongTyService nhanVienCongTyService;

    @Autowired
    private DichVu_CongTyService dichVu_CongTyService;

    @Autowired
    private ThanhToanCongTy thanhToan;

    @Autowired
    private DichVuService dichVuService;
    @Autowired
    private DichVu_CongTyRepository dichVu_congTyRepository;
//    @Override
//    public List<DichVu> getAllDichVuByCongTy(CongTy congTy) {
//        List<DichVu> dichVuList = new ArrayList<>();
//        List<DichVu_CongTy> dichVu_CongTyList = repository.findByCongTy(congTy);// lấy từ csdl
//        for (DichVu_CongTy dichVu_CongTy : dichVu_CongTyList){
//            dichVuList.add(dichVu_CongTy.getDichVu());
//        }
//        return dichVuList;    }
    private List<DichVu> getListServiceOfCongTy(CongTy congTy) {
        List<DichVu> dichVuList = new ArrayList<>();
        List<DichVu_CongTy> dichVu_CongTyList = dichVu_congTyRepository.findByCongTy(congTy);// lấy từ csdl
        for (DichVu_CongTy dichVu_CongTy : dichVu_CongTyList){
            dichVuList.add(dichVu_CongTy.getDichVu());
        }
        return dichVuList;
    }
    @Override
    public List<HoaDonResponseDto> getHoaDon() {
        logger.info("Bắt đầu lấy ds công ty...");
        List<CongTy> congTyList = congTyRepository.findAll();

        List<HoaDonResponseDto> hoaDonResponseDtoList = new ArrayList<>();
        for (CongTy congTy : congTyList) {
            HoaDonResponseDto hoaDonResponseDto=null;
            int soLuongNhanVien = nhanVienCongTyService.getSoNhanVienCongTyByCongTy(congTy);
            List<DichVu> dichVu = getListServiceOfCongTy(congTy);
            double tienThueMatBang=thanhToan.tienThueMatBang(congTy.getDienTichThue());
            double tongPhiDichVu =thanhToan.tongPhiDichVu(0L,dichVu);
            double tongTienThue = thanhToan.tongTienThue(tienThueMatBang,tongPhiDichVu);


            if(congTy!=null){
                hoaDonResponseDto=HoaDonResponseDto.builder()//doi tuong bang lop build len
                        .id(congTy.getId())
                        .maCongTy(congTy.getMa())
                        .tenCongty(congTy.getTen())
                        .tienThueMatBang(tienThueMatBang)
                        .tongPhiDichVu(tongPhiDichVu)
                        .tongTienThue(tongTienThue)
                        .thang(1)
                        .build();
            }
            hoaDonResponseDtoList.add(hoaDonResponseDto);

        }
        return hoaDonResponseDtoList;
    }


    public List<HoaDonResponseDto> getThongKeChiTietHoaDonByID(int id){
        List<HoaDonResponseDto> hoaDonResponseDtoList = new ArrayList<>();
        CongTy congTy = null;
        congTy = congTyRepository.getOne(id);


        List<DichVu_CongTy> dichVu_congTyList =
                dichVu_congTyRepository.findByCongTy_Id(id);

        for (int i=1;i<=12;i++){
            List<DichVu> dichVuList = new ArrayList<>();
            HoaDonResponseDto hoaDonResponseDto=null;
            for (DichVu_CongTy dichVu_CongTy : dichVu_congTyList){
                if(dichVu_CongTy.getThangThue()==i)
                    dichVuList.add(dichVu_CongTy.getDichVu());
            }
            double tienThueMatBang=thanhToan.tienThueMatBang(congTy.getDienTichThue());
            double tongPhiDichVu =thanhToan.tongPhiDichVu(0L,dichVuList);
            double tongTienThue = thanhToan.tongTienThue(tienThueMatBang,tongPhiDichVu);

            hoaDonResponseDto=HoaDonResponseDto.builder()//doi tuong bang lop build len
                        .tienThueMatBang(tienThueMatBang)
                        .tongPhiDichVu(tongPhiDichVu)
                        .tongTienThue(tongTienThue)
                        .thang(i)
                        .build();
            hoaDonResponseDtoList.add(hoaDonResponseDto);

        }
        return hoaDonResponseDtoList;

    }

}
