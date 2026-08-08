package com.smartbank.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.smartbank.loan.model.LoanStatus;

public class ApproveLoanResponseDto {

	private Long loanId;
	private BigDecimal interestRate;
	private String approvedBy;
	private LoanStatus loanStatus;
	private LocalDateTime lastModifiedDate;
	private LocalDate approvalDate;
	private String modifiedBy;

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

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

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public LocalDate getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDate approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Override
	public String toString() {
		return "ApproveLoanResponse [loanId=" + loanId + ", interestRate=" + interestRate + ", approvedBy=" + approvedBy
				+ ", loanStatus=" + loanStatus + ", lastModifiedDate=" + lastModifiedDate + ", approvalDate="
				+ approvalDate + ", modifiedBy=" + modifiedBy + "]";
	}

	public ApproveLoanResponseDto() {

	}

}
