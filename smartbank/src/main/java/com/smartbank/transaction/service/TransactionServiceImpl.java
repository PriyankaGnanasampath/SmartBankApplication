package com.smartbank.transaction.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartbank.account.common.AccountConstants;
import com.smartbank.account.model.Account;
import com.smartbank.account.model.AccountStatus;
import com.smartbank.account.model.AccountType;
import com.smartbank.account.repository.AccountRepository;
import com.smartbank.common.service.SequenceGeneratorService;
import com.smartbank.transaction.common.TransactionConstants;
import com.smartbank.transaction.dto.DepositRequest;
import com.smartbank.transaction.dto.FundTransferRequest;
import com.smartbank.transaction.dto.FundTransferResponse;
import com.smartbank.transaction.dto.WithdrawRequest;
import com.smartbank.transaction.exception.InsufficientBalanceException;
import com.smartbank.transaction.exception.InvalidTransactionRequestException;
import com.smartbank.transaction.exception.TransactionNotFoundException;
import com.smartbank.transaction.model.Transaction;
import com.smartbank.transaction.model.TransactionStatus;
import com.smartbank.transaction.model.TransactionType;
import com.smartbank.transaction.repository.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	private final TransactionRepository transactionRepository;
	private final AccountRepository accountRepository;
	private final SequenceGeneratorService sequenceGeneratorService;

	public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository,
			SequenceGeneratorService sequenceGeneratorService) {
		this.transactionRepository = transactionRepository;
		this.accountRepository = accountRepository;
		this.sequenceGeneratorService = sequenceGeneratorService;
	}

	@Override
	public Transaction deposit(DepositRequest depositRequest) {

		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		Account account = getAccountInformation(depositRequest.getAccountNumber());
		validateAccountStatus(account.getAccountStatus());
		BigDecimal updatedBalance = updateBalance(TransactionType.DEPOSIT, depositRequest.getAmount(),
				account.getOpeningBalance());
		populateDepositTransactionData(depositRequest, account, transaction, updatedBalance);
		account.setOpeningBalance(updatedBalance);
		accountRepository.save(account);
		transactionRepository.save(transaction);
		return transaction;
	}

	@Override
	public Transaction withdraw(WithdrawRequest withdrawRequest) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		Account account = getAccountInformation(withdrawRequest.getAccountNumber());
		validateAccountStatus(account.getAccountStatus());
		BigDecimal updatedBalance = updateBalance(TransactionType.WITHDRAWAL, withdrawRequest.getAmount(),
				account.getOpeningBalance());
		account.setOpeningBalance(updatedBalance);
		minimumBalanceValidation(updatedBalance, account.getAccountType());
		populateWithdrawalTransactionData(withdrawRequest, account, transaction, updatedBalance);

		accountRepository.save(account);
		transactionRepository.save(transaction);
		return transaction;
	}

	@Override
	public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest) {
		// TODO Auto-generated method stub
		Transaction debitTransaction = new Transaction();
		Transaction creditTransaction = new Transaction();

		if (fundTransferRequest.getFromAccountNumber().equals(fundTransferRequest.getToAccountNumber())) {
			throw new InvalidTransactionRequestException(
					"From_Account_Number and To_Account_Number should be different. From_Account_Number is "
							+ fundTransferRequest.getFromAccountNumber() + " and To_Account_Number is "
							+ fundTransferRequest.getToAccountNumber());
		}
		Account sourceAccount = getAccountInformation(fundTransferRequest.getFromAccountNumber());
		Account destinationAccount = getAccountInformation(fundTransferRequest.getToAccountNumber());
		validateAccountStatus(sourceAccount.getAccountStatus());
		validateAccountStatus(destinationAccount.getAccountStatus());
		debitTransaction = buildFundTransferTransaction(sourceAccount, fundTransferRequest, TransactionConstants.DEBIT);
		creditTransaction = buildFundTransferTransaction(destinationAccount, fundTransferRequest,
				TransactionConstants.CREDIT);
		setCreditTransReference(debitTransaction.getTransactionReferenceNumber(), creditTransaction,
				TransactionConstants.CREDIT);
		accountRepository.save(sourceAccount);
		accountRepository.save(destinationAccount);
		transactionRepository.save(debitTransaction);
		transactionRepository.save(creditTransaction);
		FundTransferResponse fundTransferResponse = buildFundTransferResponse(fundTransferRequest, debitTransaction,
				creditTransaction);
		return fundTransferResponse;
	}

	private void setCreditTransReference(String transReference, Transaction transaction, String debitCreditFlag) {
		if (debitCreditFlag.equals(TransactionConstants.CREDIT)) {
			transaction.setTransactionReferenceNumber(transReference);
		}
	}

	private Transaction buildFundTransferTransaction(Account account, FundTransferRequest fundTransferRequest,
			String debitCreditFlag) {
		Transaction transaction = new Transaction();
		BigDecimal updatedAccountBalance = updateBalance(getTransactionType(debitCreditFlag),
				fundTransferRequest.getAmount(), account.getOpeningBalance());
		account.setOpeningBalance(updatedAccountBalance);
		transaction.setAccount(account);
		transaction.setAmount(fundTransferRequest.getAmount());
		transaction.setAvailableBalance(updatedAccountBalance);
		transaction.setRemarks(fundTransferRequest.getRemarks());
		transaction.setTransactionDateTime(LocalDateTime.now());
		transaction.setTransactionMode(fundTransferRequest.getTransactionMode());
		if (debitCreditFlag.equals(TransactionConstants.DEBIT)) {
			transaction.setTransactionReferenceNumber(
					sequenceGeneratorService.generateSequenceNumber(TransactionConstants.TRANSACTION_REFERENCE_NUMBER));
		}
		transaction.setTransactionStatus(TransactionStatus.SUCCESS);
		transaction.setTransactionType(TransactionType.TRANSFER);
		return transaction;
	}

	private TransactionType getTransactionType(String debitCreditFlag) {
		switch (debitCreditFlag) {
		case TransactionConstants.DEBIT:
			return TransactionType.WITHDRAWAL;
		case TransactionConstants.CREDIT:
			return TransactionType.DEPOSIT;
		default:
			break;
		}
		return null;

	}

	private FundTransferResponse buildFundTransferResponse(FundTransferRequest fundTransferRequest,
			Transaction debtitTranInfo, Transaction creditTranInfo) {
		FundTransferResponse fundTransferResponse = new FundTransferResponse();
		fundTransferResponse.setDebitAccountBalance(debtitTranInfo.getAvailableBalance());
		fundTransferResponse.setDebitTransactionId(debtitTranInfo.getTransactionId());
		fundTransferResponse.setDebitTransactionReferenceNumber(debtitTranInfo.getTransactionReferenceNumber());
		fundTransferResponse.setCreditAccountBalance(creditTranInfo.getAvailableBalance());
		fundTransferResponse.setCreditTransactionId(creditTranInfo.getTransactionId());
		fundTransferResponse.setCreditTransactionReferenceNumber(creditTranInfo.getTransactionReferenceNumber());
		fundTransferResponse.setRemarks(fundTransferRequest.getRemarks());
		fundTransferResponse.setTransactionAmount(fundTransferRequest.getAmount());
		fundTransferResponse.setTransactionMode(fundTransferRequest.getTransactionMode());
		fundTransferResponse.setTransactionStatus(TransactionStatus.SUCCESS);
		fundTransferResponse.setTransactionType(TransactionType.TRANSFER);
		return fundTransferResponse;
	}

	@Override
	public Transaction getTransactionReferenceNumber(String transactionRefNumber) {
		// TODO Auto-generated method stub
		Transaction existingTransactionDetails = transactionRepository
				.findByTransactionReferenceNumber(transactionRefNumber)
				.orElseThrow(() -> new TransactionNotFoundException(
						"Please provide the valid Transaction Reference Number. Transaction Reference is "
								+ transactionRefNumber));
		if (null == existingTransactionDetails) {
			throw new TransactionNotFoundException(
					"Transaction Not found. TransactionRefrence is" + transactionRefNumber);
		}
		return existingTransactionDetails;
	}

	@Override
	public List<Transaction> getAccountByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		List<Transaction> existingTransactionDetails = transactionRepository.findByAccountAccountNumber(accountNumber);
		return existingTransactionDetails;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		List<Transaction> existingTransactionDetails = transactionRepository.findAll();
		return existingTransactionDetails;
	}

	private Account getAccountInformation(String accountNumber) {
		Account existingAccount = accountRepository.findAccountByAccountNumber(accountNumber)
				.orElseThrow(() -> new InvalidTransactionRequestException(
						"Invalid Account Number. AccountNumber is" + accountNumber));
		return existingAccount;
	}

	private void validateAccountStatus(AccountStatus accountStatus) {

		if (!AccountStatus.ACTIVE.equals(accountStatus)) {
			throw new InvalidTransactionRequestException(
					"Account number should be active. The given account is" + accountStatus);
		}
	}

	private BigDecimal updateBalance(TransactionType transactionType, BigDecimal transactionAmount,
			BigDecimal availableBalance) {
		BigDecimal finalAmount = null;
		switch (transactionType) {
		case DEPOSIT:
			finalAmount = availableBalance.add(transactionAmount).abs();
			break;
		case WITHDRAWAL:
			insufficientBalanceValidation(availableBalance, transactionAmount);
			finalAmount = availableBalance.subtract(transactionAmount);
			break;

		default:
			break;
		}
		return finalAmount;
	}

	private void insufficientBalanceValidation(BigDecimal availableBalance, BigDecimal transactionAmount) {
		if ((availableBalance.compareTo(transactionAmount) < 0)) {
			throw new InsufficientBalanceException(
					"Insufficient Account Balance. Current Balance is" + availableBalance);
		}
		// return true;
	}

	private void minimumBalanceValidation(BigDecimal updatedBalance, AccountType accountType) {

		switch (accountType) {
		case SAVINGS:
			if (updatedBalance.compareTo(AccountConstants.SAVINGS_MIN_BALANCE) < 0) {
				throw new InsufficientBalanceException(
						"Transaction declined due to minimum balance constaint. Minimum Balance amount should be "
								+ AccountConstants.SAVINGS_MIN_BALANCE);
			}
			break;
		case CURRENT:
			if (updatedBalance.compareTo(AccountConstants.CURRENT_MIN_BALANCE) < 0) {
				throw new InsufficientBalanceException(
						"Transaction declined due to minimum balance constaint. Minimum Balance amount should be "
								+ AccountConstants.CURRENT_MIN_BALANCE);
			}
			break;
		case NRI:
			if (updatedBalance.compareTo(AccountConstants.NRI_MIN_BALANCE) < 0) {
				throw new InsufficientBalanceException(
						"Transaction declined due to minimum balance constaint. Minimum Balance amount should be "
								+ AccountConstants.NRI_MIN_BALANCE);
			}
			break;
		case STUDENT:
			if (updatedBalance.compareTo(AccountConstants.STUDENT_MIN_BALANCE) < 0) {
				throw new InsufficientBalanceException(
						"Transaction declined due to minimum balance constaint. Minimum Balance amount should be "
								+ AccountConstants.STUDENT_MIN_BALANCE);
			}
			break;
		case JOINT:
			if (updatedBalance.compareTo(AccountConstants.JOINT_MIN_BALANCE) < 0) {
				throw new InsufficientBalanceException(
						"Transaction declined due to minimum balance constaint. Minimum Balance amount should be "
								+ AccountConstants.JOINT_MIN_BALANCE);
			}
			break;
		default:
			break;
		}
		// return true;

	}

	private void populateDepositTransactionData(DepositRequest depositRequest, Account account, Transaction transaction,
			BigDecimal updatedBalance) {
		transaction.setAccount(account);
		transaction.setAmount(depositRequest.getAmount());
		transaction.setAvailableBalance(updatedBalance);
		transaction.setRemarks(depositRequest.getRemarks());
		transaction.setTransactionDateTime(LocalDateTime.now());
		transaction.setTransactionMode(depositRequest.getTransactionMode());
		transaction.setTransactionReferenceNumber(
				sequenceGeneratorService.generateSequenceNumber(TransactionConstants.TRANSACTION_REFERENCE_NUMBER));
		transaction.setTransactionStatus(TransactionStatus.SUCCESS);
		transaction.setTransactionType(TransactionType.DEPOSIT);
	}

	private void populateWithdrawalTransactionData(WithdrawRequest withdrawRequest, Account account,
			Transaction transaction, BigDecimal updatedBalance) {
		transaction.setAccount(account);
		transaction.setAmount(withdrawRequest.getAmount());
		transaction.setAvailableBalance(updatedBalance);
		transaction.setRemarks(withdrawRequest.getRemarks());
		transaction.setTransactionDateTime(LocalDateTime.now());
		transaction.setTransactionMode(withdrawRequest.getTransactionMode());
		transaction.setTransactionReferenceNumber(
				sequenceGeneratorService.generateSequenceNumber(TransactionConstants.TRANSACTION_REFERENCE_NUMBER));
		transaction.setTransactionStatus(TransactionStatus.SUCCESS);
		transaction.setTransactionType(TransactionType.WITHDRAWAL);
	}

	@Override
	public List<Transaction> getTransactionStatus(TransactionStatus transactionStatus) {
		// TODO Auto-generated method stub

		List<Transaction> existingTransactionDetails = transactionRepository.findByTransactionStatus(transactionStatus);
		return existingTransactionDetails;
	}

	@Override
	public List<Transaction> getTransactionType(TransactionType transactionType) {
		// TODO Auto-generated method stub
		List<Transaction> existingTransactionDetails = transactionRepository.findByTransactionType(transactionType);
		return existingTransactionDetails;
	}

	@Override
	public List<Transaction> getTransactionDateTimeBetween(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		List<Transaction> existingTransactionDetails = transactionRepository.findByTransactionDateTimeBetween(from, to);
		return existingTransactionDetails;
	}
}
