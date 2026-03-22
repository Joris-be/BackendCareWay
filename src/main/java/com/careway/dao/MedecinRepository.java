package com.careway.dao;
 
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Medecin;
 
public interface MedecinRepository extends JpaRepository<Medecin, Integer> {
    //permet de trouver un medecin par son mail et rpps, le optional permet de retourner null si le medecin n'est pas trouvé
    Optional<Medecin> findByRpps(String rpps);
    Optional<Medecin> findByMail(String mail);
    List<Medecin> findByNomAndPrenom(String nom, String prenom);
}
