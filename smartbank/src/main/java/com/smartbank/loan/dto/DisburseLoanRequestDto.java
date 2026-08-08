package com.smartbank.loan.dto;

public class DisburseLoanRequestDto {

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

	public DisburseLoanRequestDto()
	{
		
	}
}
