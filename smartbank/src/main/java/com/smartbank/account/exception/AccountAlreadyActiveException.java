package com.smartbank.account.exception;

public class AccountAlreadyActiveException extends RuntimeException {
	public AccountAlreadyActiveException(String message)
	{
		super(message);
	}
}
