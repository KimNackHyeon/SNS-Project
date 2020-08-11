package com.web.curation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.FeedData;

@Repository
public interface FeedDataRepo extends JpaRepository<FeedData, Long>{
	
	FeedData findByFeedNoAndTindex(Long feedNo, Long index);

	List<FeedData> findByFeedNo(Long feedNo);
	
	
}
