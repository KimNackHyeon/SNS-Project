package com.web.curation.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.curation.model.GroupBuying;

@Repository
public interface GroupBuyingRepo extends JpaRepository<GroupBuying, Long> {

	GroupBuying findByNo(Long no);

	@Modifying
	@Transactional
	@Query(value="UPDATE GroupBuying a SET a.now_people = a.now_people+1 where a.no = :group_no", nativeQuery = false)
	void updateMembercount(Long group_no);

}