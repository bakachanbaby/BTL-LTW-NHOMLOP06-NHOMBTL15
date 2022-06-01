package com.example.qlvp.repository;

import com.example.qlvp.model.entity.CongTy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CongTyRepository extends JpaRepository<CongTy, Integer> {

}
