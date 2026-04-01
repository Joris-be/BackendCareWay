package com.careway.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.careway.dao.TransportRepository;
import com.careway.dto.TransportDTO;
import com.careway.dto.TransporteurDTO;
import com.careway.entity.Transport;
import com.careway.entity.Transporteur;

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

    public List<TransportDTO> getTransportsByPatient(Integer idpatient) {
        List<Transport> transports = transportRepository.findByIdpatient(idpatient);
        return transports.stream().map(this::convertToDTO).toList();
    }

    public Transport saveTransport(Transport transport) {
        return transportRepository.save(transport);
    }

    public Transport updateTransport(Integer id, Transport transportData) {
        Transport transport = getTransportById(id);
        transport.setLieudepart(transportData.getLieudepart());
        transport.setLieuarrive(transportData.getLieuarrive());
        transport.setTypetransport(transportData.getTypetransport());
        transport.setStatut(transportData.getStatut());
        return transportRepository.save(transport);
    }

    public void deleteTransport(Integer id) {
        transportRepository.deleteById(id);
    }

    private TransportDTO convertToDTO(Transport transport) {
        TransportDTO dto = new TransportDTO();
        dto.setIdtransport(transport.getIdtransport());
        dto.setDatetransport(transport.getDatetransport());
        dto.setLieudepart(transport.getLieudepart());
        dto.setLieuarrive(transport.getLieuarrive());
        dto.setTypetransport(transport.getTypetransport());
        dto.setIdpatient(transport.getIdpatient());
        dto.setStatut(transport.getStatut());

        // Ajouter le transporteur si disponible
        if (transport.getTransporteurs() != null && !transport.getTransporteurs().isEmpty()) {
            Transporteur transporteur = transport.getTransporteurs().get(0);
            TransporteurDTO transporteurDTO = new TransporteurDTO();
            transporteurDTO.setIdtransporteur(transporteur.getIdtransporteur());
            transporteurDTO.setNom(transporteur.getNom());
            transporteurDTO.setPrenom(transporteur.getPrenom());
            transporteurDTO.setTel(transporteur.getTel());
            transporteurDTO.setMail(transporteur.getMail());
            dto.setTransporteur(transporteurDTO);
        }

        return dto;
    }
}
