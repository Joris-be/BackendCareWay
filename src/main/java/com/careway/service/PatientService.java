package com.careway.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.careway.dao.PatientRepository;
import com.careway.dao.PrescriptionRepository;
import com.careway.dto.PatientDTO;
import com.careway.entity.Patient;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PrescriptionRepository prescriptionRepository;

    public PatientService(PatientRepository patientRepository, PrescriptionRepository prescriptionRepository) {
        this.patientRepository = patientRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    // Convertir Patient en PatientDTO
    private PatientDTO toDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setIdpatient(patient.getIdpatient());
        dto.setPrenom(patient.getPrenom());
        dto.setNom(patient.getNom());
        dto.setDatenaiss(patient.getDatenaiss());
        dto.setNss(patient.getNss());
        dto.setAdresse(patient.getAdresse());
        dto.setMaladie(patient.getMaladie());
        dto.setTel(patient.getTel());
        dto.setMail(patient.getMail());
        dto.setGenre(patient.getGenre());
        dto.setPays(patient.getPays());
        dto.setImage(patient.getImage());

        if (patient.getEvaluations() != null) {
            dto.setIdEvaluations(
                    patient.getEvaluations().stream()
                            .map(e -> e.getIdevaluation())
                            .collect(Collectors.toList()));
        } else {
            dto.setIdEvaluations(null);
        }

        if (patient.getNotes() != null) {
            dto.setIdNotes(
                    patient.getNotes().stream()
                            .map(n -> n.getIdnote())
                            .collect(Collectors.toList()));
        } else {
            dto.setIdNotes(null);
        }

        if (patient.getPrescriptions() != null) {
            dto.setIdPrescriptions(
                    patient.getPrescriptions().stream()
                            .map(p -> p.getIdprescription())
                            .collect(Collectors.toList()));
        } else {
            dto.setIdPrescriptions(null);
        }

        return dto;
    }

    // Récupérer tous les patients
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer un patient par son id
    public PatientDTO getPatientById(Integer id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id : " + id));
        return toDTO(patient);
    }

    // Créer ou modifier un patient
    public PatientDTO savePatient(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setIdpatient(dto.getIdpatient());
        patient.setPrenom(dto.getPrenom());
        patient.setNom(dto.getNom());
        patient.setDatenaiss(dto.getDatenaiss());
        patient.setNss(dto.getNss());
        patient.setAdresse(dto.getAdresse());
        patient.setMaladie(dto.getMaladie());
        patient.setTel(dto.getTel());
        patient.setMail(dto.getMail());
        patient.setGenre(dto.getGenre());
        patient.setPays(dto.getPays());
        patient.setImage(dto.getImage());
        return toDTO(patientRepository.save(patient));
    }

    // Supprimer un patient
    public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
    }

    // Trouver un patient par NSS
    public Patient getPatientByNSS(String nss) {
        return patientRepository.findAll().stream()
                .filter(p -> p.getNss() != null && p.getNss().equals(nss))
                .findFirst()
                .orElse(null);
    }

    // Valider le mot de passe du patient
    public boolean validatePatientPassword(Patient patient, String password) {
        return patient.getMotdepasse() != null && patient.getMotdepasse().equals(password);
    }

    // Récupérer les patients d'un médecin (via ses prescriptions)
    public List<PatientDTO> getPatientsByMedecinId(Integer medecinId) {
        Set<Integer> patientIds = prescriptionRepository.findAll().stream()
                .filter(p -> p.getMedecin() != null && p.getMedecin().equals(medecinId.toString()))
                .map(p -> p.getIdpatient())
                .collect(Collectors.toSet());

        return patientRepository.findAll().stream()
                .filter(p -> patientIds.contains(p.getIdpatient()))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}