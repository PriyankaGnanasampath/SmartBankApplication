package com.smartbank.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.smartbank.account.model.Account;
import com.smartbank.transaction.model.TransactionMode;
import com.smartbank.transaction.model.TransactionStatus;
import com.smartbank.transaction.model.TransactionType;

public class FundTransferResponse {
	private Long debitTransactionId;
	private Long creditTransactionId;
	private String debitTransactionReferenceNumber;
	private String creditTransactionReferenceNumber;
	private TransactionType transactionType;
	private TransactionMode transactionMode;
	private BigDecimal transactionAmount;
	private BigDecimal debitAccountBalance;
	private BigDecimal creditAccountBalance;
	private TransactionStatus transactionStatus;
	private String remarks;
	
	public Long getDebitTransactionId() {
		return debitTransactionId;
	}

	public void setDebitTransactionId(Long debitTransactionId) {
		this.debitTransactionId = debitTransactionId;
	}

	public Long getCreditTransactionId() {
		return creditTransactionId;
	}

	public void setCreditTransactionId(Long creditTransactionId) {
		this.creditTransactionId = creditTransactionId;
	}

	public String getDebitTransactionReferenceNumber() {
		return debitTransactionReferenceNumber;
	}

	public void setDebitTransactionReferenceNumber(String debitTransactionReferenceNumber) {
		this.debitTransactionReferenceNumber = debitTransactionReferenceNumber;
	}

	public String getCreditTransactionReferenceNumber() {
		return creditTransactionReferenceNumber;
	}

	public void setCreditTransactionReferenceNumber(String creditTransactionReferenceNumber) {
		this.creditTransactionReferenceNumber = creditTransactionReferenceNumber;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public TransactionMode getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(TransactionMode transactionMode) {
		this.transactionMode = transactionMode;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public BigDecimal getDebitAccountBalance() {
		return debitAccountBalance;
	}

	public void setDebitAccountBalance(BigDecimal debitAccountBalance) {
		this.debitAccountBalance = debitAccountBalance;
	}

	public BigDecimal getCreditAccountBalance() {
		return creditAccountBalance;
	}

	public void setCreditAccountBalance(BigDecimal creditAccountBalance) {
		this.creditAccountBalance = creditAccountBalance;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "FundTransferResponse [debitTransactionId=" + debitTransactionId + ", creditTransactionId="
				+ creditTransactionId + ", debitTransactionReferenceNumber=" + debitTransactionReferenceNumber
				+ ", creditTransactionReferenceNumber=" + creditTransactionReferenceNumber + ", transactionType="
				+ transactionType + ", transactionMode=" + transactionMode + ", transactionAmount=" + transactionAmount
				+ ", debitAccountBalance=" + debitAccountBalance + ", creditAccountBalance=" + creditAccountBalance
				+ ", transactionStatus=" + transactionStatus + ", remarks=" + remarks + "]";
	}

	

	public FundTransferResponse()
	{
		
	}
	
}
