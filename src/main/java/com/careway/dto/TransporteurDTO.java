package com.careway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransporteurDTO {
    private Integer idtransporteur;
    private String nom;
    private String prenom;
    private String tel;
    private String mail;
}
