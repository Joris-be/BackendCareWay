package com.careway.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careway.dao.QRCodeRepository;
import com.careway.dao.EtapeRepository;
import com.careway.entity.QRCode;
import com.careway.entity.Etape;

@Service
public class QRCodeService {
    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private EtapeRepository etapeRepository;

    public QRCode generateQRCode(Integer etapeId, Integer expirationMinutes) {
        Optional<Etape> etape = etapeRepository.findById(etapeId);
        if (etape.isEmpty()) {
            throw new RuntimeException("Etape non trouvée");
        }

        String code = UUID.randomUUID().toString();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(expirationMinutes);

        QRCode qrCode = new QRCode(etape.get(), code, expiresAt);
        return qrCodeRepository.save(qrCode);
    }

    public QRCode scanQRCode(String code) {
        Optional<QRCode> qrCode = qrCodeRepository.findByCode(code);
        if (qrCode.isEmpty()) {
            throw new RuntimeException("QR Code non trouvé");
        }

        QRCode qr = qrCode.get();
        if (qr.isExpired()) {
            throw new RuntimeException("QR Code expiré");
        }
        if (qr.getScanned()) {
            throw new RuntimeException("QR Code déjà scanné");
        }

        qr.setScanned(true);
        qr.setScannedAt(LocalDateTime.now());
        return qrCodeRepository.save(qr);
    }

    public QRCode getCurrentQRCode(Integer etapeId) {
        Optional<Etape> etape = etapeRepository.findById(etapeId);
        if (etape.isEmpty()) {
            throw new RuntimeException("Etape non trouvée");
        }

        Optional<QRCode> activeQR = qrCodeRepository.findByEtapeAndScannedFalse(etape.get());
        if (activeQR.isPresent()) {
            QRCode qr = activeQR.get();
            if (qr.isExpired()) {
                // Générer un nouveau si expiré
                return generateQRCode(etapeId, 120);
            }
            return qr;
        }

        // Générer un nouveau si aucun n'existe
        return generateQRCode(etapeId, 120);
    }

    public List<QRCode> getQRCodesByEtape(Integer etapeId) {
        Optional<Etape> etape = etapeRepository.findById(etapeId);
        if (etape.isEmpty()) {
            throw new RuntimeException("Etape non trouvée");
        }
        return qrCodeRepository.findByEtape(etape.get());
    }

    public QRCode getQRCodeById(Integer id) {
        return qrCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR Code non trouvé"));
    }

    public boolean isExpired(Integer qrCodeId) {
        QRCode qrCode = getQRCodeById(qrCodeId);
        return qrCode.isExpired();
    }
}
