package com.example.leadManagementSystem2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.leadManagementSystem2.Entity.Leads;

public interface LeadsRepository extends JpaRepository<Leads, Long> {

	List<Leads> findByLeadStatus(String leadStatus);
}
