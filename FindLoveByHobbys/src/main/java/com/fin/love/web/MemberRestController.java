package com.fin.love.web;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fin.love.dto.member.MemberSignUpDto;
import com.fin.love.respository.member.Role;
import com.fin.love.service.MatchingDetailService;
import com.fin.love.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberRestController {
	
	private final MemberService memberService;
	private final MatchingDetailService matchingDetailService;
	
	@GetMapping("/{userid}")
	public ResponseEntity<Boolean> idDupleCheck(@PathVariable String userid) {
		log.info("idDuplecheck(id={})", userid);
		
		return ResponseEntity.ok(memberService.idDupleCheck(userid));
	}
	
	@PostMapping("/{nickname}")
	public ResponseEntity<Boolean> nickDupleCheck(@PathVariable String nickname) {
		log.info("nickDuplecheck(nickname={})", nickname);
		
		Boolean result = memberService.nickDupleCheck(nickname);
		
		return ResponseEntity.ok(result);
	}
	
}
