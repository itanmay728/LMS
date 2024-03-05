package com.example.leadManagementSystem2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.leadManagementSystem2.Entity.Users_Credentials;
import com.example.leadManagementSystem2.Repository.User_Credentials_Repository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	User_Credentials_Repository credentials_Repository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Users_Credentials  users_Credentials = credentials_Repository.getUsersCredentialsByUserName(username);
		
		
		if(users_Credentials == null) {
			throw new UsernameNotFoundException("user not found !!");
		}
		
		CustomUserDetails CustomUserDetails = new CustomUserDetails(users_Credentials);
		
		return CustomUserDetails;
	}



}
