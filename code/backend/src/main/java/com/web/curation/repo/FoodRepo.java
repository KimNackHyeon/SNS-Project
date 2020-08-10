package com.web.curation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Food;

@Repository
public interface FoodRepo extends JpaRepository<Food, Long>{
	
	List<Food> findByFeedNo(Long feedNo);
	
}
