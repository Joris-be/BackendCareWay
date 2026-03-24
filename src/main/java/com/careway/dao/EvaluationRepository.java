package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer>{
    // en attente du front pr lier avec chayma
}
