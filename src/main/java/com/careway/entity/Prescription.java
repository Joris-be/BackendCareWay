package com.careway.entity;

import java.util.Date;

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
public class Prescription {
    
    @Id 
    @Basic(optional=false)
    @NonNull
    private Integer idprescription;
    
    @Basic(optional=false)
    @NonNull
    private String motifmedical;
   
    @Basic(optional=false)
    @NonNull
    private String typetransport;

    @Basic(optional=false)
    @NonNull
    private Date dateprescription;

    @Basic(optional=false)
    @NonNull
    private Date dategeneration;

    @ManyToOne
    @JoinColumn(name = "idmedecin")
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "idpatient")
    private Patient patient;

}
