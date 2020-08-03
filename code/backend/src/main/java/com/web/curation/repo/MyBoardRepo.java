package com.web.curation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.MyBoard;

public interface MyBoardRepo extends JpaRepository<MyBoard, Long> {
	Optional<MyBoard> getMyBoardByEmail(String email);
}
