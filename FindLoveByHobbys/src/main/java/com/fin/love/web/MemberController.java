package com.fin.love.web;


import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fin.love.dto.member.MemberLogInDto;
import com.fin.love.dto.member.MemberSignUpDto;
import com.fin.love.repository.profile.Profile;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.Role;
import com.fin.love.service.MatchingDetailService;
import com.fin.love.service.MemberService;
import com.fin.love.service.profile.ProfileService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	private final ProfileService profileService;
	private final MatchingDetailService matchingDetailService;

	
	@GetMapping("/login")
	public void login() {
	log.info("login() GET");
	}
	
	@GetMapping("/signup")
	public void signup () {
		log.info("signup() GET");
		
	}
	
	@GetMapping("/searchid")
	public void searchId () {
		log.info("searchId() GET");
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
		dto.setRole(Role.UNVARIFIED_USER);
		log.info(combinedAddress);
		dto.setAddress(combinedAddress);
		
		String id = memberService.signUp(dto);
		log.info("signup id = {}", id);
		
		matchingDetailService.newCreat(dto.getUserid());
		
		return "/member/login";
	}
	
	@GetMapping("/loginsuccess")
	public String loginscs(HttpSession session) {
		
		log.info("로그인 완료");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = authentication.getName();
        
        session.setAttribute("userid", userid);
		log.info("userid=({})",userid);
        
		Profile profile = profileService.findById(userid);
		Member member = memberService.login(userid);
		
		if (profile == null) {
			return "redirect:/profile/profiles";
		}
		
		log.info("member.getRole() >> " + member.getRole());
		
		if (member.getRole() == Role.UNVARIFIED_USER || member.getRole() == Role.RIP_USER) {
			
			return "redirect:/member/unvarified";
		}
		
		return "redirect:/matching/matchingList/" + userid; 
		
	}
	

}
