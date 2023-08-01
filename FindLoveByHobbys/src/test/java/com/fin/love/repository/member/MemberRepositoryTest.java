package com.fin.love.repository.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MemberRepositoryTest {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void test() {
		Member member = memberRepository.findById("admin").orElseThrow();
		log.info(member.toString());
	}

}
