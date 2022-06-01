package com.example.qlvp.service;

import com.example.qlvp.mapper.DichVuMapper;
import com.example.qlvp.model.dto.DichVuRequestDto;
import com.example.qlvp.model.dto.DichVuResponseDto;
import com.example.qlvp.model.entity.DichVu;
import com.example.qlvp.repository.DichVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
@Transactional
public class DichVuServiceIml implements DichVuService {

    private static final Logger logger = LoggerFactory.getLogger(DichVuServiceIml.class);

    @Autowired
    private DichVuRepository dichVuRepository;

    @Autowired
    private DichVuMapper dichVuMapper;

    @Autowired
    private NhanVien_DichVuService nhanVien_dichVuService;

    @Autowired
    private DichVu_CongTyService dichVu_congTyService;

    @Override
    public DichVuResponseDto addDichVu(DichVuRequestDto requestDto) {
        logger.info("Thêm mới dịch vụ...");
        DichVu dichVu = dichVuMapper.convertToEntity(requestDto);
        DichVu dichVuAdded = dichVuRepository.save(dichVu);
        logger.info("Dịch vụ được thêm: {}", dichVuAdded);
        logger.info("THêm mới dịch vụ thành công...");
        return dichVuMapper.convertToResponseDto(dichVuAdded);
    }

    @Override
    public DichVuResponseDto updateDichVu(DichVuRequestDto requestDto) {
        logger.info("Bắt đầu update dịch vụ");
        DichVu dichVuEntity = dichVuMapper.convertToEntity(requestDto);
        DichVu dichVu = dichVuRepository.findById(dichVuEntity.getId()).orElse(null);
        DichVu dichVuUpdated = null;

        if (dichVu != null){
            dichVuUpdated = dichVuRepository.saveAndFlush(dichVuEntity);
            logger.info("Dịch vụ đang được update: {}", dichVuUpdated);
        }
        else {
            logger.error("Dịch vụ không tìm thấy...");
        }
        logger.info("thành công...");
        return dichVuMapper.convertToResponseDto(dichVuUpdated);
    }

    @Override
    public boolean deleteDichVu(int id) {
        logger.info("Bắt đầu xóa dịch vụ...");
        boolean resultDeleted = false;
        DichVu dichVu = dichVuRepository.findById(id).orElse(null);
        if (dichVu != null){
            logger.info("Xóa tất cả nhân viên dịch vụ:");
            nhanVien_dichVuService.deleteAllNhanVien_DichVuByDichVu(dichVu);

            logger.info("Xóa tất cả dịch vụ công ty:");
            dichVu_congTyService.deleteAllDichVu_CongTyByDichVu(dichVu);

            logger.info("Xóa dịch vụ: {}", dichVu);
            dichVuRepository.deleteById(id);
            resultDeleted = true;
        }
        return resultDeleted;    }

    @Override
    public List<DichVuResponseDto> getAllDichVu() {
        logger.info("Bắt đầu đọc danh sách dịch vụ...");
        List<DichVu> dichVuList = dichVuRepository.findAll();
        logger.info("Danh sách dịch vụ đã đọc: {}", dichVuList);
        logger.info("kết thúc...");
        return dichVuMapper.convertToResponseDtoList(dichVuList);
    }

    @Override
    public DichVu getByTen(String ten) {
        logger.info("Tìm dịch vụ theo tên:{}", ten);
        return dichVuRepository.findByTen(ten);
    }

    @Override
    public DichVuResponseDto getDichVuById(int id) {
        logger.info("Bắt đầu đọc dịch vụ...");
        DichVu dichVu = dichVuRepository.findById(id).orElse(null);
        logger.info("Dịch vụ được đọc: {}", dichVu);
        logger.info("kết thúc ...");
        return dichVuMapper.convertToResponseDto(dichVu);
    }
}
