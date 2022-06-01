package com.example.qlvp.controller;

import com.example.qlvp.model.dto.NhanVienCongTyRequestDto;
import com.example.qlvp.model.dto.NhanVienCongTyResponseDto;
import com.example.qlvp.service.NhanVienCongTyService;
import com.example.qlvp.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/congty/nhanviencongty")
@CrossOrigin("*")
@Transactional
public class NhanVienCongTyRestController {

    private static final Logger logger = LoggerFactory.getLogger(NhanVienCongTyRestController.class);

    @Autowired
    private NhanVienCongTyService nhanVienCongTyService;

    @Autowired
    private Validation validation;

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<?> getNhanVienCongTyById(@PathVariable("id") int id) {
        logger.info("Tìm nhân viên công ty của công ty theo id: {}", id);
        if (id <= 0) {
            return new ResponseEntity<>("Id không thể nhỏ hơn hoặc bằng 0", HttpStatus.BAD_REQUEST);
        }

        NhanVienCongTyResponseDto nhanVienCongTyCompanyResponseDto = nhanVienCongTyService.getNhanVienCongTyById(id);

        if (nhanVienCongTyCompanyResponseDto != null) {
            logger.info("Thành công");
            return new ResponseEntity<>(nhanVienCongTyCompanyResponseDto, HttpStatus.OK);
        } else {
            logger.error("Nhân viên công ty của công ty không tìm thấy");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllNhanVienCongTy() {
        logger.info("Lấy tất cả nhân viên công ty");

        List<NhanVienCongTyResponseDto> nhanVienCongTyCompanyResponseDtoList = nhanVienCongTyService.getAllNhanVienCongTy();

        if (nhanVienCongTyCompanyResponseDtoList.isEmpty()) {
            logger.error("Không tìm thấy");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Thành công ...");
            return new ResponseEntity<>(nhanVienCongTyCompanyResponseDtoList, HttpStatus.OK);
        }
    }

    @Transactional
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addNhanVienCongTy(@RequestBody NhanVienCongTyRequestDto nhanVienCongTy) {
        logger.info("Thêm nhân viên công ty cho công ty: {}", nhanVienCongTy);

        List<String> errors = validation.getInputError(nhanVienCongTy);
        if (!errors.isEmpty()) {
            logger.error("BAD_REQUEST: Có thể thiếu 1 số trường ");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        NhanVienCongTyResponseDto nhanVienCongTyCompanyResponseDto = nhanVienCongTyService.addNhanVienCongTy(nhanVienCongTy);

        if (nhanVienCongTyCompanyResponseDto != null) {
            logger.info("Thành công");
            return new ResponseEntity<>(nhanVienCongTyCompanyResponseDto, HttpStatus.CREATED);
        } else {
            logger.error("Không thể thêm nhân viên công ty cho công ty");
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @Transactional
    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateNhanVienCongTy(@RequestBody NhanVienCongTyRequestDto nhanVienCongTy) {
        logger.error("Sửa nhân viên công ty cho công ty...");

        List<String> errors = validation.getInputError(nhanVienCongTy);

        if (nhanVienCongTy.getId() <= 0) {
            errors.add("Id không thể ít hơn hoặc bằng 0");
        }

        if (!errors.isEmpty()) {
            logger.error("BAD_REQUEST: Có thể thiếu 1 số trường ");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        NhanVienCongTyResponseDto nhanVienCongTyCompanyResponseDto = nhanVienCongTyService.updateNhanVienCongTy(nhanVienCongTy);

        if (nhanVienCongTyCompanyResponseDto != null) {
            logger.info("Thành công...");
            return new ResponseEntity<>(nhanVienCongTyCompanyResponseDto, HttpStatus.OK);
        } else {
            logger.error("Không thể sửa nhân viên công ty cho công ty");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteNhanVienCongTyById(@PathVariable("id") int id) {

        if (id <= 0) {
            return new ResponseEntity<>("Id không thể nhỏ hơn hoặc bằng 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("Xóa nhân viên công ty của công ty có id:{}", id);

        boolean nhanVienCongTyCompanyResponseDto = nhanVienCongTyService.deleteNhanVienCongTy(id);

        if (nhanVienCongTyCompanyResponseDto) {
            logger.info("Thành công");
            return new ResponseEntity<>(nhanVienCongTyCompanyResponseDto, HttpStatus.OK);
        } else {
            logger.info("Xóa không thành công");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/get_by_congty", produces = "application/json")
    public ResponseEntity<?> getListNhanVienCongTyByIdCongTy(@RequestParam(name = "id_congty") int idCongTy) {

        if (idCongTy <= 0) {
            return new ResponseEntity<>("Id công ty không thể nhỏ hơn hoặc bằng 0", HttpStatus.BAD_REQUEST);
        }
        logger.info("danh sách nhân viên công ty bởi id");
        List<NhanVienCongTyResponseDto> responseDtoList = nhanVienCongTyService.getAllNhanVienCongTyByCongTy(idCongTy);
        logger.info("kết quả trả về: {}", responseDtoList);
        if (responseDtoList.isEmpty()) {
            logger.error("trống");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
        }
    }
}
