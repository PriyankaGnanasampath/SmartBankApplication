package com.smartbank.loan.exception;

public class LoanAlreadyClosedException extends RuntimeException {
	public LoanAlreadyClosedException(String message)
	{
		super(message);
	}

}
