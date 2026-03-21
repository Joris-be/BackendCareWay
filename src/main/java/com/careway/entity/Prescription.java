package com.careway.entity;

import java.util.Date;

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

}
