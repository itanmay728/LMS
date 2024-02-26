package com.example.leadManagementSystem2.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Leads {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String name;	
	
	private String email;
	
	private String phone;
	
	private String address;
	
	private String course;
	
	@Column(length = 100)
	private String message;
	
	private String leadStatus;
	
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLeadStatus() {
		return leadStatus;
	}
	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}
	
	
	public Leads(Long id, String name, String email, String phone, String address, String course, String message,
			String leadStatus) {
		super();
		Id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.course = course;
		this.message = message;
		this.leadStatus = leadStatus;
	}
	
	public Leads() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Leads [Id=" + Id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", course=" + course + ", message=" + message + ", leadStatus=" + leadStatus + "]";
	}
	
	
	


	
	
	
	
}
