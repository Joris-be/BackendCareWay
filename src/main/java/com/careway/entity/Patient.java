package com.careway.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class Patient {

    @Id
    @Basic(optional = false)
    @NonNull
    private Integer idpatient;

    @Basic(optional = false)
    @NonNull
    private String prenom;

    @Basic(optional = false)
    @NonNull
    private String nom;

    @Basic(optional = false)
    @NonNull
    private Date datenaiss;

    @Basic(optional = false)
    @NonNull
    @Column(unique = true)
    private String nss;

    @Basic(optional = false)
    @NonNull
    private String motdepasse;

    @Basic(optional = false)
    @NonNull
    private String adresse;

    @Basic(optional = false)
    @NonNull
    private String maladie;

    @Basic(optional = false)
    @NonNull
    private String tel;

    @Basic(optional = false)
    @NonNull
    private String mail;

    @Basic(optional = false)
    @NonNull
    private String genre;

    @Basic(optional = true)
    private String image;

    @Basic(optional = false)
    @NonNull
    private String pays;

    // un patient possède plusieurs prescriptions
    @OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptions;

    // un patient écrit plusieurs évaluations
    @OneToMany(mappedBy = "patient")
    private List<Evaluation> evaluations;

    // un patient donne plusieurs notes
    @OneToMany(mappedBy = "patient")
    private List<Note> notes;

    // un patient possède des transporteurs favoris
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientFavoris> favoris;
}
