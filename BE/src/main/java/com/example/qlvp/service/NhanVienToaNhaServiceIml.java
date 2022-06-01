package com.example.qlvp.service;

import com.example.qlvp.mapper.NhanVienToaNhaMapper;
import com.example.qlvp.model.dto.HoaDonResponseDto;
import com.example.qlvp.model.dto.NhanVienToaNhaRequestDto;
import com.example.qlvp.model.dto.NhanVienToaNhaResponseDto;
import com.example.qlvp.model.dto.TienLuongResponseDto;
import com.example.qlvp.model.entity.CongTy;
import com.example.qlvp.model.entity.DichVu;
import com.example.qlvp.model.entity.NhanVienToaNha;
import com.example.qlvp.model.entity.NhanVien_DichVu;
import com.example.qlvp.repository.NhanVienToaNhaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class NhanVienToaNhaServiceIml implements NhanVienToaNhaService {
    private static final Logger logger = LoggerFactory.getLogger(NhanVienToaNhaServiceIml.class);
    @Autowired
    private NhanVienToaNhaRepository nhanVienToaNhaRepository;

    @Autowired
    private NhanVienToaNhaMapper nhanVienToaNhaMapper;

    @Autowired
    private NhanVien_DichVuService nhanVien_dichVuService;


    /**
     * @param requestDto
     * @return
     */
    @Override
    @Transactional
    public NhanVienToaNhaResponseDto addNhanVienToaNha(NhanVienToaNhaRequestDto requestDto) {

        logger.info("bắt đầu");
        NhanVienToaNha nhanVienToaNha = nhanVienToaNhaMapper.convertToEntity(requestDto);
        NhanVienToaNha nhanVienToaNha1 = nhanVienToaNhaRepository.save(nhanVienToaNha);
        logger.info("nhân viên được lưu là", nhanVienToaNha1);
        return nhanVienToaNhaMapper.convertToResponseDto(nhanVienToaNha1);


    }

    /**
     * @param requestDto
     * @return
     */
    @Override
    public NhanVienToaNhaResponseDto updateNhanVienToaNha(NhanVienToaNhaRequestDto requestDto) {
        logger.info("bắt đầu");
        NhanVienToaNha nhanVienToaNha = nhanVienToaNhaMapper.convertToEntity(requestDto);
        NhanVienToaNha nhanVienToaNha_csdl = nhanVienToaNhaRepository.findById(nhanVienToaNha.getId())
                .orElse(null);
        NhanVienToaNha nhanVienToaNha1 = null;
        if (nhanVienToaNha_csdl != null) {
            nhanVienToaNha1 = nhanVienToaNhaRepository.saveAndFlush(nhanVienToaNha);
            logger.info("nhân viên update là :", nhanVienToaNha1);
        } else logger.info("không tìm tháy nhân viên");
        return nhanVienToaNhaMapper.convertToResponseDto(nhanVienToaNha1);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteNhanVienToaNha(int id) {
        logger.info("bắt đầu");
        boolean res = false;
        NhanVienToaNha nhanVienToaNha = nhanVienToaNhaRepository.findById(id)
                .orElse(null);
        if (nhanVienToaNha != null) {
            logger.info("xóa tất cả dịch vụ nhtn đăng ký");
            nhanVien_dichVuService.deleteAllNhanVien_DichVuByNhanVien(nhanVienToaNha);
            logger.info("xóa hân viên tòa nhà");
            nhanVienToaNhaRepository.deleteById(id);
            res = true;
        }
        return res;
    }

    /**
     * @return
     */
    @Override
    public List<NhanVienToaNhaResponseDto> getAllNhanVienToaNha() throws ParseException {
        List<NhanVienToaNha> list = nhanVienToaNhaRepository.findAll();
//        for (NhanVienToaNha nhanVienToaNha: list) {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//            SimpleDateFormat formatternew = new SimpleDateFormat("yyyy-mm-dd");
//            String date2 = formatter.format(nhanVienToaNha.getNgaySinh());
//            Date ngaysinh = formatternew.parse(date2);
//            nhanVienToaNha.setNgaySinh(ngaysinh);
//            logger.info(ngaysinh.toString());
//        }
        return nhanVienToaNhaMapper.aconvertToResponseDtoList(list);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public NhanVienToaNhaResponseDto getNhanVienToaNhaById(int id) {
        NhanVienToaNha nhanVienToaNha = nhanVienToaNhaRepository.findById(id)
                .orElse(null);
        return nhanVienToaNhaMapper.convertToResponseDto(nhanVienToaNha);
    }

}
