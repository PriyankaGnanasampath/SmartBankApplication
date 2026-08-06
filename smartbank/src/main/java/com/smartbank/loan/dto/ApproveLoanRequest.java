package com.smartbank.loan.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

public class ApproveLoanRequest {

	private Long loanId;
	@Positive(message = "Intrest Rate shoud be greater than Zero")
	private BigDecimal interestRate;

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return "ApproveLoanRequest [loanId=" + loanId + ", interestRate=" + interestRate + "]";
	}

	public ApproveLoanRequest() {

	}

}
