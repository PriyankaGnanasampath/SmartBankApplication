package com.smartbank.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.smartbank.loan.model.LoanStatus;
import com.smartbank.loan.model.LoanType;

public class ApplyLoanResponseDto {
	private Long customerId;
	private Long loanId;
	private String loanNumber;
	private LoanType loanType;
	private BigDecimal loanAmount;
	private LoanStatus loanStatus;
	private LocalDateTime createdDate;
	private String createdBy;
	private Integer tenureMonths;

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getTenureMonths() {
		return tenureMonths;
	}

	public void setTenureMonths(Integer tenureMonths) {
		this.tenureMonths = tenureMonths;
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

	@Override
	public String toString() {
		return "ApplyLoanResponseDto [customerId=" + customerId + ", loanId=" + loanId + ", loanNumber=" + loanNumber
				+ ", loanType=" + loanType + ", loanAmount=" + loanAmount + ", loanStatus=" + loanStatus
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", tenureMonths=" + tenureMonths + "]";
	}

	public ApplyLoanResponseDto() {

	}

}
