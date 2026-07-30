package com.smartbank.account.exception;

public class InvalidAccountRequestException extends RuntimeException {
	public InvalidAccountRequestException(String message)
	{
		super(message);
	}
}
