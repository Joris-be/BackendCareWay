package com.careway.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.careway.dao.PrescriptionRepository;
import com.careway.dao.PatientRepository;
import com.careway.dao.MedecinRepository;
import com.careway.dto.PrescriptionFormDTO;
import com.careway.entity.Prescription;
import com.careway.entity.Patient;
import com.careway.entity.Medecin;
import com.careway.service.PrescriptionPDFService.PrescriptionFormData;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final PrescriptionPDFService pdfService;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
            PatientRepository patientRepository,
            MedecinRepository medecinRepository,
            PrescriptionPDFService pdfService) {
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.pdfService = pdfService;
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public List<Prescription> getPrescriptionsByMedecinId(Integer medecinId) {
        return prescriptionRepository.findAll().stream()
                .filter(p -> p.getMedecin() != null && p.getMedecin().equals(medecinId.toString()))
                .collect(Collectors.toList());
    }

    public Prescription getPrescriptionById(Integer id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription non trouvée"));
    }

    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    /**
     * Crée une prescription avec génération automatique du PDF
     * 
     * @param formData Données du formulaire de prescription depuis le frontend
     * @return La prescription créée avec le PDF généré
     */
    public Prescription createPrescriptionWithPDF(PrescriptionFormDTO formData) throws Exception {
        // Récupérer les données du patient et du médecin
        Patient patient = patientRepository.findById(formData.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));

        Medecin medecin = medecinRepository.findById(formData.getMedecinId())
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé"));

        // Créer les données du formulaire pour le service PDF
        PrescriptionFormData pdfFormData = new PrescriptionFormData(
                formData.getSituation1(),
                formData.getDate_at_mp(),
                formData.getMode_transport(),
                formData.getTrajet_depart(),
                formData.getTrajet_depart_autre(),
                formData.getTrajet_depart_structure(),
                formData.getTrajet_arrivee(),
                formData.getTrajet_arrivee_autre(),
                formData.getTrajet_arrivee_structure(),
                formData.getNombre_transports(),
                formData.getExoneration(),
                formData.getPension_militaire());

        // Générer le PDF
        byte[] pdfBytes = pdfService.generatePrescriptionPDF(patient, medecin, pdfFormData);

        // Créer la prescription
        Prescription prescription = new Prescription();
        prescription.setIdpatient(formData.getPatientId());
        prescription.setMedecin(formData.getMedecinId().toString());
        prescription.setMotifmedical(
                String.join(", ", formData.getSituation1() != null ? formData.getSituation1() : new String[0]));
        prescription
                .setTypetransport(formData.getMode_transport() != null ? formData.getMode_transport() : "Non spécifié");
        prescription.setDateprescription(new Date());
        prescription.setDategeneration(new Date());
        prescription.setPdfData(pdfBytes);

        // Générer un ID pour la prescription (vous pouvez adapter selon votre
        // stratégie)
        int maxId = prescriptionRepository.findAll().stream()
                .mapToInt(Prescription::getIdprescription)
                .max()
                .orElse(0);
        prescription.setIdprescription(maxId + 1);

        return prescriptionRepository.save(prescription);
    }

    public Prescription updatePrescription(Integer id, Prescription prescriptionData) {
        Prescription prescription = getPrescriptionById(id);
        prescription.setMotifmedical(prescriptionData.getMotifmedical());
        prescription.setTypetransport(prescriptionData.getTypetransport());
        prescription.setDateprescription(prescriptionData.getDateprescription());
        prescription.setDategeneration(prescriptionData.getDategeneration());
        return prescriptionRepository.save(prescription);
    }

    public void deletePrescription(Integer id) {
        prescriptionRepository.deleteById(id);
    }
}
