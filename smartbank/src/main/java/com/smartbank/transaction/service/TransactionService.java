package com.smartbank.transaction.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartbank.transaction.dto.DepositRequest;
import com.smartbank.transaction.dto.FundTransferRequest;
import com.smartbank.transaction.dto.FundTransferResponse;
import com.smartbank.transaction.dto.WithdrawRequest;
import com.smartbank.transaction.model.Transaction;
import com.smartbank.transaction.model.TransactionStatus;
import com.smartbank.transaction.model.TransactionType;

public interface TransactionService {
	public Transaction deposit(DepositRequest depositRequest);

	public Transaction withdraw(WithdrawRequest withdrawRequest);

	public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest);

	public List<Transaction> getAccountByAccountNumber(String accountNumber);

	public Transaction getTransactionReferenceNumber(String transactionRefNumber);

	public List<Transaction> getTransactionStatus(TransactionStatus transactionStatus);

	public List<Transaction> getTransactionType(TransactionType transactionType);

	public List<Transaction> getTransactionDateTimeBetween(LocalDateTime from, LocalDateTime to);
	
	public List<Transaction> getAllTransactions();
	
}
