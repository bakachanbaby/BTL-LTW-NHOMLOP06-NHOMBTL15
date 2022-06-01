package com.example.qlvp.controller;

import com.example.qlvp.model.dto.*;
import com.example.qlvp.model.entity.NhanVienToaNha;
import com.example.qlvp.service.NhanVien_DichVuService;
import com.example.qlvp.service.NhanVienToaNhaService;

import com.example.qlvp.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/nhanvien_dichvu")
@CrossOrigin("*")
public class NhanVien_DichVuRestController {

    private static final Logger logger = LoggerFactory.getLogger(NhanVien_DichVuRestController.class);

    @Autowired
    private NhanVien_DichVuService nhanVien_dichVuService;
    @Autowired
    private NhanVienToaNhaService nhanVienToaNhaService;

    @Autowired
    private Validation validation;

//    @GetMapping(produces = "application/json")
//    public ResponseEntity<?> getAllNhanVien_DichVu() {
//        logger.info("Lấy tất cả nhân viên công ty");
//
//        List<NhanVien_DichVuResponseDto>
//                nhanVienCongTyCompanyResponseDtoList = nhanVien_dichVuService.getAllNhanVienDichVu();
//
//        if (nhanVienCongTyCompanyResponseDtoList.isEmpty()) {
//            logger.error("Không tìm thấy");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            logger.info("Thành công ...");
//            return new ResponseEntity<>(nhanVienCongTyCompanyResponseDtoList, HttpStatus.OK);
//        }
//    }

    @GetMapping(value = "/{id}" , produces = "application/json")
    public ResponseEntity<?> getAllNhanVienDichVuByNhanVienToaNha_Id(@PathVariable("id") int id) {
        logger.info("Lấy tất cả nhân viên công ty");
//    int id=1;
        List<NhanVien_DichVuResponseDto>
                nhanVienCongTyCompanyResponseDtoList = nhanVien_dichVuService.getAllNhanVienDichVuByNhanVienToaNha_Id(id);

        if (nhanVienCongTyCompanyResponseDtoList.isEmpty()) {
            logger.error("Không tìm thấy");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Thành công ...");
            return new ResponseEntity<>(nhanVienCongTyCompanyResponseDtoList, HttpStatus.OK);
        }
    }
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addNhanVien_DichVu(@RequestBody NhanVien_DichVuRequestDto requestDto){
        logger.info("Thêm nhân viên dịch vụ");

        List<String> errors = validation.getInputError(requestDto);
//        if (requestDto.getId() <= 0){
//            errors.add("Id không được nhỏ hơn hoặc bằng 0.");
//        }
        if (!errors.isEmpty()){
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        NhanVien_DichVuResponseDto responseDto = nhanVien_dichVuService.addNhanVien_DichVu(requestDto);
        if (responseDto != null){
            logger.info("Thêm thành công");
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        }
        else {
            logger.error("Thêm thất bại");
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateNhanVien_DichVu(@RequestBody NhanVien_DichVuRequestDto requestDto){
        logger.info("Sửa nhân viên dịch vụ");

        List<String> errors = validation.getInputError(requestDto);
        if (requestDto.getId() <= 0){
            errors.add("Id không được thấp hơn hoặc bằng 0");
        }
        if (!errors.isEmpty()){
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        NhanVien_DichVuResponseDto responseDto = nhanVien_dichVuService.updateNhanVien_DichVu(requestDto);
        if (responseDto != null){
            logger.info("Sửa thành công");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        else {
            logger.error("Sửa thất bại");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNhanVien_DichVuById(@PathVariable int id){

        if (id <= 0){
            return new ResponseEntity<>("Id không được thấp hơn hoặc bằng 0", HttpStatus.BAD_REQUEST);
        }

        logger.info("Xóa nhân viên dịch vụ với id");
        boolean resultOfDeletion = nhanVien_dichVuService.deleteNhanVien_DichVu(id);
        if (resultOfDeletion){
            logger.info("Xóa thành công");
            return new ResponseEntity<>("Xóa thành công",HttpStatus.OK);
        }
        else {
            logger.error("Xóa thất bại");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping(value = "/{id}" , produces = "application/json")
//    public ResponseEntity<?> getNhanVien_DichVuByID(@PathVariable("id") int id){
//        logger.info("Thành công");
//        if(id<=0) return new ResponseEntity<>("id truyền vào bị âm",HttpStatus.BAD_REQUEST);
////        NhanVienToaNha nhanVienToaNha= nhanVienToaNhaService.getNhanVienToaNhaById(id);
////        NhanVienToaNhaRequestDto  nhanVienToaNhaRequestDto
//        NhanVien_DichVuResponseDto nhanVien_dichVuResponseDto=nhanVien_dichVuService.g;
//        if(nhanVien_dichVuResponseDto!=null)
//            return new ResponseEntity<>(nhanVien_dichVuResponseDto,HttpStatus.OK);
//        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }



}
