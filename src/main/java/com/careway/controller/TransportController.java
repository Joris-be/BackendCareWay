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

import com.careway.dto.TransportDTO;
import com.careway.service.TransportService;

@RestController
@RequestMapping("/transports")
public class TransportController {

    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @GetMapping
    public List<TransportDTO> getAllTransports() {
        return transportService.getAllTransports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportDTO> getTransportById(@PathVariable Integer id) {
        return ResponseEntity.ok(transportService.getTransportById(id));
    }

    @PostMapping
    public ResponseEntity<TransportDTO> createTransport(@RequestBody TransportDTO transportDTO) {
        return ResponseEntity.ok(transportService.saveTransport(transportDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransportDTO> updateTransport(@PathVariable Integer id,
            @RequestBody TransportDTO transportDTO) {
        return ResponseEntity.ok(transportService.updateTransport(id, transportDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransport(@PathVariable Integer id) {
        transportService.deleteTransport(id);
        return ResponseEntity.noContent().build();
    }
}
