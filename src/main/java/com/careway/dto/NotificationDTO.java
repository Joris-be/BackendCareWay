package com.careway.dto;

import java.io.Serializable;

public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer patientId;
    private String title;
    private String message;
    private String type;
    private Boolean read;
    private String date;

    public NotificationDTO() {
    }

    public NotificationDTO(Integer patientId, String title, String message, String type, String date) {
        this.patientId = patientId;
        this.title = title;
        this.message = message;
        this.type = type;
        this.read = false;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
