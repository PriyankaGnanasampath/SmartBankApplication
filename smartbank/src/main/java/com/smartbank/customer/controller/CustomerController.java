package com.smartbank.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbank.account.model.Account;
import com.smartbank.customer.dto.UpdateContactDetailsRequest;
import com.smartbank.customer.model.Customer;
import com.smartbank.customer.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Customer Management", description = "APIs for customer registration, retrieval, contact update and customer closure.")
@RestController
@RequestMapping("/customers")
public class CustomerController {
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Operation(summary = "Register Customer", description = "Registers a new customer in SmartBank.")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Customer registered successfully"),

			@ApiResponse(responseCode = "400", description = "Invalid customer request"),

			@ApiResponse(responseCode = "409", description = "Duplicate customer information") })
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {

		Customer savedCustomer = customerService.registerCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);

	}

	@Operation(summary = "Get Customer By ID", description = "Get the customer from customer table based on the given ID in SmartBank.")

	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Customer retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid Customer Data"),
			@ApiResponse(responseCode = "404", description = "Customer not found") })
	@GetMapping("/{customerId}")
	public Customer getCustomerByCustomerId(@PathVariable Long customerId) {
		return customerService.getCustomerByCustomerId(customerId);
	}

	@Operation(summary = "Get Customer By PAN ", description = "Get the customer from customer table based on the given PAN in SmartBank.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Customer retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid Customer Data"),
			@ApiResponse(responseCode = "404", description = "Customer not found") })
	@GetMapping("/pan/{panNumber}")
	public Customer getCustomerByPanNumber(@PathVariable String panNumber) {
		return customerService.getCustomerByPanNumber(panNumber);
	}

	@Operation(summary = "Get Customer By AADHAR ", description = "Get the customer from customer table based on the given AADHAR in SmartBank.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Customer retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid Customer Data"),
			@ApiResponse(responseCode = "404", description = "Customer not found") })
	@GetMapping("/aadhar/{aadharNumber}")
	public Customer getCustomerByAadhaarNumber(@PathVariable String aadharNumber) {
		return customerService.getCustomerByAadharNumber(aadharNumber);
	}
	
	

	@Operation(summary = "Get Customer By EMAIL ", description = "Get the customer from customer table based on the given EMAIL in SmartBank.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Customer retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid Customer Data"),
			@ApiResponse(responseCode = "404", description = "Customer not found") })
	@GetMapping("/email/{emailAddress}")
	public Customer getCustomerByEmail(@PathVariable String emailAddress) {
		return customerService.getCustomerByEmail(emailAddress);
	}

	@Operation(summary = "Get Customer By PHONE ", description = "Get the customer from customer table based on the given PHONE NUMBER in SmartBank.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Customer retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid Customer Data"),
			@ApiResponse(responseCode = "404", description = "Customer not found") })
	@GetMapping("/phone/{phoneNumber}")
	public Customer getCustomerByPhoneNumber(@PathVariable String phoneNumber) {
		return customerService.getCustomerByPhoneNumber(phoneNumber);
	}
	

	@Operation(summary = "Update Contact Details ", description = "Update the Contact details for the given customer in Smartbank Application")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Customer Updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid customer request"),
			@ApiResponse(responseCode = "409", description = "Duplicate customer information") })
	@PutMapping("/{customerId}/contact-details")
	public ResponseEntity<Customer> updateContactDetails(@PathVariable Long customerId,
			@RequestBody UpdateContactDetailsRequest request) {
		Customer updatedCustomerContactDetails = customerService.updateContactDetails(customerId, request);
		return ResponseEntity.ok(updatedCustomerContactDetails);

	}

	@Operation(summary = "Close Customer", description = "Close the given customer record in Smartbank Application")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Customer Data Closed successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid customer request"),
			@ApiResponse(responseCode = "409", description = "Duplicate customer information") })
	@PatchMapping("/{customerId}/close")
	public ResponseEntity<Customer> closeCustomer(@PathVariable Long customerId) {
		Customer closeCustomer = customerService.closeCustomer(customerId);
		return ResponseEntity.ok(closeCustomer);

	}
	
	
}
