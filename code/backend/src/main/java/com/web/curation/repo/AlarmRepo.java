package com.web.curation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Alarm;

@Repository
public interface AlarmRepo extends JpaRepository<Alarm, Long>{
//	Optional<Alarm> findByEmailAndFeedNo(String email);

	List<Alarm> findByEmail(String email);
}
