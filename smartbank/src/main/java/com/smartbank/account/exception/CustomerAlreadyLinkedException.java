package com.smartbank.account.exception;

public class CustomerAlreadyLinkedException extends RuntimeException {
	public CustomerAlreadyLinkedException(String message)
	{
		super(message);
	}
}
