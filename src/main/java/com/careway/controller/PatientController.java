package com.careway.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careway.dto.ChangePasswordDTO;
import com.careway.dto.PatientDTO;
import com.careway.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Integer id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.savePatient(patientDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Integer id, @RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/medecin/{medecinId}")
    public List<PatientDTO> getPatientsByMedecinId(@PathVariable Integer medecinId) {
        return patientService.getPatientsByMedecinId(medecinId);
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Integer id, @RequestBody ChangePasswordDTO dto) {
        try {
            patientService.changePassword(id, dto.getCurrentPassword(), dto.getNewPassword());
            return ResponseEntity.ok("Mot de passe changé avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}