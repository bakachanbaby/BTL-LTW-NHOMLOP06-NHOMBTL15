package com.example.qlvp.controller;


import com.example.qlvp.model.dto.CongTyRequestDto;
import com.example.qlvp.model.dto.CongTyResponseDto;
import com.example.qlvp.model.dto.HoaDonResponseDto;
import com.example.qlvp.service.CongTyService;
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
@RequestMapping("api/v1/congty")
@CrossOrigin("*")
public class CongTyRestController {
    private static final Logger logger = LoggerFactory.getLogger(CongTyRestController.class);

    @Autowired
    private CongTyService congTyService;
    @Autowired
    private Validation validation;

    @Transactional
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addCongTY(@RequestBody CongTyRequestDto requestDto){

        List<String> list_error = validation.getInputError(requestDto);
        if (!list_error.isEmpty()){
            return new ResponseEntity<>(list_error, HttpStatus.BAD_REQUEST);
        }

        CongTyResponseDto congTyResponseDto = congTyService.addCongTy(requestDto);
        if (congTyResponseDto != null){
            return new ResponseEntity<>(congTyResponseDto, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @Transactional
    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateCongTy(@RequestBody CongTyRequestDto requestDto){

        List<String> list_error = validation.getInputError(requestDto);
        if (requestDto.getId() <= 0){
            list_error.add("id nhập vào không được âm");
        }
        if (!list_error.isEmpty()){
            return new ResponseEntity<>(list_error, HttpStatus.BAD_REQUEST);
        }
        CongTyResponseDto congTyResponseDto = congTyService.updateCongTy(requestDto);
        if (congTyResponseDto != null){
            return new ResponseEntity<>(congTyResponseDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCongTy(@PathVariable("id") int id){
        if (id <= 0){
            return new ResponseEntity<>("id không được là số âm", HttpStatus.BAD_REQUEST);
        }

        boolean res = congTyService.deleteCongTy(id);
        if (res){
            return new ResponseEntity<>("okiiiiii",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getCongTyById(@PathVariable("id") int id){

        if (id <= 0){
            return new ResponseEntity<>("id không được là số âm", HttpStatus.BAD_REQUEST);
        }

        CongTyResponseDto congTyResponseDto = congTyService.getCongTyById(id);
        if (congTyResponseDto != null){
            return new ResponseEntity<>(congTyResponseDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllCongTy(){
        List<CongTyResponseDto> congTyResponseDtoList = congTyService.getAllCongTy();
        if (!congTyResponseDtoList.isEmpty()){
            return new ResponseEntity<>(congTyResponseDtoList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
