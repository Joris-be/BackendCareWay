package com.careway.entity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
@Table(name = "medecin")
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


}