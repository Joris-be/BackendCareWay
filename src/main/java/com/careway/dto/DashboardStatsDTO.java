package com.careway.dto;

import java.io.Serializable;

public class DashboardStatsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer appointments;
    private Integer newPatients;
    private Integer operations;
    private Double transportCost;
    private Double income;

    public DashboardStatsDTO() {
    }

    public DashboardStatsDTO(Integer appointments, Integer newPatients, Integer operations, Double transportCost,
            Double income) {
        this.appointments = appointments;
        this.newPatients = newPatients;
        this.operations = operations;
        this.transportCost = transportCost;
        this.income = income;
    }

    public Integer getAppointments() {
        return appointments;
    }

    public void setAppointments(Integer appointments) {
        this.appointments = appointments;
    }

    public Integer getNewPatients() {
        return newPatients;
    }

    public void setNewPatients(Integer newPatients) {
        this.newPatients = newPatients;
    }

    public Integer getOperations() {
        return operations;
    }

    public void setOperations(Integer operations) {
        this.operations = operations;
    }

    public Double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(Double transportCost) {
        this.transportCost = transportCost;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
}
