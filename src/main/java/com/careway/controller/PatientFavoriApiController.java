package com.careway.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careway.dto.PatientFavorisDTO;
import com.careway.entity.TypeTransport;
import com.careway.service.PatientFavorisService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patient-favori")
@RequiredArgsConstructor
public class PatientFavoriApiController {

    private final PatientFavorisService patientFavorisService;

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PatientFavorisDTO>> getFavoris(@PathVariable Integer patientId) {
        return ResponseEntity.ok(patientFavorisService.getFavoris(patientId));
    }

    @PostMapping("/set")
    public ResponseEntity<PatientFavorisDTO> setFavori(
            @RequestParam Integer patientId,
            @RequestParam Integer transporteurId,
            @RequestParam TypeTransport typeTransport) {

        PatientFavorisDTO dto = new PatientFavorisDTO();
        dto.setIdPatient(patientId);
        dto.setIdTransporteur(transporteurId);
        dto.setTypeTransport(typeTransport);

        return ResponseEntity.ok(patientFavorisService.setFavori(dto));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeFavori(
            @RequestParam Integer patientId,
            @RequestParam TypeTransport typeTransport) {

        patientFavorisService.deleteFavori(patientId, typeTransport);
        return ResponseEntity.noContent().build();
    }
}
