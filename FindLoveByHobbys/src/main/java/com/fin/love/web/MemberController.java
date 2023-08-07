package com.fin.love.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.love.dto.member.MemberLogInDto;
import com.fin.love.dto.member.MemberSignUpDto;

import com.fin.love.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;

	
	@GetMapping("/login")
	public void login(Model model, HttpSession session) {
		
		log.info("login() GET");
		String userid = (String) session.getAttribute("username");
		session.setAttribute("userid", userid);
		log.info("userid=({})",userid);
		model.addAttribute("userid",userid);
	}
	
	
	@GetMapping("/signup")
	public void signup () {
		log.info("signup() GET");
		
	}
	

	@PostMapping("/signup")
	public String createId(MemberSignUpDto dto, 
			@RequestParam("useraddress") String userAddress,
			@RequestParam("userdetailaddress") String userDetailAddress,
			@RequestParam("useraddressnotes") String userAddressNotes) {
		log.info("create({}) POST", dto);
		
		// 주소 api 정보 합치기
		String combinedAddress = userAddress + " " + userDetailAddress + " " + userAddressNotes;
		log.info("combinedAddress=({})",combinedAddress);
		
		dto.setAddress(combinedAddress);
		
		String id = memberService.signUp(dto);
		log.info("signup id = {}", id);
		
		return "/member/login";
	}
	
	
	

}
