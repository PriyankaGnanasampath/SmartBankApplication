package com.smartbank.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartbank.account.dto.OpenAccountRequest;
import com.smartbank.account.dto.UpdateAccountRequest;
import com.smartbank.account.model.Account;
import com.smartbank.account.model.AccountType;
import com.smartbank.account.repository.AccountRepository;
import com.smartbank.customer.model.Customer;
import com.smartbank.customer.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Account openAccount(OpenAccountRequest openAccountRequest) {

		validateMandatoryFields();
		validateCustomers();
		validateCustomerStatus();
		validateDuplicateCustomers();
		validateSavingsAccount();
		validateCurrentAccount();
		validateLoanAccount();
		validateStudentAccount();
		validateNriAccount();
		validateJointAccount();
		generateAccountNumber();
		populateAccount();
		return null;
	}

	private void validateMandatoryFields() {
		// TODO Auto-generated method stub
		
	}

	private void validateCustomers() {
		// TODO Auto-generated method stub
		
	}

	private void validateCustomerStatus() {
		// TODO Auto-generated method stub
		
	}

	private void validateDuplicateCustomers() {
		// TODO Auto-generated method stub
		
	}

	private void validateSavingsAccount() {
		// TODO Auto-generated method stub
		
	}

	private void validateCurrentAccount() {
		// TODO Auto-generated method stub
		
	}

	private void validateLoanAccount() {
		// TODO Auto-generated method stub
		
	}

	private void validateStudentAccount() {
		// TODO Auto-generated method stub
		
	}

	private void validateNriAccount() {
		// TODO Auto-generated method stub
		
	}

	private void validateJointAccount() {
		// TODO Auto-generated method stub
		
	}

	private void generateAccountNumber() {
		// TODO Auto-generated method stub
		
	}

	private void populateAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account getAccountById(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountByAccountNumber(Long accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateAccount(Long accountId, UpdateAccountRequest updateAccountRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account closeAccount(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account freezeAccount(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByCustomer(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account linkCustomerToAccount() {
		// TODO Auto-generated method stub
		return null;
	}

}
