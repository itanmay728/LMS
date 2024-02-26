package com.example.leadManagementSystem2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/businessAssociate")
public class BusinessAssociateController {

	@GetMapping("/businessAssociateDashboard")
	public String getBusinessAssociateDashboard() {
		return "BusinessAssociate/businessAssociate_Dashboard";
	}
	

}
