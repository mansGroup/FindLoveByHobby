package com.fin.love.respository.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	List<Member> findBySex(int sexCode);
	
}
