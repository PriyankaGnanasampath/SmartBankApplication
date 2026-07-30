package com.smartbank.account.service;

import java.util.List;

import com.smartbank.account.dto.OpenAccountRequest;
import com.smartbank.account.dto.UpdateAccountRequest;
import com.smartbank.account.model.Account;

public interface AccountService {
	public Account openAccount(OpenAccountRequest openAccountRequest);

	public Account getAccountById(Long accountId);

	public Account getAccountByAccountNumber(Long accountNumber);

	public Account updateAccount(Long accountId,UpdateAccountRequest updateAccountRequest );

	public Account closeAccount(Long accountId);

	public Account freezeAccount(Long accountId);

	public List<Account> getAccountsByCustomer(Long customerId);

	public Account linkCustomerToAccount();
}
