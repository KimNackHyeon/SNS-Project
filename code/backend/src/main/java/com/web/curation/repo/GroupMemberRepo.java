package com.web.curation.repo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.GroupMember;

@Repository
public interface GroupMemberRepo extends JpaRepository<GroupMember, Long>{

	ArrayList<GroupMember> findByGroupNo(String groupno);

	Optional<GroupMember> findByGroupNoAndParticipantEmail(String groupNo, String participantEmail);
	

//   GroupBuying findByNo(Long no);
   
   

}