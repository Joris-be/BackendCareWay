package com.careway.dao;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Medecin;
 
public interface MedecinRepository extends JpaRepository<Medecin, Integer> {
 
}
