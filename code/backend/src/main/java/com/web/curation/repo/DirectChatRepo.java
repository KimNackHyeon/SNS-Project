package com.web.curation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.DirectChat;

@Repository
public interface DirectChatRepo extends JpaRepository<DirectChat, Long>{

	Optional<DirectChat> findByChatName(String chatName);
	
}
