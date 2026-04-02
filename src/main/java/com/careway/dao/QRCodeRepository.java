package com.careway.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careway.entity.QRCode;
import com.careway.entity.Etape;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Integer> {
    Optional<QRCode> findByCode(String code);

    List<QRCode> findByEtape(Etape etape);

    Optional<QRCode> findByEtapeAndScannedFalse(Etape etape);
}
