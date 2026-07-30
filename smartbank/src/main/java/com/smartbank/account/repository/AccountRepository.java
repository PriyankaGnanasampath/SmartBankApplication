package com.smartbank.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbank.account.model.Account;
import com.smartbank.account.model.AccountStatus;
import com.smartbank.account.model.AccountType;
import com.smartbank.customer.model.Customer;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findAccountByAccountNumber(String accountNumber);

	Optional<List<Account>> findByAccountStatus(AccountStatus status);

	Optional<List<Account>> findByAccountType(AccountType accountType);

	Optional<List<Account>> findByCustomersCustomerId(Long customerId);

	boolean existsByAccountNumber(String accountNumber);

	boolean existsByCustomersCustomerIdAndAccountType(Long customerId, AccountType accountType);
}
