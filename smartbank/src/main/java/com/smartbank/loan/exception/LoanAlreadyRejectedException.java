package com.smartbank.loan.exception;

public class LoanAlreadyRejectedException extends RuntimeException {
	public LoanAlreadyRejectedException(String message)
	{
		super(message);
	}

}
