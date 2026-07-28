package com.smartbank.customer.exception;

public class InvalidCustomerRequestException extends RuntimeException{
	public InvalidCustomerRequestException(String message)
	{
		super(message);
	}

}
