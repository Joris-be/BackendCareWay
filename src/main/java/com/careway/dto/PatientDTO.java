package com.careway.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatientDTO {
    private Integer idpatient;
    private String prenom;
    private String nom;
    private Date datenaiss;
    private String nss;
    private String adresse;
    private String tel;
    private String mail;
    private String genre;
    private String pays;
    private List<Integer> idEvaluations;
    private List<Integer> idNotes;
    private List<Integer> idPrescriptions;
}