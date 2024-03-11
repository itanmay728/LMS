package com.example.leadManagementSystem2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.LeadService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/Caller")
public class CallerController {

	@Autowired
	private LeadsRepository leadsRepository;
	
	@Autowired
	private LeadService leadService;
	
	@GetMapping("/Caller_Dashboard")
	public String getCallerDashboard(HttpSession session) {
		
		String username = (String) session.getAttribute("username");
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
			
		return "Caller/FollowUpLeads";
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
		
		leadsRepository.save(existingLead);
		return "redirect:/Caller/Caller_Dashboard/Leads/edit/{id}";
	}
	
	/* Lead End */
	
	
}
