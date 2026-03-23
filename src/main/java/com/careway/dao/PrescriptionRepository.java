package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Prescription;

public interface  PrescriptionRepository extends JpaRepository<Prescription, Integer>{
    // pas de modif pour l'instant mais peut etre apres si il y a fonction de trie ou de recherche
}
