package com.example.leadManagementSystem2.Entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Users_Credentials {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	 */
	
	@Id
	private UUID id;
	
	private String name;
	
	@Column(unique = true)
	private String userName;
	
	private String password;
	
	private String role;

	@OneToOne
	@JoinColumn(name = "Employee_Id")
	private EmployeeDetails employeeDetails;
	
	
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
	
	
	

	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	public Users_Credentials() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users_Credentials(UUID id, String name, String userName, String password, String role,
			EmployeeDetails employeeDetails) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.employeeDetails = employeeDetails;
	}

	@Override
	public String toString() {
		return "Users_Credentials [id=" + id + ", name=" + name + ", userName=" + userName + ", password=" + password
				+ ", role=" + role + ", employeeDetails=" + employeeDetails + "]";
	}

	
	
	
	
	
	
}
