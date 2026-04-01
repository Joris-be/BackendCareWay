package com.careway.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Etape {

    @Id
    @Basic(optional = false)
    @NonNull
    private Integer idetape;

    @Basic(optional = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idtransport")
    private Transport transport;
}
