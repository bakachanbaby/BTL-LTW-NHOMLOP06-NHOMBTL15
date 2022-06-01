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
public class HoaDonResponseDto implements Serializable {
    @JsonProperty("id")
    private int id;

    @JsonProperty("macongty")
    private String maCongTy;

    @JsonProperty("tencongty")
    private String tenCongty;

    @JsonProperty("tienthuematbang")
    private double tienThueMatBang;

    @JsonProperty("tongphidichvu")
    private double tongPhiDichVu;

    @JsonProperty("tongtienthue")
    private double tongTienThue;

    @JsonProperty("thang")
    private int thang;
}
