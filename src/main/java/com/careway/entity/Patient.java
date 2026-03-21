package com.careway.entity;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
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
public class Patient {
    
    @Id 
    @Basic(optional=false)
    @NonNull
    private Integer idpatient;
    
    @Basic(optional=false)
    @NonNull
    private String nom;
   
    @Basic(optional=false)
    @NonNull
    private Date datenaiss;

    @Basic(optional=false)
    @NonNull
    @Column(unique = true)
    private String nss;

    @Basic(optional=false)
    @NonNull
    private String adresse;
    
    @Basic(optional=false)
    @NonNull
    private String tel;

    @Basic(optional=false)
    @NonNull
    private String mail;

    @Basic(optional=false)
    @NonNull
    private String genre;
   
    @Basic(optional=false)
    @NonNull
    private String pays;

}
