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
public class DichVuResponseDto  implements Serializable{//giong voi request y het
    @JsonProperty("id")
    private int id;
    @JsonProperty("ma")
    private String ma;

    @JsonProperty("ten")
    private String ten;

    @JsonProperty("loaidichvu")
    private String loaiDichVu;

    @JsonProperty("dongia")
    private double donGia;

    @JsonProperty("mota")
    private String moTa;
}
