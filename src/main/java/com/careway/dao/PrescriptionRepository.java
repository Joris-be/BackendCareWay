package com.careway.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.careway.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    @Query("SELECT p FROM Prescription p WHERE p.medecin = :medecinId")
    List<Prescription> findByMedecinId(@Param("medecinId") Integer medecinId);

    @Query("SELECT p FROM Prescription p WHERE p.idpatient = :patientId")
    List<Prescription> findByPatientId(@Param("patientId") Integer patientId);
}
