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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class Prescription {

    @Id
    @Basic(optional = false)
    @NonNull
    private Integer idprescription;

    @Basic(optional = false)
    @NonNull
    private String motifmedical;

    @Basic(optional = false)
    @NonNull
    private String typetransport;

    @Basic(optional = false)
    @NonNull
    private Date dateprescription;

    @Basic(optional = false)
    @NonNull
    private Date dategeneration;

    private Integer idpatient;

    private String medecin;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idmedecin")
    private Medecin medecinObj;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idpatient", insertable = false, updatable = false)
    private Patient patient;

}
