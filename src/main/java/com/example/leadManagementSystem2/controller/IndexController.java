package com.example.leadManagementSystem2.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.BusinessAssociateRepository;
import com.example.leadManagementSystem2.Repository.EmployeeDetailsRepository;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.EmployeeService;
import com.example.leadManagementSystem2.Service.LeadService;
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
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	LeadService leadService;

	@Autowired
	EmployeeDetailsRepository employeeDetailsRepository;
	
	@GetMapping("")
	public String getIndexPage() {

		return "Index";

	}

	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}

	/*
	 * private String getUsername() { // Cache the username retrieval Authentication
	 * authentication = SecurityContextHolder.getContext().getAuthentication();
	 * String username = authentication != null ? authentication.getName() : null;
	 * 
	 * return username; }
	 * 
	 * private BusinessAssociate getBusinessAssociate(String username) { return
	 * user_Credentials_Repository.getUsersCredentialsByUserName(username).
	 * getBusinessAssociate(); }
	 */

	@GetMapping("/CustomersForm/PublicEntryForm")
	public String getCustomersForm(@RequestParam(name = "partyid") Long id, Model model) {
		
		/*
		 * String username = getUsername(); if (username == null) { return
		 * "redirect:/login"; }
		 */
		 
		/*
		 * BusinessAssociate businessAssociate = getBusinessAssociate(username);
		 * model.addAttribute("businessAssociate", businessAssociate);
		 */
		model.addAttribute("leads", new Leads());
		return "BusinessAssociate/CustomersForm";
	}

	@PostMapping("/saveLeads")
	public String saveLeads(@Valid @ModelAttribute Leads leads, BindingResult result, HttpSession session, Model model) {
		/*
		 * String username = getUsername(); if (username == null) { return
		 * "redirect:/login"; } BusinessAssociate businessAssociate =
		 * getBusinessAssociate(username); model.addAttribute("businessAssociate",
		 * businessAssociate);
		 */

		if (result.hasErrors()) {
			return "BusinessAssociate/CustomersForm";
		}

		try {
			leads.setLeadStatus("New");
			//leads.setBusinessAssociate(businessAssociate);
			leadService.assignLeadsToaCaller("ROLE_CALLER", leads);
			
			Leads savedLead = leadsRepository.save(leads);
			session.setAttribute("msg", "Saved Successfully");
		} catch (Exception e) {
			session.setAttribute("msg", "Something went wrong!");
		}

		return "redirect:/CustomersForm";
	}

	@GetMapping("/addAdmin")
	public String getAddadmin() {
		
		/*
		 * List<EmployeeDetails> employeeDetails =
		 * employeeDetailsRepository.findByRole("ROLE_CALLER");
		 * 
		 * EmployeeDetails empDetails = employeeDetails.get(0);
		 * 
		 * 
		 * 
		 * List<Leads> leads = empDetails.getLeads();
		 * 
		 * String name = leads.get(1).getName(); String Email =
		 * empDetails.getUserName(); System.out.println(name);
		 * System.out.println(Email);
		 */

		return "AddAdmin";
	}

	@PostMapping("/SaveAdmin")
	public String SaveAdmin(@ModelAttribute EmployeeDetails employeeDetails) {

		//userService.saveUser(users_Credentials);
		
		employeeService.saveEmployeeDetails(employeeDetails);

		return "redirect:/addAdmin";
	}

}
