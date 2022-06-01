package com.example.qlvp.controller;

import com.example.qlvp.model.dto.DichVu_CongTyRequestDto;
import com.example.qlvp.model.dto.DichVu_CongTyResponseDto;
import com.example.qlvp.service.DichVu_CongTyService;
import com.example.qlvp.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/congty/dichvu")
@CrossOrigin("*")
public class DichVu_CongTyRestController {
    private static final Logger logger = LoggerFactory.getLogger(DichVu_CongTyRestController.class);

    @Autowired
    private DichVu_CongTyService dichVu_congTyService;

    @Autowired
    private Validation validation;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getDichVu_CongTyByIdCongTy(@PathVariable int id) {
        logger.info("Lấy tất cả dịch vụ theo id công ty{}", id);

        List<DichVu_CongTyResponseDto> responseDto = dichVu_congTyService.getAllDichVu_CongTy(id);

        if (responseDto != null && !responseDto.isEmpty()) {
            logger.info("Thành công");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            logger.info("Thất bại");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addDichVu_CongTy(@RequestBody DichVu_CongTyRequestDto requestDto) {

        List<String> errors = validation.getInputError(requestDto);

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        logger.info("Thêm dịch vụ {} cho công ty {}", requestDto, requestDto.getCongTy());

        DichVu_CongTyResponseDto responseDto = dichVu_congTyService.addDichVu_CongTy(requestDto);

        if (responseDto != null) {
            logger.info("Thêm thành công");
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } else {
            logger.info("Thêm thất bại");
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateDichVu_CongTy(@RequestBody DichVu_CongTyRequestDto requestDto) {

        List<String> errors = validation.getInputError(requestDto);

        if (requestDto.getId() <= 0) {
            errors.add("Id không được nhỏ hơn hoặc bằng 0");
        }
        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        logger.info("Sửa dịch vụ {}", requestDto);

        DichVu_CongTyResponseDto responseDto = dichVu_congTyService.updateDichVu_CongTy(requestDto);

        if (responseDto != null) {
            logger.info("Sửa thành công");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            logger.info("Sửa thất bại");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteDichVu_CongTy(@PathVariable int id) {

        if (id <= 0) {
            return new ResponseEntity<>("Id không được nhỏ hơn hoặc bằng 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("Xóa dịch vụ công ty với id {}", id);
        boolean response = dichVu_congTyService.deleteDichVu_CongTy(id);
        if (response) {
            logger.info("Xóa thành công");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            logger.info("Xóa thất bại");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
