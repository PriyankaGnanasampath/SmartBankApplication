package com.smartbank.customer.exception;

public class CustomerAlreadyClosedException extends RuntimeException{
	public CustomerAlreadyClosedException(String message)
	{
		super(message);
	}

}
