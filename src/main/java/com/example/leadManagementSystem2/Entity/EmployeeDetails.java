package com.example.leadManagementSystem2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class EmployeeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "name cannot be empty!!")
	private String name;

	@NotBlank(message = "Email cannot be empty!!")
	@Email(message = "Invalid email format!")
	@Column(unique = true)
	private String userName;

	private String image;

	@NotBlank(message = "Address cannot be empty!!")
	private String address;

	@NotBlank(message = "Phone cannot be empty!!")
	private String phone;

	@NotBlank(message = "Pan number cannot be empty!!")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number")
	private String panNumber;

	@NotBlank(message = "Aadhaar number cannot be empty!!")
	@Pattern(regexp = "[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}", message = "Invalid Aadhaar number")
	private String aadhaar;

	@NotBlank(message = "Account holder name cannot be empty!!")
	private String accountHolderName;

	@NotBlank(message = "Account number cannot be empty!!")
	private String accountNumber;

	@NotBlank(message = "IFSC code cannot be empty!!")
	@Pattern(regexp = "[A-Z]{4}0[A-Z0-9]{6}", message = "Invalid IFSC Code")
	private String ifscCode;

	@NotBlank(message = "Branch Address cannot be empty!!")
	private String branchAddress;

	@NotBlank(message = "Password cannot be empty!!")
	private String password;

	private String role;

	private boolean approve;

	@OneToOne(mappedBy = "employeeDetails",cascade = CascadeType.ALL)
	@JsonIgnore
	private Users_Credentials users_Credentials;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public EmployeeDetails(Long id, String name, String userName, String image, String address, String phone,
			String panNumber, String aadhaar, String accountHolderName, String accountNumber, String ifscCode,
			String branchAddress, String password, String role, boolean approve, Users_Credentials users_Credentials) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.image = image;
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
		return "EmployeeDetails [id=" + id + ", name=" + name + ", userName=" + userName + ", image=" + image
				+ ", address=" + address + ", phone=" + phone + ", panNumber=" + panNumber + ", aadhaar=" + aadhaar
				+ ", accountHolderName=" + accountHolderName + ", accountNumber=" + accountNumber + ", ifscCode="
				+ ifscCode + ", branchAddress=" + branchAddress + ", password=" + password + ", role=" + role
				+ ", approve=" + approve + ", users_Credentials=" + users_Credentials + "]";
	}

}
