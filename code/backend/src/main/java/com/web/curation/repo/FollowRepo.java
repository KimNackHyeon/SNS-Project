package com.web.curation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Follow;

@Repository
public interface FollowRepo extends JpaRepository<Follow, Long>{
	Long countByEmail(String email);
	Long countByYourEmail(String email);
	
}
