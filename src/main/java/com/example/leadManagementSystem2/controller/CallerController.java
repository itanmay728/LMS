package com.example.leadManagementSystem2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Service.DataFetchingService;


@Controller
@RequestMapping("/Caller")
public class CallerController {

	@Autowired
	LeadsRepository leadsRepository;
	@Autowired
	DataFetchingService dataFetchingService;
	
	@GetMapping("/Caller_Dashboard")
	public String getCallerDashboard() {
		return "Caller/Caller_Dashboard";
	}
	
	@GetMapping("/Caller_Dashboard/Leads")
	public String GetLeads(ModelMap model) {
		
		model.addAttribute("Leads", dataFetchingService.getAllLeadsDetails());
		return "Caller/Leads";
	}
	
	@GetMapping("/Caller_Dashboard/Leads/edit/{id}")
	public String getEditLeadsPage(@PathVariable Long id, Model model) {
		
		model.addAttribute("Leads", leadsRepository.findById(id).get());
		return "Caller/edit_Leads";
	}
	
	@PostMapping("/Caller_Dashboard/Leads/edit/{id}")
	public String updateLead(@PathVariable Long id, @ModelAttribute Leads leads ) {
		
		Leads existingLead = leadsRepository.findById(id).get();
		
		existingLead.setName(leads.getName());
		existingLead.setEmail(leads.getEmail());
		existingLead.setPhone(leads.getPhone());
		existingLead.setAddress(leads.getAddress());
		existingLead.setCourse(leads.getCourse());
		existingLead.setMessage(leads.getMessage());
		existingLead.setLeadStatus(leads.getLeadStatus());
		
		leadsRepository.save(existingLead);
		return "redirect:/Caller/Caller_Dashboard/Leads";
	}
	
	
	
}
