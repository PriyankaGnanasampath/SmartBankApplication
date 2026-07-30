package com.smartbank.account.dto;

import java.math.BigDecimal;
import java.util.List;

import com.smartbank.account.model.AccountType;

public class OpenAccountRequest {
	private List<Long> customerids;
	private AccountType accountType;
	private BigDecimal openingBalance;

	public List<Long> getCustomerids() {
		return customerids;
	}

	public void setCustomerids(List<Long> customerids) {
		this.customerids = customerids;
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

	@Override
	public String toString() {
		return "OpenAccountRequest [customerids=" + customerids + ", accountType=" + accountType + ", openingBalance="
				+ openingBalance + "]";
	}

	public OpenAccountRequest() {

	}
}
