package com.example.leadManagementSystem2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leadManagementSystem2.Entity.LeadsConversation;

public interface LeadsConversationRepository extends JpaRepository<LeadsConversation, Long> {

}
