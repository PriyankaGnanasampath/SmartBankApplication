package com.smartbank.loan.exception;

public class LoanAlreadyActiveException extends RuntimeException {
	public LoanAlreadyActiveException(String message)
	{
		super(message);
	}

}
