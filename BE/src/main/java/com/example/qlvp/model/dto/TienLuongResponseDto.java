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
public class TienLuongResponseDto implements Serializable {
    @JsonProperty("id")
    private int id;

    @JsonProperty("manhanvien")
    private String maNhanVien;

    @JsonProperty("tennhanvien")
    private String tenNhanVien;

    @JsonProperty("vitri")
    private String viTri;

    @JsonProperty("tongtienluong")
    private double tongTienLuong;

    @JsonProperty("thang")
    private int thang;

}
