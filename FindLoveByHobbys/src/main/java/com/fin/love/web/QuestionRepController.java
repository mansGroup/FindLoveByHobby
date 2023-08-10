package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/questionrep")
public class QuestionRepController {
	
	@GetMapping("/qrepcreate")
	public void qrepcreate() {
		
		log.info("qrepcreate()");
		
	}
	
}
