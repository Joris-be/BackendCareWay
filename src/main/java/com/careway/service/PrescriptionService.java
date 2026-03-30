package com.careway.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careway.dao.PrescriptionRepository;
import com.careway.dto.PrescriptionDTO;
import com.careway.entity.Prescription;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<PrescriptionDTO> getAllPrescriptions() {
        return prescriptionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PrescriptionDTO getPrescriptionById(Integer id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        return convertToDTO(prescription);
    }

    public PrescriptionDTO savePrescription(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = convertToEntity(prescriptionDTO);
        Prescription saved = prescriptionRepository.save(prescription);
        return convertToDTO(saved);
    }

    public PrescriptionDTO updatePrescription(Integer id, PrescriptionDTO prescriptionDTO) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        prescription.setDate(prescriptionDTO.getDate());
        prescription.setTransportType(prescriptionDTO.getTransportType());
        prescription.setStatus(prescriptionDTO.getStatus());
        prescription.setNotes(prescriptionDTO.getNotes());
        Prescription updated = prescriptionRepository.save(prescription);
        return convertToDTO(updated);
    }

    public void deletePrescription(Integer id) {
        prescriptionRepository.deleteById(id);
    }

    private PrescriptionDTO convertToDTO(Prescription prescription) {
        PrescriptionDTO dto = new PrescriptionDTO();
        dto.setId(prescription.getId());
        dto.setPatientId(prescription.getPatientId());
        dto.setDate(prescription.getDate());
        dto.setTransportType(prescription.getTransportType());
        dto.setStatus(prescription.getStatus());
        dto.setNotes(prescription.getNotes());
        return dto;
    }

    private Prescription convertToEntity(PrescriptionDTO dto) {
        Prescription prescription = new Prescription();
        prescription.setPatientId(dto.getPatientId());
        prescription.setDate(dto.getDate());
        prescription.setTransportType(dto.getTransportType());
        prescription.setStatus(dto.getStatus());
        prescription.setNotes(dto.getNotes());
        return prescription;
    }
}
