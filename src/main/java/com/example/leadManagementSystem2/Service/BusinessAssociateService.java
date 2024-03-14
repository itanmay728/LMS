package com.example.leadManagementSystem2.Service;

import java.util.List;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.BusinessAssociateHistory;
import com.example.leadManagementSystem2.Entity.Leads;

import jakarta.servlet.http.HttpSession;

public interface BusinessAssociateService {

	List<BusinessAssociate> getBusinessAssociateByApprove(boolean flag);
	public BusinessAssociate saveBusinessAssociate(BusinessAssociate businessAssociate);
	public BusinessAssociate approveBusinessAssociate(Long businessAssociateId);
	boolean rejectBusinessAssociate(Long businessAssociateId, BusinessAssociateHistory businessAssociateHistory);
	public void removeSessionMessage();
	
	public void walletUpdate(Leads leads);
}
