package com.careway.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.careway.entity.Remboursement;
import com.careway.service.RemboursementService;

@RestController
@RequestMapping("/remboursements")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "https://careway-frontend.onrender.com"}, 
             methods = {org.springframework.web.bind.annotation.RequestMethod.GET, 
                       org.springframework.web.bind.annotation.RequestMethod.POST,
                       org.springframework.web.bind.annotation.RequestMethod.PUT,
                       org.springframework.web.bind.annotation.RequestMethod.DELETE,
                       org.springframework.web.bind.annotation.RequestMethod.OPTIONS},
             allowCredentials = "true",
             maxAge = 3600)
public class RemboursementController {
    private final RemboursementService remboursementService;

    public RemboursementController(RemboursementService remboursementService) {
        this.remboursementService = remboursementService;
    }

    /**
     * Récupère tous les remboursements
     */
    @GetMapping
    public List<Remboursement> getAllRemboursements() {
        return remboursementService.getAllRemboursements();
    }

    /**
     * Récupère tous les remboursements d'un patient
     * 
     * @param patientId ID du patient
     * @return Liste des remboursements du patient
     */
    @GetMapping("/patient/{patientId}")
    public List<Remboursement> getRemboursementsByPatientId(@PathVariable Integer patientId) {
        return remboursementService.getRemboursementsByPatientId(patientId);
    }

    /**
     * Récupère un remboursement par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Remboursement> getRemboursementById(@PathVariable Integer id) {
        return ResponseEntity.ok(remboursementService.getRemboursementById(id));
    }

    /**
     * Crée ou met à jour un remboursement
     */
    @PostMapping
    public ResponseEntity<Remboursement> createRemboursement(@RequestBody Remboursement remboursement) {
        return ResponseEntity.ok(remboursementService.saveRemboursement(remboursement));
    }

    /**
     * Met à jour un remboursement
     */
    @PutMapping("/{id}")
    public ResponseEntity<Remboursement> updateRemboursement(@PathVariable Integer id,
            @RequestBody Remboursement remboursement) {
        remboursement.setIdremboursement(id);
        return ResponseEntity.ok(remboursementService.saveRemboursement(remboursement));
    }

    /**
     * Supprime un remboursement
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Integer id) {
        remboursementService.deleteRemboursement(id);
        return ResponseEntity.noContent().build();
    }
}
