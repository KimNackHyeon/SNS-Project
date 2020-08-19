package com.web.curation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Evaluate;

@Repository
public interface EvaluateRepo extends JpaRepository<Evaluate, Long>{
//	Optional<Alarm> findByEmailAndFeedNo(String email);

//	List<Evaluate> findByEmail(String email);
}
