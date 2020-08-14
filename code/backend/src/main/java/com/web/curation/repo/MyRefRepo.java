package com.web.curation.repo;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.model.MyRef;

public interface MyRefRepo extends JpaRepository<MyRef, Long>{
	
	ArrayList<MyRef> findByEmail(String email);
	
	MyRef findByEmailAndName(String email, String name);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE MyRef a SET a.amount = (a.amount+:amount) where (a.email = :email and a.name = :name)", nativeQuery = false)
	void update(String email,  String name, Long amount);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM MyRef a where (a.email = :email and a.name = :name)", nativeQuery = false)
	void deleteByEmailAndName(String email, String name);
}
