package com.smartbank.loan.helper;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.smartbank.loan.common.LoanConstants;
import com.smartbank.loan.dto.LoanRepaymentRequestDto;
import com.smartbank.loan.exception.EMIRepaymentException;
import com.smartbank.loan.exception.InvalidLoanRequestException;
import com.smartbank.loan.model.LoanInstallment;
import com.smartbank.loan.model.LoanStatus;
import com.smartbank.loan.model.PaymentStatus;
import com.smartbank.loan.repository.LoanInstallmentRepository;

@Component
public class LoanRepaymentHelper {
	private final LoanInstallmentRepository loanInstallmentRepository;

	public LoanRepaymentHelper(LoanInstallmentRepository loanInstallmentRepository) {
		this.loanInstallmentRepository = loanInstallmentRepository;
	}

	public void validateOutstandingAmount(BigDecimal outstandingAmount)

	{
		if (outstandingAmount.compareTo(BigDecimal.ZERO) <=0) {
			throw new EMIRepaymentException(
					"Loan is already completed and the Outstanding amount zero" + outstandingAmount);
		}
	}

	public BigDecimal calculateOutstandingAmount(BigDecimal outstandingAmount, BigDecimal emiAmount)

	{
		return outstandingAmount.subtract(emiAmount);
	}

	public LoanStatus getLoanStatus(BigDecimal outstandingAmount) {
		if(outstandingAmount.compareTo(BigDecimal.ZERO)==0)
		{
			return LoanStatus.CLOSED;
			
		}
		return LoanStatus.ACTIVE;
	}
	public void validateEMIAmount(BigDecimal requestAmount, BigDecimal emiAmount)

	{
		if (requestAmount.compareTo(emiAmount) !=0) {
			throw new EMIRepaymentException("Amount should be match with EMIAmount. EmiAmount is " + emiAmount);
		}
	}

	public void updateLoanInstallment(LoanRepaymentRequestDto loanPaymentRequest,
			LoanInstallment existingLoanInstallment) {
		existingLoanInstallment.setPaymentStatus(PaymentStatus.PAID);
		existingLoanInstallment.setPaymentDate(LocalDate.now());
		existingLoanInstallment.setPaidAmount(loanPaymentRequest.getAmount());
		existingLoanInstallment.setLastModifiedBy(LoanConstants.MODIFIED_USER_NAME);
		existingLoanInstallment.setLastModifiedDate(LocalDate.now());
		loanInstallmentRepository.save(existingLoanInstallment);
	}
	public void validateLoanStatus(LoanStatus loanStatus) {
		if (!LoanStatus.ACTIVE.equals(loanStatus)) {
			throw new InvalidLoanRequestException(
					"EMI can be paid only for Active loans. Loan status is " + loanStatus);
		}
	}
}
