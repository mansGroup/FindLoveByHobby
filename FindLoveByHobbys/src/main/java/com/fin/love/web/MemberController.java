package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.dto.member.MemberSignUpDto;
import com.fin.love.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	
	
	@GetMapping("/signup")
	public void signup () {
		log.info("signup() GET");
		
	}
	
	@PostMapping("/signup")
	public String createId(MemberSignUpDto dto) {
		log.info("create({}) POST", dto);
		
		memberService.signup(dto);
		
		return "landing/index";
	}
	
}
