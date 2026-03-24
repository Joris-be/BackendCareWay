package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

import com.careway.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{
    //permet de trouver un patient par son num de secu sociale et nom et prenom
    Optional<Patient> findByNss(String nss);
    List<Patient> findByNomAndPrenom(String nom, String prenom);

}
