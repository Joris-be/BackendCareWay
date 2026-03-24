package com.careway.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransporteurDTO {
    private Integer idtransporteur;
    private String nom;
    private String prenom;
    private String tel;
    private String mail;
    private List<Integer> idNotes;
    private Integer idtransport;
}
