/*
http://localhost:8080/api/v1/congty/hoadon
http://localhost:8080/api/v1/congty/hoadon
*/
package com.example.qlvp.controller;

import com.example.qlvp.model.dto.HoaDonResponseDto;
import com.example.qlvp.model.dto.TienLuongResponseDto;
import com.example.qlvp.service.HoaDonService;
import com.example.qlvp.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/congty/hoadon")
@CrossOrigin("*")
public class HoaDonRestController {
    private static final Logger logger = LoggerFactory.getLogger(CongTyRestController.class);

    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private Validation validation;

    @GetMapping( produces = "application/json")
    public ResponseEntity<?> getHoaDon(){
        List<HoaDonResponseDto> hoaDonResponseDtoList = hoaDonService.getHoaDon();
        if (!hoaDonResponseDtoList.isEmpty()){
            return new ResponseEntity<>(hoaDonResponseDtoList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping( value="/{id}" ,produces = "application/json")
    public ResponseEntity<?> getThongKeChiTietHoaDonByID(@PathVariable("id") int id){
        List<HoaDonResponseDto> hoaDonResponseDtoList = hoaDonService.getThongKeChiTietHoaDonByID(id);
        if (!hoaDonResponseDtoList.isEmpty()){
            return new ResponseEntity<>(hoaDonResponseDtoList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
