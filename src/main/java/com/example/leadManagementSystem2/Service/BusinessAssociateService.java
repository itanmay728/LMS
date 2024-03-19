package com.example.leadManagementSystem2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.BusinessAssociateHistory;
import com.example.leadManagementSystem2.Entity.Leads;

import jakarta.persistence.EntityManager;

public interface BusinessAssociateService {
	

	List<BusinessAssociate> getBusinessAssociateByApprove(boolean flag);
	List<BusinessAssociate> findByFieldManagerId(Long id);
	public BusinessAssociate saveBusinessAssociate(BusinessAssociate businessAssociate);
	public BusinessAssociate approveBusinessAssociate(Long businessAssociateId);
	boolean rejectBusinessAssociate(Long businessAssociateId, BusinessAssociateHistory businessAssociateHistory);
	public void removeSessionMessage();
	
	public void walletUpdate(Leads leads);
	


}
