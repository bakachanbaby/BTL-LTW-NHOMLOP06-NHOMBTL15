package com.example.qlvp.model.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Table(name="dichvu_congty")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DichVu_CongTy implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="id_congty",nullable = false)
    private CongTy congTy;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_dichvu",nullable = false)
    private DichVu dichVu;

    @Column(name = "thangthue")
    private int thangThue;

    @Column(name = "namthue")
    private int namThue;

}
