package com.careway.controller;

import com.careway.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
@CrossOrigin(origins = "http://localhost:5188", allowCredentials = "true")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllHospitals() {
        return ResponseEntity.ok(hospitalService.getAllHospitals());
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchHospitals(@RequestParam(required = false) String query) {
        return ResponseEntity.ok(hospitalService.searchHospitals(query));
    }
}
