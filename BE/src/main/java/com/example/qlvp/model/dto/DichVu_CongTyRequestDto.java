package com.example.qlvp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DichVu_CongTyRequestDto implements Serializable {//giong voi response y het
    @JsonProperty("id")
    private int id;

    @JsonProperty("congty")
    private CongTyRequestDto congTy;

    @JsonProperty("dichvu")
    private DichVuRequestDto dichVu;

    @JsonProperty("thangthue")
    private int thangthue;

    @JsonProperty("namthue")
    private int namthue;

}
