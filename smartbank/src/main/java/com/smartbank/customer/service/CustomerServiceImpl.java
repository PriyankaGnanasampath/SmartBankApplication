package com.smartbank.customer.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbank.common.constants.ValidationConstants;
import com.smartbank.customer.dto.UpdateContactDetailsRequest;
import com.smartbank.customer.exception.AadhaarAlreadyExistsException;
import com.smartbank.customer.exception.CustomerAlreadyClosedException;
import com.smartbank.customer.exception.CustomerNotFoundException;
import com.smartbank.customer.exception.EmailAlreadyExistsException;
import com.smartbank.customer.exception.InvalidCustomerRequestException;
import com.smartbank.customer.exception.PanAlreadyExistsException;
import com.smartbank.customer.exception.PhoneNumberAlreadyExistsException;
import com.smartbank.customer.model.Customer;
import com.smartbank.customer.model.CustomerStatus;
import com.smartbank.customer.model.MaritalStatus;
import com.smartbank.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	private CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	public Customer registerCustomer(Customer customer) {
		// TODO Auto-generated method stub
		LOGGER.info("Customer registration started.");
		validateMandatoryFields(customer);
		validateDateOfBirth(customer);
		validateFamilyDetails(customer);
		validateFieldPatterns(customer);
		validatePan(customer.getPanNumber());
		validateAadhar(customer.getAadharNumber());
		validateEmail(customer.getEmail());
		validatePhoneNumber(customer.getPhoneNumber());
		customer.setCustomerStatus(CustomerStatus.ACTIVE);
		customer.setCreatedDate(LocalDate.now());
		customer.setLastModifiedDate(LocalDateTime.now());
		Customer savedCustomer = customerRepository.save(customer);
		LOGGER.info("Customer registered successfully. CustomerId={}", savedCustomer.getCustomerId());
		return savedCustomer;
	}

	@Override
	public Customer getCustomerByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		LOGGER.info("Fetching  Customer by CustomerId= {}", customerId);
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with CustomerId: " + customerId));
	}

	@Override
	public Customer getCustomerByPanNumber(String panNumber) {
		// TODO Auto-generated method stub
		LOGGER.info("Fetching  Customer by PanNumber={}", panNumber);
		return customerRepository.findCustomerByPanNumber(panNumber)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with PanNumber: " + panNumber));
	}

	@Override
	public Customer getCustomerByAadharNumber(String aadharNumber) {
		// TODO Auto-generated method stub
		LOGGER.info("Fetching  Customer by Aadhar= {}", aadharNumber);
		return customerRepository.findCustomerByAadharNumber(aadharNumber)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with Aadhar: " + aadharNumber));
	}

	@Override
	public Customer updateContactDetails(Long customerId, UpdateContactDetailsRequest request) {

		LOGGER.info("Updating Contact Details started.CustomerId= {}", customerId);
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with CustomerId: " + customerId));

		validateContactDetails(request);
		validateContactFieldPatterns(request);
		if (!existingCustomer.getEmail().equals(request.getEmail())) {
			validateEmail(request.getEmail());
		}
		if (!existingCustomer.getPhoneNumber().equals(request.getPhoneNumber())) {
			validatePhoneNumber(request.getPhoneNumber());
		}
		existingCustomer.setEmail(request.getEmail());
		existingCustomer.setPhoneNumber(request.getPhoneNumber());
		if (request.getAddress() != null) {
			existingCustomer.setAddress(request.getAddress());
		}
		if (request.getOccupationType() != null) {
			existingCustomer.setOccupationType(request.getOccupationType());
		}
		existingCustomer.setLastModifiedDate(LocalDateTime.now());
		Customer updateContactDetails = customerRepository.save(existingCustomer);
		LOGGER.info("Contact Details successfully Updated. CustomerID={}", updateContactDetails.getCustomerId());
		return updateContactDetails;
	}

	@Override
	public Customer closeCustomer(Long customerId) {
		// TODO Auto-generated method stub
		LOGGER.info("Closing Customer. CustomerId= {}", customerId);
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with CustomerId: " + customerId));
		if (existingCustomer.getCustomerStatus() == CustomerStatus.CLOSED) {
			LOGGER.warn("Customer already closed. CustomerId={}", customerId);
			throw new CustomerAlreadyClosedException("Customer already closed. CustomerId: " + customerId);
		}
		existingCustomer.setCustomerStatus(CustomerStatus.CLOSED);
		existingCustomer.setLastModifiedDate(LocalDateTime.now());
		LOGGER.info("Close Customer successfully completed.CustomerId={}", customerId);
		return customerRepository.save(existingCustomer);
	}

	private void validateContactDetails(UpdateContactDetailsRequest request) {
		if (request.getEmail() == null || request.getEmail().isBlank()) {
			throw new InvalidCustomerRequestException("EMAIL is Mandatory");
		}
		if (request.getPhoneNumber() == null || request.getPhoneNumber().isBlank()) {
			throw new InvalidCustomerRequestException("Phone Number is Mandatory");
		}
	}

	private void validateContactFieldPatterns(UpdateContactDetailsRequest request) {
		if (!request.getPhoneNumber().matches(ValidationConstants.PHONE_REGEX)) {
			throw new InvalidCustomerRequestException("Invalid Phone Number");
		}
		if (!request.getEmail().matches(ValidationConstants.EMAIL_REGEX)) {
			throw new InvalidCustomerRequestException("Invalid Email Address");
		}

	}

	private void validatePan(String panNumber) {
		if (customerRepository.findCustomerByPanNumber(panNumber).isPresent()) {
			LOGGER.warn("Duplicate PAN detected. PanNumber={}", panNumber);
			throw new PanAlreadyExistsException("PAN already exists. PanNumber: " + panNumber);
		}
	}

	private void validateAadhar(String aadharNumber) {
		if (customerRepository.findCustomerByAadharNumber(aadharNumber).isPresent()) {
			LOGGER.warn("Duplicate AADHAR detected. Aadhar={}", aadharNumber);
			throw new AadhaarAlreadyExistsException("AADHAR already exist.AadharNumber: " + aadharNumber);
		}
	}

	private void validateEmail(String email) {
		if (customerRepository.findCustomerByEmail(email).isPresent()) {
			LOGGER.warn("Duplicate Email detected. Email={}", email);
			throw new EmailAlreadyExistsException("EMAIL already exist.Email: " + email);
		}
	}

	private void validatePhoneNumber(String phoneNumber) {
		if (customerRepository.findCustomerByPhoneNumber(phoneNumber).isPresent()) {
			LOGGER.warn("Duplicate PhoneNumber detected. PhoneNumber={}", phoneNumber);
			throw new PhoneNumberAlreadyExistsException("Phone Number already exist.PhoneNumber: " + phoneNumber);
		}
	}

	private void validateMandatoryFields(Customer customer) {
		if (customer.getFirstName() == null || customer.getFirstName().isBlank()) {
			throw new InvalidCustomerRequestException("First Name is Mandatory");
		}
		if (customer.getLastName() == null || customer.getLastName().isBlank()) {
			throw new InvalidCustomerRequestException("Last Name is Mandatory");
		}
		if (customer.getPanNumber() == null || customer.getPanNumber().isBlank()) {
			throw new InvalidCustomerRequestException("PAN Number is Mandatory");
		}
		if (customer.getAadharNumber() == null || customer.getAadharNumber().isBlank()) {
			throw new InvalidCustomerRequestException("AAdhar Number is Mandatory");
		}
		if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isBlank()) {
			throw new InvalidCustomerRequestException("Phone Number is Mandatory");
		}
		if (customer.getEmail() == null || customer.getEmail().isBlank()) {
			throw new InvalidCustomerRequestException("EMAIL is Mandatory");
		}
	}

	private void validateFieldPatterns(Customer customer) {

		if (!customer.getPanNumber().matches(ValidationConstants.PAN_REGEX)) {
			throw new InvalidCustomerRequestException("Invalid PAN Number");
		}
		if (!customer.getAadharNumber().matches(ValidationConstants.AADHAR_REGEX)) {
			throw new InvalidCustomerRequestException("Invalid AAdhar Number");
		}
		if (!customer.getPhoneNumber().matches(ValidationConstants.PHONE_REGEX)) {
			throw new InvalidCustomerRequestException("Invalid Phone Number");
		}
		if (!customer.getEmail().matches(ValidationConstants.EMAIL_REGEX)) {
			throw new InvalidCustomerRequestException("Invalid Email Address");
		}

	}

	private void validateDateOfBirth(Customer customer) {
		if (customer.getDateOfBirth() == null) {
			throw new InvalidCustomerRequestException("DateOfBirth is Mandatory");
		}
		if (customer.getDateOfBirth().isAfter(LocalDate.now())) {
			throw new InvalidCustomerRequestException("DateOfBirth Should not be a Future Date");
		}

	}

	private void validateFamilyDetails(Customer customer) {
		if (customer.getMaritalStatus().equals(MaritalStatus.SINGLE)
				&& (customer.getFatherName() == null || customer.getFatherName().isBlank())) {
			throw new InvalidCustomerRequestException("Father Name is Mandatory");

		}
		if (customer.getMaritalStatus().equals(MaritalStatus.MARRIED)
				&& ((customer.getFatherName() == null || customer.getFatherName().isBlank())
						|| (customer.getSpouseName() == null || customer.getSpouseName().isBlank()))) {
			throw new InvalidCustomerRequestException("Father and Spouse Name is Mandatory");

		}
	}

	@Override
	public Customer getCustomerByEmail(String emailAddress) {
		// TODO Auto-generated method stub
		LOGGER.info("Fetching  Customer by Email= {}", emailAddress);
		return customerRepository.findCustomerByEmail(emailAddress)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with EMAIL: " + emailAddress));
	}

	/*
	 * @Override public Customer getCustomerByPhoneNumber(String phoneNumber) { //
	 * TODO Auto-generated method stub
	 * LOGGER.info("Fetching  Customer by PhoneNumber= {}", phoneNumber); return
	 * customerRepository.findByPhoneNumber(phoneNumber).orElseThrow( () -> new
	 * CustomerNotFoundException("Customer not found with PhoneNumber: " +
	 * phoneNumber)); }
	 */
	
	@Override
	public Customer getCustomerByPhoneNumber(String phoneNumber) {return customerRepository.findCustomerByPhoneNumber(phoneNumber)
	        .orElseThrow(() ->
            new CustomerNotFoundException(
                    "Customer not found with PhoneNumber: " + phoneNumber));}

}
