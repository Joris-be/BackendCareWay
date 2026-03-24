package com.careway.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.careway.dao.EvaluationRepository;
import com.careway.dao.PatientRepository;
import com.careway.dao.TransportRepository;
import com.careway.dto.EvaluationDTO;
import com.careway.entity.Evaluation;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final PatientRepository patientRepository;
    private final TransportRepository transportRepository;

    public EvaluationService(EvaluationRepository evaluationRepository,
                              PatientRepository patientRepository,
                              TransportRepository transportRepository) {
        this.evaluationRepository = evaluationRepository;
        this.patientRepository = patientRepository;
        this.transportRepository = transportRepository;
    }

    // Convertir Evaluation en EvaluationDTO
    private EvaluationDTO toDTO(Evaluation evaluation) {
        EvaluationDTO dto = new EvaluationDTO();
        dto.setIdevaluation(evaluation.getIdevaluation());
        dto.setNote(evaluation.getNote());
        dto.setCommentaire(evaluation.getCommentaire());
        dto.setIdpatient(evaluation.getPatient() != null ? evaluation.getPatient().getIdpatient() : null);
        dto.setIdtransport(evaluation.getTransport() != null ? evaluation.getTransport().getIdtransport() : null);
        return dto;
    }

    // Récupérer toutes les évaluations
    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer une évaluation par son id
    public EvaluationDTO getEvaluationById(Integer id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation non trouvée avec l'id : " + id));
        return toDTO(evaluation);
    }

    // Créer ou modifier une évaluation
    public EvaluationDTO saveEvaluation(EvaluationDTO dto) {
         if (dto.getNote() < 0 || dto.getNote() > 5
            || (dto.getNote() * 2) % 1 != 0) {
        throw new IllegalArgumentException("La note doit être entre 0 et 5 par pas de 0.5");
    }
        Evaluation evaluation = new Evaluation();
        evaluation.setIdevaluation(dto.getIdevaluation());
        evaluation.setNote(dto.getNote());
        evaluation.setCommentaire(dto.getCommentaire());

        if (dto.getIdpatient() != null) {
            evaluation.setPatient(patientRepository.findById(dto.getIdpatient())
                    .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id : " + dto.getIdpatient())));
        }
        if (dto.getIdtransport() != null) {
            evaluation.setTransport(transportRepository.findById(dto.getIdtransport())
                    .orElseThrow(() -> new RuntimeException("Transport non trouvé avec l'id : " + dto.getIdtransport())));
        }

        return toDTO(evaluationRepository.save(evaluation));
    }

    // Supprimer une évaluation
    public void deleteEvaluation(Integer id) {
        evaluationRepository.deleteById(id);
    }
}