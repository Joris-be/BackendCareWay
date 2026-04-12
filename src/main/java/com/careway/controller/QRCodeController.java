package com.careway.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.careway.dto.EtapeDTO;
import com.careway.entity.QRCode;
import com.careway.service.QRCodeService;

@RestController
@RequestMapping("/qr-codes")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @PostMapping("/generate")
    public ResponseEntity<QRCode> generateQRCode(
            @RequestParam Integer etapeId,
            @RequestParam(defaultValue = "120") Integer expirationMinutes) {
        QRCode qrCode = qrCodeService.generateQRCode(etapeId, expirationMinutes);
        return ResponseEntity.ok(qrCode);
    }

    @PostMapping("/scan")
    public ResponseEntity<QRCode> scanQRCode(@RequestParam String code) {
        QRCode qrCode = qrCodeService.scanQRCode(code);
        return ResponseEntity.ok(qrCode);
    }

    @GetMapping("/current/{etapeId}")
    public ResponseEntity<QRCode> getCurrentQRCode(@PathVariable Integer etapeId) {
        QRCode qrCode = qrCodeService.getCurrentQRCode(etapeId);
        return ResponseEntity.ok(qrCode);
    }

    @GetMapping("/etape/{etapeId}")
    public ResponseEntity<List<QRCode>> getQRCodesByEtape(@PathVariable Integer etapeId) {
        List<QRCode> qrCodes = qrCodeService.getQRCodesByEtape(etapeId);
        return ResponseEntity.ok(qrCodes);
    }

    @GetMapping("/etapes")
    public ResponseEntity<List<EtapeDTO>> getAllEtapesWithQRCodes() {
        List<EtapeDTO> etapes = qrCodeService.getAllEtapesWithQRCodes();
        return ResponseEntity.ok(etapes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QRCode> getQRCodeById(@PathVariable Integer id) {
        QRCode qrCode = qrCodeService.getQRCodeById(id);
        return ResponseEntity.ok(qrCode);
    }

    @GetMapping("/{id}/expired")
    public ResponseEntity<Boolean> isExpired(@PathVariable Integer id) {
        boolean expired = qrCodeService.isExpired(id);
        return ResponseEntity.ok(expired);
    }
}
