package com.example.leadManagementSystem2.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class WalletDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private String transaction_id;
	
	private Long amount = 0L;
	
	private String rejection_reason;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "businessAssociate_id")
	private BusinessAssociate businessAssociate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getRejection_reason() {
		return rejection_reason;
	}

	public void setRejection_reason(String rejection_reason) {
		this.rejection_reason = rejection_reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BusinessAssociate getBusinessAssociate() {
		return businessAssociate;
	}

	public void setBusinessAssociate(BusinessAssociate businessAssociate) {
		this.businessAssociate = businessAssociate;
	}

	public WalletDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WalletDetails(Long id, @NotBlank(message = "transaction id cannot be empty!!") String transaction_id,
			Long amount, String rejection_reason, String status, BusinessAssociate businessAssociate) {
		super();
		this.id = id;
		this.transaction_id = transaction_id;
		this.amount = amount;
		this.rejection_reason = rejection_reason;
		this.status = status;
		this.businessAssociate = businessAssociate;
	}

	@Override
	public String toString() {
		return "WalletDetails [id=" + id + ", transaction_id=" + transaction_id + ", amount=" + amount
				+ ", rejection_reason=" + rejection_reason + ", status=" + status + ", businessAssociate="
				+ businessAssociate + "]";
	}
	
	
}
