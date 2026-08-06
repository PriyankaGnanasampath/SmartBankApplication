package com.smartbank.loan.dto;

import java.math.BigDecimal;

import com.smartbank.transaction.model.TransactionMode;

public class LoanPaymentRequest {
	private String loanNumber;

	private BigDecimal amount;

	private TransactionMode transactionMode;
	private String remarks;

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionMode getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(TransactionMode transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "LoanPaymentRequest [loanNumber=" + loanNumber + ", amount=" + amount + ", transactionMode="
				+ transactionMode + ", remarks=" + remarks + "]";
	}

	public LoanPaymentRequest() {

	}
}
