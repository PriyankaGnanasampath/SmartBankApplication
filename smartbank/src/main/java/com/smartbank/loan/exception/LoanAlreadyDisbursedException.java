package com.smartbank.loan.exception;

public class LoanAlreadyDisbursedException extends RuntimeException {
	public LoanAlreadyDisbursedException(String message)
	{
		super(message);
	}

}
