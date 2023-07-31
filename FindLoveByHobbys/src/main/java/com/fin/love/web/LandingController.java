package com.fin.love.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@Controller
public class LandingController {

	@GetMapping("/")
	public String home(Model model) {
		log.info("landing()");
		
		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now", now);
		
		return "/landing/index";
	}
	
	
	
}
