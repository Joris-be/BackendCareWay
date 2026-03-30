package com.careway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careway.dao.PatientRepository;
import com.careway.dto.DashboardStatsDTO;

@Service
public class DashboardService {

    @Autowired
    private PatientRepository patientRepository;

    public DashboardStatsDTO getStats() {
        long totalPatients = patientRepository.count();

        DashboardStatsDTO stats = new DashboardStatsDTO();
        stats.setAppointments(213);
        stats.setNewPatients((int) totalPatients);
        stats.setOperations(24);
        stats.setTransportCost(12174.0);
        stats.setIncome(100000.0);

        return stats;
    }
}
