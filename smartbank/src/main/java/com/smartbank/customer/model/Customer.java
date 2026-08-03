package com.smartbank.customer.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.smartbank.account.model.Account;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	private String firstName;
	private String lastName;
	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String email;

	private String phoneNumber;

	private LocalDate dateOfBirth;

	private String fatherName;
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;

	private String spouseName;

	private String aadharNumber;

	private String panNumber;
	@Enumerated(EnumType.STRING)
	private OccupationType occupationType;

	private String nationality;
	@Embedded
	private Address address;

	private LocalDate createdDate;

	private LocalDateTime lastModifiedDate;
	@Enumerated(EnumType.STRING)
	private CustomerStatus customerStatus;
	/*
	 * Customer customer = customerRepository.findById(1L).get(); If the
	 * relationship is:
	 * 
	 * @ManyToMany(fetch = FetchType.EAGER) Hibernate will execute something
	 * like:Fetch the customer.Immediately fetch all three accounts. Even if your
	 * code never calls: customer.getAccounts(); This can become expensive when a
	 * customer has many related records. If the relationship is:
	 * 
	 * @ManyToMany(fetch = FetchType.LAZY) Hibernate initially fetches only the
	 * customer.Later, when your code executes: customer.getAccounts(); only then
	 * will Hibernate fetch the accounts.This is called lazy loading.
	 * 
	 * The default fetch type depends on the relationship.Relationship Default Fetch
	 * Type
	 * 
	 * @ManyToOne EAGER
	 * 
	 * @OneToOne EAGER
	 * 
	 * @OneToMany LAZY
	 * 
	 * @ManyToMany LAZY
	 */

	/*
	 * @ManyToMany(mappedBy = "customers", fetch = FetchType.LAZY)
	 * 
	 * @JsonManagedReference private Set<Account> accounts;
	 * 
	 * public Set<Account> getAccounts() { return accounts; }
	 * 
	 * public void setAccounts(Set<Account> accounts) { this.accounts = accounts; }
	 * 
	 * ✅ No @JsonManagedReference ✅ No @JsonBackReference ✅ No infinite recursion ✅
	 * No 415 errors ✅ Simpler APIs ✅ Hibernate still creates and maintains the
	 * ACCOUNT_CUSTOMER bridge table.
	 * 
	 * This is the design I would recommend for your current SmartBank project. It
	 * keeps the code simpler while still allowing the bridge table to work exactly
	 * as intended. As your project grows and you genuinely need to fetch a
	 * customer's accounts directly, you can introduce the reverse relationship or
	 * use a query/DTO instead of exposing the entity relationship.
	 */ public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setAadhaarNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setCustomerStatus(CustomerStatus customerStatus) {
		this.customerStatus = customerStatus;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public String getFatherName() {
		return fatherName;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public OccupationType getOccupationType() {
		return occupationType;
	}

	public String getNationality() {
		return nationality;
	}

	public Address getAddress() {
		return address;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public CustomerStatus getCustomerStatus() {
		return customerStatus;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public void setOccupationType(OccupationType occupationType) {
		this.occupationType = occupationType;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth="
				+ dateOfBirth + ", fatherName=" + fatherName + ", maritalStatus=" + maritalStatus + ", spouseName="
				+ spouseName + ", aadharNumber=" + aadharNumber + ", panNumber=" + panNumber + ", occupationType="
				+ occupationType + ", nationality=" + nationality + ", address=" + address + ", createdDate="
				+ createdDate + ", lastModifiedDate=" + lastModifiedDate + ", customerStatus=" + customerStatus + "]";
	}

	public Customer() {

	}
}
