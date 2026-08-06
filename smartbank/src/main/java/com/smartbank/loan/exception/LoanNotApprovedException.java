package com.smartbank.loan.exception;

public class LoanNotApprovedException extends RuntimeException {
	public LoanNotApprovedException(String message)
	{
		super(message);
	}

}
