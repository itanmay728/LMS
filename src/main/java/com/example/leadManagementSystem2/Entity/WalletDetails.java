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

	@NotBlank(message = "transaction id cannot be empty!!")
	private String transaction_id;
	
	private Long amount;
	
	private String rejection_reason;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "businessAssociate_id")
	private BusinessAssociate businessAssociate;
	
}
