package com.smartbank.loan.exception;

public class InvalidLoanRequestException extends RuntimeException {
	public InvalidLoanRequestException(String message)
	{
		super(message);
	}

}
