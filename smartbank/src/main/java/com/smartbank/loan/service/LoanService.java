package com.smartbank.loan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartbank.loan.dto.ApplyLoanRequestDto;
import com.smartbank.loan.dto.ApplyLoanResponseDto;
import com.smartbank.loan.dto.ApproveLoanRequestDto;
import com.smartbank.loan.dto.ApproveLoanResponseDto;
import com.smartbank.loan.dto.DisburseLoanRequestDto;
import com.smartbank.loan.dto.LoanRepaymentRequestDto;
import com.smartbank.loan.dto.RejectLoanRequestDto;
import com.smartbank.loan.model.Loan;

@Service
public interface LoanService {
	public ApplyLoanResponseDto applyLoan(ApplyLoanRequestDto applyLoanRequest);

	public ApproveLoanResponseDto approveLoan(Long loanId, ApproveLoanRequestDto approveLoanRequest);

	public Loan rejectLoan(Long loanId, RejectLoanRequestDto rejectLoanRequestDto);

	public Loan disburseLoan(Long loanId, DisburseLoanRequestDto disburseLoanRequest );

	public Loan payEMI(LoanRepaymentRequestDto loanPaymentRequest);

	public Loan closeLoan(String loanNumber);

	public Loan getLoan(String loanNumber);

	public List<Loan> getCustomerLoans(Long customeId);

	public List<Loan> getAllLoans();
}
