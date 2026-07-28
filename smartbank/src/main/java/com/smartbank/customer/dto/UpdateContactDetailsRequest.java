package com.smartbank.customer.dto;

import com.smartbank.customer.model.Address;
import com.smartbank.customer.model.OccupationType;

import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateContactDetailsRequest {
	@Schema(description = "Customer Email Address", example = "priya@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
	private String email;
	@Schema(description = "Customer Phone Number", example = "9876543210", requiredMode = Schema.RequiredMode.REQUIRED)
	private String phoneNumber;
	private Address address;
	private OccupationType occupationType;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public OccupationType getOccupationType() {
		return occupationType;
	}

	public void setOccupationType(OccupationType occupationType) {
		this.occupationType = occupationType;
	}

}
