package com.careway.dto;

import java.io.Serializable;

public class TransportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String transportType;
    private String driver;
    private String vehicle;
    private String phone;
    private String status;

    public TransportDTO() {
    }

    public TransportDTO(String transportType, String driver, String vehicle, String status) {
        this.transportType = transportType;
        this.driver = driver;
        this.vehicle = vehicle;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
