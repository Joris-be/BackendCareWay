package com.careway.entity;

import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "transporteur")
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "idtransport")
    private Transport transport;

    @OneToMany(mappedBy = "transporteur")
    private List<PatientFavoris> patientsFavoris;
}
