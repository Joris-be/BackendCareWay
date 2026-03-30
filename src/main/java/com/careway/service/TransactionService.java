package com.careway.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.careway.dao.TransactionRepository;
import com.careway.entity.Transaction;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction non trouvée"));
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Integer id, Transaction transactionData) {
        Transaction transaction = getTransactionById(id);
        transaction.setPatientId(transactionData.getPatientId());
        transaction.setDescription(transactionData.getDescription());
        transaction.setAmount(transactionData.getAmount());
        transaction.setStatus(transactionData.getStatus());
        transaction.setDate(transactionData.getDate());
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }
}
