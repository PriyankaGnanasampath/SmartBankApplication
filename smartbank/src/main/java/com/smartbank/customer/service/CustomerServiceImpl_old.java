package com.smartbank.customer.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.smartbank.common.constants.ValidationConstants;
import com.smartbank.customer.dto.UpdateContactDetailsRequest;
import com.smartbank.customer.exception.AadhaarAlreadyExistsException;
import com.smartbank.customer.exception.CustomerNotFoundException;
import com.smartbank.customer.exception.EmailAlreadyExistsException;
import com.smartbank.customer.exception.InvalidCustomerRequestException;
import com.smartbank.customer.exception.PanAlreadyExistsException;
import com.smartbank.customer.model.Customer;
import com.smartbank.customer.model.MaritalStatus;
import com.smartbank.customer.repository.CustomerRepository;

/*public class CustomerServiceImpl_old implements CustomerService {*/
public class CustomerServiceImpl_old {
	/*
	 * 
	 * private CustomerRepository customerRepository;
	 * 
	 * 
	 * public CustomerServiceImpl_old(CustomerRepository customerRepository) {
	 * this.customerRepository = customerRepository; }
	 * 
	 * 
	 * 
	 * public Customer registerCustomer(Customer customer) { // TODO Auto-generated
	 * method stub validateMandatoryFields(customer); validateDateOfBirth(customer);
	 * validateFamilyDetails(customer); validateFieldPatterns(customer);
	 * validatePan(customer.getPanNumber());
	 * validateAadhar(customer.getAadharNumber());
	 * validateEmail(customer.getEmail());
	 * validatePhoneNumber(customer.getPhoneNumber()); return
	 * customerRepository.save(customer); }
	 * 
	 * 
	 * public Customer getCustomerByCustomerId(Long customerId) { // TODO
	 * Auto-generated method stub Customer existingCustomer =
	 * customerRepository.findByCustomerId(customerId); if (existingCustomer ==
	 * null) { throw new CustomerNotFoundException("Customer not found with ID: " +
	 * customerId); } return existingCustomer; }
	 * 
	 * 
	 * public Customer getCustomerByPanNumber(String panNumber) { // TODO
	 * Auto-generated method stub Customer existingCustomer =
	 * customerRepository.findByPanNumber(panNumber); if (existingCustomer == null)
	 * { throw new CustomerNotFoundException("Customer not found with PAN: " +
	 * panNumber); } return existingCustomer; }
	 * 
	 * 
	 * public Customer getCustomerByAadharNumber(String aadharNumber) { // TODO
	 * Auto-generated method stub Customer existingCustomer =
	 * customerRepository.findByAadharNumber(aadharNumber); if (existingCustomer ==
	 * null) { throw new
	 * CustomerNotFoundException("Customer not found with AADHAR: " + aadharNumber);
	 * } return existingCustomer; }
	 * 
	 * 
	 * private void validateContactDetails(UpdateContactDetailsRequest request) { if
	 * (request.getEmail() == null || request.getEmail().isBlank()) { throw new
	 * InvalidCustomerRequestException("EMAIL is Mandatory"); } if
	 * (request.getPhoneNumber() == null || request.getPhoneNumber().isBlank()) {
	 * throw new InvalidCustomerRequestException("Phone Number is Mandatory"); } }
	 * private void validateContactFieldPatterns(UpdateContactDetailsRequest
	 * request) { if
	 * (!request.getPhoneNumber().matches(ValidationConstants.PHONE_REGEX)) { throw
	 * new InvalidCustomerRequestException("Invalid Phone Number"); } if
	 * (!request.getEmail().matches(ValidationConstants.EMAIL_REGEX)) { throw new
	 * InvalidCustomerRequestException("Invalid Email Address"); }
	 * 
	 * } private void validatePan(String panNumber) { Customer existingCustomerByPan
	 * = customerRepository.findByPanNumber(panNumber); if (existingCustomerByPan !=
	 * null) { throw new PanAlreadyExistsException("PAN already exist: " +
	 * panNumber); } }
	 * 
	 * private void validateAadhar(String aadharNumber) { Customer
	 * existingCustomerByAadhar =
	 * customerRepository.findByAadharNumber(aadharNumber); if
	 * (existingCustomerByAadhar != null) { throw new
	 * AadhaarAlreadyExistsException("AADHAR already exist: " + aadharNumber); } }
	 * 
	 * private void validateEmail(String email) {
	 * 
	 * Customer existingCustomerByEmail = customerRepository.findByEmail(email); if
	 * (existingCustomerByEmail != null) { throw new
	 * EmailAlreadyExistsException("EMAIL already exist: " + email); } }
	 * 
	 * private void validatePhoneNumber(String phoneNumber) { Customer
	 * existingCustomerByEmail = customerRepository.findByEmail(phoneNumber); if
	 * (existingCustomerByEmail != null) { throw new
	 * EmailAlreadyExistsException("EMAIL already exist: " + phoneNumber); } }
	 * 
	 * private void validateMandatoryFields(Customer customer) { if
	 * (customer.getFirstName() == null || customer.getFirstName().isBlank()) {
	 * throw new InvalidCustomerRequestException("First Name is Mandatory"); } if
	 * (customer.getLastName() == null || customer.getLastName().isBlank()) { throw
	 * new InvalidCustomerRequestException("Last Name is Mandatory"); } if
	 * (customer.getPanNumber() == null || customer.getPanNumber().isBlank()) {
	 * throw new InvalidCustomerRequestException("PAN Number is Mandatory"); } if
	 * (customer.getAadharNumber() == null || customer.getAadharNumber().isBlank())
	 * { throw new InvalidCustomerRequestException("AAdhar Number is Mandatory"); }
	 * if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isBlank())
	 * { throw new InvalidCustomerRequestException("Phone Number is Mandatory"); }
	 * if (customer.getEmail() == null || customer.getEmail().isBlank()) { throw new
	 * InvalidCustomerRequestException("EMAIL is Mandatory"); } if
	 * (customer.getPhoneNumber() == null || customer.getPhoneNumber().isBlank()) {
	 * throw new InvalidCustomerRequestException("Phone Number is Mandatory"); } }
	 * 
	 * private void validateFieldPatterns(Customer customer) {
	 * 
	 * if (!customer.getPanNumber().matches(ValidationConstants.PAN_REGEX)) { throw
	 * new InvalidCustomerRequestException("Invalid PAN Number"); } if
	 * (!customer.getAadharNumber().matches(ValidationConstants.AADHAR_REGEX)) {
	 * throw new InvalidCustomerRequestException("Invalid AAdhar Number"); } if
	 * (!customer.getPhoneNumber().matches(ValidationConstants.PHONE_REGEX)) { throw
	 * new InvalidCustomerRequestException("Invalid Phone Number"); } if
	 * (!customer.getEmail().matches(ValidationConstants.EMAIL_REGEX)) { throw new
	 * InvalidCustomerRequestException("Invalid Email Address"); }
	 * 
	 * }
	 * 
	 * private void validateDateOfBirth(Customer customer) { if
	 * (customer.getDateOfBirth() == null) { throw new
	 * InvalidCustomerRequestException("DateOfBirth is Mandatory"); } if
	 * (customer.getDateOfBirth().isAfter(LocalDate.now())) { throw new
	 * InvalidCustomerRequestException("DateOfBirth Should not be a Future Date"); }
	 * 
	 * }
	 * 
	 * private void validateFamilyDetails(Customer customer) { if
	 * (customer.getMaritalStatus().equals(MaritalStatus.SINGLE) &&
	 * (customer.getFatherName() == null || customer.getFatherName().isBlank())) {
	 * throw new InvalidCustomerRequestException("Father Name is Mandatory");
	 * 
	 * } if (customer.getMaritalStatus().equals(MaritalStatus.MARRIED) &&
	 * (customer.getFatherName() == null || customer.getFatherName().isBlank()) &&
	 * (customer.getSpouseName() == null || customer.getSpouseName().isBlank())) {
	 * throw new InvalidCustomerRequestException("Spouse Name is Mandatory");
	 * 
	 * } }
	 * 
	 * private void validateImmutableFields(Customer existingCustomer, Customer
	 * customer) { if
	 * (!existingCustomer.getCustomerId().equals(customer.getCustomerId())) { throw
	 * new
	 * InvalidCustomerRequestException("Customer ID cannot be modified once registered."
	 * ); } if (!existingCustomer.getFirstName().equals(customer.getFirstName())) {
	 * throw new
	 * InvalidCustomerRequestException("First Name cannot be modified once registered."
	 * ); } if (!existingCustomer.getLastName().equals(customer.getLastName())) {
	 * throw new
	 * InvalidCustomerRequestException("Last Name cannot be modified once registered."
	 * ); } if
	 * (!existingCustomer.getDateOfBirth().equals(customer.getDateOfBirth())) {
	 * throw new
	 * InvalidCustomerRequestException("DateOfBirth cannot be modified once registered."
	 * ); } if (!existingCustomer.getFatherName().equals(customer.getFatherName()))
	 * { throw new
	 * InvalidCustomerRequestException("Father Name cannot be modified once registered."
	 * ); } if (!existingCustomer.getPanNumber().equals(customer.getPanNumber())) {
	 * throw new
	 * InvalidCustomerRequestException("PAN Number cannot be modified once registered."
	 * ); } if
	 * (!existingCustomer.getAadharNumber().equals(customer.getAadharNumber())) {
	 * throw new
	 * InvalidCustomerRequestException("Aadhar Number cannot be modified once registered."
	 * ); } if
	 * (!existingCustomer.getCustomerStatus().equals(customer.getCustomerStatus()))
	 * { throw new
	 * InvalidCustomerRequestException("Customer Status cannot be modified once registered."
	 * ); } if
	 * (!existingCustomer.getCreatedDate().equals(customer.getCreatedDate())) {
	 * throw new
	 * InvalidCustomerRequestException("CreatedDate cannot be modified once registered."
	 * ); }
	 * 
	 * }
	 * 
	 * 
	 * public UpdateContactDetailsRequest updateCustomerContactDetails(Long
	 * cystomerId, UpdateContactDetailsRequest request) { // TODO Auto-generated
	 * method stub return null; }
	 */}
