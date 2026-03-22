package com.careway.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Transport {
    
    @Id 
    @Basic(optional=false)
    @NonNull
    private Integer idtransport;
    
    @Basic(optional=false)
    @NonNull
    private Date datetransport;
   
    @Basic(optional=false)
    @NonNull
    private String lieudepart;

    @Basic(optional=false)
    @NonNull
    private String lieuarrive;

    @Basic(optional=false)
    @NonNull
    private String typetransport;
    
    // un transport compose plusieurs étapes
    @OneToMany(mappedBy = "transport")
    private List<Etape> etapes; 

    // un transport possède plusieurs évaluations
    @OneToMany(mappedBy = "transport")
    private List<Evaluation> evaluations; 

    // un transport a 0 ou 1 remboursement
    @OneToOne(mappedBy = "transport")
    private Remboursement remboursement;

    @OneToMany(mappedBy = "transport")
    private List<Transporteur> transporteurs;
}
