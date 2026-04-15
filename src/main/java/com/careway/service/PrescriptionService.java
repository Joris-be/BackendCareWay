package com.careway.service;

import java.time.LocalDateTime;
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
import com.careway.entity.Notification;
import com.careway.service.PrescriptionPDFService.PrescriptionFormData;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final PrescriptionPDFService pdfService;
    private final NotificationService notificationService;
    private final TransportService transportService;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
            PatientRepository patientRepository,
            MedecinRepository medecinRepository,
            PrescriptionPDFService pdfService,
            NotificationService notificationService,
            TransportService transportService) {
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.pdfService = pdfService;
        this.notificationService = notificationService;
        this.transportService = transportService;
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public List<Prescription> getPrescriptionsByMedecinId(Integer medecinId) {
        return prescriptionRepository.findAll().stream()
                .filter(p -> p.getMedecin() != null && p.getMedecin().equals(medecinId))
                .collect(Collectors.toList());
    }

    public List<Prescription> getPrescriptionsByPatientId(Integer patientId) {
        return prescriptionRepository.findAll().stream()
                .filter(p -> p.getIdpatient() != null && p.getIdpatient().equals(patientId))
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
                formData.getVehiculeType(),
                formData.getTrajet_depart(),
                formData.getTrajet_depart_autre(),
                formData.getTrajet_depart_structure(),
                formData.getTrajet_arrivee(),
                formData.getTrajet_arrivee_autre(),
                formData.getTrajet_arrivee_structure(),
                formData.getNombre_transports(),
                formData.getExoneration(),
                formData.getPension_militaire());

        // Ajouter la date du transport programmé
        pdfFormData.setDate_transport(formData.getDate_transport());

        // Générer le PDF
        byte[] pdfBytes = pdfService.generatePrescriptionPDF(patient, medecin, pdfFormData);

        // Créer la prescription
        Prescription prescription = new Prescription();
        prescription.setIdpatient(formData.getPatientId());
        prescription.setMedecin(formData.getMedecinId());
        prescription.setMotifmedical(
                String.join(", ", formData.getSituation1() != null ? formData.getSituation1() : new String[0]));
        prescription
                .setTypetransport(formData.getVehiculeType() != null ? formData.getVehiculeType() : "Non spécifié");
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

        Prescription savedPrescription = prescriptionRepository.save(prescription);

        // Créer une notification pour le patient
        createPrescriptionNotification(patient, medecin, formData.getMode_transport());

        // Créer automatiquement un transport si une date est spécifiée
        if (formData.getDate_transport() != null && !formData.getDate_transport().isEmpty()) {
            try {
                // Déterminer le lieu de départ
                String lieuDepart = "Domicile du patient";
                if ("autre".equals(formData.getTrajet_depart())) {
                    lieuDepart = formData.getTrajet_depart_autre();
                } else if ("structure".equals(formData.getTrajet_depart())) {
                    lieuDepart = formData.getTrajet_depart_structure();
                }

                // Déterminer le lieu d'arrivée
                String lieuArrivee = "Hôpital";
                if ("autre".equals(formData.getTrajet_arrivee())) {
                    lieuArrivee = formData.getTrajet_arrivee_autre();
                } else if ("hospital".equals(formData.getTrajet_arrivee())) {
                    lieuArrivee = formData.getTrajet_arrivee_structure();
                }

                // Convertir la date de string à Date
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                Date dateTransport = sdf.parse(formData.getDate_transport());

                // Créer le transport avec statut "EN_ATTENTE"
                transportService.createTransportFromPrescription(
                        formData.getPatientId(),
                        dateTransport,
                        lieuDepart,
                        lieuArrivee,
                        formData.getVehiculeType());
            } catch (Exception e) {
                // Si la création du transport échoue, ne pas bloquer la création de la
                // prescription
                System.err.println("Erreur lors de la création du transport : " + e.getMessage());
            }
        }

        return savedPrescription;
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

    /**
     * Génère un PDF pour une prescription si elle n'en a pas
     * 
     * @param prescriptionId ID de la prescription
     * @return Les données PDF générées
     */
    public byte[] generatePDFIfNeeded(Integer prescriptionId) throws Exception {
        Prescription prescription = getPrescriptionById(prescriptionId);

        // Si le PDF existe déjà, le retourner
        if (prescription.getPdfData() != null && prescription.getPdfData().length > 0) {
            return prescription.getPdfData();
        }

        // Récupérer les données du patient et du médecin
        Patient patient = patientRepository.findById(prescription.getIdpatient())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));

        Medecin medecin = null;
        if (prescription.getMedecin() != null) {
            medecin = medecinRepository.findById(prescription.getMedecin())
                    .orElse(null);
        }

        // Créer des données de formulaire basiques
        PrescriptionFormData pdfFormData = new PrescriptionFormData(
                new String[] { prescription.getMotifmedical() },
                prescription.getDateprescription().toString(),
                prescription.getTypetransport(),
                prescription.getTypetransport(),
                "domicile",
                "",
                "",
                "domicile",
                "",
                "",
                1,
                new String[] {},
                new String[] {});

        // Générer le PDF
        byte[] pdfBytes = pdfService.generatePrescriptionPDF(patient, medecin, pdfFormData);

        // Stocker le PDF généré
        prescription.setPdfData(pdfBytes);
        prescriptionRepository.save(prescription);

        return pdfBytes;
    }

    /**
     * Crée une notification pour le patient quand une prescription est créée
     */
    private void createPrescriptionNotification(Patient patient, Medecin medecin, String typeTransport) {
        try {
            Notification notification = new Notification();
            notification.setPatientId(patient.getIdpatient());
            notification.setTitle("Nouvelle prescription");
            notification.setMessage("Dr. " + medecin.getPrenom() + " " + medecin.getNom()
                    + " vous a créé une prescription : " + typeTransport);
            notification.setType("PRESCRIPTION");
            notification.setDate(LocalDateTime.now().toString());
            notification.setRead(false);

            notificationService.saveNotification(notification);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la notification : " + e.getMessage());
        }
    }
}
