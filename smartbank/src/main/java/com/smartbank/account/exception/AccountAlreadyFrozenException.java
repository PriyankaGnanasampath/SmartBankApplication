package com.smartbank.account.exception;

public class AccountAlreadyFrozenException extends RuntimeException {
	public AccountAlreadyFrozenException(String message)
	{
		super(message);
	}
}
