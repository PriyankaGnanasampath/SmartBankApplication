package com.smartbank.customer.service;

import com.smartbank.customer.dto.UpdateContactDetailsRequest;
import com.smartbank.customer.model.Customer;

public interface CustomerService {
	public Customer registerCustomer(Customer customer);

	public Customer getCustomerByCustomerId(Long customerId);

	public Customer getCustomerByPanNumber(String panNumber);

	public Customer getCustomerByAadharNumber(String aadharNumber);

	public Customer getCustomerByEmail(String emailAddress);

	public Customer getCustomerByPhoneNumber(String phoneNumber);

	public Customer updateContactDetails(Long customerId, UpdateContactDetailsRequest request);

	public Customer closeCustomer(Long customerId);
}
