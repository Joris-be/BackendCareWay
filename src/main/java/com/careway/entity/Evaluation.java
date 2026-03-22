package com.careway.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Evaluation {
    @Id 
    @Basic(optional=false)
    @NonNull
    private Integer idevaluation;
    
    @Basic(optional=false)
    @NonNull
    private float note;
   
    @Basic(optional=false)
    @NonNull
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "idpatient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "idtransport")
    private Transport transport;
}
