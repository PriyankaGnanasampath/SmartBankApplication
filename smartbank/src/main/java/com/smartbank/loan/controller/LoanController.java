package com.smartbank.loan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbank.loan.dto.ApplyLoanRequest;
import com.smartbank.loan.dto.ApplyLoanResponse;
import com.smartbank.loan.dto.ApproveLoanRequest;
import com.smartbank.loan.dto.ApproveLoanResponse;
import com.smartbank.loan.dto.DisburseLoanRequest;
import com.smartbank.loan.dto.LoanPaymentRequest;
import com.smartbank.loan.model.Loan;
import com.smartbank.loan.service.LoanService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/loans")
public class LoanController {

	private final LoanService loanService;

	public LoanController(LoanService loanServie) {
		this.loanService = loanServie;
	}

	@PostMapping("/apply")
	public ResponseEntity<ApplyLoanResponse> applyLoan(@RequestBody ApplyLoanRequest applyLoanRequest) {
		ApplyLoanResponse appliedLoanResponse = loanService.applyLoan(applyLoanRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(appliedLoanResponse);
	}

	@PostMapping("/pay-emi")
	public ResponseEntity<Loan> payEMI(@RequestBody LoanPaymentRequest loanPaymentRequest) {
		Loan loan = loanService.payEMI(loanPaymentRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(loan);
	}

	@PatchMapping("/{loanId}/approve")
	public ResponseEntity<ApproveLoanResponse> approveLoan(@PathVariable Long loanId,
			@RequestBody ApproveLoanRequest approveLoanRequest) {
		ApproveLoanResponse loan = loanService.approveLoan(loanId, approveLoanRequest);
		return ResponseEntity.status(HttpStatus.OK).body(loan);
	}

	@PatchMapping("/{loanId}/reject")
	public ResponseEntity<Loan> rejectLoan(@PathVariable Long loanId, @RequestBody String remarks) {
		Loan loan = loanService.rejectLoan(loanId, remarks);
		return ResponseEntity.status(HttpStatus.OK).body(loan);
	}

	@PatchMapping("/{loanId}/disburse")
	public ResponseEntity<Loan> disburseLoan(@PathVariable Long loanId,
			@RequestBody DisburseLoanRequest disburseLoanRequest) {
		Loan loan = loanService.disburseLoan(loanId, disburseLoanRequest);
		return ResponseEntity.status(HttpStatus.OK).body(loan);
	}

	@PatchMapping("/{loanNumber}/close")
	public ResponseEntity<Loan> close(@PathVariable String loanNumber) {
		Loan loan = loanService.closeLoan(loanNumber);
		return ResponseEntity.status(HttpStatus.OK).body(loan);
	}

	@GetMapping("/{loanNumber}")
	public ResponseEntity<Loan> getLoan(@PathVariable String loanNumber) {
		Loan loan = loanService.getLoan(loanNumber);
		return ResponseEntity.status(HttpStatus.OK).body(loan);
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<Loan>> getCustomerLoans(@PathVariable Long customerId) {
		List<Loan> loan = loanService.getCustomerLoans(customerId);
		return ResponseEntity.status(HttpStatus.OK).body(loan);
	}

	@GetMapping
	public ResponseEntity<List<Loan>> getAllLoans() {
		List<Loan> loan = loanService.getAllLoans();
		return ResponseEntity.status(HttpStatus.OK).body(loan);

	}

}
