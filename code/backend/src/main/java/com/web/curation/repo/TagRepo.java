package com.web.curation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Tag;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long>{
	
	List<Tag> findByFeedNo(Long feedNo);

	Tag findByFeedNoAndTagName(Long feedNo, String tagName);
	
	
}
