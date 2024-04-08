package com.example.leadManagementSystem2.Service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IllegalFormatWidthException;
import java.util.List;
import java.util.Random;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Manager;
import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.EmployeeDetailsRepository;
import com.example.leadManagementSystem2.Repository.ManagerRepository;
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
	
	@Autowired
	private ManagerRepository managerRepository;

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
		
		if (employeeDetails.getRole().equals("ROLE_MANAGER")) {
			Manager manager = new Manager();
			manager.setName(employeeDetails.getName());
			manager.setUserName(employeeDetails.getUserName());
			manager.setAddress(employeeDetails.getAddress());
			manager.setPhone(employeeDetails.getPhone());
			manager.setPanNumber(employeeDetails.getPanNumber());
			manager.setAadhaar(employeeDetails.getAadhaar());
			manager.setRole(employeeDetails.getRole());
			manager.setAccountHolderName(employeeDetails.getAccountHolderName());
			manager.setAccountNumber(employeeDetails.getAccountNumber());
			manager.setIfscCode(employeeDetails.getIfscCode());
			manager.setBranchAddress(employeeDetails.getBranchAddress());
			manager.setPassword(employeeDetails.getPassword());
			managerRepository.save(manager);
		}
		
		if ((employeeDetails.getRole().equals("ROLE_CALLER")) || (employeeDetails.getRole().equals("ROLE_FIELDMANAGER"))) {
			
			List<Manager> manager = managerRepository.findAll();
			
			
			 Random myRandomizer = new Random();
			  
			 Manager managerD = manager.get(myRandomizer.nextInt(manager.size()));
			 employeeDetails.setManager(managerD);
			
		}
		
//		if (employeeDetails.getRole().equals("ROLE_CALLER")) {
			
//			List<Manager> manager = managerRepository.findAll();
			
			 
//			List<Integer> numberOfCaller = new ArrayList<>();
//			
//			for(int i = 0; i<manager.size(); i++) {
//				numberOfCaller.add(manager.get(i).getEmployeeDetails().size()); 
//			}
//			
//			
//			Integer smallestNumberOfCaller = Collections.min(numberOfCaller);
//			
//			for(int i = 0; i<manager.size(); i++) {
//				
//				if (smallestNumberOfCaller == manager.get(i).getEmployeeDetails().size()) {
//					employeeDetails.setManager(manager.get(i));
//					
//				}
//			}
			
			
//		}
		
//	if (employeeDetails.getRole().equals("ROLE_FIELDMANAGER")) {
			
//			List<Manager> manager = managerRepository.findAll();
		
			
//			 Random myRandomizer = new Random();
			  
//			 Manager managerD = manager.get(myRandomizer.nextInt(manager.size()));
//			 employeeDetails.setManager(managerD);
			 
			
//			List<Integer> numberOfFIELDMANAGER = new ArrayList<>();
			
//			for(int i = 0; i<manager.size(); i++) {
//				numberOfFIELDMANAGER.add(manager.get(i).getEmployeeDetails().size()); 
//			}
			
			
//			Integer smallestNumberOfFIELDMANAGER = Collections.min(numberOfFIELDMANAGER);
			
//			for(int i = 0; i<manager.size(); i++) {
//				
//				if (smallestNumberOfFIELDMANAGER == manager.get(i).getEmployeeDetails().size()) {
//					employeeDetails.setManager(manager.get(i));
//					
//				}
//			}
			
			
	//	}
		

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
