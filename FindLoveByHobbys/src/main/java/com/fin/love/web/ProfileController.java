package com.fin.love.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.profile.Age;
import com.fin.love.repository.profile.Height;
import com.fin.love.service.AgeService;
import com.fin.love.service.HeightService;
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
	private final AgeService ageService;	
	private final HeightService heightService;
	
	// 취미, 나이, 키 리스트 불러서 뷰에 출력
	@GetMapping("/profiles")
	public String profileHome(Model model) {
		log.info("profileHome()");
		
		List<Hobby> hobby = hobbyService.readHobbyList();	
		
		List<Age> age = ageService.readAgeList();
		
		List<Height> height = heightService.readHeightList();
		
		model.addAttribute("hobbys", hobby);		
		model.addAttribute("ages", age);		
		model.addAttribute("heights", height);
		
		return "/profile/profiles";
	}

	
	@PostMapping("/profiles")
	public String smokerCheck(@RequestParam("smoker") String smoker) {
		
		
		return "redirect:/index";
	}

}
