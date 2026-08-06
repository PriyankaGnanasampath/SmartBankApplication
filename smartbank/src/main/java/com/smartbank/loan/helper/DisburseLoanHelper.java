package com.smartbank.loan.helper;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.smartbank.loan.common.LoanConstants;
import com.smartbank.loan.exception.InvalidLoanRequestException;
import com.smartbank.loan.model.Loan;
import com.smartbank.loan.model.LoanStatus;

@Component
public class DisburseLoanHelper {

	public DisburseLoanHelper() {

	}

	public void validateLoanStatus(LoanStatus loanStatus) {
		if (!LoanStatus.APPROVED.equals(loanStatus)) {
			throw new InvalidLoanRequestException(
					"Loan can be disbursed only for Approved loans. Loan status is " + loanStatus);
		}
	}

	public void validateLoanDisburse(Loan loan) {
		if (null == loan.getInterestRate() || loan.getInterestRate().compareTo(LoanConstants.ZERO) <= 0) {
			throw new InvalidLoanRequestException(
					"IntrestRate should be greater than Zero.  IntrestRate is " + loan.getInterestRate());

		}
		if (loan.getLoanAmount().compareTo(LoanConstants.ZERO) <= 0) {
			throw new InvalidLoanRequestException(
					"LoanAmount should be greater than Zero.  IntrestRate is " + loan.getLoanAmount());
		}
		if (loan.getTenureMonths() <= 0) {
			throw new InvalidLoanRequestException(
					"TenureMonths should be greater than Zero.  TenureMonths is " + loan.getTenureMonths());
		}

	}

	public BigDecimal calculateTotalIntrestAmount(BigDecimal loanAmount, BigDecimal intrestRate, int tenureMonths) {
		BigDecimal totalIntrest = loanAmount.multiply(intrestRate).multiply(BigDecimal.valueOf(tenureMonths));
		return totalIntrest;
	}

	public BigDecimal calculateTotalRepaymentAmount(BigDecimal intrestAmount, BigDecimal loanAmount) {
		BigDecimal totalRepaymentAmount = loanAmount.add(intrestAmount);
		return totalRepaymentAmount;
	}

	public BigDecimal calculateEMI(BigDecimal totalRepayment, int tenureMonths) {
		BigDecimal totalRepaymentAmount = totalRepayment.divide(BigDecimal.valueOf(tenureMonths));
		return totalRepaymentAmount;
	}

	public BigDecimal calculateOutstandingAmount(BigDecimal totalRepayment) {
		BigDecimal outstandingAmount = totalRepayment;
		return outstandingAmount;
	}

	public LocalDate calculateLoanEndDate(int tenureMonths)
	{
		LocalDate endDate=LocalDate.now().plusMonths(tenureMonths);
		return endDate;
	}
	public void generateEMISchedule() {
		// logic needs to be check
	}

}
