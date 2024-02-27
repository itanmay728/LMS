package com.example.leadManagementSystem2.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.leadManagementSystem2.Entity.Users_Credentials;

public interface User_Credentials_Repository extends JpaRepository<Users_Credentials, UUID> {

	@Query("select u from Users_Credentials u where u.userName = :userName")
	public Users_Credentials getUsersCredentialsByUserName(@Param("userName") String userName);
	
	
}
