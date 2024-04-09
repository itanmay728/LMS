package com.example.leadManagementSystem2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.EmployeeService;
import com.example.leadManagementSystem2.Service.ManagerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private User_Credentials_Repository user_Credentials_Repository;

	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/managerDashboard")
	public String getManagerDashboard(Model model, HttpSession session) {

		String Username = (String) session.getAttribute("username");
		System.out.println(Username);

		if (Username == null) {
			// Handle the case where username is not found in the session
			return "redirect:/login"; // Redirect to login page or handle appropriately
		}

		return "Manager/ManagerDashboard";
	}

	@GetMapping("/managerDashboard/fieldManager")
	public String getFieldManager(HttpSession session, Model model) {
		String Username = (String) session.getAttribute("username");
		Users_Credentials user = user_Credentials_Repository.getUsersCredentialsByUserName(Username);
		//session.setAttribute("user", user);
		String managerUsername = user.getUserName();
		List<EmployeeDetails> fieldManager = managerService.getFieldManagerOfManager(managerUsername);
		
//		 List<Integer> countOfBA = new ArrayList<>();
//
//		    for (EmployeeDetails fm : fieldManager) {
//		        int count = employeeService.getCountOfBusinessAssociatesUnderFieldManager(fm);
//		        countOfBA.add(count);
//		    }

		model.addAttribute("fm", fieldManager);

		return "Manager/FieldManager";
	}
	
	@GetMapping("/managerDashboard/caller")
	public String getCaller(HttpSession session, Model model){
		
		String Username = (String) session.getAttribute("username");
		Users_Credentials user = user_Credentials_Repository.getUsersCredentialsByUserName(Username);
		//session.setAttribute("user", user);
		String managerUsername = user.getUserName();
		List<EmployeeDetails> caller = managerService.getCallerOfManager(managerUsername);
		
		model.addAttribute("caller", caller);
		
		return "Manager/Caller";
	}

}
