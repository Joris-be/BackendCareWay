package com.careway.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careway.dao.TransportRepository;
import com.careway.dto.TransportDTO;
import com.careway.entity.Transport;

@Service
public class TransportService {

    @Autowired
    private TransportRepository transportRepository;

    public List<TransportDTO> getAllTransports() {
        return transportRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TransportDTO getTransportById(Integer id) {
        Transport transport = transportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transport not found"));
        return convertToDTO(transport);
    }

    public TransportDTO saveTransport(TransportDTO transportDTO) {
        Transport transport = convertToEntity(transportDTO);
        Transport saved = transportRepository.save(transport);
        return convertToDTO(saved);
    }

    public TransportDTO updateTransport(Integer id, TransportDTO transportDTO) {
        Transport transport = transportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transport not found"));
        transport.setTransportType(transportDTO.getTransportType());
        transport.setDriver(transportDTO.getDriver());
        transport.setVehicle(transportDTO.getVehicle());
        transport.setPhone(transportDTO.getPhone());
        transport.setStatus(transportDTO.getStatus());
        Transport updated = transportRepository.save(transport);
        return convertToDTO(updated);
    }

    public void deleteTransport(Integer id) {
        transportRepository.deleteById(id);
    }

    private TransportDTO convertToDTO(Transport transport) {
        TransportDTO dto = new TransportDTO();
        dto.setId(transport.getId());
        dto.setTransportType(transport.getTransportType());
        dto.setDriver(transport.getDriver());
        dto.setVehicle(transport.getVehicle());
        dto.setPhone(transport.getPhone());
        dto.setStatus(transport.getStatus());
        return dto;
    }

    private Transport convertToEntity(TransportDTO dto) {
        Transport transport = new Transport();
        transport.setTransportType(dto.getTransportType());
        transport.setDriver(dto.getDriver());
        transport.setVehicle(dto.getVehicle());
        transport.setPhone(dto.getPhone());
        transport.setStatus(dto.getStatus());
        return transport;
    }
}
