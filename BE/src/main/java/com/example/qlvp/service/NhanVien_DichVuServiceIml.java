package com.example.qlvp.service;

import com.example.qlvp.mapper.DichVuMapper;
import com.example.qlvp.mapper.NhanVienToaNhaMapper;
import com.example.qlvp.mapper.NhanVien_DichVuMapper;
import com.example.qlvp.model.dto.*;
import com.example.qlvp.model.entity.CongTy;
import com.example.qlvp.model.entity.DichVu;
import com.example.qlvp.model.entity.NhanVienToaNha;
import com.example.qlvp.model.entity.NhanVien_DichVu;
import com.example.qlvp.repository.NhanVien_DichVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NhanVien_DichVuServiceIml implements NhanVien_DichVuService {

    private static final Logger logger = LoggerFactory.getLogger(NhanVien_DichVuServiceIml.class);

    @Autowired
    private NhanVien_DichVuRepository nhanVien_DichVuRepository;

    @Autowired
    private NhanVienToaNhaMapper nhanVienToaNhaMapper;

    @Autowired
    private DichVuMapper dichVuMapper;

    @Autowired
    private NhanVien_DichVuMapper nhanVien_DichVuMapper;


    @Override
    public NhanVien_DichVuResponseDto addNhanVien_DichVu(NhanVien_DichVuRequestDto requestDto) {
        logger.info("Thêm mới nhân viên dịch vụ...");
        logger.info("request: {}", requestDto);
        NhanVien_DichVu nhanVien_DichVu = nhanVien_DichVuMapper.convertToEntity(requestDto);
        NhanVien_DichVu nhanVien_DichVuAdded = nhanVien_DichVuRepository.save(nhanVien_DichVu);
        logger.info("Nhân viên dịch vụ được thêm: {}", nhanVien_DichVuAdded);
        logger.info("finish ....");
        return nhanVien_DichVuMapper.convertToResponseDto(nhanVien_DichVuAdded);
    }

    @Override
    public NhanVien_DichVuResponseDto updateNhanVien_DichVu(NhanVien_DichVuRequestDto requestDto) {
        logger.info("Cập nhật nhân viên dịch vụ....");
        logger.info("Request: {}", requestDto);
        NhanVien_DichVu nhanVien_DichVu = nhanVien_DichVuMapper.convertToEntity(requestDto);
        NhanVien_DichVu nhanVien_DichVuIsExisted = nhanVien_DichVuRepository.findById(nhanVien_DichVu.getId()).orElse(null);
        if (nhanVien_DichVuIsExisted != null) {
            NhanVien_DichVu nhanVien_DichVuUpdated = nhanVien_DichVuRepository.saveAndFlush(nhanVien_DichVu);
            logger.info("Update trên db thành công ....");
            return nhanVien_DichVuMapper.convertToResponseDto(nhanVien_DichVuUpdated);
        } else {
            logger.error("finish ....");
            return null;
        }
    }

    @Override
    public boolean deleteNhanVien_DichVu(int id) {
        boolean resultOfDelete = false;
        logger.info("Xóa nhân viên dịch vụ....");
        NhanVien_DichVu nhanVien_DichVuIsExisted = nhanVien_DichVuRepository.findById(id).orElse(null);
        if (nhanVien_DichVuIsExisted != null) {
            nhanVien_DichVuRepository.deleteById(id);
            logger.info("Xóa nhân viên dịch vụ thành công");
            resultOfDelete = true;
        } else {
            logger.error("Không tìm thấy nhân viên dịch vụ");
        }
        logger.info("kết thúc ....");
        return resultOfDelete;
    }

    @Override
    public void deleteAllNhanVien_DichVuByNhanVien(NhanVienToaNha NhanVienToaNha) {
        logger.info("Xóa nhân viên dịch vụ theo nhân viên tòa nhà: {}", NhanVienToaNha);
        List<NhanVien_DichVu> nhanVien_DichVuListIsExisted = nhanVien_DichVuRepository.findByNhanVienToaNha(NhanVienToaNha);
        if (!nhanVien_DichVuListIsExisted.isEmpty()) {
            nhanVien_DichVuRepository.deleteByNhanVienToaNha(NhanVienToaNha);
            logger.info("Xóa thành công");
        } else {
            logger.warn("Nhân viên này không theo nhân viên tòa nhà nào.");
        }
    }

    @Override
    public void deleteAllNhanVien_DichVuByDichVu(DichVu dichVu) {
        logger.info("Xóa nhân viên dịch vụ theo dịch vụ: {}", dichVu);
        List<NhanVien_DichVu> nhanVien_DichVuListIsExist = nhanVien_DichVuRepository.findByDichVu(dichVu);
        if (!nhanVien_DichVuListIsExist.isEmpty()) {
            nhanVien_DichVuRepository.deleteByDichVu(dichVu);
            logger.info("Xóa thành công");
        } else {
            logger.warn("Nhân viên này không theo dịch vụ nào.");
        }
    }

    @Override
    public List<NhanVien_DichVuResponseDto> getAllNhanVienDichVuByDichVu(DichVuRequestDto requestDto) {
        logger.info("Tìm tất cả nhân viên dịch vụ có dịch vụ:{}", requestDto);
        DichVu dichVu = dichVuMapper.convertToEntity(requestDto);
        List<NhanVien_DichVu> nhanVien_DichVuListIsRead = nhanVien_DichVuRepository.findByDichVu(dichVu);
        logger.info("finish ....");
        return nhanVien_DichVuMapper.convertToResponseDtoList(nhanVien_DichVuListIsRead);
    }

    @Override
    public List<NhanVien_DichVuResponseDto> getAllNhanVienDichVu() {
        logger.info("Bắt đầu lấy ds công ty...");
        List<NhanVien_DichVu> nhanVienDichVuList = nhanVien_DichVuRepository.findAll();
        logger.info("Danh sách công ty: {}", nhanVienDichVuList);
        logger.info("kết thúc...");

        List<NhanVien_DichVuResponseDto> nhanVien_dichVuResponseDtoList = new ArrayList<>();

        for (NhanVien_DichVu nhanVien_dichVu : nhanVienDichVuList) {
            NhanVien_DichVuResponseDto nhanVien_dichVuResponseDto = nhanVien_DichVuMapper.convertToResponseDto(nhanVien_dichVu);
            nhanVien_dichVuResponseDtoList.add(nhanVien_dichVuResponseDto);
        }
        return nhanVien_dichVuResponseDtoList;
    }

    @Override
    public List<NhanVien_DichVuResponseDto> getAllNhanVienDichVuByNhanVienToaNha_Id(int id) {
        List<NhanVien_DichVu> nhanVien_DichVuListIsRead = nhanVien_DichVuRepository.findByNhanVienToaNha_Id(id);
        return nhanVien_DichVuMapper.convertToResponseDtoList(nhanVien_DichVuListIsRead);
    }

    @Override
    public List<NhanVien_DichVuResponseDto> getAllDichVuByNhanVienToaNha(NhanVienToaNhaRequestDto requestDto) {
        logger.info("Tìm tất cả nhân viên dịch vụ có nhân viên tòa nhà: {}", requestDto);
        NhanVienToaNha nhanVienToaNha = nhanVienToaNhaMapper.convertToEntity(requestDto);
        List<NhanVien_DichVu> nhanVien_DichVuListIsRead = nhanVien_DichVuRepository.findByNhanVienToaNha(nhanVienToaNha);
        logger.info("finish ....");
        return nhanVien_DichVuMapper.convertToResponseDtoList(nhanVien_DichVuListIsRead);
    }
}
