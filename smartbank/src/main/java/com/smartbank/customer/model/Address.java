package com.smartbank.customer.model;

import javax.persistence.Embeddable;

import io.swagger.v3.oas.annotations.media.Schema;
@Embeddable
public class Address {
	@Schema(description = "Door Number", example = "no 10", requiredMode = Schema.RequiredMode.REQUIRED)
	private String doorNumber;
	@Schema(description = "Street", example = "sundarar steer", requiredMode = Schema.RequiredMode.REQUIRED)
	private String street;
	@Schema(description = "Area", example = "thamarai nagar", requiredMode = Schema.RequiredMode.REQUIRED)
	private String area;
	@Schema(description = "City", example = "Tambaram", requiredMode = Schema.RequiredMode.REQUIRED)
	private String city;
	@Schema(description = "District", example = "chennai", requiredMode = Schema.RequiredMode.REQUIRED)
	private String district;
	@Schema(description = "State", example = "tamilnadu", requiredMode = Schema.RequiredMode.REQUIRED)
	private String state;
	@Schema(description = "Country", example = "India", requiredMode = Schema.RequiredMode.REQUIRED)
	private String country;
	@Schema(description = "Postal Code", example = "600001", requiredMode = Schema.RequiredMode.REQUIRED)
	private String postalCode;
	@Schema(description = "Landmark", example = "near indian oil petrol bun", requiredMode = Schema.RequiredMode.REQUIRED)
	private String landmark;

	public String getDoorNumber() {
		return doorNumber;
	}

	public String getStreet() {
		return street;
	}

	public String getArea() {
		return area;
	}

	public String getCity() {
		return city;
	}

	public String getDistrict() {
		return district;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	public void setSreet(String street) {
		this.street = street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Address() {

	}
}
