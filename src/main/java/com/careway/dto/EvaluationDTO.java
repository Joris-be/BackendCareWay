package com.careway.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EvaluationDTO {
    private Integer idevaluation;
    private float note;
    private String commentaire;
    private Integer idpatient;
    private Integer idtransport;
}
