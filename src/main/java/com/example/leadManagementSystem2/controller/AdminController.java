package com.example.leadManagementSystem2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.BusinessAssociateHistory;
import com.example.leadManagementSystem2.Entity.Course;
import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Entity.LeadsConversation;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Entity.WalletDetails;
import com.example.leadManagementSystem2.Repository.BusinessAssociateHistoryRepo;
import com.example.leadManagementSystem2.Repository.BusinessAssociateRepository;
import com.example.leadManagementSystem2.Repository.CourseRepository;
import com.example.leadManagementSystem2.Repository.EmployeeDetailsRepository;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Repository.WalletDetailsRepository;
import com.example.leadManagementSystem2.Service.BusinessAssociateService;
import com.example.leadManagementSystem2.Service.EmployeeService;
import com.example.leadManagementSystem2.Service.LeadService;
import com.example.leadManagementSystem2.Service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	private User_Credentials_Repository user_Credentials_Repository;
	@Autowired
	private LeadsRepository leadsRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BusinessAssociateRepository businessAssociateRepository;

	@Autowired
	private BusinessAssociateHistoryRepo businessAssociateHistoryRepo;

	@Autowired
	private BusinessAssociateService businessAssociateService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;

	@Autowired
	private LeadService leadService;

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private WalletDetailsRepository walletDetailsRepository;

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

		long numberOfLeads = leadsRepository.count();
		model.addAttribute("numberOfLeads", numberOfLeads);

		model.addAttribute("numberOfFreshLeads", leadService.getLeadsDetailsByStatus("New").size());

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

		return "redirect:/Admin/admin_Dashboard/registration";
	}

	/* Leads Start */

	// Fresh Leads
	@GetMapping("/admin_Dashboard/freshleads")
	public String getFreshLeads(Model model) {

		model.addAttribute("leads", leadService.getLeadsDetailsByStatus("New"));

		return "Admin/FreshLeads";
	}

	// Follow Up Leads
	@GetMapping("/admin_Dashboard/followupleads")
	public String getFollowUpLeads(Model model) {

		model.addAttribute("leads", leadService.getLeadsDetailsByStatus("Follow up"));

		return "Admin/FollowUpLeads";

	}

	// Success Leads
	@GetMapping("/admin_Dashboard/successleads")
	public String getSuccessLeads(Model model) {

		model.addAttribute("leads", leadService.getLeadsDetailsByStatus("Success"));
		return "Admin/SuccessLeads";
	}

	// All Leads
	@GetMapping("/admin_Dashboard/Leads")
	public String getAllLeads(ModelMap model) {

		List<Leads> leads = leadService.getAllLeadsDetails();

		model.addAttribute("Leads", leads);
		// model.addAttribute("Employees", employeeService.getAllEmployees());
		return "Admin/AllLeads";
	}

	// Editing lead
	@GetMapping("/admin_Dashboard/Leads/edit/{id}")
	public String getEditLeadsPage(@PathVariable Long id, Model model) {

		model.addAttribute("Leads", leadsRepository.findById(id).get());
		return "Admin/Edit_Leads";
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
			businessAssociateService.walletUpdate(existingLead);
			leadsRepository.save(existingLead);
			session.setAttribute("msg", "Updated Successfully");
		} catch (Exception e) {
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

	@PostMapping("/admin_Dashboard/Leads/saveconversation/{id}")
	public String saveConversationOfLead(@PathVariable Long id, @ModelAttribute LeadsConversation leadsConversation) {

		leadService.saveLeadsConversation(id, leadsConversation);

		return "redirect:/Admin/admin_Dashboard/Leads/edit/{id}";
	}

	/* Leads End */

	// BusinessAssociate start

	@GetMapping("/admin_Dashboard/ApproveBusinessAssociate")
	public String getApproveBusinessAssociatePage(ModelMap model) {

		List<BusinessAssociate> approvedBAs = businessAssociateService.getBusinessAssociateByApprove(false); // dataFetchingService.getBusinessAssociateByApprove(false);

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

		List<BusinessAssociate> businessAssociate = businessAssociateService.getBusinessAssociateByApprove(true);
		model.addAttribute("approvedBusinessAssociates", businessAssociate);

		return "Admin/ApprovedBusinessAssociate";

	}

	@GetMapping("/admin_Dashboard/rejectedBusinessAssociate")
	public String getRejectedBusinessAssociatePage(ModelMap model) {

		List<BusinessAssociateHistory> rejectedBAs = businessAssociateHistoryRepo.findAll();
		model.addAttribute("rejectedBusinessAssociates", rejectedBAs);

		return "Admin/RejectedBusinessAssociate";
	}

	// BusinessAssociate End

	@GetMapping("/profile")
	public String getProfile(Model model) {

		return "Admin/AdminProfile";

	}

	@GetMapping("/admin_Dashboard/users")
	public String getCallerDetails() {
		return "Admin/SearchEmployee";
	}

	@GetMapping("/admin_Dashboard/users/{id}")
	public String getSearchedEmployee(@PathVariable("id") Long id, Model model) {

		EmployeeDetails employeeDetails = employeeDetailsRepository.findById(id).get();

		model.addAttribute("employee", employeeDetails);

		return "Admin/EmployeePage";
	}

	// user search in user menu

	@GetMapping("/admin_Dashboard/users/search/{query}")
	@ResponseBody
	public ResponseEntity<?> search(@PathVariable("query") String query) {

		System.out.println(query);

		List<EmployeeDetails> employees = this.employeeDetailsRepository.findByUserNameContaining(query);

		return ResponseEntity.ok(employees);
	}

	// lead search in all leads

	@GetMapping("/admin_Dashboard/Leads/search/{query}")
	@ResponseBody
	public ResponseEntity<?> searchLead(@PathVariable("query") String query) {

		System.out.println(query);

		List<Leads> leads = this.leadsRepository.findByEmailContaining(query);

		return ResponseEntity.ok(leads);
	}

	@GetMapping("/admin_Dashboard/businessAssociateUnderFM/{id}")
	public String businessAssociateUnderFieldManager(@PathVariable Long id, Model model) {
		List<BusinessAssociate> businessAssociates = businessAssociateService.findByFieldManagerId(id);
		model.addAttribute("businessAssociates", businessAssociates);
		return "Admin/BusinessAssociateUnderFM";
	}

	@GetMapping("/admin_Dashboard/leadsUnderCaller/{id}")
	public String leadsUnderCaller(@PathVariable Long id, Model model) {

		// Users_Credentials users_Credentials =
		// user_Credentials_Repository.getById(id);
		// users_Credentials.getEmployeeDetails()

		EmployeeDetails employeeDetails = employeeDetailsRepository.findById(id).get();

		List<Leads> leads = employeeDetails.getLeads();

		model.addAttribute("leads", leads);

		model.addAttribute("callerId", id);
		return "Admin/LeadsOfParticularCaller";
	}


	// BA search in approved BA

	@GetMapping("/admin_Dashboard/BusinessAssociate/search/{query}")
	@ResponseBody
	public ResponseEntity<?> searchBusinessAssociate(@PathVariable("query") String query) {

		System.out.println(query);

		List<BusinessAssociate> businessAssociates = this.businessAssociateRepository.findByUserNameContaining(query);

		return ResponseEntity.ok(businessAssociates);
	}

	@GetMapping("/admin_Dashboard/Users/edit/{id}")
	public String getEditEmployeePage(@PathVariable Long id, Model model) {

		model.addAttribute("employee", employeeDetailsRepository.findById(id).get());
		return "Admin/EditEmployee";
	}

	@PostMapping("/admin_Dashboard/Users/edit/{id}")
	// @Transactional
	public String updateEmployee(@PathVariable Long id, @ModelAttribute EmployeeDetails employee) {

		// model.addAttribute("employee", employeeDetailsRepository.findById(id).get());

		employeeService.updateEmployeeDetails(id, employee);

		try {

			// employeeService.saveEmployeeDetails(existingEmp);
			// session.setAttribute("msg", "Updated Successfully");
		} catch (Exception e) {
			System.out.println(e);// session.setAttribute("msg", "Something went wrong!");
		}

		return "redirect:/Admin/admin_Dashboard/Users/edit/{id}";
	}

	@GetMapping("/admin_Dashboard/addCourse")
	public String getAddCoursePage(Model model) {

		model.addAttribute("Courses", courseRepository.findAll());

		return "Admin/AddCourse";
	}

	@PostMapping("/admin_Dashboard/saveCourse")
	public String saveCourse(@ModelAttribute Course course) {

		courseRepository.save(course);
		return "redirect:/Admin/admin_Dashboard/addCourse";
	}

	@GetMapping("/admin_Dashboard/deleteCourse/{id}")
	public String deleteCourse(@PathVariable Long id) {

		courseRepository.deleteById(id);

		return "redirect:/Admin/admin_Dashboard/addCourse";
	}

	// method to transfer selected leads

	@PostMapping("/admin_Dashboard/leadsUnderCaller/{id}")
	public String transferSelectedLeads(@PathVariable Long id,
			@RequestParam("leadIds") List<List<String>> leadIdsAsStrings,
			@RequestParam("newCallerId") Long newCallerId) {

		// Flatten the list of lists into a single list of strings and remove square brackets and quotes
	    List<String> flattenedLeadIds = leadIdsAsStrings.stream()
	                                                    .flatMap(List::stream)
	                                                    .map(s -> s.replaceAll("[\\[\\]\"]", ""))
	                                                    .collect(Collectors.toList());

	    // Convert List<String> to List<Long> using a stream
	    List<Long> leadIds = flattenedLeadIds.stream()
	                                          .map(Long::valueOf)
	                                          .collect(Collectors.toList());
		System.out.println(leadIds);
		System.out.println(newCallerId);

// Call the service with the converted leadIds
		leadService.transferSelectedLeads(leadIds, newCallerId);

		return "redirect:/Admin/admin_Dashboard/leadsUnderCaller/{id}";
	}
	
	
	//Payment of BA
	
	@GetMapping("/admin_Dashboard/paymentRequest")
	public String paymentRequestPage(Model model) {
		
		model.addAttribute("walletDetails", walletDetailsRepository.findAll());
		return "Admin/BusinessAssociatePaymentRequest";
	}
	
	@PostMapping("/admin_Dashboard/ApprovePaymentRequest/{id}")
	public String approvePayment(@PathVariable("id") Long id, @ModelAttribute WalletDetails walletDetails ) {
		
		WalletDetails walletDetails2 = walletDetailsRepository.findById(id).get();
		walletDetails2.setTransaction_id(walletDetails.getTransaction_id());
		walletDetails2.setStatus("Approved");
		
		walletDetailsRepository.save(walletDetails2);
		return "redirect:/Admin/admin_Dashboard/paymentRequest";
	}
	
	@PostMapping("/admin_Dashboard/RejectPaymentRequest/{id}")
	public String rejectPayment(@PathVariable("id") Long id, @ModelAttribute WalletDetails walletDetails ) {
		
		WalletDetails walletDetails2 = walletDetailsRepository.findById(id).get();
		walletDetails2.setRejection_reason(walletDetails.getRejection_reason());
		walletDetails2.setStatus("Rejected");
		
		walletDetailsRepository.save(walletDetails2);
		return "redirect:/Admin/admin_Dashboard/paymentRequest";
	}

}
