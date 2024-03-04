package com.example.leadManagementSystem2.configuration;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		String username = authentication.getName();
	    request.getSession().setAttribute("username", username);

		if (roles.contains("ROLE_ADMIN")) {
//			String username = authentication.getName();
//		    request.getSession().setAttribute("username", username);
		    response.sendRedirect("/Admin/admin_Dashboard");
		} 
		else if (roles.contains("ROLE_CALLER")) {
			response.sendRedirect("/Caller/Caller_Dashboard");
		}
		else if (roles.contains("ROLE_FIELDMANAGER")) {
			response.sendRedirect("/fieldManager/fieldManagerDashboard");
		}
		else if (roles.contains("ROLE_BUSINESSASSOCIATE")) {
			
			response.sendRedirect("/businessAssociate/businessAssociateDashboard");
		}
		else {
			response.sendRedirect("/user/profile");
		}

	}

}
