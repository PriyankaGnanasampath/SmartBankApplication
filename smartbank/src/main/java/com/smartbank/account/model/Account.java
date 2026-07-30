package com.smartbank.account.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.smartbank.customer.model.Customer;

@Entity
@Table(name = "ACCOUNT")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	private String accountNumber;
	@NotBlank(message = "accountType should not be Blank")
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	@NotNull(message = "Opening Balance should not be Null")
	@Positive(message = "Opening Balance should be greater than Zero")
	private BigDecimal openingBalance;
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	private LocalDate openingDate;
	private LocalDate createdDate;
	private String createdBy;
	private LocalDateTime lastModifiedDate;
	private String modifiedBy;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ACCOUNT_CUSTOMER", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	@NotEmpty(message = "Opening Balance should not be Empty")
	private Set<Customer> customers;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return openingBalance;
	}

	public void setBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", accountType=" + accountType
				+ ", openingBalance=" + openingBalance + ", accountStatus=" + accountStatus + ", openingDate="
				+ openingDate + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", lastModifiedDate="
				+ lastModifiedDate + ", modifiedBy=" + modifiedBy + ", customers=" + customers + "]";
	}

	public Account() {

	}

}
