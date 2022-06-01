package com.example.qlvp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NhanVienToaNhaRequestDto  implements Serializable{//giong y het response
    @JsonProperty("id")
    private int id;
    @JsonProperty("ma")
    private String ma;

    @JsonProperty("ten")
    private String ten;

    @JsonProperty("cccd")
    private String cccd;

    @JsonProperty("ngaysinh")
    private Date ngaySinh;

    @JsonProperty("sdt")
    private String sdt;

    @JsonProperty("vitri")
    private String viTri;
}
