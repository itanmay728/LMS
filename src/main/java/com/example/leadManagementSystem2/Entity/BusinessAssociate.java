package com.example.leadManagementSystem2.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class BusinessAssociate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "name cannot be empty!!")
	private String name;

	@NotBlank(message = "Email cannot be empty!!")
	@Email(message = "Invalid email format!")
	@Column(unique = true)
	private String userName;

	@NotBlank(message = "Phone cannot be empty!!")
	private String phone;

	@NotBlank(message = "Address cannot be empty!!")
	private String address;

	@NotBlank(message = "Business name cannot be empty!!")
	private String businessName;

	@NotBlank(message = "Pan number cannot be empty!!")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number")
	private String panNumber;

	@NotBlank(message = "Aadhaar number cannot be empty!!")
	@Pattern(regexp = "[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}", message = "Invalid Aadhaar number")
	private String aadhaarNumber;

	@NotBlank(message = "Account number cannot be empty!!")
	private String accountNumber;

	@NotBlank(message = "Account holder name cannot be empty!!")
	private String accountHolderName;

	@NotBlank(message = "IFSC code cannot be empty!!")
	@Pattern(regexp = "[A-Z]{4}0[A-Z0-9]{6}", message = "Invalid IFSC Code")
	private String ifscCode;

	@NotBlank(message = "Branch Address cannot be empty!!")
	private String branchAddress;

	// @NotBlank(message="name cannot be empty!!")
	private String password;

	private boolean approval;
	
	private Long wallet = 0L;

	@OneToOne(mappedBy = "businessAssociate", cascade = CascadeType.ALL)
	private Users_Credentials users_Credentials;

	@OneToMany(mappedBy = "businessAssociate")
	private List<Leads> leads;

	@ManyToOne
	@JoinColumn(name = "fieldManager_id")
	private EmployeeDetails fieldManager;
	
	@OneToMany(mappedBy = "businessAssociate")
	private List<WalletDetails> walletDetails;

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

	public EmployeeDetails getFieldManager() {
		return fieldManager;
	}

	public void setFieldManager(EmployeeDetails fieldManager) {
		this.fieldManager = fieldManager;
	}

	public Long getWallet() {
		return wallet;
	}

	public void setWallet(Long wallet) {
		this.wallet = wallet;
	}
	

	public List<WalletDetails> getWalletDetails() {
		return walletDetails;
	}

	public void setWalletDetails(List<WalletDetails> walletDetails) {
		this.walletDetails = walletDetails;
	}

	public BusinessAssociate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessAssociate(Long id, @NotBlank(message = "name cannot be empty!!") String name,
			@NotBlank(message = "Email cannot be empty!!") @Email(message = "Invalid email format!") String userName,
			@NotBlank(message = "Phone cannot be empty!!") String phone,
			@NotBlank(message = "Address cannot be empty!!") String address,
			@NotBlank(message = "Business name cannot be empty!!") String businessName,
			@NotBlank(message = "Pan number cannot be empty!!") @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number") String panNumber,
			@NotBlank(message = "Aadhaar number cannot be empty!!") @Pattern(regexp = "[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}", message = "Invalid Aadhaar number") String aadhaarNumber,
			@NotBlank(message = "Account number cannot be empty!!") String accountNumber,
			@NotBlank(message = "Account holder name cannot be empty!!") String accountHolderName,
			@NotBlank(message = "IFSC code cannot be empty!!") @Pattern(regexp = "[A-Z]{4}0[A-Z0-9]{6}", message = "Invalid IFSC Code") String ifscCode,
			@NotBlank(message = "Branch Address cannot be empty!!") String branchAddress, String password,
			boolean approval, Long wallet, Users_Credentials users_Credentials, List<Leads> leads,
			EmployeeDetails fieldManager, List<WalletDetails> walletDetails) {
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
		this.wallet = wallet;
		this.users_Credentials = users_Credentials;
		this.leads = leads;
		this.fieldManager = fieldManager;
		this.walletDetails = walletDetails;
	}

	@Override
	public String toString() {
		return "BusinessAssociate [id=" + id + ", name=" + name + ", userName=" + userName + ", phone=" + phone
				+ ", address=" + address + ", businessName=" + businessName + ", panNumber=" + panNumber
				+ ", aadhaarNumber=" + aadhaarNumber + ", accountNumber=" + accountNumber + ", accountHolderName="
				+ accountHolderName + ", ifscCode=" + ifscCode + ", branchAddress=" + branchAddress + ", password="
				+ password + ", approval=" + approval + ", wallet=" + wallet + ", users_Credentials="
				+ users_Credentials + ", leads=" + leads + ", fieldManager=" + fieldManager + ", walletDetails="
				+ walletDetails + "]";
	}

	
	

}
