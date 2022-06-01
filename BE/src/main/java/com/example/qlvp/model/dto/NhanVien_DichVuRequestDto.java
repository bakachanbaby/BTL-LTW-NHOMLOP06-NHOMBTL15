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
public class NhanVien_DichVuRequestDto implements Serializable {//giong voi response y het
    @JsonProperty("id")
    private int id;

    @JsonProperty("dichvu")
    private DichVuRequestDto dichVu;

    @JsonProperty("nhanvientoanha")
    private NhanVienToaNhaRequestDto nhanVienToaNha;

    @JsonProperty("mucluong")
    private double mucLuong;

    @JsonProperty("thanglam")
    private int thangLam;

    @JsonProperty("namlam")
    private int namLam;


}
