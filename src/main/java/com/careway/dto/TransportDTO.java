package com.careway.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransportDTO {
    private Integer idtransport;
    private Date datetransport;
    private String lieudepart;
    private String lieuarrive;
    private String typetransport;
    private Integer idpatient;
    private String statut;
    private TransporteurDTO transporteur;
}