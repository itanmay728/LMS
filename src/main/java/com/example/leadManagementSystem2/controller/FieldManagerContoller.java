package com.example.leadManagementSystem2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Service.BusinessAssociateService;

@Controller
@RequestMapping("/fieldManager")
public class FieldManagerContoller {

	@Autowired
	BusinessAssociateService businessAssociateService;
	
	@GetMapping("/fieldManagerDashboard")
	public String getFieldManager() {
		return "FieldManager/FieldManagerDashboard";
	}
	
	@GetMapping("/fieldManagerDashboard/AssociateForm")
	public String getBusinessAssociateForm() {
		return "FieldManager/BusinessAssociateForm";
	}
	
	@PostMapping("/saveBA")
	public String SaveBusinessAssociate(@ModelAttribute BusinessAssociate businessAssociate) {
		
		businessAssociateService.saveBusinessAssociate(businessAssociate);
		
		 
		return "redirect:/fieldManager/fieldManagerDashboard/AssociateForm";
	}
}
