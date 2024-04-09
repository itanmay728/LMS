package com.example.leadManagementSystem2.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Manager;
import com.example.leadManagementSystem2.Repository.EmployeeDetailsRepository;
import com.example.leadManagementSystem2.Repository.ManagerRepository;
import com.example.leadManagementSystem2.Service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;

	@Autowired
	private ManagerRepository managerRepository;

	@Override
	public Manager getManagerFromUsername(String username) {

		Manager manager = managerRepository.getManagerByUserName(username);

		return manager;
	}

	@Override
	public List<EmployeeDetails> getFieldManagerOfManager(String managerUsername) {

		Manager manager = getManagerFromUsername(managerUsername);
		Long id = manager.getId();

		List<EmployeeDetails> employees = manager.getEmployeeDetails();
		List<EmployeeDetails> fieldmanager = new ArrayList<>();

		for (EmployeeDetails fm : employees) {
			if (fm.getRole().equals("ROLE_FIELDMANAGER")) {
				fieldmanager.add(fm);
			}else {
				
			}

		}
		return fieldmanager;

	}
	
	@Override
	public List<EmployeeDetails> getCallerOfManager(String managerUsername) {

		Manager manager = getManagerFromUsername(managerUsername);
		Long id = manager.getId();

		List<EmployeeDetails> employees = manager.getEmployeeDetails();
		List<EmployeeDetails> caller = new ArrayList<>();

		for (EmployeeDetails c : employees) {
			if (c.getRole().equals("ROLE_CALLER")) {
				caller.add(c);
			}else {
				
			}

		}
		return caller;

	}
}
