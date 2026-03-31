package com.careway.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.careway.entity.Prescription;
import com.careway.dto.PrescriptionFormDTO;
import com.careway.service.PrescriptionService;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Integer id) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }

    @GetMapping("/medecin/{medecinId}")
    public List<Prescription> getPrescriptionsByMedecinId(@PathVariable Integer medecinId) {
        return prescriptionService.getPrescriptionsByMedecinId(medecinId);
    }

    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
        return ResponseEntity.ok(prescriptionService.savePrescription(prescription));
    }

    /**
     * Crée une prescription avec génération automatique du PDF
     * 
     * @param formData Données du formulaire depuis le frontend
     * @return La prescription créée avec le PDF
     */
    @PostMapping("/create-with-pdf")
    public ResponseEntity<Prescription> createPrescriptionWithPDF(@RequestBody PrescriptionFormDTO formData) {
        try {
            Prescription prescription = prescriptionService.createPrescriptionWithPDF(formData);
            return ResponseEntity.ok(prescription);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Télécharge le PDF d'une prescription
     * 
     * @param id ID de la prescription
     * @return Le PDF en tant que fichier binaire
     */
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPrescriptionPDF(@PathVariable Integer id) {
        Prescription prescription = prescriptionService.getPrescriptionById(id);

        if (prescription.getPdfData() == null || prescription.getPdfData().length == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"prescription_" + id + ".pdf\"")
                .body(prescription.getPdfData());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable Integer id,
            @RequestBody Prescription prescription) {
        return ResponseEntity.ok(prescriptionService.updatePrescription(id, prescription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Integer id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}
