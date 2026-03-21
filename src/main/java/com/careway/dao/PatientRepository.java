package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{
    
}
