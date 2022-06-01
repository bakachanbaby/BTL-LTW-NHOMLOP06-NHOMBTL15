package com.example.qlvp.repository;

import com.example.qlvp.model.entity.CongTy;
import com.example.qlvp.model.entity.DichVu;
import com.example.qlvp.model.entity.DichVu_CongTy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DichVu_CongTyRepository extends JpaRepository<DichVu_CongTy, Integer> {
    List<DichVu_CongTy> findByCongTy_Id(int id);
    int countByCongTy(CongTy congTy);
    void deleteByCongTy(CongTy congTy);
    void deleteByDichVu(DichVu dichVu);
    List<DichVu_CongTy> findByCongTy(CongTy congTy);
    List<DichVu_CongTy> findByDichVu(DichVu dichVu);

}
