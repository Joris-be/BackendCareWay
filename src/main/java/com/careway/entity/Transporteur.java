package com.careway.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Transporteur {
    @Id 
    @Basic(optional=false)
    @NonNull
    private Integer idtransporteur;
    
    @Basic(optional=false)
    @NonNull
    private String nom;
   
    @Basic(optional=false)
    @NonNull
    private String prenom;

    @Basic(optional=false)
    @NonNull
    private String tel;

    @Basic(optional=false)
    @NonNull
    private String mail;
}
