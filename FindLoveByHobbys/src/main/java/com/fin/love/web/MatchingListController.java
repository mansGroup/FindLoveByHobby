package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.repository.profile.UserHobbyRepository;
import com.fin.love.respository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingListController {
	
	@GetMapping("/matchingList")
	public void listHome() {
        log.info("listHome()");
    }
	
	@GetMapping("/test")
	public void sssss() {
		
	}
	
}
