package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Etape;

public interface EtapeRepository extends JpaRepository<Etape, Integer>{
    
}
