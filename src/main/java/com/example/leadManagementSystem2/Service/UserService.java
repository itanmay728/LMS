package com.example.leadManagementSystem2.Service;

import com.example.leadManagementSystem2.Entity.Users_Credentials;

public interface UserService {

	
    public Users_Credentials saveUser(Users_Credentials users_credentials);
	
	public void removeSessionMessage();
}
