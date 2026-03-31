package com.careway.dto;

import com.careway.entity.TypeTransport;

import lombok.Data;

@Data
public class PatientFavorisDTO {
    private Integer idPatient;
    private Integer idTransporteur;
    private TypeTransport typeTransport;
}