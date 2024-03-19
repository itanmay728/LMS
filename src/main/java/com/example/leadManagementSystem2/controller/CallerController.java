package com.example.leadManagementSystem2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Entity.LeadsConversation;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.BusinessAssociateService;
import com.example.leadManagementSystem2.Service.LeadService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/Caller")
public class CallerController {

	@Autowired
	private LeadsRepository leadsRepository;
	
	@Autowired
	private LeadService leadService;
	
	@Autowired
	private User_Credentials_Repository user_Credentials_Repository;
	
	@Autowired
	private BusinessAssociateService businessAssociateService;
	
	@GetMapping("/Caller_Dashboard")
	public String getCallerDashboard(HttpSession session) {
		
		String username = (String) session.getAttribute("username");
		
		if (username == null) {
			// Handle the case where username is not found in the session
			return "redirect:/login"; // Redirect to login page or handle appropriately
		}

		Users_Credentials user = user_Credentials_Repository.getUsersCredentialsByUserName(username);

		EmployeeDetails employeeDetails = user.getEmployeeDetails();
		session.setAttribute("employeeDetails", employeeDetails);
		
		System.out.println(username);
		return "Caller/Caller_Dashboard";
	}
	
	/* Lead Start */
	
	//Fresh Leads
	@GetMapping("/Caller_Dashboard/freshleads")
	public String getFreshLeads(ModelMap model, HttpSession session) {
		
		String username = (String) session.getAttribute("username");
		
		List<Leads> leads =  leadService.getLeadsDetailsByStatusOfAParticularCaller(username, "New");
		
		model.addAttribute("Leads", leads);
		
		return "Caller/FreshLeads";
	}
	
	//Follow Up Leads
	@GetMapping("/Caller_Dashboard/followupleads")
	public String getFollowUpLeads(ModelMap model, HttpSession session) {
		
		String username = (String) session.getAttribute("username");
		
		List<Leads> leads =  leadService.getLeadsDetailsByStatusOfAParticularCaller(username, "Follow up");
		
		model.addAttribute("Leads", leads); 
		
		return "Caller/FollowUpLeads";
	}
	
	//Success Leads
	@GetMapping("/Caller_Dashboard/Successleads")
	public String getSuccessLeads(ModelMap model, HttpSession session) {
			
		String username = (String) session.getAttribute("username");
			
		List<Leads> leads =  leadService.getLeadsDetailsByStatusOfAParticularCaller(username, "Success");
			
		model.addAttribute("Leads", leads);
			
		return "Caller/SuccessLeads";
		}
	
	
	//All Leads
	@GetMapping("/Caller_Dashboard/Leads")
	public String GetLeads(ModelMap model, HttpSession session) {
		
		String username = (String) session.getAttribute("username");
		
		List<Leads> leads =  leadService.getLeadsDetailsOfAParticularCaller(username);
		
		//model.addAttribute("Leads", dataFetchingService.getAllLeadsDetails());
		model.addAttribute("Leads", leads);
		return "Caller/Leads";
	}
	
	@GetMapping("/Caller_Dashboard/Leads/edit/{id}")
	public String getEditLeadsPage(@PathVariable Long id, Model model) {
		
		model.addAttribute("Leads", leadsRepository.findById(id).get());
		return "Caller/Edit_Leads";
	}
	
	@PostMapping("/Caller_Dashboard/Leads/edit/{id}")
	public String updateLead(@PathVariable Long id, @ModelAttribute Leads leads ) {
		
		Leads existingLead = leadsRepository.findById(id).get();
		
		//existingLead.setName(leads.getName());
		//existingLead.setEmail(leads.getEmail());
		//existingLead.setPhone(leads.getPhone());
		//existingLead.setAddress(leads.getAddress());
		//existingLead.setCourse(leads.getCourse());
		//existingLead.setMessage(leads.getMessage());
		
		
		//existingLead.setMessageOfCaller(leads.getMessageOfCaller());
		existingLead.setLeadStatus(leads.getLeadStatus());
		businessAssociateService.walletUpdate(existingLead);
		
		leadsRepository.save(existingLead);
		return "redirect:/Caller/Caller_Dashboard/Leads/edit/{id}";
	}
	
	/* Lead End */
	
	/* profile */
	
	@GetMapping("/Caller_Dashboard/profile")
	public String getProfile() {
		
		return "Caller/CallerProfile";
	}
	
	
	@GetMapping("/Caller_Dashboard/Leads/{query}")
	@ResponseBody
	public ResponseEntity<?> search(@PathVariable("query") String query , HttpSession session) {

		System.out.println(query);
		
		String username = (String) session.getAttribute("username");
		Users_Credentials users_Credentials = user_Credentials_Repository.getUsersCredentialsByUserName(username);
		EmployeeDetails employeeDetails = users_Credentials.getEmployeeDetails();

		List<Leads> leads = this.leadsRepository.findByEmailContainingAndEmployeeDetails(query, employeeDetails);

		return ResponseEntity.ok(leads);
	}
	
	@PostMapping("/Caller_Dashboard/saveconversation/{id}")
	public String saveConversationOfLead(@PathVariable Long id, @ModelAttribute LeadsConversation leadsConversation) {
		
		leadService.saveLeadsConversation(id, leadsConversation);
		
		return "redirect:/Caller/Caller_Dashboard/Leads/edit/{id}";
	}
	
}
