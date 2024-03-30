package com.example.leadManagementSystem2.Service;

import java.util.List;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Entity.LeadsConversation;

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
	
	public void saveLeadsConversation(Long id, LeadsConversation leadsConversation);
	
	public void transferLeads(Long id, EmployeeDetails employeeDetails);

	public void transferSelectedLeads(List<Long> leadIds, Long newCallerId);

	
}
