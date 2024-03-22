package com.example.leadManagementSystem2.Service;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;

public interface EmployeeService {

	public EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails);

	public void removeSessionMessage();
	
	public void updateEmployeeDetails(Long id, EmployeeDetails employeeDetails);

	
}
