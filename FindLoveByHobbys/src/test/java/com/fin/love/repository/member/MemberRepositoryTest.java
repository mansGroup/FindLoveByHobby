//package com.fin.love.repository.member;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.fin.love.respository.member.Member;
//import com.fin.love.respository.member.MemberRepository;
//import com.fin.love.service.MemberService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@SpringBootTest
//public class MemberRepositoryTest {
//	
//	@Autowired
//	private MemberRepository memberRepository;
//	
////	@Test
////	public void test() {
////		Member member = memberRepository.findById("admin").orElseThrow();
////		log.info(member.toString());
////	}
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private MemberService memberService;
//	
//	 @Test
//	 @DisplayName("패스워드 암호화 테스트")
//	   void passwordEncode() {
//	      
//	      String rawPassword = "12345678";
//
//	      
//	      String encodedPassword = passwordEncoder.encode(rawPassword);
//
//	      
//	      assertNotEquals(rawPassword, encodedPassword);
//	      assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
//	   } 
//}
