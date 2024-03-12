package com.example.leadManagementSystem2.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Leads {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotBlank(message="name cannot be empty!!")
	private String name;	
	
	@NotBlank(message="Email cannot be empty!!")
	@Email(message="Invalid email format!")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message="Phone cannot be empty!!")
	private String phone;

	@NotBlank(message="Address cannot be empty!!")
	private String address;
	
	private String course;
	
	@Column(length = 100)
	private String message;
	
	private List<String> messageOfCaller;
	
	private String leadStatus;
	
	@ManyToOne
	@JoinColumn(name = "businessAssociate_id")
	@JsonIgnore
	private BusinessAssociate businessAssociate;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	@JsonIgnore
	private EmployeeDetails employeeDetails;

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

	public BusinessAssociate getBusinessAssociate() {
		return businessAssociate;
	}

	public void setBusinessAssociate(BusinessAssociate businessAssociate) {
		this.businessAssociate = businessAssociate;
	}

	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	public List<String> getMessageOfCaller() {
		return messageOfCaller;
	}

	public void setMessageOfCaller(List<String> messageOfCaller) {
		this.messageOfCaller = messageOfCaller;
	}

	public Leads() {
		super();
		// TODO Auto-generated constructor stub = messageOfCaller
	}

	public Leads(Long id, @NotBlank(message = "name cannot be empty!!") String name,
			@NotBlank(message = "Email cannot be empty!!") @Email(message = "Invalid email format!") String email,
			@NotBlank(message = "Phone cannot be empty!!") String phone,
			@NotBlank(message = "Address cannot be empty!!") String address, String course, String message,
			List<String> messageOfCaller, String leadStatus, BusinessAssociate businessAssociate,
			EmployeeDetails employeeDetails) {
		super();
		Id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.course = course;
		this.message = message;
		this.messageOfCaller = messageOfCaller;
		this.leadStatus = leadStatus;
		this.businessAssociate = businessAssociate;
		this.employeeDetails = employeeDetails;
	}

	@Override
	public String toString() {
		return "Leads [Id=" + Id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", course=" + course + ", message=" + message + ", messageOfCaller=" + messageOfCaller
				+ ", leadStatus=" + leadStatus + ", businessAssociate=" + businessAssociate + ", employeeDetails="
				+ employeeDetails + "]";
	}

	

	
	
	
	
	
}
