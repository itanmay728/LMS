package com.example.leadManagementSystem2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leadManagementSystem2.Entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
