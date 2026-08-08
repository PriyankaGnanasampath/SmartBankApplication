package com.smartbank.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.smartbank.account.exception.AccountAlreadyActiveException;
import com.smartbank.account.exception.AccountAlreadyClosedException;
import com.smartbank.account.exception.AccountAlreadyExistsException;
import com.smartbank.account.exception.AccountAlreadyFrozenException;
import com.smartbank.account.exception.AccountNotFoundException;
import com.smartbank.account.exception.CustomerAlreadyLinkedException;
import com.smartbank.account.exception.InvalidAccountRequestException;
import com.smartbank.account.exception.MinimumBalanceException;
import com.smartbank.common.dto.ErrorResponse;
import com.smartbank.customer.exception.AadhaarAlreadyExistsException;
import com.smartbank.customer.exception.CustomerAlreadyClosedException;
import com.smartbank.customer.exception.CustomerNotFoundException;
import com.smartbank.customer.exception.EmailAlreadyExistsException;
import com.smartbank.customer.exception.InvalidCustomerRequestException;
import com.smartbank.customer.exception.PanAlreadyExistsException;
import com.smartbank.customer.exception.PhoneNumberAlreadyExistsException;
import com.smartbank.loan.exception.EMIRepaymentException;
import com.smartbank.loan.exception.InvalidLoanRequestException;
import com.smartbank.loan.exception.LoanAlreadyActiveException;
import com.smartbank.loan.exception.LoanAlreadyApprovedException;
import com.smartbank.loan.exception.LoanAlreadyClosedException;
import com.smartbank.loan.exception.LoanAlreadyDisbursedException;
import com.smartbank.loan.exception.LoanAlreadyRejectedException;
import com.smartbank.loan.exception.LoanNotApprovedException;
import com.smartbank.loan.exception.LoanNotFoundException;
import com.smartbank.transaction.exception.InsufficientBalanceException;
import com.smartbank.transaction.exception.InvalidTransactionRequestException;
import com.smartbank.transaction.exception.TransactionFailedException;
import com.smartbank.transaction.exception.TransactionNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_001", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(CustomerAlreadyClosedException.class)
	public ResponseEntity<ErrorResponse> handleCustomerAlreadyClosedxception(CustomerAlreadyClosedException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_002", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(InvalidCustomerRequestException.class)
	public ResponseEntity<ErrorResponse> handleInvalidCustomerRequestxception(InvalidCustomerRequestException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_003", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(AadhaarAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleAadhaarAlreadyExistsException(AadhaarAlreadyExistsException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_004", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_005", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(PanAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handlePanAlreadyExistsException(PanAlreadyExistsException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_006", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(PhoneNumberAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handlePhoneNumberAlreadyExistxception(PhoneNumberAlreadyExistsException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_007", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(AccountAlreadyClosedException.class)
	public ResponseEntity<ErrorResponse> handleAccountAlreadyClosedException(AccountAlreadyClosedException ex) {
		ErrorResponse errorResponse = new ErrorResponse("ACC_001", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(AccountAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleAccountAlreadyExistsException(AccountAlreadyExistsException ex) {
		ErrorResponse errorResponse = new ErrorResponse("ACC_002", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse("ACC_003", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(CustomerAlreadyLinkedException.class)
	public ResponseEntity<ErrorResponse> handleCustomerAlreadyLinkedException(CustomerAlreadyLinkedException ex) {
		ErrorResponse errorResponse = new ErrorResponse("ACC_004", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(InvalidAccountRequestException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAccountExistxception(InvalidAccountRequestException ex) {
		ErrorResponse errorResponse = new ErrorResponse("ACC_005", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(MinimumBalanceException.class)
	public ResponseEntity<ErrorResponse> handleMinimumBalanceException(MinimumBalanceException ex) {
		ErrorResponse errorResponse = new ErrorResponse("ACC_006", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(AccountAlreadyFrozenException.class)
	public ResponseEntity<ErrorResponse> handleAccountAlreadyFrozenException(AccountAlreadyFrozenException ex) {
		ErrorResponse errorResponse = new ErrorResponse("ACC_007", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(AccountAlreadyActiveException.class)
	public ResponseEntity<ErrorResponse> handleAccountAlreadyActiveException(AccountAlreadyActiveException ex) {
		ErrorResponse errorResponse = new ErrorResponse("ACC_008", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleTransactionNotFoundException(TransactionNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse("TRAN_001", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(InsufficientBalanceException ex) {
		ErrorResponse errorResponse = new ErrorResponse("TRAN_002", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(InvalidTransactionRequestException.class)
	public ResponseEntity<ErrorResponse> handleInvalidTransactionRequestException(
			InvalidTransactionRequestException ex) {
		ErrorResponse errorResponse = new ErrorResponse("TRAN_003", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(TransactionFailedException.class)
	public ResponseEntity<ErrorResponse> handleTransactionFailedException(TransactionFailedException ex) {
		ErrorResponse errorResponse = new ErrorResponse("TRAN_004", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleLoanNotFoundException(LoanNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse("LOAN_001", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(InvalidLoanRequestException.class)
	public ResponseEntity<ErrorResponse> handleInvalidLoanRequestException(InvalidLoanRequestException ex) {
		ErrorResponse errorResponse = new ErrorResponse("LOAN_002", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(LoanAlreadyClosedException.class)
	public ResponseEntity<ErrorResponse> handleLoanAlreadyClosedException(LoanAlreadyClosedException ex) {
		ErrorResponse errorResponse = new ErrorResponse("LOAN_003", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(LoanNotApprovedException.class)
	public ResponseEntity<ErrorResponse> handleLoanNotApprovedException(LoanNotApprovedException ex) {
		ErrorResponse errorResponse = new ErrorResponse("LOAN_004", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(LoanAlreadyDisbursedException.class)
	public ResponseEntity<ErrorResponse> handleLoanAlreadyDisbursedException(LoanAlreadyDisbursedException ex) {
		ErrorResponse errorResponse = new ErrorResponse("LOAN_005", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(EMIRepaymentException.class)
	public ResponseEntity<ErrorResponse> handleEMIPaymentException(EMIRepaymentException ex) {
		ErrorResponse errorResponse = new ErrorResponse("LOAN_006", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(LoanAlreadyApprovedException.class)
	public ResponseEntity<ErrorResponse> handleLoanAlreadyApprovedException(LoanAlreadyApprovedException ex) {
		ErrorResponse errorResponse = new ErrorResponse("LOAN_007", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(LoanAlreadyActiveException.class)
	public ResponseEntity<ErrorResponse> handleLoanAlreadyActiveException(LoanAlreadyActiveException ex) {
		ErrorResponse errorResponse = new ErrorResponse("LOAN_008", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(LoanAlreadyRejectedException.class)
	public ResponseEntity<ErrorResponse> handleLoanAlreadyRejectedException(LoanAlreadyRejectedException ex) {
		ErrorResponse errorResponse = new ErrorResponse("LOAN_009", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

}
