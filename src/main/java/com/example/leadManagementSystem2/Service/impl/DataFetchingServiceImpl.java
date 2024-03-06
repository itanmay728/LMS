package com.example.leadManagementSystem2.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.leadManagementSystem2.Entity.BusinessAssociate;
import com.example.leadManagementSystem2.Entity.Leads;
import com.example.leadManagementSystem2.Repository.BusinessAssociateRepository;
import com.example.leadManagementSystem2.Repository.LeadsRepository;
import com.example.leadManagementSystem2.Service.DataFetchingService;

@Service
public class DataFetchingServiceImpl implements DataFetchingService {
	
	@Autowired
	LeadsRepository leadsRepository;
	
	@Autowired
	BusinessAssociateRepository businessAssociateRepository;

	@Override
	public List<Leads> getAllLeadsDetails() {
		
		return leadsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	@Override
	public List<Leads> getFreshLeadsDetails(String status) {
		
		return leadsRepository.findByLeadStatus(status);
	}

	@Override
	public List<BusinessAssociate> getBusinessAssociateByApprove(boolean flag) {
	
		return businessAssociateRepository.findByApproval(flag);
	}

	



}
