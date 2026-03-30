package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careway.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
