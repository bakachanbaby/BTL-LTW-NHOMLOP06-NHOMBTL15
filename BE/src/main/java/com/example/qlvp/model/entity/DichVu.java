package com.example.qlvp.model.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Table(name = "dichvu")
@Entity
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DichVu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "loaidichvu")
    private String loaiDichVu;

    @Column(name = "dongia")
    private double donGia;

    @Column(name = "mota")
    private String moTa;
}

