package com.smartbank.account.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartbank.account.common.AccountConstants;
import com.smartbank.account.dto.OpenAccountRequest;
import com.smartbank.account.dto.UpdateAccountRequest;
import com.smartbank.account.exception.AccountAlreadyActiveException;
import com.smartbank.account.exception.AccountAlreadyClosedException;
import com.smartbank.account.exception.AccountNotFoundException;
import com.smartbank.account.exception.InvalidAccountRequestException;
import com.smartbank.account.exception.MinimumBalanceException;
import com.smartbank.account.helper.AccountHelper;
import com.smartbank.account.model.Account;
import com.smartbank.account.model.AccountStatus;
import com.smartbank.account.model.AccountType;
import com.smartbank.account.repository.AccountRepository;
import com.smartbank.customer.exception.CustomerAlreadyClosedException;
import com.smartbank.customer.exception.CustomerNotFoundException;
import com.smartbank.customer.model.Customer;
import com.smartbank.customer.model.CustomerStatus;
import com.smartbank.customer.model.OccupationType;
import com.smartbank.customer.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService {
	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;
	private final AccountHelper accountHelper;

	public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository,
			AccountHelper accountHelper) {
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
		this.accountHelper = accountHelper;
	}

	private static long accountSequence = 10000;
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Override

	public Account openAccount(OpenAccountRequest openAccountRequest) {
		logger.info("Account creation started");
		Account account = new Account();
		List<Customer> customer = getCustomerDetails(openAccountRequest);
		validateCustomers(customer, openAccountRequest);
		validateCustomerStatus(customer);
		validateDuplicateCustomers(openAccountRequest);
		validateAccountInformation(openAccountRequest, customer);
		String accountNumber = generateAccountNumber(openAccountRequest);
		populateAccount(account, openAccountRequest, accountNumber, customer);
		Account savedAccount = accountRepository.save(account);
		logger.info("Account has been successfully created. Account={}", account);
		return savedAccount;
	}

	private List<Customer> getCustomerDetails(OpenAccountRequest openAccountRequest)

	{
		logger.info("Fetching the customer from CustomerTable");

		return customerRepository.findAllById(openAccountRequest.getCustomerIds());

	}

	private void validateCustomers(List<Customer> customer, OpenAccountRequest openAccountRequest) {
		// TODO Auto-generated method stub
		logger.info("Validating the Customer present in the database");

		if (openAccountRequest.getCustomerIds().size() != customer.size()) {
			throw new CustomerNotFoundException(
					"customer not present in the database" + openAccountRequest.getCustomerIds());
		}
	}

	private void validateCustomerStatus(List<Customer> customer) {
		// TODO Auto-generated method stub
		logger.info("Validating the Customer Status present in the database.");
		customer.forEach(status -> {
			if (!status.getCustomerStatus().equals(CustomerStatus.ACTIVE)) {
				throw new CustomerAlreadyClosedException(
						"Customer is already closed.CustomerId" + status.getCustomerId());
			}
		});
	}

	private void validateDuplicateCustomers(OpenAccountRequest openAccountRequest) {
		// TODO Auto-generated method stub
		logger.info("Validating the Duplicate Customer");
		Set<Long> newCustomerId = new HashSet<>();
		List<Long> duplicateCustId = openAccountRequest.getCustomerIds().stream()
				.filter(customerId -> !newCustomerId.add(customerId)).collect(Collectors.toList());
		if (!duplicateCustId.isEmpty()) {
			throw new InvalidAccountRequestException("Duplicate Customer present in the request" + duplicateCustId);
		}
	}

	private void validateAccountInformation(OpenAccountRequest openAccountRequest, List<Customer> customer) {
		switch (openAccountRequest.getAccountType()) {
		case SAVINGS: {
			validateSavingsAccount(openAccountRequest);
			return;
		}
		case CURRENT: {
			validateCurrentAccount(openAccountRequest);
			return;
		}
		case JOINT: {
			validateJointAccount(openAccountRequest);
			return;
		}
		case LOAN: {
			validateLoanAccount(openAccountRequest);
			return;
		}
		case NRI: {
			validateNriAccount(openAccountRequest, customer);
			return;
		}
		case STUDENT: {
			validateStudentAccount(openAccountRequest, customer);
			return;
		}
		default: {
			throw new InvalidAccountRequestException("Invalid AccountType" + openAccountRequest.getAccountType());

		}
		}
	}

	private void validateSavingsAccount(OpenAccountRequest openAccountRequest) {
		// TODO Auto-generated method stub
		logger.info("Entering the Savings Account validation");

		if (openAccountRequest.getOpeningBalance().compareTo(AccountConstants.SAVINGS_MIN_BALANCE) < 0) {
			throw new MinimumBalanceException(
					"Mininum balance for savings account is:" + AccountConstants.SAVINGS_MIN_BALANCE);
		}
		if (openAccountRequest.getCustomerIds().size() != 1) {
			throw new InvalidAccountRequestException(
					"For saving account only one customer should be allowed" + openAccountRequest.getCustomerIds());
		}
		logger.info("Exiting the Savings Account Validation");

	}

	private void validateCurrentAccount(OpenAccountRequest openAccountRequest) {
		// TODO Auto-generated method stub
		logger.info("Entering the Current Account validation");

		if (openAccountRequest.getOpeningBalance().compareTo(AccountConstants.CURRENT_MIN_BALANCE) < 0) {
			throw new MinimumBalanceException(
					"Mininum balance for savings account is:" + AccountConstants.CURRENT_MIN_BALANCE);
		}
		if (openAccountRequest.getCustomerIds().size() != 1) {
			throw new InvalidAccountRequestException(
					"For Current account only one customer should be allowed" + openAccountRequest.getCustomerIds());
		}
		logger.info("Exiting the Current Account validation");

	}

	private void validateLoanAccount(OpenAccountRequest openAccountRequest) {
		// TODO Auto-generated method stub
		logger.info("Entering the Loan Account validation");

		if (openAccountRequest.getOpeningBalance().equals(AccountConstants.LOAN_BALANCE)) {
			throw new MinimumBalanceException(
					"Loan opening balance should always be 0" + AccountConstants.LOAN_BALANCE);
		}
		if (openAccountRequest.getCustomerIds().size() != 1) {
			throw new InvalidAccountRequestException(
					"For Loan account only one customer should be allowed" + openAccountRequest.getCustomerIds());
		}
		logger.info("Exiting the Loan Account validation");

	}

	private void validateNriAccount(OpenAccountRequest openAccountRequest, List<Customer> customer) {
		// TODO Auto-generated method stub
		logger.info("Entering the NRI Account validation");

		if (openAccountRequest.getOpeningBalance().compareTo(AccountConstants.NRI_MIN_BALANCE) < 0) {
			throw new MinimumBalanceException("Mininum balance for NRI account is:" + AccountConstants.NRI_MIN_BALANCE);
		}
		if (openAccountRequest.getCustomerIds().size() != 1) {
			throw new InvalidAccountRequestException(
					"For NRI account only one customer should be allowed" + openAccountRequest.getCustomerIds());
		}
		customer.forEach(nationality -> {
			if (!nationality.getNationality().equalsIgnoreCase("Indian")) {
				throw new InvalidAccountRequestException("For NRI Customer should be Indian ");
			}
		});
		logger.info("Exiting the NRI Account validation");

	}

	private void validateStudentAccount(OpenAccountRequest openAccountRequest, List<Customer> customer) {
		// TODO Auto-generated method stub
		logger.info("Entering the Student Account validation");

		if (openAccountRequest.getOpeningBalance().compareTo(AccountConstants.STUDENT_MIN_BALANCE) < 0) {
			throw new MinimumBalanceException(
					"Mininum balance for Student account is:" + AccountConstants.STUDENT_MIN_BALANCE);
		}
		if (openAccountRequest.getCustomerIds().size() != 1) {
			throw new InvalidAccountRequestException(
					"For Student account only one customer should be allowed" + openAccountRequest.getCustomerIds());
		}
		customer.forEach(age -> {
			if (accountHelper.CalculateAgeFromDOB(age.getDateOfBirth()) > AccountConstants.STUDENT_MAX_AGE) {
				throw new InvalidAccountRequestException("Student Age should be less than or equal to 25");
			}
		});
		customer.forEach(occupation -> {
			if (!occupation.getOccupationType().equals(OccupationType.STUDENT)) {
				throw new InvalidAccountRequestException("Customer should be student");
			}
		});
		logger.info("Exiting the Student Account validation");

	}

	private void validateJointAccount(OpenAccountRequest openAccountRequest) {
		// TODO Auto-generated method stub
		logger.info("Entering the Joint Account validation");

		if (openAccountRequest.getOpeningBalance().compareTo(AccountConstants.JOINT_MIN_BALANCE) < 0) {
			throw new MinimumBalanceException(
					"Mininum balance for Joint account is:" + AccountConstants.JOINT_MIN_BALANCE);
		}
		if (openAccountRequest.getCustomerIds().size() != 2) {
			throw new InvalidAccountRequestException(
					"For Joint account only two customer should be allowed" + openAccountRequest.getCustomerIds());
		}
		logger.info("Exiting the Joint Account validation");

	}

	private String generateAccountNumber(OpenAccountRequest openAccountRequest) {

		// TODO Auto-generated method stub
		logger.info("Entering the Generate Account Number");

		String accountNumber = "";
		accountSequence++;
		switch (openAccountRequest.getAccountType()) {
		case SAVINGS: {
			accountNumber = AccountConstants.SAVINGS_ACCOUNT_PREFIX + accountSequence;
			logger.info(" AccountNumber has been successfully generated for Savings Account. AccountNumber={}",
					accountNumber);

			return accountNumber;
		}
		case CURRENT: {
			accountNumber = AccountConstants.CURRENT_ACCOUNT_PREFIX + accountSequence;
			logger.info(" AccountNumber has been successfully generated for Current Account. AccountNumber={}",
					accountNumber);

			return accountNumber;
		}
		case JOINT: {
			accountNumber = AccountConstants.JOINT_ACCOUNT_PREFIX + accountSequence;
			logger.info(" AccountNumber has been successfully generated for Joint Account. AccountNumber={}",
					accountNumber);

			return accountNumber;
		}
		case LOAN: {
			accountNumber = AccountConstants.LOAN_ACCOUNT_PREFIX + accountSequence;
			logger.info(" AccountNumber has been successfully generated for Loan Account. AccountNumber={}",
					accountNumber);

			return accountNumber;
		}
		case NRI: {
			accountNumber = AccountConstants.NRI_ACCOUNT_PREFIX + accountSequence;
			logger.info(" AccountNumber has been successfully generated for NRI Account. AccountNumber={}",
					accountNumber);

			return accountNumber;
		}
		case STUDENT: {
			accountNumber = AccountConstants.STUDENT_ACCOUNT_PREFIX + accountSequence;
			return accountNumber;
		}
		default:
		}
		return "";

	}

	private void populateAccount(Account account, OpenAccountRequest openAccountRequest, String accountNumber,
			List<Customer> customer) {
		// TODO Auto-generated method stub
		logger.info("Populate the Account Details");

		Set<Customer> cust = new HashSet<>(customer);
		account.setAccountNumber(accountNumber);
		account.setAccountStatus(AccountStatus.ACTIVE);
		account.setBranchName(openAccountRequest.getBranchName());
		account.setCreatedBy(AccountConstants.MODIFIED_USER_NAME);
		account.setIfscCode(openAccountRequest.getBranchName().getIfscCode());
		account.setCreatedDate(LocalDateTime.now());
		account.setCustomers(cust);
		account.setLastModifiedDate(LocalDateTime.now());
		account.setOpeningBalance(openAccountRequest.getOpeningBalance());
		account.setOpeningDate(LocalDate.now());
		logger.info(" Data has been successfully updated in the Account. Account={}", account);
	}

	@Override
	public Account getAccountById(Long accountId) {
		// TODO Auto-generated method stub

		return accountRepository.findById(accountId).orElseThrow(
				() -> new AccountNotFoundException("Account is not present in the database. AccountId=" + accountId));

	}

	@Override
	public Account getAccountByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub

		return accountRepository.findAccountByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException(
						"Account is not present in the database. AccountNumber=" + accountNumber));
	}

	@Override
	public Account updateAccount(Long accountId, UpdateAccountRequest updateAccountRequest) {
		// TODO Auto-generated method stub

		Account existingAccount = accountRepository.findById(accountId).orElseThrow(
				() -> new AccountNotFoundException("Account is not present in the database. AccountId=" + accountId));
		existingAccount.setBranchName(updateAccountRequest.getBranch());
		existingAccount.setIfscCode(updateAccountRequest.getBranch().getIfscCode());
		existingAccount.setLastModifiedDate(LocalDateTime.now());
		existingAccount.setModifiedBy(AccountConstants.MODIFIED_USER_NAME);
		return existingAccount;
	}

	@Override
	public Account freezeAccount(Long accountId) {
		// TODO Auto-generated method stub
		Account existingAccount = accountRepository.findById(accountId).orElseThrow(
				() -> new AccountNotFoundException("Account is not present in the database. AccountId=" + accountId));
		switch (existingAccount.getAccountStatus()) {
		case ACTIVE:
			existingAccount.setAccountStatus(AccountStatus.FROZEN);
			existingAccount.setModifiedBy(AccountConstants.MODIFIED_USER_NAME);
			existingAccount.setLastModifiedDate(LocalDateTime.now());
			break;
		case CLOSED:
			throw new AccountAlreadyClosedException("Account is already closed. AccountId:" + accountId);
		case FROZEN:
			throw new AccountAlreadyClosedException("Account is already Frozen. AccountId:" + accountId);
		default:
			break;
		}

		return existingAccount;
	}

	@Override
	public Account activateAccount(Long accountId) {
		Account existingAccount = accountRepository.findById(accountId).orElseThrow(
				() -> new AccountNotFoundException("Account is not present in the database. AccountId=" + accountId));
		switch (existingAccount.getAccountStatus()) {
		case ACTIVE:
			throw new AccountAlreadyActiveException("Account is already closed. AccountId:" + accountId);
		case CLOSED:
			throw new AccountAlreadyClosedException("Account is already closed. AccountId:" + accountId);
		case FROZEN:
			existingAccount.setAccountStatus(AccountStatus.ACTIVE);
			existingAccount.setModifiedBy(AccountConstants.MODIFIED_USER_NAME);
			existingAccount.setLastModifiedDate(LocalDateTime.now());

		default:
			break;
		}
		return existingAccount;
	}

	@Override
	public Account closeAccount(Long accountId) {
		// TODO Auto-generated method stub
		Account existingAccount = accountRepository.findById(accountId).orElseThrow(
				() -> new AccountNotFoundException("Account is not present in the database. AccountId=" + accountId));
		if (existingAccount.getAccountStatus().equals(AccountStatus.CLOSED)) {
			throw new AccountAlreadyClosedException("Account is already closed. AccountId:" + accountId);
		}
		if (existingAccount.getAccountStatus().equals(AccountStatus.ACTIVE)
				|| existingAccount.getAccountStatus().equals(AccountStatus.FROZEN)) {
			existingAccount.setAccountStatus(AccountStatus.CLOSED);
			existingAccount.setModifiedBy(AccountConstants.MODIFIED_USER_NAME);
			existingAccount.setLastModifiedDate(LocalDateTime.now());
		}

		return existingAccount;
	}

	@Override
	public List<Account> getAccountsByCustomer(Long customerId) {
		// TODO Auto-generated method stub

		customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(
				"Customer is not present in the database. CustomerId:" + customerId));

		return accountRepository.findByCustomersCustomerId(customerId).orElseThrow(() -> new CustomerNotFoundException(
				"Account is not present in the database for the customer. CustomerId:" + customerId));

	}

	@Override
	public Account linkCustomerToAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByAccountType(AccountType accountType) {
		// TODO Auto-generated method stub

		return accountRepository.findByAccountType(accountType).orElseThrow(() -> new AccountNotFoundException(
				"Account is not present in the database. AccountType=" + accountType));

	}

	@Override
	public List<Account> getAccountsByAccountStatus(AccountStatus accountStatus) {
		// TODO Auto-generated method stub
		return accountRepository.findByAccountStatus(accountStatus).orElseThrow(() -> new AccountNotFoundException(
				"Account is not present in the database. AccountStatus=" + accountStatus));
	}

}
