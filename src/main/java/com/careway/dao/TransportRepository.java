package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Transport;

public interface TransportRepository extends JpaRepository<Transport, Integer> {
    
}
