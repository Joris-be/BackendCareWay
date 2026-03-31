package com.careway.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careway.entity.PatientFavoris;
import com.careway.entity.TypeTransport;

@Repository
public interface PatientFavorisRepository extends JpaRepository<PatientFavoris, Integer> {

    List<PatientFavoris> findByPatientIdpatient(Integer idPatient);

    Optional<PatientFavoris> findByPatientIdpatientAndTypetransport(Integer idPatient, TypeTransport typetransport);
}