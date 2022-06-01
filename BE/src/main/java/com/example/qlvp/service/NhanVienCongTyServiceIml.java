package com.example.qlvp.service;

import com.example.qlvp.mapper.NhanVienCongTyMapper;
import com.example.qlvp.model.dto.NhanVienCongTyRequestDto;
import com.example.qlvp.model.dto.NhanVienCongTyResponseDto;
import com.example.qlvp.model.entity.CongTy;
import com.example.qlvp.model.entity.NhanVienCongTy;
import com.example.qlvp.repository.NhanVienCongTyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NhanVienCongTyServiceIml implements NhanVienCongTyService{

    private static final Logger logger = LoggerFactory.getLogger(NhanVienCongTyServiceIml.class);

    @Autowired
    private NhanVienCongTyRepository nhanVienCongTyRepository;

    @Autowired
    private NhanVienCongTyMapper nhanVienCongTyMapper;

    @Autowired
    private CongTyService CongTyService;

    @Override
    public NhanVienCongTyResponseDto addNhanVienCongTy(NhanVienCongTyRequestDto requestDto) {
        logger.info("Thêm nhân viên công ty cho công ty");
        NhanVienCongTy nhanVienCongTy = nhanVienCongTyMapper.convertToEntity(requestDto);
        NhanVienCongTy nhanVienCongTyAdded = nhanVienCongTyRepository.save(nhanVienCongTy);
        logger.info("thành công");
        return nhanVienCongTyMapper.convertToResponseDto(nhanVienCongTyAdded);
    }

    @Override
    public NhanVienCongTyResponseDto updateNhanVienCongTy(NhanVienCongTyRequestDto requestDto) {
        logger.info("Update nhân viên công ty cho công ty");
        NhanVienCongTy nhanVienCongTy = nhanVienCongTyMapper.convertToEntity(requestDto);
        NhanVienCongTy existNhanVienCongTy = nhanVienCongTyRepository.findById(nhanVienCongTy.getId()).orElse(null);
        if (existNhanVienCongTy != null){
            existNhanVienCongTy = nhanVienCongTyRepository.saveAndFlush(nhanVienCongTy);
        }
        logger.info("kết thúc");
        return nhanVienCongTyMapper.convertToResponseDto(existNhanVienCongTy);
    }

    @Override
    public boolean deleteNhanVienCongTy(int id) {
        logger.info("Xóa nhân viên công ty cho công ty: {}", id);
        boolean result = false;
        NhanVienCongTy nhanVienCongTy = nhanVienCongTyRepository.findById(id).orElse(null);
        if (nhanVienCongTy != null){
            nhanVienCongTyRepository.deleteById(id);
            result = true;
        }
        return result;    }

    @Override
    public List<NhanVienCongTyResponseDto> getAllNhanVienCongTy() {
        logger.info("Lấy danh sách nhân viên công ty");
        List<NhanVienCongTy> nhanVienCongTyList = nhanVienCongTyRepository.findAll();
        logger.info("kết thúc ...");
        return nhanVienCongTyMapper.convertToResponseDtoList(nhanVienCongTyList);
    }

    @Override
    public NhanVienCongTyResponseDto getNhanVienCongTyById(int id) {
        logger.info("Lấy ra nhân viên công ty theo id");
        NhanVienCongTy nhanVienCongTy = nhanVienCongTyRepository.findById(id).orElse(null);
        logger.info("kết thúc ...");
        return nhanVienCongTyMapper.convertToResponseDto(nhanVienCongTy);
    }

    @Override
    public int getSoNhanVienCongTyByCongTy(CongTy congTy) {
        logger.info("Số nhân viên công ty trong công ty");
        int soNhanVienCongTy = nhanVienCongTyRepository.countByCongTy(congTy);
        logger.info("kết thúc...");
        return soNhanVienCongTy;    }

    @Override
    public void deleteAllNhanVienCongTyByCongTy(CongTy congTy) {
        logger.info("delete all:");
        nhanVienCongTyRepository.deleteByCongTy(congTy);
        logger.info("kết thúc ....");
    }

    @Override
    public List<NhanVienCongTyResponseDto> getAllNhanVienCongTyByCongTy(int idCongTy) {
        logger.info("Lấy tất cả nhân viên công ty theo công ty");
        List<NhanVienCongTy> nhanVienCongTyList = new ArrayList<>();
        CongTy congTy = CongTyService.getById(idCongTy);
        if (congTy != null){
            logger.info("CongTy ko trống");
            nhanVienCongTyList = nhanVienCongTyRepository.findByCongTy(congTy);
            logger.info("danh sách : {}", nhanVienCongTyList);
        }
        return nhanVienCongTyMapper.convertToResponseDtoList(nhanVienCongTyList);
    }
}
