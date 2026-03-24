package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Remboursement;

public interface RemboursementRepository extends JpaRepository<Remboursement, Integer>{
    //affichage des trajets remboursés ou pas avec le montant du remboursement, faire du tri par étape du rembursement 
    
}
