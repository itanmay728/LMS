package com.example.leadManagementSystem2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.BusinessAssociateRepository;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
	
	@GetMapping("")
	public String getIndexPage() {
		
		return "Index";
		
	}

	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	
	@GetMapping("/CustomersForm")
	public String getCustomersForm(Model model, HttpSession session) {
		String Username = (String) session.getAttribute("username");
		System.out.println(Username);
		
		if (Username == null) {
	        // Handle the case where username is not found in the session
	        return "redirect:/login"; // Redirect to login page or handle appropriately
	    }

	    Users_Credentials user = user_Credentials_Repository.getUsersCredentialsByUserName(Username);
	    
	    BusinessAssociate businessAssociate = user.getBusinessAssociate();
		 session.setAttribute("businessAssociate", businessAssociate);
		
		//BusinessAssociate businessAssociate =   businessAssociateRepository.getBusinessAssociateByUserName(Username);
		//System.out.println(businessAssociate);
		//model.addAttribute("businessAssociate", businessAssociate);
		model.addAttribute("leads", new Leads());
		return "BusinessAssociate/CustomersForm";
	}
	
	@PostMapping("/saveLeads")
	public String saveLeads(@Valid @ModelAttribute Leads leads, BindingResult result, HttpSession session) {
		
		//System.out.println(leads);
		//leads.setLeadStatus("New");
		//leadsRepository.save(leads);
		
		if (result.hasErrors()) {
			System.out.println(result);
			return "BusinessAssociate/CustomersForm";
		}

		System.out.println(leads);

		try {
			leads.setLeadStatus("New");
			Leads lead = leadsRepository.save(leads);
			session.setAttribute("msg", "Saved Successfully");
		} catch (Exception e) {
			session.setAttribute("msg", "Something went wrong!");
		}
		
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
