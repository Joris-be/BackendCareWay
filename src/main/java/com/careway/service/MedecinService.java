package com.careway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.careway.dao.MedecinRepository;
import com.careway.entity.Medecin;

@Service
public class MedecinService {

    private final MedecinRepository medecinRepository;

    public MedecinService(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    public Optional<Medecin> getMedecinById(Integer id) {
        return medecinRepository.findById(id);
    }

    public Optional<Medecin> getMedecinByRPPS(String rpps) {
        return medecinRepository.findAll().stream()
                .filter(m -> m.getRpps() != null && m.getRpps().equals(rpps))
                .findFirst();
    }

    public boolean validateMedecinPassword(Medecin medecin, String password) {
        return medecin.getMotdepasse() != null && medecin.getMotdepasse().equals(password);
    }

    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }
}
