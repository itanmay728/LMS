package com.example.leadManagementSystem2.Service;

import java.util.List;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.Leads;



public interface DataFetchingService {

	List<Leads> getAllLeadsDetails();
	
	List<BusinessAssociate> getBusinessAssociateByApprove();

}
