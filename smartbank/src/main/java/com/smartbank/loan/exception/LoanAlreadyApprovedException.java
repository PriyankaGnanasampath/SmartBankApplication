package com.smartbank.loan.exception;

public class LoanAlreadyApprovedException extends RuntimeException {
	public LoanAlreadyApprovedException(String message)
	{
		super(message);
	}

}
