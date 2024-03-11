package com.example.leadManagementSystem2.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.BusinessAssociateRepository;
import com.example.leadManagementSystem2.Repository.EmployeeDetailsRepository;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.LeadService;

import jakarta.servlet.http.HttpSession;

@Service
public class LeadServiceImpl implements LeadService {
	
	@Autowired
	LeadsRepository leadsRepository;
	
	@Autowired
	BusinessAssociateRepository businessAssociateRepository;
	
	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;
	
	@Autowired
	private User_Credentials_Repository user_Credentials_Repository;
	
	@Override
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();

		session.removeAttribute("msg");

	}

	//using this in Admin controller
	@Override
	public List<Leads> getAllLeadsDetails() {
		
		return leadsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public List<Leads> getLeadsDetailsByStatus(String status) {
		
		return leadsRepository.findByLeadStatus(status);
	}
	
	//using this method in index controller
	//Assigning lead to a caller
	@Override
	public void assignLeadsToaCaller(String role, Leads leads) {
		
		List<EmployeeDetails> employeeDetails =  employeeDetailsRepository.findByRole(role);
		
		Random myRandomizer = new Random();
		
		EmployeeDetails emDetails = employeeDetails.get(myRandomizer.nextInt(employeeDetails.size()));
		
		leads.setEmployeeDetails(emDetails);
		
	}

	@Override
	public List<Leads> getLeadsDetailsOfAParticularCaller(String username) {
		
		Users_Credentials users_Credentials = user_Credentials_Repository.getUsersCredentialsByUserName(username);
		
		EmployeeDetails employeeDetails =  users_Credentials.getEmployeeDetails();
		
		List<Leads> leads =  employeeDetails.getLeads();
		
		return leads;
	}
	
	@Override
	public List<Leads> getLeadsDetailsByStatusOfAParticularCaller(String username, String status) {
		
		List<Leads> leads =  getLeadsDetailsOfAParticularCaller(username);
		
		List<Leads> leadsByStatus = new ArrayList<>();
		
		for(Leads L : leads) {
			
		if (L.getLeadStatus().equals(status)) {
			
			leadsByStatus.add(L);
		}	
			
			
		}
		
		
		return leadsByStatus;
	}

	

	

}
