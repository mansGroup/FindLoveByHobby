package com.fin.love.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.love.repository.assessment.Assessment;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.profile.Profile;
import com.fin.love.repository.profile.UserHobby;
import com.fin.love.respository.member.Member;
import com.fin.love.service.MatchingDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/matching")
public class MatchingDetailController {

	private final MatchingDetailService matchingDetailService;
	
	@GetMapping("/matchingDetail")
	public String matchingDetail(@RequestParam("id") String id, Model model) {

		// 취미 목록 가져오기
		List<Hobby> hobbies = matchingDetailService.getAllHobbies();
		model.addAttribute("hobbies", hobbies);

		// id에 해당하는 Assessment 가져오기
		Assessment assessment = matchingDetailService.getUserAssessment(id);
		model.addAttribute("assessment", assessment);

		// id에 해당하는 Profile 가져오기
		Optional<Profile> profile = matchingDetailService.getUserProfile(id);
		model.addAttribute("profile", profile);

		// id에 해당하는 UserHobbies 가져오기
		List<UserHobby> userHobbies = matchingDetailService.getUserHobbies(id);
		model.addAttribute("userHobbies", userHobbies);

		Optional<Member> member = matchingDetailService.getUserinfo(id);
		model.addAttribute("member", member);
		
		return "matchingDetail";
	}

}
