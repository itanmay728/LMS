package com.example.leadManagementSystem2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.BusinessAssociateHistory;
import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.BusinessAssociateHistoryRepo;
import com.example.leadManagementSystem2.Repository.BusinessAssociateRepository;
import com.example.leadManagementSystem2.Repository.EmployeeDetailsRepository;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.BusinessAssociateService;
import com.example.leadManagementSystem2.Service.DataFetchingService;
import com.example.leadManagementSystem2.Service.EmployeeService;
import com.example.leadManagementSystem2.Service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	User_Credentials_Repository user_Credentials_Repository;
	@Autowired
	LeadsRepository leadsRepository;

	@Autowired
	private UserService userService;

	@Autowired
	DataFetchingService dataFetchingService;

	@Autowired
	BusinessAssociateRepository businessAssociateRepository;

	@Autowired
	BusinessAssociateHistoryRepo businessAssociateHistoryRepo;

	@Autowired
	BusinessAssociateService businessAssociateService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;

	@GetMapping("/admin_Dashboard")
	public String getAdminDashboard(Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		System.out.println(username);
		if (username == null) {
			// Handle the case where username is not found in the session
			return "redirect:/login"; // Redirect to login page or handle appropriately
		}

		Users_Credentials user = user_Credentials_Repository.getUsersCredentialsByUserName(username);

		EmployeeDetails employeeDetails = user.getEmployeeDetails();
		session.setAttribute("employeeDetails", employeeDetails);

		return "Admin/Admin_Dashboard";
	}

	@GetMapping("/admin_Dashboard/registration")
	public String getAccountRegistrationPage(Model model, HttpSession session) {
		model.addAttribute("employeeDetails", new EmployeeDetails());

		String username = (String) session.getAttribute("username");
		session.setAttribute("name", username);

		return "Admin/AddEmployeeForm";
	}

	@PostMapping("/saveUser")
	public String CreateAccount(@Valid @ModelAttribute EmployeeDetails employeeDetails, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			System.out.println(result);
			return "Admin/AddEmployeeForm";
		}

		System.out.println(employeeDetails);

		try {
			EmployeeDetails emp = employeeService.saveEmployeeDetails(employeeDetails);
			session.setAttribute("msg", "Saved Successfully");
		} catch (DataIntegrityViolationException e) {
			// Handle the exception for duplicate username
			session.setAttribute("msg", "Email already exists");

			return "Admin/AddEmployeeForm";
		} catch (Exception e) {
			session.setAttribute("msg", "Something went wrong!");
		}

		return "redirect:/Admin/registration";
	}

	/* Leads Start */

	// Fresh Leads
	@GetMapping("/admin_Dashboard/freshleads")
	public String getFreshLeads(Model model) {

		model.addAttribute("leads", dataFetchingService.getFreshLeadsDetails("New"));
		return "Admin/freshLeads";
	}

	// Follow Up Leads
	@GetMapping("/admin_Dashboard/followupleads")
	public String getFollowUpLeads(Model model) {
		
		model.addAttribute("leads" ,dataFetchingService.getFreshLeadsDetails("Follow up"));
		return "Admin/followUpLeads";

	}

	// Success Leads
	@GetMapping("/admin_Dashboard/successleads")
	public String getSuccessLeads(Model model) {

		model.addAttribute("leads", dataFetchingService.getFreshLeadsDetails("Success"));
		return "Admin/successLeads";
	}

	@GetMapping("/admin_Dashboard/Leads")
	public String getAllLeads(ModelMap model) {

		model.addAttribute("Leads", dataFetchingService.getAllLeadsDetails());
		return "Admin/AllLeads";
	}

	// Editing lead
	@GetMapping("/admin_Dashboard/Leads/edit/{id}")
	public String getEditLeadsPage(@PathVariable Long id, Model model) {

		model.addAttribute("Leads", leadsRepository.findById(id).get());
		return "Admin/edit_Leads";
	}

	// Saving edited lead
	@PostMapping("/admin_Dashboard/Leads/edit/{id}")
	public String updateLead(@PathVariable Long id, @ModelAttribute Leads leads, HttpSession session) {

		Leads existingLead = leadsRepository.findById(id).get();

		existingLead.setName(leads.getName());
		existingLead.setEmail(leads.getEmail());
		existingLead.setPhone(leads.getPhone());
		existingLead.setAddress(leads.getAddress());
		existingLead.setCourse(leads.getCourse());
		existingLead.setMessage(leads.getMessage());
		existingLead.setLeadStatus(leads.getLeadStatus());
		
		
		try {
			leadsRepository.save(existingLead);			
			session.setAttribute("msg", "Updated Successfully");
		}  catch (Exception e) {
			session.setAttribute("msg", "Something went wrong!");
		}
		return "redirect:/Admin/admin_Dashboard/Leads/edit/{id}";
	}

	// deleting Lead by id
	@GetMapping("/admin_Dashboard/Leads/{id}")
	public String deleteLead(@PathVariable Long id) {
		leadsRepository.deleteById(id);
		return "redirect:/Admin/admin_Dashboard/Leads";
	}

	/* Leads End */

	// BusinessAssociate start

	@GetMapping("/admin_Dashboard/ApproveBusinessAssociate")
	public String getApproveBusinessAssociatePage(ModelMap model) {

		List<BusinessAssociate> approvedBAs = businessAssociateRepository.findByApproval(false); // dataFetchingService.getBusinessAssociateByApprove(false);

		model.addAttribute("approvedBusinessAssociates", approvedBAs);
		
		return "Admin/VerifyBusinessAssociate";

	}

	@GetMapping("/admin_Dashboard/approve/{id}")
	public String approveBA(@PathVariable Long id) {

		BusinessAssociate ba = businessAssociateService.approveBusinessAssociate(id);

		return "redirect:/Admin/admin_Dashboard/ApproveBusinessAssociate";
	}

	@GetMapping("/admin_Dashboard/reject/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String rejectBusinessAssociate(@PathVariable Long id,
			@ModelAttribute BusinessAssociateHistory businessAssociateHistory) {

		businessAssociateService.rejectBusinessAssociate(id, businessAssociateHistory);

		return "redirect:/Admin/admin_Dashboard/ApproveBusinessAssociate";
	}

	@PostMapping("/admin_Dashboard/reject/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String rejectBA(@PathVariable Long id, @ModelAttribute BusinessAssociateHistory businessAssociateHistory) {

		businessAssociateService.rejectBusinessAssociate(id, businessAssociateHistory);

		return "redirect:/Admin/admin_Dashboard/ApproveBusinessAssociate";
	}


	@GetMapping("/admin_Dashboard/businessAssociatePage")
	public String getBusinessAssociatePage(ModelMap model) {

		List<BusinessAssociate> businessAssociate = businessAssociateRepository.findByApproval(true);
		model.addAttribute("approvedBusinessAssociates", businessAssociate);
		
		return "Admin/ApprovedBusinessAssociate";

	}

	@GetMapping("/admin_Dashboard/rejectedBusinessAssociate")
	public String getRejectedBusinessAssociatePage(ModelMap model) {

		List<BusinessAssociateHistory> rejectedBAs = businessAssociateHistoryRepo.findAll();
		model.addAttribute("rejectedBusinessAssociates", rejectedBAs);

		return "Admin/RejectedBusinessAssociate";
	}
	
	//BusinessAssociate End
	
	@GetMapping("/profile")
	public String getProfile(Model model) {

		return "Admin/AdminProfile";

	}

	@GetMapping("/admin_Dashboard/users")
	public String getCallerDetails() {
		return "Admin/UsersDetails";
	}

}
