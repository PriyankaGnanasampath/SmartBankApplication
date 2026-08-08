package com.smartbank.loan.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartbank.customer.model.Customer;

@Entity
@Table(name = "LOAN")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;

	private String loanNumber;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;
	@Enumerated(EnumType.STRING)
	private LoanType loanType;

	private BigDecimal loanAmount;

	private BigDecimal interestRate;

	private Integer tenureMonths;

	private BigDecimal totalInterest;

	private BigDecimal totalRepaymentAmount;

	private BigDecimal outstandingAmount;
	@Enumerated(EnumType.STRING)
	private LoanStatus loanStatus;

	private String approvedBy;

	private LocalDate approvalDate;

	private LocalDate disbursementDate;

	private LocalDate loanStartDate;

	private LocalDate loanEndDate;

	private LocalDateTime createdDate;

	private String createdBy;

	private LocalDateTime lastModifiedDate;
	private String modifiedBy;
	private BigDecimal emiAmount;
	private String remarks;


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


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
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


	public BigDecimal getInterestRate() {
		return interestRate;
	}


	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}


	public Integer getTenureMonths() {
		return tenureMonths;
	}


	public void setTenureMonths(Integer tenureMonths) {
		this.tenureMonths = tenureMonths;
	}


	public BigDecimal getTotalInterest() {
		return totalInterest;
	}


	public void setTotalInterest(BigDecimal totalInterest) {
		this.totalInterest = totalInterest;
	}


	public BigDecimal getTotalRepaymentAmount() {
		return totalRepaymentAmount;
	}


	public void setTotalRepaymentAmount(BigDecimal totalRepaymentAmount) {
		this.totalRepaymentAmount = totalRepaymentAmount;
	}


	public BigDecimal getOutstandingAmount() {
		return outstandingAmount;
	}


	public void setOutstandingAmount(BigDecimal outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}


	public LoanStatus getLoanStatus() {
		return loanStatus;
	}


	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}


	public String getApprovedBy() {
		return approvedBy;
	}


	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}


	public LocalDate getApprovalDate() {
		return approvalDate;
	}


	public void setApprovalDate(LocalDate approvalDate) {
		this.approvalDate = approvalDate;
	}


	public LocalDate getDisbursementDate() {
		return disbursementDate;
	}


	public void setDisbursementDate(LocalDate disbursementDate) {
		this.disbursementDate = disbursementDate;
	}


	public LocalDate getLoanStartDate() {
		return loanStartDate;
	}


	public void setLoanStartDate(LocalDate loanStartDate) {
		this.loanStartDate = loanStartDate;
	}


	public LocalDate getLoanEndDate() {
		return loanEndDate;
	}


	public void setLoanEndDate(LocalDate loanEndDate) {
		this.loanEndDate = loanEndDate;
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


	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}


	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public BigDecimal getEmiAmount() {
		return emiAmount;
	}


	public void setEmiAmount(BigDecimal emiAmount) {
		this.emiAmount = emiAmount;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanNumber=" + loanNumber + ", loanType=" + loanType + ", loanAmount="
				+ loanAmount + ", interestRate=" + interestRate + ", tenureMonths=" + tenureMonths + ", totalInterest="
				+ totalInterest + ", totalRepaymentAmount=" + totalRepaymentAmount + ", outstandingAmount="
				+ outstandingAmount + ", loanStatus=" + loanStatus + ", approvedBy=" + approvedBy + ", approvalDate="
				+ approvalDate + ", disbursementDate=" + disbursementDate + ", loanStartDate=" + loanStartDate
				+ ", loanEndDate=" + loanEndDate + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", lastModifiedDate=" + lastModifiedDate + ", modifiedBy=" + modifiedBy + ", emiAmount=" + emiAmount
				+ ", remarks=" + remarks + "]";
	}


	public Loan() {

	}
}
