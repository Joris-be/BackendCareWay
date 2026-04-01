package com.careway.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idevaluation;

    @Basic(optional = false)
    private float note;

    @Basic(optional = false)
    private String commentaire;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idpatient")
    private Patient patient;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idtransport")
    private Transport transport;
}
