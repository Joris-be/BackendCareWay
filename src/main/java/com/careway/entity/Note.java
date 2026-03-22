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
public class Note {
    
    @Id 
    @Basic(optional=false)
    @NonNull
    private Integer idnote;
    
    @Basic(optional=false)
    private float nombreetoiles;
}
