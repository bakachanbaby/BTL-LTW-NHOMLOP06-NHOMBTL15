package com.example.qlvp.controller;


import com.example.qlvp.model.dto.DichVuRequestDto;
import com.example.qlvp.model.dto.DichVuResponseDto;
import com.example.qlvp.service.DichVuService;
import com.example.qlvp.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/dichvu")
@CrossOrigin("*")
public class DichVuRestController {

    private static final Logger logger = LoggerFactory.getLogger(DichVuRestController.class);
    @Autowired
    private DichVuService dichVuService;

    @Autowired
    private Validation validation;

 // list_error
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addDichVu(@RequestBody DichVuRequestDto requestDto){

        List<String> list_error = validation.getInputError(requestDto);
        if (!list_error.isEmpty()){
            return new ResponseEntity<>(list_error, HttpStatus.BAD_REQUEST);
        }

        DichVuResponseDto dichVuResponseDto =dichVuService.addDichVu(requestDto);
        if (dichVuResponseDto != null){
            return new ResponseEntity<>(dichVuResponseDto, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("fail", HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateSDichVu(@RequestBody DichVuRequestDto requestDto){
        List<String> list_error = validation.getInputError(requestDto);
        if (requestDto.getId() <=  0){
            list_error.add("id không được là số âm");
        }
        if (!list_error.isEmpty()){
            return new ResponseEntity<>(list_error, HttpStatus.BAD_REQUEST);
        }

        DichVuResponseDto dichVuResponseDto = dichVuService.updateDichVu(requestDto);
        if (dichVuResponseDto != null){
            return new ResponseEntity<>(dichVuResponseDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDichVu(@PathVariable("id") int id){

        if (id <= 0){
            return new ResponseEntity<>("không được nhập id âm", HttpStatus.BAD_REQUEST);
        }
        boolean res = dichVuService.deleteDichVu(id);
        if (res){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getDichVuById(@PathVariable("id") int id){
        if (id <= 0){
            return new ResponseEntity<>("không được nhập id âm", HttpStatus.BAD_REQUEST);
        }
        DichVuResponseDto dichVuResponseDto = dichVuService.getDichVuById(id);
        if (dichVuResponseDto != null){
            return new ResponseEntity<>(dichVuResponseDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllDichVu(){
        List<DichVuResponseDto> dichVuResponseDtos = dichVuService.getAllDichVu();
        if (!dichVuResponseDtos.isEmpty()){
            return new ResponseEntity<>(dichVuResponseDtos, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
