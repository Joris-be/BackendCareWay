package com.careway.controller;

import com.careway.dto.PatientFavorisDTO;
import com.careway.entity.TypeTransport;
import com.careway.service.PatientFavorisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients/{idPatient}/favoris")
@RequiredArgsConstructor
public class PatientFavorisController {

    private final PatientFavorisService patientFavorisService;

    @GetMapping
    public ResponseEntity<List<PatientFavorisDTO>> getFavoris(@PathVariable Integer idPatient) {
        return ResponseEntity.ok(patientFavorisService.getFavoris(idPatient));
    }

    @GetMapping("/{typetransport}")
    public ResponseEntity<PatientFavorisDTO> getFavoriByType(
            @PathVariable Integer idPatient,
            @PathVariable TypeTransport typetransport) {
        return ResponseEntity.ok(patientFavorisService.getFavoriByType(idPatient, typetransport));
    }

    @PutMapping
    public ResponseEntity<PatientFavorisDTO> setFavori(
            @PathVariable Integer idPatient,
            @RequestBody PatientFavorisDTO dto) {
        dto.setIdPatient(idPatient);
        return ResponseEntity.ok(patientFavorisService.setFavori(dto));
    }

    @DeleteMapping("/{typetransport}")
    public ResponseEntity<Void> deleteFavori(
            @PathVariable Integer idPatient,
            @PathVariable TypeTransport typetransport) {
        patientFavorisService.deleteFavori(idPatient, typetransport);
        return ResponseEntity.noContent().build();
    }
}