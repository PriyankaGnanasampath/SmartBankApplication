package com.smartbank.loan.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smartbank.customer.model.Customer;
import com.smartbank.customer.model.CustomerStatus;
import com.smartbank.loan.common.LoanConstants;
import com.smartbank.loan.exception.InvalidLoanRequestException;
import com.smartbank.loan.model.Loan;
import com.smartbank.loan.model.LoanInstallment;
import com.smartbank.loan.model.LoanStatus;
import com.smartbank.loan.model.PaymentStatus;
import com.smartbank.loan.repository.LoanInstallmentRepository;

@Component
public class DisburseLoanHelper {
	private final LoanHelper loanHelper;
	private final LoanInstallmentRepository loanInstallmentRepository;

	public DisburseLoanHelper(LoanHelper loanHelper, LoanInstallmentRepository loanInstallmentRepository) {
		this.loanHelper = loanHelper;
		this.loanInstallmentRepository = loanInstallmentRepository;
	}

	public void validateLoanDisburse(Loan loan) {
		loanHelper.validateCustomerStatus(loan.getCustomer().getCustomerId());
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

	private BigDecimal getNoOfYears(int tenureMonths) {
		BigDecimal noOfYears = BigDecimal.valueOf(tenureMonths).divide(LoanConstants.TWELVE, 2, RoundingMode.HALF_UP);

		return noOfYears;
	}

	public BigDecimal calculateTotalIntrestAmount(BigDecimal loanAmount, BigDecimal intrestRate, int tenureMonths) {
		BigDecimal totalAmount = loanAmount.multiply(intrestRate).multiply(getNoOfYears(tenureMonths));
		BigDecimal totalIntrest = totalAmount.divide(LoanConstants.HUNDRED, 2, RoundingMode.HALF_UP);
		return totalIntrest;
	}

	public BigDecimal calculateTotalRepaymentAmount(BigDecimal loanAmount, BigDecimal intrestAmount) {
		BigDecimal totalRepaymentAmount = loanAmount.add(intrestAmount);
		return totalRepaymentAmount;
	}

	public BigDecimal calculateEMI(BigDecimal totalRepayment, int tenureMonths) {
		BigDecimal totalEmi = totalRepayment.divide(BigDecimal.valueOf(tenureMonths), 2, RoundingMode.HALF_UP);
		return totalEmi;
	}

	public LocalDate calculateLoanEndDate(int tenureMonths) {
		LocalDate endDate = LocalDate.now().plusMonths(tenureMonths);
		return endDate;
	}

	public void generateEMISchedule() {
		// logic needs to be check
	}

	public void updateLoanInstallment(Loan loan) {
		List<LoanInstallment> loanInstallmentList = new ArrayList<>();

		for (int noOfInstallment = 1; noOfInstallment <= loan.getTenureMonths(); noOfInstallment++) {
			LoanInstallment loanInstallment = new LoanInstallment();
			loanInstallment.setLoan(loan);
			loanInstallment.setInstallmentNumber(noOfInstallment);
			loanInstallment.setDueDate(LocalDate.now().plusMonths(noOfInstallment));
			loanInstallment.setPrincipalAmount(getPrincipalAmount(loan.getLoanAmount(), loan.getTenureMonths()));
			loanInstallment.setInterestAmount(getIntrestAmount(loan.getTotalInterest(), loan.getTenureMonths()));
			loanInstallment
					.setEmiAmount(getEMI(loanInstallment.getPrincipalAmount(), loanInstallment.getInterestAmount()));
			loanInstallment.setPaidAmount(BigDecimal.ZERO);
			loanInstallment.setPaymentStatus(PaymentStatus.PENDING);
			loanInstallment.setCreatedBy(LoanConstants.CREATED_USER_NAME);
			loanInstallment.setCreatedDate(LocalDate.now());
			loanInstallmentList.add(loanInstallment);
		}
		loanInstallmentRepository.saveAll(loanInstallmentList);

	}

	private BigDecimal getPrincipalAmount(BigDecimal principal, int tenureMonths) {

		return principal.divide(BigDecimal.valueOf(tenureMonths), 2, RoundingMode.HALF_UP);
	}

	private BigDecimal getIntrestAmount(BigDecimal totalIntrest, int tenureMonths) {

		return totalIntrest.divide(BigDecimal.valueOf(tenureMonths), 2, RoundingMode.HALF_UP);
	}

	private BigDecimal getEMI(BigDecimal principal, BigDecimal intrest) {

		return principal.add(intrest);
	}
	public void validateLoanStatus(LoanStatus loanStatus) {
		if (!LoanStatus.APPROVED.equals(loanStatus)) {
			throw new InvalidLoanRequestException(
					"Loan can be disbursed only for Approved loans. Loan status is " + loanStatus);
		}
	}

}
