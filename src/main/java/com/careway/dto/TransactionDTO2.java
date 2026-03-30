package com.careway.dto;

import java.io.Serializable;

public class TransactionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer patientId;
    private String description;
    private Double amount;
    private String status;
    private String date;

    public TransactionDTO() {
    }

    public TransactionDTO(Integer patientId, String description, Double amount, String status, String date) {
        this.patientId = patientId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
