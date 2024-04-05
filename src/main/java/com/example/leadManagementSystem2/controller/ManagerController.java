package com.example.leadManagementSystem2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Manager")
public class ManagerController {

	@GetMapping("/ManagerDashboard")
	public String getManagerDashboard() {
		return "Manager/ManagerDashboard";
	}
	
}
