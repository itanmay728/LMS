package com.example.leadManagementSystem2.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.QRCodeGenerator;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.LeadService;
import com.google.zxing.WriterException;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/businessAssociate")
public class BusinessAssociateController {

	@Autowired
	private User_Credentials_Repository user_Credentials_Repository;

	@Autowired
	private LeadsRepository leadsRepository;

	@Autowired
	private LeadService leadService;

	@GetMapping("/businessAssociateDashboard")
	public String getBusinessAssociateDashboard(Model model, HttpSession session) {
		String Username = (String) session.getAttribute("username");
		System.out.println(Username);

		if (Username == null) {
			// Handle the case where username is not found in the session
			return "redirect:/login"; // Redirect to login page or handle appropriately
		}

		Users_Credentials user = user_Credentials_Repository.getUsersCredentialsByUserName(Username);
		session.setAttribute("user", user);
		BusinessAssociate businessAssociate = user.getBusinessAssociate();
		session.setAttribute("businessAssociate", businessAssociate);

		String username = (String) session.getAttribute("username");
		long numberOfLeads = leadService.getLeadsCountOfBusinessAssociate(username);
		model.addAttribute("numberOfLeads", numberOfLeads);

		long numberOfSuccessLeads = leadService.getLeadsCountByStatusOfBusinessAssociate(username, "Success");
		model.addAttribute("numberOfSuccessLeads", numberOfSuccessLeads);
		return "BusinessAssociate/businessAssociate_Dashboard";
	}

	@GetMapping("/profile")
	public String getProfilePage() {
		return "BusinessAssociate/BusinessAssociateProfile";
	}

	// All leads

	@GetMapping("/businessAssociateDashboard/leads")
	public String getLeads(Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		long numberOfLeads = leadService.getLeadsCountOfBusinessAssociate(username);
		model.addAttribute("numberOfLeads", numberOfLeads);
		return "BusinessAssociate/Leads";
	}

	// Success Leads
	@GetMapping("/businessAssociateDashboard/successLeads")
	public String getSuccessLeads(Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");

		long numberOfSuccessLeads = leadService.getLeadsCountByStatusOfBusinessAssociate(username, "Success");
		model.addAttribute("numberOfSuccessLeads", numberOfSuccessLeads);
		return "BusinessAssociate/SuccessLeads";
	}

	// Wallet
	@GetMapping("/businessAssociateDashboard/Wallet")
	public String getWalletPage() {
		return "BusinessAssociate/BusinessAssociateWallet";
	}

	// QR Code generation
	@GetMapping("/businessAssociateDashboard/QRCodePage")
	public String getQRCodePage(@RequestParam(name = "partyid") Long id, Model model, HttpSession session)
			throws WriterException, IOException {
		BusinessAssociate businessAssociate = (BusinessAssociate) session.getAttribute("businessAssociate");

		String url = "http://localhost:8080/CustomersForm/PublicEntryForm?partyid=" + id;
		System.out.println(url);

		int qrCodeWidth = 256; // Adjust as needed
		int qrCodeHeight = 256; // Adjust as needed
		byte[] qrCodeImage = QRCodeGenerator.generateQRCode(url, qrCodeWidth, qrCodeHeight);

		// Encode QR code image as Base64 for Thymeleaf access
		String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCodeImage);

//			  // Add QR code data to the model
		model.addAttribute("qrCodeBase64", qrCodeBase64);
		return "BusinessAssociate/QRCodePage";

	}

	@GetMapping("/businessAssociateDashboard/personalformlink")
	public String getPersonalFormLinkPage() {

		return "BusinessAssociate/PersonalFormLink";
	}

}
