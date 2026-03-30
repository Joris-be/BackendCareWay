package com.careway.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransportDTO {
    private Integer idtransport;
    private Date datetransport;
    private String lieudepart;
    private String lieuarrive;
    private String typetransport;
    private List<Integer> idEtapes;
    private List<Integer> idEvaluations;
    private Integer idRemboursement;
    private List<Integer> idTransporteurs;
}