package com.careway.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RemboursementDTO {
    private Integer idremboursement;
    private float montant;
    private float tauxprisencharge;
    private String statutremboursement;
    private Date dateremboursement;
    private Integer idtransport;
}