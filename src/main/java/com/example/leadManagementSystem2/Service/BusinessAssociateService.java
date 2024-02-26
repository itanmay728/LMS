package com.example.leadManagementSystem2.Service;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.BusinessAssociateHistory;

import jakarta.servlet.http.HttpSession;

public interface BusinessAssociateService {

	public BusinessAssociate saveBusinessAssociate(BusinessAssociate businessAssociate);
	public BusinessAssociate approveBusinessAssociate(Long businessAssociateId);
	boolean rejectBusinessAssociate(Long businessAssociateId, BusinessAssociateHistory businessAssociateHistory);
}
