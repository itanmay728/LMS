package com.example.leadManagementSystem2.Entity;

import jakarta.validation.constraints.NotBlank;

public class FormData {

	@NotBlank(message="name cannot be empty!!")
	private String name;
	
	private String email;
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
	@Override
	public String toString() {
		return "FormData [name=" + name + ", email=" + email + "]";
	}
	
	
}
