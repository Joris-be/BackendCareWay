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
import com.careway.entity.Transport;
import com.careway.service.TransportService;

@RestController
@RequestMapping("/transports")
public class TransportController {
    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @GetMapping
    public List<Transport> getAllTransports() {
        return transportService.getAllTransports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transport> getTransportById(@PathVariable Integer id) {
        return ResponseEntity.ok(transportService.getTransportById(id));
    }

    @GetMapping("/patient/{idpatient}")
    public List<TransportDTO> getTransportsByPatient(@PathVariable Integer idpatient) {
        return transportService.getTransportsByPatient(idpatient);
    }

    @PostMapping
    public ResponseEntity<Transport> createTransport(@RequestBody Transport transport) {
        return ResponseEntity.ok(transportService.saveTransport(transport));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transport> updateTransport(@PathVariable Integer id,
            @RequestBody Transport transport) {
        return ResponseEntity.ok(transportService.updateTransport(id, transport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransport(@PathVariable Integer id) {
        transportService.deleteTransport(id);
        return ResponseEntity.ok().build();
    }
}
