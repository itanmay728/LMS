package com.example.leadManagementSystem2.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, UUID> {

	
	@Query("select u from EmployeeDetails u where u.id = :id")
	public EmployeeDetails getEmployeeDetailsByUUID(@Param("id") UUID id);
}
