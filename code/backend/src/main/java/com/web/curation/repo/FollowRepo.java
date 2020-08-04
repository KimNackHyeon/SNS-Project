package com.web.curation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.Follow;

public interface FollowRepo extends JpaRepository<Follow, Long>{
	Long countByEmail(String email);
	Long countByYourEmail(String email);
	
}
