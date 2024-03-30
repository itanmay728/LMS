package com.example.leadManagementSystem2.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String courseName;
	private Long  commission;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Long getCommission() {
		return commission;
	}

	public void setCommission(Long commission) {
		this.commission = commission;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(Long id, String courseName, Long commission) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.commission = commission;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", commission=" + commission + "]";
	}

	
	
}
