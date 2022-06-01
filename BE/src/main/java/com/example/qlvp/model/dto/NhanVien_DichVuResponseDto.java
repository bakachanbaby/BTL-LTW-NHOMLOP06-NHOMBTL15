package com.example.qlvp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NhanVien_DichVuResponseDto implements Serializable {//giong voi request y het

    @JsonProperty("id")
    private int id;

//    @JsonProperty("dichvu")
//    private DichVuResponseDto dichVu;
//
//    @JsonProperty("nhanvientoanha")
//    private NhanVienToaNhaResponseDto nhanVienToaNha;
    @JsonProperty("madichvu")
    private String maDichVu;

    @JsonProperty("tendichvu")
    private String tenDichVu;

    @JsonProperty("mucluong")
    private double mucLuong;

    @JsonProperty("thanglam")
    private int thangLam;

    @JsonProperty("namlam")
    private int namLam;
}
