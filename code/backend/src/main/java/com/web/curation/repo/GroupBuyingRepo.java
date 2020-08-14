package com.web.curation.repo;

import java.util.ArrayList;

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
	
	@Modifying
	@Transactional
	@Query(value="UPDATE GroupBuying a SET a.title = :title, a.food = :food, a.food_kor = :food_kor, a.end_date = :end_date, "
			+ "a.max_people = :max_people, a.content = :content, a.link = :link where a.no = :no", nativeQuery = false)
	void update(Long no, String title, String food, String food_kor, String end_date, Long max_people,
			String content, String link);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM GroupBuying a where a.no = :no", nativeQuery = false)
	void d(Long no);

}