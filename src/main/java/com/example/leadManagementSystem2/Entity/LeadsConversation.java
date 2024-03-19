package com.example.leadManagementSystem2.Entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LeadsConversation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@CreationTimestamp
	private Date date;
	
	@Column(length = 1000)
	private String message;
	private String nextFollowUpDate;
	private String nextFollowUpTime;
	
	@ManyToOne
	@JoinColumn(name = "lead_id")
	private Leads leads;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNextFollowUpDate() {
		return nextFollowUpDate;
	}

	public void setNextFollowUpDate(String nextFollowUpDate) {
		this.nextFollowUpDate = nextFollowUpDate;
	}

	public String getNextFollowUpTime() {
		return nextFollowUpTime;
	}

	public void setNextFollowUpTime(String nextFollowUpTime) {
		this.nextFollowUpTime = nextFollowUpTime;
	}

	public Leads getLeads() {
		return leads;
	}

	public void setLeads(Leads leads) {
		this.leads = leads;
	}

	public LeadsConversation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeadsConversation(Long id, Date date, String message, String nextFollowUpDate, String nextFollowUpTime,
			Leads leads) {
		super();
		Id = id;
		this.date = date;
		this.message = message;
		this.nextFollowUpDate = nextFollowUpDate;
		this.nextFollowUpTime = nextFollowUpTime;
		this.leads = leads;
	}

	@Override
	public String toString() {
		return "LeadsConversation [Id=" + Id + ", date=" + date + ", message=" + message + ", nextFollowUpDate="
				+ nextFollowUpDate + ", nextFollowUpTime=" + nextFollowUpTime + ", leads=" + leads + "]";
	}
	
	
	
	
}
