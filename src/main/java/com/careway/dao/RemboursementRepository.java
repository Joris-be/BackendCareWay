package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Remboursement;

public interface RemboursementRepository extends JpaRepository<Remboursement, Integer>{
    
}
