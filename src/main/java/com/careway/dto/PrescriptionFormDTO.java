package com.careway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrescriptionFormDTO {
    private Integer patientId;
    private Integer medecinId;

    @JsonProperty("situation1")
    private String[] situation1;

    @JsonProperty("date_at_mp")
    private String date_at_mp;

    @JsonProperty("mode_transport")
    private String mode_transport;

    @JsonProperty("trajet_depart")
    private String trajet_depart;

    @JsonProperty("trajet_depart_autre")
    private String trajet_depart_autre;

    @JsonProperty("trajet_depart_structure")
    private String trajet_depart_structure;

    @JsonProperty("trajet_arrivee")
    private String trajet_arrivee;

    @JsonProperty("trajet_arrivee_autre")
    private String trajet_arrivee_autre;

    @JsonProperty("trajet_arrivee_structure")
    private String trajet_arrivee_structure;

    @JsonProperty("nombre_transports")
    private Integer nombre_transports;

    @JsonProperty("exoneration")
    private String[] exoneration;

    @JsonProperty("pension_militaire")
    private String[] pension_militaire;

    // Constructeurs
    public PrescriptionFormDTO() {
    }

    public PrescriptionFormDTO(Integer patientId, Integer medecinId, String[] situation1, String date_at_mp,
            String mode_transport, String trajet_depart, String trajet_depart_autre,
            String trajet_depart_structure, String trajet_arrivee, String trajet_arrivee_autre,
            String trajet_arrivee_structure, Integer nombre_transports, String[] exoneration,
            String[] pension_militaire) {
        this.patientId = patientId;
        this.medecinId = medecinId;
        this.situation1 = situation1;
        this.date_at_mp = date_at_mp;
        this.mode_transport = mode_transport;
        this.trajet_depart = trajet_depart;
        this.trajet_depart_autre = trajet_depart_autre;
        this.trajet_depart_structure = trajet_depart_structure;
        this.trajet_arrivee = trajet_arrivee;
        this.trajet_arrivee_autre = trajet_arrivee_autre;
        this.trajet_arrivee_structure = trajet_arrivee_structure;
        this.nombre_transports = nombre_transports;
        this.exoneration = exoneration;
        this.pension_militaire = pension_militaire;
    }

    // Getters et Setters
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(Integer medecinId) {
        this.medecinId = medecinId;
    }

    public String[] getSituation1() {
        return situation1;
    }

    public void setSituation1(String[] situation1) {
        this.situation1 = situation1;
    }

    public String getDate_at_mp() {
        return date_at_mp;
    }

    public void setDate_at_mp(String date_at_mp) {
        this.date_at_mp = date_at_mp;
    }

    public String getMode_transport() {
        return mode_transport;
    }

    public void setMode_transport(String mode_transport) {
        this.mode_transport = mode_transport;
    }

    public String getTrajet_depart() {
        return trajet_depart;
    }

    public void setTrajet_depart(String trajet_depart) {
        this.trajet_depart = trajet_depart;
    }

    public String getTrajet_depart_autre() {
        return trajet_depart_autre;
    }

    public void setTrajet_depart_autre(String trajet_depart_autre) {
        this.trajet_depart_autre = trajet_depart_autre;
    }

    public String getTrajet_depart_structure() {
        return trajet_depart_structure;
    }

    public void setTrajet_depart_structure(String trajet_depart_structure) {
        this.trajet_depart_structure = trajet_depart_structure;
    }

    public String getTrajet_arrivee() {
        return trajet_arrivee;
    }

    public void setTrajet_arrivee(String trajet_arrivee) {
        this.trajet_arrivee = trajet_arrivee;
    }

    public String getTrajet_arrivee_autre() {
        return trajet_arrivee_autre;
    }

    public void setTrajet_arrivee_autre(String trajet_arrivee_autre) {
        this.trajet_arrivee_autre = trajet_arrivee_autre;
    }

    public String getTrajet_arrivee_structure() {
        return trajet_arrivee_structure;
    }

    public void setTrajet_arrivee_structure(String trajet_arrivee_structure) {
        this.trajet_arrivee_structure = trajet_arrivee_structure;
    }

    public Integer getNombre_transports() {
        return nombre_transports;
    }

    public void setNombre_transports(Integer nombre_transports) {
        this.nombre_transports = nombre_transports;
    }

    public String[] getExoneration() {
        return exoneration;
    }

    public void setExoneration(String[] exoneration) {
        this.exoneration = exoneration;
    }

    public String[] getPension_militaire() {
        return pension_militaire;
    }

    public void setPension_militaire(String[] pension_militaire) {
        this.pension_militaire = pension_militaire;
    }
}
