package com.smartbank.transaction.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartbank.transaction.model.Transaction;
import com.smartbank.transaction.model.TransactionStatus;
import com.smartbank.transaction.model.TransactionType;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	public Optional<Transaction> findByTransactionReferenceNumber(String transactionRefNumber);

	public List<Transaction> findByAccountAccountNumber(String accountNumber);

	public List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus);

	public List<Transaction> findByTransactionType(TransactionType transactionType);

	public List<Transaction> findByTransactionDateTimeBetween(LocalDateTime from, LocalDateTime to);
}
