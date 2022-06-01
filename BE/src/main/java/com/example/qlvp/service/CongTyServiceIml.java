package com.example.qlvp.service;


import com.example.qlvp.mapper.CongTyMapper;
import com.example.qlvp.model.dto.CongTyRequestDto;
import com.example.qlvp.model.dto.CongTyResponseDto;
import com.example.qlvp.model.dto.DichVuResponseDto;
import com.example.qlvp.model.dto.HoaDonResponseDto;
import com.example.qlvp.model.entity.CongTy;
import com.example.qlvp.model.entity.DichVu;
import com.example.qlvp.model.entity.DichVu_CongTy;
import com.example.qlvp.repository.CongTyRepository;

import com.example.qlvp.util.ThanhToanCongTy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CongTyServiceIml implements CongTyService {
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

    @Override
    public CongTyResponseDto addCongTy(CongTyRequestDto requestDto) {
        logger.info("Bắt đầu thêm công ty...");
        CongTy congTy = congTyMapper.convertToEntity(requestDto);
        CongTy congTyAdded = congTyRepository.save(congTy);
        logger.info("Công ty được thêm là: {}", congTyAdded);
        logger.info("finish...");

        logger.info("Thêm dịch vụ bảo vệ và vệ sinh cho công ty");

        CongTyResponseDto congTyResponseDto = congTyMapper.convertToResponseDto(congTyAdded);
        congTyResponseDto.setSoNhanVien(0);
        List<DichVu> dichVu = getListServiceOfCongTy(congTyAdded);
        double tienThueMatBang=thanhToan.tienThueMatBang(congTyAdded.getDienTichThue());
        double tongPhiDichVu =thanhToan.tongPhiDichVu(0L,dichVu);
        double tongTienThue = thanhToan.tongTienThue(tienThueMatBang,tongPhiDichVu);
        congTyResponseDto.setPhiSuDung(tongTienThue);
        return congTyResponseDto;
    }

    @Override
    public CongTyResponseDto updateCongTy(CongTyRequestDto requestDto) {
        logger.info("Bắt đầu sửa công ty...");
        CongTy congTyEntity = congTyMapper.convertToEntity(requestDto);
        CongTy congTy = congTyRepository.findById(congTyEntity.getId()).orElse(null);
        CongTy congTyUpdated = null;
        if (congTy != null) {
            congTyUpdated = congTyRepository.saveAndFlush(congTyEntity);
            logger.info("Công ty được sửa là: {}", congTyUpdated);
        } else {
            logger.error("Công ty không tìm thấy...");
        }
        logger.info("finish...");

        CongTyResponseDto congTyResponseDto = congTyMapper.convertToResponseDto(congTyUpdated);
        int soLuongNhanVien = nhanVienCongTyService.getSoNhanVienCongTyByCongTy(congTyUpdated);
        congTyResponseDto.setSoNhanVien(soLuongNhanVien);

        List<DichVu> dichVu = getListServiceOfCongTy(congTyUpdated);
        double tienThueMatBang=thanhToan.tienThueMatBang(congTyUpdated.getDienTichThue());
        double tongPhiDichVu =thanhToan.tongPhiDichVu(0L,dichVu);
        double tongTienThue = thanhToan.tongTienThue(tienThueMatBang,tongPhiDichVu);
        congTyResponseDto.setPhiSuDung(tongTienThue);
        congTyResponseDto.setPhiSuDung(tongTienThue);
        return congTyResponseDto;
    }

    @Override
    public boolean deleteCongTy(int id) {
        logger.info("Bắt đầu xóa công ty...");
        boolean resultDeleted = false;
        CongTy congTy = congTyRepository.findById(id).orElse(null);
        if (congTy != null) {
            logger.info("Xóa tất cả nhân viên của công ty trước khi xóa cong ty:");

            logger.info("cty:{}",congTy);
            nhanVienCongTyService.deleteAllNhanVienCongTyByCongTy(congTy);//xóa nhân viên
            logger.info("Xóa tất cả dịch vụ của công ty trước khi xóa công ty:");
            dichVu_CongTyService.deleteAllDichVu_CongTyByCongTy(congTy); //xóa dịch vụ mà cty đó thuê
            logger.info("Xóa cong ty: {}", congTy);
            congTyRepository.deleteById(id);
            resultDeleted = true;
        }
        return resultDeleted;
    }

    @Override
    public List<CongTyResponseDto> getAllCongTy() {
        logger.info("Bắt đầu lấy ds công ty...");
        List<CongTy> congTyList = congTyRepository.findAll();
        logger.info("Danh sách công ty: {}", congTyList);
        logger.info("finish...");

        List<CongTyResponseDto> congTyResponseList = new ArrayList<>();

        for (CongTy congTy : congTyList) {
            CongTyResponseDto congTyResponseDto = congTyMapper.convertToResponseDto(congTy);
            int soLuongNhanVien = nhanVienCongTyService.getSoNhanVienCongTyByCongTy(congTy);
            List<DichVu> dichVu = getListServiceOfCongTy(congTy);
            logger.info("Danh sách dịch vụ của công ty: {}", dichVu);
            double tienThueMatBang=thanhToan.tienThueMatBang(congTy.getDienTichThue());
            double tongPhiDichVu =thanhToan.tongPhiDichVu(0L,dichVu);
            double tongTienThue = thanhToan.tongTienThue(tienThueMatBang,tongPhiDichVu);
//            double tongTienThue = thanhToan.tongTienThue(congTy.getDienTichThue(), (long) soLuongNhanVien, dichVu);
            congTyResponseDto.setSoNhanVien(soLuongNhanVien);
            congTyResponseDto.setPhiSuDung(tongTienThue);
            congTyResponseList.add(congTyResponseDto);
        }
        return congTyResponseList;
    }

    @Override
    public CongTy getById(int id) {
        logger.info("Lấy công ty theo id công ty: {}", id);
        return congTyRepository.findById(id).orElse(null);
    }

    @Override
    public CongTyResponseDto getCongTyById(int id) {
        logger.info("Bắt đầu đọc công ty...");
        CongTy congTy = congTyRepository.findById(id).orElse(null);
        logger.info("Công ty là: {}", congTy);
        logger.info("finish...");
        CongTyResponseDto responseDto = congTyMapper.convertToResponseDto(congTy);
        if (responseDto != null) {
            int soLuongNhanVien = nhanVienCongTyService.getSoNhanVienCongTyByCongTy(congTy);
            List<DichVu> dichVu = getListServiceOfCongTy(congTy);
            assert congTy != null;
            double tongTienThue=0;
//            double tongTienThue = thanhToan.tongTienThue(congTy.getDienTichThue(), (long) soLuongNhanVien, dichVu);
            responseDto.setSoNhanVien(soLuongNhanVien);
            responseDto.setPhiSuDung(tongTienThue);
        }
        return responseDto;
    }

    private List<DichVu> getListServiceOfCongTy(CongTy congTy) {
        return dichVu_CongTyService.getAllDichVuByCongTy(congTy);
    }


}
