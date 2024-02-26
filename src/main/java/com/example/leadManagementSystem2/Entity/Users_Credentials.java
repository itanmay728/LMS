package com.example.leadManagementSystem2.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users_Credentials {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String Name;
	
	@Column(unique = true)
	private String userName;
	
	private String password;
	
	private String role;
	
	private String Address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Users_Credentials(Long id, String name, String userName, String password, String role, String address) {
		super();
		this.id = id;
		Name = name;
		this.userName = userName;
		this.password = password;
		this.role = role;
		Address = address;
	}

	public Users_Credentials() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Users_Credentials [id=" + id + ", Name=" + Name + ", userName=" + userName + ", password=" + password
				+ ", role=" + role + ", Address=" + Address + "]";
	}
	
	
	
	
}
