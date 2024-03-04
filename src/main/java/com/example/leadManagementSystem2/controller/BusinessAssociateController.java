package com.example.leadManagementSystem2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/businessAssociate")
public class BusinessAssociateController {
	
	@Autowired
	private User_Credentials_Repository user_Credentials_Repository;

	@GetMapping("/businessAssociateDashboard")
	public String getBusinessAssociateDashboard(HttpSession session) {
		String Username = (String) session.getAttribute("username");
		System.out.println(Username);
		
		if (Username == null) {
	        // Handle the case where username is not found in the session
	        return "redirect:/login"; // Redirect to login page or handle appropriately
	    }

	    Users_Credentials user = user_Credentials_Repository.getUsersCredentialsByUserName(Username);
	    
	    BusinessAssociate businessAssociate = user.getBusinessAssociate();
		 session.setAttribute("businessAssociate", businessAssociate);
		return "BusinessAssociate/businessAssociate_Dashboard";
	}
	

}
