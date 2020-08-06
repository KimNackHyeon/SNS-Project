package com.web.curation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Follow;

@Repository
public interface FollowRepo extends JpaRepository<Follow, Long>{
	Long countByEmail(String email);
	Long countByYourEmail(String email);

	List<Follow> findByEmail(String email);
	List<Follow> findByYourEmail(String email);
	Optional<Follow> findUserByEmailAndYourEmail(String email, String yourEmail);
	
}
