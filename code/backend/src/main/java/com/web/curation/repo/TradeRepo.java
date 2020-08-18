package com.web.curation.repo;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.curation.model.GroupMember;
import com.web.curation.model.Trade;
import com.web.curation.model.TradeMember;

@Repository
public interface TradeRepo extends JpaRepository<Trade, Long>{
	ArrayList<Trade> findAll();
	ArrayList<Trade> findByMyfood(String content);
	Trade getByNo(Long no);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM Trade a where (a.no = :no)", nativeQuery = false)
	void deleteByNo(Long no);
	
	
	@Modifying
	@Transactional
	@Query(value="UPDATE Trade a SET a.tradefood1 = :tradefood1, a.tradefood2 = :tradefood2, a.tradefood1_kor = :tradefood1_kor, a.tradefood2_kor = :tradefood2_kor, "
			+ "a.myfoodcount1 = :myfoodcount1, a.myfoodcount2 = :myfoodcount2, a.content = :content, a.address = :address, a.tradefoodcount1 = :tradefoodcount1, a.tradefoodcount2 = :tradefoodcount2  where a.no = :no", nativeQuery = false)
	void update(Long no, String tradefood1, String tradefood2, String tradefood1_kor, String tradefood2_kor,
			String myfoodcount1, String myfoodcount2, String content, String address, String tradefoodcount1,
			String tradefoodcount2);
	
	
	
	
	
	
}
