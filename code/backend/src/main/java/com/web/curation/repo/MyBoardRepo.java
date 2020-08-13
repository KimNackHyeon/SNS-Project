package com.web.curation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.MyBoard;

@Repository
public interface MyBoardRepo extends JpaRepository<MyBoard, Long> {
//	Optional<MyBoard> getMyBoardByEmail(String email);
	Long countByEmail(String email);
	List<MyBoard> findAll();

	Optional<MyBoard> findMyBoardByNo(Long feedNo);

	MyBoard findTopByOrderByNoDesc();
	
	List<MyBoard> findByEmail(String email);
}
