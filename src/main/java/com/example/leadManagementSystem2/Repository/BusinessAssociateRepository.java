package com.example.leadManagementSystem2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;

public interface BusinessAssociateRepository extends JpaRepository<BusinessAssociate, Long> {

	List<BusinessAssociate> findByApproval(boolean Approval);
}
