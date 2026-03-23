package com.careway.entity;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Medecin {
    
    @Id 
    @Basic(optional=false)
    @NonNull
    private Integer idmedecin;
    
    @Basic(optional=false)
    @NonNull
    private String nom;
   
    @Basic(optional=false)
    @NonNull
    private String prenom;

    @Basic(optional=false)
    @NonNull
    private String specialite;

    @Basic(optional=false)
    @Column(unique = true)
    @NonNull
    private String rpps;

    @Basic(optional=false)
    @NonNull
    private String mail;

    // un médecin écrit plusieurs prescriptions
    @OneToMany(mappedBy = "medecin")
    private List<Prescription> prescriptions;
}