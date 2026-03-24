package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Prescription;

public interface  PrescriptionRepository extends JpaRepository<Prescription, Integer>{
    
}
