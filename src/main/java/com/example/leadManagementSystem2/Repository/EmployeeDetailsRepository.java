package com.example.leadManagementSystem2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {

	
	@Query("select u from EmployeeDetails u where u.id = :id")
	public EmployeeDetails getEmployeeDetailsByid(@Param("id") Long id);

	//search
	public List<EmployeeDetails> findByNameContaining(String name);

}
