package com.careway.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "qr_codes")
public class QRCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "etape_id", nullable = false)
    private Etape etape;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "scanned")
    private Boolean scanned = false;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "scanned_at")
    private LocalDateTime scannedAt;

    public QRCode() {
    }

    public QRCode(Etape etape, String code, LocalDateTime expiresAt) {
        this.etape = etape;
        this.code = code;
        this.expiresAt = expiresAt;
        this.generatedAt = LocalDateTime.now();
        this.scanned = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getScanned() {
        return scanned;
    }

    public void setScanned(Boolean scanned) {
        this.scanned = scanned;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getScannedAt() {
        return scannedAt;
    }

    public void setScannedAt(LocalDateTime scannedAt) {
        this.scannedAt = scannedAt;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public boolean isActive() {
        return !scanned && !isExpired();
    }
}
