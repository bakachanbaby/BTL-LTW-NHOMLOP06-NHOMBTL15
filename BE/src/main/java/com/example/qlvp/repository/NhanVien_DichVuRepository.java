package com.example.qlvp.repository;

import com.example.qlvp.model.entity.DichVu;
import com.example.qlvp.model.entity.NhanVienToaNha;
import com.example.qlvp.model.entity.NhanVien_DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVien_DichVuRepository extends JpaRepository<NhanVien_DichVu, Integer> {
//    List<NhanVien_DichVu> findByNhanVienToaNha_Id(int id);
//    int countByNhanVien(NhanVien NhanVien);
    void deleteByNhanVienToaNha(NhanVienToaNha nhanVienToaNha);
    void deleteByDichVu(DichVu dichVu);
    List<NhanVien_DichVu> findByNhanVienToaNha(NhanVienToaNha nhanVienToaNha);
    List<NhanVien_DichVu> findByDichVu(DichVu dichVu);

    List<NhanVien_DichVu> findByNhanVienToaNha_Id(int id);

}
