package com.fin.love.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.repository.hobby.Hobby;
import com.fin.love.service.HobbyService;
import com.fin.love.service.ProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	private final ProfileService profileService;
	
	private final HobbyService hobbyService;
	
	// 취미 리스트
	@GetMapping("/profiles")
	public String profileHome(Model model) {
		log.info("profileHome()");
		
		List<Hobby> hobby = hobbyService.read();
		
		
		model.addAttribute("hobbys", hobby);
		
		return "/profile/profiles";
	}
	

}
