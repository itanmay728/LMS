package com.example.leadManagementSystem2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.leadManagementSystem2.Entity.Manager;
import com.example.leadManagementSystem2.Entity.Users_Credentials;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

	@Query("select m from Manager m where m.userName = :userName")
	public Manager getManagerByUserName(@Param("userName") String userName);

}
