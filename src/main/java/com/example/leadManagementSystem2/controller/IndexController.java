package com.example.leadManagementSystem2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.BusinessAssociateHistoryRepo;
import com.example.leadManagementSystem2.Repository.BusinessAssociateRepository;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {

	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	LeadsRepository leadsRepository;
	
	@Autowired
	User_Credentials_Repository user_Credentials_Repository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BusinessAssociateRepository businessAssociateRepository;
	
	@GetMapping()
	public String getIndexPage() {
		
		return "Index";
		
	}
	

	
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	
	@GetMapping("/CustomersForm")
	public String getCustomersForm(Model model, Principal principal) {
		String Username = principal.getName();
		System.out.println(Username);
		
		BusinessAssociate businessAssociate =   businessAssociateRepository.getBusinessAssociateByUserName(Username);
		
		model.addAttribute("businessAssociate", businessAssociate);
		return "BusinessAssociate/CustomersForm";
	}
	
	@PostMapping("/saveLeads")
	public String saveLeads(@ModelAttribute Leads leads) {
		
		System.out.println(leads);
		leads.setLeadStatus("New");
		leadsRepository.save(leads);
		
		return "redirect:/CustomersForm";
	}
	
	
	  
	
	  
	@GetMapping("/addAdmin")
	public String getAddadmin() {
		
		return "AddAdmin";
	}
	
	@PostMapping("/SaveAdmin")
	public String SaveAdmin(@ModelAttribute Users_Credentials users_Credentials) {
		
		userService.saveUser(users_Credentials);
		
		return "redirect:/addAdmin";
	}
	
	
}
