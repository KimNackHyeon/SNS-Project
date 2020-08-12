package com.web.curation.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.GroupMember;

@Repository
public interface GroupMemberRepo extends JpaRepository<GroupMember, Long>{

	ArrayList<GroupMember> findByGroupNo(String groupno);
	

//   GroupBuying findByNo(Long no);
   
   

}