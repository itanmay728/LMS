package com.example.leadManagementSystem2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;

public interface BusinessAssociateRepository extends JpaRepository<BusinessAssociate, Long> {

	List<BusinessAssociate> findByApproval(boolean Approval);
	
	@Query("select u from BusinessAssociate u where u.userName = :userName")
	public BusinessAssociate getBusinessAssociateByUserName(@Param("userName") String userName);

	//search
	List<BusinessAssociate> findByUserNameContaining(String userName);
}
