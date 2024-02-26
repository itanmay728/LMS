package com.example.leadManagementSystem2.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private User_Credentials_Repository user_Credentials_Repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Users_Credentials saveUser(Users_Credentials users_credentials) {
		String password =  passwordEncoder.encode(users_credentials.getPassword());
		users_credentials.setPassword(password);
		users_credentials.setRole("ROLE_"+users_credentials.getRole());
		Users_Credentials newUser = user_Credentials_Repository.save(users_credentials);
		
		return newUser;
	}
	
	@Override
	public void removeSessionMessage() {
		
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msg");
	}
}
