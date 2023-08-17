package com.fin.love.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import com.fin.love.respository.member.Role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {
	
	private final MemberRepository memberRepository;
	
	@GetMapping("/room")
	public String mypage(Model model) {
		log.info("mypage()");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String userId = authentication.getName();
	    
	    Member member = memberRepository.findById(userId).orElseThrow();
	    Role[] roles = Role.values();
	    
	    model.addAttribute("roles", roles);
	    model.addAttribute("user", member);
		
		return "/mypage/mypage";
	}
	
}
