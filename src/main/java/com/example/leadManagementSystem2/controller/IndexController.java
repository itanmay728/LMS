package com.example.leadManagementSystem2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.Course;
import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Repository.BusinessAssociateRepository;
import com.example.leadManagementSystem2.Repository.CourseRepository;
import com.example.leadManagementSystem2.Repository.EmployeeDetailsRepository;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.BusinessAssociateService;
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
	
	@Autowired
	BusinessAssociateService businessAssociateService;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("")
	public String getIndexPage() {

		return "Index";

	}

	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}


	
	@GetMapping("/CustomersForm/PublicEntryForm")
	public String getCustomersForm(@RequestParam(name = "partyid") Long id, Model model) {
		
		String businessName = businessAssociateService.uniqueForm(id);
		model.addAttribute("businessName", businessName);
		model.addAttribute("courses", courseRepository.findAll());
		model.addAttribute("leads", new Leads());
		
		return "BusinessAssociate/CustomersForm";
	}
	
	//get mapping for getting qr code image page
	
//	public String getCustomersFormQRCode(@RequestParam(name = "partyid") Long id, Model model) {
	
	public String getCustomersFormQRCode() {
		
//		String businessName = businessAssociateService.uniqueForm(id);
//		model.addAttribute("businessName", businessName);
//		model.addAttribute("courses", courseRepository.findAll());
//		model.addAttribute("leads", new Leads());
		
		return "BusinessAssociate/QRCodePage";
	}

	@PostMapping("/CustomersForm/PublicEntryForm")
	public String saveLeads(@RequestParam(name = "partyid") Long id, @Valid @ModelAttribute Leads leads, BindingResult result, HttpSession session, Model model) {
		
		String businessName = businessAssociateService.uniqueForm(id);
		model.addAttribute("businessName", businessName);
		System.out.println(id);

		if (result.hasErrors()) {
			return "BusinessAssociate/CustomersForm";
		}

		try {
			BusinessAssociate businessAssociate = businessAssociateRepository.findById(id).get();
			leads.setLeadStatus("New");
			leads.setBusinessAssociate(businessAssociate);
			leadService.assignLeadsToaCaller("ROLE_CALLER", leads);
			leadsRepository.save(leads);
			session.setAttribute("msg", "Saved Successfully");
		} catch (Exception e) {
			session.setAttribute("msg", "Something went wrong!");
			
		}

		return "BusinessAssociate/CustomersForm";
	}

	@GetMapping("/addAdmin")
	public String getAddadmin() {

		return "AddAdmin";
	}

	@PostMapping("/SaveAdmin")
	public String SaveAdmin(@ModelAttribute EmployeeDetails employeeDetails) {

		//userService.saveUser(users_Credentials);
		
		employeeService.saveEmployeeDetails(employeeDetails);

		return "redirect:/addAdmin";
	}

}
