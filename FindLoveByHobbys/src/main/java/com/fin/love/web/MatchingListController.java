package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.repository.image.Picture;
import com.fin.love.repository.profile.UserHobbyRepository;
import com.fin.love.respository.member.MemberRepository;
import com.fin.love.service.MatchingService;
import com.fin.love.service.PictureService;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingListController {
	
	private final MatchingService matchingService;
	private final PictureService pictureService;
	
	@GetMapping("/matchingList/{userId}")
	public void listHome(@PathVariable String userId, Model model) {
        log.info("listHome()");
        
        Picture pic = pictureService.findById(userId);
        
    }
	
	@GetMapping("/test")
	public void sssss() {
		
	}
	
}
