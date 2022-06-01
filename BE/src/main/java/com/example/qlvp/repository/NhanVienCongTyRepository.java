package com.example.qlvp.repository;

import com.example.qlvp.model.entity.CongTy;
import com.example.qlvp.model.entity.NhanVienCongTy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienCongTyRepository extends JpaRepository<NhanVienCongTy, Integer> {

    int countByCongTy(CongTy congTy);
    void deleteByCongTy(CongTy congTy);
    List<NhanVienCongTy> findByCongTy(CongTy congTy);
}
