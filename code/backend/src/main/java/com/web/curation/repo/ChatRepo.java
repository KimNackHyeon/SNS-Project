package com.web.curation.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.ChatRoom;

public interface ChatRepo extends JpaRepository<ChatRoom, Long>{
	ArrayList<ChatRoom> findByEmail(String email);

	
	
	
}
