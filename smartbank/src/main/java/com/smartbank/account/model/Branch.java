package com.smartbank.account.model;

public enum Branch {
	CHENNAI("Chennai", "SMBK000101"), BANGALORE("Bangalore", "SMBK000102"), HYDERABAD("Hyderabad", "SMBK000103"),
	MUMBAI("Mumbai", "SMBK000104"), COIMBATORE("Coimbatore", "SMBK000105");

	private final String ifscCode;
	private final String branch;

	Branch(String branchName, String ifscCode) {
		this.branch = branchName;
		this.ifscCode = ifscCode;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public String getBranchName() {
		return branch;
	}

}
