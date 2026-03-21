package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer>{
    
}
