package com.smartbank.account.exception;

public class AccountAlreadyExistsException extends RuntimeException {
	public AccountAlreadyExistsException(String message)
	{
		super(message);
	}
}
