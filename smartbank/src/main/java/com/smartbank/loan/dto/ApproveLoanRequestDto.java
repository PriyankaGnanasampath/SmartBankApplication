package com.smartbank.loan.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

public class ApproveLoanRequestDto {

	
	@Positive(message = "Intrest Rate shoud be greater than Zero")
	private BigDecimal interestRate;

	
	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	
	@Override
	public String toString() {
		return "ApproveLoanRequestDto [interestRate=" + interestRate + "]";
	}

	public ApproveLoanRequestDto() {

	}

}
