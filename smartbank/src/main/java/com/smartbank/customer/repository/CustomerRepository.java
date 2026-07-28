package com.smartbank.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smartbank.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Optional<Customer> findCustomerByAadharNumber(String aadharNumber);

	public Optional<Customer> findCustomerByPanNumber(String panNumber);

	public Optional<Customer> findCustomerByEmail(String email);

	//public Optional<Customer> findByPhoneNumber(String phoneNumber);
	//@Query("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber")
	public Optional<Customer> findCustomerByPhoneNumber( String phoneNumber);
}
