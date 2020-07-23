
package com.web.curation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    Member getUserByEmail(String email);

    Optional<Member> findUserByEmailAndPassword(String email, String password);
    
    Optional<Member> findByEmail(String email);

}
