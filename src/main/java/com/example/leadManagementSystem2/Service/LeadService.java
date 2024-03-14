package com.example.leadManagementSystem2.Service;

import java.util.List;

import com.example.leadManagementSystem2.Entity.Leads;

public interface LeadService {

	public void removeSessionMessage();

	List<Leads> getAllLeadsDetails();
	
	List<Leads> getLeadsDetailsByStatus(String status);
	
	public void assignLeadsToaCaller(String role, Leads leads);
	
	List<Leads> getLeadsDetailsOfAParticularCaller(String username);
	
	List<Leads> getLeadsDetailsByStatusOfAParticularCaller(String username , String status);

	int getLeadsCountOfBusinessAssociate(String username);

	int getLeadsCountByStatusOfBusinessAssociate(String username, String status);

	List<Leads> getLeadsOfBusinessAssociate(String username);
	
}
