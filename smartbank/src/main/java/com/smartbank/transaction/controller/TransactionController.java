package com.smartbank.transaction.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbank.transaction.dto.DepositRequest;
import com.smartbank.transaction.dto.FundTransferRequest;
import com.smartbank.transaction.dto.FundTransferResponse;
import com.smartbank.transaction.dto.WithdrawRequest;
import com.smartbank.transaction.model.Transaction;
import com.smartbank.transaction.model.TransactionStatus;
import com.smartbank.transaction.model.TransactionType;
import com.smartbank.transaction.repository.TransactionRepository;
import com.smartbank.transaction.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/deposit")
	public ResponseEntity<Transaction> deposit(@RequestBody DepositRequest depositRequest) {
		Transaction transctionDeposited = transactionService.deposit(depositRequest);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(transctionDeposited);
	}

	@PostMapping("/withdraw")
	public ResponseEntity<Transaction> withdraw(@RequestBody WithdrawRequest withdrawRequest) {
		Transaction transctionWithdraw = transactionService.withdraw(withdrawRequest);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(transctionWithdraw);
	}

	@PostMapping("/transfer")
	public ResponseEntity<FundTransferResponse> fundTransfer(@RequestBody FundTransferRequest fundTransferRequest) {
		FundTransferResponse fundTransfer = transactionService.fundTransfer(fundTransferRequest);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(fundTransfer);
	}

	@GetMapping("/{transactionRefNumber}")
	public ResponseEntity<Transaction> getTransactionByReference(@PathVariable String transactionRefNumber) {
		Transaction transactionByReference = transactionService.getTransactionReferenceNumber(transactionRefNumber);
		return ResponseEntity.status(HttpStatus.OK).body(transactionByReference);
	}

	@GetMapping("/account/{accountNumber}")
	public ResponseEntity<List<Transaction>> getAccountByAccountNumber(@PathVariable String accountNumber) {
		List<Transaction> transactionsByAccount = transactionService.getAccountByAccountNumber(accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body(transactionsByAccount);
	}

	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		List<Transaction> allTransactions = transactionService.getAllTransactions();
		return ResponseEntity.status(HttpStatus.OK).body(allTransactions);
	}

	@GetMapping("/transaction-status/{transactionStatus}")
	public ResponseEntity<List<Transaction>> getTransactionStatus(@PathVariable TransactionStatus  transactionStatus) {
		List<Transaction> existingTransactionDetails = transactionService.getTransactionStatus(transactionStatus);
		return ResponseEntity.status(HttpStatus.OK).body(existingTransactionDetails);
	}

	@GetMapping("/transaction-type/{transactionType}")
	public ResponseEntity<List<Transaction>> getTransactionType(@PathVariable TransactionType transactionType) {
		// TODO Auto-generated method stub
		List<Transaction> existingTransactionDetails = transactionService.getTransactionType(transactionType);
		return ResponseEntity.status(HttpStatus.OK).body(existingTransactionDetails);
	}

	@GetMapping("/date-range/{from}/{to}")
	public ResponseEntity<List<Transaction>> getTransactionDateTimeBetween(@PathVariable LocalDateTime from,@PathVariable LocalDateTime to) {
		// TODO Auto-generated method stub
		List<Transaction> existingTransactionDetails = transactionService.getTransactionDateTimeBetween(from, to);
		return ResponseEntity.status(HttpStatus.OK).body(existingTransactionDetails);
	}
}
