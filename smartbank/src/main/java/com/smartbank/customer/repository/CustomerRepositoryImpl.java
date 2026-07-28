package com.smartbank.customer.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.smartbank.customer.model.Customer;
import com.smartbank.customer.model.CustomerStatus;
/// this class is not required since we are implementing jpa repository
@Repository
public class CustomerRepositoryImpl /* implements CustomerRepository */ {
	private Map<Long, Customer> customerDatabase = new HashMap<>();
	private Long customerSequence = 1000L;

	//@Override
	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		customerSequence++;
		customer.setCustomerId(customerSequence);
		customer.setCustomerStatus(CustomerStatus.ACTIVE);
		customer.setCreatedDate(LocalDate.now());
		customer.setLastModifiedDate(LocalDateTime.now());
		customerDatabase.put(customer.getCustomerId(), customer);
		return customer;
	}

	//@Override
	public Customer findByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return customerDatabase.get(customerId);
	}

	//@Override
	public Customer findByAadharNumber(String aadharNumber) {
		for (Customer cust : customerDatabase.values()) {
			if (cust.getAadharNumber().equals(aadharNumber)) {
				return cust;
			}
		}

		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public Customer findByPanNumber(String panNumber) {
		for (Customer cust : customerDatabase.values()) {
			if (cust.getPanNumber().equals(panNumber)) {
				return cust;
			}
		}
		return null;
	}

	//@Override
	public Customer updateContactDetails(Customer customer) {
		// TODO Auto-generated method stub
		customerDatabase.put(customer.getCustomerId(), customer);
		return customer;

	}

	//@Override
	public Customer findByEmail(String email) {

		for (Customer cust : customerDatabase.values()) {
			if (cust.getAadharNumber().equals(email)) {
				return cust;
			}
		}
		return null;
	}

	//@Override
	public Customer findByPhoneNumber(String phoneNumber) {
		for (Customer cust : customerDatabase.values()) {
			if (cust.getAadharNumber().equals(phoneNumber)) {
				return cust;
			}
		}
		return null;
	}

	//@Override
	public Customer update(Customer customer) {
		// TODO Auto-generated method stub
		customerDatabase.put(customer.getCustomerId(), customer);
		return customer;
	}

}
