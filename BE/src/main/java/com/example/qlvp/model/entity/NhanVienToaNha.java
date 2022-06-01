package com.example.qlvp.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;//dung cho ngay sinh

@Entity
@Table(name = "nhanvientoanha")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class NhanVienToaNha implements Serializable {

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
    //            sonhanvien

    @Column(name = "ngaysinh")
    private Date ngaySinh;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "vitri")
    private String viTri;

}