package com.smartbank.account.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
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
	@Column(unique = true, nullable = false)
	private String accountNumber;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	@Column(nullable = false)
	private BigDecimal openingBalance;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AccountStatus accountStatus;
	@Column(nullable = false)
	private String ifscCode;
	@Enumerated(EnumType.STRING)
	private Branch branch;
	private LocalDate openingDate;
	private LocalDateTime createdDate;
	private String createdBy;
	private LocalDateTime lastModifiedDate;
	private String modifiedBy;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ACCOUNT_CUSTOMER", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
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

	public BigDecimal getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Branch getBranchName() {
		return branch;
	}

	public void setBranchName(Branch branchName) {
		this.branch = branchName;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
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
				+ ", openingBalance=" + openingBalance + ", accountStatus=" + accountStatus + ", ifscCode=" + ifscCode
				+ ", branchName=" + branch + ", openingDate=" + openingDate + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate + ", modifiedBy=" + modifiedBy
				+ ", customers=" + customers + "]";
	}

	public Account() {

	}

}
