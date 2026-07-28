package com.smartbank.customer.exception;

public class CustomerNotFoundException extends RuntimeException{
	public CustomerNotFoundException(String message)
	{
		super(message);
	}

}
