
/*

http://localhost:8080/api/v1/nhanvientoanha/tienluong
 */

package com.example.qlvp.controller;

import com.example.qlvp.model.dto.CongTyRequestDto;
import com.example.qlvp.model.dto.CongTyResponseDto;
import com.example.qlvp.model.dto.HoaDonResponseDto;
import com.example.qlvp.model.dto.TienLuongResponseDto;
import com.example.qlvp.service.CongTyService;
import com.example.qlvp.service.TienLuongService;
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
@RequestMapping("api/v1/nhanvientoanha/tienluong")
@CrossOrigin("*")
public class TienLuongRestController {
    private static final Logger logger = LoggerFactory.getLogger(CongTyRestController.class);

    @Autowired
    private TienLuongService tienLuongService;
    @Autowired
    private Validation validation;

    @GetMapping( produces = "application/json")
    public ResponseEntity<?> getThongKeTienLuong(){
        List<TienLuongResponseDto> tienLuongResponseDtoList = tienLuongService.getThongKeTienLuong();
        if (!tienLuongResponseDtoList.isEmpty()){
            return new ResponseEntity<>(tienLuongResponseDtoList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping( value="/{id}" ,produces = "application/json")
    public ResponseEntity<?> getThongKeChiTietTienLuongByID(@PathVariable("id") int id){
        List<TienLuongResponseDto> tienLuongResponseDtoList = tienLuongService.getThongKeChiTietTienLuongByID(id);
        if (!tienLuongResponseDtoList.isEmpty()){
            return new ResponseEntity<>(tienLuongResponseDtoList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
