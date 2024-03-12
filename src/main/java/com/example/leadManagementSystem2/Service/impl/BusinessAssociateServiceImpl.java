package com.example.leadManagementSystem2.Service.impl;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.BusinessAssociateHistory;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.BusinessAssociateHistoryRepo;
import com.example.leadManagementSystem2.Repository.BusinessAssociateRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.BusinessAssociateService;

import jakarta.servlet.http.HttpSession;

@Service
public class BusinessAssociateServiceImpl implements BusinessAssociateService {

	@Autowired
	private BusinessAssociateRepository businessAssociateRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private User_Credentials_Repository user_Credentials_Repository;

	@Autowired
	private BusinessAssociateHistoryRepo businessAssociateHistoryRepo;

	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public BusinessAssociate saveBusinessAssociate(BusinessAssociate businessAssociate) {

		BusinessAssociate newBA = businessAssociateRepository.save(businessAssociate);

		return newBA;

	}

	@Override
	public List<BusinessAssociate> getBusinessAssociateByApprove(boolean flag) {

		return businessAssociateRepository.findByApproval(flag);
	}

	@Override
	public BusinessAssociate approveBusinessAssociate(Long businessAssociateId) {

		BusinessAssociate ba = businessAssociateRepository.findById(businessAssociateId) // Adjusted for Integer ID
				.orElseThrow(() -> new IllegalArgumentException("BusinessAssociate not found")); // Simple exception
		if (ba.isApproval()) {
			throw new IllegalArgumentException("BusinessAssociate already approved"); // Simple exception
		}

		// Generate password if not already set
		if (ba.getPassword() == null) {
			String passwordHash = generateSecurePassword(); // Assuming you have this method
			// String passwordHash = passwordEncoder.encode(rawPassword); // Assuming you
			// have passwordEncoder injected
			ba.setPassword(passwordHash);
		}
		ba.setApproval(true);

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("ktanmay728@gmail.com");
		message.setTo(ba.getUserName());
		message.setSubject("Subject : Successfully approved");

		message.setText("Hi " + ba.getName() + "\n" + "Your Details are Successfully approved. Now you can Login."
				+ "\n" + "Your user name and password is mentioned below" + "\n" + "UserName: " + ba.getUserName()
				+ "\n" + "Password: " + ba.getPassword());

		Users_Credentials users_Credentials = new Users_Credentials();

		users_Credentials.setName(ba.getName());

		String password = bCryptPasswordEncoder.encode(ba.getPassword());
		users_Credentials.setPassword(password);

		users_Credentials.setUserName(ba.getUserName());
		users_Credentials.setRole("ROLE_BUSINESSASSOCIATE");
		users_Credentials.setBusinessAssociate(ba);

		ba.setUsers_Credentials(users_Credentials);
		// user_Credentials_Repository.save(users_Credentials);

		javaMailSender.send(message);

		// Return a custom response with success message and approved BA
		return businessAssociateRepository.save(ba); // Save and return the approved BA
	}

	private String generateSecurePassword() {
		int passwordLength = 12; // Adjustable to your security requirements
		String passwordChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()-_=+[]{};':\",<.>/?\\|";
		Random random = new SecureRandom();
		StringBuilder password = new StringBuilder();
		for (int i = 0; i < passwordLength; i++) {
			password.append(passwordChars.charAt(random.nextInt(passwordChars.length())));
		}
		return password.toString();
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean rejectBusinessAssociate(Long businessAssociateId,
			BusinessAssociateHistory businessAssociateHistory) {
		BusinessAssociate ba = businessAssociateRepository.findById(businessAssociateId)
				.orElseThrow(() -> new IllegalArgumentException("BusinessAssociate not found"));

//	    if (!ba.isApproved()) {
//	        throw new IllegalArgumentException("BusinessAssociate already rejected");
//	    }

		// Move to history table
		// ba.setRejectionReason(businessAssociateHistory.getRejectionReason());
		BusinessAssociateHistory baHistory = new BusinessAssociateHistory(ba);
		baHistory.setRejectionReason(businessAssociateHistory.getRejectionReason());
		businessAssociateHistoryRepo.save(baHistory);

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("ktanmay728@gmail.com");
		message.setTo(ba.getUserName());
		message.setSubject("Subject : Application rejected");
		message.setText("Hi " + ba.getName() + "\n" + "Your application has been rejected" + "\n" + "\n" + "$ REASON $"
				+ "\n" + businessAssociateHistory.getRejectionReason());
		javaMailSender.send(message);

		// Optionally, delete from main table for efficiency:
		businessAssociateRepository.deleteById(businessAssociateId);

		return true; // Indicate successful rejection
	}

	@Override
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");

	}

}
