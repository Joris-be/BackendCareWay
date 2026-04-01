package com.careway.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.careway.dao.PatientRepository;
import com.careway.dao.TransportRepository;
import com.careway.dto.EvaluationDTO;
import com.careway.entity.Evaluation;
import com.careway.entity.Patient;
import com.careway.entity.Transport;
import com.careway.service.EvaluationService;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {
    private final EvaluationService evaluationService;
    private final PatientRepository patientRepository;
    private final TransportRepository transportRepository;

    public EvaluationController(EvaluationService evaluationService, PatientRepository patientRepository,
            TransportRepository transportRepository) {
        this.evaluationService = evaluationService;
        this.patientRepository = patientRepository;
        this.transportRepository = transportRepository;
    }

    @GetMapping
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Integer id) {
        return ResponseEntity.ok(evaluationService.getEvaluationById(id));
    }

    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        // Convertir le DTO en Evaluation
        Evaluation evaluation = new Evaluation();

        // Calculer une note moyenne entre la note de transport et driver
        float averageRating = (evaluationDTO.getTransportRating() + evaluationDTO.getDriverRating()) / 2f;
        evaluation.setNote(averageRating);

        // Utiliser le feedback comme commentaire
        String commentaire = evaluationDTO.getFeedback();
        if (commentaire == null || commentaire.isEmpty()) {
            commentaire = evaluationDTO.getNotes();
        }
        evaluation.setCommentaire(commentaire != null ? commentaire : "");

        // Récupérer et assigner les relations
        Patient patient = patientRepository.findById(evaluationDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));
        Transport transport = transportRepository.findById(evaluationDTO.getTransportId())
                .orElseThrow(() -> new RuntimeException("Transport non trouvé"));

        evaluation.setPatient(patient);
        evaluation.setTransport(transport);

        Evaluation savedEvaluation = evaluationService.saveEvaluation(evaluation);
        return ResponseEntity.ok(savedEvaluation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Integer id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }
}