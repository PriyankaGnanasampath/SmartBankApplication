package com.smartbank.account.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbank.account.dto.OpenAccountRequest;
import com.smartbank.account.dto.UpdateAccountRequest;
import com.smartbank.account.exception.InvalidAccountRequestException;
import com.smartbank.account.model.Account;
import com.smartbank.account.service.AccountService;
import com.smartbank.customer.model.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account", description = "Account module")
@Tag(name = "Account Management", description = "APIs for Account registration, Account update,Account freeze and Account closure.")

public class AccountController {
	@Autowired
	AccountService accountService;

	@PostMapping("/open")
	@Operation(operationId = "post", description = "Create a new account for the existing customer")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Account Created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid account request"),
			@ApiResponse(responseCode = "409", description = "Duplicate account information") })
	public ResponseEntity<Account> openAccount(@Valid @RequestBody OpenAccountRequest account)throws InvalidAccountRequestException {
		Account openedAccount = accountService.openAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(openedAccount);
	}

	@GetMapping("/{accountId}")
	public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountById(accountId));
	}

	@GetMapping("/account-number/{accountNumber}")
	public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable Long accountNumber) {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountByAccountNumber(accountNumber));
	}

	@PutMapping("{accountId}")
	public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody UpdateAccountRequest updateAccountRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.updateAccount(accountId,updateAccountRequest));
	}

	@PatchMapping("{accountId}/close")
	public ResponseEntity<Account> closeAccount(@PathVariable Long accountId) {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.closeAccount(accountId));
	}

	@PatchMapping("{accountId}/freeze")
	public ResponseEntity<Account> freezeAccount(@PathVariable Long accountId) {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.freezeAccount(accountId));
	}

	@GetMapping("/{customerId}/accounts")
	public ResponseEntity<List<Account>> getAccountsByCustomer(@PathVariable Long customerId) {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountsByCustomer(customerId));
	}

}
