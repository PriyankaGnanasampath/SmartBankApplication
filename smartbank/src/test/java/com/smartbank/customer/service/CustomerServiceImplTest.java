package com.smartbank.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.smartbank.common.JsonUtil;
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

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	Customer customerRequest;
	Customer customerResponse;
	UpdateContactDetailsRequest updateContactDetailsRequest;
	PanAlreadyExistsException panAlreadyExistsException;

	@BeforeEach
	void setup() throws Exception {
		customerRequest = JsonUtil.readJson("request/CustomerServiceRequest.json", Customer.class);
		customerResponse = JsonUtil.readJson("response/CustomerServiceResponse.json", Customer.class);
		updateContactDetailsRequest = JsonUtil.readJson("request/UpdateContactDetailsRequest.json",
				UpdateContactDetailsRequest.class);
		
	}

	@Test
	@DisplayName("Register Customer - Customer Should complete the registration successfully")
	/*
	 * When Jenkins or SonarQube generates reports, you'll see:
	 * registerCustomer_WhenValidCustomer_ShouldReturnSavedCustomer It's readable
	 * for developers, but not very friendly for QA managers, architects, or product
	 * owners. So the displayName is used to make it clear
	 */
	public void registerCustomer_WhenValidCustomer_ShouldReturnSavedCustomer() throws Exception {
		// in this sequence we have to write the code,this is called
		// AAA(Arrange,Act,Assert)pattern
		// Arrange
		when(customerRepository.save(customerRequest)).thenReturn(customerResponse);
		// Act
		Customer savedCustomer = customerServiceImpl.registerCustomer(customerRequest);
		// Assert
		assertEquals(customerResponse, savedCustomer);
		// verify
		verify(customerRepository, times(1)).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenInValidCustomer_ShouldThrowPanAlreadyExistsException() throws Exception {
		// in this sequence we have to write the code,this is called
		// AAA(Arrange,Act,Assert)pattern
		// Arrange

		when(customerRepository.findCustomerByPanNumber(customerRequest.getPanNumber()))
				.thenReturn(Optional.of(customerResponse));
		PanAlreadyExistsException exception = assertThrows(PanAlreadyExistsException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("PAN already exists. PanNumber: BZCPP8969M", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenInValidCustomer_ShouldThrowAadharAlreadyExistsException() throws Exception {
		// in this sequence we have to write the code,this is called
		// AAA(Arrange,Act,Assert)pattern
		// Arrange

		when(customerRepository.findCustomerByAadharNumber(customerRequest.getAadharNumber()))
				.thenReturn(Optional.of(customerResponse));
		AadhaarAlreadyExistsException exception = assertThrows(AadhaarAlreadyExistsException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("AADHAR already exist.AadharNumber: 789456123025", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenInValidCustomer_ShouldThrowEmaillAlreadyExistsException() throws Exception {
		when(customerRepository.findCustomerByEmail(customerRequest.getEmail()))
				.thenReturn(Optional.of(customerResponse));

		EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("EMAIL already exist.Email: priyag75@gmail.com", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenInValidCustomer_ShouldThrowPhoneNumberAlreadyExistsException() throws Exception {
		when(customerRepository.findCustomerByPhoneNumber(customerRequest.getPhoneNumber()))
				.thenReturn(Optional.of(customerResponse));
		PhoneNumberAlreadyExistsException exception = assertThrows(PhoneNumberAlreadyExistsException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("Phone Number already exist.PhoneNumber: 9876543210", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenRequestHasFutureDateOfBirth_ShouldThrowInvalidCustomerRequestException()
			throws Exception {
		LocalDate futureDOB = LocalDate.of(2026, 07, 29);
		customerRequest.setDateOfBirth(futureDOB);
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("DateOfBirth Should not be a Future Date", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenRequestHasInvalidDateOfBirth_ShouldThrowInvalidCustomerRequestException()
			throws Exception {
		customerRequest.setDateOfBirth(null);
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("DateOfBirth is Mandatory", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenRequestHasInvalidEmailFormat_ShouldThrowInvalidCustomerRequestException()
			throws Exception {
		customerRequest.setEmail("priya@gmail");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("Invalid Email Address", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenRequestHasInvalidPhoneNumber_ShouldThrowInvalidCustomerRequestException()
			throws Exception {
		customerRequest.setPhoneNumber("7894561");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("Invalid Phone Number", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenRequestHasInvalidAadhar_ShouldThrowInvalidCustomerRequestException()
			throws Exception {
		customerRequest.setAadhaarNumber("7894561");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("Invalid AAdhar Number", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenRequestHasInvalidPan_ShouldThrowInvalidCustomerRequestException()
			throws Exception {
		customerRequest.setPanNumber("7894561");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("Invalid PAN Number", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenMarriedWithoutSpouseName_ShouldThrowInvalidCustomerRequestException()
			throws Exception {
		customerRequest.setMaritalStatus(MaritalStatus.MARRIED);
		customerRequest.setSpouseName("");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("Father and Spouse Name is Mandatory", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenMarriedWithoutFatherName_ShouldThrowInvalidCustomerRequestException()
			throws Exception {
		customerRequest.setMaritalStatus(MaritalStatus.MARRIED);
		customerRequest.setFatherName("");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("Father and Spouse Name is Mandatory", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenSingleWithoutFatherName_ShouldThrowInvalidCustomerRequestException()
			throws Exception {
		customerRequest.setMaritalStatus(MaritalStatus.SINGLE);
		customerRequest.setFatherName("");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("Father Name is Mandatory", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenFirstNameIsMissing_ShouldThrowInvalidCustomerRequestException() throws Exception {
		customerRequest.setFirstName("");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("First Name is Mandatory", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenLastNameIsMissing_ShouldThrowInvalidCustomerRequestException() throws Exception {
		customerRequest.setLastName("");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("Last Name is Mandatory", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenPanNumberIsMissing_ShouldThrowInvalidCustomerRequestException() throws Exception {
		customerRequest.setPanNumber("");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("PAN Number is Mandatory", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenAadharIsMissing_ShouldThrowInvalidCustomerRequestException() throws Exception {
		customerRequest.setAadhaarNumber("");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("AAdhar Number is Mandatory", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenPoneNumberIsMissing_ShouldThrowInvalidCustomerRequestException() throws Exception {
		customerRequest.setPhoneNumber("");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("Phone Number is Mandatory", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void registerCustomer_WhenEmailIsMissing_ShouldThrowInvalidCustomerRequestException() throws Exception {
		customerRequest.setEmail("");
		InvalidCustomerRequestException exception = assertThrows(InvalidCustomerRequestException.class,
				() -> customerServiceImpl.registerCustomer(customerRequest));
		assertEquals("EMAIL is Mandatory", exception.getMessage());
		verify(customerRepository, never()).save(customerRequest);
	}

	@Test
	public void getCustomerById_WhenFetchingValidCustomer_ShouldReturnCustomerById() throws Exception {
		when(customerRepository.findById(customerRequest.getCustomerId())).thenReturn(Optional.of(customerResponse));
		Customer customerById = customerServiceImpl.getCustomerByCustomerId(customerRequest.getCustomerId());
		assertEquals(customerResponse, customerById);
	}

	@Test
	public void getCustomerByPan_WhenFetchingInValidCustomerById_ShouldThrowCustomerNotFoundException()
			throws Exception {
		customerRequest.setCustomerId(1001L);
		CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
				() -> customerServiceImpl.getCustomerByCustomerId(customerRequest.getCustomerId()));
		assertEquals("Customer not found with CustomerId: 1001", exception.getMessage());
	}

	@Test
	public void getCustomerByPan_WhenFetchingValidCustomer_ShouldReturnCustomerByPan() throws Exception {
		when(customerRepository.findCustomerByPanNumber(customerRequest.getPanNumber()))
				.thenReturn(Optional.of(customerResponse));
		Customer customerByPan = customerServiceImpl.getCustomerByPanNumber(customerRequest.getPanNumber());
		assertEquals(customerResponse, customerByPan);
		verify(customerRepository, times(0)).save(customerByPan);
	}

	@Test
	public void getCustomerByPan_WhenFetchingInValidCustomerByPan_ShouldThrowCustomerNotFoundException()
			throws Exception {
		CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
				() -> customerServiceImpl.getCustomerByPanNumber(customerRequest.getPanNumber()));
		assertEquals("Customer not found with PanNumber: BZCPP8969M", exception.getMessage());
	}

	@Test
	public void getCustomerByAadhar_WhenFetchingValidCustomer_ShouldReturnCustomerByAadhar() throws Exception {
		when(customerRepository.findCustomerByAadharNumber(customerRequest.getAadharNumber()))
				.thenReturn(Optional.of(customerResponse));
		Customer customerByAadhar = customerServiceImpl.getCustomerByAadharNumber(customerRequest.getAadharNumber());
		assertEquals(customerResponse, customerByAadhar);
	}

	@Test
	public void getCustomerByAadhar_WhenFetchingInValidCustomerByAadhar_ShouldThrowCustomerNotFoundException()
			throws Exception {
		CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
				() -> customerServiceImpl.getCustomerByAadharNumber(customerRequest.getAadharNumber()));
		assertEquals("Customer not found with Aadhar: 789456123025", exception.getMessage());
	}

	@Test
	public void updateCustomer_WhenValidCustomer_ShouldReturnUpdatedCustomer() throws Exception {
		when(customerRepository.findById(1001L)).thenReturn(Optional.of(customerResponse));
		when(customerRepository.save(customerResponse)).thenReturn(customerResponse);
		Customer updatedCustomer = customerServiceImpl.updateContactDetails(1001L, updateContactDetailsRequest);
		assertEquals(customerResponse.getAddress(), updatedCustomer.getAddress());
		assertEquals(customerResponse.getEmail(), updatedCustomer.getEmail());
		assertEquals(customerResponse.getOccupationType(), updatedCustomer.getOccupationType());
		assertEquals(customerResponse.getPhoneNumber(), updatedCustomer.getPhoneNumber());
		verify(customerRepository, times(1)).findById(updatedCustomer.getCustomerId());
	}

	@Test
	public void closeCustomer_WhenValidCustomer_ShouldReturnCloseCustomer() throws Exception {
		when(customerRepository.findById(1001L)).thenReturn(Optional.of(customerResponse));
		when(customerRepository.save(customerResponse)).thenReturn(customerResponse);
		Customer closeCustomer = customerServiceImpl.closeCustomer(1001L);
		customerResponse.setCustomerStatus(CustomerStatus.CLOSED);
		assertEquals(customerResponse.getCustomerStatus(), closeCustomer.getCustomerStatus());
		verify(customerRepository, times(1)).save(closeCustomer);
	}

	@Test
	public void closeCustomer_WhenAlreadyClosedCustomer_ShouldThrowCustomerAlreadyClosedException() throws Exception {
		customerResponse.setCustomerStatus(CustomerStatus.CLOSED);
		when(customerRepository.findById(1001L)).thenReturn(Optional.of(customerResponse));
		CustomerAlreadyClosedException exception = assertThrows(CustomerAlreadyClosedException.class,
				() -> customerServiceImpl.closeCustomer(1001L));
		assertEquals("Customer already closed. CustomerId: 1001", exception.getMessage());
		verify(customerRepository, never()).save(customerResponse);
	}

	@Test
	public void closeCustomer_WhenInvalidCustomerId_ShouldThrowCustomerNotFoundException() throws Exception {
		
		when(customerRepository.findById(1001L)).thenReturn(Optional.empty());
		CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
				() -> customerServiceImpl.closeCustomer(1001L));
		assertEquals("Customer not found with CustomerId: 1001", exception.getMessage());
		verify(customerRepository, never()).save(customerResponse);
	}

}
