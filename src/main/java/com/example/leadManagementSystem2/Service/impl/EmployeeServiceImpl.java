package com.example.leadManagementSystem2.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.EmployeeDetailsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.EmployeeService;
import com.example.leadManagementSystem2.Service.UserService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private User_Credentials_Repository user_Credentials_Repository;
	
	@Override
	public EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails) {
		
		employeeDetails.setRole("ROLE_"+employeeDetails.getRole());
		
		EmployeeDetails newEmployee = employeeDetailsRepository.save(employeeDetails);
		
		
		 Users_Credentials users_Credentials = new Users_Credentials();
	        
		 	users_Credentials.setId(newEmployee.getId());
	        users_Credentials.setName(newEmployee.getName());
	        
	        String password =  bCryptPasswordEncoder.encode(newEmployee.getPassword());
	        users_Credentials.setPassword(password);
	        
	        
	        users_Credentials.setUserName(newEmployee.getUserName());
	        users_Credentials.setRole(newEmployee.getRole());
	        
	        
	        user_Credentials_Repository.save(users_Credentials);
		
		return newEmployee;
	}

}
