package com.careway.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MedecinDTO {
    private Integer idmedecin;
    private String nom;
    private String prenom;
    private String specialite;
    private String rpps;
    private String mail;
    private List<Integer> idPrescriptions;
}