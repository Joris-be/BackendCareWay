package com.careway.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.careway.dao.PatientFavorisRepository;
import com.careway.dao.PatientRepository;
import com.careway.dao.TransporteurRepository;
import com.careway.dto.PatientFavorisDTO;
import com.careway.entity.Patient;
import com.careway.entity.PatientFavoris;
import com.careway.entity.Transporteur;
import com.careway.entity.TypeTransport;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientFavorisService {

    private final PatientFavorisRepository patientFavorisRepository;
    private final PatientRepository patientRepository;
    private final TransporteurRepository transporteurRepository;

    public List<PatientFavorisDTO> getFavoris(Integer idPatient) {
        return patientFavorisRepository.findByPatientIdpatient(idPatient)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PatientFavorisDTO getFavoriByType(Integer idPatient, TypeTransport typetransport) {
        PatientFavoris favori = patientFavorisRepository
                .findByPatientIdpatientAndTypetransport(idPatient, typetransport)
                .orElseThrow(() -> new RuntimeException(
                    "Aucun favori trouvé pour le patient " + idPatient + " et le type " + typetransport
                ));
        return toDTO(favori);
    }

    public PatientFavorisDTO setFavori(PatientFavorisDTO dto) {
        Patient patient = patientRepository.findById(dto.getIdPatient())
                .orElseThrow(() -> new RuntimeException("Patient introuvable : " + dto.getIdPatient()));

        Transporteur transporteur = transporteurRepository.findById(dto.getIdTransporteur())
                .orElseThrow(() -> new RuntimeException("Transporteur introuvable : " + dto.getIdTransporteur()));

        PatientFavoris favori = patientFavorisRepository
                .findByPatientIdpatientAndTypetransport(dto.getIdPatient(), dto.getTypeTransport())
                .orElse(new PatientFavoris(patient, transporteur, dto.getTypeTransport()));

        favori.setTypetransport(dto.getTypeTransport());
        return toDTO(patientFavorisRepository.save(favori));
    }

    public void deleteFavori(Integer idPatient, TypeTransport typetransport) {
        PatientFavoris favori = patientFavorisRepository
                .findByPatientIdpatientAndTypetransport(idPatient, typetransport)
                .orElseThrow(() -> new RuntimeException(
                    "Aucun favori trouvé pour le patient " + idPatient + " et le type " + typetransport
                ));
        patientFavorisRepository.delete(favori);
    }

    private PatientFavorisDTO toDTO(PatientFavoris favori) {
    PatientFavorisDTO dto = new PatientFavorisDTO();
    dto.setIdPatient(favori.getPatient().getIdpatient());
    dto.setIdTransporteur(favori.getTransporteur().getIdtransporteur());
    dto.setTypeTransport(favori.getTypetransport());
    return dto;
}
}