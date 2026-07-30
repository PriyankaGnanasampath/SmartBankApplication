package com.smartbank.account.exception;

public class AccountAlreadyClosedException extends RuntimeException {
	public AccountAlreadyClosedException(String message)
	{
		super(message);
	}
}
