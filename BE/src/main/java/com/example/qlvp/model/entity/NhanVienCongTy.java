package com.example.qlvp.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@Entity
@Table(name = "nhanviencongty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienCongTy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "cccd")
    private String cccd;

    @Column(name = "ngaysinh")
    private Date ngaySinh;

    @Column(name = "sdt")
    private String sdt;

    @ManyToOne
    @JoinColumn(name = "id_congty",nullable = false)
    private CongTy congTy;// id_congty la co ca 1 cong ty


}
