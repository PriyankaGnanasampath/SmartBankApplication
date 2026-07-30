package com.smartbank.account.exception;

public class MinimumBalanceException extends RuntimeException {
	public MinimumBalanceException(String message)
	{
		super(message);
	}
}
