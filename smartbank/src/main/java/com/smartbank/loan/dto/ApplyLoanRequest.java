package com.smartbank.loan.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.smartbank.loan.model.LoanType;

public class ApplyLoanRequest {
	@NotNull(message = "CustomerId should not be Null or Empty")
	private Long customerId;
	@NotNull(message = "LoanType should not be Null or Empty")
	private LoanType loanType;
	@NotNull(message = "LoanAmount should not be Null or Empty")
	@Positive(message = "LoanAmount should be Greater than Zero")
	private BigDecimal loanAmount;
	@Positive(message = "TenureMonth should be Greater than Zero")
	private int tenureMonths;

	public int getTenureMonths() {
		return tenureMonths;
	}

	public void setTenureMonths(int tenureMonths) {
		this.tenureMonths = tenureMonths;
	}

	@Override
	public String toString() {
		return "ApplyLoanRequest [customerId=" + customerId + ", loanType=" + loanType + ", loanAmount=" + loanAmount
				+ ", tenureMonths=" + tenureMonths + "]";
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	
	public ApplyLoanRequest() {

	}

}
