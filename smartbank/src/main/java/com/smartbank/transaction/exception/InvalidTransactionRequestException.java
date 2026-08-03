package com.smartbank.transaction.exception;

public class InvalidTransactionRequestException extends RuntimeException {
	public InvalidTransactionRequestException(String message) {
		super(message);
	}
}
