package com.careway.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EvaluationDTO {

    @JsonProperty("patientId")
    private Integer patientId;

    @JsonProperty("transportId")
    private Integer transportId;

    @JsonProperty("transporteurId")
    private Integer transporteurId;

    @JsonProperty("transportRating")
    private float transportRating;

    @JsonProperty("driverRating")
    private float driverRating;

    @JsonProperty("feedback")
    private String feedback;

    @JsonProperty("date")
    private LocalDateTime date;

    @JsonProperty("notes")
    private String notes;

    // Constructors
    public EvaluationDTO() {
    }

    public EvaluationDTO(Integer patientId, Integer transportId, Integer transporteurId,
            float transportRating, float driverRating, String feedback,
            LocalDateTime date, String notes) {
        this.patientId = patientId;
        this.transportId = transportId;
        this.transporteurId = transporteurId;
        this.transportRating = transportRating;
        this.driverRating = driverRating;
        this.feedback = feedback;
        this.date = date;
        this.notes = notes;
    }

    // Getters and Setters
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public Integer getTransporteurId() {
        return transporteurId;
    }

    public void setTransporteurId(Integer transporteurId) {
        this.transporteurId = transporteurId;
    }

    public float getTransportRating() {
        return transportRating;
    }

    public void setTransportRating(float transportRating) {
        this.transportRating = transportRating;
    }

    public float getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(float driverRating) {
        this.driverRating = driverRating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "EvaluationDTO{" +
                "patientId=" + patientId +
                ", transportId=" + transportId +
                ", transporteurId=" + transporteurId +
                ", transportRating=" + transportRating +
                ", driverRating=" + driverRating +
                ", feedback='" + feedback + '\'' +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                '}';
    }
}
