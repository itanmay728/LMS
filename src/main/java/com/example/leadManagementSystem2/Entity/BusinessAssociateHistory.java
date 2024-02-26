package com.example.leadManagementSystem2.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class BusinessAssociateHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long baId;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String businessName;
	private String panNumber;
	private String aadhaarNumber;
	private String accountNumber;
	private String accountHolderName;
	private String ifscCode;
	private String branchAddress;
	private String rejectionReason;
	
	
	public BusinessAssociateHistory() {
		super();
	}





	public BusinessAssociateHistory(BusinessAssociate ba) {
	    this.baId = ba.getId();
	    this.name = ba.getName();
	    this.email = ba.getUserName();
	    this.phone = ba.getPhone();
	    this.address = ba.getAddress();
	    this.businessName = ba.getBusinessName();
	    this.panNumber = ba.getPanNumber();
	    this.aadhaarNumber = ba.getAadhaarNumber();
	    this.accountHolderName=ba.getAccountHolderName();
	    this.accountNumber=ba.getAccountNumber();
	    this.ifscCode=ba.getIfscCode();
	    this.branchAddress=ba.getBranchAddress();
	}





	public Long getBaId() {
		return baId;
	}

	public void setBaId(Long baId) {
		this.baId = baId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	
	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	
	public String getRejectionReason() {
		return rejectionReason;
	}


	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}





	@Override
	public String toString() {
		return "BusinessAssociateHistory [baId=" + baId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", businessName=" + businessName + ", panNumber=" + panNumber
				+ ", aadhaarNumber=" + aadhaarNumber + ", accountNumber=" + accountNumber + ", accountHolderName="
				+ accountHolderName + ", ifscCode=" + ifscCode + ", branchAddress=" + branchAddress
				+ ", rejectionReason=" + rejectionReason + "]";
	}
	
	




}
