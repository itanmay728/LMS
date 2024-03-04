package com.example.leadManagementSystem2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Service.BusinessAssociateService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/fieldManager")
public class FieldManagerContoller {

	@Autowired
	BusinessAssociateService businessAssociateService;

	@Autowired
	LeadsRepository leadsRepository;

	@GetMapping("/fieldManagerDashboard")
	public String getFieldManager() {
		return "FieldManager/FieldManagerDashboard";
	}

	@GetMapping("/fieldManagerDashboard/AssociateForm")
	public String getBusinessAssociateForm(Model model) {
		model.addAttribute("businessAssociate", new BusinessAssociate());
		return "FieldManager/BusinessAssociateForm";
	}

	@GetMapping("/fieldManagerDashboard/leads")
	public String getNumberOfLeads(ModelMap model) {
		long numberOfLeads = leadsRepository.count();
		model.addAttribute("numberOfLeads", numberOfLeads);
		return "FieldManager/Leads";
	}

	@PostMapping("/saveBA")
	public String SaveBusinessAssociate(@Valid @ModelAttribute BusinessAssociate businessAssociate,
			BindingResult result, HttpSession session) {

		if (result.hasErrors()) {
			System.out.println(result);
			return "FieldManager/BusinessAssociateForm";
		}

		System.out.println(businessAssociate);

		try {
			BusinessAssociate ba = businessAssociateService.saveBusinessAssociate(businessAssociate);
			session.setAttribute("msg", "Saved Successfully");
		} catch (DataIntegrityViolationException e) {
			// Handle the exception for duplicate username
			session.setAttribute("msg", "Email already exists");

			return "FieldManager/BusinessAssociateForm";
		} catch (Exception e) {
			session.setAttribute("msg", "Something went wrong!");
		}

		return "redirect:/fieldManager/fieldManagerDashboard/AssociateForm";
	}
}
