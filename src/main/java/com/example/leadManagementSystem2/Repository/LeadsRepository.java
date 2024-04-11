package com.example.leadManagementSystem2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.leadManagementSystem2.Entity.EmployeeDetails;
import com.example.leadManagementSystem2.Entity.Leads;

public interface LeadsRepository extends JpaRepository<Leads, Long> {

	List<Leads> findByLeadStatus(String leadStatus);
	
	int countByLeadStatus(String leadStatus);
	
	public List<Leads> findByEmailContainingAndEmployeeDetails(String email, EmployeeDetails employeeDetails);
	
	
	//search
	public List<Leads> findByEmailContaining(String email);
	
	 @Query("SELECT l FROM Leads l WHERE YEAR(l.date) = :year AND MONTH(l.date) = :month")
	 List<Leads> findLeadsByMonth(int year, int month);
}
