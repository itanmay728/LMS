package com.example.leadManagementSystem2.Service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Entity.LeadsConversation;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.BusinessAssociateRepository;
import com.example.leadManagementSystem2.Repository.EmployeeDetailsRepository;
import com.example.leadManagementSystem2.Repository.LeadsConversationRepository;
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

	@Autowired
	private LeadsConversationRepository leadsConversationRepository;

	@Override
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");

	}

	// using this in Admin controller
	@Override
	public List<Leads> getAllLeadsDetails() {

		return leadsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public List<Leads> getLeadsDetailsByStatus(String status) {

		return leadsRepository.findByLeadStatus(status);
	}

	// using this method in index controller
	// Assigning lead to a caller
	@Override
	public void assignLeadsToaCaller(String role, Leads leads) {

		List<EmployeeDetails> employeeDetails = employeeDetailsRepository.findByRole(role);

		/*
		 * Random myRandomizer = new Random();
		 * 
		 * EmployeeDetails emDetails =
		 * employeeDetails.get(myRandomizer.nextInt(employeeDetails.size()));
		 * leads.setEmployeeDetails(emDetails);
		 */

		List<Long> numberOfLeads = new ArrayList<>();

		for (int i = 0; i < employeeDetails.size(); i++) {

			numberOfLeads.add((long) employeeDetails.get(i).getLeads().size());
		}

		Long smallestNumberOfLeads = Collections.min(numberOfLeads);
		for (int i = 0; i < employeeDetails.size(); i++) {

			if (smallestNumberOfLeads == (long) employeeDetails.get(i).getLeads().size()) {
				leads.setEmployeeDetails(employeeDetails.get(i));
				break;
			}

		}

	}

	@Override
	public List<Leads> getLeadsDetailsOfAParticularCaller(String username) {

		Users_Credentials users_Credentials = user_Credentials_Repository.getUsersCredentialsByUserName(username);

		EmployeeDetails employeeDetails = users_Credentials.getEmployeeDetails();

		List<Leads> leads = employeeDetails.getLeads();

		return leads;
	}

	@Override
	public List<Leads> getLeadsDetailsByStatusOfAParticularCaller(String username, String status) {

		List<Leads> leads = getLeadsDetailsOfAParticularCaller(username);

		List<Leads> leadsByStatus = new ArrayList<>();

		for (Leads L : leads) {

			if (L.getLeadStatus().equals(status)) {

				leadsByStatus.add(L);
			}

		}

		return leadsByStatus;
	}

	// used in business associate controller to show count of all leads and success
	// leads of particular BA

	@Override
	public int getLeadsCountOfBusinessAssociate(String username) {

		Users_Credentials users_Credentials = user_Credentials_Repository.getUsersCredentialsByUserName(username);

		BusinessAssociate BA = users_Credentials.getBusinessAssociate();

		List<Leads> leads = BA.getLeads();

		return leads.size();
	}

	@Override
	public List<Leads> getLeadsOfBusinessAssociate(String username) {

		Users_Credentials users_Credentials = user_Credentials_Repository.getUsersCredentialsByUserName(username);

		BusinessAssociate BA = users_Credentials.getBusinessAssociate();

		List<Leads> leads = BA.getLeads();

		return leads;
	}

	@Override
	public int getLeadsCountByStatusOfBusinessAssociate(String username, String status) {

		// Get the leads directly associated with the business associate
		List<Leads> leads = getLeadsOfBusinessAssociate(username);

		int leadsByStatusCount = 0;

		for (Leads L : leads) {

			if (L.getLeadStatus().equals(status)) {
				leadsByStatusCount++;
			}
		}

		// Return the count of leads with the specified status
		return leadsByStatusCount;
	}

	@Override
	public void saveLeadsConversation(Long id, LeadsConversation leadsConversation) {

		Leads lead = leadsRepository.findById(id).get();
		leadsConversation.setLeads(lead);

		leadsConversationRepository.save(leadsConversation);

	}

	@Override
	public void transferLeads(Long id, EmployeeDetails employeeDetails) {

		// caller whose leads will be transferred
		EmployeeDetails employeeDetails1 = employeeDetailsRepository.findById(employeeDetails.getId()).get();

		// id comes from entering in the box, caller to whom the lead will be
		// transferred
		EmployeeDetails employeeDetails2 = employeeDetailsRepository.findById(id).get();

		List<Leads> leads = employeeDetails2.getLeads();

		for (Leads leads2 : leads) {
			leads2.setEmployeeDetails(employeeDetails1);
			leadsRepository.save(leads2);
		}

	}

	@Override
	public void transferSelectedLeads(List<Long> leadIds, Long newCallerId) {
		List<Leads> leadsToTransfer = leadsRepository.findAllById(leadIds);
		for (Leads lead : leadsToTransfer) {
			EmployeeDetails emp = employeeDetailsRepository.getById(newCallerId);
			lead.setEmployeeDetails(emp); // Update caller ID for each lead
			leadsRepository.save(lead);
		}
	}
}
