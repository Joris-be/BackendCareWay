package com.careway.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class Transport {

    @Id
    @Basic(optional = false)
    @NonNull
    private Integer idtransport;

    @Basic(optional = false)
    @NonNull
    private Date datetransport;

    @Basic(optional = false)
    @NonNull
    private String lieudepart;

    @Basic(optional = false)
    @NonNull
    private String lieuarrive;

    @Basic(optional = false)
    @NonNull
    private String typetransport;

    // lien vers le patient
    private Integer idpatient;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idpatient", insertable = false, updatable = false)
    private Patient patient;

    // statut du transport : PLANIFIE, EN_COURS, TERMINE
    private String statut;

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
