package com.example.leadManagementSystem2.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class BusinessAssociate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String userName;
	
	private String phone;
	
	private String address;
	
	private String businessName;
	
	private String panNumber;
	
	private String aadhaarNumber;
	
	private String accountNumber;
	private String accountHolderName;
	private String ifscCode;
	private String branchAddress;
	
	private String password;
	
	private boolean approval;

	@OneToOne(mappedBy = "businessAssociate", cascade = CascadeType.ALL)
	private Users_Credentials users_Credentials;
	
	@OneToMany(mappedBy = "businessAssociate")
	private List<Leads> leads;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public Users_Credentials getUsers_Credentials() {
		return users_Credentials;
	}

	public void setUsers_Credentials(Users_Credentials users_Credentials) {
		this.users_Credentials = users_Credentials;
	}

	public List<Leads> getLeads() {
		return leads;
	}

	public void setLeads(List<Leads> leads) {
		this.leads = leads;
	}

	public BusinessAssociate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessAssociate(Long id, String name, String userName, String phone, String address, String businessName,
			String panNumber, String aadhaarNumber, String accountNumber, String accountHolderName, String ifscCode,
			String branchAddress, String password, boolean approval, Users_Credentials users_Credentials,
			List<Leads> leads) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.phone = phone;
		this.address = address;
		this.businessName = businessName;
		this.panNumber = panNumber;
		this.aadhaarNumber = aadhaarNumber;
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.ifscCode = ifscCode;
		this.branchAddress = branchAddress;
		this.password = password;
		this.approval = approval;
		this.users_Credentials = users_Credentials;
		this.leads = leads;
	}

	@Override
	public String toString() {
		return "BusinessAssociate [id=" + id + ", name=" + name + ", userName=" + userName + ", phone=" + phone
				+ ", address=" + address + ", businessName=" + businessName + ", panNumber=" + panNumber
				+ ", aadhaarNumber=" + aadhaarNumber + ", accountNumber=" + accountNumber + ", accountHolderName="
				+ accountHolderName + ", ifscCode=" + ifscCode + ", branchAddress=" + branchAddress + ", password="
				+ password + ", approval=" + approval + ", users_Credentials=" + users_Credentials + ", leads=" + leads
				+ "]";
	}
	
	
	
}
