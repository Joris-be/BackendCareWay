package com.careway.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careway.dao.TransactionRepository;
import com.careway.dto.TransactionDTO;
import com.careway.entity.Transaction;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO getTransactionById(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return convertToDTO(transaction);
    }

    public TransactionDTO saveTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = convertToEntity(transactionDTO);
        Transaction saved = transactionRepository.save(transaction);
        return convertToDTO(saved);
    }

    public TransactionDTO updateTransaction(Integer id, TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setStatus(transactionDTO.getStatus());
        transaction.setDate(transactionDTO.getDate());
        Transaction updated = transactionRepository.save(transaction);
        return convertToDTO(updated);
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setPatientId(transaction.getPatientId());
        dto.setDescription(transaction.getDescription());
        dto.setAmount(transaction.getAmount());
        dto.setStatus(transaction.getStatus());
        dto.setDate(transaction.getDate());
        return dto;
    }

    private Transaction convertToEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setPatientId(dto.getPatientId());
        transaction.setDescription(dto.getDescription());
        transaction.setAmount(dto.getAmount());
        transaction.setStatus(dto.getStatus());
        transaction.setDate(dto.getDate());
        return transaction;
    }
}
