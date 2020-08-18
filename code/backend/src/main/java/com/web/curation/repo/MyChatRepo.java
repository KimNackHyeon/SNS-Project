package com.web.curation.repo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.MyChat;

@Repository
public interface MyChatRepo extends JpaRepository<MyChat, Long>{

	ArrayList<MyChat> findByMyEmail(String myEmail);

	ArrayList<Optional<MyChat>> findByChatKey(String chatKey);

	MyChat findByChatKeyAndMyEmail(String chatkey, String email);

}
