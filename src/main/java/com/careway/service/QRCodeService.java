package com.careway.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careway.dao.QRCodeRepository;
import com.careway.dao.EtapeRepository;
import com.careway.dao.TransportRepository;
import com.careway.dto.EtapeDTO;
import com.careway.entity.QRCode;
import com.careway.entity.Etape;
import com.careway.entity.Transport;

@Service
public class QRCodeService {
    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private EtapeRepository etapeRepository;

    @Autowired
    private TransportRepository transportRepository;

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

    public List<EtapeDTO> getAllEtapesWithQRCodes() {
        // Récupérer tous les transports
        List<Transport> allTransports = transportRepository.findAll();

        if (allTransports.isEmpty()) {
            return List.of();
        }

        // Trouver le transport avec la date la plus ancienne
        Transport oldestTransport = allTransports.stream()
                .min(Comparator.comparing(Transport::getDatetransport))
                .orElse(null);

        if (oldestTransport == null) {
            return List.of();
        }

        // Récupérer les 4 étapes du transport le plus ancien
        return oldestTransport.getEtapes().stream()
                .sorted(Comparator.comparingInt(Etape::getIdetape))
                .map(etape -> {
                    EtapeDTO dto = new EtapeDTO();
                    dto.setIdetape(etape.getIdetape());
                    dto.setStatut(etape.getStatut().toString());
                    dto.setIdtransport(etape.getTransport().getIdtransport());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
