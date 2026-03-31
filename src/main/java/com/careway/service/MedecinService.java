package com.careway.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.careway.dao.MedecinRepository;
import com.careway.dao.PrescriptionRepository;
import com.careway.dao.PatientRepository;
import com.careway.dto.MedecinStatsDTO;
import com.careway.entity.Medecin;

@Service
public class MedecinService {

    private final MedecinRepository medecinRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;

    public MedecinService(MedecinRepository medecinRepository, PrescriptionRepository prescriptionRepository,
            PatientRepository patientRepository) {
        this.medecinRepository = medecinRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
    }

    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    public Optional<Medecin> getMedecinById(Integer id) {
        return medecinRepository.findById(id);
    }

    public Optional<Medecin> getMedecinByRPPS(String rpps) {
        return medecinRepository.findAll().stream()
                .filter(m -> m.getRpps() != null && m.getRpps().equals(rpps))
                .findFirst();
    }

    public boolean validateMedecinPassword(Medecin medecin, String password) {
        return medecin.getMotdepasse() != null && medecin.getMotdepasse().equals(password);
    }

    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    public MedecinStatsDTO getMedecinStats(Integer medecinId) {
        Optional<Medecin> medecinOpt = getMedecinById(medecinId);
        if (!medecinOpt.isPresent()) {
            return null;
        }

        Medecin medecin = medecinOpt.get();

        // Count prescriptions for this medecin
        List<com.careway.entity.Prescription> allPrescriptions = prescriptionRepository.findAll();
        long prescriptionsCount = allPrescriptions.stream()
                .filter(p -> p.getMedecin() != null && p.getMedecin().equals(medecinId.toString()))
                .count();

        // Count patients for this medecin (via prescriptions)
        Set<Integer> patientIds = allPrescriptions.stream()
                .filter(p -> p.getMedecin() != null && p.getMedecin().equals(medecinId.toString()))
                .map(p -> p.getIdpatient())
                .collect(Collectors.toSet());

        // Calculate average prescriptions
        List<Medecin> allMedecins = getAllMedecins();
        double averagePrescriptions = allMedecins.stream()
                .mapToLong(m -> allPrescriptions.stream()
                        .filter(p -> p.getMedecin() != null && p.getMedecin().equals(m.getIdmedecin().toString()))
                        .count())
                .average()
                .orElse(0.0);

        // Top disease for this medecin
        String topDisease = patientRepository.findAll().stream()
                .filter(p -> patientIds.contains(p.getIdpatient()))
                .map(p -> p.getMaladie())
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()))
                .entrySet().stream()
                .max((a, b) -> a.getValue().compareTo(b.getValue()))
                .map(e -> e.getKey())
                .orElse("N/A");

        // Top transport type for this medecin
        String topTransport = allPrescriptions.stream()
                .filter(p -> p.getMedecin() != null && p.getMedecin().equals(medecinId.toString()))
                .map(p -> p.getTypetransport())
                .collect(Collectors.groupingBy(t -> t, Collectors.counting()))
                .entrySet().stream()
                .max((a, b) -> a.getValue().compareTo(b.getValue()))
                .map(e -> e.getKey())
                .orElse("N/A");

        return new MedecinStatsDTO(
                medecinId,
                medecin.getPrenom() + " " + medecin.getNom(),
                medecin.getSpecialite(),
                (int) prescriptionsCount,
                averagePrescriptions,
                patientIds.size(),
                0, // excellent rating (placeholder)
                topDisease,
                topTransport);
    }
}
