package com.example.qlvp.repository;

import com.example.qlvp.model.entity.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DichVuRepository extends JpaRepository<DichVu, Integer> {
    DichVu findByTen (String ten);
}