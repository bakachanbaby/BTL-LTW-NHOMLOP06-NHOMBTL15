package com.example.qlvp.service;

import com.example.qlvp.mapper.DichVu_CongTyMapper;
import com.example.qlvp.model.dto.DichVu_CongTyRequestDto;
import com.example.qlvp.model.dto.DichVu_CongTyResponseDto;
import com.example.qlvp.model.entity.CongTy;
import com.example.qlvp.model.entity.DichVu;
import com.example.qlvp.model.entity.DichVu_CongTy;
import com.example.qlvp.repository.DichVu_CongTyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class DichVu_CongTyServiceIml implements DichVu_CongTyService {

    private static final Logger logger = LoggerFactory.getLogger(DichVu_CongTyServiceIml.class);

    @Autowired
    private DichVu_CongTyRepository repository;

    @Autowired
    private DichVu_CongTyMapper mapper;


    @Override
    public DichVu_CongTyResponseDto addDichVu_CongTy(DichVu_CongTyRequestDto requestDto) {
        logger.info("Thêm mới dịch vụ công ty");
        DichVu_CongTy dichVu_CongTy = mapper.requestMapToEntity(requestDto);
        DichVu_CongTy dichVu_CongTyAdded = repository.save(dichVu_CongTy);
        logger.info("Dịch vụ công ty được thêm: {}", dichVu_CongTyAdded);
        return mapper.entityMapToResponse(dichVu_CongTyAdded);
    }

    @Override
    public DichVu_CongTyResponseDto updateDichVu_CongTy(DichVu_CongTyRequestDto requestDto) {
        logger.info("sửa công ty");
        DichVu_CongTy dichVu_CongTy = mapper.requestMapToEntity(requestDto);
        DichVu_CongTy exist = repository.findById(dichVu_CongTy.getId()).orElse(null);
        if (exist != null) {
            exist = repository.saveAndFlush(dichVu_CongTy);
        }
        logger.info("sửa thành công !!!!!!!!!!!");
        return mapper.entityMapToResponse(exist);
    }

    @Override
    public boolean deleteDichVu_CongTy(int id) {
        logger.info("Delete");
        DichVu_CongTy dichVu_CongTy = repository.findById(id).orElse(null);
        if (dichVu_CongTy != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<DichVu_CongTyResponseDto> getAllDichVu_CongTy(int id) {
        logger.info("Lấy tất cả dịch vụ công ty");
        List<DichVu_CongTy> dichVu_CongTyList = repository.findByCongTy_Id(id);
        return mapper.entityMapToResponseList(dichVu_CongTyList);

    }


    @Override
    public DichVu_CongTyResponseDto getDichVu_CongTyById(int id) {
        logger.info("Lấy dịch vụ công ty theo id");
        DichVu_CongTy dichVu_CongTy = repository.findById(id).orElse(null);
        return mapper.entityMapToResponse(dichVu_CongTy);
    }

    @Override
    public void deleteAllDichVu_CongTyByCongTy(CongTy congTy) {
        logger.info("Xóa dịch vụ công ty theo công ty :{}", congTy);
        List<DichVu_CongTy> dichVu_CongTyList = repository.findByCongTy(congTy);
        if (!dichVu_CongTyList.isEmpty()) {
            repository.deleteByCongTy(congTy);
            logger.info("delete success");
        } else {
            logger.warn("Công ty này hiện không có dịch vụ.");
        }
    }

    @Override
    public void deleteAllDichVu_CongTyByDichVu(DichVu dichVu) {
        logger.info("Xóa dịch vụ công ty theo dịch vụ:{}", dichVu);
        List<DichVu_CongTy> dichVu_CongTyList = repository.findByDichVu(dichVu);
        if (!dichVu_CongTyList.isEmpty()){
            repository.deleteByDichVu(dichVu);
            logger.info("delete success");
        }
        else {
            logger.warn("Dịch vụ này hiện không có trong công ty nào.");
        }
    }

    @Override
    public void addDichVuChoCongTyMoi(DichVu_CongTy dichVu_CongTy) {
        logger.info("Thêm dịch vụ cho công ty mới");
        repository.save(dichVu_CongTy);
    }

    @Override
    public List<DichVu> getAllDichVuByCongTy(CongTy congTy) {
        List<DichVu> dichVuList = new ArrayList<>();
        List<DichVu_CongTy> dichVu_CongTyList = repository.findByCongTy(congTy);// lấy từ csdl
        return dichVuList;    }
}
