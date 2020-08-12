package com.web.curation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Scrap;

@Repository
public interface ScrapRepo extends JpaRepository<Scrap, Long>{
	Optional<Scrap> findByEmailAndFeedNo(String email, Long feedNo);

	List<Scrap> findByEmail(String email);
}
