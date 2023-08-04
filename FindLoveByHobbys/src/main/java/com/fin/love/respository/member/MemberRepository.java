package com.fin.love.respository.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	int countByNickname(String nickname);
	
}
