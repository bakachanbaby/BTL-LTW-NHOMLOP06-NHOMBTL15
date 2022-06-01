package com.example.qlvp.model.entity;


import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Table(name = "nhanvien_dichvu")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVien_DichVu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "mucluong")
    private double mucLuong;

    @Column(name = "thanglam")
    private int thangLam;

    @Column(name = "namlam")
    private int namLam;

    @ManyToOne
    @JoinColumn(name = "id_dichvu", nullable = false)
    private DichVu dichVu;

    @ManyToOne
    @JoinColumn(name = "id_nhanvientoanha", nullable = false)
    private NhanVienToaNha nhanVienToaNha;
}
