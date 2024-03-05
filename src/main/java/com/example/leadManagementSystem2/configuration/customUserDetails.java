package com.example.leadManagementSystem2.configuration;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.leadManagementSystem2.Entity.Users_Credentials;

public class CustomUserDetails implements UserDetails {

	private Users_Credentials users_Credentials;
	
	
	public CustomUserDetails(Users_Credentials users_Credentials) {
		super();
		this.users_Credentials = users_Credentials;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		SimpleGrantedAuthority SimpleGrantedAuthority = new SimpleGrantedAuthority(users_Credentials.getRole());
		
		return List.of(SimpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		
		return users_Credentials.getPassword();
	}

	@Override
	public String getUsername() {
		
		return users_Credentials.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
