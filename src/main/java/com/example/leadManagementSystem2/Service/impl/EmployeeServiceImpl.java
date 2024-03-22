package com.example.leadManagementSystem2.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.EmployeeDetailsRepository;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;
import com.example.leadManagementSystem2.Service.EmployeeService;
import com.example.leadManagementSystem2.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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

		employeeDetails.setRole("ROLE_" + employeeDetails.getRole());


		Users_Credentials users_Credentials = new Users_Credentials();
		users_Credentials.setName(employeeDetails.getName());

		String password = bCryptPasswordEncoder.encode(employeeDetails.getPassword());
		users_Credentials.setPassword(password);

		users_Credentials.setUserName(employeeDetails.getUserName());
		users_Credentials.setRole(employeeDetails.getRole());

		users_Credentials.setEmployeeDetails(employeeDetails);

		employeeDetails.setUsers_Credentials(users_Credentials);

		return employeeDetailsRepository.save(employeeDetails);
	}

	@Override
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");

	}

	@Override
	public void updateEmployeeDetails(Long id, EmployeeDetails employeeDetails) {
		
		EmployeeDetails existingEmp = employeeDetailsRepository.findById(id).get();
		Users_Credentials users_Credentials =  existingEmp.getUsers_Credentials();
	
		existingEmp.setName(employeeDetails.getName());
		users_Credentials.setName(employeeDetails.getName());
		
// 		existingEmp.setUserName(employeeDetails.getUserName());
//		users_Credentials.setUserName(employeeDetails.getUserName());
		
		existingEmp.setPassword(employeeDetails.getPassword());
		String password = bCryptPasswordEncoder.encode(employeeDetails.getPassword());
		users_Credentials.setPassword(password);
		
		existingEmp.setRole("ROLE_" + employeeDetails.getRole());
		users_Credentials.setRole("ROLE_" + employeeDetails.getRole());
		
		existingEmp.setPhone(employeeDetails.getPhone());
		existingEmp.setAddress(employeeDetails.getAddress());	
//		existingEmp.setAadhaar(employeeDetails.getAadhaar());
//		existingEmp.setPanNumber(employeeDetails.getPanNumber());
		existingEmp.setAccountHolderName(employeeDetails.getAccountHolderName());
		existingEmp.setAccountNumber(employeeDetails.getAccountNumber());
		existingEmp.setBranchAddress(employeeDetails.getBranchAddress());
		existingEmp.setIfscCode(employeeDetails.getIfscCode());
		
		employeeDetailsRepository.save(existingEmp);
	}

}
