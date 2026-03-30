package com.careway.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.careway.dao.PrescriptionRepository;
import com.careway.entity.Prescription;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Prescription getPrescriptionById(Integer id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription non trouvée"));
    }

    public Prescription savePrescription(Prescription prescription) {
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
