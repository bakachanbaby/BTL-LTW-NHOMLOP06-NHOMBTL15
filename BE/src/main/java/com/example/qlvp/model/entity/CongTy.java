package com.example.qlvp.model.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "congty")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CongTy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "linhvuchoatdong")
    private String linhVucHoatDong;

    @Column(name = "diachitrongtoanha")
    private String diaChiTrongToaNha;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "dientichthue")
    private int dienTichThue;


}
