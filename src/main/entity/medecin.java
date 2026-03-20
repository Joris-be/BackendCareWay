package com.careway.backend;
import java.util.ArrayList;
import java.util.List;
 
import jakarta.persistence.*;
 
import lombok.*;
 

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class medecin {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(unique = true)
    @NonNull
    private String mail;


}