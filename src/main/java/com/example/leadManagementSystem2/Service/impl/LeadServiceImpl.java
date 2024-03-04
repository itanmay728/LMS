package com.example.leadManagementSystem2.Service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.leadManagementSystem2.Service.LeadService;

import jakarta.servlet.http.HttpSession;

@Service
public class LeadServiceImpl implements LeadService {
	
	@Override
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
		
	}

}
