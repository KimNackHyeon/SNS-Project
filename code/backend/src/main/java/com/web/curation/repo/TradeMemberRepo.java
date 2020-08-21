package com.web.curation.repo;

import java.util.ArrayList;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.TradeMember;

@Repository
public interface TradeMemberRepo extends JpaRepository<TradeMember, Long>{

   ArrayList<TradeMember> findByTradeNo(String tradeNo);

	Optional<TradeMember> findByTradeNoAndParticipantEmail(String tradeNo, String participantEmail);
	
}
