package com.web.curation.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.MyRef;

public interface MyRefRepo extends JpaRepository<MyRef, Long>{
	
	ArrayList<MyRef> findByEmail(String email);
}
