package com.web.curation.repo;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.model.Trade;

public interface TradeRepo extends JpaRepository<Trade, Long>{
	ArrayList<Trade> findAll();
	ArrayList<Trade> findByMyfood(String content);
	Trade getByNo(Long no);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM Trade a where (a.no = :no)", nativeQuery = false)
	void deleteByNo(Long no);
	
	
//	@Modifying
//	@Transactional
//	@Query(value="UPDATE Trade a SET a.amount = (a.amount+:amount) where (a.no = :trade. and a.name = :name)", nativeQuery = false)
//	void update(Trade trade);
	
}
