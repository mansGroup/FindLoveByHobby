package com.fin.love.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.profile.dto.ProfileCreateDto;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.profile.Age;
import com.fin.love.repository.profile.Height;
import com.fin.love.service.AgeService;
import com.fin.love.service.HeightService;
import com.fin.love.service.HobbyService;
import com.fin.love.service.ProfileService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private HttpSession session;
	
	private final ProfileService profileService;
	
	private final HobbyService hobbyService;	
	private final AgeService ageService;	
	private final HeightService heightService;
	
	// 취미, 나이, 키 리스트 불러서 뷰에 출력
	@GetMapping("/profiles")
	public String profileHome(Model model) {
		log.info("profileHome()");
		
//		String name = (String) session.getAttribute("name");
//		log.info(name);
//		ProfileReadUserInfoDto dto = profileService.readMemberInfo(name);
				
		List<Hobby> hobby = hobbyService.readHobbyList();	
		
		List<Age> age = ageService.readAgeList();
		
		List<Height> height = heightService.readHeightList();
		
		model.addAttribute("hobbys", hobby);		
		model.addAttribute("ages", age);		
		model.addAttribute("heights", height);
		
//		model.addAttribute("names", dto);
//		log.info("readMemberInfoSuccess!!!()");
		
		return "/profile/profiles";
	}
	
	// 클라이언트에서 받은 데이터를 DB로 넘겨줌
	@PostMapping("/user/profileimage")
	public String createProfile(ProfileCreateDto dto) {
		log.info("createProfile(dto={})POST", dto);
		
		profileService.createProfile(dto);
		
		return "/profile/profileimage";
	}
	

}
