package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.service.AnnouncementEventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/event")
public class AnnouncementEventController {
	
	private final AnnouncementEventService announcementEventService;
	
	@GetMapping("/announcementEvent")
	public void announcementEvent(@PathVariable("id") String id) {
		
		
	}
	
	
	
	// 테스트 페이지로 이동하는 메서드
	@GetMapping("/eventTest")
	public void eventTest(Model model) {
		String code = "a";
		model.addAttribute("id", code);
	}

}
