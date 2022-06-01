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
public class CongTyResponseDto  implements Serializable {

    @JsonProperty("id")
    private int id;

    @JsonProperty("ma")
    private String ma;

    @JsonProperty("ten")
    private String ten;

    @JsonProperty("linhvuchoatdong")
    private String linhVucHoatDong;

    @JsonProperty("diachitrongtoanha")
    private String diaChiTrongToaNha;

    @JsonProperty("sdt")
    private String sdt;

    @JsonProperty("dientichthue")
    private int dienTichThue;

    //show theem
    @JsonProperty("sonhanvien")
    private int soNhanVien;

    @JsonProperty("phisudung")
    private double phiSuDung;
}
