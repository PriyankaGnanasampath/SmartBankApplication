package com.smartbank.account.dto;

import com.smartbank.account.model.Branch;

public class UpdateAccountRequest {
	private Branch branch;
	private String ifscCode;
	private String Nominee;

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getNominee() {
		return Nominee;
	}

	public void setNominee(String nominee) {
		Nominee = nominee;
	}

	@Override
	public String toString() {
		return "UpdateAccountRequest [branch=" + branch + ", ifscCode=" + ifscCode + ", Nominee=" + Nominee + "]";
	}

}
