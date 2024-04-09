package com.example.leadManagementSystem2.Service;

import java.util.List;
import java.util.Optional;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Manager;

public interface ManagerService {

	List<EmployeeDetails> getFieldManagerOfManager(String managerUsername);

	Manager getManagerFromUsername(String username);

	List<EmployeeDetails> getCallerOfManager(String managerUsername);

}
