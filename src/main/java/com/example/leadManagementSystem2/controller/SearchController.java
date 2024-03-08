package com.example.leadManagementSystem2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Repository.EmployeeDetailsRepository;

@RestController
public class SearchController {

	
	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;
	
	//search handler
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query){
		
		System.out.println(query);
		
		List<EmployeeDetails> employees=this.employeeDetailsRepository.findByNameContaining(query);
		
		return ResponseEntity.ok(employees);
	}
}
