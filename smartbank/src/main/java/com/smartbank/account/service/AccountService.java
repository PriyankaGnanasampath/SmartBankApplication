package com.smartbank.account.service;

import java.util.List;
import java.util.Optional;

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

	public Account closeAccount(Long accountId);

	public Account freezeAccount(Long accountId);

	public Account activateAccount(Long accountId);

	public Account updateAccount(Long accountId, UpdateAccountRequest updateAccountRequest);

	public Account linkCustomerToAccount();
}
