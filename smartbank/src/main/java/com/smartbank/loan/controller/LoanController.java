package com.smartbank.loan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbank.loan.dto.ApplyLoanRequestDto;
import com.smartbank.loan.dto.ApplyLoanResponseDto;
import com.smartbank.loan.dto.ApproveLoanRequestDto;
import com.smartbank.loan.dto.ApproveLoanResponseDto;
import com.smartbank.loan.dto.DisburseLoanRequestDto;
import com.smartbank.loan.dto.LoanRepaymentRequestDto;
import com.smartbank.loan.dto.RejectLoanRequestDto;
import com.smartbank.loan.model.Loan;
import com.smartbank.loan.service.LoanService;


@RestController
@RequestMapping("/loans")
public class LoanController {

	private final LoanService loanService;

	public LoanController(LoanService loanServie) {
		this.loanService = loanServie;
	}

	@PostMapping("/apply")
	public ResponseEntity<ApplyLoanResponseDto> applyLoan(@Valid @RequestBody ApplyLoanRequestDto applyLoanRequest) {
		ApplyLoanResponseDto appliedLoanResponse = loanService.applyLoan(applyLoanRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(appliedLoanResponse);
	}

	@PostMapping("/pay-emi")
	public ResponseEntity<Loan> payEMI(@Valid @RequestBody LoanRepaymentRequestDto loanPaymentRequest) {
		Loan loan = loanService.payEMI(loanPaymentRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(loan);
	}

	@PatchMapping("/{loanId}/approve")
	public ResponseEntity<ApproveLoanResponseDto> approveLoan(@PathVariable Long loanId,
			@Valid @RequestBody ApproveLoanRequestDto approveLoanRequest) {
		ApproveLoanResponseDto loan = loanService.approveLoan(loanId, approveLoanRequest);
		return ResponseEntity.status(HttpStatus.OK).body(loan);
	}

	@PatchMapping("/{loanId}/reject")
	public ResponseEntity<Loan> rejectLoan(@PathVariable Long loanId,@Valid @RequestBody RejectLoanRequestDto rejectLoanRequestDto) {
		Loan loan = loanService.rejectLoan(loanId, rejectLoanRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(loan);
	}

	@PatchMapping("/{loanId}/disburse")
	public ResponseEntity<Loan> disburseLoan(@PathVariable Long loanId,
			@Valid	@RequestBody DisburseLoanRequestDto disburseLoanRequest) {
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
