package com.smartbank.loan.dto;

public class DisburseLoanRequest {

	private String disbursementDate;
	
	public String getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(String disbursementDate) {
		this.disbursementDate = disbursementDate;
	}

	@Override
	public String toString() {
		return "DisburseLoanRequest [disbursementDate=" + disbursementDate + "]";
	}

	public DisburseLoanRequest()
	{
		
	}
}
