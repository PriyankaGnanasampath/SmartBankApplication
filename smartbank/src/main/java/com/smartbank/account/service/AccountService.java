package com.smartbank.account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.smartbank.account.dto.OpenAccountRequest;
import com.smartbank.account.dto.UpdateAccountRequest;
import com.smartbank.account.model.Account;
import com.smartbank.account.model.AccountStatus;
import com.smartbank.account.model.AccountType;

public interface AccountService {
	public Account openAccount(OpenAccountRequest openAccountRequest);

	public Account getAccountById(Long accountId);

	public Account getAccountByAccountNumber(String accountNumber);

	public List<Account> getAccountsByCustomer(Long customerId);

	public List<Account> getAccountsByAccountType(AccountType accountType);

	public List<Account> getAccountsByAccountStatus(AccountStatus accountStatus);
	public List<Account> getAllAccounts();

	@Transactional
	public Account closeAccount(String accountNumber);
	@Transactional
	public Account freezeAccount(String accountNumber);
	@Transactional
	public Account activateAccount(String accountNumber);
	@Transactional
	public Account updateAccount(Long accountId, UpdateAccountRequest updateAccountRequest);

	public Account linkCustomerToAccount();
}
