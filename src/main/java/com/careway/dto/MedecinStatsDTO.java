package com.careway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MedecinStatsDTO {
    private Integer medecinId;
    private String medecinNom;
    private String specialite;
    private Integer prescriptionsCount;
    private Double averagePrescriptions;
    private Integer patientsCount;
    private Integer excellentRating; // prescriptions avec feedback excellent
    private String topDiseaseByMedecin; // maladie la plus traitée par ce médecin
    private String topTransportType; // type de transport le plus prescrit
}
