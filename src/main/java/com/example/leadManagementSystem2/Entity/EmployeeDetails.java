package com.example.leadManagementSystem2.Entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class EmployeeDetails {

	@Id
	@UuidGenerator(style = UuidGenerator.Style.RANDOM)
	private UUID id;
	
	private String name;
	private String userName;
	private String address;
	private String phone;
	private String panNumber;
	private String aadhaar;
	private String accountHolderName;
	private String accountNumber;
	private String ifscCode;
	private String branchAddress;
	private String password;
	private String role;
	private boolean approve;
	
	@OneToOne
	private Users_Credentials users_Credentials;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isApprove() {
		return approve;
	}
	public void setApprove(boolean approve) {
		this.approve = approve;
	}
	
	public Users_Credentials getUsers_Credentials() {
		return users_Credentials;
	}
	public void setUsers_Credentials(Users_Credentials users_Credentials) {
		this.users_Credentials = users_Credentials;
	}
	public EmployeeDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeDetails(UUID id, String name, String userName, String address, String phone, String panNumber,
			String aadhaar, String accountHolderName, String accountNumber, String ifscCode, String branchAddress,
			String password, String role, boolean approve, Users_Credentials users_Credentials) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.address = address;
		this.phone = phone;
		this.panNumber = panNumber;
		this.aadhaar = aadhaar;
		this.accountHolderName = accountHolderName;
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.branchAddress = branchAddress;
		this.password = password;
		this.role = role;
		this.approve = approve;
		this.users_Credentials = users_Credentials;
	}
	
	@Override
	public String toString() {
		return "EmployeeDetails [id=" + id + ", name=" + name + ", userName=" + userName + ", address=" + address
				+ ", phone=" + phone + ", panNumber=" + panNumber + ", aadhaar=" + aadhaar + ", accountHolderName="
				+ accountHolderName + ", accountNumber=" + accountNumber + ", ifscCode=" + ifscCode + ", branchAddress="
				+ branchAddress + ", password=" + password + ", role=" + role + ", approve=" + approve
				+ ", users_Credentials=" + users_Credentials + "]";
	}
	

	
	
	
	
	
}
