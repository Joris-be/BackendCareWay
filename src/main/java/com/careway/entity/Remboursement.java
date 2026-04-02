package com.careway.entity;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Remboursement {

    @Id
    @Basic(optional = false)
    @NonNull
    private Integer idremboursement;

    @Basic(optional = false)
    @NonNull
    private float montant;

    @Basic(optional = false)
    @NonNull
    private float tauxprisencharge;

    @Basic(optional = false)
    @NonNull
    private String statutremboursement;

    @Basic(optional = false)
    @NonNull
    private Date dateremboursement;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "idtransport")
    private Transport transport;
}
