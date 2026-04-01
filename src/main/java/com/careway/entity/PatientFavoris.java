package com.careway.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "patient_favori", uniqueConstraints = @UniqueConstraint(columnNames = { "idpatient", "type_transport" }))
public class PatientFavoris {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idpatient")
    @NonNull
    @JsonIgnore
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idtransporteur")
    @NonNull
    private Transporteur transporteur;

    // Type de transport : "AMBULANCE", "VSL", "TAXI"

    @Enumerated(EnumType.STRING)
    @Column(name = "type_transport", nullable = false, length = 20)
    @NonNull
    private TypeTransport typetransport;
}