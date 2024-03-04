package com.example.leadManagementSystem2.Entity;

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
	
	private String leadStatus;
	
	@ManyToOne
	@JoinColumn(name = "businessAssociate_id")
	private BusinessAssociate businessAssociate;

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

	public Leads() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Leads(Long id, String name, String email, String phone, String address, String course, String message,
			String leadStatus, BusinessAssociate businessAssociate) {
		super();
		Id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.course = course;
		this.message = message;
		this.leadStatus = leadStatus;
		this.businessAssociate = businessAssociate;
	}

	@Override
	public String toString() {
		return "Leads [Id=" + Id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", course=" + course + ", message=" + message + ", leadStatus=" + leadStatus + ", businessAssociate="
				+ businessAssociate + "]";
	}
	
	
	
}
