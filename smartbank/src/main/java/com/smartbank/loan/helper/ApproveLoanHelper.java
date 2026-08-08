package com.smartbank.loan.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.smartbank.customer.model.Customer;
import com.smartbank.loan.common.LoanConstants;
import com.smartbank.loan.dto.ApplyLoanRequestDto;
import com.smartbank.loan.dto.ApplyLoanResponseDto;
import com.smartbank.loan.dto.ApproveLoanRequestDto;
import com.smartbank.loan.dto.ApproveLoanResponseDto;
import com.smartbank.loan.exception.InvalidLoanRequestException;
import com.smartbank.loan.exception.LoanAlreadyActiveException;
import com.smartbank.loan.exception.LoanAlreadyApprovedException;
import com.smartbank.loan.exception.LoanAlreadyClosedException;
import com.smartbank.loan.exception.LoanAlreadyDisbursedException;
import com.smartbank.loan.exception.LoanAlreadyRejectedException;
import com.smartbank.loan.exception.LoanNotFoundException;
import com.smartbank.loan.model.Loan;
import com.smartbank.loan.model.LoanStatus;
import com.smartbank.loan.repository.LoanRepository;
@Component
public class ApproveLoanHelper {
	private final LoanHelper loanHelper;
	private final LoanRepository loanRepository;

	public ApproveLoanHelper(LoanHelper loanHelper, LoanRepository loanRepository) {
		this.loanHelper = loanHelper;
		this.loanRepository = loanRepository;
	}

	public boolean validateLoanForApproval(Loan existingLoan) {
		if (!LoanStatus.PENDING.equals(existingLoan.getLoanStatus())) {
			throw new InvalidLoanRequestException("Cannot Approve the Loan. The Loan is already"
					+ existingLoan.getLoanStatus() + " and the LoanNumber is " + existingLoan.getLoanNumber());
		} else if (LoanStatus.PENDING.equals(existingLoan.getLoanStatus())) {
			return true;
		}
		return false;
	}

	public Loan buildApproveDetails(ApproveLoanRequestDto approveLoanRequest,Loan existingLoan) {
		existingLoan.setLoanStatus(LoanStatus.APPROVED);
		existingLoan.setInterestRate(approveLoanRequest.getInterestRate());
		existingLoan.setModifiedBy(LoanConstants.APPROVED_USER_NAME);
		existingLoan.setLastModifiedDate(LocalDateTime.now());
		existingLoan.setApprovalDate(LocalDate.now());
		existingLoan.setApprovedBy(LoanConstants.APPROVED_USER_NAME);
		return existingLoan;
	}

	public ApproveLoanResponseDto populateLoanResponse(Loan updatedLoanDetails) {
		ApproveLoanResponseDto approveLoanResponse = new ApproveLoanResponseDto();
		approveLoanResponse.setApprovedBy(updatedLoanDetails.getApprovedBy());
		approveLoanResponse.setInterestRate(updatedLoanDetails.getInterestRate());
		approveLoanResponse.setLastModifiedDate(updatedLoanDetails.getLastModifiedDate());
		approveLoanResponse.setLoanId(updatedLoanDetails.getLoanId());
		approveLoanResponse.setLoanStatus(updatedLoanDetails.getLoanStatus());
		approveLoanResponse.setModifiedBy(updatedLoanDetails.getModifiedBy());
		approveLoanResponse.setApprovalDate(updatedLoanDetails.getApprovalDate());
		return approveLoanResponse;

	}
}
