package com.careway.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.careway.dao.TransportRepository;
import com.careway.entity.Transport;

@Service
public class TransportService {
    private final TransportRepository transportRepository;

    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    public List<Transport> getAllTransports() {
        return transportRepository.findAll();
    }

    public Transport getTransportById(Integer id) {
        return transportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transport non trouvé"));
    }

    public Transport saveTransport(Transport transport) {
        return transportRepository.save(transport);
    }

    public Transport updateTransport(Integer id, Transport transportData) {
        Transport transport = getTransportById(id);
        transport.setLieudepart(transportData.getLieudepart());
        transport.setLieuarrive(transportData.getLieuarrive());
        transport.setTypetransport(transportData.getTypetransport());
        return transportRepository.save(transport);
    }

    public void deleteTransport(Integer id) {
        transportRepository.deleteById(id);
    }
}
