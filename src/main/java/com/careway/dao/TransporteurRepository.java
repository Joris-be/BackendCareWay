package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Transporteur;

public interface TransporteurRepository extends JpaRepository<Transporteur, Integer>{
    // tri ds les transporteur à venir termine etc 
}
