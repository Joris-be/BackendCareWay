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
    
}
