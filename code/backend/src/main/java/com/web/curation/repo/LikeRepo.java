package com.web.curation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.LikeFeed;

@Repository
public interface LikeRepo extends JpaRepository<LikeFeed, Long>{
	Optional<LikeFeed> findByEmailAndFeedNo(String email, Long feedNo);

	List<LikeFeed> findByEmail(String email);
}
