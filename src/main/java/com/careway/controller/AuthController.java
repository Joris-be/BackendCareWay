package com.careway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careway.dto.LoginRequest;
import com.careway.dto.LoginResponse;
import com.careway.entity.Medecin;
import com.careway.entity.Patient;
import com.careway.service.MedecinService;
import com.careway.service.PatientService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final MedecinService medecinService;
    private final PatientService patientService;

    public AuthController(MedecinService medecinService, PatientService patientService) {
        this.medecinService = medecinService;
        this.patientService = patientService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            if ("medecin".equalsIgnoreCase(request.getUserType())) {
                // Medecin login with RPPS code
                Medecin medecin = medecinService.getMedecinByRPPS(request.getCode())
                        .orElse(null);

                if (medecin == null) {
                    return ResponseEntity.ok(
                            new LoginResponse(false, "RPPS code non trouvé", "medecin", null, null));
                }

                if (!medecinService.validateMedecinPassword(medecin, request.getPassword())) {
                    return ResponseEntity.ok(
                            new LoginResponse(false, "Mot de passe incorrect", "medecin", null, null));
                }

                return ResponseEntity.ok(
                        new LoginResponse(
                                true,
                                "Connexion réussie",
                                "medecin",
                                medecin.getIdmedecin(),
                                medecin.getPrenom() + " " + medecin.getNom(),
                                medecin.getSpecialite(),
                                medecin.getMail()));

            } else if ("patient".equalsIgnoreCase(request.getUserType())) {
                // Patient login with NSS code
                Patient patient = patientService.getPatientByNSS(request.getCode());

                if (patient == null) {
                    return ResponseEntity.ok(
                            new LoginResponse(false, "NSS non trouvé", "patient", null, null));
                }

                if (!patientService.validatePatientPassword(patient, request.getPassword())) {
                    return ResponseEntity.ok(
                            new LoginResponse(false, "Mot de passe incorrect", "patient", null, null));
                }

                return ResponseEntity.ok(
                        new LoginResponse(
                                true,
                                "Connexion réussie",
                                "patient",
                                patient.getIdpatient(),
                                patient.getPrenom() + " " + patient.getNom()));

            } else {
                return ResponseEntity.ok(
                        new LoginResponse(false, "Type d'utilisateur invalide", null, null, null));
            }

        } catch (Exception e) {
            return ResponseEntity.ok(
                    new LoginResponse(false, "Erreur lors de la connexion: " + e.getMessage(), null, null, null));
        }
    }
}
