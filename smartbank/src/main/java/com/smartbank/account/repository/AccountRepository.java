package com.smartbank.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbank.account.model.Account;
import com.smartbank.account.model.AccountStatus;
import com.smartbank.account.model.AccountType;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findAccountByAccountNumber(String accountNumber);

	List<Account> findByAccountStatus(AccountStatus status);

	List<Account> findByAccountType(AccountType accountType);

	List<Account> findByCustomersCustomerId(Long customerId);

	boolean existsByAccountNumber(String accountNumber);
}
