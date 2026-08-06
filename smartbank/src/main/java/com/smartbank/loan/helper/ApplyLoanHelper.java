package com.smartbank.loan.helper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.smartbank.customer.model.Customer;
import com.smartbank.customer.model.CustomerStatus;
import com.smartbank.loan.common.LoanConstants;
import com.smartbank.loan.dto.ApplyLoanRequest;
import com.smartbank.loan.dto.ApplyLoanResponse;
import com.smartbank.loan.exception.InvalidLoanRequestException;
import com.smartbank.loan.model.Loan;
import com.smartbank.loan.model.LoanStatus;
import com.smartbank.loan.model.LoanType;

@Component
public class ApplyLoanHelper {
	private final LoanHelper loanHelper;

	public ApplyLoanHelper(LoanHelper loanHelper) {
		this.loanHelper = loanHelper;
	}

	public void validateApplyLoanRequest(Customer existingCustomer, ApplyLoanRequest applyLoanRequest) {
		if (!CustomerStatus.ACTIVE.equals(existingCustomer.getCustomerStatus())) {
			throw new InvalidLoanRequestException("Customer is not an Active Customer.Given Customertatus is`"
					+ existingCustomer.getCustomerStatus());
		}
		Optional<List<Loan>> existingLoans = Optional
				.of(loanHelper.getExistingLoansForCustomer(applyLoanRequest.getCustomerId()));
		if (existingLoans.isPresent()) {
			maximumLoanLimit(existingLoans);
			duplicateLoanRequestCheck(existingLoans, applyLoanRequest.getLoanType());
		}
		validateLoanTenure(applyLoanRequest);
	}

	public ApplyLoanResponse populateLoanResponse(Loan updatedLoanDetails) {
		ApplyLoanResponse applyLoanResponse = new ApplyLoanResponse();
		applyLoanResponse.setCreatedBy(updatedLoanDetails.getCreatedBy());
		applyLoanResponse.setCreatedDate(LocalDateTime.now());
		applyLoanResponse.setCustomerId(updatedLoanDetails.getCustomer().getCustomerId());
		applyLoanResponse.setLoanAmount(updatedLoanDetails.getLoanAmount());
		applyLoanResponse.setLoanStatus(updatedLoanDetails.getLoanStatus());
		applyLoanResponse.setLoanNumber(updatedLoanDetails.getLoanNumber());
		applyLoanResponse.setTenureMonths(updatedLoanDetails.getTenureMonths());
		return applyLoanResponse;

	}

	public void maximumLoanLimit(Optional<List<Loan>> existingLoans) {
		if ((existingLoans.get().size() > LoanConstants.MAX_ACTIVE_LOAN)) {
			throw new InvalidLoanRequestException(
					"Customer is already having maximum number of Active Loans. Total Active Loan is"
							+ existingLoans.get().size());
		}
	}

	public void duplicateLoanRequestCheck(Optional<List<Loan>> existingLoans, LoanType loanType) {
		existingLoans.get().stream().forEach(customer -> {

			if (customer.getLoanType().equals(loanType) && customer.getLoanStatus().equals(LoanStatus.ACTIVE)) {
				throw new InvalidLoanRequestException(
						"Customer is already having the Active Loan for this Loan Type. LoanType is " + loanType);
			}
		});
	}

	private void validateTenureMonthLimits(int tenureMonthRequest, int minTenureMonth, int maxTenureMonth) {
		if (tenureMonthRequest < minTenureMonth || tenureMonthRequest > maxTenureMonth) {
			throw new InvalidLoanRequestException(
					"Invalid TenureMonths in the request. Please proivde the tenure months between " + minTenureMonth
							+ " and " + maxTenureMonth);
		}

	}

	public void validateLoanTenure(ApplyLoanRequest applyLoanRequest) {
		switch (applyLoanRequest.getLoanType()) {
		case HOME:
			validateTenureMonthLimits(applyLoanRequest.getTenureMonths(), LoanConstants.MIN_HOME_LOAN_TENURE_MONTHS,
					LoanConstants.MAX_HOME_LOAN_TENURE_MONTHS);
			break;

		case EDUCATION:
			validateTenureMonthLimits(applyLoanRequest.getTenureMonths(),
					LoanConstants.MIN_EDUCATION_LOAN_TENURE_MONTHS, LoanConstants.MAX_EDUCATION_TENURE_MONTHS);
			break;
		case GOLD:
			validateTenureMonthLimits(applyLoanRequest.getTenureMonths(), LoanConstants.MIN_GOLD_LOAN_TENURE_MONTHS,
					LoanConstants.MAX_GOLD_LOAN_TENURE_MONTHS);
			break;
		case PERSONAL:
			validateTenureMonthLimits(applyLoanRequest.getTenureMonths(), LoanConstants.MIN_PERSONAL_LOAN_TENURE_MONTHS,
					LoanConstants.MAX_PERSONAL_LOAN_TENURE_MONTHS);
			break;
		case VEHICLE:
			validateTenureMonthLimits(applyLoanRequest.getTenureMonths(), LoanConstants.MIN_VEHICLE_LOAN_TENURE_MONTHS,
					LoanConstants.MAX_VEHICLE__LOAN_TENURE_MONTHS);
			break;
		default:
			break;
		}
	}

	public Loan buildApplyLoanDetails(ApplyLoanRequest applyLoanRequest, Customer existingCustomer) {
		Loan loan = new Loan();
		loan.setCreatedBy(LoanConstants.CREATED_USER_NAME);
		loan.setCreatedDate(LocalDateTime.now());
		loan.setLoanAmount(applyLoanRequest.getLoanAmount());
		loan.setLoanNumber(loanHelper.getLoanNumber(applyLoanRequest.getLoanType().toString()));
		loan.setLoanStatus(LoanStatus.PENDING);
		loan.setLoanType(applyLoanRequest.getLoanType());
		loan.setTenureMonths(applyLoanRequest.getTenureMonths());
		loan.setCustomer(existingCustomer);
		return loan;
	}
}
