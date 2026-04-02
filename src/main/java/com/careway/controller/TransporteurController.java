package com.careway.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careway.dao.TransporteurRepository;
import com.careway.dto.TransporteurDTO;
import com.careway.entity.Transporteur;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transporteurs")
@RequiredArgsConstructor
public class TransporteurController {

    private final TransporteurRepository transporteurRepository;

    @GetMapping
    public ResponseEntity<List<TransporteurDTO>> getAllTransporteurs() {
        List<Transporteur> transporteurs = transporteurRepository.findAll();
        List<TransporteurDTO> dtos = transporteurs.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/type/{typeTransport}")
    public ResponseEntity<List<TransporteurDTO>> getTransporteursByType(@PathVariable String typeTransport) {
        // Pour maintenant, retourner tous les transporteurs
        // (La vraie implémentation nécessiterait une relation directe entre
        // Transporteur et TypeTransport)
        List<Transporteur> transporteurs = transporteurRepository.findAll();
        List<TransporteurDTO> dtos = transporteurs.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private TransporteurDTO toDTO(Transporteur transporteur) {
        return new TransporteurDTO(
                transporteur.getIdtransporteur(),
                transporteur.getNom(),
                transporteur.getPrenom(),
                transporteur.getTel(),
                transporteur.getMail());
    }
}
