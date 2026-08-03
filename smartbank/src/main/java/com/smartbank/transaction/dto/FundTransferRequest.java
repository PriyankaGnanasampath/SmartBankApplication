package com.smartbank.transaction.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.smartbank.transaction.model.TransactionMode;
import com.smartbank.transaction.model.TransactionType;

public class FundTransferRequest {
	@NotEmpty(message = "FromAccountNumber should not be Empty")
	private String fromAccountNumber;
	@NotEmpty(message = "ToAccountNumber should not be Empty")
	private String toAccountNumber;
	@NotEmpty(message = "AccountNumber should not be Empty")
	@Positive(message = "Amount hould not be Empty")
	private BigDecimal amount;
	@NotEmpty(message = "TransactionMode should not be Empty")
	private TransactionMode transactionMode;
	@NotEmpty(message = "Remarks should not be Empty")
	private String remarks;
	
	public String getFromAccountNumber() {
		return fromAccountNumber;
	}
	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}
	public String getToAccountNumber() {
		return toAccountNumber;
	}
	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
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
		return "FundTransferRequest [fromAccountNumber=" + fromAccountNumber + ", toAccountNumber=" + toAccountNumber
				+ ", amount=" + amount + ", transactionMode=" + transactionMode + ", remarks=" + remarks
				+ "]";
	}
	public FundTransferRequest()
	{
		
	}
}
