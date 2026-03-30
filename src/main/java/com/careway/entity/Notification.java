package com.careway.entity;package com.careway.entity;

}

public void setDate(String date) { this.date = date; }    public String getDate() { return date; }    public void setRead(Boolean read) { this.read = read; }    public Boolean getRead() { return read; }    public void setType(String type) { this.type = type; }    public String getType() { return type; }    public void setMessage(String message) { this.message = message; }    public String getMessage() { return message; }    public void setTitle(String title) { this.title = title; }    public String getTitle() { return title; }    public void setPatientId(Integer patientId) { this.patientId = patientId; }    public Integer getPatientId() { return patientId; }    public void setId(Integer id) { this.id = id; }    public Integer getId() { return id; }    }        this.date = date;        this.read = false;        this.type = type;        this.message = message;        this.title = title;        this.patientId = patientId;    public Notification(Integer patientId, String title, String message, String type, String date) {    public Notification() {}    private String date;    @Column(name = "date")        private Boolean read;    @Column(name = "read_status")        private String type;    @Column(name = "type")        private String message;    @Column(name = "message")        private String title;    @Column(name = "title")        private Integer patientId;    @Column(name = "patient_id")        private Integer id;    @GeneratedValue(strategy = GenerationType.IDENTITY)    @Idpublic class Notification {@Table(name = "notifications")@Entityimport jakarta.persistence.*;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "patient_id")
    private Integer patientId;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "amount")
    private Double amount;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "date")
    private String date;

    public Transaction() {}

    public Transaction(Integer patientId, String description, Double amount, String status, String date) {
        this.patientId = patientId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.date = date;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
