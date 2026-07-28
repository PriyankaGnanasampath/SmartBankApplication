package com.smartbank.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.smartbank.common.dto.ErrorResponse;
import com.smartbank.customer.exception.AadhaarAlreadyExistsException;
import com.smartbank.customer.exception.CustomerAlreadyClosedException;
import com.smartbank.customer.exception.CustomerNotFoundException;
import com.smartbank.customer.exception.EmailAlreadyExistsException;
import com.smartbank.customer.exception.InvalidCustomerRequestException;
import com.smartbank.customer.exception.PanAlreadyExistsException;
import com.smartbank.customer.exception.PhoneNumberAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_001", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(CustomerAlreadyClosedException.class)
	public ResponseEntity<ErrorResponse> handleCustomerAlreadyClosedxception(CustomerAlreadyClosedException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_002", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	@ExceptionHandler(InvalidCustomerRequestException.class)
	public ResponseEntity<ErrorResponse> handleInvalidCustomerRequestxception(InvalidCustomerRequestException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_003", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(AadhaarAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleAadhaarAlreadyExistsException(AadhaarAlreadyExistsException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_003", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_003", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	@ExceptionHandler(PanAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handlePanAlreadyExistsException(PanAlreadyExistsException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_003", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	@ExceptionHandler(PhoneNumberAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleIPhoneNumberAlreadyExistxception(PhoneNumberAlreadyExistsException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUS_003", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	
	
}
