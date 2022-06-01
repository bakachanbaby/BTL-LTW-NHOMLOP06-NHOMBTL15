package com.example.qlvp.repository;

import com.example.qlvp.model.entity.NhanVienToaNha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienToaNhaRepository extends JpaRepository<NhanVienToaNha, Integer> {


}
