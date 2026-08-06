package com.smartbank.loan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartbank.loan.dto.ApplyLoanRequest;
import com.smartbank.loan.dto.ApplyLoanResponse;
import com.smartbank.loan.dto.ApproveLoanRequest;
import com.smartbank.loan.dto.ApproveLoanResponse;
import com.smartbank.loan.dto.DisburseLoanRequest;
import com.smartbank.loan.dto.LoanPaymentRequest;
import com.smartbank.loan.model.Loan;

@Service
public interface LoanService {
	public ApplyLoanResponse applyLoan(ApplyLoanRequest applyLoanRequest);

	public ApproveLoanResponse approveLoan(Long loanId, ApproveLoanRequest approveLoanRequest);

	public Loan rejectLoan(Long loanId, String remarks);

	public Loan disburseLoan(Long loanId, DisburseLoanRequest disburseLoanRequest );

	public Loan payEMI(LoanPaymentRequest loanPaymentRequest);

	public Loan closeLoan(String loanNumber);

	public Loan getLoan(String loanNumber);

	public List<Loan> getCustomerLoans(Long customeId);

	public List<Loan> getAllLoans();
}
