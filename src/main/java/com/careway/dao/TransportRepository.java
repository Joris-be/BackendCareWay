package com.careway.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Transport;

public interface TransportRepository extends JpaRepository<Transport, Integer> {
    List<Transport> findByIdpatient(Integer idpatient);
}
