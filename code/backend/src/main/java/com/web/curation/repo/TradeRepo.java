package com.web.curation.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.Trade;

public interface TradeRepo extends JpaRepository<Trade, Long>{
	ArrayList<Trade> findAll();
	ArrayList<Trade> findByMyfood(String content);
}
