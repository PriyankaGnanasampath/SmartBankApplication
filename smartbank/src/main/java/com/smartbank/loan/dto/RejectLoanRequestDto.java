package com.smartbank.loan.dto;

public class RejectLoanRequestDto {
	private String rejectedBy;
	private String remarks;
	private String reasonCode;

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getRejectedBy() {
		return rejectedBy;
	}

	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "RejectLoanRequestDto [rejectedBy=" + rejectedBy + ", remarks=" + remarks + ", reasonCode=" + reasonCode
				+ "]";
	}

	public RejectLoanRequestDto() {

	}

}
