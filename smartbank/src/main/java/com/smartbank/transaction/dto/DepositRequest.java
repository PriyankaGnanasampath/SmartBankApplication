package com.smartbank.transaction.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.smartbank.transaction.model.TransactionMode;
import com.smartbank.transaction.model.TransactionType;

public class DepositRequest {
	@NotEmpty(message = "AccountNumber should not be Empty")
	private String accountNumber;
	@NotEmpty(message = "AccountNumber should not be Empty")
	@Positive(message = "Amount hould not be Empty")
	private BigDecimal amount;
	@NotEmpty(message = "TransactionMode should not be Empty")
	private TransactionMode transactionMode;
	@NotEmpty(message = "Remarks should not be Empty")
	private String remarks;
		
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
		return "DepositRequest [accountNumber=" + accountNumber + ", amount=" + amount + ", transactionMode="
				+ transactionMode + ", remarks=" + remarks + "]";
	}
	public DepositRequest()
	{
		
	}
}
