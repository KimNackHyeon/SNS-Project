package com.web.curation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
//	Optional<MyBoard> getMyBoardByEmail(String email);
	
	List<Comment> findByFeedNo(Long feedNo);
}
