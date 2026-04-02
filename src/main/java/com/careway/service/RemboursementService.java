package com.careway.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.careway.dao.RemboursementRepository;
import com.careway.entity.Remboursement;

@Service
public class RemboursementService {
    private final RemboursementRepository remboursementRepository;

    public RemboursementService(RemboursementRepository remboursementRepository) {
        this.remboursementRepository = remboursementRepository;
    }

    public List<Remboursement> getAllRemboursements() {
        return remboursementRepository.findAll();
    }

    public List<Remboursement> getRemboursementsByPatientId(Integer patientId) {
        return remboursementRepository.findAll().stream()
                .filter(r -> r.getPatientId() != null && r.getPatientId().equals(patientId))
                .collect(Collectors.toList());
    }

    public Remboursement getRemboursementById(Integer id) {
        return remboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Remboursement non trouvé"));
    }

    public Remboursement saveRemboursement(Remboursement remboursement) {
        return remboursementRepository.save(remboursement);
    }

    public void deleteRemboursement(Integer id) {
        remboursementRepository.deleteById(id);
    }
}
