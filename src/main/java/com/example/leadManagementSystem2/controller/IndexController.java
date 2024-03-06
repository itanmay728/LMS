package com.example.leadManagementSystem2.controller;

import java.security.Principal;

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

	private String getUsername() {
		// Cache the username retrieval
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication != null ? authentication.getName() : null;
		return username;
	}

	private BusinessAssociate getBusinessAssociate(String username) {
		return user_Credentials_Repository.getUsersCredentialsByUserName(username).getBusinessAssociate();
	}

	@GetMapping("/CustomersForm")
	public String getCustomersForm(Model model) {
		String username = getUsername();
		if (username == null) {
			return "redirect:/login";
		}
		BusinessAssociate businessAssociate = getBusinessAssociate(username);
		model.addAttribute("businessAssociate", businessAssociate);
		model.addAttribute("leads", new Leads());
		return "BusinessAssociate/CustomersForm";
	}

	@PostMapping("/saveLeads")
	public String saveLeads(@Valid @ModelAttribute Leads leads, BindingResult result, HttpSession session,
			Model model) {
		String username = getUsername();
		if (username == null) {
			return "redirect:/login";
		}
		BusinessAssociate businessAssociate = getBusinessAssociate(username);
		model.addAttribute("businessAssociate", businessAssociate);

		if (result.hasErrors()) {
			return "BusinessAssociate/CustomersForm";
		}

		try {
			leads.setLeadStatus("New");
			Leads savedLead = leadsRepository.save(leads);
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
