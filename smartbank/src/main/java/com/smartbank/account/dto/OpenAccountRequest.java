package com.smartbank.account.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.smartbank.account.model.AccountType;
import com.smartbank.account.model.Branch;

public class OpenAccountRequest {
	@NotEmpty(message = "Customer Id should not be Null or Empty")
	private List<Long> customerIds;
	@NotNull(message = "accountType should not be Null or Empty")
	private AccountType accountType;
	@NotNull(message = "Opening Balance should not be Null or Empty")
	@Positive(message = "Opening Balance should be greater than Zero")
	private BigDecimal openingBalance;
	@NotNull(message = "BranchName should not be Null or Empty")
	private Branch branchName;

		public List<Long> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<Long> customerIds) {
		this.customerIds = customerIds;
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

	public Branch getBranchName() {
		return branchName;
	}

	public void setBranchName(Branch branchName) {
		this.branchName = branchName;
	}

		@Override
	public String toString() {
		return "OpenAccountRequest [customerIds=" + customerIds + ", accountType=" + accountType + ", openingBalance="
				+ openingBalance + ", branchName=" + branchName + "]";
	}

		public OpenAccountRequest() {

	}
}
