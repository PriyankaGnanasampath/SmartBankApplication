package com.smartbank.customer.exception;

public class PanAlreadyExistsException extends RuntimeException {
	public PanAlreadyExistsException(String message) {
		super(message);
	}

}
