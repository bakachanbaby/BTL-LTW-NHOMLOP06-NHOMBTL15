package com.example.qlvp.controller;


import com.example.qlvp.model.dto.NhanVienToaNhaRequestDto;
import com.example.qlvp.model.dto.NhanVienToaNhaResponseDto;
import com.example.qlvp.model.entity.NhanVienToaNha;
import com.example.qlvp.service.NhanVienToaNhaService;
import com.example.qlvp.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

// có service , validation
@RestController
@RequestMapping("api/v1/nhanvientoanha")
@CrossOrigin("*")
public class NhanVienToaNhaRestController {
    private static final Logger logger = LoggerFactory.getLogger(NhanVienToaNhaRestController.class);
    @Autowired
    private NhanVienToaNhaService nhanVienToaNhaService;

    @Autowired
    private Validation validation;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addNhanVienToaNha(@RequestBody NhanVienToaNhaRequestDto requestDto) {
        List<String> list_error = validation.getInputError(requestDto);
        if(!list_error.isEmpty()){
            return new ResponseEntity<>(list_error, HttpStatus.BAD_REQUEST);
        }
        NhanVienToaNhaResponseDto nhanVienToaNhaResponseDto=nhanVienToaNhaService.addNhanVienToaNha(requestDto);
        if (nhanVienToaNhaResponseDto!=null){
            return new ResponseEntity<>(nhanVienToaNhaResponseDto,HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("fail", HttpStatus.SEE_OTHER);
    }


    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateNhanVienToaNha(@RequestBody @Validated NhanVienToaNhaRequestDto requestDto) {
        List<String> list_error = validation.getInputError(requestDto);

        if(!list_error.isEmpty()){
            return new ResponseEntity<>(list_error, HttpStatus.BAD_REQUEST);
        }
        NhanVienToaNhaResponseDto nhanVienToaNhaResponseDto
                =nhanVienToaNhaService.updateNhanVienToaNha(requestDto);

        if (nhanVienToaNhaResponseDto!=null){
            return new ResponseEntity<>(nhanVienToaNhaResponseDto,
                    HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteNhanVienToaNha( @PathVariable("id") int id ) {
        if(id<=0) return new ResponseEntity<>("id truyền vào bị âm",HttpStatus.BAD_REQUEST);

        boolean res= nhanVienToaNhaService.deleteNhanVienToaNha(id);
        if (res){
            logger.info("Thành công");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}" , produces = "application/json")
    public ResponseEntity<?> getNhanVienToaNhaByID(@PathVariable("id") int id){
        logger.info("Thành công");
        if(id<=0) return new ResponseEntity<>("id truyền vào bị âm",HttpStatus.BAD_REQUEST);
        NhanVienToaNhaResponseDto nhanVienToaNhaResponseDto=nhanVienToaNhaService.getNhanVienToaNhaById(id);
        if(nhanVienToaNhaResponseDto!=null)
            return new ResponseEntity<>(nhanVienToaNhaResponseDto,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping( produces = "application/json")
    public ResponseEntity<?> getAllNhanVienToaNha() throws ParseException {
        List<NhanVienToaNhaResponseDto> nhanVienToaNhaResponseDtoList=nhanVienToaNhaService.getAllNhanVienToaNha();

        if(!nhanVienToaNhaResponseDtoList.isEmpty())
            return new ResponseEntity<>(nhanVienToaNhaResponseDtoList,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}

