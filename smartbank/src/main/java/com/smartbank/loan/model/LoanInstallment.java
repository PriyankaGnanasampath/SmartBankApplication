package com.smartbank.loan.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LOAN_INSTALLMENT")
public class LoanInstallment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long installmentId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loan_id")
	@JsonIgnore
	private Loan loan;

	private Integer installmentNumber;

	private LocalDate dueDate;

	private BigDecimal emiAmount;

	private BigDecimal principalAmount;

	private BigDecimal interestAmount;

	private BigDecimal paidAmount;

	private LocalDate paymentDate;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	private String lastModifiedBy;
	private LocalDate lastModifiedDate;

	private String createdBy;
	private LocalDate createdDate;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public LocalDate getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDate lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getInstallmentId() {
		return installmentId;
	}

	public void setInstallmentId(Long installmentId) {
		this.installmentId = installmentId;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public Integer getInstallmentNumber() {
		return installmentNumber;
	}

	public void setInstallmentNumber(Integer installmentNumber) {
		this.installmentNumber = installmentNumber;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(BigDecimal emiAmount) {
		this.emiAmount = emiAmount;
	}

	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	public BigDecimal getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "LoanInstallment [installmentId=" + installmentId + ", installmentNumber=" + installmentNumber
				+ ", dueDate=" + dueDate + ", emiAmount=" + emiAmount + ", principalAmount=" + principalAmount
				+ ", interestAmount=" + interestAmount + ", paidAmount=" + paidAmount + ", paymentDate=" + paymentDate
				+ ", paymentStatus=" + paymentStatus + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
	}

	public LoanInstallment() {

	}
}
