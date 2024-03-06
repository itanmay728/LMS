package com.example.leadManagementSystem2.Service;

import java.util.List;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.Leads;



public interface DataFetchingService {

	List<Leads> getAllLeadsDetails();
	
	List<Leads> getFreshLeadsDetails(String status);
	
	List<BusinessAssociate> getBusinessAssociateByApprove(boolean flag);

}
